package com.zkzn.les.oms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zkzn.les.common.pojo.StaticCode;
import com.zkzn.les.common.util.json.JsonUtil;
import com.zkzn.les.common.util.lang.BeanUtil;
import com.zkzn.les.common.util.page.PageUtil;
import com.zkzn.les.oms.dao.ProcessOrderDao;
import com.zkzn.les.oms.dao.ProcessOrderDetailDao;
import com.zkzn.les.oms.fegin.GawFeignService;
import com.zkzn.les.oms.pojo.ProcessOrder;
import com.zkzn.les.oms.pojo.ProcessOrderDetail;
import com.zkzn.les.oms.service.ProcessOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.Map.Entry;

//import com.zkzn.les.common.pojo.StaticCode;


@SuppressWarnings("unchecked")
@Service
@Slf4j
public class ProcessOrderServiceImpl implements ProcessOrderService {

    @Autowired
    private ProcessOrderDao processOrderDao;
    @Autowired
    private ProcessOrderDetailDao processOrderDetailDao;
    @Autowired
    private GawFeignService gawFeignService;

    private static Logger LOGGER = LoggerFactory.getLogger(ProcessOrderServiceImpl.class);
    @Override
    public int saveProcessOrder(List<ProcessOrder> order) {
        // TODO Auto-generated method stub
        return processOrderDao.saveProcessOrder(order);
    }

    @Override
    public List<Map<String, Object>> listLockOrder(Map<String, Object> param) {
        // TODO Auto-generated method stub
        return processOrderDao.listLockOrder(param);
    }

    @Override
    public List<Map<String, Object>> listMesOrderInfo(String id) {
        // TODO Auto-generated method stub
        return processOrderDao.listMesOrderInfo(id);
    }



    public List<Map<String, Object>> listAbnormalMaterialWorkshopBL(String taskcode, String storageLocation,List<String> ids) {
        // TODO Auto-generated method stub
        return processOrderDao.listAbnormalMaterialWorkshopBL(taskcode,storageLocation,ids);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void doLockOrder(String id) {
        // TODO Auto-generated method stub
        List<Map<String, Object>> waitLockList = listMesOrderInfo(id);
        String factory = null;//工厂
        String workshopCode = null;//车间
        String lineCode = null;//产线
        List<String> orderCodes = new ArrayList<>();//订单号
        List<String> vinCodes = new ArrayList<>();//vin号
        //存放每个订单对应的 顺序号和上线时间
        Map<String, Object> orderMap = new HashMap<>();

        for (Map<String, Object> map : waitLockList) {
            if (map.get("orderCode") != null) {
                orderCodes.add("" + map.get("orderCode"));
            }
            if (map.get("vinCode") != null) {
                vinCodes.add("" + map.get("vinCode"));
            }
            factory = "" + map.get("factory");
            workshopCode = "" + map.get("shopCode");
            lineCode = "" + map.get("lineCode");
            orderMap.put("" + map.get("orderCode"), map);
        }
        Map<String, Object> param = new HashMap<>();
        param.put("orderCodes", orderCodes);
        param.put("vinCodes", vinCodes);
        param.put("factory", factory);
        param.put("lineCode", lineCode);
        param.put("workshopCode", workshopCode);
        List<Map<String, Object>> orderList = listLockOrder(param);
        //生产锁定订单
        crateOrder(orderMap, orderList, workshopCode, lineCode);
        //更新mes锁定计划表状态
        processOrderDao.updateMesLockPlanStatus(id);
    }


    /**
     * .
     *
     * @param orderMap
     * @param orderList
     * @param workshopCode
     * @param lineCode
     * @Author:wangzhou
     * @date:2020年8月28日
     * @Description:生成锁定订单
     */
    private void crateOrder(Map<String, Object> orderMap, List<Map<String, Object>> orderList, String workshopCode, String lineCode) {
        List<ProcessOrder> processOrderList = new ArrayList<>();
        ProcessOrder processOrder = null;
        List<ProcessOrderDetail> processOrderDetailList = new ArrayList<>();
        ProcessOrderDetail processOrderDetail = null;
        String orderUuid = null;
        String orderCode = null;
        Set<String> orderCodes = new HashSet<>();

        Map<String, Object> orderTempMap = null;
        //需要推送给总装立库的生产订单
        List<Map<String, Object>> toGawList = new ArrayList<>();
        String stationCode = null;
        for (Map<String, Object> tempMap : orderList) {
            processOrder = (ProcessOrder) BeanUtil.mapToObject(tempMap, ProcessOrder.class);
            processOrder.setWorkshopCode(workshopCode);
            processOrder.setLineCode(lineCode);
            processOrder.setBusinessType("1");//业务类型为计划拉动
            processOrderDetail = (ProcessOrderDetail) BeanUtil.mapToObject(tempMap, ProcessOrderDetail.class);
            stationCode = processOrderDetail.getStationCode();
            //替换原订单工位中的产线 值
//            stationCode = replaceLineCode(stationCode, lineCode);
//            processOrderDetail.setStationCode(stationCode);

            orderCode = processOrder.getOrderCode();
            if (!orderCodes.contains(orderCode)) {
                orderTempMap = (Map<String, Object>) orderMap.get(orderCode);
                if (orderTempMap.get("onlineTime") != null) {
                    processOrder.setRequiredTime((Date) orderTempMap.get("onlineTime"));
                }
                if (orderTempMap.get("onlineSeqnumber") != null) {
                    processOrder.setPriority("" + orderTempMap.get("onlineSeqnumber"));
                }
                processOrder.setCreateTime(new Date());
                //orderUuid = UuidUtil.getUUID();
                processOrder.setId(orderUuid);
                processOrderList.add(processOrder);
                orderCodes.add(orderCode);
            }
            if (StaticCode.gawStorageLocation.equals(processOrderDetail.getStorageLocation())) {//总装立库的订单推
                tempMap.put("stationCode", stationCode);
                toGawList.add(tempMap);
            }
            processOrderDetail.setCreateTime(new Date());
            processOrderDetail.setStatus(0);
            processOrderDetail.setProcessOrderId(orderUuid);
            processOrderDetailList.add(processOrderDetail);
        }
        log.info("生产入ProcessDetail数据表======={}", JsonUtil.toJson(processOrderDetailList));
        if (processOrderDetailList.size() > 0) {
            if(processOrderDetailList.size() >5000){
                List<ProcessOrderDetail> tempList = new ArrayList<>();
                for(ProcessOrderDetail p:processOrderDetailList){
                    tempList.add(p);
                    if(tempList.size()%5000==0){
                        processOrderDetailDao.saveLostOrderDetail(tempList);
                        tempList.clear();
                    }
                }
                if(tempList.size()>0){
                    processOrderDetailDao.saveLostOrderDetail(tempList);
                }
            }else{
                processOrderDetailDao.saveLostOrderDetail(processOrderDetailList);
            }
        }
        if (processOrderList.size() > 0) {
            saveProcessOrder(processOrderList);
        }
        if (toGawList.size() > 0) {//推送生产计划
            pushToGawSystem(toGawList, orderMap);
        }
    }

    /**
     * .
     *
     * @param stationCode
     * @param lineCode
     * @Author:wangzhou
     * @date:2020年8月28日
     * @Description:替换原订单工位中的产线 值
     */
    private String replaceLineCode(String stationCode, String lineCode) {
        if (stationCode != null && stationCode.length() > 0) {
            char[] character = stationCode.toCharArray();
            character[3] = lineCode.charAt(0);
            stationCode = new String(character);
            return stationCode;
        }
        return null;
    }

    /**
     * .
     *
     * @param toGawList
     * @param orderMap
     * @Author:wangzhou
     * @date:2020年8月31日
     * @Description:向总装立库推送 立库的生产计划
     */
    private void pushToGawSystem(List<Map<String, Object>> toGawList, Map<String, Object> orderMap) {

        List<Map<String, Object>> pushList = new ArrayList<>();//要推送的订单集合
        Map<String, Object> orderKey = new HashMap<>();//以订单为key存放订单头信息
        String orderCode = null;
        Map<String, Object> headMap = new HashMap<>();//每个订单头信息
        Map<String, Object> orderTempMap = null;//以订单号为key存储生产时间和生产序号
        List<Map<String, Object>> materialList = null;//物料集合
        Map<String, Object> materialMap = null;//存放每个物料信息
        for (Map<String, Object> tempMap : toGawList) {
            orderCode = (String) tempMap.get("orderCode");
            if (orderKey.containsKey(orderCode)) {

                headMap = (Map<String, Object>) orderKey.get(orderCode);
                materialList = (List<Map<String, Object>>) headMap.get("materialList");
                materialMap = new HashMap<>();
                materialMap.put("materialCode", tempMap.get("materialCode"));
                materialMap.put("materialNum", tempMap.get("requiredNum"));
                materialMap.put("stationCode", tempMap.get("stationCode"));
                materialMap.put("itemNo", tempMap.get("materialRow"));
                materialMap.put("supplierCode", null);
                materialMap.put("supplierName", null);
                materialMap.put("batchNo", null);
                materialList.add(materialMap);
                headMap.put("materialList", materialList);
                orderKey.put(orderCode, headMap);
            } else {
                headMap = new HashMap<String, Object>();
                //订单号
                headMap.put("productionPlanCode", orderCode);
                orderTempMap = (Map<String, Object>) orderMap.get(orderCode);
                //生产时间
                headMap.put("productionPlanDate", orderTempMap.get("onlineTime"));
                //生产序号
                headMap.put("orderNum", orderTempMap.get("onlineSeqnumber"));

                materialList = new ArrayList<>();
                materialMap = new HashMap<>();
                materialMap.put("materialCode", tempMap.get("materialCode"));
                materialMap.put("materialNum", tempMap.get("requiredNum"));
                materialMap.put("stationCode", tempMap.get("stationCode"));
                materialMap.put("itemNo", tempMap.get("reserveRow"));
                materialMap.put("supplierCode", null);
                materialMap.put("supplierName", null);
                materialMap.put("batchNo", null);
                materialList.add(materialMap);
                headMap.put("materialList", materialList);
                orderKey.put(orderCode, headMap);
            }
        }
        if (!orderKey.isEmpty()) {
            Set<Entry<String, Object>> set = orderKey.entrySet();
            Iterator<Entry<String, Object>> iterator = set.iterator();
            Entry<String, Object> tempEntry = null;
            while (iterator.hasNext()) {
                tempEntry = iterator.next();
                pushList.add((Map<String, Object>) tempEntry.getValue());
            }
            String jsonStr = JsonUtil.toJson(pushList);
            gawFeignService.pushProcessOrder(jsonStr);
        }

    }

    /**
     * @param processOrder
     * @return
     * @Author:luozhihong
     * @date:2020年9月2日
     * @Description:分页查询物料需求清单
     */
    public PageInfo<ProcessOrder> listProcessOrder(ProcessOrder processOrder) {

        PageUtil.setPageParam(processOrder);
        if(StringUtils.isEmpty(processOrder.getBusinessType())){
            processOrder.setBusinessType("1;2");
        }
        String requiredTimeStr = processOrder.getRequiredTimeStr();//创建时间
        if (!"".equals(requiredTimeStr) && requiredTimeStr != null) {
            String[] split = requiredTimeStr.split(" - ");
            processOrder.setStartRequiredTime(split[0]);
            processOrder.setEndRequiredTime(split[1]);
        }
        List<ProcessOrder> list = processOrderDao.listProcessOrder(processOrder);
        setWarehouseName(list);
        PageInfo<ProcessOrder> pageInfo = new PageInfo<ProcessOrder>(list);
        return pageInfo;
    }

    @Override
    public List<ProcessOrder> listAllProcessOrder(ProcessOrder processOrder) {
        // TODO Auto-generated method stub
        String requiredTimeStr = processOrder.getRequiredTimeStr();//创建时间
        if (!"".equals(requiredTimeStr) && requiredTimeStr != null) {
            String[] split = requiredTimeStr.split(" - ");
            processOrder.setStartRequiredTime(split[0]);
            processOrder.setEndRequiredTime(split[1]);
        }
        List<ProcessOrder> list = processOrderDao.listAllProcessOrder(processOrder);
        setWarehouseName(list);
        return list;
    }

    /**
     * @param list
     * @return
     * @Author:luozhihong
     * @date:2020年9月2日
     * @Description:设置订单对应的仓库列表
     */
    private void setWarehouseName(List<ProcessOrder> list ){
        for (ProcessOrder item:list) {
            StringBuilder sbWarehouseName= new StringBuilder();
            List<String> warehouseNames =processOrderDao.getWarehouseNames(item.getId());
            for (String warehouseName:warehouseNames) {
                sbWarehouseName.append(warehouseName);
                sbWarehouseName.append(",");
            }
            String warehouseName=null;
            if(sbWarehouseName.length()>0){
                warehouseName=sbWarehouseName.toString();
            }
            if(warehouseName!=null){
                item.setWarehouseName(warehouseName.substring(0,warehouseName.length()-1));
            }

        }
    }
    @Override
    public void orderSplit() {
        List<String> mesLockPlanIds = processOrderDao.getUnOrderSplit();
        if (mesLockPlanIds.size()>0){
            for (int i = 0; i < mesLockPlanIds.size(); i++) {
                doLockOrder(mesLockPlanIds.get(i));
            }
        }
    }

    @Override
    public List<ProcessOrder> listSortProcessOrder(ProcessOrder processOrder) {
        processOrder.setLimit(80);
        List<ProcessOrder> list = processOrderDao.listSortProcessOrder(processOrder);
        return list;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void doFillMaterialOrder(Map<String, Object> paraMaps) throws Exception {
        // TODO Auto-generated method stub
        //必传参数校验
        String[] key = {"params"};
       // ParamUtil.paramsCheck(paraMaps, key);
        //Map paraMap = (Map) paraMaps.get("params");
        JSONObject paraMap = JSONObject.parseObject(paraMaps.get("params").toString());
        String taskCode = String.valueOf(paraMap.get("taskCode"));
        List<String> list = (List<String>) paraMap.get("ids");
        String storageLocation = paraMap.get("storageLocation") != null ? String.valueOf(paraMap.get("storageLocation")) : null;
        String abnormalCode = paraMap.get("abnormalCode") != null ? String.valueOf(paraMap.get("abnormalCode")) : null;
        LOGGER.info("接收到rabbitMq消息:[{}]",paraMap.toString());
        List<Map<String, Object>> fillMaterialList = listAbnormalMaterialWorkshopBL(taskCode,storageLocation,list);
        LOGGER.info("查询到退补料异常信息:[{}]",fillMaterialList.toString());
        if (abnormalCode != null && "4".equals(abnormalCode)) {
            //退补料 退料数量=<点收数量
            List<Map<String,Object>> materials = (List)paraMap.get("materials");
            //异常接受
            for (Map<String, Object> taskCodeOfMaterialCodes : fillMaterialList) {
                    //点收数量
                for (Map<String, Object> receiveCodes : materials) {
                    if (taskCodeOfMaterialCodes.get("materialCode").toString().equals(receiveCodes.get("materialCode").toString())) {
                        taskCodeOfMaterialCodes.put("materialNum",receiveCodes.get("receiveNum"));
                    }
                }
            }
        }
        //缺料呼叫更新订单，更新呼叫时间和呼叫数量
        if("1".equals(abnormalCode)){
            updateProcessOrderDetailCallInfo(fillMaterialList);
        }else{

            String workshopCode = null;//车间
            String lineCode = null;//产线
            for (Map<String, Object> map : fillMaterialList) {
                workshopCode = "" + map.get("shopCode");
                lineCode = "" + map.get("lineCode");
            }
            List<Map<String, Object>> orderList = fillMaterialOrder(fillMaterialList, abnormalCode);
            //生产锁定订单
            createFillMaterialOrder(orderList, workshopCode, lineCode);
        }

    }
    /**
     * @param fillMaterialList
     * @Author:luozhihong
     * @date:2020年11月04日
     * @Description:更新生产订单呼叫信息
     */
    private void updateProcessOrderDetailCallInfo(List<Map<String, Object>> fillMaterialList) {
        //封装需要更新的生产列表
        List<ProcessOrderDetail> processOrderDetailList = new ArrayList<ProcessOrderDetail>();
        for (Map<String, Object> item:fillMaterialList) {
            ProcessOrderDetail processOrderDetail = new ProcessOrderDetail();
            processOrderDetail.setOrderCode(item.get("orderCode").toString());
            processOrderDetail.setMaterialCode(item.get("materialCode").toString());
            processOrderDetail.setCallTime((Date) item.get("createTime"));
            processOrderDetail.setCallNum(Double.valueOf(item.get("materialNum").toString()));
        }
        processOrderDetailDao.updateProcessOrderDetailCallInfo(processOrderDetailList);
    }

    /**
     * @param list
     * @Author:luozhihong
     * @date:2020年11月04日
     * @Description:补充补料订单数据
     */
    private List<Map<String, Object>> fillMaterialOrder(List<Map<String, Object>> list, String abnormalCode) {
        List<Map<String, Object>> orderList = new ArrayList<>();
        for (Map<String, Object> map : list) {
            Map<String, Object> orderMap = new HashMap<>();
           // orderMap.put("orderCode", map.get("taskcode"));orderCode
            orderMap.put("orderCode", map.get("orderCode"));
            orderMap.put("vinCode", map.get("vinCode"));
            orderMap.put("orderType", "");
            orderMap.put("vehicleCode",  map.get("vehicleCode"));
            orderMap.put("vehicleDesc", map.get("vehicleDesc"));
            orderMap.put("sellOrder", map.get("sellOrder"));
            orderMap.put("sellOrderItem", map.get("sellOrderItem"));
            orderMap.put("customCode", map.get("customCode"));
            orderMap.put("customDesc", "");
            orderMap.put("orderNum", map.get("materialNum"));
            orderMap.put("unit", map.get("materailUnit"));
            orderMap.put("priority", "");
            orderMap.put("serialNum", "");
            orderMap.put("factory", map.get("factory"));
            orderMap.put("orderId", map.get("orderId"));
            orderMap.put("reserveCode", map.get("reserveCode"));
            orderMap.put("reserveRow", map.get("reserveRow"));
            orderMap.put("materialRow", map.get("materialRow"));
            orderMap.put("materialCode", map.get("materialCode"));
            orderMap.put("requiredNum", map.get("materialNum"));
            orderMap.put("itemUnit", map.get("materailUnit"));
            orderMap.put("recoilMaterialTab", "");
            orderMap.put("bulkMaterialTab", "");
            orderMap.put("specialType", "");
            orderMap.put("storageLocation", map.get("storageLocation"));
            orderMap.put("doneActiveNo", "");
            orderMap.put("stationCode", map.get("stationCode"));
            orderMap.put("stationDesc", map.get("stationDesc"));
            orderMap.put("carModel", "");
            orderMap.put("businessType", abnormalCode);
            //orderMap.put("orderDetailId", UuidUtil.getUUID());
            orderMap.put("requiredTime", map.get("onlineTime"));
            orderList.add(orderMap);
        }
        return orderList;
    }

    /**
     * @param orderList
     * @param workshopCode
     * @param lineCode
     * @Author:luozhihong
     * @date:2020年10月28日
     * @Description:生成补料订单
     */
    private void createFillMaterialOrder(List<Map<String, Object>> orderList, String workshopCode, String lineCode) {


        List<ProcessOrder> processOrderList = new ArrayList<ProcessOrder>();
        ProcessOrder processOrder = null;
        List<ProcessOrderDetail> processOrderDetailList = new ArrayList<ProcessOrderDetail>();
        ProcessOrderDetail processOrderDetail = null;
        String orderUuid = null;
        String orderCode = null;
        Set<String> orderCodes = new HashSet<String>();

        //String stationCode = null;
        for (Map<String, Object> tempMap : orderList) {
            processOrder = (ProcessOrder) BeanUtil.mapToObject(tempMap, ProcessOrder.class);
            orderCode = processOrder.getOrderCode();
            if (!orderCodes.contains(orderCode)) {
                //orderUuid = UuidUtil.getUUID();
            }
            processOrder.setId(orderUuid);
            processOrder.setWorkshopCode(workshopCode);
            processOrder.setLineCode(lineCode);
            //订单类型(0-生产出库、1-调拨出库、2-领料、3-补料 4-退补料、5-退货采购订单)
            processOrderDetail = (ProcessOrderDetail) BeanUtil.mapToObject(tempMap, ProcessOrderDetail.class);
            processOrderDetail.setProcessOrderId(orderUuid);
//            stationCode = processOrderDetail.getStationCode();
//            //替换原订单工位中的产线 值
//            stationCode = replaceLineCode(stationCode, lineCode);
//            processOrderDetail.setStationCode(stationCode);
            if (!orderCodes.contains(orderCode)) {

                processOrder.setCreateTime(new Date());
                processOrderList.add(processOrder);
                orderCodes.add(orderCode);

            }
            processOrderDetail.setCreateTime(new Date());
            processOrderDetail.setStatus(0);
            processOrderDetailList.add(processOrderDetail);
        }

        if (processOrderDetailList.size() > 0) {

            if(processOrderDetailList.size() >5000){

                List<ProcessOrderDetail> tempList = new ArrayList<>();
                for(ProcessOrderDetail p:processOrderDetailList){
                    tempList.add(p);
                    if(tempList.size()%5000==0){
                        processOrderDetailDao.saveLostOrderDetail(tempList);
                        tempList.clear();
                    }
                }
                if(tempList.size()>0){
                    processOrderDetailDao.saveLostOrderDetail(tempList);
                }

            }else{
                processOrderDetailDao.saveLostOrderDetail(processOrderDetailList);
            }


        }

        if (processOrderList.size() > 0) {
            LOGGER.info("保存processOrder信息:[{}]",processOrderList.toString());
            saveProcessOrder(processOrderList);
        }


    }
}

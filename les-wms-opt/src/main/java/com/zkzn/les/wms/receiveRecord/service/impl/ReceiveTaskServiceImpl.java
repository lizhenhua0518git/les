package com.zkzn.les.wms.receiveRecord.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.zkzn.les.common.exception.EmptyExamine;
import com.zkzn.les.common.util.json.JsonUtil;
import com.zkzn.les.common.util.redis.RedisNoUtil;
import com.zkzn.les.common.util.str.StrUtil;
import com.zkzn.les.wms.constant.WmsConstants;
import com.zkzn.les.wms.dao.InspectionTaskDao;
import com.zkzn.les.wms.feign.StockFeignService;
import com.zkzn.les.wms.feign.TacticsFeignService;
import com.zkzn.les.wms.receiveRecord.dao.CarrierDao;
import com.zkzn.les.wms.receiveRecord.dao.ReceiveTaskDao;
import com.zkzn.les.wms.receiveRecord.pojo.ReceiveCarrierPojo;
import com.zkzn.les.wms.receiveRecord.pojo.SaveReceiveDetailPojo;
import com.zkzn.les.wms.receiveRecord.pojo.UpdateReceiveTaskPojo;
import com.zkzn.les.wms.receiveRecord.service.ReceiveTaskService;
import com.zkzn.les.wms.upperFrame.pojo.UpperFrameData;
import com.zkzn.les.wms.upperFrame.pojo.UpperPositionPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName ReceiveTaskServiceImpl
 * @Description 点收服务
 * @Author zhanglei
 * Date 2021/6/15 14:16
 * @Version 1.0
 **/
@Service
public class ReceiveTaskServiceImpl implements ReceiveTaskService {

    @Autowired
    private ReceiveTaskDao receiveTaskDao;
    @Autowired
    private CarrierDao carrierDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private InspectionTaskDao inspectionTaskDao;
    @Autowired
    private StockFeignService stockFeignService;
    @Autowired
    private TacticsFeignService tacticsFeignService;
    /***
     * @Discription: 点收主表数据查询
     * @param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zhanglei on 2021/6/15 14:20
     */
    @Override
    public List<Map<String, Object>> listReceiveRecord(Map<String,Object> param) throws Exception {
        return receiveTaskDao.listReceiveTask(param);
    }

    /**
     * @Description : 点收详情列表数据查询
     * @Author : leizhang
     * @Date 4:09 下午 2021/6/19
     * @param param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @Override
    public List<Map<String, Object>> listReceiveRecordDetail(Map<String, Object> param) {
        return receiveTaskDao.listReceiveRecordDetail(param);
    }

    /**
     * 点收开始信息保存
     * @param updateReceiveTaskPojo
     */
    @Override
    public void saveReceiveInfoStart(UpdateReceiveTaskPojo updateReceiveTaskPojo) {
        //1.修改点收状态 记录点收开始时间
        updateReceiveTaskPojo.setReceiveTaskId(updateReceiveTaskPojo.getReceiveId());
        updateReceiveTaskPojo.setReceiveStartTime(new Date());
        updateReceiveTaskPojo.setReceiveStatus(20);
        receiveTaskDao.updateReceiveTaskByPojo(updateReceiveTaskPojo);
        //2.更新子表点收状态
        receiveTaskDao.updateReceiveDetailByTaskId(updateReceiveTaskPojo);
    }

    /**
     * 保存点收详情信息
     * @param saveReceiveDetailPojo
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveReceiveRecordInfo(SaveReceiveDetailPojo saveReceiveDetailPojo){
        //1、修改点收详情任务状态
        saveReceiveDetailPojo.setReceiveDetailStatus(30);
        receiveTaskDao.updateReceiveDetailTask(saveReceiveDetailPojo);
        //2.保存载具信息
        List<ReceiveCarrierPojo> carrierList = saveReceiveDetailPojo.getCarrierList();
        carrierDao.saveCarrierInfo(carrierList);
        //3.调用仓位库存保存库存信息
        List<Map<String,Object>> materialInStockList = new ArrayList<>();
        for (int i = 0; i < carrierList.size(); i++) {
            Map<String,Object> materialInStockMap = new HashMap<>();
            ReceiveCarrierPojo receiveCarrierPojo = carrierList.get(i);
            materialInStockMap.put("materialDesc",receiveCarrierPojo.getMaterialDesc());//货物名称
            materialInStockMap.put("materialUnit",receiveCarrierPojo.getMaterialUnit());//货物单位
            materialInStockMap.put("clientName",saveReceiveDetailPojo.getClientName());//客户名称
            materialInStockMap.put("batchNo",receiveCarrierPojo.getBatchNo());//批次号
            materialInStockMap.put("warehouseCode",saveReceiveDetailPojo.getWarehouseCode());//仓库编号
            materialInStockMap.put("warehouseName",saveReceiveDetailPojo.getWarehouseName());//仓库名称
            materialInStockMap.put("qualifiedType",receiveCarrierPojo.getQualifiedType());//物料合格类型0：合格、1：不合格
            materialInStockMap.put("unlimitedNumber",receiveCarrierPojo.getCarryNumber());//非限制数量
            materialInStockList.add(materialInStockMap);
        }
        stockFeignService.updateMaterialInStockList(materialInStockList);
    }

    //校验点收区域and载具是否合法
    private void examine(List<Map<String,Object>> carrierList,int receiveDetailId) throws Exception{
        //校验点收区域是否合法
        List<Map<String, Object>> areaLists = receiveTaskDao.listArea(WmsConstants.NUMBER_2_STR,carrierList);
        List<Map<String, Object>> carriers = carrierDao.listCarrier(carrierList);
        if (carriers.isEmpty()) {
            throw  new Exception("载具信息不存在");
        }
        if (areaLists.isEmpty()) {
            throw  new Exception("点收区域不存在");
        }
        String areaCodeCode = null;
        String carrierCode = null;
        for (Map<String, Object> carrier : carrierList) {
            areaCodeCode = carrier.get(WmsConstants.AREA_CODE).toString();
            carrier.put(WmsConstants.RECEIVE_DETAIL_ID,receiveDetailId);
            for (Map<String, Object> area : areaLists) {
                if (area.get(WmsConstants.AREA_CODE).equals(areaCodeCode)) {
                    carrier.put(WmsConstants.AREA_ID,area.get(WmsConstants.ID));
                    carrier.put(WmsConstants.AREA_NAME,area.get(WmsConstants.AREA_NAME));
                    break;
                }
                throw new Exception("点收区域信息错误");
            }
            //校验载具是否合法
            carrierCode = carrier.get(WmsConstants.CARRIER_CODE).toString();
            for (Map<String, Object> car : carriers) {
                if (car.get(WmsConstants.CARRIER_CODE).equals(carrierCode)) {
                    carrier.put(WmsConstants.CARRIER_ID,car.get(WmsConstants.ID));
                    carrier.put(WmsConstants.CARRIER_NAME,car.get(WmsConstants.CARRIER_NAME));
                    carrier.put(WmsConstants.CARRIER_TYPE,car.get(WmsConstants.CARRIER_TYPE));
                    break;
                }
                throw new Exception("载具信息不存在");
            }
        }

    }

    //保存序列号
    private void saveMaterialSerial(List<Map<String, Object>> list, Map<String, Object> param,int taskType) {
        int isKey = (int)param.get(WmsConstants.IS_KEY);
        if (WmsConstants.NUMBER_1 == isKey) {// 物料是序列号管理
            List<Map<String, Object>> serialList = Lists.newArrayList();
            // 保存物料序列号信息
            String serialListJson = null;
            for (Map<String, Object> map : list) {
                 serialListJson = map.get(WmsConstants.SERIAL_LIST).toString();
                JSONArray jSONArray = JSONObject.parseArray(serialListJson);
                Map<String, Object> serialMap;
                JSONObject object;
                for (int i = 0; i < jSONArray.size(); i++) {
                     object = jSONArray.getJSONObject(i);
                     serialMap = new HashMap<>();
                    serialMap.put(WmsConstants.BATCH_NO, map.get(WmsConstants.BATCH_NO));
                    serialMap.put(WmsConstants.MATERIAL_CODE, param.get(WmsConstants.MATERIAL_CODE));
                    serialMap.put(WmsConstants.RECEIVE_ID, param.get(WmsConstants.RECEIVE_ID));
                    serialMap.put(WmsConstants.RECEIVE_TASK_CODE, param.get(WmsConstants.RECEIVE_TASK_CODE));
                    serialMap.put(WmsConstants.TASK_ID, map.get(WmsConstants.RECEIVE_DETAIL_ID));
//                    serialMap.put("curUserId", param.get("curUserId"));
//                    serialMap.put("curUserName", param.get("curUserName"));
                    serialMap.put(WmsConstants.CARRIER_TYPE, map.get(WmsConstants.CARRIER_TYPE));
                    serialMap.put(WmsConstants.CARRIER_ID, map.get(WmsConstants.CARRIER_ID));
                    serialMap.put(WmsConstants.CARRIER_CODE, map.get(WmsConstants.CARRIER_CODE));
                    serialMap.put(WmsConstants.SERIAL_NUM, object.get(WmsConstants.SERIAL_NUM));
                    serialMap.put(WmsConstants.CREATE_TIME, new Date());
                    serialMap.put(WmsConstants.TASK_TYPE,taskType);
                    serialList.add(serialMap);
                }
            }
            receiveTaskDao.saveMaterialSerial(serialList);
        }
    }

    //异常信息保存
    private void saveAbnormalMaterial(Map<String, Object> param,int abnormalReason) throws Exception {
        param.put(WmsConstants.ABNORMAL_REASON,abnormalReason);
        receiveTaskDao.saveAbnormalMaterialInfo(param);
    }
    //库存信息
    private void getStorageBinInfo (Map<String,Object> allDetailMap,Map<String, Object> param) {
        allDetailMap.put(WmsConstants.STOCK_STATUS, WmsConstants.NUMBER_2);
//        allDetailMap.put(WmsConstants.SAP_STORAGE_LOCATION, SapStorageLocationTransfrom.lesToSapStorageLocation(redisTemplate,
//                allDetailMap.get(WmsConstants.STORAGE_LOCATION).toString()));
        // 库存类型 0 一般库存 、1寄售、2委外、3订单 4 客户
        if ("M".equals(allDetailMap.get(WmsConstants.SUBJECT_TYPE))){
            allDetailMap.put(WmsConstants.STOCK_TYPE, WmsConstants.NUMBER_3);
            allDetailMap.put(WmsConstants.SELL_ORDER,allDetailMap.get(WmsConstants.SALES_ORDER_CODE));
            allDetailMap.put(WmsConstants.SELL_ORDER_ITEM,allDetailMap.get(WmsConstants.SALES_ORDER_ITEM));
        }
        List<Map<String,Object>>  carrierList = (List)param.get(WmsConstants.CARRIER_LIST);
        //点收时 存入的为点收区编码跟点收id
        allDetailMap.put(WmsConstants.POSITION_CODE,carrierList.get(WmsConstants.NUMBER_0).get(WmsConstants.AREA_CODE));
        allDetailMap.put(WmsConstants.STORAGE_ID, carrierList.get(WmsConstants.NUMBER_0).get(WmsConstants.AREA_ID));
        allDetailMap.put(WmsConstants.STOCK_TYPE, WmsConstants.NUMBER_2);
        //供应商信息
        allDetailMap.put(WmsConstants.SUPPLIER_CODE,allDetailMap.get(WmsConstants.SHIPPER_CODE));
        allDetailMap.put(WmsConstants.SUPPLIER_NAME, allDetailMap.get(WmsConstants.SHIPPER_NAME));

    }

    /**
     * 点收结束
     * @param updateReceiveTaskPojo
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveReceiveInfoEnd(UpdateReceiveTaskPojo updateReceiveTaskPojo) {
        updateReceiveTaskPojo.setReceiveTaskId(updateReceiveTaskPojo.getReceiveId());
        List<UpperFrameData> upperFrameData = receiveTaskDao.getUpperFrameData(updateReceiveTaskPojo.getReceiveTaskId());
        updateReceiveTaskPojo.setReceiveStatus(30);
        updateReceiveTaskPojo.setReceiveEndTime(new Date());
        receiveTaskDao.updateReceiveTaskByPojo(updateReceiveTaskPojo);

        List<UpperPositionPojo> addUpperPositionPojo = new ArrayList<>();
        for (int i = 0; i < upperFrameData.size(); i++) {
            UpperFrameData upperFrameData1 = upperFrameData.get(i);
            Map<String,Object> map = new HashMap<>();
            map.put("batchNo",upperFrameData1.getBatchNo());
            map.put("clientName",upperFrameData1.getClientName());
            map.put("materialDesc",upperFrameData1.getMaterialDesc());
            map.put("warehouseCode",upperFrameData1.getWarehouseCode());
            map.put("warehouseName",upperFrameData1.getWarehouseName());
            map.put("upperType",upperFrameData1.getUpperType());
            receiveTaskDao.AddUpperFrameData(upperFrameData1);
            String storageCode = tacticsFeignService.getStorageCode(map);
            List<Map> storageCodeList = JsonUtil.jsonStrToList(storageCode);
            if (storageCodeList != null){
                for (int j = 0; j < storageCodeList.size(); j++) {
                    UpperPositionPojo upperPositionPojo = new UpperPositionPojo();
                    Map map1 = storageCodeList.get(j);
                    upperPositionPojo.setUpperFrameId(upperFrameData1.getUpperFrameId());
                    upperPositionPojo.setMaterialUnit(map1.get("materialUnit")+"");
                    upperPositionPojo.setStoragePositionId(Integer.parseInt(map1.get("storagePositionId")+""));
                    upperPositionPojo.setPositionCode(map1.get("positionCode")+"");
                    upperPositionPojo.setSumCount(Double.parseDouble(map1.get("sumCount")+""));
                    upperPositionPojo.setPositionStatus(Integer.parseInt(map1.get("positionStatus")+""));
                    upperPositionPojo.setPositionCarCode(map1.get("positionCarCode")+"");
                    addUpperPositionPojo.add(upperPositionPojo);
                }
            }
        }
        if (addUpperPositionPojo.size()>0){
            receiveTaskDao.addUpperPositionPojo(addUpperPositionPojo);
        }
//        //2.TODO 点收过账
//        RabbitMqMessage rabbitMqMessage = new RabbitMqMessage(param.get(WmsConstants.RECEIVE_ID).toString());
//        RabbitMqUtil.INSTANCE.sendMessage(JsonUtil.getBeanToJson(rabbitMqMessage),WmsConstants.SAP_QUEUE);
    }

    /***
     * @Discription: 点收完成，初始化质检信息
     *              1.检验质检区位置信息
     *              2.初始化质检区主表信息
     *              3.初始化质检区子表信息
     *              4.修改点收任务主子表任务状态
     *
     *            移库质检区请求数据格式：
     *            {\r\n" +
     * 			"	\"receiveId\": \"主表主键\",\r\n" +
     * 			"	\"inspectionList\": [{\r\n" +
     * 			"	    \"useDefaultInspect\": \"是否使用默认质检区地标\",\r\n" +
     * 			"		\"inspectPositionId\": \"地标id\",\r\n" +
     * 			"		\"receiveCarrierId\": \"物料托盘id\",\r\n" +
     * 			"		\"areaCode\": \"地标编码\"\r\n" +
     * 			"	}]\r\n" +
     * 			"}
     * @param param
     * @return void
     * @Author: zhanglei on 2021/6/21 15:46
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveInspectionInfo(Map<String, Object> param) throws Exception{
        EmptyExamine.examine(param,WmsConstants.RECEIVE_ID,WmsConstants.INSPECT_LIST);
        if (!(param.get(WmsConstants.INSPECT_LIST) instanceof List)) {
            throw new Exception("inspectionList 参数类型错误");
        }
        List<Map<String,Object>> inspectionLists = (List)param.get(WmsConstants.INSPECT_LIST);
        //1.校验质检区位置信息
        List<Map<String, Object>> areaLists = receiveTaskDao.listArea(WmsConstants.NUMBER_3_STR,inspectionLists);
//        if (inspectionLists.size() != areaLists.size()) {
//            throw new Exception("质检区信息错误");
//        }
        String areaCode = null;
        for (Map<String, Object> inspectionMap : inspectionLists) {
             areaCode = inspectionMap.get(WmsConstants.AREA_CODE).toString();
             if (StrUtil.isBlank(areaCode)) {
                 continue;
             }
            for (Map<String, Object> area : areaLists) {
                if (areaCode.equals(area.get(WmsConstants.AREA_CODE))) {
                    inspectionMap.put(WmsConstants.INSPECTION_POSITION_CODE,areaCode);
                    inspectionMap.put(WmsConstants.INSPECTION_POSITION_ID,area.get(WmsConstants.ID));
                    inspectionMap.put(WmsConstants.STATUS,WmsConstants.NUMBER_35);
                    break;
                }
            }
        }
        //2.初始化质检主表数据
        Map<String,Object> receiveTaskMap = receiveTaskDao.listReceiveTask(param).get(WmsConstants.NUMBER_0);
        if (receiveTaskMap.isEmpty()){
            throw new Exception("点收任务数据错误");
        }
        receiveTaskMap.putIfAbsent(WmsConstants.INSPECTION_TASK_CODE, RedisNoUtil.getSeqNo(RedisNoUtil.ZJ_ORDER_KEY_M,redisTemplate));
         inspectionTaskDao.saveInspectionInfo(receiveTaskMap);
        //3.保存子表信息
        List<Map<String, Object>> receiveRecordDetail = receiveTaskDao.listReceiveRecordDetail(param);
        int size = receiveRecordDetail.size();
        Map<String, Object> receiveDetail = null;
        for (int i = 0; i < size; i++) {
             receiveDetail = receiveRecordDetail.get(i);
            for (Map<String, Object> inspectionList : inspectionLists) {
                if (null ==inspectionList.get(WmsConstants.INSPECTION_POSITION_ID)) {
                    throw new Exception(String.format("质检区%s不存在",inspectionList.get(WmsConstants.AREA_CODE)));
                }
                receiveDetail.put(WmsConstants.INSPECTION_POSITION_ID,inspectionList.get(WmsConstants.INSPECTION_POSITION_ID));
                receiveDetail.put(WmsConstants.INSPECTION_POSITION_CODE,inspectionList.get(WmsConstants.INSPECTION_POSITION_CODE));
                receiveDetail.put(WmsConstants.SAP_ITEM_NO,i);
                receiveDetail.put(WmsConstants.INSPECTION_TASK_ID,receiveTaskMap.get(WmsConstants.ID));
                receiveDetail.put(WmsConstants.MATERIAL_NUM,receiveDetail.get(WmsConstants.ACTUAL_NUM));
                receiveDetail.put(WmsConstants.INSPECTION_TASK_CODE, RedisNoUtil.getSeqNo(RedisNoUtil.ZJ_ORDER_KEY,redisTemplate));
            }
        }
        inspectionTaskDao.saveInspectionDetailInfo(receiveRecordDetail);
        //4.修改点收表任务状态
        param.put(WmsConstants.STATUS,WmsConstants.NUMBER_35);
        receiveTaskDao.updateReceiveTask(param);
        //receiveTaskDao.updateReceiveDetailTask(param);
    }

    /***
     * @Discription: 移库质检区列表查询
     * @param param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zhanglei on 2021/7/2 14:26
     */
    @Override
    public List<Map<String, Object>> listReceiveCarrierInfo(Map<String, Object> param) {
        return receiveTaskDao.listReceiveCarrierInfo(param);
    }
}

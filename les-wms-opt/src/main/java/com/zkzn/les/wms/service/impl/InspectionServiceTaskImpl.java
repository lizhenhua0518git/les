package com.zkzn.les.wms.service.impl;

import com.zkzn.les.common.exception.EmptyExamine;
import com.zkzn.les.common.util.json.JsonUtil;
import com.zkzn.les.common.util.rabbitMq.RabbitMqUtil;
import com.zkzn.les.common.util.redis.RedisNoUtil;
import com.zkzn.les.wms.constant.WmsConstants;
import com.zkzn.les.wms.dao.InspectionTaskDao;
import com.zkzn.les.wms.dao.PlatTransferTaskDao;
import com.zkzn.les.wms.receiveRecord.dao.ReceiveTaskDao;
import com.zkzn.les.wms.upperFrame.dao.UpperFrameTaskDao;
import com.zkzn.les.wms.feign.StockFeignService;
import com.zkzn.les.wms.feign.StrategyFeignService;
import com.zkzn.les.wms.service.InspectionTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName InspectionServiceImpl
 * @Description TODO
 * @Author zhanglei
 * Date 2021/6/22 9:44
 * @Version 1.0
 **/
@Service
public class InspectionServiceTaskImpl implements InspectionTaskService {

    @Autowired
    private InspectionTaskDao inspectionTaskDao;

    @Autowired
    private PlatTransferTaskDao platTransferTaskDao;

    @Autowired
    private ReceiveTaskDao receiveTaskDao;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StockFeignService stockFeignService;
    @Autowired
    private UpperFrameTaskDao upperFrameTaskDao;
    @Autowired
    private StrategyFeignService strategyFeignService;

    /***
     * @Discription: 质检主表信息查询
     * @param param
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @Author: zhanglei on 2021/6/22 10:11
     */
    @Override
    public List<Map<String, Object>> listInspectionInfo(Map<String, Object> param) throws Exception {
        return inspectionTaskDao.listInspectionInfo(param);
    }

    /***
     * @Discription: 质检子表信息查询
     * @param param
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @Author: zhanglei on 2021/6/22 10:11
     */
    @Override
    public List<Map<String, Object>> listInspectionDetailInfo(Map<String, Object> param) throws Exception {
        return inspectionTaskDao.listInspectionDetailInfo(param);
    }

    /***
     * @Discription: 质检作业提交
     *               1.参数校验 1）质检主表Id  2）质检子表id 3)质检合格数量 4）质检不合格数量 5）质检总数量
     *               2.计算质检结果 15-质检不合格 20-质检合格 25-质检部分合格
     *               3.质检、不合格物料信息保存
     *               4.更新质检子表状态与结果
     *           数据格式：{"inspectionId":"质检主表id","inspectionDetailId":"质检子表id","inspectionNum":"质检总数",
     *           "qualifiedNum":"质检合格数量","unQualifiedNum":"质检不合格数量","inspectionResult":"质检结果","arrivalCode":"到货通知单编码",
     *            "serialList":[{"serialNum":""},{"serialNum":""}]
     *           }
     *
     * @param params
     * @return void
     * @Author: zhanglei on 2021/6/22 10:38
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateInspectionInfo(Map<String, Object> params) throws Exception {
        //1.参数校验 质检总数/质检不合格数/质检合格数  数字类型
        EmptyExamine.examine(params, WmsConstants.INSPECTION_ID, WmsConstants.INSPECTION_DETAIL_ID,
                WmsConstants.INSPECTION_NUM, WmsConstants.QUALIFIED_NUM, WmsConstants.UNQUALIFIED_NUM, WmsConstants.INSPECTION_RESULT,WmsConstants.ARRIVAL_CODE);
        //质检总数 = 质检合格数量 + 质检不合格数量
        if (!(params.get(WmsConstants.INSPECTION_NUM) instanceof Double && (params.get(WmsConstants.QUALIFIED_NUM) instanceof Double)
                && params.get(WmsConstants.UNQUALIFIED_NUM) instanceof Double)) {
            throw new Exception(String.format("质检总数/质检不合格数/质检合格数，数字类型"));
        }
        double inspectionNum = (double) params.get(WmsConstants.INSPECTION_NUM);
        double unQualifiedNum = (double) params.get(WmsConstants.UNQUALIFIED_NUM);
        double qualifiedNum = (double) params.get(WmsConstants.QUALIFIED_NUM);
        if (inspectionNum != unQualifiedNum + qualifiedNum) {
            throw new Exception("质检数量错误");
        }
        //质检结果
        int inspectionResult = (int) params.get(WmsConstants.INSPECTION_RESULT);
        if (unQualifiedNum > 0 && WmsConstants.NUMBER_20 == inspectionResult) {
            //质检部分合格
            inspectionResult = WmsConstants.NUMBER_25;
            params.put(WmsConstants.PLAT_TRANSFER_STATUS, WmsConstants.NUMBER_1);
            params.put(WmsConstants.MATERIAL_NUM, unQualifiedNum);
        } else if (WmsConstants.NUMBER_15 == inspectionResult) {
            //质检不合格
            params.put(WmsConstants.MATERIAL_NUM, unQualifiedNum);
        }

        //3.更新质检子表状态与结果
        params.put(WmsConstants.STATUS, WmsConstants.NUMBER_25);
        params.put(WmsConstants.INSPECTION_RESULT, inspectionResult);
        params.put(WmsConstants.INSPECTION_TIME,new Date());
        inspectionTaskDao.updateInspectionDetailInfo(params);
        //质检物料信息查询
        List<Map<String, Object>> inspectionDetailInfo = inspectionTaskDao.listInspectionDetailInfo(params);
        //4.合格、不合格直接推送物料上架
        //调用上架策略获取仓位信息
        if (WmsConstants.NUMBER_25 != inspectionResult) {
//            String uploadStrategyResult = strategyFeignService.getStoragePositionResult(inspectionDetailInfo.get(WmsConstants.NUMBER_0));
//            Map<String, Object> uploadResult = JsonUtil.getJsonToBean(uploadStrategyResult, Map.class);
//            if (!WmsConstants.NUMBER_1_STR_.equals(uploadResult.get(WmsConstants.CODE).toString())) {
//                List<Map<String, Object>> upperFrameTaskInfo = this.saveUpperFrameTask(inspectionDetailInfo, inspectionResult, null);
//                upperFrameTaskDao.addUpperFrameRecordList(upperFrameTaskInfo);
//            }
            List<Map<String, Object>> upperFrameTaskInfo = this.saveUpperFrameTask(inspectionDetailInfo, inspectionResult, null);
            upperFrameTaskDao.addUpperFrameRecordList(upperFrameTaskInfo);
        }


        //6.拆盘任务初始化
        if (WmsConstants.NUMBER_25 == inspectionResult) {
            //2.质检不合格的物料存入质检异常表(物料序列号维护)
            inspectionTaskDao.saveInspectionAbnormalInfo(params);
            saveTransferTask(inspectionDetailInfo, params);
            Map<String, Object> unInspectionInfo = storageBinInfo(inspectionDetailInfo, unQualifiedNum,WmsConstants.NUMBER_1);
            stockFeignService.updateProductInventory(unInspectionInfo);
            Map<String, Object> inspectionInfo = storageBinInfo(inspectionDetailInfo, unQualifiedNum,WmsConstants.NUMBER_0);
            stockFeignService.updateProductInventory(inspectionInfo);
        } else if (WmsConstants.NUMBER_15 == inspectionResult) {
            //5.推送库存 质检不合格
            Map<String, Object> inspectionInfo = storageBinInfo(inspectionDetailInfo, unQualifiedNum,WmsConstants.NUMBER_1);
            stockFeignService.updateProductInventory(inspectionInfo);
        }else {
            Map<String, Object> inspectionInfo = storageBinInfo(inspectionDetailInfo, unQualifiedNum,WmsConstants.NUMBER_0);
            stockFeignService.updateProductInventory(inspectionInfo);
        }
        //7.序列号异常信息保存
        if (params.get(WmsConstants.SERIAL_LIST) instanceof List) {
            throw new Exception("异常序列号数据格式异常");
        }
        List<Map<String, Object>> serialList = (List) params.get(WmsConstants.SERIAL_LIST);
        if (null != serialList && !serialList.isEmpty()) {
            saveMaterialSerial(serialList, inspectionDetailInfo.get(WmsConstants.NUMBER_0));
        }
    }

    //库存信息
    private Map<String, Object> storageBinInfo(List<Map<String, Object>> param, double unQualifiedNum,int stockStatus) {
        Map<String, Object> inspectionDetailInfo = param.get(WmsConstants.NUMBER_0);
        //质检区编码/质检区id
        inspectionDetailInfo.put(WmsConstants.POSITION_CODE, inspectionDetailInfo.get(WmsConstants.INSPECTION_POSITION_CODE));
        inspectionDetailInfo.put(WmsConstants.STORAGE_ID, inspectionDetailInfo.get(WmsConstants.INSPECTION_POSITION_ID));
        //添加合格数量 冻结数量
        inspectionDetailInfo.put(WmsConstants.ENABLE_COUNT, (double) inspectionDetailInfo.get(WmsConstants.MATERIAL_NUM) - unQualifiedNum);
        inspectionDetailInfo.put(WmsConstants.STOCK_COUNT, (double) inspectionDetailInfo.get(WmsConstants.MATERIAL_NUM));
        inspectionDetailInfo.put(WmsConstants.FROZEN_COUNT, unQualifiedNum);
        //0合格 1 不合格
        if (stockStatus == 1) {
            inspectionDetailInfo.put(WmsConstants.STOCK_STATUS,stockStatus);
        }
        return inspectionDetailInfo;
    }

    //保存物料序列号
    private void saveMaterialSerial(List<Map<String, Object>> serialList, Map<String, Object> paraMap) throws Exception {
        for (Map<String, Object> serial : serialList) {
            serial.put(WmsConstants.TASK_TYPE, WmsConstants.NUMBER_2);
            serial.put(WmsConstants.TASK_ID, paraMap.get(WmsConstants.INSPECTION_DETAIL_ID));
            serial.put(WmsConstants.RECEIVED_TASK_CODE, paraMap.get(WmsConstants.INSPECTION_TASK_CODE));
        }
        receiveTaskDao.saveMaterialSerial(serialList);
    }

    //拆盘任务创建 以到货通知单位
    private void saveTransferTask(List<Map<String, Object>> inspectionDetailInfo, Map<String, Object> param) throws Exception {
        //查询当前到货通知单是否有拆盘任务
        List<Map<String, Object>> platTransferInfos = platTransferTaskDao.listPlatTransferInfos(param);
        //主表id
        int id;
        if (platTransferInfos.isEmpty()) {
            //创建拆盘主表任务
            List<Map<String, Object>> inspectionInfo = inspectionTaskDao.listInspectionInfo(param);
            Map<String, Object> inspectionInfoMap = inspectionInfo.get(WmsConstants.NUMBER_0);
            inspectionInfoMap.put(WmsConstants.TRANSFER_TASK_CODE, RedisNoUtil.getSeqNo(RedisNoUtil.CP_ORDER_KEY_M, redisTemplate));
            platTransferTaskDao.savePlatTransferInfo(inspectionInfoMap);
            id = (int) inspectionInfoMap.get(WmsConstants.ID);
        } else {
            id = (int) platTransferInfos.get(WmsConstants.NUMBER_0).get(WmsConstants.ID);
        }
        Map<String, Object> inspectionDetail = inspectionDetailInfo.get(WmsConstants.NUMBER_0);
        inspectionDetail.put(WmsConstants.TRANSFER_TASK_CODE, RedisNoUtil.getSeqNo(RedisNoUtil.CP_ORDER_KEY, redisTemplate));
        inspectionDetail.put(WmsConstants.TRANSFER_TASK_ID, id);
        inspectionDetail.put(WmsConstants.QUALIFIED_NUM,param.get(WmsConstants.QUALIFIED_NUM));
        inspectionDetail.put(WmsConstants.UNQUALIFIED_NUM,param.get(WmsConstants.UNQUALIFIED_NUM));
        //拆盘子表保存
        platTransferTaskDao.savePlatTransferDetailInfos(inspectionDetail);
    }

    private List<Map<String, Object>> saveUpperFrameTask(List<Map<String, Object>> inspectionDetailInfo, int upperType, List<Map<String, Object>> upperInfo) throws Exception {
        if (inspectionDetailInfo.isEmpty()) {
            throw new Exception("质检信息为空");
        }
        if (upperType > 20) {
            return null;
        }

//        String positionCode = Joiner.on(WmsConstants.FENHAO).join(upperInfo.stream().map(map -> {
//            return map.get(WmsConstants.POSITION_CODE);
//        }).collect(Collectors.toList()));

        List<Map<String, Object>> result = inspectionDetailInfo.stream().map(map -> {
            map.put(WmsConstants.UPPER_TYPE, upperType == WmsConstants.NUMBER_15 ? WmsConstants.NUMBER_1 : upperType == WmsConstants.NUMBER_20 ? WmsConstants.NUMBER_0 : WmsConstants.NUMBER_3);
            map.put(WmsConstants.UPPER_TASK_CODE, RedisNoUtil.getSeqNo(RedisNoUtil.UPPER_FRAME_KEY, redisTemplate));
            map.put(WmsConstants.RECEIVE_RECORD_ID, map.get(WmsConstants.INSPECTION_ID));
            map.put(WmsConstants.RECEIVE_DETAIL_ID, map.get(WmsConstants.INSPECTION_DETAIL_ID));
//            map.put(WmsConstants.RECOMMENDED_POSITION_CODE, positionCode);
            map.put(WmsConstants.SUPPLIER_CODE, map.get(WmsConstants.SHIPPER_CODE));
            map.put(WmsConstants.SUPPLIER_NAME, map.get(WmsConstants.SHIPPER_NAME));
            return map;
        }).collect(Collectors.toList());
        return result;
    }

    /***
     * @Discription: 质检开始信息记录
     *             1.记录主表任务开始时间
     *             2.修改主表任务状态
     * @param params
     * @return void
     * @Author: zhanglei on 2021/6/23 10:20
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateInspectionStartInfo(Map<String, Object> params) throws Exception {
        EmptyExamine.examine(params, WmsConstants.INSPECTION_ID);
        params.put(WmsConstants.STATUS, WmsConstants.NUMBER_20);
        params.put(WmsConstants.INSPECTION_START_TIME, new Date());
        inspectionTaskDao.updateInspectionInfo(params);
        inspectionTaskDao.updateInspectionDetailInfo(params);
    }

    /***
     * @Discription: 质检开始信息记录
     *           1.修改主表任务状态
     *           2.SAP过账
     * @param params
     * @return void
     * @Author: zhanglei on 2021/6/23 10:20
     */
    @Override
    public void updateInspectionEndInfo(Map<String, Object> params) throws Exception {
        //主表状态
        //查询判断当前任务单质检是否全部完成
        EmptyExamine.examine(params, WmsConstants.INSPECTION_ID);
        List<Map<String, Object>> inspectionDetailInfo = inspectionTaskDao.listInspectionDetailInfo(params);
        List<Map<String, Object>> statusList = inspectionDetailInfo.stream().filter(inspection -> WmsConstants.NUMBER_25 != (int) inspection.get(WmsConstants.STATUS)).collect(Collectors.toList());
        if (statusList.size() > 0) {
            throw new Exception("当前任务存在没有完成的任务");
        }
        Map<String, Object> inspectionTask = inspectionDetailInfo.get(WmsConstants.NUMBER_0);
        Date startTime = (Date) inspectionTask.get(WmsConstants.INSPECTION_START_TIME);
        params.put(WmsConstants.STATUS, WmsConstants.NUMBER_25);
        Date endTime = new Date();
        params.put(WmsConstants.INSPECTION_END_TIME,endTime);
        params.put(WmsConstants.INSPECTION_TOTAL_TIME, endTime.getTime() - startTime.getTime());
        inspectionTaskDao.updateInspectionInfo(params);
        // 过账sap
//        RabbitMqMessage rabbitMqMessage = new RabbitMqMessage(WmsConstants.NUMBER_2, params.get(WmsConstants.INSPECTION_ID).toString());
//        RabbitMqUtil.INSTANCE.sendMessage(JsonUtil.getBeanToJson(rabbitMqMessage), WmsConstants.SAP_QUEUE);
    }
}

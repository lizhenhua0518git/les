package com.zkzn.les.wms.constant;

/**
 * @ClassName WmsConstants
 * @Description wms服务常用类
 * @Author zhanglei
 * Date 2021/6/9 15:03
 * @Version 1.0
 **/
public class WmsConstants {
    //--------------------排号相关------------------------
    //排队状态
    public final static String QUEUE_STATE = "queueState";
    //到货通知单号
    public final static String BILL_CODE = "billCode";
    //组织编码
    public final static String ORG_CODE = "orgCode";
    //仓库编码
    public final static String WAREHOUSE_CODE = "warehouseCode";
    //仓库名称
    public final static String WAREHOUSE_NAME = "warehouseName";
    //到货通知单id
    public final static String ARRIVAL_NOTICE_ID = "arrivalNoticeId";
    //status
    public final static String STATUS = "status";
    //id
    public final static String ID = "id";
    public final static String SOURCE_ID = "sourceId";
    //queueNum
    public final static String QUEUE_NUM = "queueNum";
    //queueCode
    public final static String QUEUE_CODE = "queueCode";
    //queueTime
    public final static String QUEUE_TIME = "queueTime";
    //月台编码
    public final static String UPLOAD_PLAT = "uploadPlat";
    public final static String UPLOAD_PLAT_CODE = "uploadPlatCode";
    //SUPPLIER_NAME 供应商名称
    public final static String SUPPLIER_NAME = "supplierName";
    //SUPPLIER_Code 物料供应商编码
    public final static String SUPPLIER_CODE = "supplierCode";
    //送货供应商名称
    public final static String SHIPPER_NAME = "shipperName";
    //送货供应商编码
    public final static String SHIPPER_CODE = "shipperCode";
    //月台id
    public final static String UPLOAD_PLAT_ID = "uploadPlatId";
    //点收主表任务编号
    public final static String RECEIVED_TASK_CODE = "receivedTaskCode";
    //RECEIVE_ID
    public final static String RECEIVE_ID = "receiveId";
    public final static String RECEIVE_DETAIL_ID = "receiveDetailId";
    //RECEIVE_NUM 点收数量
    public final static String RECEIVE_NUM = "receiveNum";
    //RECEIVE_NUM 实际到货数量
    public final static String ACTUAL_NUM = "actualNum";
    //月台使用状态
    public final static String USE_STATUS = "useStatus";
    //BILLID
    public final static String BILL_ID = "billId";

    //排号状态
    public final static String QUEUE_STATUS = "queueStatus";
    //卸货状态
    public final static String UPLOAD_STATUS = "uploadStatus";
    //月台编码
    public final static String UPLOAD_CODE = "uploadCode";
    //实际到货时间
    public final static String ACTUAL_ARRIVAL_TIME = "actualArrivalTime";

    public final static String TIME_VALUE = "timeValue";

    //小时
    public final static String HOUR = "小时";
    //分钟
    public final static String MIN = "分钟";

    public final static String APP = "app";

    public final static String MATERIAL_LIST = "materialList";

    public final static String IN_FACTORY_TIME = "inFactoryTime";
    //admissionId
    public final static String ADMISSION_ID = "admissionId";
    //admissionStatus
    public final static String ADMISSION_STATUS = "admissionStatus";

    //createTime
    public final static String CREATE_TIME = "createTime";

    //库存地点
    public final static String STORAGE_LOCATION = "storageLocation";
    //factory
    public final static String FACTORY = "factory";
    //工厂编码
    public final static String FACTORY_STR = "0000";
    //业务类型
    public final static String BILL_TYPE = "billType";

    //任务编码
    public final static String RECEIVE_TASK_CODE = "receiveTaskCode";

    //创建人id
    public final static String CREATER_ID = "createrId";
    //创建人名称
    public final static String CREATER_NAME = "createrName";
    //行项目号
    public final static String ITEM_NO = "itemNo";
    //点收开始时间
    public final static String RECEIVE_START_TIME = "receiveStartTime";
    //载具信息列表
    public final static String CARRIER_LIST = "carrierList";
    //区域编码
    public final static String AREA_CODE = "areaCode";
    //区域id
    public final static String AREA_ID = "areaId";
    //区域名称
    public static final String AREA_NAME = "areaName";
    //载具编码
    public static final String CARRIER_CODE = "carrierCode";
    //载具名称
    public static final String CARRIER_NAME = "carrierName";
    //载具id
    public static final String CARRIER_ID = "carrierId";
    //载具类型
    public static final String CARRIER_TYPE = "carrierType";
    //是否为关键物料
    public static final String IS_KEY = "isKey";
    //关键物料序列号集合
    public static final String SERIAL_LIST = "serialList";
    //物料编码
    public static final String MATERIAL_CODE = "materialCode";
    //批次号
    public static final String BATCH_NO = "batchNo";
    //taskId 关键物料表中的数据来源id
    public static final String TASK_ID = "taskId";
    //关键物料序列号
    public static final String SERIAL_NUM = "serialNum";
    //关键物料表中的业务类型
    public static final String TASK_TYPE = "taskType";
    //异常原因
    public static final String ABNORMAL_REASON = "abnormalReason";
    //库存地点集合
    public static final String STORAGE_LOCATIONS = "storageLocations";
    //卸货完成时间
    public static final String UPLOAD_FINISH_TIME = "uploadFinishTime";

    //点收结束时间
    public static final String RECEIVE_END_TIME = "receiveEndTime";
    //点收总时长
    public static final String RECEIVE_TOTAL_TIME = "receiveTotalTime";
    public static final String INSPECT_LIST = "inspectionList";

    public static final String INSPECTION_CODE = "inspectionCode";
    //质检主表Id
    public static final String INSPECTION_ID = "inspectionId";
    //质检区域id
    public static final String INSPECTION_POSITION_ID = "inspectionPositionId";
    //质检区域编码
    public static final String INSPECTION_POSITION_CODE = "inspectionPositionCode";
    //质检任务编号
    public static final String INSPECTION_TASK_CODE = "inspectionTaskCode";
    //质检总数
    public static final String INSPECTION_NUM = "inspectionNum";

    //质检详情id
    public static final String INSPECTION_DETAIL_ID = "inspectionDetailId";
    //质检合格数量
    public static final String QUALIFIED_NUM = "qualifiedNum";
    //质检不合格数量
    public static final String UNQUALIFIED_NUM = "unQualifiedNum";
    //质检结果
    public static final String INSPECTION_RESULT = "inspectionResult";
    //--------------------------数字-------------------------------
    public final static String NUMBER_1_STR = "1";
    public final static String NUMBER_1_STR_ = "-1";
    public final static String NUMBER_2_STR = "2";
    public final static String NUMBER_3_STR = "3";
    public final static String NUMBER_15_STR = "15";
    public final static String NUMBER_20_STR = "20";
    public final static Integer NUMBER_10 = 10;
    public final static Integer NUMBER_15 = 15;
    public final static Integer NUMBER_20 = 20;
    public final static Integer NUMBER_25 = 25;
    public final static Integer NUMBER_30 = 30;
    public static final Integer NUMBER_35 = 35;
    public final static Integer NUMBER_0 = 0;
    public final static Integer NUMBER_1 = 1;
    public final static Integer NUMBER_2 = 2;
    public final static Integer NUMBER_3 = 3;
    public static final Integer NUMBER_4 = 4;
    //--------------------------时间格式-----------------------

    public final static String YYMMDDHHMMSS = "yyyy-MM-dd hh:mm:ss";

    //-----------------------------标点-----------------------------
    public final static String FENHAO = ";";


    public static final String MATERIAL_NUM = "materialNum";
    public static final String INSPECTION_TASK_ID = "inspectionTaskId";

    public static final String ARRIVAL_CODE = "arrivalCode";
    public static final String TRANSFER_TASK_CODE = "transferTaskCode";
    public static final String TRANSFER_TASK_ID = "transferTaskId";
    public static final String INSPECTION_START_TIME = "inspectionStartTime";
    public static final String INSPECTION_END_TIME = "inspectionEndTime";
    public static final String INSPECTION_TOTAL_TIME = "inspectionTotalTime";
    public static final String UPLOAD_PLAT_NAME = "uploadPlatName";
    public static final String UPLOAD_NAME = "uploadName";
    public static final String LT_STATUS = "ltStatus";
    public static final String PLAT_TRANSFER_STATUS = "platTransferStatus";
    public static final String TRANSFER_NUM = "transferNum";
    public static final String TRANSFER_DETAIL_ID = "transferDetailId";
    public static final String NEW_CARRIER_ID = "newCarrierId";
    public static final String NEW_CARRIER_CODE = "newCarrierCode";
    public static final String TRANSFER_TIME = "transferTime";
    public static final String OLD_CARRIER_ID = "oldCarrierId";
    public static final String ORDER_CODE = "orderCode";
    public static final String BILL_NAME = "billName";
    public static final String ARRIVAL_ITEM_NO = "arrivalItemNo";

    public static final String ABNORMAL_NUM = "abnormalNum";
    public static final String CODE = "code";
    public static final String DATA = "data";

    public static final String STRATEGY_TYPE = "strategyType";

    public static final String XHD_STRATEGY = "xhd_strategy";
    public static final String STOCK_STATUS = "stockStatus";
    public static final String SAP_STORAGE_LOCATION = "sapStorageLocation";
    public static final String SUBJECT_TYPE = "subjectType";

    public static final String STOCK_TYPE = "stockType";
    public static final String SELL_ORDER = "sellOrder";
    public static final String SELL_ORDER_ITEM = "sellOrderItem";
    public static final String SALES_ORDER_CODE = "salesOrderCode";
    public static final String SALES_ORDER_ITEM = "salesOrderItem";
    public static final String POSITION_CODE = "positionCode";
    public static final String STORAGE_ID = "storageId";


    public static final String ARRIVAL_DETAIL_ID = "arrivalDetailId";
    public static final String SAP_ITEM_NO = "sapItemNo";
    public static final String PURCHASE_CODE = "purchaseCode";
    public static final String UPPER_TYPE = "upperType";
    public static final String UPPER_TASK_CODE = "upperTaskCode";
    public static final String UPPER_TASK_TYPE = "upperTaskType";

    public static final String STOCK_COUNT = "stockCount";
    public static final String FROZEN_COUNT = "frozenCount";
    public static final String ENABLE_COUNT = "enableCount";


    public static final String RECEIVE_RECORD_ID = "receiveRecordId";
    public static final String SAP_QUEUE = "sap_queue";
    public static final String INSPECTION_TIME = "inspectionTime";
    public static final String CARRY_NUMBER = "carryNumber";
    public static final String TRANSFER_ID = "transferId";
    public static final String MATERIAL_UNIT = "materialUnit";
    public static final String POSITION_ID = "positionId";
    public static final String TOTAL_TIME = "totalTime";
    public static String RECOMMENDED_POSITION_CODE = "recommendedPositionCode";
}
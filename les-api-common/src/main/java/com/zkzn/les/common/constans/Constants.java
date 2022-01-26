package com.zkzn.les.common.constans;

/**
 * @ClassName 常量工具类
 * @Description TODO
 * @Author zhanglei
 * Date 2020/9/1 9:25
 * @Version 1.0
 **/
public interface Constants {

	//供应商载具回收常量类
	String APPLIANCES_CODE = "appliancesCode";

	String FLOW_NUM = "flowNum";

	String NEW_FLAG = "newFlg";

	String STORAGE_NUM = "storageNum";

	String EMPTY = "";

	String WAREHOUSECODES = "wareHouseCodes";

	String CHARACTER = ";";

	String WAREHOUSECODE = "wareHouseCode";

	String STATUS_STR = "statusStr";

	String BILLTYPE = "billType";

	String DATA = "data";

	String DOCUMENT_CODE = "documentCode";

	String METARIAL_CODE = "materialCode";

	//质检状态
	String INSPECT_STATUS = "32";
	//待质检状态
	Integer INSPECT_STATUS_30 = 30;
	//冲销状态查询字段
	String DELETE_STATUS_STR = "deleteStatusStr";
	//冲销状态
	String DELETE_STATUS = "0;1";
	//未冲销
	Integer DELETE_STATUS_0 = 0;

	String NUMBER_OF_ONE = "1";

	String NUMBER_OF_TWO = "2";

	String NUMBER_OF_THREE = "3";

	String NUMBER_OF_FOUR = "4";


	String SEVEN_DAYS = "sevenDays";
	//车间
	String WORK_SHOP = "workShopCodes";
	//产线
	String LINE_WORK = "lineCodes";
	//工位
	String WORK_LOCATION = "stationCodes";

	//下架状态
	String STATUS = "status";

	Integer NUMBER_OF_5 = 5;

	//质检货架code
	String ZJQ_POSITION_CODE = "ZJQ";
	//质检货架id
	String ZJQ_STORAGE_ID = "0121839283444412831ZJQ";

	/**
	 * 取下标的值
	 * 2生产退料收货(合格) 、
	 * 3 采购收货、
	 * 4 生产退料收货(退补料不合格)、
	 * 5调拨收货、
	 * 6内部订单退料收货、
	 * 7委外入库
	 */
	String[] BILL_TYPE = {"0", "1", "2", "3", "4", "5", "6", "7"};

	/**
	 * 物料不合格
	 */
	Integer UNENABLE_STATUS_ZJ = 1; //质检不合格
	Integer UNENABLE_STATUS_SC = 2; //生产不合格


	/**
	 * 上架来源 1:采购上架 2:生产退料上架 3:调拨上架 4:其他上架
	 */
	Integer UPPER_ORIGIN_CG = 1; //采购上架
	Integer UPPER_ORIGIN_SC = 2; //生产退料上架
	Integer UPPER_ORIGIN_DB = 3; //调拨上架
	Integer UPPER_ORIGIN_OTHER = 4; //其他上架


	/**
	 * 下架常量类
	 */
	String ID = "id";

	String MODIFIER_NAME = "modifierName";

	String MODIFIED_TIME = "modifiedTime";

	String MATERIALS = "materials";

	int NUMBER_9 = 9;

	int NUMBER_11 = 11;

	int NUMBER_12 = 15;

	String CARRIER_CODE = "carrierCode";

	String ORDER_TYPE = "orderType";

	String MATERIAL_SERI = "materialSeri";

	String PICK_NUM = "pickNum";

	String MATERIAL_STORAGE_ID = "materialStorageId";

	String FACTORY_CODE = "6F00";

	String BATCH_NO = "batchNo";

	/**
	 * 上下架标识
	 */
	String DOWN = "down";
	String UP = "up";

	/**
	 * 序列号表任务类型(t_material_SERIAL.TASK_TYPE) 1-点收；2-拆盘；3-上架 4-下架
	 */
	Integer ONE_POINT = 1;
	Integer TWO_BREAK = 2;
	Integer THREE_UP = 3;
	Integer FOUR_DOWN = 4;


	/**
	 * 调拨详情任务完成状态(默认状态:0调拨任务已派发  5下架完成  10已交接完成  15上架完成)
	 */
	Integer SLIP_DISTRIBUTED_0 = 0;
	Integer SLIP_BEEN_OFF_5 = 5;
	Integer SLIP_HANDED_OVER_10 = 10;
	Integer SLIP_BEEN_ON_15 = 15;


	/**
	 * Redis的CacheStorage类型
	 */
	String APP_REDIS = "app";
	String PC_REDIS = "pc";

	/**
	 * 成品车出场/入场状态
	 */
	Integer FINISHED_CAR_ENTRY = 0;
	Integer FINISHED_CAR_OUT = 5;

	/**
	 * 成品车更新/插入标识
	 */
	String UPDATE_FLAG = "update";
	String INSERT_FLAG = "insert";

	/**
	 * 成品车位置LogHistory
	 */
	String FINISHED_CAR_LOG = "les-wms::finishedCarLog::";



}

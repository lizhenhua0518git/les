package com.zkzn.les.wms.constant;

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

    String WAREHOUSECODES ="wareHouseCodes";

    String CHARACTER=";";

    String WAREHOUSECODE = "wareHouseCode";

    String STATUS_STR = "statusStr";

    String BILLTYPE = "billType";

    String DATA = "data";

    String DOCUMENT_CODE = "documentCode";

    String METARIAL_CODE= "materialCode";

    //质检状态
    String INSPECT_STATUS = "32";
    //待质检状态
    Integer INSPECT_STATUS_30=30;
    //冲销状态查询字段
    String DELETE_STATUS_STR = "deleteStatusStr";
    //冲销状态
    String DELETE_STATUS="0";
    //未冲销
    Integer DELETE_STATUS_0=0;

    String NUMBER_OF_ONE = "1";

    String NUMBER_OF_TWO = "2";

    String NUMBER_OF_THREE ="3";

    String NUMBER_OF_FOUR = "4";

    String SEVEN_DAYS = "sevenDays";
    //车间
    String WORK_SHOP = "workShopCodes";
    //产线
    String LINE_WORK ="lineCodes";
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
    String[] BILL_TYPE ={"0","1","2","3","4","5","6","7"};

}

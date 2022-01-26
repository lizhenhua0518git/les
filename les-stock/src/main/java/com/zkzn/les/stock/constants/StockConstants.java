package com.zkzn.les.stock.constants;

import com.zkzn.les.stock.pojo.SCheckInventoryTask;

/**
 * @ClassName StockConstants
 * @Description 库存常量类
 * @Author zhanglei
 * Date 2021/6/30 10:23
 * @Version 1.0
 **/
public class StockConstants {
    public static final String DATA = "data";
    public static final String STORAGE_LOCATION = "storageLocation";
    public static final String MATERIAL_CODE = "materialCode";
    public static  String STOCK_STATUS = "stockStatus";
    public static final String CHANG_LISTS = "changLists";
    public static final String CHANGE_STATUS  = "changStatus";
    public static final String SAP_QUEUE = "sap_queue";
    public static final Integer NUM_4 = 4;
    /**
     * 仓位库存 0、占用  1、空闲
     */
    public static final Integer OCCUPY_STATUS_0 = 0;
    public static final Integer OCCUPY_STATUS_1 = 1;

    /**
     * 审核状态
     * 0:未审核 5:接受结果损益 10:客户平仓 -1:取消损益
     * {@link SCheckInventoryTask#verifyStatus}
     */
    public static final int CHECK_VERIFY_STATUS_NO = 0;
    public static final int CHECK_VERIFY_STATUS_ACCEPT = 5;
    public static final int CHECK_VERIFY_STATUS_CLIENT_ADD = 10;
    public static final int CHECK_VERIFY_STATUS_CANCEL = -1;

    /**
     * 盘点状态:
     * 0:待盘点,1:正在盘点,-1:盘点结束
     * {@link SCheckInventoryTask#checkStatus}
     */
    public static final Integer TO_BE_COUNTED = 0;
    public static final Integer CHECKING = 1;
    public static final Integer CHECKED = -1;

}
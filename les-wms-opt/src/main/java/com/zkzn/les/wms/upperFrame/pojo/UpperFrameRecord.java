package com.zkzn.les.wms.upperFrame.pojo;

import com.zkzn.les.common.util.page.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 上架记录
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UpperFrameRecord extends PageCondition {

    private Integer upperFrameId;
    private Integer receiveTaskId;
    private Integer receiveDetailId;
    private Double upperNumber;
    private Date upperUpperTime;
    private Date createTime;
    private String status;
    private String positionCode;
    private String materialUnit;
    private String upperType;//-- 0-合格品，1-不合格品
    private BigDecimal materialNum;
    private String carrierCode;
    private String upperTaskCode;
    private String clientName;
    private Integer clientManageId;
    private String batchNo;
    private Integer upperOrigin;//上架来源 1:采购上架 2:生产退料上架 3:调拨上架 4:其他上架
    private String warehouseName;
    private String warehouseCode;


    private String recommendedPositionId;

    private String recommendedPositionCode;

    private String positionId;

    private String materialCode;

    private String factory;

    private String storageLocation;

    private Date upperTime;

    private String upperTimeStr;//上架日期查询条件
    private String startUpperTime;//开始上架日期
    private String endUpperTime;//结束上架日期

    private String upperName;

    private String upperId;





    private String carrierId;



    private String supplierCode;

    private String supplierName;



    private String orderCode;

    private String custormer;

    private String customName;

    private String stationCode;




    private String materialDesc;

    private String inspectPositionCode;




}

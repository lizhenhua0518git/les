package com.zkzn.les.wms.pojo;


import com.zkzn.les.common.util.page.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author luozhihong
 * @date 2020年9月29日
 * @Description 上架记录
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UpperFrameRecord extends PageCondition {

    private String upperRecordId;//主键id
    private String id;

    private String receiveRecordId;

    private String recevieDetailId;

    private String recommendedPositionId;

    private String recommendedPositionCode;

    private String positionCode;

    private String positionId;

    private String materialCode;

    private String factory;

    private String materialUnit;

    private double materialNum;

    private String upperType;//-- 0-合格品，1-不合格品

    private String storageLocation;

    private Date upperTime;

    private String upperTimeStr;//上架日期查询条件
    private String startUpperTime;//开始上架日期
    private String endUpperTime;//结束上架日期

    private String upperName;

    private String upperId;

    private Date createTime;

    private int status;

    private String carrierCode;

    private String carrierId;

    private String upperTaskCode;

    private String supplierCode;

    private String supplierName;

    private String batchNo;

    private String orderCode;

    private String custormer;

    private String customName;

    private String stationCode;


    private String warehouseName;

    private String materialDesc;

    private String inspectPositionCode;

    private Integer upperOrigin;//上架来源 1:采购上架 2:生产退料上架 3:调拨上架 4:其他上架

    private String storageId;//库位id
    private String sourceId;//来源单id(送货单:t_arrival_notice_detail)
    private String billType;//类型
}
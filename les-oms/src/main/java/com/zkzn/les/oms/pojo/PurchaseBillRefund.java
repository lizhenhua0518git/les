package com.zkzn.les.oms.pojo;

import com.zkzn.les.common.util.page.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author luozhihong
 * @date 2020年9月27日
 * @Description 退货采购订单出库
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PurchaseBillRefund extends PageCondition {
    private String id;

    private String orderCode;

    private String parentId;

    private String itemNo;

    private String itemType;

    private String subjectType;

    private String materialCode;

    private String factory;

    private String factoryDesc;

    private String storageLocation;

    private BigDecimal materialNum;

    private String materialUnit;

    private String isReturn;

    private String isFree;

    private String deliveryDate;

    private String billDate;

    private String salesOrderCode;

    private String salesOrderItem;

    private String elikz;

    private BigDecimal uebto;

    private BigDecimal untto;

    private Date createTime;

    private String requiredTimeStr;//创建时间查询条件
    private String startRequiredTime;//开始创建时间
    private String endRequiredTime;//结束创建时间


    private String orderType;
    private String supplierCode;
    private String supplierName;
    private String warehouseName;
    private String materialDesc;

    private String status;

    private String positionCode;
}

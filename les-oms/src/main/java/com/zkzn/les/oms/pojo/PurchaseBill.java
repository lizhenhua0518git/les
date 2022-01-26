package com.zkzn.les.oms.pojo;

import com.zkzn.les.common.util.page.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
/**
 *
 * @author luozhihong
 * @date 2020年9月27日
 * @Description 采购入库订单
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PurchaseBill extends PageCondition {

    private static final long serialVersionUID = 7155346495230453742L;

    private String id;

    private String orderCode;

    private String orderType;

    private String supplierCode;

    private String supplierName;

    private Date createTime;

    private String requiredTimeStr;//创建时间查询条件
    private String startRequiredTime;//开始创建时间
    private String endRequiredTime;//结束创建时间
}

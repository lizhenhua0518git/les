package com.zkzn.les.common.pojo;

import com.zkzn.les.common.util.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
/**.
 * 
 * 功能描述：过账记录
 * @author luozhihong
 * 时间：2020年9月9日
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AccountResult extends PageCondition{
	
	private static final long serialVersionUID = 1L;
	
    private String id;

    private String orderCode;

    private BigDecimal itemNo;

    private String voucherCode;

    private BigDecimal voucherItem;

    private Date createTime;

    private String orderId;

    private String orderDetailId;

    private Date voucherDate;

    private String voucherYear;

    private String accountParam;

    private Short status;

    private Short accountType;

    private String failDesc;
    
    private String materialCode;
    
    private Integer arrivalCount;

    private String taskId;
   
}
package com.zkzn.les.common.pojo;

import com.zkzn.les.common.util.PageCondition;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**.
 * 
 * @author wangzhou
 * @date 2020年8月22日
 * @Description 内部订单基础数据
 */
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class InternalBill extends PageCondition{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2730385932021007669L;

	@ApiModelProperty(name="主键id",value="id")
	private String id;
	
	@ApiModelProperty(name="订单号",value="orderCode")
	private String orderCode;
	
	@ApiModelProperty(name="订单类型",value="orderType")
	private String orderType;
	
	@ApiModelProperty(name="工厂",value="factory")
	private String factory;
	
	@ApiModelProperty(name="描述",value="describe")
	private String describe;
}

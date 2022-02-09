package com.zkzn.les.common.pojo;

import com.zkzn.les.common.util.PageCondition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SapProcessOrder extends PageCondition {
	@ApiModelProperty(name="id", value="主键")
    private String id;

	@ApiModelProperty(name="orderType", value="订单类型")
    private String orderType;
	
	@ApiModelProperty(name="orderTypeName", value="订单类型名称")
    private String orderTypeName;

	@ApiModelProperty(name="orderCode", value="订单号")
    private String orderCode;

	@ApiModelProperty(name="orderStatus", value="订单状态 0-下达，1-锁定，2-已生成集配单，9-删除")
    private int orderStatus;
	
	@ApiModelProperty(name="orderStatusName", value="订单状态名称  0-下达，1-锁定，2-已生成集配单，9-删除")
    private String orderStatusName;

	@ApiModelProperty(name="startTime", value="基本开始时间")
    private String startTime;

	@ApiModelProperty(name="endTime", value="基本结束时间")
    private String endTime;

	@ApiModelProperty(name="vehicleCode", value="产成品或半成品的物料代码  ")
    private String vehicleCode;

    @ApiModelProperty(name="id", value="销售订单")
    private String sellOrder;

    @ApiModelProperty(name="customCode", value="客户编码")
    private String customCode;

    @ApiModelProperty(name="customDesc", value="客户描述")
    private String customDesc;

    @ApiModelProperty(name="vehicleDesc", value="产成品或半成品的物料描述")
    private String vehicleDesc;

    @ApiModelProperty(name="orderNum", value="生产订单计划量")
    private BigDecimal orderNum;

    @ApiModelProperty(name="unit", value="基本计量单位")
    private String unit;

    @ApiModelProperty(name="factory", value="工厂")
    private String factory;

    @ApiModelProperty(name="batchNod", value="批次号")
    private String batchNo;

    @ApiModelProperty(name="storageLocation", value="产品库存地点")
    private String storageLocation;

    @ApiModelProperty(name="vinCode", value="VIN码=车系+车号")
    private String vinCode;
 

    @ApiModelProperty(name="sellOrderItem", value="销售订单行项目")
    private String sellOrderItem;

    @ApiModelProperty(name="priority", value="1~9,1的优先级最高")
    private String priority;

    @ApiModelProperty(name="carModel", value="车型")
    private String carModel;

    @ApiModelProperty(name="serialNum", value="序列号")
    private String serialNum;

    private static final long serialVersionUID = 1L;
}
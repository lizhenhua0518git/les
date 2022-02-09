package com.zkzn.les.oms.pojo;

import java.util.Date;
import java.util.List;

import com.zkzn.les.common.util.page.PageCondition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 *
 * @author wangzhou
 * @date 2020年8月28日
 * @Description mes锁定订单后 拆分订单实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProcessOrder extends PageCondition {

	/**
	 *
	 */
	private static final long serialVersionUID = 7155346495230458742L;

	@ApiModelProperty(name="id", value="主键id")
	private String id;
	@ApiModelProperty(name="orderCode", value="生产订单号")
	private String orderCode;
	@ApiModelProperty(name="businessType", value="业务类型")
	private String businessType;
	@ApiModelProperty(name="vinCode", value="vin号")
	private String vinCode;
	@ApiModelProperty(name="requiredTime", value="生产时间")
	private Date  requiredTime;
	private String requiredTimeStr;//创建时间查询条件
    private String startRequiredTime;//开始生产时间
    private String endRequiredTime;//结束生产时间
	@ApiModelProperty(name="workshopCode", value="车间")
	private String workshopCode;
	private String workShopName;
	@ApiModelProperty(name="lineCode", value="产线")
	private String lineCode;
	private String lineName;
	@ApiModelProperty(name="orderType", value="订单型号")
	private String orderType;
	@ApiModelProperty(name="vehicleCode", value="产成品或半成品的物料代码  ")
	private String vehicleCode;
	@ApiModelProperty(name="vehicleDesc", value="物料描述")
	private String vehicleDesc;
	@ApiModelProperty(name="sellOrder", value="销售订单")
	private String sellOrder;
	@ApiModelProperty(name="sellOrderItem", value="销售订单行")
	private String sellOrderItem;
	@ApiModelProperty(name="customCode", value="客户编码")
	private String customCode;
	@ApiModelProperty(name="customDesc", value="客户描述")
	private String  customDesc;
	@ApiModelProperty(name="orderNum", value="生产订单计划量")
	private Double orderNum;
	@ApiModelProperty(name="unit", value="基本计量单位")
	private String unit;
	@ApiModelProperty(name="priority", value="订单优先级")
	private String priority;
	@ApiModelProperty(name="batchNo", value="批次")
	private String batchNo;
	@ApiModelProperty(name="serialNum", value="序列号")
	private String serialNum;
	@ApiModelProperty(name="factory", value="工厂")
	private String factory;
	@ApiModelProperty(name="orderId", value="sap生产订单主表id")
	private String orderId;
	@ApiModelProperty(name="carModel", value="车型")
	private String carModel;

	private Date createTime;

	private String isCall;

	private String warehouseName;

	@ApiModelProperty(name="processOrderDetailList", value="子表数据")
	private List<ProcessOrderDetail> processOrderDetailList;

	@ApiModelProperty(name="storageLocation", value="库存地点")
	private String storageLocation;
	@ApiModelProperty(name="storageLocations", value="库存地点集合 列表查询用")
	private List<String> storageLocations;
	@ApiModelProperty(name="stationCode", value="工位")
	private String stationCode;
	private String stationName;
	@ApiModelProperty(name = "workshopCodes", value = "车间集合 列表查询用")
    private List<String> workshopCodes;

    @ApiModelProperty(name = "lineCodes", value = "产线集合 列表查询用")
    private List<String> lineCodes;


}

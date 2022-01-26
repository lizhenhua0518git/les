package com.zkzn.les.oms.pojo;

import java.util.List;

import com.zkzn.les.common.util.page.PageCondition;
import io.swagger.annotations.ApiModelProperty;
import net.logstash.logback.encoder.org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**.
 *
 * @author wangzhou
 * @date 2020年8月10日
 * @Description:调拨订单管理实体类
 */
public class SingleAllocate extends PageCondition {

	/**
	 *
	 */
	private static final long serialVersionUID = 8376381625116220104L;

	@ApiModelProperty(name="id", value="主键id")
	private String id;
	@ApiModelProperty(name="sourceSystem", value="来源系统，SAP/LES")
	private String sourceSystem;
	@ApiModelProperty(name="operationType", value="操作类型:SAP接口传输字段")
	private String operationType;
	@ApiModelProperty(name="systemDemandOrder", value="外围系统需求单号:SAP接口传输字段")
	private String systemDemandOrder;
	@ApiModelProperty(name="systemAllocationOrder", value="外围系统调拨单号:SAP接口传输字段")
	private String systemAllocationOrder;
	@ApiModelProperty(name="sapAllocationOrder", value="SAP调拨单号:SAP接口传输字段")
	private String sapAllocationOrder;
	@ApiModelProperty(name="voucheAccountDate", value="凭证中的过帐日期（调拨日期)")
	private String voucheAccountDate;
	@ApiModelProperty(name="voucheDate", value="凭证中的日期")
	private String voucheDate;
	@ApiModelProperty(name="userName", value="SAP中的用户")
	private String userName;
	@ApiModelProperty(name="allocationType", value="调拨类型 0-非限制 1-不合格品")
	private Integer allocationType;
	@ApiModelProperty(name="inWarehouseName", value="调入仓库名称")
	private String inWarehouseName;
	@ApiModelProperty(name="inWarehouseCode", value="调入仓库编号")
	private String inWarehouseCode;
	@ApiModelProperty(name="inStorageLocation", value="调入库存地点")
	private String inStorageLocation;
	@ApiModelProperty(name="outWarehouseName", value="调出仓库名称")
	private String outWarehouseName;
	@ApiModelProperty(name="outWarehouseCode", value="调出仓库编码")
	private String outWarehouseCode;
	@ApiModelProperty(name="outStorageLocation", value="调出库存地点")
	private String outStorageLocation;
	@ApiModelProperty(name="inFactory", value="调入工厂")
	private String inFactory;
	@ApiModelProperty(name="outFactory", value="调出工厂")
	private String outFactory;
	@ApiModelProperty(name="billType", value="订单类型")
	private String billType;
	@ApiModelProperty(name="billName", value="订单名称")
	private String billName;
	@ApiModelProperty(name="allocationOrder", value="订单号")
	private String allocationOrder;
	@ApiModelProperty(name="status", value="订单状态 0-已创建、5-已审核、10-已发货、15-已收货、99-已取消")
	private Integer status;
	@ApiModelProperty(name="stockCheckStatus", value="库存审核状态(0-待审核、5-已审核、10-库存不足)")
	private Integer stockCheckStatus;
	@ApiModelProperty(name="statusStr", value="订单状态 0-已创建、5-已审核、10-已发货、15-已收货、99-已取消")
	private String statusStr;//
	@ApiModelProperty(name="stockCheckStatusStr", value="库存审核状态(0-待审核、5-已审核、10-库存不足)")
	private String stockCheckStatusStr;
	@ApiModelProperty(name="inFactoryName", value="调入工厂名称")
	private String inFactoryName;
	@ApiModelProperty(name="outFactoryName", value="调出工厂名称")
	private String outFactoryName;
	@ApiModelProperty(name="sapInStorageLocation", value="sap调入库存地点")
	private String sapInStorageLocation;
	@ApiModelProperty(name="sapOutStorageLocation", value="sap调出库存地点")
	private String sapOutStorageLocation;
	@ApiModelProperty(name="startTimeStr", value="开始时间 用于时间区间查询")
	private String startTimeStr;
	@ApiModelProperty(name="endTimeStr", value="结束时间 用于时间区间查询")
	private String endTimeStr;

	@ApiModelProperty(name="endTimeStr", value="调出库存地点集合 列表查询用")
	private List<String> outStorageLocations;
	@ApiModelProperty(name="endTimeStr", value="调入库存地点集合  结束时间 用于时间区间查询")
	private List<String> inStorageLocations;
	@ApiModelProperty(name="stockType", value="库存类型")
	private Integer stockType;

	@ApiModelProperty(name="stockTypeStr", value="库存类型")
	private String stockTypeStr;
	@ApiModelProperty(name="allocationTypeStr", value="调拨类型 0-非限制 1-不合格品")
	private String allocationTypeStr;





	public String getStockTypeStr() {
		return stockTypeStr;
	}
	public void setStockTypeStr(String stockTypeStr) {
		this.stockTypeStr = stockTypeStr;
	}
	public String getAllocationTypeStr() {
		return allocationTypeStr;
	}
	public void setAllocationTypeStr(String allocationTypeStr) {
		this.allocationTypeStr = allocationTypeStr;
	}
	public List<String> getOutStorageLocations() {
		return outStorageLocations;
	}
	public void setOutStorageLocations(List<String> outStorageLocations) {
		this.outStorageLocations = outStorageLocations;
	}
	public List<String> getInStorageLocations() {
		return inStorageLocations;
	}
	public void setInStorageLocations(List<String> inStorageLocations) {
		this.inStorageLocations = inStorageLocations;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSourceSystem() {
		return sourceSystem;
	}
	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getSystemDemandOrder() {
		return systemDemandOrder;
	}
	public void setSystemDemandOrder(String systemDemandOrder) {
		this.systemDemandOrder = systemDemandOrder;
	}
	public String getSystemAllocationOrder() {
		return systemAllocationOrder;
	}
	public void setSystemAllocationOrder(String systemAllocationOrder) {
		this.systemAllocationOrder = systemAllocationOrder;
	}
	public String getSapAllocationOrder() {
		return sapAllocationOrder;
	}
	public void setSapAllocationOrder(String sapAllocationOrder) {
		this.sapAllocationOrder = sapAllocationOrder;
	}
	public String getVoucheAccountDate() {
		return voucheAccountDate;
	}
	public void setVoucheAccountDate(String voucheAccountDate) {
		this.voucheAccountDate = voucheAccountDate;
	}
	public String getVoucheDate() {
		return voucheDate;
	}
	public void setVoucheDate(String voucheDate) {
		this.voucheDate = voucheDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getAllocationType() {
		return allocationType;
	}
	public void setAllocationType(Integer allocationType) {
		this.allocationType = allocationType;
	}
	public String getInWarehouseName() {
		return inWarehouseName;
	}
	public void setInWarehouseName(String inWarehouseName) {
		this.inWarehouseName = inWarehouseName;
	}
	public String getInWarehouseCode() {
		return inWarehouseCode;
	}
	public void setInWarehouseCode(String inWarehouseCode) {
		this.inWarehouseCode = inWarehouseCode;
	}
	public String getInStorageLocation() {
		return inStorageLocation;
	}
	public void setInStorageLocation(String inStorageLocation) {
		this.inStorageLocation = inStorageLocation;
	}
	public String getOutWarehouseName() {
		return outWarehouseName;
	}
	public void setOutWarehouseName(String outWarehouseName) {
		this.outWarehouseName = outWarehouseName;
	}
	public String getOutWarehouseCode() {
		return outWarehouseCode;
	}
	public void setOutWarehouseCode(String outWarehouseCode) {
		this.outWarehouseCode = outWarehouseCode;
	}
	public String getOutStorageLocation() {
		return outStorageLocation;
	}
	public void setOutStorageLocation(String outStorageLocation) {
		this.outStorageLocation = outStorageLocation;
	}
	public String getInFactory() {
		return inFactory;
	}
	public void setInFactory(String inFactory) {
		this.inFactory = inFactory;
	}
	public String getOutFactory() {
		return outFactory;
	}
	public void setOutFactory(String outFactory) {
		this.outFactory = outFactory;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getBillName() {
		return billName;
	}
	public void setBillName(String billName) {
		this.billName = billName;
	}
	public String getAllocationOrder() {
		return allocationOrder;
	}
	public void setAllocationOrder(String allocationOrder) {
		this.allocationOrder = allocationOrder;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getStockCheckStatus() {
		return stockCheckStatus;
	}
	public void setStockCheckStatus(Integer stockCheckStatus) {
		this.stockCheckStatus = stockCheckStatus;
	}




	public String getStartTimeStr() {
		return startTimeStr;
	}
	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}
	public String getEndTimeStr() {
		return endTimeStr;
	}
	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}
	public String getInFactoryName() {
		return inFactoryName;
	}
	public void setInFactoryName(String inFactoryName) {
		this.inFactoryName = inFactoryName;
	}
	public String getOutFactoryName() {
		return outFactoryName;
	}
	public void setOutFactoryName(String outFactoryName) {
		this.outFactoryName = outFactoryName;
	}
	public String getSapInStorageLocation() {
		return sapInStorageLocation;
	}
	public void setSapInStorageLocation(String sapInStorageLocation) {
		this.sapInStorageLocation = sapInStorageLocation;
	}
	public String getSapOutStorageLocation() {
		return sapOutStorageLocation;
	}
	public void setSapOutStorageLocation(String sapOutStorageLocation) {
		this.sapOutStorageLocation = sapOutStorageLocation;
	}
	public String getStatusStr() {
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	public String getStockCheckStatusStr() {
		return stockCheckStatusStr;
	}
	public void setStockCheckStatusStr(String stockCheckStatusStr) {
		this.stockCheckStatusStr = stockCheckStatusStr;
	}


	public Integer getStockType() {
		return stockType;
	}
	public void setStockType(Integer stockType) {
		this.stockType = stockType;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ReflectionToStringBuilder.toString(this);
	}
}

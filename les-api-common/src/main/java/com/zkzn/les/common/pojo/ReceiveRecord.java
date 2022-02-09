package com.zkzn.les.common.pojo;

import com.zkzn.les.common.util.PageCondition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**.
 * 
 * @author wangzhou
 * @date 2020年8月20日
 * @Description:收货记录主表实体类（排号、点收、质检）
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ReceiveRecord extends PageCondition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5261192058006602073L;
	
	@ApiModelProperty(name="id",value="主键id")
	private String id;
	@ApiModelProperty(name="billCode",value="送货单号")
	private String billCode;
	@ApiModelProperty(name="orderCode",value="订单号")
	private String orderCode;
	@ApiModelProperty(name="factory",value="工厂")
	private String factory;
	@ApiModelProperty(name="factoryName",value="工厂名称")
	private String factoryName;
	@ApiModelProperty(name="warehouseCode",value="仓库编码")
	private String warehouseCode;
	@ApiModelProperty(name="billType",value="单据类型")
	private String billType;
	@ApiModelProperty(name="status",value="状态")
	private int status;
	@ApiModelProperty(name="statusStr",value="状态字符")
	private String statusStr;
	@ApiModelProperty(name="sourceId",value="来源单据id")
	private String sourceId;
	@ApiModelProperty(name="queueCode",value="排队号")
	private String queueCode;
	@ApiModelProperty(name="queueNum",value="排队顺序号")
	private int queueNum;
	@ApiModelProperty(name="queueTime",value="排队时间")
	private Date queueTime;
	@ApiModelProperty(name="uplodPlat",value="卸货月台编码")
	private String uplodPlat;
	@ApiModelProperty(name="supplierCode",value="发货方名称（供应商名称、仓库名称、车间名称）")
	private String supplierCode;
	@ApiModelProperty(name="supplierName",value="发货方名称（供应商名称、仓库名称、车间名称）")
	private String supplierName;
	@ApiModelProperty(name="uploadPlatId",value="卸货月台表id")
	private String uploadPlatId;
	@ApiModelProperty(name="billName",value="单据名称")
	private String billName;
	@ApiModelProperty(name="warehouseName",value="仓库名称")
	private String warehouseName;
	@ApiModelProperty(name="storageLocation",value="库存地点")
	private String storageLocation;
	@ApiModelProperty(name="sapStorageLocation",value="sap库存地点")
	private String sapStorageLocation;
	@ApiModelProperty(name="receivedTaskCode",value="入库任务号")
	private String receivedTaskCode;
	@ApiModelProperty(name="receivedStartTime",value="点收开始时间")
	private Date receivedStartTime;
	@ApiModelProperty(name="startTimeBeginStr",value="点收开始时间查询的开始区间")
	private String startTimeBeginStr;
	@ApiModelProperty(name="startTimeEndStr",value="点收开始时间查询的结束区间")
	private String startTimeEndStr;
	
	@ApiModelProperty(name="receivedEndTime",value="点收结束时间")
	private Date receivedEndTime;
	@ApiModelProperty(name="endTimeBeginStr",value="点收结束时间 查询的开始区间")
	private String endTimeBeginStr;
	@ApiModelProperty(name="endTimeEndStr",value="点收结束时间 查询的结束区间")
	private String endTimeEndStr;
	@ApiModelProperty(name="warehouseCodes",value="仓库编码集合，用于查询")
	private List<String> warehouseCodes;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getStatusStr() {
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getQueueCode() {
		return queueCode;
	}
	public void setQueueCode(String queueCode) {
		this.queueCode = queueCode;
	}
	public int getQueueNum() {
		return queueNum;
	}
	public void setQueueNum(int queueNum) {
		this.queueNum = queueNum;
	}
	public Date getQueueTime() {
		return queueTime;
	}
	public void setQueueTime(Date queueTime) {
		this.queueTime = queueTime;
	}
	public String getUplodPlat() {
		return uplodPlat;
	}
	public void setUplodPlat(String uplodPlat) {
		this.uplodPlat = uplodPlat;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getUploadPlatId() {
		return uploadPlatId;
	}
	public void setUploadPlatId(String uploadPlatId) {
		this.uploadPlatId = uploadPlatId;
	}
	public String getBillName() {
		return billName;
	}
	public void setBillName(String billName) {
		this.billName = billName;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getStorageLocation() {
		return storageLocation;
	}
	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}
	public String getSapStorageLocation() {
		return sapStorageLocation;
	}
	public void setSapStorageLocation(String sapStorageLocation) {
		this.sapStorageLocation = sapStorageLocation;
	}
	public String getReceivedTaskCode() {
		return receivedTaskCode;
	}
	public void setReceivedTaskCode(String receivedTaskCode) {
		this.receivedTaskCode = receivedTaskCode;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public Date getReceivedStartTime() {
		return receivedStartTime;
	}
	public void setReceivedStartTime(Date receivedStartTime) {
		this.receivedStartTime = receivedStartTime;
	}
	public String getStartTimeBeginStr() {
		return startTimeBeginStr;
	}
	public void setStartTimeBeginStr(String startTimeBeginStr) {
		this.startTimeBeginStr = startTimeBeginStr;
	}
	public String getStartTimeEndStr() {
		return startTimeEndStr;
	}
	public void setStartTimeEndStr(String startTimeEndStr) {
		this.startTimeEndStr = startTimeEndStr;
	}
	public Date getReceivedEndTime() {
		return receivedEndTime;
	}
	public void setReceivedEndTime(Date receivedEndTime) {
		this.receivedEndTime = receivedEndTime;
	}
	public String getEndTimeBeginStr() {
		return endTimeBeginStr;
	}
	public void setEndTimeBeginStr(String endTimeBeginStr) {
		this.endTimeBeginStr = endTimeBeginStr;
	}
	public String getEndTimeEndStr() {
		return endTimeEndStr;
	}
	public void setEndTimeEndStr(String endTimeEndStr) {
		this.endTimeEndStr = endTimeEndStr;
	}
	public List<String> getWarehouseCodes() {
		return warehouseCodes;
	}
	public void setWarehouseCodes(List<String> warehouseCodes) {
		this.warehouseCodes = warehouseCodes;
	}

}

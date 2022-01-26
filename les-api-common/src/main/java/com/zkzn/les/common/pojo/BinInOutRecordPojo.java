package com.zkzn.les.common.pojo;

import com.zkzn.les.common.util.PageCondition;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.util.Date;

/**
 * 仓位库存流水实体
 */
public class BinInOutRecordPojo extends PageCondition {

	private String id;//主键id
	private String materialCode;//物料号
	private String materialDesc;//物料描述
	private String factory;//工厂
	private String storageLocation;//库存地点
	private String warehouseCode;//仓库编码
	private String warehouseName;//仓库名称
	private Integer stockType;//库存类型  0自有、1寄售、2委外、3订单 来自字典表
	private String batchNo;//批次号
	private String supplierCode;//供应商编码
	private String supplierName;//供应商名称
	private String customerCode;//客户编码
	private String customerName;//客户名称
	private String orderCode;//订单号
	private Integer orderItemNo;//订单行号
	private String positionCode;//库位编码
	private String storageId;//库位id
	private Date receivedDate;//收货日期
	private Date changeTime;//变更时间
	private String changeCode;//变更单号
	private String changeType;//变更类型
	private Integer stockStatus;//库存状态  0非限制 1冻结  2待质检 来自字典表
	private String modifierId;//变更人
	private Double changeNum;//变更数量
	private String modifierName;//变更人名
	private String storageBinId;//库位库存id

	private String stockTypeName;//库存类型展示
	private String stockStatusName;//库存状态展示

	private String startingTime;
	private String endTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getMaterialDesc() {
		return materialDesc;
	}

	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getStorageLocation() {
		return storageLocation;
	}

	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public Integer getStockType() {
		return stockType;
	}

	public void setStockType(Integer stockType) {
		this.stockType = stockType;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getOrderItemNo() {
		return orderItemNo;
	}

	public void setOrderItemNo(Integer orderItemNo) {
		this.orderItemNo = orderItemNo;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public Date getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}

	public String getChangeCode() {
		return changeCode;
	}

	public void setChangeCode(String changeCode) {
		this.changeCode = changeCode;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public Integer getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(Integer stockStatus) {
		this.stockStatus = stockStatus;
	}

	@Override
	public String getModifierId() {
		return modifierId;
	}

	@Override
	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}

	public Double getChangeNum() {
		return changeNum;
	}

	public void setChangeNum(Double changeNum) {
		this.changeNum = changeNum;
	}

	@Override
	public String getModifierName() {
		return modifierName;
	}

	@Override
	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
	}

	public String getStorageBinId() {
		return storageBinId;
	}

	public void setStorageBinId(String storageBinId) {
		this.storageBinId = storageBinId;
	}

	public String getStockTypeName() {
		return stockTypeName;
	}

	public void setStockTypeName(String stockTypeName) {
		this.stockTypeName = stockTypeName;
	}

	public String getStockStatusName() {
		return stockStatusName;
	}

	public void setStockStatusName(String stockStatusName) {
		this.stockStatusName = stockStatusName;
	}

	public String getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(String startingTime) {
		this.startingTime = startingTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
}

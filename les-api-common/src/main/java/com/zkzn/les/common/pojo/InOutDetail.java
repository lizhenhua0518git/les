package com.zkzn.les.common.pojo;

import java.util.List;

import com.zkzn.les.common.util.PageCondition;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * .
 *
 * 功能描述:库存流水实体类
 * 
 * 时间:  2020-08-20 16:54
 *
 * @author  刘松山  
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InOutDetail extends PageCondition{

	/**
	 * 创建人: wangzhou
	 * 时间:20202020年4月3日下午5:02:18
	 * 功能描述:
	 */
	private static final long serialVersionUID = -4677782425872161445L;
	
 
	@ApiModelProperty(name = "id", value = "主键Id")
	private String id;

	@ApiModelProperty(name = "supplierCode", value = "物料编号")
	private String materialCode;

	@ApiModelProperty(name = "factory", value = "工厂")
	private String factory;

	@ApiModelProperty(name = "storageLocation", value = "库存地点")
	private String storageLocation;
	
	@ApiModelProperty(name = "sapStorageLocation", value = "工厂库存地点")
	private String sapStorageLocation;

	@ApiModelProperty(name = "stockCount", value = "变更数量")
	private double stockCount;

 
	@ApiModelProperty(name = "batchNo", value = "供应商编码")
	private String batchNo; // 批次号

	@ApiModelProperty(name = "stockType", value = "库存类型 0 自有 1寄售 2订单 3客户 来自字典 ")
	private String stockType;  
	
	@ApiModelProperty(name = "stockTypeName", value = "库存类型名称  0 自有 1寄售 2订单 3客户 ")
	private String stockTypeName;  
	
	@ApiModelProperty(name = "stockStatus", value = "库存状态  1非限制 2冻结  3待质检 来自字典表 ")
	private String stockStatus;  
	
	@ApiModelProperty(name = "stockStatusName", value = "库存状态名称 1非限制 2冻结  3待质检 来自字典表 ")
	private String stockStatusName;  
	
	@ApiModelProperty(name = "businessType", value = "变更类型 ")
	private String businessType;
	
	@ApiModelProperty(name = "businessTypeName", value = "变更类型名称 ")
	private String businessTypeName;
	
	@ApiModelProperty(name = "businessCode", value = "变更号 ")
	private String businessCode;

	@ApiModelProperty(name = "materialDesc", value = "物料名称")
	private String materialDesc;
	@ApiModelProperty(name = "materialUnit", value = "单位") // 物料描述
	private String materialUnit;

	@ApiModelProperty(name = "supplierCode", value = "供应商编码")
	private String supplierCode; //
	@ApiModelProperty(name = "supplierName", value = "供应商名称")
	private String supplierName;

	@ApiModelProperty(name = "customerCode", value = "客户编号")
	private String customerCode;

	@ApiModelProperty(name = "customerName", value = "客户名称")
	private String customerName;
	
	@ApiModelProperty(name = "warehouseName", value = "仓库名称")
	private String warehouseName;
	
	@ApiModelProperty(name = "warehouseCode", value = "仓库编码")
	private String warehouseCode;
	
	@ApiModelProperty(name = "orderCode", value = "订单号")
	private String orderCode;
	
	@ApiModelProperty(name = "orderItemNo", value = "订单行项目号")
	private String orderItemNo;
	
	@ApiModelProperty(name = "inStockId", value = "仓库库存表id")
	private String inStockId;

	private List<String> storageList;
	private List<String> warehouseList;
	private List<String> stockTypeList;
	private List<String> stockStatusList;
	private List<String> businessTypeList;
	
	@ApiModelProperty(name = "startTime", value = "开始时间 查询用")
	private String startTime;
	
	@ApiModelProperty(name = "endTime", value = "结束时间 查询用")
	private String endTime;
	/**
	 * 获取 id
	 * @return 返回 id
	 */
	public String getId() {
		return id;
	}
	/**
	 * 获取 materialCode
	 * @return 返回 materialCode
	 */
	public String getMaterialCode() {
		return materialCode;
	}
	/**
	 * 获取 factory
	 * @return 返回 factory
	 */
	public String getFactory() {
		return factory;
	}
	/**
	 * 获取 storageLocation
	 * @return 返回 storageLocation
	 */
	public String getStorageLocation() {
		return storageLocation;
	}
	/**
	 * 获取 stockCount
	 * @return 返回 stockCount
	 */
	public double getStockCount() {
		return stockCount;
	}
	/**
	 * 获取 batchNo
	 * @return 返回 batchNo
	 */
	public String getBatchNo() {
		return batchNo;
	}
	/**
	 * 获取 stockType
	 * @return 返回 stockType
	 */
	public String getStockType() {
		return stockType;
	}
	/**
	 * 获取 stockTypeName
	 * @return 返回 stockTypeName
	 */
	public String getStockTypeName() {
		return stockTypeName;
	}
	/**
	 * 获取 stockStatus
	 * @return 返回 stockStatus
	 */
	public String getStockStatus() {
		return stockStatus;
	}
	/**
	 * 获取 stockStatusName
	 * @return 返回 stockStatusName
	 */
	public String getStockStatusName() {
		return stockStatusName;
	}
	/**
	 * 获取 businessType
	 * @return 返回 businessType
	 */
	public String getBusinessType() {
		return businessType;
	}
	/**
	 * 获取 businessTypeName
	 * @return 返回 businessTypeName
	 */
	public String getBusinessTypeName() {
		return businessTypeName;
	}
	/**
	 * 获取 businessCode
	 * @return 返回 businessCode
	 */
	public String getBusinessCode() {
		return businessCode;
	}
	/**
	 * 获取 materialDesc
	 * @return 返回 materialDesc
	 */
	public String getMaterialDesc() {
		return materialDesc;
	}
	/**
	 * 获取 materialUnit
	 * @return 返回 materialUnit
	 */
	public String getMaterialUnit() {
		return materialUnit;
	}
	/**
	 * 获取 supplierCode
	 * @return 返回 supplierCode
	 */
	public String getSupplierCode() {
		return supplierCode;
	}
	/**
	 * 获取 supplierName
	 * @return 返回 supplierName
	 */
	public String getSupplierName() {
		return supplierName;
	}
	/**
	 * 获取 customerCode
	 * @return 返回 customerCode
	 */
	public String getCustomerCode() {
		return customerCode;
	}
	/**
	 * 获取 customerName
	 * @return 返回 customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * 获取 warehouseName
	 * @return 返回 warehouseName
	 */
	public String getWarehouseName() {
		return warehouseName;
	}
	/**
	 * 获取 warehouseCode
	 * @return 返回 warehouseCode
	 */
	public String getWarehouseCode() {
		return warehouseCode;
	}
	/**
	 * 获取 orderCode
	 * @return 返回 orderCode
	 */
	public String getOrderCode() {
		return orderCode;
	}
	/**
	 * 获取 orderItemNo
	 * @return 返回 orderItemNo
	 */
	public String getOrderItemNo() {
		return orderItemNo;
	}
	/**
	 * 获取 storageList
	 * @return 返回 storageList
	 */
	public List<String> getStorageList() {
		return storageList;
	}
	/**
	 * 获取 warehouseList
	 * @return 返回 warehouseList
	 */
	public List<String> getWarehouseList() {
		return warehouseList;
	}
	/**
	 * 获取 stockTypeList
	 * @return 返回 stockTypeList
	 */
	public List<String> getStockTypeList() {
		return stockTypeList;
	}
	/**
	 * 获取 stockStatusList
	 * @return 返回 stockStatusList
	 */
	public List<String> getStockStatusList() {
		return stockStatusList;
	}
	/**
	 * 获取 businessTypeList
	 * @return 返回 businessTypeList
	 */
	public List<String> getBusinessTypeList() {
		return businessTypeList;
	}
	/**
	 * 设置 id
	 * @param id 对id进行赋值
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 设置 materialCode
	 * @param materialCode 对materialCode进行赋值
	 */
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	/**
	 * 设置 factory
	 * @param factory 对factory进行赋值
	 */
	public void setFactory(String factory) {
		this.factory = factory;
	}
	/**
	 * 设置 storageLocation
	 * @param storageLocation 对storageLocation进行赋值
	 */
	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}
	/**
	 * 设置 stockCount
	 * @param stockCount 对stockCount进行赋值
	 */
	public void setStockCount(double stockCount) {
		this.stockCount = stockCount;
	}
	/**
	 * 设置 batchNo
	 * @param batchNo 对batchNo进行赋值
	 */
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	/**
	 * 设置 stockType
	 * @param stockType 对stockType进行赋值
	 */
	public void setStockType(String stockType) {
		this.stockType = stockType;
	}
	/**
	 * 设置 stockTypeName
	 * @param stockTypeName 对stockTypeName进行赋值
	 */
	public void setStockTypeName(String stockTypeName) {
		this.stockTypeName = stockTypeName;
	}
	/**
	 * 设置 stockStatus
	 * @param stockStatus 对stockStatus进行赋值
	 */
	public void setStockStatus(String stockStatus) {
		this.stockStatus = stockStatus;
	}
	/**
	 * 设置 stockStatusName
	 * @param stockStatusName 对stockStatusName进行赋值
	 */
	public void setStockStatusName(String stockStatusName) {
		this.stockStatusName = stockStatusName;
	}
	/**
	 * 设置 businessType
	 * @param businessType 对businessType进行赋值
	 */
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	/**
	 * 设置 businessTypeName
	 * @param businessTypeName 对businessTypeName进行赋值
	 */
	public void setBusinessTypeName(String businessTypeName) {
		this.businessTypeName = businessTypeName;
	}
	/**
	 * 设置 businessCode
	 * @param businessCode 对businessCode进行赋值
	 */
	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}
	/**
	 * 设置 materialDesc
	 * @param materialDesc 对materialDesc进行赋值
	 */
	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}
	/**
	 * 设置 materialUnit
	 * @param materialUnit 对materialUnit进行赋值
	 */
	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}
	/**
	 * 设置 supplierCode
	 * @param supplierCode 对supplierCode进行赋值
	 */
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	/**
	 * 设置 supplierName
	 * @param supplierName 对supplierName进行赋值
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	/**
	 * 设置 customerCode
	 * @param customerCode 对customerCode进行赋值
	 */
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	/**
	 * 设置 customerName
	 * @param customerName 对customerName进行赋值
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * 设置 warehouseName
	 * @param warehouseName 对warehouseName进行赋值
	 */
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	/**
	 * 设置 warehouseCode
	 * @param warehouseCode 对warehouseCode进行赋值
	 */
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	/**
	 * 设置 orderCode
	 * @param orderCode 对orderCode进行赋值
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	/**
	 * 设置 orderItemNo
	 * @param orderItemNo 对orderItemNo进行赋值
	 */
	public void setOrderItemNo(String orderItemNo) {
		this.orderItemNo = orderItemNo;
	}
	/**
	 * 设置 storageList
	 * @param storageList 对storageList进行赋值
	 */
	public void setStorageList(List<String> storageList) {
		this.storageList = storageList;
	}
	/**
	 * 设置 warehouseList
	 * @param warehouseList 对warehouseList进行赋值
	 */
	public void setWarehouseList(List<String> warehouseList) {
		this.warehouseList = warehouseList;
	}
	/**
	 * 设置 stockTypeList
	 * @param stockTypeList 对stockTypeList进行赋值
	 */
	public void setStockTypeList(List<String> stockTypeList) {
		this.stockTypeList = stockTypeList;
	}
	/**
	 * 设置 stockStatusList
	 * @param stockStatusList 对stockStatusList进行赋值
	 */
	public void setStockStatusList(List<String> stockStatusList) {
		this.stockStatusList = stockStatusList;
	}
	/**
	 * 设置 businessTypeList
	 * @param businessTypeList 对businessTypeList进行赋值
	 */
	public void setBusinessTypeList(List<String> businessTypeList) {
		this.businessTypeList = businessTypeList;
	}
	/**
	 * 获取 startTime
	 * @return 返回 startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * 获取 endTime
	 * @return 返回 endTime
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * 设置 startTime
	 * @param startTime 对startTime进行赋值
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * 设置 endTime
	 * @param endTime 对endTime进行赋值
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取 sapStorageLocation
	 * @return 返回 sapStorageLocation
	 */
	public String getSapStorageLocation() {
		return sapStorageLocation;
	}
	/**
	 * 获取 inStockId
	 * @return 返回 inStockId
	 */
	public String getInStockId() {
		return inStockId;
	}
	/**
	 * 设置 sapStorageLocation
	 * @param sapStorageLocation 对sapStorageLocation进行赋值
	 */
	public void setSapStorageLocation(String sapStorageLocation) {
		this.sapStorageLocation = sapStorageLocation;
	}
	/**
	 * 设置 inStockId
	 * @param inStockId 对inStockId进行赋值
	 */
	public void setInStockId(String inStockId) {
		this.inStockId = inStockId;
	}

}

package com.zkzn.les.wms.pojo;

import java.util.Date;

/**
 * @ProjectName: lesGroup
 * @Package: com.zkzn.les.wms.app.pojo
 * @ClassName: MaterialStorageBin
 * @Author: 胡志明
 * @Description: 仓位库存数据
 * @Date: 2020/9/11 15:42
 */
public class MaterialStorageBin {
    private String id;
    private String materialStorageBinId;//主键id
    private String positionCode;//库位编码
    private String storageId;//仓位表id
    private String materialCode;//物料号
    private String materialUnit;//单位
    private Double stockCount;//库存数量
    private Double enableCount;//可用库存
    private Double preUseCount;//预占库存
    private String supplierCode;//供应商编码
    private String supplierName;//供应商名称
    private Date receiveDate;//收货日期
    private String batchNo;//批次
    private Date createTime;//创建时间
    private String storageLocation;//库存地点
    private String factory;//工厂
    private String warehouseCode;//仓库编码
    private String warehouseName;//仓库名称
    private String orderCode;//订单号
    private Integer orderItemNo;//订单行号
    private Integer stockStatus;//库存状态:0非限制 1采购冻结 2待质检  3生产冻结
    private String customerCode;//客户编码
    private String customerName;//客户名称
    private Date modifiedTime;//修改时间
    private Integer stockType;//库存类型 null 一般库存 、1寄售、2委外、3订单 4 客户
    private String stationCode;//工位
    private Double inspectionCount;//待质检库存
    private Double unenableCount;//不合格品库存数量
    private String materialDesc;
    private String orderDetailId; //任务详情表id
    private Integer unenableReason;//不合格原因: 1 质检不合格 2 生产不合格


    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaterialDesc() {
        return materialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }

    private String originalPositionId;//原仓位Id


    public String getMaterialStorageBinId() {
        return materialStorageBinId;
    }

    public void setMaterialStorageBinId(String materialStorageBinId) {
        this.materialStorageBinId = materialStorageBinId;
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

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialUnit() {
        return materialUnit;
    }

    public void setMaterialUnit(String materialUnit) {
        this.materialUnit = materialUnit;
    }

    public Double getStockCount() {
        return stockCount;
    }

    public void setStockCount(Double stockCount) {
        this.stockCount = stockCount;
    }

    public Double getEnableCount() {
        return enableCount;
    }

    public void setEnableCount(Double enableCount) {
        this.enableCount = enableCount;
    }

    public Double getPreUseCount() {
        return preUseCount;
    }

    public void setPreUseCount(Double preUseCount) {
        this.preUseCount = preUseCount;
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

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
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

    public Integer getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(Integer stockStatus) {
        this.stockStatus = stockStatus;
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

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getStockType() {
        return stockType;
    }

    public void setStockType(Integer stockType) {
        this.stockType = stockType;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public Double getInspectionCount() {
        return inspectionCount;
    }

    public void setInspectionCount(Double inspectionCount) {
        this.inspectionCount = inspectionCount;
    }

    public Double getUnenableCount() {
        return unenableCount;
    }

    public void setUnenableCount(Double unenableCount) {
        this.unenableCount = unenableCount;
    }

    public String getOriginalPositionId() {
        return originalPositionId;
    }

    public void setOriginalPositionId(String originalPositionId) {
        this.originalPositionId = originalPositionId;
    }


    public Integer getUnenableReason() {
        return unenableReason;
    }

    public MaterialStorageBin setUnenableReason(Integer unenableReason) {
        this.unenableReason = unenableReason;
        return this;
    }
}

package com.zkzn.les.common.pojo;

import java.util.Date;

/**
 * @ProjectName: lesGroup
 * @Package: com.zkzn.les.wms.app.pojo
 * @ClassName: MaterialInStock
 * @Author: 胡志明
 * @Description:
 * @Date: 2020/9/7 16:55
 */
public class MaterialInStock {

    private String inStockId;//物料库存ID
    private String materialCode;//物料编号
    private String factory;//工厂
    private String batchNo;//批次号
    private String supplierCode;//供应商编码
    private String storageLocation;//库存地点
    private Double stockCount;//库存数量
    private Double frozenCount;//预占用库存
    private String supplierName;//供应商名称
    private String customerCode;//客户编号
    private String customerName;//客户名称
    private Date createTime;//创建时间
    private String orderCode;//订单号
    private String createrId;//创建人id
    private String createrName;//创建人
    private Double useCount;//可用库存
    private String stockStatus;//库存状态  1非限制 2冻结  3待质检 来自字典表
    private String stockType;//库存类型  1自有、2寄售、3委外、4订单 来自字典表
    private String sapStorageLocation;//工厂库存地点
    private String materialDesc;//物料名称
    private String materialUnit;//物料单位
    private String warehouseCode;//仓库编码
    private String warehouseName;//仓库名称
    private String stationCode;//工位

    public String getInStockId() {
        return inStockId;
    }

    public void setInStockId(String inStockId) {
        this.inStockId = inStockId;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
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

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public Double getStockCount() {
        return stockCount;
    }

    public void setStockCount(Double stockCount) {
        this.stockCount = stockCount;
    }

    public Double getFrozenCount() {
        return frozenCount;
    }

    public void setFrozenCount(Double frozenCount) {
        this.frozenCount = frozenCount;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getCreaterId() {
        return createrId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public Double getUseCount() {
        return useCount;
    }

    public void setUseCount(Double useCount) {
        this.useCount = useCount;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public String getSapStorageLocation() {
        return sapStorageLocation;
    }

    public void setSapStorageLocation(String sapStorageLocation) {
        this.sapStorageLocation = sapStorageLocation;
    }

    public String getMaterialDesc() {
        return materialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }

    public String getMaterialUnit() {
        return materialUnit;
    }

    public void setMaterialUnit(String materialUnit) {
        this.materialUnit = materialUnit;
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

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }
}

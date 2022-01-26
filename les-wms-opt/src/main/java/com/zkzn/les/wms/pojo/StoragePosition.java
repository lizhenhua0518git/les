package com.zkzn.les.wms.pojo;

import java.util.List;

/**
 * @ProjectName: lesGroup
 * @Package: com.zkzn.les.wms.app.pojo
 * @ClassName: StoragePosition
 * @Author: 胡志明
 * @Description: 仓位数据信息
 * @Date: 2020/9/10 15:06
 */
public class StoragePosition {

    private String positionId;//仓位ID

    private String positionCode;//仓位号

    private Double stockCount;//库存数量

    private Double materialNum;//上架数量

    private String supplierCode;//供应商编号

    private String supplierName;//供应商名称

    private String stationCode;//工位号

    private String storageLocation;//库存地点

    private String upperId;//上架Id/调拨单子集id

    private String pId;//调拨单父Id

    private String detailId;//调拨单详情主键


    private String materialCode;//物料号

    private Integer stockStatus;//库存状态:0非限制 1冻结

    private Integer deviantData;//0正常数据。1是不符合存储策略的数据

    private List<String> materialSerialList;//序列号集合

    private String batchNo;//批次号

    private Integer inspectionStatus;//合格不合格状态



    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public Double getStockCount() {
        return stockCount;
    }

    public void setStockCount(Double stockCount) {
        this.stockCount = stockCount;
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

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public String getUpperId() {
        return upperId;
    }

    public void setUpperId(String upperId) {
        this.upperId = upperId;
    }

    public Integer getDeviantData() {
        return deviantData;
    }

    public void setDeviantData(Integer deviantData) {
        this.deviantData = deviantData;
    }

    public List<String> getMaterialSerialList() {
        return materialSerialList;
    }

    public void setMaterialSerialList(List<String> materialSerialList) {
        this.materialSerialList = materialSerialList;
    }

    public Double getMaterialNum() {
        return materialNum;
    }

    public void setMaterialNum(Double materialNum) {
        this.materialNum = materialNum;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public Integer getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(Integer stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Integer getInspectionStatus() {
        return inspectionStatus;
    }

    public void setInspectionStatus(Integer inspectionStatus) {
        this.inspectionStatus = inspectionStatus;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }
}

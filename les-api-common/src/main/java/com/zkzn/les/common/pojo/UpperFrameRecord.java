package com.zkzn.les.common.pojo;

import java.util.Date;

/**
 * @ProjectName: lesGroup
 * @Package: com.zkzn.les.wms.app.pojo
 * @ClassName: UpperFrameRecord
 * @Author: 胡志明
 * @Description: 上架表实体
 * @Date: 2020/9/7 14:06
 */
public class UpperFrameRecord{

    private String upperRecordId;//主键id
    private String receiveRecordId;//入库任务主表id
    private String recevieDetailId;//入库任务子表id
    private String recommendedPositionId;//推荐仓位号id
    private String recommendedPositionCode;//推荐仓位号
    private String positionCode;//上架仓位号
    private String positionId;//上架仓位id
    private String materialCode;//物料号
    private String factory;//工厂
    private String materialUnit;//物料单位
    private Double materialNum;//物料数量
    private Integer upperType;//0-合格品，1-不合格品
    private String storageLocation;//库存地点
    private Date upperTime;//上架时间
    private String upperName;//上架人名
    private String upperId;//上架人
    private Date createTime;//创建时间
    private Integer status;//0-待上架，1-上架完成
    private String carrierCode;//载具号
    private String carrierId;//载具id
    private String upperTaskCode;//上架任务号
    private String supplierCode;//供应商编码
    private String supplierName;//供应商名称
    private String batchNo;//批次号
    private String orderCode;//订单号
    private String custormer;//客户编码
    private String customName;//客户名称
    private String stationCode;//工位

    private String storageId;//库位id

    public String getUpperRecordId() {
        return upperRecordId;
    }

    public void setUpperRecordId(String upperRecordId) {
        this.upperRecordId = upperRecordId;
    }

    public String getReceiveRecordId() {
        return receiveRecordId;
    }

    public void setReceiveRecordId(String receiveRecordId) {
        this.receiveRecordId = receiveRecordId;
    }

    public String getRecevieDetailId() {
        return recevieDetailId;
    }

    public void setRecevieDetailId(String recevieDetailId) {
        this.recevieDetailId = recevieDetailId;
    }

    public String getRecommendedPositionId() {
        return recommendedPositionId;
    }

    public void setRecommendedPositionId(String recommendedPositionId) {
        this.recommendedPositionId = recommendedPositionId;
    }

    public String getRecommendedPositionCode() {
        return recommendedPositionCode;
    }

    public void setRecommendedPositionCode(String recommendedPositionCode) {
        this.recommendedPositionCode = recommendedPositionCode;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
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

    public String getMaterialUnit() {
        return materialUnit;
    }

    public void setMaterialUnit(String materialUnit) {
        this.materialUnit = materialUnit;
    }

    public Double getMaterialNum() {
        return materialNum;
    }

    public void setMaterialNum(Double materialNum) {
        this.materialNum = materialNum;
    }

    public Integer getUpperType() {
        return upperType;
    }

    public void setUpperType(Integer upperType) {
        this.upperType = upperType;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public Date getUpperTime() {
        return upperTime;
    }

    public void setUpperTime(Date upperTime) {
        this.upperTime = upperTime;
    }

    public String getUpperName() {
        return upperName;
    }

    public void setUpperName(String upperName) {
        this.upperName = upperName;
    }

    public String getUpperId() {
        return upperId;
    }

    public void setUpperId(String upperId) {
        this.upperId = upperId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    public String getUpperTaskCode() {
        return upperTaskCode;
    }

    public void setUpperTaskCode(String upperTaskCode) {
        this.upperTaskCode = upperTaskCode;
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

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getCustormer() {
        return custormer;
    }

    public void setCustormer(String custormer) {
        this.custormer = custormer;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }
}

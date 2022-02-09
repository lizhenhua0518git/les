package com.zkzn.les.common.pojo;

import com.zkzn.les.common.util.PageCondition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**.
 *
 *
 * 功能描述：储位信息实体类
 * @author wangzhou
 * 时间：2018年7月4日
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StoragePosition  extends PageCondition{

    /**.
     *
     */
    private static final long serialVersionUID = 1L;

    private String id;//主键id

    private String positionName;//库位名称

    private String parentId;//父节点id

    private String shelfId;//所在货架

    private String shelfName;//所在货架名称

    private Integer shelfFloor;//所在货架层级

    private String stationId;//工位ID

    private String stationCode;//工位号

    private double maxLoadWeight;//最大承重数

    private Integer frozenFlagEntry;//入库冻结标识 0：不冻结 1：冻结

    private Integer frozenFlagDelivery;//出库冻结标识 0：不冻结 1：冻结

    private Integer binType;//仓位类型  1-地堆 2-货架

    private String storageType;//SAP储位类型

    private String warehouse;//所属仓库

    private String factory;//工厂

    private String shelfCode;

    private String tunnel;//巷道

    private Integer rowNumber;//排

    private Integer performNumber;//行

    private Integer layerNumber;//层

    private String storageLocation;//库存地点

    private String warehouseCode;//仓库编码
    private String warehouseName;//仓库名称
    private String positionCode;//库位编码
    private String areaCode;//库区编码
    private String areaName;//库区名称
    private String areaType;//库区类型
    private String workAreaCode;//作业区域编码
    private String workAreaName;//作业区域名称
    private Integer status;//状态 0-禁用 1-启用
    private Integer occupyStatus;//占用状态0、占用  1、空闲
    private String statusName;//状态名称

    private String areaId;//库区ID
    private String workAreaId;//作业区域ID

    public Integer getOccupyStatus() {
        return occupyStatus;
    }

    public void setOccupyStatus(Integer occupyStatus) {
        this.occupyStatus = occupyStatus;
    }

    public String getWorkAreaId() {
        return workAreaId;
    }

    public void setWorkAreaId(String workAreaId) {
        this.workAreaId = workAreaId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getWorkAreaCode() {
        return workAreaCode;
    }

    public void setWorkAreaCode(String workAreaCode) {
        this.workAreaCode = workAreaCode;
    }

    public String getWorkAreaName() {
        return workAreaName;
    }

    public void setWorkAreaName(String workAreaName) {
        this.workAreaName = workAreaName;
    }

    public String getShelfCode() {
        return shelfCode;
    }

    public void setShelfCode(String shelfCode) {
        this.shelfCode = shelfCode;
    }

    public String getTunnel() {
        return tunnel;
    }

    public void setTunnel(String tunnel) {
        this.tunnel = tunnel;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Integer getPerformNumber() {
        return performNumber;
    }

    public void setPerformNumber(Integer performNumber) {
        this.performNumber = performNumber;
    }

    public Integer getLayerNumber() {
        return layerNumber;
    }

    public void setLayerNumber(Integer layerNumber) {
        this.layerNumber = layerNumber;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public String getId() {
        return id;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getShelfId() {
        return shelfId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public void setShelfId(String shelfId) {
        this.shelfId = shelfId;
    }

    public Integer getShelfFloor() {
        return shelfFloor;
    }

    public void setShelfFloor(Integer shelfFloor) {
        this.shelfFloor = shelfFloor;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public double getMaxLoadWeight() {
        return maxLoadWeight;
    }

    public void setMaxLoadWeight(double maxLoadWeight) {
        this.maxLoadWeight = maxLoadWeight;
    }

    public Integer getFrozenFlagEntry() {
        return frozenFlagEntry;
    }

    public void setFrozenFlagEntry(Integer frozenFlagEntry) {
        this.frozenFlagEntry = frozenFlagEntry;
    }

    public Integer getFrozenFlagDelivery() {
        return frozenFlagDelivery;
    }

    public void setFrozenFlagDelivery(Integer frozenFlagDelivery) {
        this.frozenFlagDelivery = frozenFlagDelivery;
    }


    public Integer getBinType() {
        return binType;
    }

    public void setBinType(Integer binType) {
        this.binType = binType;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }


    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

}


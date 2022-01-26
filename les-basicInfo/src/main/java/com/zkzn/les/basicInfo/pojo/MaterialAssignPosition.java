package com.zkzn.les.basicInfo.pojo;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import com.zkzn.les.basicInfo.util.PageCondition;

public class MaterialAssignPosition  extends PageCondition{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2489392478248651279L;
	private String id;
	private String materialCode;
	private String materialDesc;//物料描述
	private String warehouseCode;//仓库
	private String warehouseName;//仓库名称
	private String storagePositionId;
	private String positionCode;
	private String factory;
	private String storageLocation;
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
	public String getStoragePositionId() {
		return storagePositionId;
	}
	public void setStoragePositionId(String storagePositionId) {
		this.storagePositionId = storagePositionId;
	}
	public String getPositionCode() {
		return positionCode;
	}
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
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
	
	public String getMaterialDesc() {
		return materialDesc;
	}
	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
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
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}

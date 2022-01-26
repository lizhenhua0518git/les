/*
 * 文 件 名:  OverstorageStrategy.java
 * 版    权:  
 * 描    述:  描述:
 * 修 改 人:  liusongshan 
 * 修改时间:  2018年8月7日
 * 跟踪单号:  跟踪单号:
 * 修改单号:  修改单号:
 * 修改内容:  修改内容:
 */
package com.zkzn.les.basicInfo.pojo;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import com.zkzn.les.basicInfo.util.PageCondition;

/**.
 * 功能描述:超储策略管理页面展示值
 * 时间:  2018年8月7日
 * @author  liusongshan  
 * 
 */
public class OverstorageStrategy extends PageCondition{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private String materialCode;			//物料编号
	private String materialDesc;			//物料名称
	private String materialUnit; 			//物料单位
	private String factory;					//工厂编号
	private String purchaseType;			//采购类型
	private String storageLocation;			//库存地点
	private double boundCount;					//超储临界值
	
	private String warehouseCode;//仓库编码
	private String warehouseName;// 仓库名称
	
	// 查询需要的参数
	private String boundCountIsNull;           //超储临界值是否为空
	
	public String getBoundCountIsNull() {
		return boundCountIsNull;
	}
	public void setBoundCountIsNull(String boundCountIsNull) {
		this.boundCountIsNull = boundCountIsNull;
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
	public String getMaterialUnit() {
		return materialUnit;
	}
	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	public String getStorageLocation() {
		return storageLocation;
	}
	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}
	public double getBoundCount() {
		return boundCount;
	}
	public void setBoundCount(double boundCount) {
		this.boundCount = boundCount;
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

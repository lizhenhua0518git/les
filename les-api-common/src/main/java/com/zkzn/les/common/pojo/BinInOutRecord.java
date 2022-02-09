package com.zkzn.les.common.pojo;

import com.zkzn.les.common.util.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**.
 * 
 * @author wangzhou
 * 仓库库存流水 实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BinInOutRecord extends PageCondition{

	/**
	 * 创建人: wangzhou
	 * 时间:20202020年4月3日下午3:53:54
	 * 功能描述:
	 */
	private static final long serialVersionUID = -5405988296084487804L;

	
	private String 	id;		
	private String binStockId;//仓位库存id
	private Integer storageType;//库存类别:1-普通、2-采购冻结、3-厂内冻结
	private String businessNode;//业务节点
	private Double mvCount;//移动数量
	private String specialType;//特殊类型
	private String specialTypeCode;//特殊类型编号
	private String voucherCode;//凭证单号
	private Integer voucherItemNo;//凭证行项目号
	private Date mvTime;//移动时间
	private String sourceBillCode;//来源单据号
	private Integer sourceBillItemNo;//来源单据行项目号
	
	
	//查询追加
    private String positionCode;//仓位号
	private String materialCode;//物料编号   
    private String materialDesc;
    private String materialUnit;
	private String factory;//工厂  
	private String storageLocation;//库存地点 
    private String batchNo;//批次号
    private String projectId;//项目号 
    private String stationCode;//工位编码
	private String supplierCode;//供应商                                           	                  
	private String supplierName;//供应商                                           	                  
    private String lastMaintainTime;//最后一次定保时间/到期日
    private String mvTim;//移动时间
    private String storageTypes;//库存类别
    private String evaluativeType;//评估类型
    private String warehouse;// 仓库编码
    private List<String> warehouses;//仓库编码
    
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public String getEvaluativeType() {
		return evaluativeType;
	}
	public void setEvaluativeType(String evaluativeType) {
		this.evaluativeType = evaluativeType;
	}
	public String getStorageTypes() {
		return storageTypes;
	}
	public void setStorageTypes(String storageTypes) {
		this.storageTypes = storageTypes;
	}
	public String getMvTim() {
		return mvTim;
	}
	public void setMvTim(String mvTim) {
		this.mvTim = mvTim;
	}
	public BinInOutRecord() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBinStockId() {
		return binStockId;
	}
	public void setBinStockId(String binStockId) {
		this.binStockId = binStockId;
	}
	public Integer getStorageType() {
		return storageType;
	}
	public void setStorageType(Integer storageType) {
		this.storageType = storageType;
	}
	public String getBusinessNode() {
		return businessNode;
	}
	public void setBusinessNode(String businessNode) {
		this.businessNode = businessNode;
	}
	public Double getMvCount() {
		return mvCount;
	}
	public void setMvCount(Double mvCount) {
		this.mvCount = mvCount;
	}
	public String getSpecialType() {
		return specialType;
	}
	public void setSpecialType(String specialType) {
		this.specialType = specialType;
	}
	public String getSpecialTypeCode() {
		return specialTypeCode;
	}
	public void setSpecialTypeCode(String specialTypeCode) {
		this.specialTypeCode = specialTypeCode;
	}
	public String getVoucherCode() {
		return voucherCode;
	}
	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}
	public Integer getVoucherItemNo() {
		return voucherItemNo;
	}
	public void setVoucherItemNo(Integer voucherItemNo) {
		this.voucherItemNo = voucherItemNo;
	}
	public Date getMvTime() {
		return mvTime;
	}
	public void setMvTime(Date date) {
		this.mvTime = date;
	}
	public String getSourceBillCode() {
		return sourceBillCode;
	}
	public void setSourceBillCode(String sourceBillCode) {
		this.sourceBillCode = sourceBillCode;
	}
	public Integer getSourceBillItemNo() {
		return sourceBillItemNo;
	}
	public void setSourceBillItemNo(Integer sourceBillItemNo) {
		this.sourceBillItemNo = sourceBillItemNo;
	}
	public String getPositionCode() {
		return positionCode;
	}
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
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
	public String getStorageLocation() {
		return storageLocation;
	}
	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getStationCode() {
		return stationCode;
	}
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}
	public String getLastMaintainTime() {
		return lastMaintainTime;
	}
	public void setLastMaintainTime(String lastMaintainTime) {
		this.lastMaintainTime = lastMaintainTime;
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
	
	
	public List<String> getWarehouses() {
		return warehouses;
	}
	public void setWarehouses(List<String> warehouses) {
		this.warehouses = warehouses;
	}
}

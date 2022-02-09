package com.zkzn.les.common.pojo;

import com.zkzn.les.common.util.PageCondition;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Material extends PageCondition {

	@ApiModelProperty(name = "id", value = "主键")
	private String id;

	@ApiModelProperty(name = "materialCode", value = "物料编号")
	private String materialCode;

	@ApiModelProperty(name = "materialDesc", value = "物料名称")
	private String materialDesc;

	@ApiModelProperty(name = "materialUnit", value = "物料单位")
	private String materialUnit;

	@ApiModelProperty(name = "materialType", value = "物料类型  ZFRT-成品 ZHLB-半成品  ZROH-原材料 ZFW-服务物料 ZSB-设备备件、工装模具、工具、仪器仪表、劳保用品")
	private String materialType;

	@ApiModelProperty(name = "materialTypeName", value = "物料类型名称  ZFRT-成品 ZHLB-半成品  ZROH-原材料 ZFW-服务物料 ZSB-设备备件、工装模具、工具、仪器仪表、劳保用品")
	private String materialTypeName;

	@ApiModelProperty(name = "factory", value = "工厂")
	private String factory;

	@ApiModelProperty(name = "isBatch", value = "X-启用批次")
	private String isBatch;
	@ApiModelProperty(name = "isBatchName", value = "是否启用批次 X-启用批次")
	private String isBatchName;

	@ApiModelProperty(name = "purchaseType", value = "采购类型 E-自制 F-外购  X-即可自制又可外购")
	private String purchaseType;

	@ApiModelProperty(name = "purchaseTypeName", value = "采购类型名称 E-自制 F-外购  X-即可自制又可外购")
	private String purchaseTypeName;

	@ApiModelProperty(name = "purchaseClass", value = "特殊采购类 10-寄售 30-外协 50-虚拟件")
	private String purchaseClass;

	@ApiModelProperty(name = "purchaseClassName", value = "特殊采购类名称 10-寄售 30-外协 50-虚拟件")
	private String purchaseClassName;

	@ApiModelProperty(name = "storageLocation", value = "库存地点")
	private String storageLocation;

	@ApiModelProperty(name = "warehouse", value = "仓库号")
	private String warehouse;

	@ApiModelProperty(name = "storageType", value = "0-地堆 1-货架")
	private Long storageType;

	@ApiModelProperty(name = "inspectMgrUid", value = "质检SAP管理工")
	private String inspectMgrUid;

	@ApiModelProperty(name = "assembleMgrUid", value = "集配SAP管理工")
	private String assembleMgrUid;

	@ApiModelProperty(name = "deliveryCarrierType", value = "配送工装类型")
	private String deliveryCarrierType;

	@ApiModelProperty(name = "materialImgUrl", value = "物料图片地址")
	private String materialImgUrl;

	@ApiModelProperty(name = "status", value = "物料状态 0-禁用 1-启用")
	private Long status;

	@ApiModelProperty(name = "statusName", value = "物料状态名称 0-禁用 1-启用")
	private Long statusName;

	@ApiModelProperty(name = "netWeight", value = "净重")
	private double netWeight;

	@ApiModelProperty(name = "boundCount", value = "最大库存水平")
	private double boundCount;

	@ApiModelProperty(name = "isBulk", value = "是否散装物料")
	private String isBulk;

	@ApiModelProperty(name = "isBulkName", value = "是否散装物料")
	private String isBulkName;

	@ApiModelProperty(name = "materialCodeOld", value = "旧物料编号")
	private String materialCodeOld;

	@ApiModelProperty(name = "isKey", value = "是否21类关键件")
	private String isKey;

	@ApiModelProperty(name = "isKeyName", value = "是否21类关键件")
	private String isKeyName;

	@ApiModelProperty(name = "isRecoil", value = "是否反冲物料")
	private String isRecoil;

	@ApiModelProperty(name = "isRecoilName", value = "是否反冲物料")
	private String isRecoilName;

	@ApiModelProperty(name = "factoryMaterialStatus", value = "跨工厂物料状态 99-总冻结")
	private String factoryMaterialStatus;

	@ApiModelProperty(name = "materialGroup", value = "物料组（品类） 是采购划分的品类，")
	private String materialGroup;

	@ApiModelProperty(name = "externalMaterialGroup", value = "外部物料组  是中车的物料属性分组")
	private String externalMaterialGroup;

	private static final long serialVersionUID = 1L;

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

	public String getMaterialUnit() {
		return materialUnit;
	}

	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getIsBatch() {
		return isBatch;
	}

	public void setIsBatch(String isBatch) {
		this.isBatch = isBatch;
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

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public Long getStorageType() {
		return storageType;
	}

	public void setStorageType(Long storageType) {
		this.storageType = storageType;
	}

	public String getInspectMgrUid() {
		return inspectMgrUid;
	}

	public void setInspectMgrUid(String inspectMgrUid) {
		this.inspectMgrUid = inspectMgrUid;
	}

	public String getAssembleMgrUid() {
		return assembleMgrUid;
	}

	public void setAssembleMgrUid(String assembleMgrUid) {
		this.assembleMgrUid = assembleMgrUid;
	}

	public String getDeliveryCarrierType() {
		return deliveryCarrierType;
	}

	public void setDeliveryCarrierType(String deliveryCarrierType) {
		this.deliveryCarrierType = deliveryCarrierType;
	}

	public String getMaterialImgUrl() {
		return materialImgUrl;
	}

	public void setMaterialImgUrl(String materialImgUrl) {
		this.materialImgUrl = materialImgUrl;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(double netWeight) {
		this.netWeight = netWeight;
	}

	public double getBoundCount() {
		return boundCount;
	}

	public void setBoundCount(double boundCount) {
		this.boundCount = boundCount;
	}

	public String getIsBulk() {
		return isBulk;
	}

	public void setIsBulk(String isBulk) {
		this.isBulk = isBulk;
	}

	public String getMaterialCodeOld() {
		return materialCodeOld;
	}

	public void setMaterialCodeOld(String materialCodeOld) {
		this.materialCodeOld = materialCodeOld;
	}

	public String getIsKey() {
		return isKey;
	}

	public void setIsKey(String isKey) {
		this.isKey = isKey;
	}

	public String getIsRecoil() {
		return isRecoil;
	}

	public void setIsRecoil(String isRecoil) {
		this.isRecoil = isRecoil;
	}

	public String getFactoryMaterialStatus() {
		return factoryMaterialStatus;
	}

	public void setFactoryMaterialStatus(String factoryMaterialStatus) {
		this.factoryMaterialStatus = factoryMaterialStatus;
	}

	public String getMaterialGroup() {
		return materialGroup;
	}

	public void setMaterialGroup(String materialGroup) {
		this.materialGroup = materialGroup;
	}

	public String getExternalMaterialGroup() {
		return externalMaterialGroup;
	}

	public void setExternalMaterialGroup(String externalMaterialGroup) {
		this.externalMaterialGroup = externalMaterialGroup;
	}

	/**
	 * 获取 purchaseClass
	 * 
	 * @return 返回 purchaseClass
	 */
	public String getPurchaseClass() {
		return purchaseClass;
	}

	/**
	 * 设置 purchaseClass
	 * 
	 * @param purchaseClass
	 *            对purchaseClass进行赋值
	 */
	public void setPurchaseClass(String purchaseClass) {
		this.purchaseClass = purchaseClass;
	}

	/**
	 * 获取 materialTypeName
	 * 
	 * @return 返回 materialTypeName
	 */
	public String getMaterialTypeName() {
		return materialTypeName;
	}

	/**
	 * 获取 purchaseTypeName
	 * 
	 * @return 返回 purchaseTypeName
	 */
	public String getPurchaseTypeName() {
		return purchaseTypeName;
	}

	/**
	 * 设置 materialTypeName
	 * 
	 * @param materialTypeName
	 *            对materialTypeName进行赋值
	 */
	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}

	/**
	 * 设置 purchaseTypeName
	 * 
	 * @param purchaseTypeName
	 *            对purchaseTypeName进行赋值
	 */
	public void setPurchaseTypeName(String purchaseTypeName) {
		this.purchaseTypeName = purchaseTypeName;
	}

	/**
	 * 获取 isBatchName
	 * 
	 * @return 返回 isBatchName
	 */
	public String getIsBatchName() {
		return isBatchName;
	}

	/**
	 * 获取 purchaseClassName
	 * 
	 * @return 返回 purchaseClassName
	 */
	public String getPurchaseClassName() {
		return purchaseClassName;
	}

	/**
	 * 设置 isBatchName
	 * 
	 * @param isBatchName
	 *            对isBatchName进行赋值
	 */
	public void setIsBatchName(String isBatchName) {
		this.isBatchName = isBatchName;
	}

	/**
	 * 设置 purchaseClassName
	 * 
	 * @param purchaseClassName
	 *            对purchaseClassName进行赋值
	 */
	public void setPurchaseClassName(String purchaseClassName) {
		this.purchaseClassName = purchaseClassName;
	}

	/**
	 * 获取 statusName
	 * @return 返回 statusName
	 */
	public Long getStatusName() {
		return statusName;
	}

	/**
	 * 获取 isBulkName
	 * @return 返回 isBulkName
	 */
	public String getIsBulkName() {
		return isBulkName;
	}

	/**
	 * 获取 isKeyName
	 * @return 返回 isKeyName
	 */
	public String getIsKeyName() {
		return isKeyName;
	}

	/**
	 * 获取 isRecoilName
	 * @return 返回 isRecoilName
	 */
	public String getIsRecoilName() {
		return isRecoilName;
	}

	/**
	 * 设置 statusName
	 * @param statusName 对statusName进行赋值
	 */
	public void setStatusName(Long statusName) {
		this.statusName = statusName;
	}

	/**
	 * 设置 isBulkName
	 * @param isBulkName 对isBulkName进行赋值
	 */
	public void setIsBulkName(String isBulkName) {
		this.isBulkName = isBulkName;
	}

	/**
	 * 设置 isKeyName
	 * @param isKeyName 对isKeyName进行赋值
	 */
	public void setIsKeyName(String isKeyName) {
		this.isKeyName = isKeyName;
	}

	/**
	 * 设置 isRecoilName
	 * @param isRecoilName 对isRecoilName进行赋值
	 */
	public void setIsRecoilName(String isRecoilName) {
		this.isRecoilName = isRecoilName;
	}

}
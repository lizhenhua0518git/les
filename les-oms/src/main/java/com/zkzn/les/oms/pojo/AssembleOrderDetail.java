package com.zkzn.les.oms.pojo;

import com.zkzn.les.common.util.page.PageCondition;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.util.Date;


/**.
 *
 *
 * 功能描述：集配单详细实体类
 * @author wangzhou
 * 时间：2018年8月23日
 */
public class AssembleOrderDetail extends PageCondition {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String id;//主键id
	private String assembleId;//集配单id;
	private String materialCode;//SAP物料号
	private String materialDesc;//物料描述
	private String materialUnit;//单位
	private Double requiredCount;//需求数量
	private Double enableCount;//可配送数量
	private Double unsendCount;//未发数量
	private String originCarrierId;//原载具id
	private String carrierId;//现载具id
	private Integer putLevel;//摆放层级
	private String storageBinId;//储位id
	private String sapAdmin;//SAP管理工
	private String deliverMode;//配送方式
	private String deliverPlatform;//配送台位
	private Date planFillgoodTime;//计划补货完成日期
	private String planFillgoodTimeS;//计划补货完成日期
	private Date planDeliverTime;//计划配送完成时间
	private String planDeliverTimeS;//计划配送完成时间
	private Integer jobNode;//状态
	private Date assembleTime;//集配时间
	private String assembleUid;//集配人
	private Date lowerFrameTime;//下架时间
	private String lowerFrameUid;//下架人
	private Date checkTime;//复核时间
	private String 	checkUid;//复核人
	private String deliverRowCode;//出库行项目号
	private String factory;//工厂
	private String reserveCode;//预留号
	private String reserveRow;//预留行
	private String assembleArea;//集配区域
	private Integer isUrgent;//是否紧急
	private String voucherYear;//凭证年
	private String voucherCode;//凭证号
	private String voucherLineNum;//凭证行项目
	private String batchNum;//批次
	private String evaluationType;//评估类型
	private String pDetailId;//工序订单详情ID
	private String mStorageBinId;//仓位库存id
	private String voucherId;//凭证行ID
	private String supplierCode;//供应商编号
	private String supplierName;//供应商名称

	private Double pOrdercount;//总数（用于反写工序订单子表数据）
	private Double pOrderUnsendCount;//未发数量（用于反写工序订单子表数据）
	private Double pOrderFinishedCount;//已发数量（用于反写工序订单子表数据）

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAssembleId() {
		return assembleId;
	}

	public void setAssembleId(String assembleId) {
		this.assembleId = assembleId;
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

	public Double getpOrdercount() {
		return pOrdercount;
	}

	public void setpOrdercount(Double pOrdercount) {
		this.pOrdercount = pOrdercount;
	}

	public Double getpOrderUnsendCount() {
		return pOrderUnsendCount;
	}

	public void setpOrderUnsendCount(Double pOrderUnsendCount) {
		this.pOrderUnsendCount = pOrderUnsendCount;
	}

	public Double getpOrderFinishedCount() {
		return pOrderFinishedCount;
	}

	public void setpOrderFinishedCount(Double pOrderFinishedCount) {
		this.pOrderFinishedCount = pOrderFinishedCount;
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

	public Double getRequiredCount() {
		return requiredCount;
	}

	public void setRequiredCount(Double requiredCount) {
		this.requiredCount = requiredCount;
	}

	public Double getEnableCount() {
		return enableCount;
	}

	public void setEnableCount(Double enableCount) {
		this.enableCount = enableCount;
	}

	public String getOriginCarrierId() {
		return originCarrierId;
	}

	public void setOriginCarrierId(String originCarrierId) {
		this.originCarrierId = originCarrierId;
	}

	public String getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(String carrierId) {
		this.carrierId = carrierId;
	}

	public String getPlanFillgoodTimeS() {
		return planFillgoodTimeS;
	}

	public void setPlanFillgoodTimeS(String planFillgoodTimeS) {
		this.planFillgoodTimeS = planFillgoodTimeS;
	}

	public String getPlanDeliverTimeS() {
		return planDeliverTimeS;
	}

	public void setPlanDeliverTimeS(String planDeliverTimeS) {
		this.planDeliverTimeS = planDeliverTimeS;
	}

	public Integer getPutLevel() {
		return putLevel;
	}

	public void setPutLevel(Integer putLevel) {
		this.putLevel = putLevel;
	}

	public String getStorageBinId() {
		return storageBinId;
	}

	public void setStorageBinId(String storageBinId) {
		this.storageBinId = storageBinId;
	}

	public String getSapAdmin() {
		return sapAdmin;
	}

	public void setSapAdmin(String sapAdmin) {
		this.sapAdmin = sapAdmin;
	}

	public String getDeliverMode() {
		return deliverMode;
	}

	public void setDeliverMode(String deliverMode) {
		this.deliverMode = deliverMode;
	}

	public String getDeliverPlatform() {
		return deliverPlatform;
	}

	public void setDeliverPlatform(String deliverPlatform) {
		this.deliverPlatform = deliverPlatform;
	}

	public Date getPlanFillgoodTime() {
		return planFillgoodTime;
	}

	public void setPlanFillgoodTime(Date planFillgoodTime) {
		this.planFillgoodTime = planFillgoodTime;
	}

	public Date getPlanDeliverTime() {
		return planDeliverTime;
	}

	public void setPlanDeliverTime(Date planDeliverTime) {
		this.planDeliverTime = planDeliverTime;
	}

	public Integer getJobNode() {
		return jobNode;
	}

	public void setJobNode(Integer jobNode) {
		this.jobNode = jobNode;
	}

	public Date getAssembleTime() {
		return assembleTime;
	}

	public void setAssembleTime(Date assembleTime) {
		this.assembleTime = assembleTime;
	}

	public String getAssembleUid() {
		return assembleUid;
	}

	public void setAssembleUid(String assembleUid) {
		this.assembleUid = assembleUid;
	}

	public Date getLowerFrameTime() {
		return lowerFrameTime;
	}

	public void setLowerFrameTime(Date lowerFrameTime) {
		this.lowerFrameTime = lowerFrameTime;
	}

	public String getLowerFrameUid() {
		return lowerFrameUid;
	}

	public void setLowerFrameUid(String lowerFrameUid) {
		this.lowerFrameUid = lowerFrameUid;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getCheckUid() {
		return checkUid;
	}

	public void setCheckUid(String checkUid) {
		this.checkUid = checkUid;
	}

	public Double getUnsendCount() {
		return unsendCount;
	}

	public void setUnsendCount(Double unsendCount) {
		this.unsendCount = unsendCount;
	}

	public String getDeliverRowCode() {
		return deliverRowCode;
	}

	public void setDeliverRowCode(String deliverRowCode) {
		this.deliverRowCode = deliverRowCode;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getReserveCode() {
		return reserveCode;
	}

	public void setReserveCode(String reserveCode) {
		this.reserveCode = reserveCode;
	}

	public String getReserveRow() {
		return reserveRow;
	}

	public void setReserveRow(String reserveRow) {
		this.reserveRow = reserveRow;
	}

	public String getAssembleArea() {
		return assembleArea;
	}

	public void setAssembleArea(String assembleArea) {
		this.assembleArea = assembleArea;
	}

	public Integer getIsUrgent() {
		return isUrgent;
	}

	public void setIsUrgent(Integer isUrgent) {
		this.isUrgent = isUrgent;
	}

	public String getVoucherYear() {
		return voucherYear;
	}

	public void setVoucherYear(String voucherYear) {
		this.voucherYear = voucherYear;
	}

	public String getVoucherCode() {
		return voucherCode;
	}

	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}

	public String getVoucherLineNum() {
		return voucherLineNum;
	}

	public void setVoucherLineNum(String voucherLineNum) {
		this.voucherLineNum = voucherLineNum;
	}

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getEvaluationType() {
		return evaluationType;
	}

	public void setEvaluationType(String evaluationType) {
		this.evaluationType = evaluationType;
	}

	public String getpDetailId() {
		return pDetailId;
	}

	public void setpDetailId(String pDetailId) {
		this.pDetailId = pDetailId;
	}


	public String getmStorageBinId() {
		return mStorageBinId;
	}

	public void setmStorageBinId(String mStorageBinId) {
		this.mStorageBinId = mStorageBinId;
	}

	public String getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(String voucherId) {
		this.voucherId = voucherId;
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

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}



}

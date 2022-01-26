package com.zkzn.les.oms.pojo;

import com.zkzn.les.common.util.page.PageCondition;

import java.util.Date;


/**.
 *
 *
 * 功能描述：集配单实体类
 * @author wangzhou
 * 时间：2018年8月23日
 */
public class AssembleOrder extends PageCondition {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String id;//主键
	private String assembleOrderCode;//集配单据号
	private String waveId;//波次id
	private String processOrderId;//订单id
	private String storageLocation;//库存地点
	private Integer enableRowsCount;//可配送行
	private Integer status;//状态
	private String assembleArea;//集配区域
	private Integer activeNum;//激活次数
	private Date createDate;//创建日期
	private String createDateS;////创建日期
	private String voucherCode;//凭证号
	private String voucherYear;//凭证年
	private String voucherId;//凭证ID
	private String waveOldNum;//原始波次号
	private String waveOldId;//原始波次ID

	public String getId() {
		return id;
	}
	public String getAssembleOrderCode() {
		return assembleOrderCode;
	}
	public String getWaveId() {
		return waveId;
	}
	public String getProcessOrderId() {
		return processOrderId;
	}
	public String getStorageLocation() {
		return storageLocation;
	}
	public Integer getEnableRowsCount() {
		return enableRowsCount;
	}
	public Integer getStatus() {
		return status;
	}
	public String getAssembleArea() {
		return assembleArea;
	}
	public Integer getActiveNum() {
		return activeNum;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public String getVoucherCode() {
		return voucherCode;
	}
	public String getVoucherYear() {
		return voucherYear;
	}
	public String getVoucherId() {
		return voucherId;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setAssembleOrderCode(String assembleOrderCode) {
		this.assembleOrderCode = assembleOrderCode;
	}
	public void setWaveId(String waveId) {
		this.waveId = waveId;
	}
	public void setProcessOrderId(String processOrderId) {
		this.processOrderId = processOrderId;
	}
	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}
	public void setEnableRowsCount(Integer enableRowsCount) {
		this.enableRowsCount = enableRowsCount;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setAssembleArea(String assembleArea) {
		this.assembleArea = assembleArea;
	}
	public void setActiveNum(Integer activeNum) {
		this.activeNum = activeNum;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}
	public void setVoucherYear(String voucherYear) {
		this.voucherYear = voucherYear;
	}
	public void setVoucherId(String voucherId) {
		this.voucherId = voucherId;
	}
	public String getWaveOldNum() {
		return waveOldNum;
	}
	public void setWaveOldNum(String waveOldNum) {
		this.waveOldNum = waveOldNum;
	}
	public String getWaveOldId() {
		return waveOldId;
	}
	public void setWaveOldId(String waveOldId) {
		this.waveOldId = waveOldId;
	}

	public String getCreateDateS() {
		return createDateS;
	}
	public void setCreateDateS(String createDateS) {
		this.createDateS = createDateS;
	}
	@Override
	public String toString() {
		return "AssembleOrder [id=" + id + ", assembleOrderCode=" + assembleOrderCode + ", waveId=" + waveId
				+ ", processOrderId=" + processOrderId + ", storageLocation=" + storageLocation + ", enableRowsCount="
				+ enableRowsCount + ", status=" + status + ", assembleArea=" + assembleArea + ", activeNum=" + activeNum
				+ ", createDate=" + createDate + ", voucherCode=" + voucherCode + ", voucherYear=" + voucherYear
				+ ", voucherId=" + voucherId + ", waveOldNum=" + waveOldNum + ", waveOldId=" + waveOldId + "]";
	}



}

package com.zkzn.les.basicInfo.pojo;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import com.zkzn.les.basicInfo.util.PageCondition;

public class LaunchStrategyNew extends PageCondition{
	
	private static final long serialVersionUID = 1L;
	private String lid;//
	private Integer orderType;//单据类型
	private Integer materialStatus;//物料状态
	private String warehouseCode;//仓库编码
	private String area;//区域
	private String stationCode;//工位
	private Integer positionNumber;//上架仓位序号
	private String positionCode;//上架仓位区域
	private String createUserName;//创建人

	private String mid;//t_material_LAUNCH_REMARK的id
	private String materialCode;
	private String materialDesc;
	private String remarks;//备注
	

	


	public String getLid() {
		return lid;
	}





	public void setLid(String lid) {
		this.lid = lid;
	}





	public Integer getOrderType() {
		return orderType;
	}





	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}





	public Integer getMaterialStatus() {
		return materialStatus;
	}





	public void setMaterialStatus(Integer materialStatus) {
		this.materialStatus = materialStatus;
	}





	public String getWarehouseCode() {
		return warehouseCode;
	}





	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}





	public String getArea() {
		return area;
	}





	public void setArea(String area) {
		this.area = area;
	}





	public String getStationCode() {
		return stationCode;
	}





	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}





	public Integer getPositionNumber() {
		return positionNumber;
	}





	public void setPositionNumber(Integer positionNumber) {
		this.positionNumber = positionNumber;
	}





	public String getPositionCode() {
		return positionCode;
	}





	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}





	public String getCreateUserName() {
		return createUserName;
	}





	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}





	public String getMid() {
		return mid;
	}





	public void setMid(String mid) {
		this.mid = mid;
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





	public String getRemarks() {
		return remarks;
	}





	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}

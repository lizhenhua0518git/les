package com.zkzn.les.basicInfo.pojo;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import com.zkzn.les.basicInfo.util.PageCondition;

public class LaunchStrategy extends PageCondition{
	
	private static final long serialVersionUID = 1L;
	private String id;//
	private Integer orderType;//单据类型
	private Integer materialStatus;//物料状态
	private String warehouseCode;//仓库编码
	private String warehouseName;//仓库名称
	private String area;//区域
	private String stationCode;//工位
	private Integer positionNumber;//上架仓位序号
	private String positionCode;//上架仓位区域
	private String createUserName;//创建人
	private String createUserId;//创建人Id

    private String levelOneCode;//物料一级分类
	
	private String levelOneName;//物料一级分类名称
	
	private String levelTwoCode;// 物料二级分类
	
	private String levelTwoName;// 物料二级分类名称
	
	private String levelThreeCode;// 物料三级分类
	
	private String levelThreeName;// 物料三级分类名称
	

	public String getWarehouseName() {
		return warehouseName;
	}


	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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


	public String getLevelOneCode() {
		return levelOneCode;
	}


	public void setLevelOneCode(String levelOneCode) {
		this.levelOneCode = levelOneCode;
	}


	public String getLevelOneName() {
		return levelOneName;
	}


	public void setLevelOneName(String levelOneName) {
		this.levelOneName = levelOneName;
	}


	public String getLevelTwoCode() {
		return levelTwoCode;
	}


	public void setLevelTwoCode(String levelTwoCode) {
		this.levelTwoCode = levelTwoCode;
	}


	public String getLevelTwoName() {
		return levelTwoName;
	}


	public void setLevelTwoName(String levelTwoName) {
		this.levelTwoName = levelTwoName;
	}


	public String getLevelThreeCode() {
		return levelThreeCode;
	}


	public void setLevelThreeCode(String levelThreeCode) {
		this.levelThreeCode = levelThreeCode;
	}


	public String getLevelThreeName() {
		return levelThreeName;
	}


	public void setLevelThreeName(String levelThreeName) {
		this.levelThreeName = levelThreeName;
	}


	public String getCreateUserId() {
		return createUserId;
	}


	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}

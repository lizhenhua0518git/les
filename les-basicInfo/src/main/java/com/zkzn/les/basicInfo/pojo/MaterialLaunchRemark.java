package com.zkzn.les.basicInfo.pojo;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import com.zkzn.les.basicInfo.util.PageCondition;
 

public class MaterialLaunchRemark  extends PageCondition{
	
 
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1312893688570975805L;
	private String id;//
	private String materialCode;//物料编码
	private String materialDesc;//物料描述
	private String warehouseCode;//仓库
	private String warehouseName;//仓库名称
	private String area;//区域
	private String stationCode;//工位
	private String remarks;//备注
	private int isFixed;//是否固定 仓位
	
	private String levelOneCode;//物料一级分类
	
	private String levelOneName;//物料一级分类名称
	
	private String levelTwoCode;// 物料二级分类
	
	private String levelTwoName;// 物料二级分类名称
	
	private String levelThreeCode;// 物料三级分类
	
	private String levelThreeName;// 物料三级分类名称
	
	private String createUserId;// 创建人id 

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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getIsFixed() {
		return isFixed;
	}

	public void setIsFixed(int isFixed) {
		this.isFixed = isFixed;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getLevelOneCode() {
		return levelOneCode;
	}

	public void setLevelOneCode(String levelOneCode) {
		this.levelOneCode = levelOneCode;
	}

	public String getLevelTwoCode() {
		return levelTwoCode;
	}

	public void setLevelTwoCode(String levelTwoCode) {
		this.levelTwoCode = levelTwoCode;
	}

	public String getLevelThreeCode() {
		return levelThreeCode;
	}

	public void setLevelThreeCode(String levelThreeCode) {
		this.levelThreeCode = levelThreeCode;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getLevelOneName() {
		return levelOneName;
	}

	public void setLevelOneName(String levelOneName) {
		this.levelOneName = levelOneName;
	}

	public String getLevelTwoName() {
		return levelTwoName;
	}

	public void setLevelTwoName(String levelTwoName) {
		this.levelTwoName = levelTwoName;
	}

	public String getLevelThreeName() {
		return levelThreeName;
	}

	public void setLevelThreeName(String levelThreeName) {
		this.levelThreeName = levelThreeName;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}

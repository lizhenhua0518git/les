package com.zkzn.les.oms.pojo.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassTitle: DetailList
 * @ProjectName les
 * @Description: vo
 * @Author Sangsang
 * @Date 2021/1/24
 * @Time 17:44
 */
public class DetailList {
	@NotBlank(message = "详情id不可为空")
	private String id;
	@NotBlank(message = "materialCode不可为空")
	private String materialCode;
	@NotBlank(message = "materialDesc不可为空")
	private String materialDesc;
	@NotBlank(message = "materialUnit不可为空")
	private String materialUnit;
	@NotNull(message = "currentIssuedAmount不可为空")
	private Double currentIssuedAmount;
	@NotBlank(message = "storageLocation不可为空")
	private String storageLocation;
	@NotNull(message = "itemNo不可为空")
	private Integer itemNo;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}

	public String getMaterialDesc() {
		return materialDesc;
	}

	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}

	public String getMaterialUnit() {
		return materialUnit;
	}



	public String getStorageLocation() {
		return storageLocation;
	}

	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}

	public Double getCurrentIssuedAmount() {
		return currentIssuedAmount;
	}

	public void setCurrentIssuedAmount(Double currentIssuedAmount) {
		this.currentIssuedAmount = currentIssuedAmount;
	}


	public Integer getItemNo() {
		return itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}
}

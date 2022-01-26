package com.zkzn.les.oms.pojo.vo;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @ClassTitle: PurchaseBillVO
 * @ProjectName les
 * @Description: 采购单VO
 * url文档地址 : https://blog.csdn.net/weixin_42956047/article/details/103408491?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-7&spm=1001.2101.3001.4242
 * @Author Sangsang
 * @Date 2021/1/24
 * @Time 17:42
 */
public class PurchaseBillVO {
	@NotBlank(message = "主键不可为空")
	private String id;
	@NotBlank(message = "orderCode不可为空")
	private String orderCode;
	@NotBlank(message = "supplierName不可为空")
	private String supplierName;
	@NotBlank(message = "supplierCode不可为空")
	private String supplierCode;
	@NotBlank(message = "warehouseCode不可为空")
	private String warehouseCode;
	@NotBlank(message = "warehouseName不可为空")
	private String warehouseName;
	@NotBlank(message = "storageLocation不可为空")
	private String storageLocation;
	@NotEmpty(message = "detailList不可为空")
	@Valid
	private List<DetailList> detailList;
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getOrderCode() {
		return orderCode;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getSupplierCode() {
		return supplierCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getWarehouseName() {
		return warehouseName;
	}

	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}
	public String getStorageLocation() {
		return storageLocation;
	}

	public void setDetailList(List<DetailList> detailList) {
		this.detailList = detailList;
	}
	public List<DetailList> getDetailList() {
		return detailList;
	}
}

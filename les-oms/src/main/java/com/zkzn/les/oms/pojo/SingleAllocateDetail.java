package com.zkzn.les.oms.pojo;

import com.zkzn.les.common.util.page.PageCondition;
import io.swagger.annotations.ApiModelProperty;
import net.logstash.logback.encoder.org.apache.commons.lang.builder.ReflectionToStringBuilder;


/**.
 *
 * @author wangzhou
 * @date 2020年8月10日
 * @Description:调拨订单物料明细实体
 */
public class SingleAllocateDetail extends PageCondition {

	/**
	 *
	 */
	private static final long serialVersionUID = -893619203712978000L;

	@ApiModelProperty(name="id", value="主键id")
	private String id;
	@ApiModelProperty(name="sapAllocationOrder", value="SAP调拨单号")
	private String sapAllocationOrder;
	@ApiModelProperty(name="parentId", value="父表id")
	private String parentId;
	@ApiModelProperty(name="itemNo", value="sap系统行项目号")
	private String itemNo;
	@ApiModelProperty(name="materialCode", value="物料编码")
	private String materialCode;
	@ApiModelProperty(name="materialName", value="物料名称")
	private String materialName;
	@ApiModelProperty(name="factory", value="工厂")
	private String factory;
	@ApiModelProperty(name="outStorageLocation", value="调出库存地点编码")
	private String outStorageLocation;
	@ApiModelProperty(name="inStorageLocation", value="调入库存地点编码")
	private String inStorageLocation;
	@ApiModelProperty(name="materialNum", value="物料数量")
	private Double materialNum;
	@ApiModelProperty(name="supplierCode", value="供货商编码")
	private String supplierCode;
	@ApiModelProperty(name="assessmentType", value="评估类型")
	private String assessmentType;
	@ApiModelProperty(name="batchNo", value="批次")
	private String batchNo;
	@ApiModelProperty(name="reasonMovement", value="移动原因")
	private String reasonMovement;
	@ApiModelProperty(name="sendNum", value="发货数量")
	private Double sendNum;
	@ApiModelProperty(name="receivedNum", value="收货数量")
	private Double receivedNum;
	@ApiModelProperty(name="status", value="0-已创建、5-已审核、10-已发货、15-已收货、99-已取消")
	private Integer status;
	@ApiModelProperty(name="stockStatus", value="库存状态(0-非限制，1-不合格)")
	private Integer stockStatus;
	@ApiModelProperty(name="stockType", value="库存类型(0-自有，1-寄售，2-订单，3-客户)")
	private Integer stockType;
	@ApiModelProperty(name="supplierName", value="供应商名称")
	private String supplierName;
	@ApiModelProperty(name="sellOrder", value="销售订单号")
	private String sellOrder;
	@ApiModelProperty(name="sellItem", value="销售订单行项目号")
	private String sellItem;
	@ApiModelProperty(name="customer", value="客户编码")
	private String customer;
	@ApiModelProperty(name="customerName", value="客户名称")
	private String customerName;
	@ApiModelProperty(name="lesItemNo", value="行项目号，les系统生成")
	private Integer lesItemNo;
	@ApiModelProperty(name="materialUnit", value="物料单位")
	private String materialUnit;




	public String getMaterialUnit() {
		return materialUnit;
	}


	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getSapAllocationOrder() {
		return sapAllocationOrder;
	}


	public void setSapAllocationOrder(String sapAllocationOrder) {
		this.sapAllocationOrder = sapAllocationOrder;
	}


	public String getParentId() {
		return parentId;
	}


	public void setParentId(String parentId) {
		this.parentId = parentId;
	}


	public String getItemNo() {
		return itemNo;
	}


	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}


	public String getMaterialCode() {
		return materialCode;
	}


	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}


	public String getFactory() {
		return factory;
	}


	public void setFactory(String factory) {
		this.factory = factory;
	}


	public String getOutStorageLocation() {
		return outStorageLocation;
	}


	public void setOutStorageLocation(String outStorageLocation) {
		this.outStorageLocation = outStorageLocation;
	}


	public String getInStorageLocation() {
		return inStorageLocation;
	}


	public void setInStorageLocation(String inStorageLocation) {
		this.inStorageLocation = inStorageLocation;
	}


	public Double getMaterialNum() {
		return materialNum;
	}


	public void setMaterialNum(Double materialNum) {
		this.materialNum = materialNum;
	}


	public String getSupplierCode() {
		return supplierCode;
	}


	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}


	public String getAssessmentType() {
		return assessmentType;
	}


	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}


	public String getBatchNo() {
		return batchNo;
	}


	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}


	public String getReasonMovement() {
		return reasonMovement;
	}


	public void setReasonMovement(String reasonMovement) {
		this.reasonMovement = reasonMovement;
	}


	public Double getSendNum() {
		return sendNum;
	}


	public void setSendNum(Double sendNum) {
		this.sendNum = sendNum;
	}


	public Double getReceivedNum() {
		return receivedNum;
	}


	public void setReceivedNum(Double receivedNum) {
		this.receivedNum = receivedNum;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Integer getStockStatus() {
		return stockStatus;
	}


	public void setStockStatus(Integer stockStatus) {
		this.stockStatus = stockStatus;
	}


	public Integer getStockType() {
		return stockType;
	}


	public void setStockType(Integer stockType) {
		this.stockType = stockType;
	}


	public String getSupplierName() {
		return supplierName;
	}


	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}


	public String getSellOrder() {
		return sellOrder;
	}


	public void setSellOrder(String sellOrder) {
		this.sellOrder = sellOrder;
	}


	public String getSellItem() {
		return sellItem;
	}


	public void setSellItem(String sellItem) {
		this.sellItem = sellItem;
	}


	public String getCustomer() {
		return customer;
	}


	public void setCustomer(String customer) {
		this.customer = customer;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public Integer getLesItemNo() {
		return lesItemNo;
	}


	public void setLesItemNo(Integer lesItemNo) {
		this.lesItemNo = lesItemNo;
	}




	public String getMaterialName() {
		return materialName;
	}


	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ReflectionToStringBuilder.toString(this);
	}
}

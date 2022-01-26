package com.zkzn.les.wms.pojo.upperFrameTask;

import java.util.Date;

/**
 * @ProjectName: lesGroup
 * @Package: com.zkzn.les.wms.app.pojo
 * @ClassName: ReceiveDetailRecord
 * @Author: 胡志明
 * @Description: 点收详情实体
 * @Date: 2020/9/4 10:09
 */
public class ReceiveDetailRecord {

    private String detailID;//点收详情主键id
    private String materialCode;//物料号
    private String materialDesc;//物料名称
    private String materialUnit;//物料单位
    private Double receiveNum;//物料收货数量
    private String batchNo;//批次号
    private String serialNum;//是否序列号管理  0、否  1、是
    private String storageLocation;//库存地点
    private String factory;//工厂
    private Integer status;//15待点收、16点收异常 30 待质检、 31-等待审核  32-质检完成  35-已生成上架任务
    private Integer inspectionStatus;//0-合格、1-不合格
    private String pointerName;//点收人名
    private String pointerId;//点收人id
    private Date pointTime;//点收时间
    private String inspectionerName;//质检人名
    private String inspectionerId;//质检人id
    private Date inspectionTime;//质检时间
    private Date createTime;//创建时间
    private String sourceId;//来源单id
    private String inspectionCode;//检验批号
    private String pointPositionId;//点收库位id
    private String pointPositionCode;//点收库位号
    private String inspectPositionId;//质检库位id
    private String inspectPositionCode;//质检库位号
    private String carrierCode;//载具号
    private String carrierId;//载具id
    private String carrierType;//载具类型
    private String receivedTaskCode;//任务号
    private Integer itemNo;//行项目号
    private Double qualifiedNum;//合格品数
    private Double unqualifiedNum;//不合格品数
    private String receiveId;//收货表id
    private Integer orderItemNo;//订单行项目号
    private Integer printStatus;//打印状态
    private Double extractNum;//抽检数量
    private String remarks;//备注

    private Double qualifiedExtractNum;//抽检合格数量
    private Double unqualifiedExtractNum;//抽检不合格数量

    private String billCode;//送货单号/质检单号
    private String materialSerialNum;//序列号
    private String stationCode;//工位
    private Integer storageType;//0-地堆 1-货架
    private Double boundCount;//最大仓储容量
    private String supplierCode;//供应商编号
    private String supplierName;//供应商名称
    private String positionCode;//仓位号
    private String warehouse;//仓库编号
    private String warehouseName;//仓库名称
    private String orderCode;//订单号
    private String failMaterTaskcode;//订单号
    private String writeOffUser;//冲销操作人
    private Date writeOffTime;//冲销操作时间
    private String writeOffReason;//冲销理由
    private Integer deleteStatus;//冲销状态
    
    private String purchaseCode;//采购订单编码
    private String purchaseItem;//采购订单行项目号
    private String subjectType;//科目分配类别 M-专用料   T-通用料
    private String salesOrderCode;//销售订单号
    private String salesOrderItem;//销售订单行
    private String sapLfbnr;//参考凭证
    private String sapLfpos;//参考凭证行
    private Integer sapItemNo;//调sap接口行项目号

    private String reserveCode;//预留号
    private String reserveRow;//预留行项目
    
    private String specialType;//订单类型
    
    private String amwId;//是否有补料过 有值 补料过 空值 无补料过
    
    /**
	 * 获取 specialType
	 * @return 返回 specialType
	 */
	public String getSpecialType() {
		return specialType;
	}

	/**
	 * 设置 specialType
	 * @param specialType 对specialType进行赋值
	 */
	public void setSpecialType(String specialType) {
		this.specialType = specialType;
	}

	private String sellOrder;//销售订单
    private String sellOrderItem;//销售订单项目

    public String getSalesOrderCode() {
        return salesOrderCode;
    }

    public void setSalesOrderCode(String salesOrderCode) {
        this.salesOrderCode = salesOrderCode;
    }

    public String getSalesOrderItem() {
        return salesOrderItem;
    }

    public void setSalesOrderItem(String salesOrderItem) {
        this.salesOrderItem = salesOrderItem;
    }

    public String getSapLfbnr() {
        return sapLfbnr;
    }

    public void setSapLfbnr(String sapLfbnr) {
        this.sapLfbnr = sapLfbnr;
    }

    public String getSapLfpos() {
        return sapLfpos;
    }

    public void setSapLfpos(String sapLfpos) {
        this.sapLfpos = sapLfpos;
    }

    public String getDetailID() {
        return detailID;
    }

    public void setDetailID(String detailID) {
        this.detailID = detailID;
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

    public Double getReceiveNum() {
        return receiveNum;
    }

    public void setReceiveNum(Double receiveNum) {
        this.receiveNum = receiveNum;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getInspectionStatus() {
        return inspectionStatus;
    }

    public void setInspectionStatus(Integer inspectionStatus) {
        this.inspectionStatus = inspectionStatus;
    }

    public String getPointerName() {
        return pointerName;
    }

    public void setPointerName(String pointerName) {
        this.pointerName = pointerName;
    }

    public String getPointerId() {
        return pointerId;
    }

    public void setPointerId(String pointerId) {
        this.pointerId = pointerId;
    }

    public Date getPointTime() {
        return pointTime;
    }

    public void setPointTime(Date pointTime) {
        this.pointTime = pointTime;
    }

    public String getInspectionerName() {
        return inspectionerName;
    }

    public void setInspectionerName(String inspectionerName) {
        this.inspectionerName = inspectionerName;
    }

    public String getInspectionerId() {
        return inspectionerId;
    }

    public void setInspectionerId(String inspectionerId) {
        this.inspectionerId = inspectionerId;
    }

    public Date getInspectionTime() {
        return inspectionTime;
    }

    public void setInspectionTime(Date inspectionTime) {
        this.inspectionTime = inspectionTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getInspectionCode() {
        return inspectionCode;
    }

    public void setInspectionCode(String inspectionCode) {
        this.inspectionCode = inspectionCode;
    }

    public String getPointPositionId() {
        return pointPositionId;
    }

    public void setPointPositionId(String pointPositionId) {
        this.pointPositionId = pointPositionId;
    }

    public String getPointPositionCode() {
        return pointPositionCode;
    }

    public void setPointPositionCode(String pointPositionCode) {
        this.pointPositionCode = pointPositionCode;
    }

    public String getInspectPositionId() {
        return inspectPositionId;
    }

    public void setInspectPositionId(String inspectPositionId) {
        this.inspectPositionId = inspectPositionId;
    }

    public String getInspectPositionCode() {
        return inspectPositionCode;
    }

    public void setInspectPositionCode(String inspectPositionCode) {
        this.inspectPositionCode = inspectPositionCode;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    public String getCarrierType() {
        return carrierType;
    }

    public void setCarrierType(String carrierType) {
        this.carrierType = carrierType;
    }

    public String getReceivedTaskCode() {
        return receivedTaskCode;
    }

    public void setReceivedTaskCode(String receivedTaskCode) {
        this.receivedTaskCode = receivedTaskCode;
    }

    public Integer getItemNo() {
        return itemNo;
    }

    public void setItemNo(Integer itemNo) {
        this.itemNo = itemNo;
    }

    public Double getQualifiedNum() {
        return qualifiedNum;
    }

    public void setQualifiedNum(Double qualifiedNum) {
        this.qualifiedNum = qualifiedNum;
    }

    public Double getUnqualifiedNum() {
        return unqualifiedNum;
    }

    public void setUnqualifiedNum(Double unqualifiedNum) {
        this.unqualifiedNum = unqualifiedNum;
    }

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
    }

    public Integer getOrderItemNo() {
        return orderItemNo;
    }

    public void setOrderItemNo(Integer orderItemNo) {
        this.orderItemNo = orderItemNo;
    }

    public Integer getPrintStatus() {
        return printStatus;
    }

    public void setPrintStatus(Integer printStatus) {
        this.printStatus = printStatus;
    }

    public Double getExtractNum() {
        return extractNum;
    }

    public void setExtractNum(Double extractNum) {
        this.extractNum = extractNum;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getMaterialSerialNum() {
        return materialSerialNum;
    }

    public void setMaterialSerialNum(String materialSerialNum) {
        this.materialSerialNum = materialSerialNum;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public Integer getStorageType() {
        return storageType;
    }

    public void setStorageType(Integer storageType) {
        this.storageType = storageType;
    }

    public Double getBoundCount() {
        return boundCount;
    }

    public void setBoundCount(Double boundCount) {
        this.boundCount = boundCount;
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

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Integer getSapItemNo() {
        return sapItemNo;
    }

    public void setSapItemNo(Integer sapItemNo) {
        this.sapItemNo = sapItemNo;
    }

    public String getFailMaterTaskcode() {
        return failMaterTaskcode;
    }

    public ReceiveDetailRecord setFailMaterTaskcode(String failMaterTaskcode) {
        this.failMaterTaskcode = failMaterTaskcode;
        return this;
    }

    public String getWriteOffUser() {
        return writeOffUser;
    }

    public void setWriteOffUser(String writeOffUser) {
        this.writeOffUser = writeOffUser;
    }

    public Double getQualifiedExtractNum() {
        return qualifiedExtractNum;
    }

    public void setQualifiedExtractNum(Double qualifiedExtractNum) {
        this.qualifiedExtractNum = qualifiedExtractNum;
    }

    public Double getUnqualifiedExtractNum() {
        return unqualifiedExtractNum;
    }

    public void setUnqualifiedExtractNum(Double unqualifiedExtractNum) {
        this.unqualifiedExtractNum = unqualifiedExtractNum;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public Date getWriteOffTime() {
        return writeOffTime;
    }

    public void setWriteOffTime(Date writeOffTime) {
        this.writeOffTime = writeOffTime;
    }

    public String getWriteOffReason() {
        return writeOffReason;
    }

    public void setWriteOffReason(String writeOffReason) {
        this.writeOffReason = writeOffReason;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

	/**
	 * 获取 purchaseCode
	 * @return 返回 purchaseCode
	 */
	public String getPurchaseCode() {
		return purchaseCode;
	}

	/**
	 * 获取 purchaseItem
	 * @return 返回 purchaseItem
	 */
	public String getPurchaseItem() {
		return purchaseItem;
	}

	/**
	 * 设置 purchaseCode
	 * @param purchaseCode 对purchaseCode进行赋值
	 */
	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}

	/**
	 * 设置 purchaseItem
	 * @param purchaseItem 对purchaseItem进行赋值
	 */
	public void setPurchaseItem(String purchaseItem) {
		this.purchaseItem = purchaseItem;
	}

	/**
	 * 获取 reserveCode
	 * @return 返回 reserveCode
	 */
	public String getReserveCode() {
		return reserveCode;
	}

	/**
	 * 获取 reserveRow
	 * @return 返回 reserveRow
	 */
	public String getReserveRow() {
		return reserveRow;
	}

	/**
	 * 设置 reserveCode
	 * @param reserveCode 对reserveCode进行赋值
	 */
	public void setReserveCode(String reserveCode) {
		this.reserveCode = reserveCode;
	}

	/**
	 * 设置 reserveRow
	 * @param reserveRow 对reserveRow进行赋值
	 */
	public void setReserveRow(String reserveRow) {
		this.reserveRow = reserveRow;
	}

	/**
	 * 获取 sellOrder
	 * @return 返回 sellOrder
	 */
	public String getSellOrder() {
		return sellOrder;
	}

	/**
	 * 获取 sellOrderItem
	 * @return 返回 sellOrderItem
	 */
	public String getSellOrderItem() {
		return sellOrderItem;
	}

	/**
	 * 设置 sellOrder
	 * @param sellOrder 对sellOrder进行赋值
	 */
	public void setSellOrder(String sellOrder) {
		this.sellOrder = sellOrder;
	}

	/**
	 * 设置 sellOrderItem
	 * @param sellOrderItem 对sellOrderItem进行赋值
	 */
	public void setSellOrderItem(String sellOrderItem) {
		this.sellOrderItem = sellOrderItem;
	}

	/**
	 * 获取 amwId
	 * @return 返回 amwId
	 */
	public String getAmwId() {
		return amwId;
	}

	/**
	 * 设置 amwId
	 * @param amwId 对amwId进行赋值
	 */
	public void setAmwId(String amwId) {
		this.amwId = amwId;
	}

}

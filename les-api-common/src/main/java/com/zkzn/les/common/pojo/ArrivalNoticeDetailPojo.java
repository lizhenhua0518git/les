package com.zkzn.les.common.pojo;

import com.zkzn.les.common.util.PageCondition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 送货单详情
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ArrivalNoticeDetailPojo extends PageCondition {

	private static final long serialVersionUID = 1L;
	private String id;//送货单详情ID
	private Integer arrivalItemNo;//行项目号
	private String materialCode;//物料号
	private String materialDesc;//物料名称
	private Double arrivalCount;//到货数量
	private String batchNo;//批次号
	private String stationCode;//工位
	private String factory;//工厂
	private String storageLocation;//库存地点
	private Date createTime;//创建日期
	private String arrivalId;//到货通知单主表id
	private String supplierCode;//供应商编码
	private String supplierName;//供应商名称
	private String materilUnit;//单位
	private Double receiveNum;//收货数量
	private Double upperNum;//上架数量
	private Integer receivedStatus;//收货状态(0-待收货 1-部分收货、2-收货完成)
 
	private String customer;//客户编码
	private String customerName;//客户名称
 
	private Integer orderItem;//订单行号
	private String purchaseCode;//采购订单号
	private Integer purchaseItem;//采购订单行号
	private Integer inspectionStatus;//质检状态 0-无需质检、1-待质检、2-部分质检、3-质检完成
	private Integer upperStatus;//上架状态(0-待上架、1-部分上架、2-上架完成)
	private Integer isDiffer;//是否差异(0-否，1-是)
	private String abnormalReason;//异常原因
	private Integer abnormalNum;//异常数量
	private String abnormalType;//异常类型

	private String receivedName;//点收状态名称
	private String inspectionName;//质检状态名称
	private String upperName;//上架状态名称

	private String warehouseCode;//仓库编码
	private String warehouseName;//仓库名称
	private String arrivalCode;//到货通知单号
 
	private String billType;//单据类型编码
	private String billName;//单据类型名称
	private String modelCode;//工厂编号
	private String modelName;//工厂名称

	private Date expectedArrivalDate;//预计到货日期
	private Date actualArrivalTime;//实际到货时间
	private String createStr;//创建时间查询条件
	private String startCreate;//开始创建时间
	private String endCreate;//结束创建时间
	private String expectedArrivalStr;//预计到货日期查询条件
	private String startExpectedArrival;//开始预计到货日期
	private String endExpectedArrival;//结束预计到货日期
	private String actualArrivalStr;//实际到货日期查询条件
	private String startActualArrival;//开始实际到货日期
	private String endActualArrival;//结束实际到货日期

	private String produceBatchNo;//生产批次号
	private String produceTime;//生产时间
	
	@ApiModelProperty(name="orderCode", value="订单号")
	private String orderCode;
	@ApiModelProperty(name="shipperCode", value="采购送货单对应的是供应商编码，调拨送货单对应的是仓库编码 ")
	private String shipperCode;
	@ApiModelProperty(name="shipperName", value="采购送货单对应的是供应商名称，调拨送货单对应的是仓库名称")
    private String shipperName;
	
	@ApiModelProperty(name="sellOrder", value="销售单号 ")
	private String sellOrder;
	@ApiModelProperty(name="sellOrderItem", value="销售单行")
    private Integer sellOrderItem;

	private String inspectionStatusStr;//质检状态 0-无需质检、1-待质检、2-部分质检、3-质检完成

	 

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArrivalNoticeDetailPojo other = (ArrivalNoticeDetailPojo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
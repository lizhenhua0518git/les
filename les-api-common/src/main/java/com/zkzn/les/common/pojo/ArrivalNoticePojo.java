package com.zkzn.les.common.pojo;

import com.zkzn.les.common.util.PageCondition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ArrivalNoticePojo extends PageCondition {
	private static final long serialVersionUID = 1L;
	
	private String id;//到货通知主表ID
    private String arrivalCode;//到货通知单号
    private String orderCode;//订单号
    private String warehouseCode;//仓库编码
    private String warehouseName;//仓库名称
    private String billType;//单据类型编码
    private String billName;//单据类型名称
	@ApiModelProperty(name="shipperCode", value="采购送货单对应的是供应商编码，调拨送货单对应的是仓库编码 ")
	private String shipperCode;
	@ApiModelProperty(name="shipperName", value="采购送货单对应的是供应商名称，调拨送货单对应的是仓库名称")
    private String shipperName;
    private Date sendDate;//发货日期
    private Date expectedArrivalDate;//预计到货日期
    private Date actualArrivalTime;//实际到货时间
    private String queueName;//排号状态名称
    private Integer queueStatus;//排号状态
    private String uploadName;//卸货状态名称
    private Integer uploadStatus;//卸货状态
    private String receivedName;//点收状态名称
    private Integer receivedStatus;//点收状态
    private String upperName;//上架状态名称
    private Integer upperStatus;//上架状态
    private String modelCode;//工厂编号
    private String modelName;//工厂名称
    private String sapStorageLocation;//工厂库存地点
    private String storageLocation;//仓库库存地点
    private String supplierContacts;//供应商联系人
    private String supplierContacttinfo;//供应商电话
    private String sendAddress;//发货点
    private String logisticsCompany;//物流公司
    private String logisticsCode;//物流单号
    private String carCdoe;//车牌
    private String createStr;//创建时间查询条件
    private String startCreate;//开始创建时间
    private String endCreate;//结束创建时间
    private String expectedArrivalStr;//预计到货日期查询条件
    private String startExpectedArrival;//开始预计到货日期
    private String endExpectedArrival;//结束预计到货日期
    private String actualArrivalStr;//实际到货日期查询条件
    private String startActualArrival;//开始实际到货日期
    private String endActualArrival;//结束实际到货日期
	@ApiModelProperty(name="factory", value="工厂")
    private String factory;
	@ApiModelProperty(name="purchaseCode", value="采购单号")
    private String purchaseCode;
	
     
}

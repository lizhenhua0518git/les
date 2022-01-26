package com.zkzn.les.wms.pojo.arrivalNotice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ArrivalNotice
 * @Description 到货通知单主表
 * @Author zhanglei
 * Date 2021/6/11 15:59
 * @Version 1.0
 **/
@ApiModel(value = "到货通知单主表")
@Data
public class ArrivalNotice implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "到货通知主表ID")
    private String id;
    @ApiModelProperty(value = "到货通知单号")
    private String arrivalCode;
    @ApiModelProperty(value = "订单号")
    private String orderCode;
    @ApiModelProperty(name="shipperCode", value="采购送货单对应的是供应商编码，调拨送货单对应的是仓库编码 ")
    private String shipperCode;
    @ApiModelProperty(name="shipperName", value="采购送货单对应的是供应商名称，调拨送货单对应的是仓库名称")
    private String shipperName;
    @ApiModelProperty(value = "发货日期")
    private Date createTime;
    @ApiModelProperty(value = "预计到货日期")
    private Date expectedArrivalDate;
    @ApiModelProperty(value = "仓库编码")
    private String warehouseCode;
    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;
    @ApiModelProperty(value = "仓库库存地点")
    private String storageLocation;
    @ApiModelProperty(value = "单据类型编码")
    private String billType;
    @ApiModelProperty(value = "单据类型名称")
    private String billName;
    @ApiModelProperty(value = "发货日期")
    private Date sendDate;
    @ApiModelProperty(value = "实际到货时间")
    private Date actualArrivalTime;
    @ApiModelProperty(value = "排号状态")
    private Integer queueStatus;
    @ApiModelProperty(name="factory", value="工厂")
    private String factory;
    @ApiModelProperty(value = "供应商联系人")
    private String supplierContacts;
    @ApiModelProperty(value = "供应商电话")
    private String supplierContacttinfo;
    @ApiModelProperty(value = "发货点")
    private String sendAddress;
    @ApiModelProperty(value = "物流公司")
    private String logisticsCompany;
    @ApiModelProperty(value = "物流单号")
    private String logisticsCode;
    @ApiModelProperty(value = "车牌")
    private String carCode;
    @ApiModelProperty(value = "卸货状态")
    private Integer uploadStatus;
    @ApiModelProperty(value = "点收状态")
    private Integer receivedStatus;
    @ApiModelProperty(value = "上架状态")
    private Integer upperStatus;
    @ApiModelProperty(value = "采购订单号")
    private String purchaseCode;
    @ApiModelProperty(value = "供货商批次号")
    private String supplierBatchNo;
    @ApiModelProperty(value = "供货商生产时间")
    private Date supplierProductDate;






}
package com.zkzn.les.wms.pojo.arrivalNotice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ArrivalNoticeDetial
 * @Description TODO
 * @Author zhanglei
 * Date 2021/6/11 16:17
 * @Version 1.0
 **/
@ApiModel(value = "到货通知单详情表")
@Data
public class ArrivalNoticeDetails implements Serializable {
    private static final long serialVersionUID = -1L;
@ApiModelProperty(value = "送货单详情ID")
    private String id;
    @ApiModelProperty(value = "行项目号")
    private Integer arrivalItemNo;
    @ApiModelProperty(value = "物料号")
    private String materialCode;
    @ApiModelProperty(value = "物料名称")
    private String materialDesc;
    @ApiModelProperty(value = "到货数量")
    private Double arrivalCount;
    @ApiModelProperty(value = "批次号")
    private String batchNo;
    @ApiModelProperty(value = "工位")
    private String stationCode;
    @ApiModelProperty(value = "工厂")
    private String factory;
    @ApiModelProperty(value = "库存地点")
    private String storageLocation;
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
    @ApiModelProperty(value = "到货通知单主表id")
    private String arrivalId;
    @ApiModelProperty(value = "供应商编码")
    private String supplierCode;
    @ApiModelProperty(value = "供应商名称")
    private String supplierName;
    @ApiModelProperty(value = "单位")
    private String materilUnit;
    @ApiModelProperty(value = "收货数量")
    private Double receiveNum;
    @ApiModelProperty(value = "上架数量")
    private Double upperNum;
    @ApiModelProperty(value = "收货状态(0-待收货 1-部分收货、2-收货完成)")
    private Integer receivedStatus;
    @ApiModelProperty(value = "客户编码")
    private String customer;
    @ApiModelProperty(value = "客户名称")
    private String customerName;
    @ApiModelProperty(value = "订单行号")
    private Integer orderItem;
    @ApiModelProperty(value = "采购订单号")
    private String purchaseCode;
    @ApiModelProperty(value = "采购订单行号")
    private Integer purchaseItem;
    @ApiModelProperty(value = "质检状态 0-无需质检、1-待质检、2-部分质检、3-质检完成")
    private Integer inspectionStatus;
    @ApiModelProperty(value = "上架状态(0-待上架、1-部分上架、2-上架完成)")
    private Integer upperStatus;
    @ApiModelProperty(value = "是否差异(0-否，1-是)")
    private Integer isDiffer;
    @ApiModelProperty(value = "异常原因")
    private String abnormalReason;
    @ApiModelProperty(value = "异常数量")
    private Integer abnormalNum;
    @ApiModelProperty(value = "异常类型")
    private String abnormalType;
    @ApiModelProperty(value = "生产批次号")
    private String produceBatchNo;
    @ApiModelProperty(value = "生产时间")
    private String produceTime;

}
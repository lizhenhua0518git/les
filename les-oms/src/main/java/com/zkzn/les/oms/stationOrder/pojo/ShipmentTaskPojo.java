package com.zkzn.les.oms.stationOrder.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString(callSuper = true)
public class ShipmentTaskPojo {

    private Integer shipmentTaskId;//出库任务id
    private Integer orderTaskId;//订单任务id
    private String warehouseCode;//仓库编号
    private String clientName;//客户名称
    private String orderCode;//订单编号
    private Integer orderType;//订单类型 1、正常出库；2、不合格品出库；3、调拨出库
    private String orderTypeName;//订单类型名称
    private Integer taskStatus;//出库任务状态1、待下架；2、待交接；3、交接完成
    private Integer operateUserId;//操作人id
    private String operateUserName;//操作人名称
    private String connectName;//交接人名称
    private Date connectTime;//交接时间
    private Integer createrUserId;//创建用户id
    private Date createrTime;//创建时间

}

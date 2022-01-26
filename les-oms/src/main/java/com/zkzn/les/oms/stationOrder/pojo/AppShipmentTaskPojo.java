package com.zkzn.les.oms.stationOrder.pojo;

import lombok.Data;
import lombok.ToString;

/**
 * APP下架列表实体
 */
@Data
@ToString(callSuper = true)
public class AppShipmentTaskPojo {

    //返回字段
    private Integer shipmentTaskId;//出库任务id
    private String clientName;//客户名称
    private String orderCode;//订单编号
    private Integer orderType;//订单类型 1、正常出库；2、不合格品出库；3、调拨出库
    private String orderTypeName;//订单类型名称
    private Integer materialNumber;//货物种类数量

    //查询条件
    private String warehouseCode;//仓库编号
    private Integer taskStatus;//出库任务状态1、待下架；2、待交接；3、交接完成
    private Integer operateUserId;//操作人id
}

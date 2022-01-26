package com.zkzn.les.oms.stationOrder.pojo;

import lombok.Data;
import lombok.ToString;

/**
 * 匹配库存实体
 */
@Data
@ToString(callSuper = true)
public class ShipmentTaskData {

    private Integer orderTaskId;//出库任务主表id
    private Integer orderTaskDetailId;//出库任务详情表id
    private Integer shipmentTaskId;//出库任务id
    private String warehouseCode;//仓库编码
    private String clientName;//客户名称
    private String orderCode;//出库任务号
    private String orderType;//出库订单类型
    private Integer operateUserId;//操作人
    private String operateUserName;//操作人名称
    private String materialDesc;//货物名称
    private String materialUnit;//单位
    private Double sendNumber;//出库数量
    private String batchNo;//批次号

    private Integer storageBinId;//仓位库存表id
    private Integer storagePositionId;//仓位表id
    private String positionCode;//仓位编码
    private Double stockCount;//库存数量

    private Integer createrUserId;//创建用户id
    private String positionCarCode;//仓位载具

}

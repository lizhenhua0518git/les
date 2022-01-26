package com.zkzn.les.oms.stationOrder.pojo;

import lombok.Data;
import lombok.ToString;

/**
 * APP下架详情
 */
@Data
@ToString(callSuper = true)
public class AppShipmentTaskDetailPojo {

    private Integer shipmentTaskDetailId;//出库任务详情id
    private Integer shipmentTaskId;//出库任务id
    private String materialDesc;//货物描述
    private String materialUnit;//货物单位
    private Double shipmentCount;//下架数量
    private Integer storagePositionId;//下架仓位id
    private String positionCode;//下架仓位编码
    private String batchNo;//批次号
    private String carrierCode;//载具编码
    private String areaCode;//区域编码
    private Integer stockStatus;//库存状态:0：合格；1：不合格

    private String clientName;//客户名称
    private String warehouseCode;//仓库编号

    private String positionCarCode;//仓位载具

}

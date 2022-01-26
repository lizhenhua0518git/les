package com.zkzn.les.oms.stationOrder.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 修改出库任务实体
 */
@Data
@ToString(callSuper = true)
public class UpdateShipmentTaskDetail extends AppShipmentTaskDetailPojo {

    private Integer shipmentStatus;//下架状态:0：未下架；1：下架
    private Integer updateUserId;//修改用户id
    private Date updateTime;//修改时间

}

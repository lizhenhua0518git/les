package com.zkzn.les.oms.delivery.pojo;

import com.zkzn.les.oms.stationOrder.pojo.AppShipmentTaskDetailPojo;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 修改交接数据
 */
@Data
@ToString(callSuper = true)
public class DeliveryPojo extends AppShipmentTaskDetailPojo {

    private Integer updateUserId;//修改用户id
    private Date updateTime;//修改时间

    private String connectName;//交接人
    private Date connectTime;//交接时间

    private Integer taskStatus;//出库任务状态

}

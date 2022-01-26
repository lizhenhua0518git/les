package com.zkzn.les.oms.stationOrder.pojo;

import com.zkzn.les.common.util.page.PageCondition;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 出库单详情实体
 */
@Data
@ToString(callSuper = true)
public class OrderTaskDetailPojo extends PageCondition {

    private Integer orderTaskDetailId;//订单任务详情id
    private Integer orderTaskId;//订单任务id
    private String materialDesc;//物料描述
    private String materialUnit;//物料单位
    private Double sendNumber;//发货数量
    private String batchNo;//批次号
    private Integer createrUserId;//创建用户id
    private Date createrTime;//创建时间

}

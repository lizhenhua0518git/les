package com.zkzn.les.oms.stationOrder.pojo;


import com.zkzn.les.common.util.page.PageCondition;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 订单任务实体
 */
@Data
@ToString(callSuper = true)
public class OrderTaskPojo extends PageCondition {

    private Integer orderTaskId;//订单任务主键id
    private String warehouseCode;//仓库编号
    private String warehouseName;//仓库名称
    private String clientName;//客户名称
    private String orderCode;//订单编号
    private Integer orderType;//订单类型 1、正常出库；2、不合格品出库；3、调拨出库
    private Integer operateUserId;//操作人员id
    private String operateUserName;//操作人员名称
    private Integer orderStatus;//是否匹配库存 0、未匹配；1、已匹配
    private String remark;//remark
    private Integer createrUserId;//
    private Date createrTime;//

}

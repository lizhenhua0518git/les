package com.zkzn.les.oms.stationOrder.pojo;

import com.zkzn.les.common.util.page.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * PC端下架库位清单
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PcShipmentTaskPojo extends PageCondition {

    private Integer shipmentTaskId;//出库任务id
    private Integer shipmentTaskDetailId;//出库任务id
    private String warehouseName;//仓库名称
    private String warehouseCode;//仓库编码
    private String clientName;//客户名称
    private String orderCode;//任务编号
    private String materialDesc;//货物名称
    private String materialUnit;//货物单位
    private Double shipmentCount;//下架数量
    private String positionCode;//仓位编号
    private String batchNo;//批次号
    private String connectName;//交接人名称
    private String carrierCode;//载具编号
    private String areaCode;//区域编号
    private Integer stockStatus;//库存类型0:合格 1:不合格
    private String stockDesc;//合格 不合格
    private Integer shipmentStatus;//下架状态0:未下架 1:已下架
    private Date createrTime;//创建时间

    private String startTime;//交接时间开始
    private String endTime;//交接时间结束
    private Date connectTime;//交接时间
    private Integer orderType;//单据类型
    private Integer taskStatus;//单据状态

}

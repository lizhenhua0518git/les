package com.zkzn.les.wms.upperFrame.pojo;

import lombok.Data;
import lombok.ToString;

/**
 * APP校验仓位
 */
@Data
@ToString(callSuper = true)
public class VerifyPositionPojo {

    private Integer storagePositionId;//仓位id
    private String positionCode;//仓位号
    private String materialDesc;//货物名称
    private String batchNo;//批次号
    private String clientName;//客户名称
    private Integer upperType;//0-合格品，1-不合格品
    private String warehouseCode;//仓库编号

    private Integer deviantData;//仓位校验提示 0正常数据。1是不符合存储策略的数据

    private Double sumCount;//库存数量
    private Integer positionStatus;//仓位使用状态0占用、1空闲、2存储仓位

    private String positionCarCode;//仓位载具号


}

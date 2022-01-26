package com.zkzn.les.wms.upperFrame.pojo;

import lombok.Data;
import lombok.ToString;

/**
 * 上架仓位
 */
@Data
@ToString(callSuper = true)
public class UpperPositionPojo {

    private Integer upperFrameId;//上架表id
    private Integer storagePositionId;//仓位id
    private String materialDesc;//物料描述
    private String positionCode;//仓位号
    private Double upperNumber;//上架数量
    private Double sumCount;//仓位库存
    private String materialUnit;//单位

    private Integer positionStatus;//仓位使用状态0占用、1空闲、2存储仓位
    private String batchNo;//批次号
    private String clientName;//客户名称
    private Integer upperType;//0-合格品，1-不合格品

    private String warehouseName;//仓库名称
    private String warehouseCode;//仓库编号

    private String positionCarCode;//仓位载具号

}

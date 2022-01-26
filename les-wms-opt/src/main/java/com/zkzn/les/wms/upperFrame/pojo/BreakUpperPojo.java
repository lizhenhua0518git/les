package com.zkzn.les.wms.upperFrame.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * APP上架详情实体
 */
@Data
@ToString(callSuper = true)
public class BreakUpperPojo {

    private Integer upperFrameId;//上架任务表主键id
    private String materialDesc;//物料名称
    private String materialUnit;//物料单位
    private Double materialNum;//物料数量
    private Integer upperType;//0-合格品，1-不合格品
    private String carrierCode;//载具号
    private String batchNo;//批次号
    private String clientName;//客户名称

    private List<UpperPositionPojo> storageList;//上架仓位分配
}

package com.zkzn.les.wms.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName AppTransferSlipResponse.java
 * @Description 调拨单返回实体类
 * @createTime 2020年10月22日 14:56:00
 */
@Data
public class AppTransferSlipResponse {
    @ApiModelProperty(name = "id", value = "调拨单id")
    private String id;
    @ApiModelProperty(name = "tasksId", value = "任务编号")
    private String tasksId;
    @ApiModelProperty(name = "sourceEntrepot", value = "源仓库")
    private String sourceEntrepot;
    @ApiModelProperty(name = "goalEntrepot", value = "目的仓库")
    private String goalEntrepot;
    @ApiModelProperty(name = "status", value = "任务状态(0 未下发  1 已下发  2 已完成)")
    private String status;
    @ApiModelProperty(name = "slipList", value = "子集列表")
    List<TransferSlipInfo> slipList;
}

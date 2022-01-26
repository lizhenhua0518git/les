package com.zkzn.les.common.pojo;

import com.zkzn.les.common.util.PageCondition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * @author lty
 * @version 1.0.0
 * @ClassName TransferSlip.java
 * @Description 调拨单实体类
 * @createTime 2020年10月13日 14:08:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TransferSlip extends PageCondition {
    private static final long serialVersionUID = -8407327435591451813L;

    @ApiModelProperty(name = "id", value = "调拨单id")
    private String id;
    @ApiModelProperty(name = "tasksId", value = "调拨任务编号")
    private String tasksId;
    @ApiModelProperty(name = "sourceEntrepot", value = "源仓库")
    private String sourceEntrepot;
    @ApiModelProperty(name = "goalEntrepot", value = "目的仓库")
    private String goalEntrepot;
    @ApiModelProperty(name = "factory", value = "工厂")
    private String factory;
    @ApiModelProperty(name = "status", value = "任务状态(0 未下发  1 已下发  2 已完成)")
    private String status;
    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;
    @ApiModelProperty(name = "detail", value = "详情")
    private String detail;
    @ApiModelProperty(name = "useWay", value = "用途")
    private String useWay;
    @ApiModelProperty(name = "isDel", value = "是否删除(0-删除 1 未删除)")
    private String isDel;
    @ApiModelProperty(name = "createId", value = "创建人ID")
    private String createId;
    @ApiModelProperty(name = "createName", value = "创建人")
    private String createName;
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;
    @ApiModelProperty(name = "res", value = "预留字段")
    private String res;
    @ApiModelProperty(name = "sourceStorageLocation", value = "出库库存地点")
    private String sourceStorageLocation;
    @ApiModelProperty(name = "goalStorageLocation", value = "入库库存地点")
    private String goalStorageLocation;
    @ApiModelProperty(name = "goalEntrepotId", value = "源仓库id")
    private String sourceEntrepotId;
    @ApiModelProperty(name = "goalEntrepotId", value = "目的仓库id")
    private String goalEntrepotId;

    @ApiModelProperty(name = "num", value = "物料种类")
    private String num;

}

package com.zkzn.les.stock.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkzn.les.common.util.page.PageCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 库存盘点任务主表
 * </p>
 *
 * @author Hush.
 * @since 2021-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SCheckInventoryTask对象", description="库存盘点任务主表")
public class SCheckInventoryTask extends PageCondition implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "盘点任务id")
    private Long taskId;

    @ApiModelProperty(value = "任务编号")
    private String taskNumber;

    @ApiModelProperty(value = "客户名称")
    @NotBlank(message = "客户名称不能为空")
    private String clientName;

    @ApiModelProperty(value = "仓库编码")
    @NotBlank(message = "仓库编码不能为空")
    private String warehouseCode;

    @ApiModelProperty(value = "待盘点仓位数量")
    private Integer positionNum;

    @ApiModelProperty(value = "审核人id")
    @NotNull(message = "审核人信息不可为空")
    private Long verifyUserId;

    @ApiModelProperty(value = "审核人名称")
    private String verifyUserName;

    @ApiModelProperty(value = "审核状态:0:未审核 5:接受结果损益,10:客户平仓,-1:取消损益")
    private Integer verifyStatus;

    @ApiModelProperty(value = "盘点状态:0:待盘点,1:正在盘点,-1:盘点结束")
    private Integer checkStatus;

    @ApiModelProperty(value = "创建者")
    private String createBy;


    @ApiModelProperty(value = "盘点结束时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;

    @ApiModelProperty(value = "审核时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date verifyTime;

    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;

}

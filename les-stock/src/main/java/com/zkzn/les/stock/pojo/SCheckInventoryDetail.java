package com.zkzn.les.stock.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkzn.les.common.util.page.PageCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 库存盘点物料详情表
 * </p>
 *
 * @author Hush.
 * @since 2021-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SCheckInventoryDetail对象", description="库存盘点物料详情表")
public class SCheckInventoryDetail extends PageCondition implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "盘点任务详情id")
    private Long detailId;

    @ApiModelProperty(value = "盘点任务id")
    @NotNull(message = "盘点任务id[taskId]不可为空")
    private Long taskId;

    @ApiModelProperty(value = "仓位库存表主键id")
    @NotNull(message = "仓位库存表主键id[storageBinId]不可为空")
    private Long storageBinId;

    @ApiModelProperty(value = "盘点数量")
    @NotNull(message = "盘点数量[checkCount]不可为空")
    private BigDecimal checkCount;

    @ApiModelProperty(value = "仓位原非限制库存")
    @NotNull(message = "仓位原非限制库存[beCounted=>stockCount]不可为空")
    private BigDecimal stockCount;

    @ApiModelProperty(value = "盘点人")
    private String handledBy;

    @ApiModelProperty(value = "盘点时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date handledTime;


}

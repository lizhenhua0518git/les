package com.zkzn.les.common.pojo;

import com.zkzn.les.common.util.PageCondition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName TransferSlipMaterial.java
 * @Description 调拨单 仓位信息
 * @createTime 2020年10月26日 15:35:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TransferSlipMaterial  extends PageCondition {
    private static final long serialVersionUID = -8407327435591451813L;

    /***
     * @Discription: 库存数量 非限制库存
     */
    @ApiModelProperty(name = "stockCount", value = "库存数量")
    private Double stockCount;

    @ApiModelProperty(name = "materialStorageId", value = "仓位表id")
    private String materialStorageId;

    @ApiModelProperty(name = "batchNo", value = "批次")
    private String batchNo;

    /***
     * @Discription: 库位编码
     */
    @ApiModelProperty(name = "positionCode", value = "库位编码")
    private String positionCode;

    @ApiModelProperty(name = "id", value = "id")
    private String id;
}

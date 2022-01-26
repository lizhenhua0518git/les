package com.zkzn.les.stock.pojo.vo;

import com.zkzn.les.stock.pojo.SCheckInventoryDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 盘点物料详情VO </p>
 *
 * @author Hush.
 * @since 2021/12/22 20:41
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SCheckInventoryDetailVO extends SCheckInventoryDetail {
    /**
     * 物料名称
     */
    private String materialDesc;

    /**
     * 物料单位
     */
    private String materialUnit;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 物料合格类型0：合格、1：不合格
     */
    private Integer qualifiedType;

    /**
     * 仓位id
     */
    private String storagePositionId;

    /**
     * 仓位code
     */
    private String positionCode;

    /**
     * 载具编码载具编码
     */
    private String positionCarCode;
}


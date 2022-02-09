package com.zkzn.les.stock.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 仓位分组展示物料信息VO </p>
 *
 * @author Hush.
 * @since 2021/12/23 16:34
 */
@Data
@Accessors(chain = true)
public class MaterialsGroupVO {

    /**
     * 仓位id
     */
    private String storagePositionId;

    /**
     * 仓位code
     */
    private String storagePositionCode;

    /**
     * 仓位材料
     */
    private List<BeCountedMaterialsVO> materials;

    /**
     * 盘点标识
     * 未盘点 -1
     * 正在盘点 0
     * 已结束 1
     */
    private Integer checkFlag;
}

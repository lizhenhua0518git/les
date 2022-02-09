package com.zkzn.les.stock.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 待盘点物料VO </p>
 * 用于App库位分组展示 </p>
 *
 * @author Hush.
 * @since 2021/12/23 18:48
 */
@Data
public class BeCountedMaterialsVO {
    /**
     * 仓位id
     */
    private String storagePositionId;
    /**
     * 仓位库存表主键id
     */
    private String storageBinId;
    /**
     * 载具编码
     */
    private String positionCarCode;
    /**
     * 物料描述
     */
    private String materialDesc;
    /**
     * 单位
     */
    private String materialUnit;
    /**
     * 批次
     */
    private String batchNo;
    /**
     * 库存状态:0：合格；1：不合格
     */
    private Integer stockStatus;
    /**
     * 待盘点量
     */
    private String beCounted;
    /**
     * 盘点任务详情id
     */
    private Long detailId;
    /**
     * 盘点数量
     */
    private String checkCount;
    /**
     * 盘点操作人
     */
    private String handledBy;

    /**
     * 操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date handledTime;

}

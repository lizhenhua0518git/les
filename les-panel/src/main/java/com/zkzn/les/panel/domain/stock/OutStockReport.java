package com.zkzn.les.panel.domain.stock;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 出库日报表 </p>
 *
 * @author Hush.
 * @since 2022/01/04 9:36
 */
@Data
@ToString
public class OutStockReport {

    /**
     * 仓库名称
     */
    private String warehouseName;
    /**
     * 客户名称
     */
    private String clientName;
    /**
     * 任务编号
     */
    private String orderCode;
    /**
     * 货物名称
     */
    private String materialDesc;
    /**
     * 单位
     */
    private String materialUnit;
    /**
     * 批次号
     */
    private String batchNo;
    /**
     * 下架仓位
     */
    private String positionCode;
    /**
     * 下架载具
     */
    private String carrierCode;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")//出参格式化
    private Date createTime;

    /**
     * 交接时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")//出参格式化
    private Date connectTime;
    /**
     * 交接人
     */
    private String connectName;

}

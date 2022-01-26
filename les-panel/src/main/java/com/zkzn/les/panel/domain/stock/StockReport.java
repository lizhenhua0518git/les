package com.zkzn.les.panel.domain.stock;

import lombok.Data;
import lombok.ToString;

/**
 * 库存报表 </p>
 * 统计仓库现有库存信息
 *
 * @author Hush.
 * @since 2022/01/04 9:36
 */
@Data
@ToString
public class StockReport {
    private String clientName;
    private String materialDesc;
    private String warehouseName;
    private String positionCode;
    private String positionCarCode;
    private Double sumStockCount;
    private Double sumFrozenCount;
    private Double sumPreUseCount;
    private String sumAllCount;
    private String batchNo;
    private String stockStatus;
}
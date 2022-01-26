package com.zkzn.les.panel.service;

import com.zkzn.les.panel.domain.stock.StockReport;

import java.util.List;
import java.util.Map;

/**
 * 库存报表
 *
 * @author Hush
 */
public interface StockPanelService {

    /**
     * 仓库库存统计报表数据
     *
     * @param dto 检索条件
     * @return 库存
     */
    List<StockReport> listStockPanel(Map<String, Object> dto);


    /**
     * 初始化仓库下拉选项
     *
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author Hush.
     * @since 2022/1/6 14:27
     */
    List<Map<String, Object>> initWarehouseSelect();

    /**
     * 初始化客户下拉选项
     *
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author Hush.
     * @since 2022/1/6 14:27
     */
    List<Map<String, Object>> initClientSelect();
}

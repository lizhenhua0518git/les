package com.zkzn.les.panel.dao;

import com.zkzn.les.panel.domain.stock.StockReport;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 库存报表 Mapper 接口
 *
 * @author Hush
 * @since 2021/12/31 17:31
 */
@Mapper
public interface StockPanelDao {

    /**
     * 仓库库存统计报表数据
     *
     * @param dto 检索条件
     * @return 库存
     * @since 2021/12/31 17:31
     */
    List<StockReport> selectStockPanel(Map<String, Object> dto);


    /**
     * 初始化仓库下拉选项
     *
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author Hush.
     * @since 2022/1/6 14:27
     */
    List<Map<String, Object>> selectWarehouse();

    /**
     * 初始化客户下拉选项
     *
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author Hush.
     * @since 2022/1/6 14:27
     */
    List<Map<String, Object>> selectClient();
}

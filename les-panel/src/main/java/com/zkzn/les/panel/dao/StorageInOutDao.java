package com.zkzn.les.panel.dao;


import com.zkzn.les.panel.domain.stock.InStockReport;
import com.zkzn.les.panel.domain.stock.OutStockReport;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 出入库服务实现类 </p>
 *
 * @author Hush.
 * @since 2022年1月8日 15点57分
 */
@Mapper
public interface StorageInOutDao {
    /**
     * 检索入库物料报表
     *
     * @param dto: 检索条件
     * @return java.util.List<com.zkzn.les.panel.domain.stock.InStockReport>
     * @author Hush.
     * @since 2022/1/8 15:59
     */
    List<InStockReport> selectInStockReportList(Map<String, Object> dto);


    /**
     * 检索出库物料报表
     *
     * @param dto: 检索条件
     * @return java.util.List<com.zkzn.les.panel.domain.stock.OutStockReport>
     * @author Hush.
     * @since 2022/1/8 15:59
     */
    List<OutStockReport> selectOutStockReportList(Map<String, Object> dto);
}

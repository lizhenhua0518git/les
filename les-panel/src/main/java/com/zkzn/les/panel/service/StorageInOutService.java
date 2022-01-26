package com.zkzn.les.panel.service;


import com.zkzn.les.panel.domain.stock.InStockReport;
import com.zkzn.les.panel.domain.stock.OutStockReport;

import java.util.List;
import java.util.Map;

/**
 * 出入库服务接口
 *
 * @author Hush
 */
public interface StorageInOutService {

    /**
     * 入库报表检索
     *
     * @param dto:检索条件
     * @return 结果集
     * @author Hush.
     * @since 2022/1/8 15:27
     */
    List<InStockReport> listInStockReport(Map<String, Object> dto);

    /**
     * 出库报表检索
     *
     * @param dto:检索条件
     * @return 结果集
     * @author Hush.
     * @since 2022/1/8 15:27
     */
    List<OutStockReport> listOutStockReport(Map<String, Object> dto);
}

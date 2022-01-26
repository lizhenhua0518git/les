package com.zkzn.les.panel.service.impl;

import com.zkzn.les.panel.dao.StockPanelDao;
import com.zkzn.les.panel.domain.stock.StockReport;
import com.zkzn.les.panel.service.StockPanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 库存报表 </p>
 *
 * @author Hush.
 * @since 2021/12/31 17:31
 */
@Service
public class StockPanelServiceImpl implements StockPanelService {

    @Autowired
    private StockPanelDao stockPanelDao;

    /**
     * 仓库库存统计报表数据
     *
     * @param dto 检索条件
     * @return 库存
     */
    @Override
    public List<StockReport> listStockPanel(Map<String, Object> dto) {
        List<StockReport> list = stockPanelDao.selectStockPanel(dto);
        //客户_物料分组
        Map<String, List<StockReport>> stockMap = list.stream()
                .collect(Collectors.groupingBy(item -> item.getClientName() + "_" + item.getMaterialDesc()));
        for (String key : stockMap.keySet()) {
            List<StockReport> keyList = stockMap.get(key);
            //求和(客户共多少物料)
            double sum = keyList.stream().mapToDouble(item -> item.getSumStockCount() + item.getSumFrozenCount() + item.getSumPreUseCount()).sum();
            //html页面表格合并
            keyList.forEach(item -> item.setSumAllCount(String.format("%s::%s", key, sum)));
        }
        return list;
    }

    /**
     * 初始化仓库下拉选项
     *
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author Hush.
     * @since 2022/1/6 14:27
     */
    @Override
    public List<Map<String, Object>> initWarehouseSelect() {

        return stockPanelDao.selectWarehouse();
    }

    /**
     * 初始化客户下拉选项
     *
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author Hush.
     * @since 2022/1/6 14:27
     */
    @Override
    public List<Map<String, Object>> initClientSelect() {

        return stockPanelDao.selectClient();
    }
}

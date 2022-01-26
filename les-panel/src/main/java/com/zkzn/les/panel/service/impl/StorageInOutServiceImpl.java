package com.zkzn.les.panel.service.impl;

import com.zkzn.les.panel.dao.StorageInOutDao;
import com.zkzn.les.panel.domain.stock.InStockReport;
import com.zkzn.les.panel.domain.stock.OutStockReport;
import com.zkzn.les.panel.service.StorageInOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 出入库服务实现类 </p>
 *
 * @author Hush.
 * @since 2022/01/08 15:54
 */
@Service
public class StorageInOutServiceImpl implements StorageInOutService {
    @Autowired
    private StorageInOutDao storageInOutDao;

    /**
     * 入库报表检索
     *
     * @param dto :检索条件
     * @return 结果集
     * @author Hush.
     * @since 2022/1/8 15:27
     */
    @Override
    public List<InStockReport> listInStockReport(Map<String, Object> dto) {
        return storageInOutDao.selectInStockReportList(dto);
    }

    /**
     * 出库报表检索
     *
     * @param dto :检索条件
     * @return 结果集
     * @author Hush.
     * @since 2022/1/8 15:27
     */
    @Override
    public List<OutStockReport> listOutStockReport(Map<String, Object> dto) {
        return storageInOutDao.selectOutStockReportList(dto);
    }
}

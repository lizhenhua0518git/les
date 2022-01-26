package com.zkzn.les.basicInfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.dao.StockPropertyDao;
import com.zkzn.les.basicInfo.pojo.StockProperty;
import com.zkzn.les.basicInfo.service.StockPropertyService;
import com.zkzn.les.basicInfo.util.PageUtil;

@Service
public class StockPropertyServiceImpl implements StockPropertyService {

    @Autowired
    private StockPropertyDao stockPropertyDao;

    @Override
    public List<StockProperty> listStockProperty(StockProperty stockProperty) {
        // TODO Auto-generated method stub
        return stockPropertyDao.listStockProperty(stockProperty);
    }

    @Override
    public PageInfo<StockProperty> listStockPropertyPage(StockProperty stockProperty) {
        // TODO Auto-generated method stub

        PageUtil.setPageParam(stockProperty);
        List<StockProperty> list = listStockProperty(stockProperty);
        PageInfo<StockProperty> pageInfo = new PageInfo<StockProperty>(list);

        return pageInfo;
    }

    @Override
    public int saveStockProperty(StockProperty stockProperty) {
        // TODO Auto-generated method stub
        return stockPropertyDao.saveStockProperty(stockProperty);
    }

    @Override
    public int updateStockProperty(StockProperty stockProperty) {
        // TODO Auto-generated method stub
        return stockPropertyDao.updateStockProperty(stockProperty);
    }

    @Override
    public int removeStockProperty(List<String> ids) {
        // TODO Auto-generated method stub
        return stockPropertyDao.removeStockProperty(ids);
    }

}

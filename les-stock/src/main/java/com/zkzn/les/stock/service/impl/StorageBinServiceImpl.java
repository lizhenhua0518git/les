package com.zkzn.les.stock.service.impl;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.common.exception.EmptyExamine;
import com.zkzn.les.common.util.page.PageUtil;
import com.zkzn.les.stock.constants.StockConstants;
import com.zkzn.les.stock.dao.StorageBinDao;
import com.zkzn.les.stock.pojo.MaterialInStockPojo;
import com.zkzn.les.stock.pojo.MaterialStorageBin;
import com.zkzn.les.stock.service.StorageBinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName StorageBinServiceImpl
 * @Description 库存状态
 * @Author zhanglei
 * Date 2021/6/30 10:02
 * @Version 1.0
 **/
@Service
public class StorageBinServiceImpl implements StorageBinService {

    @Autowired
    private StorageBinDao storageBinDao;

    @Override
    public List<Map<String, Object>> carInStock(Map<String, Object> param) {
        List<Map<String, Object>> returnList = new ArrayList<>();
        returnList.addAll(storageBinDao.carInStock(param));
        return returnList;
    }

    @Override
    public PageInfo<MaterialStorageBin> listMaterialStorageBin(MaterialStorageBin materialStorageBin) {
        PageUtil.setPageParam(materialStorageBin);
        List<MaterialStorageBin> list = storageBinDao.listMaterialStorageBin(materialStorageBin);
        PageInfo<MaterialStorageBin> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<MaterialInStockPojo> listMaterialInstock(MaterialInStockPojo materialInStockPojo) {
        PageUtil.setPageParam(materialInStockPojo);
        List<MaterialInStockPojo> list = storageBinDao.listMaterialInstock(materialInStockPojo);
        PageInfo<MaterialInStockPojo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /***
     * @Discription: 库存状态更新
                     1.数据格式：
                       {"stockStatus":"0-非限制 1-冻结 2-点收 3-质检 4-上架","data":""}
     * @return void
     * @Author: zhanglei on 2021/6/30 10:20
     */
    @Override
    public void updateStorageBinInfo(Map<String, Object> param) throws Exception {
        //库存状态参数必传
        EmptyExamine.examine(param, StockConstants.STOCK_STATUS);

    }

    /***
     * @Discription: 删除reids中物料缓存
     * @param param
     * @return void
     * @Author: zhanglei on 2021/6/30 14:52
     */
    @Override
    public void removeProductInventoryCache(Map<String, Object> param) throws Exception {

    }

    /***
     * @Discription: 更新数据库中物料数据
     * @param param
     * @return void
     * @Author: zhanglei on 2021/6/30 14:53
     */
    @Override
    public void updateProductInventory(Map<String, Object> param) throws Exception {
        storageBinDao.updateStorageBinInfo(param);
    }
}

package com.zkzn.les.stock.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zkzn.les.stock.pojo.MaterialInStockPojo;
import com.zkzn.les.stock.pojo.MaterialStorageBin;

import java.util.List;
import java.util.Map;

/**
 * @ClassName StroageBinService
 * @Description TODO
 * @Author zhanglei
 * Date 2021/6/30 10:01
 * @Version 1.0
 **/
public interface StorageBinService {

    /**
     * 查询载具库存
     * @param param
     * @return
     */
    List<Map<String,Object>> carInStock(Map<String, Object> param);


    /**
     * pc端查询仓位库存列表
     * @param materialStorageBin
     * @return
     */
    PageInfo<MaterialStorageBin> listMaterialStorageBin(MaterialStorageBin materialStorageBin);

    /**
     * pc端查询仓库库存列表
     * @param materialInStockPojo
     * @return
     */
    PageInfo<MaterialInStockPojo> listMaterialInstock(MaterialInStockPojo  materialInStockPojo);

    /***
     * @Discription: 删除redis中的库存缓存值
     * @param param
     * @return void
     * @Author: zhanglei on 2021/6/30 14:50
     */
    void removeProductInventoryCache(Map<String,Object> param) throws Exception;

    /***
     * @Discription: 更新仓库库存
     * @param param
     * @return void
     * @Author: zhanglei on 2021/6/30 14:53
     */
    void updateProductInventory(Map<String,Object> param) throws Exception;
}

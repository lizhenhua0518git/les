package com.zkzn.les.stock.dao;

import com.zkzn.les.stock.pojo.MaterialInStockPojo;
import com.zkzn.les.stock.pojo.MaterialStorageBin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ClassName StorageBinDao
 * @Description TODO
 * @Author zhanglei
 * Date 2021/6/29 17:49
 * @Version 1.0
 **/
@Mapper
public interface StorageBinDao {

    /**
     * 查询载具库存
     * @param param
     * @return
     */
    List<Map<String, Object>> carInStock(Map<String, Object> param);

    /**
     * pc端仓位库存查询列表
     * @param materialStorageBin
     * @return
     */
    List<MaterialStorageBin> listMaterialStorageBin(MaterialStorageBin materialStorageBin);

    /**
     * pc端仓库库存查询列表
     * @param materialInStockPojo
     * @return
     */
    List<MaterialInStockPojo> listMaterialInstock(MaterialInStockPojo materialInStockPojo);

    /***
     * @Discription: 批量保存库存信息
     * @param param
     * @return void
     * @Author: zhanglei on 2021/6/29 17:53
     */
    void saveStorageBinInfo(List<Map<String,Object>> param);

    /***
     * @Discription: 库存状态更新
     * @param param
     * @return void
     * @Author: zhanglei on 2021/7/1 15:52
     */
    void updateStorageBinInfo(Map<String,Object> param);
}

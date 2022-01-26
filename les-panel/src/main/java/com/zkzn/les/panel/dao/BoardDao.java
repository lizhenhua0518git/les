package com.zkzn.les.panel.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ClassName InBoundBoardDao
 * @Description
 * @Author 刘松山
 * @date 2021/3/15 14:58
 **/
@Mapper
public interface BoardDao {
    /* *
     * @Author 刘松山
     * @Description //根据仓库分组获取入库物料信息
     * @Date 10:20 2021/3/16
     * @Param 
     * @return 
     **/
    
    List<Map<String, Object>> listAllInBoundInfo(Map<String, Object> map);
    /* *
     * @Author 刘松山
     * @Description  获取所有仓库列表
     * @Date 11:30 2021/3/16
     * @Param 
     * @return 
     **/
    
    List<Map<String, Object>> listWarehouseList();
    /* *
     * @Author 刘松山
     * @Description //获取仓库分组出库物料信息
     * @Date 10:46 2021/3/17
     * @Param
     * @return
     **/

    List<Map<String, Object>> listAllOutBoundInfo(Map<String, Object> map);
    /* *
     * @Author 刘松山
     * @Description 获取库存库存信息
     * @Date 15:29 2021/6/2
     * @Param
     * @return
     **/

    List<Map<String, Object>> listInventory();

}

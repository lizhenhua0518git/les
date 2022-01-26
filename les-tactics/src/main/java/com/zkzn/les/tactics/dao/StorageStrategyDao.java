package com.zkzn.les.tactics.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ClassName StorageStrategyDao
 * @Description 上架策略
 * @Author zhanglei
 * Date 2021/7/2 17:02
 * @Version 1.0
 **/
@Mapper
public interface StorageStrategyDao {

    /***
     * @Discription: 上架仓位获取
     * @param param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zhanglei on 2021/7/2 17:57
     */
    List<Map<String,Object>> getPositionByWarehouseCode(Map<String,Object> param);

    /***
     * @Discription: 条件查询仓位信息
     * @param param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zhanglei on 2021/7/5 14:14
     */
    List<Map<String,Object>> getPositions(Map<String,Object> param);

    /**
     * 查询当前货物已占用的仓位
     * @param param
     * @return
     */
    List<Map<String,Object>> getStorageCodeByData(Map<String,Object> param);

    /**
     * 查询当前客户的空仓位
     * @param param
     * @return
     */
    List<Map<String,Object>> getNullStorage(Map<String,Object> param);

    /**
     * 仓位占用
     * @param param
     */
    void updateOccupyStatus(Map<String,Object> param);
}

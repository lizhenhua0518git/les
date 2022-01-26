package com.zkzn.les.tactics.service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName StorageStrategyService
 * @Description 上架策略获取仓位
 * @Author zhanglei
 * Date 2021/7/2 17:00
 * @Version 1.0
 **/
public interface StorageStrategyService {

    /***
     * @Discription: 上架策略仓位信息获取
     * @param param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @Author: zhanglei on 2021/7/2 17:59
     */
    List<Map<String, Object>> getStorageStrategyResult(Map<String,Object> param) throws Exception;

    /**
     * 查询上架仓位
     * @param param
     * @return
     */
    List<Map<String, Object>> getStorageCode(Map<String, Object> param);
}

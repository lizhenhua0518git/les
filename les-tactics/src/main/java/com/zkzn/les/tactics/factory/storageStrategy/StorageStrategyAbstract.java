package com.zkzn.les.tactics.factory.storageStrategy;

import java.util.List;
import java.util.Map;

/**
 * @ClassName StorageStrategyAbstract
 * @Description 上架策略顶层抽象类
 * @Author leizhang
 * Date 2021/7/2 10:29 下午
 * @Version 1.0
 **/
public abstract class StorageStrategyAbstract {


    /**
     * @Description : 获取上架仓位信息
     * @Author : leizhang
     * @Date 10:30 下午 2021/7/2
     * @param param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    public  abstract List<Map<String, Object>> getStrategyResult(Map<String,Object> param);

}

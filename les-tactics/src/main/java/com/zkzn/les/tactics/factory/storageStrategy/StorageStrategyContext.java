package com.zkzn.les.tactics.factory.storageStrategy;

import java.util.List;
import java.util.Map;

/**
 * @ClassName StorageStrategyContext
 * @Description 上架策略上下文
 * @Author leizhang
 * Date 2021/7/2 10:26 下午
 * @Version 1.0
 **/
public  class StorageStrategyContext {
    private StorageStrategyAbstract storageStrategyAbstract;

    public StorageStrategyContext(StorageStrategyAbstract storageStrategyAbstract) {
        this.storageStrategyAbstract = storageStrategyAbstract;
    }

    /***
     * @Discription: 获取策略执行结果
     * @param param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @Author: zhanglei on 2021/6/25 17:15
     */
    public List<Map<String,Object>> getStrategyResult(Map<String,Object> param){
        return storageStrategyAbstract.getStrategyResult(param);
    }
}

package com.zkzn.les.tactics.factory.uploadStrategy;

import java.util.Map;

/**
 * @ClassName UploadPlatStrategyContext
 * @Description 卸货点获取策略上下文
 * @Author zhanglei
 * Date 2021/6/25 17:08
 * @Version 1.0
 **/
public  class UploadPlatStrategyContext {

    private UploadPlatStrategyAbstract uploadPlatStrategyAbstract;

    public UploadPlatStrategyContext(UploadPlatStrategyAbstract uploadPlatStrategyAbstract) {
        this.uploadPlatStrategyAbstract = uploadPlatStrategyAbstract;
    }

    /***
     * @Discription: 获取策略执行结果
     * @param param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @Author: zhanglei on 2021/6/25 17:15
     */
    public Map<String,Object> getStrategyResult(Map<String,Object> param){
       return uploadPlatStrategyAbstract.getStrategyResult(param);
    }
}
package com.zkzn.les.tactics.factory.uploadStrategy;

import java.util.Map;

/**
 * @ClassName UploadPlatStrategyAbstract
 * @Description  策略对象抽象类
 * @Author zhanglei
 * Date 2021/6/25 17:12
 * @Version 1.0
 **/
public abstract class UploadPlatStrategyAbstract {

    /***
     * @Discription: 抽象方法 获取卸货点策略执行结果
     * @param param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @Author: zhanglei on 2021/6/25 17:29
     */
    public  abstract Map<String,Object> getStrategyResult(Map<String,Object> param);
}
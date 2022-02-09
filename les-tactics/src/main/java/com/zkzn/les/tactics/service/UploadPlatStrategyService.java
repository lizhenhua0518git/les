package com.zkzn.les.tactics.service;

import java.util.Map;

/**
 * @ClassName UploadPlatStrategyService
 * @Author zhanglei
 * Date 2021/6/25 17:51
 * @Version 1.0
 **/
public interface UploadPlatStrategyService {
    
    /***
     * @Discription: 启动策略信息获取 by 策略类型（字典表类型）
     * @param param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @Author: zhanglei on 2021/6/25 17:53
     */
    Map<String,Object> listStrategyInfo(Map<String,Object> param) throws Exception;

    /***
     * @Discription: 卸货点策略卸货点信息获取
     * @param param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @Author: zhanglei on 2021/6/25 18:04
     */
    Map<String, Object> getUploadStrategyResult(Map<String, Object> param) throws Exception;
}

package com.zkzn.les.stock.service;

import java.util.Map;

/**
 * @ClassName MaterialStorageBinService
 * @Author zhanglei
 * Date 2021/7/21 14:05
 * @Version 1.0
 **/
public interface MaterialStorageBinService {
    /***
     * @Discription: 库存状态转换
     * @param requestMap
     * @return void
     * @Author: zhanglei on 2020/12/9 10:22
     */
    void storeBinStockStatusChange(Map<String, Object> requestMap) throws Exception;
}

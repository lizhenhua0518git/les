package com.zkzn.les.wms.service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName PlatTransferService
 * @Description 拆盘任务
 * @Author zhanglei
 * Date 2021/6/23 11:13
 * @Version 1.0
 **/
public interface PlatTransferTaskService {
    /***
     * @Discription: 拆盘主表信息查询
     * @param param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zhanglei on 2021/6/23 11:36
     */
    List<Map<String,Object>> listTransferInfo(Map<String,Object> param) throws Exception;
    
    /***
     * @Discription: 拆盘子表信息查询
     * @param param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zhanglei on 2021/6/23 15:13
     */
    List<Map<String,Object>> listTransferDetailInfo(Map<String,Object> param) throws Exception;

    /**
     * @Description : 拆盘任务提交
     * @Author : leizhang
     * @Date 7:27 下午 2021/6/23
     * @param
     * @return void
     **/
    void updatePlatTransferDetailInfo(Map<String,Object> params) throws Exception;
}

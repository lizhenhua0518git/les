package com.zkzn.les.wms.pc.inbound.service;

import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @ClassName LineUpService
 * @Description
 * @Author 刘松山
 * @date 2021/6/17 17:42
 **/
public interface LineUpPcService {
    /* *
     * @Author 刘松山
     * @Description 查询排号信息列表
     * @Date 17:42 2021/6/17
     * @Param
     * @return
     **/

    PageInfo<Map<String,Object>> listLineUpInfo(Map<String,Object> param);

    /* *
     * @Author 刘松山
     * @Description 分页查询排号卸货任务详情信息
     * @Date 13:26 2021/6/22
     * @Param 
     * @return 
     **/
    
    PageInfo<Map<String,Object>> listLineUpDetailRecord(Map<String,Object> param);

}

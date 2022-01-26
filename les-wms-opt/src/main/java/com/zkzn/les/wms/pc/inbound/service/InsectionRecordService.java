package com.zkzn.les.wms.pc.inbound.service;

import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @ClassName InsectionRecordService
 * @Description
 * @Author 刘松山
 * @date 2021/6/23 18:32
 **/
public interface InsectionRecordService {

    /***
     * @Discription: 质检记录查询
     * @param paraMap
     * @return com.github.pagehelper.PageInfo<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zhanglei on 2020/9/5 16:18
     */
    PageInfo<Map<String,Object>> listReceiveDetailRecordOfInSpect(Map<String,Object> paraMap);
}

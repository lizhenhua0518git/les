package com.zkzn.les.wms.pc.inbound.dao;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ClassName LineUpDao
 * @Description
 * @Author 刘松山
 * @date 2021/6/17 17:39
 **/
@Mapper
public interface LineUpPcDao {

    /* *
     * @Author 刘松山
     * @Description 查询排号信息列表
     * @Date 17:42 2021/6/17
     * @Param
     * @return
     **/

    List<Map<String,Object>> listLineUpInfo(Map<String,Object> param);


    /**.
     *
     * @param param
     * @return
     * @Author:wangzhou
     * @date:2020年8月21日
     * @Description:查询点收记录详情信息
     */
    List<Map<String,Object>> listLineUpDetailRecord(Map<String,Object> param);
}

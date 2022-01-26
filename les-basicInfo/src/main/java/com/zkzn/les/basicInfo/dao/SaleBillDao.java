package com.zkzn.les.basicInfo.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SaleBillDao
 * @Description
 * @Author 刘松山
 * @date 2021/5/26 14:27
 **/
@Mapper
public interface SaleBillDao {

    /* *
     * @Author 刘松山
     * @Description 获取销售订单列表
     * @Date 14:28 2021/5/26
     * @Param
     * @return
     **/

    List<Map<String,Object>> listSaleBill(Map<String,Object> map);
    /* *
     * @Author 刘松山
     * @Description 获取销售订单明细列表
     * @Date 14:29 2021/5/26
     * @Param 
     * @return 
     **/
    
    List<Map<String,Object>> listSaleBillDetail(Map<String,Object> map);
}


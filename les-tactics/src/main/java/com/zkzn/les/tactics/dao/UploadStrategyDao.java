package com.zkzn.les.tactics.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UploadStrategyDao
 * @Author zhanglei
 * Date 2021/6/25 17:40
 * @Version 1.0
 **/
@Mapper
public interface UploadStrategyDao {
    /***
     * @Discription: 启用策略信息获取
     * @param param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @Author: zhanglei on 2021/6/25 17:47
     */
    Map<String,Object> listStrategyInfo(Map<String,Object> param);

    /***
     * @Discription: 空闲卸货点查询
     * @param param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @Author: zhanglei on 2021/6/28 10:23
     */
    List<Map<String,Object>> listUploadAddress(Map<String,Object> param);

    /***
     * @Discription: 卸货点信息修改
     * @param param
     * @return void
     * @Author: zhanglei on 2021/6/28 10:35
     */
    void updateUploadAddress(Map<String,Object> param);

    /***
     * @Discription: 空闲卸货点查询
     * @param param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zhanglei on 2021/7/2 16:21
     */
    List<Map<String,Object>> listFreeAddresses(Map<String,Object> param);
}

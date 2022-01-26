package com.zkzn.les.basicInfo.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SysInterfaceDao
 * @Description
 * @Author 刘松山
 * @date 2021/6/18 15:31
 **/
@Mapper
public interface SysInterfaceDao {
    /**.
     *
     * 功能描述:保存接口配置
     * @param map
     * @return
     */
    int save(Map<String,Object> map);

    /* *
     * @Author 刘松山
     * @Description 删除记录
     * @Date 19:10 2021/6/21
     * @Param
     * @return
     **/

    int remove (String id);

    /**.
     *
     * 功能描述:获取调用接口信息
     * @return
     */
    List<Map<String,Object>> listSysInterfaceList(Map<String,Object> map);
}

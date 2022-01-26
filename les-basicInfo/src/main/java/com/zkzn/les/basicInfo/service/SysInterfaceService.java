package com.zkzn.les.basicInfo.service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SysInterfaceService
 * @Description
 * @Author 刘松山
 * @date 2021/6/18 15:32
 **/
public interface SysInterfaceService {

    /**.
     *
     * 功能描述:保存接口配置
     * @param map
     * @return
     */
    int save(Map<String,Object> map);



    List<Map<String,Object>> listSysInterfaceList(Map<String,Object> map);
}

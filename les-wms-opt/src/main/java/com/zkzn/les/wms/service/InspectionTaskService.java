package com.zkzn.les.wms.service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName InspectionService
 * @Description TODO
 * @Author zhanglei
 * Date 2021/6/22 9:44
 * @Version 1.0
 **/
public interface InspectionTaskService {

    /***
     * @Discription: 质检主表信息查询
     * @param param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zhanglei on 2021/6/22 10:09
     */
    List<Map<String,Object>> listInspectionInfo(Map<String,Object> param) throws Exception;
    
    /***
     * @Discription: 质检子表列表信息查询
     * @param param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zhanglei on 2021/6/22 10:30
     */
    List<Map<String,Object>> listInspectionDetailInfo(Map<String,Object> param) throws Exception;

    /***
     * @Discription: 质检提交作业
     * @param  param Map<String,Object>
     * @return null
     * @Author: zhanglei on 2021/6/22 10:36
     */
    void updateInspectionInfo(Map<String,Object> param) throws  Exception;


    /***
     * @Discription: 质检开始信息记录
     * @param param
     * @return void
     * @Author: zhanglei on 2021/6/23 10:17
     */
    void updateInspectionStartInfo(Map<String, Object> param) throws Exception;

    /***
     * @Discription: 质检开始信息记录
     * @param param
     * @return void
     * @Author: zhanglei on 2021/6/23 10:17
     */
    void updateInspectionEndInfo(Map<String, Object> param) throws Exception;
}

package com.zkzn.les.wms.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ClassName InspectionTaskDao
 * @Description T_INSPECTION_TASK 相关操作
 * @Author zhanglei
 * Date 2021/6/21 16:55
 * @Version 1.0
 **/
@Mapper
public interface InspectionTaskDao {
    /***
     * @Discription: 质检主表任务信息保存
     * @param param
     * @return void
     * @Author: zhanglei on 2021/6/21 17:03
     */
    void saveInspectionInfo(Map<String,Object> param);

    /***
     * @Discription: 质检子表任务信息保存
     * @param param
     * @return void
     * @Author: zhanglei on 2021/6/21 17:30
     */
    void saveInspectionDetailInfo(List<Map<String,Object>> param);

    /***
     * @Discription: 质检主表信息查询
     * @param param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zhanglei on 2021/6/22 9:46
     */
    List<Map<String,Object>> listInspectionInfo(Map<String,Object> param);
    
    /***
     * @Discription: 质检子表信息查询
     * @param param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zhanglei on 2021/6/22 9:48
     */
    List<Map<String,Object>> listInspectionDetailInfo(Map<String,Object> param);

    /***
     * @Discription: 质检异常信息保存
     * @param param
     * @return void
     * @Author: zhanglei on 2021/6/22 11:34
     */
    void saveInspectionAbnormalInfo(Map<String,Object> param);

    /***
     * @Discription: 更新质检主表信息
     * @param param
     * @return void
     * @Author: zhanglei on 2021/6/22 13:49
     */
    void updateInspectionInfo(Map<String,Object> param);
    
    /***
     * @Discription: 批量更新质检任务子表
     * @param param
     * @return void
     * @Author: zhanglei on 2021/6/22 14:00
     */
    void updateInspectionDetailInfos(List<Map<String,Object>> param);

    /***
     * @Discription: 更新质检任务子表
     * @param param
     * @return void
     * @Author: zhanglei on 2021/6/22 14:12
     */
    void updateInspectionDetailInfo(Map<String,Object> param);

}

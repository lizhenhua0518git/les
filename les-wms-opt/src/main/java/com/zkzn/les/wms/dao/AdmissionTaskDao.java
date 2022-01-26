package com.zkzn.les.wms.dao;

import com.zkzn.les.wms.pojo.admission.AdmissionTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName AdmissionTask
 * @Description 排号任务相关mapper
 * @Author zhanglei
 * Date 2021/6/12 0:45
 * @Version 1.0
 **/
@Mapper
public interface AdmissionTaskDao {
    /***
     * @Discription: 排号任务列表数据查询
     * @param requestMap
     * @return java.util.List<com.zkzn.les.wms.pojo.admission.AdmissionTask>
     * @Author: zhanglei on 2021/6/14 14:34
     */
    List<AdmissionTask> listAdmissionTasks(@Param("requestMap")Map<String, Object> requestMap);

    /***
     * @Discription: 保存排号任务
     * @param param
     * @return int
     * @Author: zhanglei on 2021/6/10 17:58
     */
    int saveAdmissionTaskInfo(Map<String, Object> param);

    /***
     * @Discription: 修改排号信息
     * @param param
     * @return void
     * @Author: zhanglei on 2021/6/11 11:02
     */
    void modifyAdmissionInfo(Map<String,Object> param);

}

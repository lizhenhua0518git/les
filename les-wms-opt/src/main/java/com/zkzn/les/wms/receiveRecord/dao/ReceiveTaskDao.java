package com.zkzn.les.wms.receiveRecord.dao;

import com.zkzn.les.wms.receiveRecord.pojo.SaveReceiveDetailPojo;
import com.zkzn.les.wms.receiveRecord.pojo.UpdateReceiveTaskPojo;
import com.zkzn.les.wms.upperFrame.pojo.UpperFrameData;
import com.zkzn.les.wms.upperFrame.pojo.UpperPositionPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ReceiveTaskDao
 * @Description 点收任务dao
 * @Author zhanglei
 * Date 2021/6/15 10:59
 * @Version 1.0
 **/
@Mapper
public interface ReceiveTaskDao {
    /***
     * @Discription: 保存点收主表
     * @param param
     * @return int
     * @Author: zhanglei on 2021/6/10 17:11
     */
    int saveReceiveInfo(List<Map<String,Object>> param);
    /***
     * @Discription: 保存点收详情
     * @param list
     * @return int
     * @Author: zhanglei on 2021/6/10 17:44
     */
    int saveReceiveRecordDetails(List<Map<String,Object>> list);

    /***
     * @Discription: 点收任务主表信息查询
     * @param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zhanglei on 2021/6/15 15:00
     */
    List<Map<String,Object>> listReceiveTask(Map<String,Object> param);

    /**
     * 查询生成上架任务数据
     * @param receiveTaskId
     * @return
     */
    List<UpperFrameData> getUpperFrameData(@Param("receiveTaskId") Integer receiveTaskId);

    /***
     * @Discription: 点收任务主表数据修改
     * @param param
     * @return void
     * @Author: zhanglei on 2021/6/15 17:53
     */
    void updateReceiveTask(Map<String,Object> param);

    /**
     * 点收任务主表数据修改
     * @param updateReceiveTaskPojo
     */
    void updateReceiveTaskByPojo(UpdateReceiveTaskPojo updateReceiveTaskPojo);

    /**
     * 根据点收主表id修改点收详情
     * @param updateReceiveTaskPojo
     */
    void updateReceiveDetailByTaskId(UpdateReceiveTaskPojo updateReceiveTaskPojo);

    /**
     * 点收详情更新
     * @param saveReceiveDetailPojo
     */
    void updateReceiveDetailTask(SaveReceiveDetailPojo saveReceiveDetailPojo);

    /***
     * @Discription: 批量更新点收任务字表
     * @param param
     * @return void
     * @Author: zhanglei on 2021/6/21 18:18
     */
    void updateReceiveDetailTasks(List<Map<String,Object>> param);




    /***
     * @Discription: 点收区域列表数据查询
     * @param param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zhanglei on 2021/6/16 16:22
     */
    List<Map<String,Object>> listArea(@Param("areaType")String areaType,@Param("list")List<Map<String,Object>> param);

    /***
     * @Discription: 物料序列号保存
     * @param param
     * @return void
     * @Author: zhanglei on 2021/6/17 11:15
     */
    void saveMaterialSerial(List<Map<String,Object>> param);

    /***
     * @Discription: 异常物料信息保存
     * @param param
     * @return void
     * @Author: zhanglei on 2021/6/17 13:38
     */
    void saveAbnormalMaterialInfo(Map<String,Object> param);

    /**
     * @Description : 点收详情列表查询
     * @Author : leizhang
     * @Date 4:14 下午 2021/6/19
     * @param param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> listReceiveRecordDetail(Map<String, Object> param);

    /***
     * @Discription: 订单信息获取
     * @param param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zhanglei on 2021/7/1 10:42
     */
    List<Map<String,Object>> getReceiveRecordDetailAllInfo(Map<String,Object> param);

    /***
     * @Discription: 移库质检区信息查询
     * @param param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zhanglei on 2021/7/2 14:27
     */
    List<Map<String, Object>> listReceiveCarrierInfo(Map<String, Object> param);

    /**
     * 新增上架任务
     * @param upperFrameData1
     */
    int AddUpperFrameData(UpperFrameData upperFrameData1);

    /**
     * 新增上架仓位分配表
     * @param addUpperPositionPojo
     * @return
     */
    int addUpperPositionPojo(List<UpperPositionPojo> addUpperPositionPojo);
}

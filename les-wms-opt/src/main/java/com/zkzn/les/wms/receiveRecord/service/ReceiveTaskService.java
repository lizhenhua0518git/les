package com.zkzn.les.wms.receiveRecord.service;

import com.zkzn.les.wms.receiveRecord.pojo.SaveReceiveDetailPojo;
import com.zkzn.les.wms.receiveRecord.pojo.UpdateReceiveTaskPojo;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ReceiveTaskService
 * @Description 点收任务服务
 * @Author zhanglei
 * Date 2021/6/15 14:16
 * @Version 1.0
 **/
public interface ReceiveTaskService {

    /***
     * @Discription: 点收主表数据查询
     * @param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zhanglei on 2021/6/15 14:59
     */
    List<Map<String,Object>> listReceiveRecord(Map<String,Object> param) throws Exception;

    /**
     * 开始点收
     * @param updateReceiveTaskPojo
     */
    void saveReceiveInfoStart(UpdateReceiveTaskPojo updateReceiveTaskPojo);

    /**
     * 点收详情保存
     * @param saveReceiveDetailPojo
     */
    void saveReceiveRecordInfo(SaveReceiveDetailPojo saveReceiveDetailPojo);

    /**
     * 点收结束信息保存
     * @param updateReceiveTaskPojo
     */
    void saveReceiveInfoEnd(UpdateReceiveTaskPojo updateReceiveTaskPojo);

    /**
     * @Description : 点收详情列表查询
     * @Author : leizhang
     * @Date 4:08 下午 2021/6/19
     * @param param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> listReceiveRecordDetail(Map<String, Object> param);

    /***
     * @Discription: 移库质检区
     * @param param
     * @return void
     * @Author: zhanglei on 2021/6/21 15:57
     */
    void saveInspectionInfo(Map<String, Object> param) throws Exception;

    /***
     * @Discription: 移库质检区列表信息查询
     * @param param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zhanglei on 2021/7/2 14:26
     */
    List<Map<String, Object>> listReceiveCarrierInfo(Map<String, Object> param);
}

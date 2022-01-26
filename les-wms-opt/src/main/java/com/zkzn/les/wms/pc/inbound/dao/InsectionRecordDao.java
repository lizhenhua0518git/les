package com.zkzn.les.wms.pc.inbound.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ClassName InsectionRecordDao
 * @Description 质检记录查询pc端
 * @Author 刘松山
 * @date 2021/6/23 18:31
 **/
@Mapper
public interface InsectionRecordDao {
    /***
     * @Discription: 质检记录数据查询,查询状态是未质检，质检中,质检审核,质检完成的质检信息
     * @param param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zhanglei on 2020/9/5 16:16
     */
    List<Map<String,Object>> listReceiveDetailRecordOfInSpect(Map<String,Object> param);

}

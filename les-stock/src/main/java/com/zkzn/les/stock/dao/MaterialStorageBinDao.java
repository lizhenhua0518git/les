package com.zkzn.les.stock.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName MaterialStorageBinDao
 * @Author zhanglei
 * Date 2021/7/21 14:13
 * @Version 1.0
 **/
@Mapper
public interface MaterialStorageBinDao {
    /***
     * @Discription: 库存状态改变
     * @param changLists
     * @param changStatus
     * @return void
     * @Author: zhanglei on 2020/12/9 13:29
     */
    void updateMaterialStorageBinStatus(@Param("list") List<Map<String, Object>> changLists, @Param("changStatus") String changStatus);

    /***
     * @Discription: 库存状态修改记录保存
     * @param requestList
     * @param changStatus
     * @return void
     * @Author: zhanglei on 2021/7/21 14:16
     */
    void saveBinStorageBinRecord(@Param("list") List<Map<String,Object>> requestList,@Param("changStatus") String changStatus);
}

package com.zkzn.les.wms.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ClassName PlatTransferTaskDao
 * @Description 拆盘任务
 * @Author zhanglei
 * Date 2021/6/22 16:58
 * @Version 1.0
 **/
@Mapper
public interface PlatTransferTaskDao {
    /***
     * @Discription: 拆盘任务主表查询
     * @param param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zhanglei on 2021/6/22 17:00
     */
    List<Map<String,Object>> listPlatTransferInfos(Map<String,Object> param);

    /***
     * @Discription: 拆盘任务主表查询
     * @param param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zhanglei on 2021/6/22 17:00
     */
    List<Map<String,Object>> listPlatTransferDetailInfos(Map<String,Object> param);

    /***
     * @Discription: 拆盘信息查询
     * @param param
     * @return void
     * @Author: zhanglei on 2021/6/22 16:59
     */
    int savePlatTransferInfo(Map<String,Object> param);
    
    /***
     * @Discription: 拆盘子表任务保存
     * @param params
     * @return void
     * @Author: zhanglei on 2021/6/22 17:06
     */
    void savePlatTransferDetailInfos(Map<String,Object> params);

    /***
     * @Discription: 更新拆盘子表信息
     * @param params
     * @return void
     * @Author: zhanglei on 2021/6/24 10:09
     */
    void updatePlatTransferDetailInfo(Map<String,Object> params);

    /***
     * @Discription: 更新拆盘子表信息
     * @param params
     * @return void
     * @Author: zhanglei on 2021/6/24 10:09
     */
    void updatePlatTransferInfo(Map<String,Object> params);
}

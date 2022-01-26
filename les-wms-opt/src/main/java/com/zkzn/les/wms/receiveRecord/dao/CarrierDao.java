package com.zkzn.les.wms.receiveRecord.dao;

import com.zkzn.les.wms.receiveRecord.pojo.ReceiveCarrierPojo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CarrierDao
 * @Description 载具dao
 * @Author zhanglei
 * Date 2021/6/16 17:42
 * @Version 1.0
 **/
@Mapper
public interface CarrierDao {
    /***
     * @Discription: 载具信息列表查询
     * @param param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zhanglei on 2021/6/16 17:41
     */
    List<Map<String,Object>> listCarrier(List<Map<String,Object>> param);

    /**
     * 保存物料载具信息
     * @param param
     */
    void saveCarrierInfo(List<ReceiveCarrierPojo> param);

    /***
     * @Discription: 更新原载具物料信息
     * @param param
     * @return void
     * @Author: zhanglei on 2021/6/24 10:49
     */
    void updateOldCarrierInfo(Map<String,Object> param);

    /***
     * @Discription: 物料载具表查询
     * @param param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @Author: zhanglei on 2021/6/24 11:15
     */
    Map<String,Object> listReceiveCarrierInfo(Map<String,Object> param);

    /***
     * @Discription: 更新原载具物料信息
     * @param param
     * @return void
     * @Author: zhanglei on 2021/6/24 10:49
     */
    void updateNewCarrierInfo(Map<String,Object> param);
}

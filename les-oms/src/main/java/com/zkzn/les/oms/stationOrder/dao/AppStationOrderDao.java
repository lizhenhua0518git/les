package com.zkzn.les.oms.stationOrder.dao;

import com.zkzn.les.oms.stationOrder.pojo.AppShipmentTaskDetailPojo;
import com.zkzn.les.oms.stationOrder.pojo.AppShipmentTaskPojo;
import com.zkzn.les.oms.stationOrder.pojo.UpdateShipmentTaskDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName AppStationOrderDao
 * @Description TODO
 * @Author zhanglei
 * Date 2020/9/16 15:59
 * @Version 1.0
 **/
@Mapper
public interface AppStationOrderDao {

    /**
     * 查询待下架列表
     * @param appShipmentTaskPojo
     * @return
     */
    List<AppShipmentTaskPojo> findStationOrder(AppShipmentTaskPojo appShipmentTaskPojo);

    /**
     * 查询待下架详情
     * @param shipmentTaskId
     * @return
     */
    List<AppShipmentTaskDetailPojo> findMaterialById(@Param("shipmentTaskId") Integer shipmentTaskId,@Param("shipmentStatus") Integer shipmentStatus);

    /**
     * 下架完成操作
     * @param updateShipmentTaskDetail
     */
    void updateStationOrderDetail(UpdateShipmentTaskDetail updateShipmentTaskDetail);

    /**
     * 查询当前下架任务详情是否都已下架完成
     * @param shipmentTaskId
     * @return
     */
    Integer getShipmentStatus(@Param("shipmentTaskId") Integer shipmentTaskId);

    /**
     * 修改下架任务节点状态
     * @param shipmentTaskId
     * @return
     */
    int updateShipmentTaskStatus(@Param("shipmentTaskId") Integer shipmentTaskId,@Param("taskStatus") Integer taskStatus);

}

package com.zkzn.les.oms.stationOrder.service;

import com.zkzn.les.oms.stationOrder.pojo.AppShipmentTaskDetailPojo;
import com.zkzn.les.oms.stationOrder.pojo.AppShipmentTaskPojo;
import com.zkzn.les.oms.stationOrder.pojo.UpdateShipmentTaskDetail;

import java.util.List;

/**
 * APP下架
 */
public interface AppStationOrderService {

    /**
     * 待下架列表查询
     * @param appShipmentTaskPojo
     * @return
     */
    List<AppShipmentTaskPojo> findStationOrder(AppShipmentTaskPojo appShipmentTaskPojo);

    /**
     * 查询下架任务详情
     * @param shipmentTaskId
     * @return
     */
    List<AppShipmentTaskDetailPojo> findMaterialById(Integer shipmentTaskId);


    /**
     * 下架完成操作
     * @param updateShipmentTaskDetail
     */
    void updateStationOrder(UpdateShipmentTaskDetail updateShipmentTaskDetail);

}

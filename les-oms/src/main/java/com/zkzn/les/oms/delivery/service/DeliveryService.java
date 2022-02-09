package com.zkzn.les.oms.delivery.service;

import com.zkzn.les.oms.delivery.pojo.DeliveryPojo;
import com.zkzn.les.oms.stationOrder.pojo.AppShipmentTaskDetailPojo;
import com.zkzn.les.oms.stationOrder.pojo.AppShipmentTaskPojo;

import java.util.List;

/**
 * @ProjectName: lesGroup
 * @Package: com.zkzn.les.wms.app.service
 * @ClassName: DeliveryService
 * @Author: 胡志明
 * @Description:
 * @Date: 2020/9/22 14:47
 */
public interface DeliveryService {

    /***
     * app交接列表
     * @param deliveryPojo
     * @return
     */
    List<AppShipmentTaskPojo> listAssembleCarrier(AppShipmentTaskPojo deliveryPojo);

    /**
     * app交接详情
     * @param shipmentTaskId
     * @return
     */
    List<AppShipmentTaskDetailPojo> listDeliveryMaterialPojo(Integer shipmentTaskId);

    /**
     * app交接提交
     * @param deliveryPojo
     */
    void submitDelivery(DeliveryPojo deliveryPojo);

}

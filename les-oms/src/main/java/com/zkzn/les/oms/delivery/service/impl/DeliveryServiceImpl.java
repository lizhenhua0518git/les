package com.zkzn.les.oms.delivery.service.impl;

import com.zkzn.les.oms.delivery.dao.DeliveryDao;
import com.zkzn.les.oms.delivery.pojo.DeliveryPojo;
import com.zkzn.les.oms.delivery.service.DeliveryService;
import com.zkzn.les.oms.fegin.StockFeignService;
import com.zkzn.les.oms.stationOrder.dao.AppStationOrderDao;
import com.zkzn.les.oms.stationOrder.pojo.AppShipmentTaskDetailPojo;
import com.zkzn.les.oms.stationOrder.pojo.AppShipmentTaskPojo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: lesGroup
 * @Package: com.zkzn.les.wms.app.service.impl
 * @ClassName: DeliveryServiceImpl
 * @Author: 胡志明
 * @Description:
 * @Date: 2020/9/22 14:48
 */
@Service
@Slf4j
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryDao deliveryDao;
    @Autowired
    private AppStationOrderDao appStationOrderDao;
    @Autowired
    private StockFeignService stockFeignService;

    @Override
    public List<AppShipmentTaskPojo> listAssembleCarrier(AppShipmentTaskPojo appShipmentTaskPojo) {
        appShipmentTaskPojo.setTaskStatus(2);
        List<AppShipmentTaskPojo> list = appStationOrderDao.findStationOrder(appShipmentTaskPojo);
        return list;
    }

    @Override
    public List<AppShipmentTaskDetailPojo> listDeliveryMaterialPojo(Integer shipmentTaskId) {
        List<AppShipmentTaskDetailPojo> appShipmentTaskDetailPojoList = appStationOrderDao.findMaterialById(shipmentTaskId,1);
        return appShipmentTaskDetailPojoList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void submitDelivery(DeliveryPojo deliveryPojo) {
        deliveryPojo.setUpdateTime(new Date());
        deliveryPojo.setConnectTime(new Date());
        deliveryPojo.setTaskStatus(3);
        deliveryDao.updateAssembleCarrier(deliveryPojo);
        List<AppShipmentTaskDetailPojo> appShipmentTaskDetailPojoList = deliveryDao.getReducesMaterialInStock(deliveryPojo.getShipmentTaskId());
        stockFeignService.reducesMaterialInStock(appShipmentTaskDetailPojoList);
    }
}

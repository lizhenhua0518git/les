package com.zkzn.les.oms.stationOrder.service.impl;

import com.zkzn.les.oms.fegin.StockFeignService;
import com.zkzn.les.oms.stationOrder.dao.AppStationOrderDao;
import com.zkzn.les.oms.stationOrder.pojo.AppShipmentTaskDetailPojo;
import com.zkzn.les.oms.stationOrder.pojo.AppShipmentTaskPojo;
import com.zkzn.les.oms.stationOrder.pojo.UpdateShipmentTaskDetail;
import com.zkzn.les.oms.stationOrder.service.AppStationOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName AppStationOrderServiceImpl
 * @Description TODO
 * @Author zhanglei
 * Date 2020/9/16 15:41
 * @Version 1.0
 **/
@Service
public class AppStationOrderServiceImpl implements AppStationOrderService {

    @Autowired
    private AppStationOrderDao appStationOrderDao;

    @Autowired
    private StockFeignService stockFeignService;

    @Override
    public List<AppShipmentTaskPojo> findStationOrder(AppShipmentTaskPojo appShipmentTaskPojo) {
        appShipmentTaskPojo.setTaskStatus(1);
        List<AppShipmentTaskPojo> appShipmentTaskPojoList = appStationOrderDao.findStationOrder(appShipmentTaskPojo);
        return appShipmentTaskPojoList;
    }

    @Override
    public List<AppShipmentTaskDetailPojo> findMaterialById(Integer shipmentTaskId) {
        List<AppShipmentTaskDetailPojo> appShipmentTaskDetailPojoList = appStationOrderDao.findMaterialById(shipmentTaskId,0);
        return appShipmentTaskDetailPojoList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStationOrder(UpdateShipmentTaskDetail updateShipmentTaskDetail) {
        updateShipmentTaskDetail.setUpdateTime(new Date());
        updateShipmentTaskDetail.setShipmentStatus(1);
        appStationOrderDao.updateStationOrderDetail(updateShipmentTaskDetail);
        Integer sumNumber = appStationOrderDao.getShipmentStatus(updateShipmentTaskDetail.getShipmentTaskId());
        if (sumNumber == 0){
            appStationOrderDao.updateShipmentTaskStatus(updateShipmentTaskDetail.getShipmentTaskId(),2);
        }
        stockFeignService.minusStorageBinStock(updateShipmentTaskDetail);
    }

}

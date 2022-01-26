package com.zkzn.les.oms.delivery.dao;

import com.zkzn.les.oms.delivery.pojo.DeliveryPojo;
import com.zkzn.les.oms.stationOrder.pojo.AppShipmentTaskDetailPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @ProjectName: lesGroup
 * @Package: com.zkzn.les.wms.app.dao
 * @ClassName: DeliveryDao
 * @Author: 胡志明
 * @Description:
 * @Date: 2020/9/22 14:56
 */
@Mapper
public interface DeliveryDao {

    int updateAssembleCarrier(DeliveryPojo deliveryPojo);

    List<AppShipmentTaskDetailPojo> getReducesMaterialInStock(@Param("shipmentTaskId") Integer shipmentTaskId);

//    int updateStationOrderDetailStatus(UpdateAssemble updateAssemble);
//
//    int deleteStorageOrderDetail(@Param("stationOrderId") String stationOrderId);
//
//    int updateStorageOrderDetail(UpdateAssemble updateAssemble);
//
//    MesDeliverPojo findDeliverInfo(UpdateAssemble updateAssemble);
//
//    int updateStationOrderDetailStatusByHandler(List<Map<String,Object>> requestParam);
}

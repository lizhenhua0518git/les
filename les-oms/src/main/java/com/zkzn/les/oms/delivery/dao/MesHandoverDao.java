package com.zkzn.les.oms.delivery.dao;

import com.zkzn.les.oms.stationOrder.pojo.PcShipmentTaskPojo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 交接pc端
 */
@Mapper
public interface MesHandoverDao {

   /**
    * 交接列表
    * @param pcShipmentTaskPojo
    * @return
    */
   List<PcShipmentTaskPojo> listHandoverOrderList(PcShipmentTaskPojo pcShipmentTaskPojo);

   /**
    * 交接详情
    * @param pcShipmentTaskPojo
    * @return
    */
   List<PcShipmentTaskPojo> listHandoverMaterialList (PcShipmentTaskPojo pcShipmentTaskPojo);

}

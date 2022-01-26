package com.zkzn.les.oms.delivery.service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.oms.stationOrder.pojo.PcShipmentTaskPojo;

import java.util.List;

/**
 * @ClassName MesHandoverService
 * @Description
 * @Author 刘松山
 * @date 2021/5/22 16:30
 **/
public interface MesHandoverService {

    /**
     * 交接PC端列表
     * @param pcShipmentTaskPojo
     * @return
     */
    PageInfo<PcShipmentTaskPojo> listHandoverOrderList (PcShipmentTaskPojo pcShipmentTaskPojo);

    /**
     * 交接PC端详情
     * @param pcShipmentTaskPojo
     * @return
     */
    PageInfo<PcShipmentTaskPojo> listHandoverMaterialList (PcShipmentTaskPojo pcShipmentTaskPojo);

    /**
     * 导出交接物料信息数据
     *
     * @param param:查询参数
     * @return java.lang.String
     * @author Hush.
     * @since 2022/1/13 10:25
     */
    List<PcShipmentTaskPojo> listHandoverMaterial(PcShipmentTaskPojo param);
}

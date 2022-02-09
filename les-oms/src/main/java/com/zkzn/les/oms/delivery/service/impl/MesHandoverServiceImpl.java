package com.zkzn.les.oms.delivery.service.impl;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.common.util.page.PageUtil;
import com.zkzn.les.oms.delivery.dao.MesHandoverDao;
import com.zkzn.les.oms.delivery.service.MesHandoverService;
import com.zkzn.les.oms.stationOrder.pojo.PcShipmentTaskPojo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 交接pc端
 */
@Service
public class MesHandoverServiceImpl implements MesHandoverService {

    @Resource
    private MesHandoverDao mesHandoverDao;

    @Override
    public PageInfo<PcShipmentTaskPojo> listHandoverOrderList(PcShipmentTaskPojo pcShipmentTaskPojo) {
        PageUtil.setPageParam(pcShipmentTaskPojo);
        List<PcShipmentTaskPojo> list = mesHandoverDao.listHandoverOrderList(pcShipmentTaskPojo);
        PageInfo<PcShipmentTaskPojo> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public PageInfo<PcShipmentTaskPojo> listHandoverMaterialList(PcShipmentTaskPojo pcShipmentTaskPojo) {
        PageUtil.setPageParam(pcShipmentTaskPojo);
        List<PcShipmentTaskPojo> list = mesHandoverDao.listHandoverMaterialList(pcShipmentTaskPojo);
        PageInfo<PcShipmentTaskPojo> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public List<PcShipmentTaskPojo> listHandoverMaterial(PcShipmentTaskPojo param) {

        List<PcShipmentTaskPojo> list = mesHandoverDao.listHandoverMaterialList(param);
        list.forEach(one -> one.setStockDesc(one.getStockStatus() == 0 ? "合格" : "不合格"));
        return list;
    }
}

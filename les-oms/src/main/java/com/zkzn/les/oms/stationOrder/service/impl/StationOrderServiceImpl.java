package com.zkzn.les.oms.stationOrder.service.impl;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.common.util.page.PageUtil;
import com.zkzn.les.common.util.redis.RedisNoUtil;
import com.zkzn.les.oms.stationOrder.dao.StationOrderDao;
import com.zkzn.les.oms.stationOrder.pojo.OrderTaskDetailPojo;
import com.zkzn.les.oms.stationOrder.pojo.OrderTaskPojo;
import com.zkzn.les.oms.stationOrder.pojo.PcShipmentTaskPojo;
import com.zkzn.les.oms.stationOrder.pojo.ShipmentTaskData;
import com.zkzn.les.oms.stationOrder.service.StationOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
@Service
public class StationOrderServiceImpl implements StationOrderService {

	@Autowired
	private StationOrderDao stationOrderDao;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;


	public PageInfo<PcShipmentTaskPojo> listStationOrder(PcShipmentTaskPojo pcShipmentTaskPojo) {
		PageUtil.setPageParam(pcShipmentTaskPojo);
		List<PcShipmentTaskPojo> list = stationOrderDao.listStationOrder(pcShipmentTaskPojo);
		PageInfo<PcShipmentTaskPojo> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public List<OrderTaskPojo> listBOMMainList(OrderTaskPojo orderTaskPojo) {
		List<OrderTaskPojo> orderTaskPojoList = stationOrderDao.listBOMMainList(orderTaskPojo);
		return orderTaskPojoList;
	}

	@Override
	@Transactional
	public int matchingInventory(Integer orderTaskId, Integer userId, String currentUserName) {
		List<ShipmentTaskData> shipmentTaskDataList = stationOrderDao.getShipmentTaskData(orderTaskId);
		if (shipmentTaskDataList.size()>0){
			List<ShipmentTaskData> addShipmentDetail = new ArrayList<>();
			Map<Integer,List<ShipmentTaskData>> addDetailMap = new HashMap<>();
			Map<Integer,Object> removeMap = new HashMap<>();
			ShipmentTaskData addShipmentTaskData = shipmentTaskDataList.get(0);
			addShipmentTaskData.setCreaterUserId(userId);
			stationOrderDao.addShipmentTask(addShipmentTaskData);
			for (int i = 0; i < shipmentTaskDataList.size(); i++) {
				ShipmentTaskData shipmentTaskData = shipmentTaskDataList.get(i);
				shipmentTaskData.setShipmentTaskId(addShipmentTaskData.getShipmentTaskId());
				shipmentTaskData.setCreaterUserId(userId);
				Integer orderTaskDetailId = shipmentTaskData.getOrderTaskDetailId();
				double sendNumber = shipmentTaskData.getSendNumber();//出库数量
				double stockCount = shipmentTaskData.getStockCount();//库存数量
				if (!removeMap.containsKey(orderTaskDetailId)){
					if (sendNumber == stockCount){
						addShipmentDetail.add(shipmentTaskData);
						removeMap.put(orderTaskDetailId,null);
						continue;
					}
					if (addDetailMap.containsKey(orderTaskDetailId)){
						List<ShipmentTaskData> shipmentTaskDataList1 = addDetailMap.get(orderTaskDetailId);
						shipmentTaskDataList1.add(shipmentTaskData);
					}else {
						List<ShipmentTaskData> addList = new ArrayList<>();
						addList.add(shipmentTaskData);
						addDetailMap.put(orderTaskDetailId,addList);
					}
				}
			}
			for (Map.Entry<Integer,List<ShipmentTaskData>> m : addDetailMap.entrySet()) {
				Integer key = m.getKey();
				if (!removeMap.containsKey(key)){
					List<ShipmentTaskData> value = m.getValue();
					ShipmentTaskData shipmentTaskData1 = value.get(0);
					double sendNumber = shipmentTaskData1.getSendNumber();//出库数量
					for (int i = 0; i < value.size(); i++) {
						if (sendNumber > 0){
							ShipmentTaskData shipmentTaskData = value.get(i);
							double stockCount = shipmentTaskData.getStockCount();
							if (sendNumber >= stockCount){
								sendNumber = sendNumber-stockCount;
								addShipmentDetail.add(shipmentTaskData);
							}
							if (sendNumber < stockCount){
								shipmentTaskData.setStockCount(sendNumber);
								sendNumber = 0D;
								addShipmentDetail.add(shipmentTaskData);
							}
						}else {
							break;
						}
					}
				}
			}
			if (addShipmentDetail.size()>0){
				stationOrderDao.addShipmentTaskDetail(addShipmentDetail);
				for (int i = 0; i < addShipmentDetail.size(); i++) {
					stationOrderDao.updatePreUseCount(addShipmentDetail.get(i));
				}
			}
			stationOrderDao.updateOrderType(orderTaskId);
		}
		return 0;
	}

	@Override
	public int bommAdd(OrderTaskPojo orderTaskPojo) {
		Map<String,Object> operate = stationOrderDao.getClientManage(orderTaskPojo.getClientName());
		orderTaskPojo.setOperateUserId(Integer.parseInt(operate.get("operateUserId")+""));
		orderTaskPojo.setOperateUserName(operate.get("operateUserName")+"");
		String seqNo = RedisNoUtil.getSeqNo("CK",redisTemplate);
		orderTaskPojo.setOrderCode(seqNo);
		int i = stationOrderDao.bommAdd(orderTaskPojo);
		return i;
	}

	@Override
	public int deleteOrderTask(List<Integer> orderTaskIds) {
		int i = stationOrderDao.deleteOrderTask(orderTaskIds);
		return i;
	}

	@Override
	public List<OrderTaskDetailPojo> listBOMDetailList(OrderTaskDetailPojo orderTaskDetailPojo) {
		List<OrderTaskDetailPojo> orderTaskDetailPojoList = stationOrderDao.listBOMDetailList(orderTaskDetailPojo);
		return orderTaskDetailPojoList;
	}

	@Override
	public int orderTaskDetailAdd(OrderTaskDetailPojo orderTaskDetailPojo) {
		int i = stationOrderDao.orderTaskDetailAdd(orderTaskDetailPojo);
		return i;
	}

	@Override
	public int deleteOrderTaskDetail(List<Integer> orderTaskDetailIds) {
		int i = stationOrderDao.deleteOrderTaskDetail(orderTaskDetailIds);
		return i;
	}

}

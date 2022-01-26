package com.zkzn.les.oms.stationOrder.service.impl;

import java.util.*;

import com.alibaba.druid.util.StringUtils;
import com.zkzn.les.common.util.page.PageUtil;
import com.zkzn.les.common.util.redis.RedisNoUtil;
import com.zkzn.les.oms.pojo.*;
import com.zkzn.les.oms.stationOrder.pojo.OrderTaskDetailPojo;
import com.zkzn.les.oms.stationOrder.pojo.OrderTaskPojo;
import com.zkzn.les.oms.stationOrder.pojo.PcShipmentTaskPojo;
import com.zkzn.les.oms.stationOrder.pojo.ShipmentTaskData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.oms.stationOrder.dao.StationOrderDao;
import com.zkzn.les.oms.dao.StationOrderDetailDao;
import com.zkzn.les.oms.stationOrder.service.StationOrderService;

@SuppressWarnings("unchecked")
@Service
public class StationOrderServiceImpl implements StationOrderService {
    private static Logger LOGGER = LoggerFactory.getLogger(StationOrderServiceImpl.class);
	@Autowired
	private StationOrderDao stationOrderDao;
	@Autowired
	private StationOrderDetailDao stationOrderDetailDao;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;


	public PageInfo<PcShipmentTaskPojo> listStationOrder(PcShipmentTaskPojo pcShipmentTaskPojo) {
		PageUtil.setPageParam(pcShipmentTaskPojo);
		List<PcShipmentTaskPojo> list = stationOrderDao.listStationOrder(pcShipmentTaskPojo);
		PageInfo<PcShipmentTaskPojo> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	/**
	 * @Discription: 工位订单下发
	 * @return void
	 * @Author: zhanglei on 2020/9/9 13:50
	 */
	@Transactional(rollbackFor = Exception.class)
	public void taskStationOrder(List<String> ids)throws Exception{
		//参数校验
		if(ids == null || ids.isEmpty()) {
			throw new Exception("参数异常");
		}
		List<StationOrder> stationOrders = stationOrderDao.selectAllById(ids);
		//创建MutliMap 对象 一键多值 对应 key为工位 值为工位订单对象
		//Multimap<String,StationOrder> multimap = ArrayListMultimap.create();
		String stationCode = null;
		for (StationOrder stationOrder : stationOrders) {
			if ((stationCode=stationOrder.getStationCode()) != null && !StringUtils.isEmpty(stationCode)) {
				//multimap.put(stationCode,stationOrder);
			}
		}
		//更新 t_station_order_detail t_station_order 新增T_WAVE数据
		StationWave wave = new StationWave();
		StationOrder stationOrder = null;
		try {
//			for (String key : multimap.keys()) {
//				List<StationOrder> stationOrderList = (List) multimap.get(key);
//				//新增波次表数据
//				stationOrder = stationOrderList.get(0);
//				wave.setLineCode(stationOrder.getLineCode());
//				wave.setStationCode(stationOrder.getStationCode());
//				wave.setWareHouseCode(stationOrder.getWarehouseCode());
//				wave.setWareHouseName(stationOrder.getWarehouseName());
//				wave.setWareShopCode(stationOrder.getWorkshopCode());
//				wave.setCreateTime(new Date());
//				//wave.setWaveCode(OrderCodeUtil.createWaveCode());
//				//uuid 作为wave波次表的唯一id
//				String waveId = "";
//				wave.setId(waveId);
//				stationOrderDao.saveWave(wave);
//				for (StationOrder order : stationOrderList) {
//					order.setWaveId(waveId);
//				}
//				//修改波次id
//				stationOrderDao.updateOrder(stationOrderList);
//			}
			//修改订单工位详情状态
			stationOrderDao.update(ids);
		}catch (Exception e){
			LOGGER.error("拣料派发：%s",e.getMessage(),e);
			//异常抛出 事务回滚
			throw  new Exception();
		}
	}


	/***
	 * @Discription: 工位订单查询
	 * @param stationOrderWithDetail
	 * @Author: zhanglei on 2020/9/9 17:01
	 */
	public List<StationOrderWithDetail> findAllOrders(StationOrderWithDetail stationOrderWithDetail) {
		String requiredTimeStr = stationOrderWithDetail.getRequiredTimeStr();//创建时间
		if (!"".equals(requiredTimeStr) && requiredTimeStr != null){
			String[] split = requiredTimeStr.split(" - ");
			stationOrderWithDetail.setStartRequiredTime(split[0]);
			stationOrderWithDetail.setEndRequiredTime(split[1]);
		}
		//List<StationOrderWithDetail> list = stationOrderDao.listStationOrder(stationOrderWithDetail);
		return null;
	}

	/**
	 * @Discription: 退货采购订单出库任务派发
	 * @Author:luozhihong
	 * @date:2020年12月2日
	 * @param ids 采购订单明细ID集合
	 * @return void
	 */
	@Transactional(rollbackFor = Exception.class)
	public String savePurchaseBillRefundStationOrder(List<String> ids)throws Exception{
		//参数校验
		if(ids == null || ids.isEmpty()) {
			return "参数异常";
			//throw new Exception("参数异常");
		}
		List<PurchaseBillDetail> purchaseBillDetails = stationOrderDao.listPurchaseBillDetailById(ids);
		Map<String,PurchaseBillDetail> returnMap = new HashMap<>();
		for (PurchaseBillDetail purchaseBillDetail : purchaseBillDetails){
			String itemNo = purchaseBillDetail.getItemNo();
			String materialCode = purchaseBillDetail.getMaterialCode();
			Double enableCount = purchaseBillDetail.getEnableCount();
			String storageLocation = purchaseBillDetail.getStorageLocation();
			String mapKey = itemNo+materialCode+storageLocation;
			if (returnMap.containsKey(mapKey)){
				PurchaseBillDetail pBillDetail = returnMap.get(mapKey);
				pBillDetail.setEnableCount(pBillDetail.getEnableCount()+enableCount);
				returnMap.put(mapKey,pBillDetail);
			}else {
				returnMap.put(mapKey,purchaseBillDetail);
			}
		}
		List<PurchaseBillDetail> purchaseBillDetailsData = new ArrayList<>();
		for (Map.Entry<String,PurchaseBillDetail> m : returnMap.entrySet()) {
			PurchaseBillDetail value = m.getValue();
			Double enableCount = value.getEnableCount();//可用库存
			Double materialNum = value.getMaterialNum();//需求数量
			if (enableCount<materialNum){
				return value.getMaterialCode()+"库存不足";
			}else {
				purchaseBillDetailsData.add(value);
			}
		}

		List<StationOrder> stationOrderList = new ArrayList<>();
		List<StationOrderDetail> stationOrderDetailList = new ArrayList<>();
		StationOrder stationOrder = null;
		StationOrderDetail stationOrderDetail = null;
		Set<String>purchaseBillRefundTaskCodes = new HashSet<>();
		//退货采购订单任务号
		String purchaseBillRefundTaskCode = "";//OrderCodeUtil.crateTaskcodeOfPurchaseBillRefund();
		String stationOrderId= "";//UuidUtil.getUUID();
		//try {

		for (PurchaseBillDetail purchaseBillDetail : purchaseBillDetailsData) {
			if (!purchaseBillRefundTaskCodes.contains(purchaseBillRefundTaskCode)) {
				stationOrder = new StationOrder();
				stationOrder.setId(stationOrderId);
				fillStationOrder(stationOrder, purchaseBillDetail,purchaseBillRefundTaskCode);
				purchaseBillRefundTaskCodes.add(purchaseBillRefundTaskCode);
				stationOrderList.add(stationOrder);
			}
			stationOrderDetail = new StationOrderDetail();
			stationOrderDetail.setStationOrderId(stationOrderId);
			fillStationOrderDetail(stationOrderDetail, purchaseBillDetail);
			stationOrderDetailList.add(stationOrderDetail);
		}
		//保存任务主表
		if (stationOrderList.size() > 0) {
			stationOrderDao.saveStationOrder(stationOrderList);
		}
		//保存任务详情表
		if (stationOrderDetailList.size() > 0) {
			stationOrderDetailDao.saveStationOrderDetail(stationOrderDetailList);
		}
//		}catch (Exception e){
//			LOGGER.error("退货采购订单出库任务派发：%s",e.getMessage(),e);
//			//异常抛出 事务回滚
//			throw  new Exception();
//		}
		return null;
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

	/**
	 * @param stationOrder
	 * @param purchaseBillDetail
	 * @param purchaseBillRefundTaskCode
	 * @Author:luozhihong
	 * @date:2020年12月2日
	 * @Description:填充工位订单主表更新字段信息
	 */
	private void fillStationOrder(StationOrder stationOrder, PurchaseBillDetail purchaseBillDetail,String purchaseBillRefundTaskCode) {
		stationOrder.setCreateTime(new Date());
		//设置退货采购订单任务号
		stationOrder.setOrderCode(purchaseBillRefundTaskCode);
		stationOrder.setFactory(purchaseBillDetail.getFactory());
		//设置订单类型为退货采购订单
		stationOrder.setOrderType(5);
		stationOrder.setBusiType(9);
		Map<String, Object> resultMap = null;// SapStorageLocationTransfrom.lesToSapStorageLocation(redisTemplate,purchaseBillDetail.getStorageLocation());
		if (resultMap != null) {
			stationOrder.setWarehouseCode((String) resultMap.get("warehouseCode"));
			stationOrder.setWarehouseName((String) resultMap.get("warehouseName"));
		}
	}

	/**
	 * @param stationOrderDetail
	 * @param purchaseBillDetail
	 * @Author:luozhihong
	 * @date:2020年12月2日
	 * @Description:填充工位订单详情信息
	 */
	private void fillStationOrderDetail(StationOrderDetail stationOrderDetail, PurchaseBillDetail purchaseBillDetail) {
		//stationOrderDetail.setId(UuidUtil.getUUID());
		stationOrderDetail.setCreateTime(new Date());
		stationOrderDetail.setMaterialCode(purchaseBillDetail.getMaterialCode());
		stationOrderDetail.setMaterialUnit(purchaseBillDetail.getMaterialUnit());
		stationOrderDetail.setPickNum(purchaseBillDetail.getMaterialNum());
		stationOrderDetail.setStationLocation(purchaseBillDetail.getStorageLocation());
		//设置退货采购订单详情ID
		stationOrderDetail.setProcessDetialId(purchaseBillDetail.getId());
		//待拣配
		stationOrderDetail.setStatus(5);
		//设置物料描述
		stationOrderDetail.setMaterialDesc(purchaseBillDetail.getMaterialDesc());
	}
}

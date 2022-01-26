package com.zkzn.les.oms.service.impl;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.common.util.json.JsonUtil;
import com.zkzn.les.common.util.page.PageUtil;
import com.zkzn.les.oms.dao.ProcessOrderDetailDao;
import com.zkzn.les.oms.stationOrder.dao.StationOrderDao;
import com.zkzn.les.oms.dao.StationOrderDetailDao;
import com.zkzn.les.oms.pojo.ProcessOrderDetail;
import com.zkzn.les.oms.pojo.StationOrder;
import com.zkzn.les.oms.pojo.StationOrderDetail;
import com.zkzn.les.oms.pojo.StorageOrderDetail;
import com.zkzn.les.oms.service.ProcessOrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Slf4j
public class ProcessOrderDetailServiceImpl implements ProcessOrderDetailService {

	@Autowired
	private ProcessOrderDetailDao processOrderDetailDao;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private StationOrderDao stationOrderDao;
	@Autowired
	private StationOrderDetailDao stationOrderDetailDao;

	private static Logger LOGGER = LoggerFactory.getLogger(ProcessOrderDetailServiceImpl.class);
	/**
	 *
	 * @param processOrderDetail
	 * @return
	 * @Author:luozhihong
	 * @date:2020年9月2日
	 * @Description:分页查询物料需求清单
	 */
	public PageInfo<ProcessOrderDetail> listProcessOrderDetail(ProcessOrderDetail processOrderDetail) {

		PageUtil.setPageParam(processOrderDetail);
		reAssembleParam(processOrderDetail);
		List<ProcessOrderDetail> list = processOrderDetailDao.listProcessOrderDetail(processOrderDetail);

		for(ProcessOrderDetail tempprocessOrderDetail :list){
			if(tempprocessOrderDetail.getMatchNum()==null && tempprocessOrderDetail.getSurplusNum()==0){
				tempprocessOrderDetail.setSurplusNum(tempprocessOrderDetail.getRequiredNum());
			}
		}
		PageInfo<ProcessOrderDetail> pageInfo = new PageInfo<ProcessOrderDetail>(list);
		return pageInfo;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean  matchStorageStock(Map<String, Object> param) {
	    log.info("================匹配库存参数："+param);
		//是否匹配成功，默认成功
		boolean flag=true;
		List<Map<String, Object>> allMatchList = new ArrayList<>();
		// 存放订单详情中 剩余数量---以订单明细表id为key 待分配数量为value
		Map<String, Double> surplusRequired = new HashMap<String, Double>();

		// 存放每个仓位重剩余数量---以仓位库存表id为key 待分配数量为value
		Map<String, Double> surplusStock = new HashMap<String, Double>();

		// 存放每个物料从某一个仓位上下架的数量---以仓位表id+订单明细表id 为key 下架总数
		Map<String, Double> detailPickNumMap = new HashMap<String, Double>();
		List<Map<String, Object>> tempList = null;

		List<Map<String, Object>> orderStockList = processOrderDetailDao.listOrderStock(param);
		tempList = stockDataHandle(orderStockList, surplusRequired, surplusStock,detailPickNumMap);
		allMatchList.addAll(tempList);

		List<Map<String, Object>> customStockList = processOrderDetailDao.listCustomStock(param);
		tempList = stockDataHandle(customStockList, surplusRequired, surplusStock,detailPickNumMap);
		allMatchList.addAll(tempList);

		List<Map<String, Object>> normalStockList = processOrderDetailDao.listNormalStock(param);
		tempList = stockDataHandle(normalStockList, surplusRequired, surplusStock,detailPickNumMap);
		allMatchList.addAll(tempList);
		LOGGER.info("匹配库存信息:[{}]",tempList.toString());
		if(allMatchList.size()==0){
			flag=false;
		}
		dataHandle(allMatchList, surplusStock, surplusRequired,detailPickNumMap);
		return flag;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor = Exception.class)
	public boolean matchStorageStockByProcessDetail(List<Map<String, Object>> requestMap) throws Exception {
		//是否匹配成功，默认成功
		boolean flag=true;
		List<Map<String, Object>> allMatchList = new ArrayList<>();
		// 存放订单详情中 剩余数量---以订单明细表id为key 待分配数量为value
		Map<String, Double> surplusRequired = new HashMap<String, Double>();

		// 存放每个仓位重剩余数量---以仓位库存表id为key 待分配数量为value
		Map<String, Double> surplusStock = new HashMap<String, Double>();

		// 存放每个物料从某一个仓位上下架的数量---以仓位表id+订单明细表id 为key 下架总数
		Map<String, Double> detailPickNumMap = new HashMap<String, Double>();
		List<Map<String, Object>> tempList = null;

		//查询满足条件的数据,根据processId匹配库存数量
		//TODO 扣减库存如果不在数据库层面维护，在内存中维护，在并发的情况下会出现库存数量扣减错误
		List<Map<String, Object>> normalStockList = processOrderDetailDao.listOfProcessIdStock(requestMap);
		//销售订单库存跟普通订单库并非共存
		if (normalStockList.isEmpty()) {
			normalStockList.addAll(processOrderDetailDao.listOfProcessIdStockByE(requestMap));
		}
		//下架数据量 过滤处理 接口传入的下架数据是可以进行修改的
		if (normalStockList.size() == 0) {
			return false;
		}
		 for (Map<String,Object> normalStock:normalStockList) {
		 	for (Map<String,Object> request:requestMap) {
		 		if (request.get("id").toString().equals(normalStock.get("podId"))) {
		 			//id相同修改下架数量
					normalStock.put("requiredNum",request.get("requireNum"));
				}
			}
		 }
		tempList = stockDataHandle(normalStockList, surplusRequired, surplusStock,detailPickNumMap);
		allMatchList.addAll(tempList);
		if(allMatchList.size()==0){
			flag=false;
		}
		dataHandle(allMatchList, surplusStock, surplusRequired,detailPickNumMap);
		return flag;
	}


	/**
	 * @param processOrderDetail
	 * @Author:luozhihong
	 * @date:2020年9月4日
	 * @Description:参数封装
	 */
	public void reAssembleParam(ProcessOrderDetail processOrderDetail) {
		List<String> storageLocations = new ArrayList<String>();
		String tempStorageLocation = null;
		String[] tempStorageLocations = null;
		List<String> stationCodes = new ArrayList<String>();
		String tempStationCode = null;
		String[] tempStationCodes = null;
		if (processOrderDetail.getStorageLocation() != null && processOrderDetail.getStorageLocation().length() > 0) {
			tempStorageLocation = processOrderDetail.getStorageLocation();
			tempStorageLocations = tempStorageLocation.split(";");
			for (int i = 0; i < tempStorageLocations.length; i++) {
				storageLocations.add(tempStorageLocations[i]);
			}

		}
		if (processOrderDetail.getStationCode() != null && processOrderDetail.getStationCode().length() > 0) {
			tempStationCode = processOrderDetail.getStationCode();
			tempStationCodes = tempStationCode.split(";");
			for (int i = 0; i < tempStationCodes.length; i++) {
				stationCodes.add(tempStationCodes[i]);
			}

		}
		processOrderDetail.setStorageLocations(storageLocations);
		processOrderDetail.setStationCodes(stationCodes);
	}

	/**
	 * @param data
	 *
	 * @param surplusRequired
	 *
	 * @param surplusStock
	 *
	 * @return
	 *
	 * @Author:wangzhou
	 *
	 * @date:2020年9月3日
	 *
	 * @Description:匹配库存
	 */
	private List<Map<String, Object>> stockDataHandle(List<Map<String, Object>> data,
			Map<String, Double> surplusRequired, Map<String, Double> surplusStock,
			Map<String, Double> detailPickNumMap) {

		Double requiredNum = null;
		Double stockNum = null;
		Double callNum = null;

		/*
		 * data.forEach(action -> {
		 *
		 * });
		 */
		List<Map<String, Object>> matchFinshList = new ArrayList<>();

		String podId = null;// 订单详情id
		String msbId = null;// 仓位库存id
		String podStorageId = null;//仓位表id+订单详情id
		for (Map<String, Object> map : data) {
			requiredNum = map.get("requiredNum") != null ? Double.valueOf("" + map.get("requiredNum")) : 0;// 需求数量
			stockNum = map.get("enableCount") != null ? Double.valueOf("" + map.get("enableCount")) : 0;// 可用数量
			callNum = map.get("callNum") != null ? Double.valueOf("" + map.get("callNum")) : 0;// 呼叫数量
			podId = (String) map.get("podId");
			msbId = (String) map.get("msbId");
			podStorageId = podId+map.get("storageId");
			if (surplusRequired.get(podId) == null) {
				surplusRequired.put(podId, requiredNum);
			} else {
				requiredNum = surplusRequired.get(podId);
			}
			if (surplusStock.get(msbId) == null) {
				surplusStock.put(msbId, stockNum);
			} else {
				stockNum = surplusStock.get(msbId);
			}

			// 此仓位没有可用库存或者此订单物料需求已满足
			if (surplusRequired.get(podId) == 0 || surplusStock.get(msbId) == 0) {
				continue;
			}
			if(callNum>0){
				if(stockNum>=callNum){//满足所有呼叫需求
					surplusRequired.put(podId, 0.0);
					surplusStock.put(msbId, stockNum-callNum);
					map.put("matchNum", callNum);//分配数量
				}else{//满足部分需求
					surplusRequired.put(podId, callNum-stockNum);
					surplusStock.put(msbId, 0.0);
					map.put("matchNum", stockNum);//分配数量
				}
			}else{
				if(stockNum>=requiredNum){//满足所有需求
					surplusRequired.put(podId, 0.0);
					surplusStock.put(msbId, stockNum-requiredNum);
					map.put("matchNum", requiredNum);//分配数量
				}else{//满足部分需求
					surplusRequired.put(podId, requiredNum-stockNum);
					surplusStock.put(msbId, 0.0);
					map.put("matchNum", stockNum);//分配数量
				}
			}

			if(detailPickNumMap.containsKey(podStorageId)){
				detailPickNumMap.put(podStorageId, Double.valueOf(""+map.get("matchNum"))
						+detailPickNumMap.get(podStorageId));
			}else{
				detailPickNumMap.put(podStorageId, Double.valueOf(""+map.get("matchNum")));
			}

			matchFinshList.add(map);
		}

		return matchFinshList;
	}

	/**
	 * .
	 *
	 * @param list
	 * @param surplusStock
	 * @Author:wangzhou
	 * @date:2020年9月2日
	 * @Description:生成工位订单，更改仓库库存、更改生产订单详情表状态
	 */
	private void dataHandle(List<Map<String, Object>> list, Map<String, Double> surplusStock,
			Map<String, Double> surplusRequired,Map<String, Double> detailPickNumMap) {
		String podId = null;// 订单详情id
		List<Map<String, Object>> processOrderDetailList = new ArrayList<>();
		List<StationOrder> stationOrderList = new ArrayList<>();
		List<StationOrderDetail> stationOrderDetailList = new ArrayList<>();
		List<StorageOrderDetail> storageOrderDetailList = new ArrayList<>();
		String tblOrderCode = "";//OrderCodeUtil.crateTaskcodeOfTBL();
		Map<String, Object> processOrderDetail = null;
		Set<String> processOrderDetailIds = new HashSet<>();
		Set<String> storageBinIds = new HashSet<>();
		Set<String> stationCodes = new HashSet<>();
		Set<String> podStorageIds = new HashSet<>();
		List<Map<String, Object>> storageBinlList = new ArrayList<>();
		Map<String, Object> storageBin = null;
		String msbId = null;
		double stockDisNum = 0.0;// 库存扣减数量
		String stationCode = null;
		StationOrder stationOrder = null;
		StationOrderDetail stationOrderDetail = null;
		String stationOrderId = null;
		String podStorageId = null;
		StorageOrderDetail storageOrderDetail = null;
		Date date = new Date();
		//存放主表id,
		Map<String,String> idMap = new HashMap<>();
		for (Map<String, Object> map : list) {
			podId = (String) map.get("podId");
			msbId = (String) map.get("msbId");
			stationCode = (String) map.get("stationCode")+map.get("orderCode");
			podStorageId = podId+map.get("storageId");
			if (!processOrderDetailIds.contains(podId)) {
				processOrderDetail = new HashMap<String, Object>();
				if (surplusRequired.containsKey(podId)) {
					processOrderDetail.put("surplusNum", surplusRequired.get(podId));
					if(surplusRequired.get(podId)==0){
						processOrderDetail.put("status", 2);
					}else{
						processOrderDetail.put("status", 1);
					}
				}else{
					map.put("surplusNum", 0);
					processOrderDetail.put("status", 0);
				}
				processOrderDetail.put("id", podId);
				processOrderDetailList.add(processOrderDetail);
				processOrderDetailIds.add(podId);
			}
			if(!storageBinIds.contains(msbId)){
				if(surplusStock.containsKey(msbId)){
					storageBin = new HashMap<String,Object>();
					storageBin.put("id",msbId);
					stockDisNum = Double.valueOf(""+map.get("enableCount")) - surplusStock.get(msbId);
					storageBin.put("stockDisNum", stockDisNum);
					storageBinlList.add(storageBin);
					storageBinIds.add(msbId);
				}
			}
			if (!stationCodes.contains(stationCode)) {
				stationOrder = new StationOrder();
				stationOrder.setCreateTime(date);
				//stationOrderId = UuidUtil.getUUID();
				idMap.put(stationCode, stationOrderId);
				stationOrder.setId(stationOrderId);
				fillStationOrder(stationOrder, map,tblOrderCode);
				stationCodes.add(stationCode);
				stationOrderList.add(stationOrder);
			}
			if(!podStorageIds.contains(podStorageId)){//子表的唯一性
				stationOrderDetail = new StationOrderDetail();
				//stationOrderDetail.setId(UuidUtil.getUUID());
				idMap.put(podStorageId, stationOrderDetail.getId());
				stationOrderDetail.setCreateTime(date);
				stationOrderDetail.setStationOrderId(idMap.get(stationCode));
				fillStationOrderDetail(stationOrderDetail, map);
				stationOrderDetail.setPickNum(detailPickNumMap.get(podStorageId));
				stationOrderDetailList.add(stationOrderDetail);
				podStorageIds.add(podStorageId);
			}
			storageOrderDetail = new StorageOrderDetail();
			storageOrderDetail.setPickNum((Double) map.get("matchNum"));
			storageOrderDetail.setPositionCode((String)map.get("positionCode"));
			storageOrderDetail.setStorageId((String)map.get("storageId"));
			storageOrderDetail.setStorageBinId((String)map.get("msbId"));
			storageOrderDetail.setStationDetailId(idMap.get(podStorageId));
			storageOrderDetail.setCreateTime(date);
			storageOrderDetailList.add(storageOrderDetail);
		}

		// 修改生产订单明细表的状态
		if (processOrderDetailList.size() > 0) {
			processOrderDetailDao.updateStatus(processOrderDetailList);
		}
		// 修改可用库存
		if (storageBinlList.size() > 0) {
			processOrderDetailDao.updateStockNum(storageBinlList);
		}
		if (stationOrderList.size() > 0) {
			LOGGER.info("保存工位订单表信息:[{}]",stationOrderList.toString());
			stationOrderDao.saveStationOrder(stationOrderList);
		}
		if (stationOrderDetailList.size() > 0) {
			LOGGER.info("保存工位订单详情信息:[{}]", JsonUtil.toJson(stationOrderDetailList));
			stationOrderDetailDao.saveStationOrderDetail(stationOrderDetailList);
		}
		if(storageOrderDetailList.size()>0){
			stationOrderDetailDao.saveStorageOrderDetail(storageOrderDetailList);
		}
	}

	/**
	 * .
	 *
	 * @param stationOrder
	 * @param map
	 * @Author:wangzhou
	 * @date:2020年9月2日
	 * @Description:填充生产订单子表更新字段信息
	 */
	private void fillStationOrder(StationOrder stationOrder, Map<String, Object> map,String tblOrderCode) {
		stationOrder.setWorkshopCode((String) map.get("workshopCode"));
		stationOrder.setLineCode((String) map.get("lineCode"));
		stationOrder.setStationCode((String) map.get("stationCode"));
		stationOrder.setRequiredDate((Date) map.get("requiredTime"));
		log.info("============================orderCode:"+map.get("orderCode"));
		stationOrder.setOrderCode((String) map.get("orderCode"));
		stationOrder.setPriority(map.get("priority")==null?null:Integer.valueOf("" + map.get("priority")));
		stationOrder.setVinCode("" + map.get("vinCode"));
		stationOrder.setFactory("" + map.get("factory"));
		//设置业务类型
		int businessType = Integer.parseInt((String) map.get("businessType"));
		log.info("=======================businessType:"+businessType);
		Double callNum = map.get("callNum") != null ? Double.valueOf("" + map.get("callNum")) : 0;// 呼叫数量
		//缺料呼叫
		if(callNum>0){
			stationOrder.setBusiType(2);
		}
		switch (businessType){
			//3 补料
			case 3 : stationOrder.setOrderType(3);
				stationOrder.setBusiType(3);
			break;
			//4 退补料
			case 4 : stationOrder.setOrderType(4);
			stationOrder.setCostCenterOrder(tblOrderCode);
			stationOrder.setBusiType(4);
			break;
			default:
				stationOrder.setOrderType(0);
				stationOrder.setBusiType(1);
		}
		stationOrder.setProcessOrderId("" + map.get("poid"));
		stationOrder.setCarModel("" + map.get("carModel"));
		stationOrder.setStationDesc((String) map.get("stationDesc"));
		Map<String, Object> resultMap = null;// SapStorageLocationTransfrom.lesToSapStorageLocation(redisTemplate, (String) map.get("storageLocation"));
		if (resultMap != null) {
			stationOrder.setWarehouseCode((String) resultMap.get("warehouseCode"));
			stationOrder.setWarehouseName((String) resultMap.get("warehouseName"));
		}
	}

	/**
	 * .
	 *
	 * @param stationOrderDetail
	 * @param map
	 * @Author:wangzhou
	 * @date:2020年9月3日
	 * @Description:填充工位订单详情信息
	 */
	private void fillStationOrderDetail(StationOrderDetail stationOrderDetail, Map<String, Object> map) {
		stationOrderDetail.setMaterialCode((String) map.get("materialCode"));
		stationOrderDetail.setMaterialUnit((String) map.get("itemUnit"));
		stationOrderDetail.setReserveCode((String) map.get("reserveCode"));
		stationOrderDetail.setReserveRow((String) map.get("reserveRow"));
		stationOrderDetail.setPickNum((Double) map.get("matchNum"));
		stationOrderDetail.setStationLocation((String) map.get("storageLocation"));
		stationOrderDetail.setPositionCode((String) map.get("positionCode"));
		stationOrderDetail.setStorageBinId((String) map.get("msbId"));
		stationOrderDetail.setStorageId((String) map.get("storageId"));
		stationOrderDetail.setProcessDetialId((String) map.get("podId"));
		stationOrderDetail.setMaterialRow((String) map.get("materialRow"));
		stationOrderDetail.setStatus(0);
		//设置物料描述
		stationOrderDetail.setMaterialDesc((String)map.get("materialDesc"));
		// 订单库存
		log.info("生产订单库存匹配数据:{}",JsonUtil.toJson(map));
		if (map.get("stockType") != null && "3".equals(map.get("stockType").toString())) {
			stationOrderDetail.setSpecialType(map.get("stockType").toString());
			stationOrderDetail.setSellOrder(map.get("sellOrder").toString());
			stationOrderDetail.setSellOrderItem(map.get("sellOrderItem").toString());

		}
		// 客户库存
		else if (map.get("stockType") != null && "B".equals(map.get("stockType").toString())) {
			stationOrderDetail.setSpecialType((String) map.get("stockType"));
			stationOrderDetail.setSpecialCode((String) map.get("sellOrder"));
			stationOrderDetail.setSpecialName((String) map.get("sellOrderItem"));
		}

	}
	/**
	 * @param param
	 * @return
	 * @Author:luozhihong
	 * @date:2020年9月18日
	 * @Description:匹配库存之前，判断是否满足匹配条件,满足返回true，不满足返回false
	 */
	@Override
	public boolean beforeMatchStorageStock(Map<String, Object> param) {
		//查询业务类型为2(缺料呼叫)待匹配物料需求清单明细
		List<ProcessOrderDetail> list=processOrderDetailDao.listMatchProcessOrderDetail(param);
		boolean flag=true;
		if(list!=null&&list.size()>0){
			ProcessOrderDetail processOrderDetail=null;

			for(int i=0;i<list.size();i++){
				processOrderDetail=list.get(i);
				//呼叫数量大于计划数量
				if(processOrderDetail.getCallNum()>processOrderDetail.getSurplusNum()){
					flag=false;
					break;
				}
			}
		}
		return flag;
	}
}

package com.zkzn.les.oms.stationOrder.service;

import java.util.List;
import java.util.Map;

import com.zkzn.les.oms.stationOrder.pojo.OrderTaskDetailPojo;
import com.zkzn.les.oms.stationOrder.pojo.OrderTaskPojo;
import com.zkzn.les.oms.stationOrder.pojo.PcShipmentTaskPojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.oms.pojo.AssembleOrder;
import com.zkzn.les.oms.pojo.AssembleOrderDetail;
import com.zkzn.les.oms.pojo.ProcessOrder;
import com.zkzn.les.oms.pojo.ProcessOrderDetail;
import com.zkzn.les.oms.pojo.StationOrderWithDetail;
import com.zkzn.les.oms.pojo.Wave;


/**.
 *
 * @author luozhihong
 * @date 2020年9月2日
 * @Description :出库任务派发 service
 */
public interface StationOrderService {


	/**
	 * 下架清单列表
	 * @param pcShipmentTaskPojo
	 * @return
	 */
	PageInfo<PcShipmentTaskPojo> listStationOrder(PcShipmentTaskPojo pcShipmentTaskPojo);

	/**
	 * 出来订单管理
	 * @param orderTaskPojo
	 * @return
	 */
	List<OrderTaskPojo> listBOMMainList(OrderTaskPojo orderTaskPojo);

	/**
	 * 匹配库存数据
	 * @param orderTaskId
	 * @param userId
	 * @param currentUserName
	 * @return
	 */
	int matchingInventory(Integer orderTaskId,Integer userId,String currentUserName);

	/**
	 * 新增出库单
	 * @param orderTaskPojo
	 * @return
	 */
	int bommAdd(OrderTaskPojo orderTaskPojo);

	/**
	 * 删除出库单信息
	 * @param orderTaskIds
	 * @return
	 */
	int deleteOrderTask(List<Integer> orderTaskIds);

	/**
	 * 查询出库单详情
	 * @param orderTaskDetailPojo
	 * @return
	 */
	List<OrderTaskDetailPojo> listBOMDetailList(OrderTaskDetailPojo orderTaskDetailPojo);

	/**
	 * 新增出库详情
	 * @param orderTaskDetailPojo
	 * @return
	 */
	int orderTaskDetailAdd(OrderTaskDetailPojo orderTaskDetailPojo);

	/**
	 * 删除出库详情
	 * @param orderTaskDetailIds
	 * @return
	 */
	int deleteOrderTaskDetail(List<Integer> orderTaskDetailIds);

}

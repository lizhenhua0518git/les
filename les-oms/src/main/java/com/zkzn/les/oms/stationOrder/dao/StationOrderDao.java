package com.zkzn.les.oms.stationOrder.dao;

import java.util.List;
import java.util.Map;

import com.zkzn.les.oms.pojo.PurchaseBillDetail;
import com.zkzn.les.oms.pojo.StationWave;
import com.zkzn.les.oms.stationOrder.pojo.OrderTaskDetailPojo;
import com.zkzn.les.oms.stationOrder.pojo.OrderTaskPojo;
import com.zkzn.les.oms.stationOrder.pojo.PcShipmentTaskPojo;
import com.zkzn.les.oms.stationOrder.pojo.ShipmentTaskData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zkzn.les.oms.pojo.StationOrder;
import com.zkzn.les.oms.pojo.StationOrderWithDetail;


/**.
 *
 * @author wangzhou
 * @date 2020年9月3日
 * @Description 工位订单dao层
 */
@Mapper
public interface StationOrderDao {

	/**
	 * 下架清单列表
	 * @param pcShipmentTaskPojo
	 * @return
	 */
	List<PcShipmentTaskPojo> listStationOrder(PcShipmentTaskPojo pcShipmentTaskPojo);

	/**
	 * 出来订单管理
	 * @param orderTaskPojo
	 * @return
	 */
	List<OrderTaskPojo> listBOMMainList(OrderTaskPojo orderTaskPojo);

	/**
	 * 修改匹配库存状态
	 * @param orderTaskId
	 * @return
	 */
	int updateOrderType(@Param("orderTaskId") Integer orderTaskId);

	/**
	 * 查询生成
	 * @param orderTaskId
	 * @return
	 */
	List<ShipmentTaskData> getShipmentTaskData(@Param("orderTaskId") Integer orderTaskId);

	/**
	 * 新增出库任务主表
	 * @param shipmentTaskData
	 * @return
	 */
	int addShipmentTask(ShipmentTaskData shipmentTaskData);

	/**
	 * 新增出库任务详情
	 * @param shipmentTaskDataList
	 * @return
	 */
	int addShipmentTaskDetail(@Param("shipmentTaskDataList") List<ShipmentTaskData> shipmentTaskDataList);

	/**
	 * 修改库位库存占用数量
	 * @param shipmentTaskData
	 * @return
	 */
	int updatePreUseCount(ShipmentTaskData shipmentTaskData);

	/**
	 * 新增出库任务
	 * @param orderTaskPojo
	 * @return
	 */
	int bommAdd(OrderTaskPojo orderTaskPojo);

	/**
	 * 删除送货单
	 * @param orderTaskIds
	 * @return
	 */
	int deleteOrderTask(List<Integer> orderTaskIds);

	/**
	 * 获取客户操作人
	 * @param clientName
	 * @return
	 */
	Map<String,Object> getClientManage(@Param("clientName") String clientName);

	/**
	 * 查询出库详情数据
	 * @param orderTaskDetailPojo
	 */
	List<OrderTaskDetailPojo> listBOMDetailList(OrderTaskDetailPojo orderTaskDetailPojo);

	/**
	 * 新增出库单详情
	 * @param orderTaskDetailPojo
	 * @return
	 */
	int orderTaskDetailAdd(OrderTaskDetailPojo orderTaskDetailPojo);

	/**
	 * 删除送货单详情
	 * @param orderTaskDetailIds
	 * @return
	 */
	int deleteOrderTaskDetail(List<Integer> orderTaskDetailIds);
}

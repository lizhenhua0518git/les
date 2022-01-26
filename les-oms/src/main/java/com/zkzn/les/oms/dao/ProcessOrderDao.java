package com.zkzn.les.oms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zkzn.les.oms.pojo.ProcessOrder;


/**.
 * 
 * @author wangzhou
 * @date 2020年8月28日
 * @Description mes锁定订单后 拆分订单Dao
 */
@Mapper
public interface ProcessOrderDao {

	
	/**.
	 * 
	 * @param order
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月28日
	 * @Description:批量保存锁定订单信息
	 */
	int saveProcessOrder(List<ProcessOrder> order);

	/**.
	 * 
	 * @return
	 * @param {orderCOdes:List<String>,vinCodes:List<String>,factory:String,stationCode:string}
	 * @Author:wangzhou
	 * @date:2020年8月28日
	 * @Description:通过mes锁定的订单查询符合条件的生产订单
	 */
	List<Map<String,Object>> listLockOrder(Map<String,Object> param);
	/**.
	 * 
	 * @param id
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月28日
	 * @Description:查询Mes锁定订单列表
	 */
	List<Map<String,Object>> listMesOrderInfo(String id);
	/**
	 * @param id
	 * @param storageLocation
	 * @return
	 * @Author:luozhihong
	 * @date:2020年10月28日
	 * @Description:查询补料信息列表
	 */
	List<Map<String,Object>> listAbnormalMaterialWorkshopBL(@Param("taskcode") String taskcode,@Param("storageLocation")  String storageLocation,@Param("list")List<String> list);
	/**.
	 * 
	 * @param id
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月28日
	 * @Description:更新mes锁定计划表状态
	 */
	int updateMesLockPlanStatus(String id);
	
	/**.
	 * 
	 * @param processOrder
	 * @return
	 * @Author:luozhihong
	 * @date:2020年9月2日
	 * @Description:分页查询物料需求清单
	 */
	List<ProcessOrder> listProcessOrder(@Param("processOrder") ProcessOrder processOrder);
	/**
	 * @param processOrder
	 * @return
	 * @Author:luozhihong
	 * @date:2020年9月2日
	 * @Description:查询待匹配的订单列表
	 */
	List<ProcessOrder> listAllProcessOrder(@Param("processOrder") ProcessOrder processOrder);
	/**
	 * @param processOrder
	 * @return
	 * @Author:luozhihong
	 * @date:2020年9月25日
	 * @Description:查询前20按优先级排序的订单列表，并按车间产线筛选
	 */
	List<ProcessOrder> listSortProcessOrder(@Param("processOrder") ProcessOrder processOrder);

	/**
	 * 查询没有分批的mes数据
	 */
	List<String> getUnOrderSplit();
	/**
	 * @param id
	 * @return
	 * @Author:luozhihong
	 * @date:2020年9月25日
	 * @Description:根据订单ID查询仓库名称列表
	 */
    List<String> getWarehouseNames(@Param("id") String id);
}

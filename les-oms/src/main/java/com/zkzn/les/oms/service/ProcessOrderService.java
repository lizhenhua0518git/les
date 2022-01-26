package com.zkzn.les.oms.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.oms.pojo.ProcessOrder;


/**.
 * 
 * @author wangzhou
 * @date 2020年8月28日
 * @Description : 已锁定订单信息 service
 */
public interface ProcessOrderService {

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


	/**.
	 * 
	 * @param id
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月28日
	 * @Description:执行锁定订单
	 */
	void doLockOrder(String id);
	/**
	 * @param id
	 * @param paraMap
	 * @return
	 * @Author:luozhihong
	 * @date:2020年10月28日
	 * @Description:生成补货订单
	 */
	void doFillMaterialOrder(Map<String,Object> paraMap) throws Exception;
	/**.
	 * 
	 * @param processOrder
	 * @return
	 * @Author:luozhihong
	 * @date:2020年9月2日
	 * @Description:分页查询物料需求清单
	 */
	PageInfo<ProcessOrder> listProcessOrder(ProcessOrder processOrder);
	/**.
	 * 
	 * @param processOrder
	 * @return
	 * @Author:luozhihong
	 * @date:2020年9月25日
	 * @Description:查询前80按优先级排序的订单列表，并按车间产线筛选
	 */
	List<ProcessOrder> listSortProcessOrder(ProcessOrder processOrder);
	
	/**.
	 * 
	 * @param processOrder
	 * @return
	 * @Author:wangzhou
	 * @date:2020年9月5日
	 * @Description:非分页查询物料需求清单：匹配库存筛选订单使用
	 */
	List<ProcessOrder> listAllProcessOrder(ProcessOrder processOrder);

	/**
	 * mes手动锁定订单服务
	 */
	void orderSplit();
}

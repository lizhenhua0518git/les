package com.zkzn.les.oms.service;

import java.util.List;

import com.zkzn.les.oms.pojo.AssembleOrder;

/**.
 * 
 * @author wangzhou
 * 集配订单 service
 */
public interface AssembleOrderService {

	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年4月6日下午5:18:45
	 * List<AssembleOrder>
	 * @param storageList
	 * @return
	 * 功能描述:查询已经存在的集配订单
	 */
	List<AssembleOrder> listAssembleOrder(List<String> storageList);
}

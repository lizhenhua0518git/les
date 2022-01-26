package com.zkzn.les.oms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zkzn.les.oms.pojo.AssembleOrder;
import com.zkzn.les.oms.pojo.AssembleOrderDetail;

/**.
 * 
 * @author wangzhou
 *	集配订单 dao
 */
@Mapper
public interface AssembleOrderDao {

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
	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年4月6日下午5:42:45
	 * int
	 * @param detailList
	 * @return
	 * 功能描述:批量插入集配单详情表
	 */
	int saveAssembleOrderDetails (List<AssembleOrderDetail> detailList);
	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年4月6日下午5:48:05
	 * int
	 * @param orderList
	 * @return
	 * 功能描述:批量插入集配单数据
	 */
	int saveAssembleOrders(List<AssembleOrder> orderList);
}

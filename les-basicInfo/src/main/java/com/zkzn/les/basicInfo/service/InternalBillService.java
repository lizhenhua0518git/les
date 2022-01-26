package com.zkzn.les.basicInfo.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.pojo.InternalBill;


/**.
 * 
 * @author wangzhou
 * @date 2020年8月22日
 * @Description 内部订单 service
 */
public interface InternalBillService {

	/**.
	 * 
	 * @param internalBill
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月22日
	 * @Description:查询内部订单
	 */
	List<InternalBill> listInternalBill(InternalBill internalBill);
	
	/**.
	 * 
	 * @param internalBill
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月22日
	 * @Description:分页查询内部订单
	 */
	PageInfo<InternalBill> listInternalBillPage(InternalBill internalBill);
	/**.
	 *
	 * @param internalBill
	 * @return
	 * @Author:luozhihong
	 * @date:2020年10月20日
	 * @Description:查询内部订单列表
	 */
	List<InternalBill> getInternalBillList(InternalBill internalBill);
}

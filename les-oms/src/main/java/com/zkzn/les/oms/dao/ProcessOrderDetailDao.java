package com.zkzn.les.oms.dao;

import com.zkzn.les.oms.pojo.ProcessOrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**.
 * 
 * @author wangzhou
 * mes锁定订单详情dao
 */
@Mapper
public interface ProcessOrderDetailDao  {

	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年4月6日下午5:06:32
	 * int
	 * @param list
	 * @return
	 * 功能描述:批量保存工序订单详情
	 */
	int saveLostOrderDetail(List<ProcessOrderDetail> list);
	
	/**.
	 * 
	 * @param processOrderDetail
	 * @return
	 * @Author:luozhihong
	 * @date:2020年9月2日
	 * @Description:分页查询物料需求清单
	 */
	List<ProcessOrderDetail> listProcessOrderDetail(@Param("processOrderDetail") ProcessOrderDetail processOrderDetail);
	/**.
	 * 
	 * @param param
	 * @return
	 * @Author:luozhihong
	 * @date:2020年9月8日
	 * @Description:查询业务类型为2(缺料呼叫)待匹配物料需求清单明细
	 */
	List<ProcessOrderDetail> listMatchProcessOrderDetail(Map<String,Object> param);
	/**.
	 * 
	 * @param param
	 * @return
	 * @Author:wangzhou
	 * @date:2020年9月1日
	 * @Description:查询订单库存信息
	 */
	List<Map<String,Object>> listOrderStock(Map<String,Object> param);
	
	/**.
	 * 
	 * @param param
	 * @return
	 * @Author:wangzhou
	 * @date:2020年9月1日
	 * @Description:查询客户库存信息
	 */
	List<Map<String,Object>> listCustomStock(Map<String,Object> param);
	
	
	/**.
	 * 
	 * @param param
	 * @return
	 * @Author:wangzhou
	 * @date:2020年9月1日
	 * @Description:查询普通库存信息
	 */
	List<Map<String,Object>> listNormalStock(Map<String,Object> param);

	/***
	 * @Discription:根据processId查询物料库存情况
	 * @param requestMap
	 * @return List.class
	 * @Author: zhanglei on 2021/1/19 16:27
	 */
	List<Map<String,Object>> listOfProcessIdStock(List<Map<String,Object>> requestMap);
	/***
	 * @Discription: 销售订单手动匹配
	 * @param requestMap
	 * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 * @Author: zhanglei on 2021/5/18 9:30
	 */
	List<Map<String,Object>> listOfProcessIdStockByE(List<Map<String,Object>> requestMap);
	/**.
	 * 
	 * @param list
	 * @return
	 * @Author:wangzhou
	 * @date:2020年9月3日
	 * @Description:修改分配库存状态
	 */
	int updateStatus(List<Map<String,Object>> list);
	/**.
	 * 
	 * @param list
	 * @return
	 * @Author:wangzhou
	 * @date:2020年9月3日
	 * @Description:更新可用库存、预占库存、库存总数
	 */
	int updateStockNum(List<Map<String,Object>> list);
	/**.
	 *
	 * @param processOrderDetailList
	 * @return
	 * @Author:luozhihong
	 * @date:2020年11月9日
	 * @Description:更新生产订单呼叫缺料信息
	 */
    int updateProcessOrderDetailCallInfo(List<ProcessOrderDetail> processOrderDetailList);
}

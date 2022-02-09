package com.zkzn.les.oms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zkzn.les.oms.pojo.StationOrderDetail;
import com.zkzn.les.oms.pojo.StorageOrderDetail;

/**.
 * 
 * @author wangzhou
 * @date 2020年9月3日
 * @Description 工位订单详情dao
 */
@Mapper
public interface StationOrderDetailDao {

	/**.
	 * 
	 * @param list
	 * @return
	 * @Author:wangzhou
	 * @date:2020年9月3日
	 * @Description:保存工位订单详情
	 */
	int saveStationOrderDetail(List<StationOrderDetail> list);
	/**.
	 * 
	 * @param list
	 * @return
	 * @Author:wangzhou
	 * @date:2020年9月4日
	 * @Description:保存工位订单详情和仓位库存表关联
	 */
	int saveStorageOrderDetail(List<StorageOrderDetail> list);
}

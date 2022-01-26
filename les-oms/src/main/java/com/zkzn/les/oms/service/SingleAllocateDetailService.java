package com.zkzn.les.oms.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.oms.pojo.SingleAllocateDetail;


/**.
 * 
 * @author wangzhou
 * @date 2020年8月10日
 * @Description:调拨订单物料详情service
 */
public interface SingleAllocateDetailService {

	
	/**.
	 * 
	 * @param singleAllocateDetail
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月10日
	 * @Description:保存调拨订单物料详情
	 */
	int saveSingleAllocateDetail(SingleAllocateDetail singleAllocateDetail);
	/**.
	 * 
	 * @param singleAllocateDetails
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月10日
	 * @Description:批量保存调拨订单物料详情
	 */
	int saveSingleAllocateDetails(List<SingleAllocateDetail> singleAllocateDetails);
	/**.
	 * 
	 * @param singleAllocateDetail
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月10日
	 * @Description:修改调拨订单物料详情
	 */
	int updateSingleAllocateDetail(SingleAllocateDetail singleAllocateDetail);
	/**.
	 * 
	 * @param singleAllocateDetail
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月10日
	 * @Description:批量修改调拨订单物料详情
	 */
	int updateSingleAllocateDetails(List<SingleAllocateDetail> singleAllocateDetails);
	
	/**
	 * 
	 * @param ids
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月10日
	 * @Description:删除调拨订单物料详情
	 */
	int deleteSingleAllocateDetail(List<String> ids);
	/**.
	 * 
	 * @param singleAllocateDetail
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月10日
	 * @Description:查询调拨订单物料详情
	 */
	List<Map<String,Object>> listSingleAllocateDetail(Map<String,Object> param);
	/**.
	 * 
	 * @param singleAllocateDetail
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月10日
	 * @Description:分页查询调拨订单物料详情
	 */
	PageInfo<Map<String,Object>> listSingleAllocateDetailPage(Map<String,Object> param);
}

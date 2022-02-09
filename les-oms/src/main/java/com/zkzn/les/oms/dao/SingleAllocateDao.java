package com.zkzn.les.oms.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;

import com.zkzn.les.oms.pojo.SingleAllocate;


/**.
 * 
 * @author wangzhou
 * @date 2020年8月10日
 * @Description:调拨订单管理dao层
 */
@Mapper
public interface SingleAllocateDao {

	/**.
	 * 
	 * @param singleAllocate
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月10日
	 * @Description:保存调拨订单
	 */
	int saveSingleAllocate(SingleAllocate singleAllocate);
	
	/**.
	 * 
	 * @param singleAllocate
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月10日
	 * @Description:修改调拨订单
	 */
	int updateSingleAllocate(SingleAllocate singleAllocate);
	/**.
	 * 
	 * @param ids
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月10日
	 * @Description:删除调拨订单
	 */
	int deleteSingleAllocate(List<String> ids);
	
	/**.
	 * 
	 * @param singleAllocate
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月10日
	 * @Description:查询生产订单
	 */
	List<SingleAllocate> listSingleAllocate(SingleAllocate singleAllocate);
	/**.
	 * 
	 * @param singleAllocate
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月16日
	 * @Description:批量修改调拨订单
	 */
	int updateSingleAllocates(List<SingleAllocate> singleAllocates);
	/**.
	 * 
	 * @param setStr
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月16日
	 * @Description:验证单据类型是否存在
	 */
	List<Map<String,Object>> checkDocument(Set<String> setStr);
	/**.
	 * 
	 * @param orgCodes
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月16日
	 * @Description:验证仓库编码
	 */
	List<Map<String,Object>> checkOrgCode(Set<String> orgCodes);
	/**.
	 * 
	 * @param storageLocation
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月16日
	 * @Description:验证库存地点
	 */
	List<Map<String,Object>> checkStorageLocation(Set<String> storageLocations);
	/**.
	 * 
	 * @param materialCodes
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月16日
	 * @Description:验证物料编码
	 */
	List<Map<String,Object>> checkMaterialCode(Set<String> materialCodes);
	
	/**.
	 * 
	 * @param singleAllocate
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月10日
	 * @Description:保存调拨订单
	 */
	int saveSingleAllocates(List<SingleAllocate> singleAllocates);
}

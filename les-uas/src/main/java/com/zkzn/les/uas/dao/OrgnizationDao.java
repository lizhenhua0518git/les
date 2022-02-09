package com.zkzn.les.uas.dao;

import com.zkzn.les.uas.pojo.Orgnization;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;



/**.
 * 
 * @author wangzhou
 *
 */
@Mapper
public interface OrgnizationDao {

	/**.
	 * 
	 * 功能描述：通过id查询信息
	 * 作者：wangzhou
	 * 时间:2018年6月7日
	 * @param id
	 * @return
	 */
	Orgnization getById(String id);
	
	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年6月3日下午2:47:57
	 * List<Map<String,Object>>
	 * @param userId
	 * @return
	 * 功能描述:通过用户查询对应仓库权限
	 */
	List<Map<String, Object>> listWarehouseByUserId(String userId);
}

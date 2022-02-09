package com.zkzn.les.basicInfo.dao;

import com.zkzn.les.basicInfo.pojo.DictItems;
import com.zkzn.les.basicInfo.pojo.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResourceDao {

	/**.
	 * 
	 * 功能描述：新增资源信息
	 * 作者：wangzhou
	 * 时间:2018年6月12日
	 * @param resource
	 * @return
	 */
	int saveResource(Resource resource);

	/**
	 * 批量新增菜单按钮
	 * @param list
	 */
	void addResourceList(List<Resource> list);

	/**.
	 * 
	 * 功能描述：删除资源信息
	 * 作者：wangzhou
	 * 时间:2018年6月12日
	 * @param id
	 * @return
	 */
	int removeResource(List<String> id);
	/**.
	 * 
	 * 功能描述：修改资源信息
	 * 作者：wangzhou
	 * 时间:2018年6月12日
	 * @param resource
	 * @return
	 */
	int updateResource(Resource resource);
	/**.
	 * 
	 * 功能描述：查询资源信息
	 * 作者：wangzhou
	 * 时间:2018年6月12日
	 * @param resource
	 * @return
	 */
	List<Resource> listresource(Resource resource);
	
	/**.
	 * 
	 * 功能描述：通过id查询资源信息
	 * 作者：wangzhou
	 * 时间:2018年6月12日
	 * @param id
	 * @return
	 */
	Resource getById(String id);
	/**.
	 * 
	 * 功能描述：通过parentId查询资源信息
	 * 作者：wangzhou
	 * 时间:2018年6月12日
	 * @param parentId
	 * @return
	 */
	List<Resource> listByParentId(String parentId);
	/**.
	 * 
	 * 功能描述：用于新增和修改时对资源名称查重
	 * 作者：wangzhou
	 * 时间:2018年6月12日
	 * @param resourceName
	 * @param id
	 * @return
	 */
	Resource getByResourceName(@Param("resourceName")String resourceName,@Param("id")String id);
	
	/**.
	 * 
	 * 功能描述：通过用户id查询资源权限
	 * 作者：wangzhou
	 * 时间：2018年10月12日
	 * @param userId 用户id
	 * @param type  资源类型
	 * @param status 资源状态
	 * @param attach 手持或PC
	 * @return
	 */
	List<Resource> listResourceByUserId(@Param("userId")String userId,@Param("type")String type,@Param("status")String status,@Param("attach")String attach);
	
	/**.
	 * 
	 * 功能描述：通过url连接查询资源信息
	 * 作者：wangzhou
	 * 时间:2018年6月19日
	 * @param url
	 * @return
	 */
	List<Resource> listResourceByUrl(String url);
	/**.
	 * 
	 * 功能描述：通过资源类型查询资源信息
	 * 作者：wangzhou
	 * 时间：2018年10月12日
	 * @param type  资源类型
	 * @param status 资源状态
	 * @param attach 手持或PC
	 * @return
	 */
	List<Resource> listResourceByType(@Param("type")String type,@Param("status")String status,@Param("attach")String attach);

	/**
	 * 根据父级页面ID查询资源信息
	 * @param resourceParentId
	 * @return
	 */
	List<String> getByResourceParentId(@Param("resourceParentId") String resourceParentId);

	List<DictItems> getDictButtonData();


}

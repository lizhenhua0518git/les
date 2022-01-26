package com.zkzn.les.basicInfo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zkzn.les.basicInfo.pojo.Orgnization;



/**.
 *
 * @author wangzhou
 *
 */
@Mapper
public interface OrgnizationDao {


	/**.
	 * 新增组织结构
	 * @param orgn
	 * @return
	 */
	int saveOrgnization(Orgnization orgn);
	/**.
	 * 修改组织结构
	 * @param orgn
	 * @return
	 */
	int updateOrgnization(Orgnization orgn);
	/**.
	 * 删除组织结构
	 * @param id
	 * @return
	 */
	int deleteOrgnization(List<String> id);
	/**.
	 * 查询所有的组织结构
	 * @return
	 */
	List<Orgnization> listAll();
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
	 * 功能描述：列表过滤查询
	 * 作者：wangzhou
	 * 时间:2018年6月8日
	 * @param orgn
	 * @return
	 */
	List<Orgnization> listByParam(Orgnization orgn);
	/**.
	 *
	 * 功能描述：批量修改组织信息的状态
	 * 作者：wangzhou
	 * 时间：2018年7月2日
	 * @param id
	 * @param status
	 * @return
	 */
	int updateStateById(@Param("id")List<String> id,@Param("status")int status);
	/**.
	 *
	 * 功能描述：查询仓库名称及对应的编码
	 * 作者：wangzhou
	 * 时间：2018年10月17日
	 * @return
	 */
	List<Map<String,Object>> listWarehouse(@Param("orgCode")String orgCode);
	/**.
	 *
	 * 功能描述：查询用户对应的组织类型
	 * 作者：wangzhou
	 * 时间：2018年10月17日
	 * @param id
	 * @return
	 */
	Map<String,Object> listUserOrgType(String id);
	/**
	 * @Title:  getUserRole
	 * @Description:   获取角色权限代码
	 * @author: nihao
	 * @date:   2019年6月27日 下午3:59:45
	 * @param:  @param userid
	 * @param:  @return
	* @return: List<Map<String,Object>>
	 * @throws
	 */
	List<Map<String, Object>> getUserRole(String userid);
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

	List<Map<String, Object>> initWarehouseSelect(Integer userId);

	List<Map<String, Object>> listWarehouseTwo(String orgCode);
}

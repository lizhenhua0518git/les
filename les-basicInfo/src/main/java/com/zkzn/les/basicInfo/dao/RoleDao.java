package com.zkzn.les.basicInfo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zkzn.les.basicInfo.pojo.Role;
@Mapper
public interface RoleDao {

	/**.
	 * 
	 * 功能描述：新增角色
	 * 作者：wangzhou
	 * 时间:2018年6月12日
	 * @param role
	 * @return
	 */
	int saveRole(Role role);
	/**.
	 * 
	 * 功能描述：删除角色
	 * 作者：wangzhou
	 * 时间:2018年6月12日
	 * @param roleId
	 * @return
	 */
	int deleteRole(List<String> roleId);
	/**.
	 * 
	 * 功能描述：修改角色
	 * 作者：wangzhou
	 * 时间:2018年6月12日
	 * @param role
	 * @return
	 */
	int updateRole(Role role);
	/**.
	 * 
	 * 功能描述：查询角色信息
	 * 作者：wangzhou
	 * 时间:2018年6月12日
	 * @param role
	 * @return
	 */
	List<Role> listRole(Role role);
	/**.
	 * 
	 * 功能描述：用于保存时对角色名称查重
	 * 作者：wangzhou
	 * 时间:2018年6月12日
	 * @param roleName
	 * @param roleId
	 * @return
	 */
	Role getByRoleName(@Param("roleName")String roleName,@Param("roleId")String roleId);
}

package com.zkzn.les.basicInfo.service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleService {

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
	PageInfo<Role> listRolePage(Role role);
	
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

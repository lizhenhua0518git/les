package com.zkzn.les.basicInfo.dao;

import com.zkzn.les.basicInfo.pojo.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserRoleDao {

	/**.
	 * 
	 * 功能描述：批量保存用户和角色关联信息
	 * 作者：wangzhou
	 * 时间:2018年6月13日
	 * @param userRoleList
	 * @return
	 */
	int saveUserRole(List<UserRole> userRoleList);
	/**.
	 * 
	 * 功能描述：批量删除用户和角色关联信息
	 * 作者：wangzhou
	 * 时间:2018年6月13日
	 * @param userRoleList
	 * @return
	 */
	int deleteUserRole(List<UserRole> userRoleList);
	/**.
	 * 
	 * 功能描述：通过roleid查询用户角色关联信息
	 * 作者：wangzhou
	 * 时间:2018年6月20日
	 * @param roleId
	 * @return
	 */
	List<UserRole> listUserRoleByroleId(String roleId);
	/**.
	 * 
	 * 功能描述：查询用户和角色关联信息总数
	 * 作者：wangzhou
	 * 时间：2018年7月5日
	 * @param roleIds
	 * @return
	 */
	int countUserRoleByRoleIds(List<String> roleIds);
}

package com.zkzn.les.basicInfo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zkzn.les.basicInfo.pojo.Privilege;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PrivilegeDao {

	
	/**.
	 * 
	 * 功能描述：批量保存资源和角色关联
	 * 作者：wangzhou
	 * 时间:2018年6月14日
	 * @param privilege
	 * @return
	 */
	int savePrivilege(List<Privilege> privilege);
	
	/**.
	 * 
	 * 功能描述：批量删除资源和角色关联
	 * 作者：wangzhou
	 * 时间:2018年6月14日
	 * @param privilege
	 * @return
	 * 
	 */
	int deletePrivilege(List<Privilege> privilege);
	
	/**.
	 * 
	 * 功能描述：通过roleId查询resId
	 * 作者：wangzhou
	 * 时间:2018年6月14日
	 * @param roleId
	 * @return
	 */
	List<Privilege> listPrivilegeByRoleId(@Param("roleId") String roleId);
}

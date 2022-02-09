package com.zkzn.les.basicInfo.user.dao;

import com.zkzn.les.basicInfo.user.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;





@Mapper
public interface UserDao {

	/**
	 * 分页查询用户信息
	 * @param user
	 * @return
	 */
	List<User> listUserPage(User user);

	/**
	 * 用户查重
	 * @param user
	 * @return
	 */
	int getUserRepeat(User user);

	/**
	 * 保存用户信息
	 * @param user
	 * @return
	 */
	int saveUser(User user);

	/**
	 * 通过id查询用户信息
	 * @param user
	 * @return
	 */
	User getUserById(User user);

	/**
	 * 修改用户信息
	 * @param u
	 * @return
	 */
	int updateUser(User u);

	/**
	 * 通过id删除用户信息
	 * @param id
	 * @return
	 */
	int deleteUser(List<String> id);

	/**
	 * 用户未分配仓库查询
	 * @param paraMap
	 * @return
	 */
	List<Map<String,Object>> listUserWareHouse(Map<String,Object> paraMap);

	/**
	 * 查询用户已分配仓库信息
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> listUserOrganization(Map<String,Object> params);

	/**
	 * 用户分配仓库
	 * @param param
	 */
	void saveUserWareHouse( List<Map<String,Object>> param);

	/**
	 * 删除用户对应仓库关系
	 * @param ids
	 * @return
	 */
	int removeUserOrganization(List<String> ids);

	/**
	 * 查询用户已有的角色信息
	 * @param param
	 * @return
	 */
	List<Map<String,Object>> listUserRole(Map<String,Object> param);

























	/**.
	 *
	 * 功能描述：过滤用户信息
	 * 作者：wangzhou
	 * 时间:2018年6月8日
	 * @param u
	 * @return
	 */
	User getUser(User u);





	/**.
	 *
	 * 功能描述：通过手机号码查询用户
	 * 作者：wangzhou
	 * 时间:2018年6月11日
	 * @param phoneNum
	 * @return
	 */
	User getUserByPhone(@Param("phoneNum")String phoneNum,@Param("id")String id);
	/**.
	 *
	 * 功能描述：查询某个角色对应的用户列表
	 * 作者：wangzhou
	 * 时间:2018年6月14日
	 * @param roleId
	 * @return
	 */
	List<User> listHasRoleUser(@Param("user")User user,@Param("roleId")String roleId);
	/**.
	 *
	 * 功能描述：查询某个角色没有对应的用户列表
	 * 作者：wangzhou
	 * 时间:2018年6月14日
	 * @param roleId
	 * @return
	 */
	List<User> listNoRoleUser(@Param("roleId")String roleId,@Param("user")User user);
	/**.
	 *
	 * 功能描述：通过用户名查询用户
	 * 作者：wangzhou
	 * 时间：2018年6月22日
	 * @param userName
	 * @return
	 */
	User getByUserName(String userName);
	/**
	 * 功能描述：通过账号密码查询用户
	 * 作者：wanghaojie
	 * 时间：2019年6月4日
	 * @param phone
	 * @param oldPassWord
	 * @return
	 */
	User getUserByPhoneAndPwd(@Param("phone")String phone, @Param("oldPassWord")String oldPassWord);


	/**.
	 *
	 * 创建人: wangzhou
	 * 时间:2020年6月1日下午5:25:03
	 * int
	 * @param list
	 * @return
	 * 功能描述:批量保存用户对应组织信息
	 */
	int saveUserOrganization(List<Map<String,Object>> list);


}

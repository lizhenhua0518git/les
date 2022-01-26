package com.zkzn.les.uas.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zkzn.les.uas.pojo.User;



@Mapper
public interface UserDao {



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
	 * 功能描述：修改用户信息
	 * 作者：wangzhou
	 * 时间:2018年6月8日
	 * @param u
	 * @return
	 */
	int updateUser(User u);
	/**.
	 *
	 * 功能描述：保存用户信息
	 * 作者：wangzhou
	 * 时间:2018年6月8日
	 * @param user
	 * @return
	 */
	int saveUser(User user);
	/**.
	 *
	 * 功能描述：分页查询用户信息
	 * 作者：wangzhou
	 * 时间:2018年6月8日
	 * @param user
	 * @return
	 */
	List<User> listUserPage(User user);
	/**.
	 *
	 * 功能描述：通过id查询用户信息
	 * 作者：wangzhou
	 * 时间:2018年6月8日
	 * @param id
	 * @return
	 */
	User getUserById(String id);
	/**.
	 *
	 * 功能描述：通过id删除用户信息
	 * 作者：wangzhou
	 * 时间:2018年6月8日
	 * @param id
	 * @return
	 */
	int deleteUser(List<String> id);
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
	 * @param userId
	 * @return
	 * @Author:wangzhou
	 * @date:2020年7月20日
	 * @Description:查询用户拥有访问权限的url
	 */
	List<String> listHasPermissed(String userId);

	/**
	 * 查询当前用户拥有的权限
	 * @param userId
	 * @return
	 */
	List<String> getUserUrl(String userId);

	/**
	 * 根据用户仓库查询人员信息
	 * @param warehouseCode
	 * @return
	 */
	List<User> initUserInfo(@Param("warehouseCode") String warehouseCode);
}

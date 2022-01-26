package com.zkzn.les.uas.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.uas.pojo.User;


public interface UserService {

	/**
	 * 分页查询用户信息列表
	 * @param user
	 * @return
	 */
	PageInfo<User> listPageUser(User user);
































	/**.
	 * 功能描述: 所有满足条件的在用用户
	 * 作者:何闰平
	 * 时间:2018年6月4日 上午10:53:57
	 * @param user
	 * @return
	 */
	User getUser(User user);

	/**.
	 * 功能描述: 更新用户
	 * 作者:何闰平
	 * 时间:2018年6月4日 上午10:54:43
	 * @param user
	 * @return
	 */
	int updateUser(User user);
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
	User getUserByPhone(String phoneNum,String id);

	/**.
	 *
	 * 功能描述：查询某个角色对应的用户列表
	 * 作者：wangzhou
	 * 时间:2018年6月14日
	 * @param roleId
	 * @return
	 */
	PageInfo<User> listHasRoleUser(User user,String roleId);

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
	User getUserByPhoneAndPwd(String phone, String oldPassWord);
	/**.
	 *
	 * 创建人: wangzhou
	 * 时间:2020年4月2日下午4:30:55
	 * List<User>
	 * @param roleId
	 * @param user
	 * @return
	 * 功能描述:查询某个角色没有对应的用户列表
	 */
	List<User> listNoRoleUser(String roleId,User user);
	/**.
	 *
	 * 创建人: wangzhou
	 * 时间:2020年4月2日下午4:31:05
	 * PageInfo<User>
	 * @param user
	 * @param roleId
	 * @return
	 * 功能描述:查询某个角色没有对应的用户列表 分页
	 */
	PageInfo<User> listNoRoleUserPage(String roleId,User user);

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
	 * 查询当前用的拥有的权限
	 * @param userId
	 * @return
	 */
	List<String> getUserUrl(String userId);

	/**
	 * 根据用户仓库查询人员信息
	 * @param warehouseCode
	 * @return
	 */
	List<User> initUserInfo(String warehouseCode);
}

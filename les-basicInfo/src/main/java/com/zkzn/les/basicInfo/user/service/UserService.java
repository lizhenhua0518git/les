package com.zkzn.les.basicInfo.user.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.user.pojo.User;


public interface UserService {

	/**
	 * 用户信息分页列表
	 * @param user
	 * @return
	 */
	List<User> listUser(User user);

	/**
	 * 保存用户信息
	 * @param user
	 * @return
	 */
	int saveUser(User user);

	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	int updateUser(User user);

	/**
	 * 通过id删除用户信息
	 * @param id
	 * @return
	 */
	int deleteUser(List<String> id);

	/**
	 * 用户未分配仓库查询
	 * @param param
	 * @return
	 */
	PageInfo<Map<String,Object>> listUserWareHouse(Map<String,Object> param);

	/**
	 * 用户已分配仓库查询
	 * @param params
	 * @return
	 */
	PageInfo<Map<String,Object>> listUserOrganizationPage(Map<String,Object> params);

	/**
	 * 用户分配仓库
	 * @param param
	 */
	void saveUserWareHouse(List<Map<String,Object>> param);

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
	PageInfo<Map<String,Object>> listUserRolePage(Map<String,Object> param);











	/**.
	 * 功能描述: 所有满足条件的在用用户
	 * 作者:何闰平
	 * 时间:2018年6月4日 上午10:53:57
	 * @param user
	 * @return
	 */
	User getUser(User user);




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
	 * 创建人: wangzhou
	 * 时间:2020年6月1日下午2:50:50
	 * int
	 * @param user
	 * @return
	 * 功能描述:用户查重
	 */
	int getUserRepeat(User user);

	/**.
	 *
	 * 创建人: wangzhou
	 * 时间:2020年6月1日下午4:35:52
	 * List<Map<String,Object>>
	 * @param params
	 * @return
	 * 功能描述:查询用户对应组织信息
	 */
	List<Map<String,Object>> listUserOrganization(Map<String,Object> params);


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


	/**.
	 *
	 * 创建人: wangzhou
	 * 时间:2020年6月2日上午11:17:25
	 * List<Map<String,Object>>
	 * @param param
	 * @return
	 * 功能描述:查询用户已有的角色信息
	 */
	List<Map<String,Object>> listUserRole(Map<String,Object> param);





}

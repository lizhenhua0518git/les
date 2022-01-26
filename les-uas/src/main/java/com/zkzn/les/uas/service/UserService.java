package com.zkzn.les.uas.service;

import com.zkzn.les.uas.pojo.User;


public interface UserService {

	/**.
	 * 功能描述: 更新用户
	 * 作者:何闰平
	 * 时间:2018年6月4日 上午10:54:43
	 * @param user
	 * @return
	 */
	int updateUser(User user);

	/**
	 * 功能描述：通过账号密码查询用户
	 * 作者：wanghaojie
	 * 时间：2019年6月4日
	 * @param phone
	 * @param oldPassWord
	 * @return
	 */
	User getUserByPhoneAndPwd(String phone, String oldPassWord);

}

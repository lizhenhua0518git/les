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

	/**
	 * 功能描述：通过账号密码查询用户
	 * 作者：wanghaojie
	 * 时间：2019年6月4日
	 * @param phone
	 * @param oldPassWord
	 * @return
	 */
	User getUserByPhoneAndPwd(@Param("phone")String phone, @Param("oldPassWord")String oldPassWord);

}

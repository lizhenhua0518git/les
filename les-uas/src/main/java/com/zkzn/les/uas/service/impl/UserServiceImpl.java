package com.zkzn.les.uas.service.impl;

import com.zkzn.les.uas.dao.UserDao;
import com.zkzn.les.uas.pojo.User;
import com.zkzn.les.uas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

	@Autowired(required=true)
	private UserDao userDao;

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}

	@Override
	public User getUserByPhoneAndPwd(String phone, String oldPassWord) {
		// TODO Auto-generated method stub
		return userDao.getUserByPhoneAndPwd(phone, oldPassWord);
	}

}

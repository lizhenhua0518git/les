package com.zkzn.les.uas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkzn.les.uas.dao.UserDao;
import com.zkzn.les.uas.pojo.User;
import com.zkzn.les.uas.service.UserService;
import com.zkzn.les.uas.util.PageUtil;


@Service
public class UserServiceImpl implements UserService {

	@Autowired(required=true)
	private UserDao userDao;

	@Override
	public PageInfo<User> listPageUser(User user) {
		PageHelper.startPage(1, 5);
		List<User> userList = userDao.listUserPage(user);
		PageInfo<User> pageInfo = new PageInfo<>(userList);
		return pageInfo;
	}



























	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
		return userDao.getUser(user);
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}

	@Override
	public int saveUser(User user) {
		// TODO Auto-generated method stub
		return userDao.saveUser(user);
	}

	@Override
	@Cacheable(value="les-uas",key="#id")
	public User getUserById(String id) {
		// TODO Auto-generated method stub
		return userDao.getUserById(id);
	}

	@Override
	public int deleteUser(List<String> id) {
		// TODO Auto-generated method stub
		return userDao.deleteUser(id);
	}

	@Override
	public User getUserByPhone(String phoneNum, String id) {
		// TODO Auto-generated method stub
		return userDao.getUserByPhone(phoneNum, id);
	}

	@Override
	public User getByUserName(String userName) {
		// TODO Auto-generated method stub
		return userDao.getByUserName(userName);
	}

	@Override
	public User getUserByPhoneAndPwd(String phone, String oldPassWord) {
		// TODO Auto-generated method stub
		return userDao.getUserByPhoneAndPwd(phone, oldPassWord);
	}

	@Override
	public List<User> listNoRoleUser(String roleId, User user) {
		// TODO Auto-generated method stub
		return this.userDao.listNoRoleUser(roleId, user);
	}

	@Override
	public PageInfo<User> listNoRoleUserPage(String roleId,User user) {


		PageUtil.setPageParam(user);
		List<User> userList = listNoRoleUser(roleId,user);
		PageInfo<User> pageInfo = new PageInfo<>(userList);

		return pageInfo;
	}

	@Override
	public PageInfo<User> listHasRoleUser(User user,String roleId) {
		PageUtil.setPageParam(user);
		List<User> userList = userDao.listHasRoleUser(user,roleId);
		PageInfo<User> pageInfo = new PageInfo<>(userList);

		return pageInfo;
	}

	@Override
	@Cacheable(value="les-uas",key="#userId+#root.methodName")
	public List<String> listHasPermissed(String userId) {
		// TODO Auto-generated method stub
		return userDao.listHasPermissed(userId);
	}

	@Override
	public List<String> getUserUrl(String userId) {
		return userDao.getUserUrl(userId);
	}

	@Override
	public List<User> initUserInfo(String warehouseCode) {
		List<User> userList = userDao.initUserInfo(warehouseCode);
		return userList;
	}


}

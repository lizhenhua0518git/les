package com.zkzn.les.basicInfo.user.service.impl;

import java.util.List;
import java.util.Map;

import com.zkzn.les.basicInfo.config.exception.ExceptionCasts;
import com.zkzn.les.basicInfo.user.pojo.User;
import com.zkzn.les.basicInfo.util.Ecode;
import com.zkzn.les.common.util.lang.MD5;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.user.dao.UserDao;
import com.zkzn.les.basicInfo.user.service.UserService;
import com.zkzn.les.basicInfo.util.PageUtil;

import javax.annotation.Resource;


@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Override
	public List<User> listUser(User user) {
		return userDao.listUserPage(user);
	}

	@Override
	public int saveUser(User user) {
		int userRepeat = userDao.getUserRepeat(user);
		if (userRepeat>0){
			return 1;
		}else {
			userDao.saveUser(user);
		}
		return 0;
	}

	@Override
	public int updateUser(User user) {
		int userRepeat = userDao.getUserRepeat(user);
		if (userRepeat>0) {
			return 1;
		}else {
			User getUser = userDao.getUserById(user);
			if(!getUser.getUserPassword().equals(user.getUserPassword())){
				user.setUserPassword(MD5.getStringMD5(user.getUserPassword()));
			}
			userDao.updateUser(user);
		}
		return 0;
	}

	@Override
	public int deleteUser(List<String> id) {
		return userDao.deleteUser(id);
	}

	@Override
	public PageInfo<Map<String, Object>> listUserWareHouse(Map<String, Object> param) {
		if (param == null || param.isEmpty()) {
			ExceptionCasts.cast(Ecode.PARAM);
		}
		PageUtil.setPageParam(param);
		List<Map<String, Object>> wareHouses = userDao.listUserWareHouse(param);
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(wareHouses);
		return pageInfo;
	}

	@Override
	public PageInfo<Map<String, Object>> listUserOrganizationPage(Map<String, Object> params) {
		PageUtil.setPageParam(params);
		List<Map<String, Object>> list = listUserOrganization(params);
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public List<Map<String, Object>> listUserOrganization(Map<String, Object> params) {
		return userDao.listUserOrganization(params);
	}

	@Override
	public void saveUserWareHouse(List<Map<String, Object>> param) {
		if (param == null || param.isEmpty()) {
			ExceptionCasts.cast(Ecode.PARAM);
		}
		userDao.saveUserWareHouse(param);
	}

	@Override
	public int removeUserOrganization(List<String> ids) {
		return userDao.removeUserOrganization(ids);
	}

	@Override
	public PageInfo<Map<String, Object>> listUserRolePage(Map<String, Object> param) {
		PageUtil.setPageParam(param);
		List<Map<String, Object>> list = listUserRole(param);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public List<Map<String, Object>> listUserRole(Map<String, Object> param) {
		return userDao.listUserRole(param);
	}





















































	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
		return userDao.getUser(user);
	}

	@Override
	@Cacheable(value="les-uas",key="#id")
	public User getUserById(String id) {
		// TODO Auto-generated method stub
		//return userDao.getUserById(id);
		return new User();
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
	public int getUserRepeat(User user) {
		// TODO Auto-generated method stub
		return userDao.getUserRepeat(user);
	}



	@Override
	public int saveUserOrganization(List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		return userDao.saveUserOrganization(list);
	}


}

package com.zkzn.les.basicInfo.service.impl;

import com.zkzn.les.basicInfo.dao.UserRoleDao;
import com.zkzn.les.basicInfo.pojo.UserRole;
import com.zkzn.les.basicInfo.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleDao userRoleDao;
	
	@Override
	public int saveUserRole(List<UserRole> userRoleList) {
		// TODO Auto-generated method stub
		return userRoleDao.saveUserRole(userRoleList);
	}

	@Override
	public int deleteUserRole(List<UserRole> userRoleList) {
		// TODO Auto-generated method stub
		return userRoleDao.deleteUserRole(userRoleList);
	}

	@Override
	public List<UserRole> listUserRoleByroleId(String roleId) {
		// TODO Auto-generated method stub
		return userRoleDao.listUserRoleByroleId(roleId);
	}

	@Override
	public int countUserRoleByRoleIds(List<String> roleIds) {
		// TODO Auto-generated method stub
		return userRoleDao.countUserRoleByRoleIds(roleIds);
	}

}

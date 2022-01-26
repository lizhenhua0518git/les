package com.zkzn.les.uas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkzn.les.uas.dao.UserRoleDao;
import com.zkzn.les.uas.pojo.UserRole;
import com.zkzn.les.uas.service.UserRoleService;

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

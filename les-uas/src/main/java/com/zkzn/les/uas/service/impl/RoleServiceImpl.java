package com.zkzn.les.uas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.uas.dao.RoleDao;
import com.zkzn.les.uas.pojo.Role;
import com.zkzn.les.uas.service.RoleService;
import com.zkzn.les.uas.util.PageUtil;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public int saveRole(Role role) {
		// TODO Auto-generated method stub
		return roleDao.saveRole(role);
	}

	@Override
	public int deleteRole(List<String> roleId) {
		// TODO Auto-generated method stub
		return roleDao.deleteRole(roleId);
	}

	@Override
	public int updateRole(Role role) {
		// TODO Auto-generated method stub
		return roleDao.updateRole(role);
	}

	@Override
	public PageInfo<Role> listRolePage(Role role) {
		// TODO Auto-generated method stub
		PageUtil.setPageParam(role);
		List<Role> roleList = roleDao.listRole(role);
		PageInfo<Role> rolePage = new PageInfo<Role>(roleList);
	 
		return rolePage;
	}

	@Override
	public Role getByRoleName(String roleName, String roleId) {
		// TODO Auto-generated method stub
		return roleDao.getByRoleName(roleName, roleId);
	}

}

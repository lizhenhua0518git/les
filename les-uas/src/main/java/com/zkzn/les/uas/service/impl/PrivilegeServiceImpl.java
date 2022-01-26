package com.zkzn.les.uas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkzn.les.uas.dao.PrivilegeDao;
import com.zkzn.les.uas.pojo.Privilege;
import com.zkzn.les.uas.service.PrivilegeService;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

	@Autowired
	private PrivilegeDao privilegeDao;
	
	@Override
	public int savePrivilege(List<Privilege> privilege) {
		// TODO Auto-generated method stub
		return privilegeDao.savePrivilege(privilege);
	}
	
	@Override
	public int deletePrivilege(List<Privilege> privilege) {
		// TODO Auto-generated method stub
		return privilegeDao.deletePrivilege(privilege);
	}
	
	@Override
	public List<Privilege> listPrivilegeByRoleId(String roleId) {
		// TODO Auto-generated method stub
		return privilegeDao.listPrivilegeByRoleId(roleId);
	}

}

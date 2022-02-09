package com.zkzn.les.basicInfo.service.impl;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.dao.RoleDao;
import com.zkzn.les.basicInfo.pojo.DictItems;
import com.zkzn.les.basicInfo.pojo.Role;
import com.zkzn.les.basicInfo.service.DictItemsService;
import com.zkzn.les.basicInfo.service.RoleService;
import com.zkzn.les.basicInfo.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	@Autowired
	private DictItemsService dictItemsService;
	
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
		if ("".equals(role.getStatus())||role.getStatus()==null){
			role.setStatus(1);
		}
		List<Role> roleList = roleDao.listRole(role);
		
		  List<String> dicts = new ArrayList<String>();
			dicts.add("enable");//启用禁用状态
			dicts.add("warehouse_type");//仓库类型
			 
			List<DictItems> dictItems = dictItemsService.listDictItemsByType(dicts);
			
			if(roleList!=null && dictItems!=null ){
				for(Role tempRole:roleList){
					for(DictItems dictItem:dictItems){
						if("enable".equals(dictItem.getDictTypeId()) && dictItem.getItemValue().equals(""+tempRole.getStatus()) ){
							tempRole.setStatusName(dictItem.getItemName());
						}
						 
						 
					}
				}
			}
		
		PageInfo<Role> rolePage = new PageInfo<Role>(roleList);
	 
		return rolePage;
	}

	@Override
	public Role getByRoleName(String roleName, String roleId) {
		// TODO Auto-generated method stub
		return roleDao.getByRoleName(roleName, roleId);
	}

}

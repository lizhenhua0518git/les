package com.zkzn.les.basicInfo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.dao.MaterialLaunchRemarkDao;
import com.zkzn.les.basicInfo.pojo.MaterialLaunchRemark;
import com.zkzn.les.basicInfo.service.MaterialLaunchRemarkService;
import com.zkzn.les.basicInfo.util.LesConstants;

@Service
public class MaterialLaunchRemarkServiceImpl implements MaterialLaunchRemarkService{
	
	@Autowired
	private MaterialLaunchRemarkDao materialLaunchRemarkDao;

	@Override
	public List<MaterialLaunchRemark> listMaterialLaunchRemark(MaterialLaunchRemark materialLaunchRemark) {
		return materialLaunchRemarkDao.listMaterialLaunchRemark(materialLaunchRemark);
	}

	@Override
	public PageInfo<MaterialLaunchRemark> listByPage(MaterialLaunchRemark materialLaunchRemark) {
		 PageHelper.startPage(materialLaunchRemark.getPage(),materialLaunchRemark.getLimit());
		 List<MaterialLaunchRemark> list=materialLaunchRemarkDao.listMaterialLaunchRemark(materialLaunchRemark);
		 PageInfo<MaterialLaunchRemark> page = new PageInfo<MaterialLaunchRemark>(list);
		 
		return page;
	}

	@Override
	public int insert(MaterialLaunchRemark materialLaunchRemark) {
		int count = materialLaunchRemarkDao.countMaterialLaunchRemark(materialLaunchRemark);
		if(count >0) {
			return LesConstants.CODE_EXIST;
		}
		return materialLaunchRemarkDao.insert(materialLaunchRemark);
	}

	@Override
	public int update(MaterialLaunchRemark materialLaunchRemark) {
		int count = materialLaunchRemarkDao.countMaterialLaunchRemark(materialLaunchRemark);
		if(count >0) {
			return LesConstants.CODE_EXIST;
		}
		return materialLaunchRemarkDao.update(materialLaunchRemark);
	}

	@Override
	public int delete(List<String> id) {
		return materialLaunchRemarkDao.delete(id);
	}

	@Override
	public int insertOrUpdate(List<MaterialLaunchRemark> list) {
		int i=0;
		if(list!=null&&list.size()>0){
			for(MaterialLaunchRemark g:list){
				if(g.getId()==null||"".equals(g.getId())){
					materialLaunchRemarkDao.insert(g);
				}else{
					materialLaunchRemarkDao.update(g);
				}
				i++;
			}
		}
		return i;
	}

	@Override
	public List<Map<String, Object>> listWarehouse(Map<String, Object> map) {
		 
		return materialLaunchRemarkDao.listWarehouse(map);
	}
}

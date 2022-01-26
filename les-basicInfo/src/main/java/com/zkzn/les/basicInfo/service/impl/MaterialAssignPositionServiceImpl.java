package com.zkzn.les.basicInfo.service.impl;

import java.util.List;
import java.util.Map;


import com.zkzn.les.basicInfo.pojo.StoragePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.dao.MaterialAssignPositionDao;
import com.zkzn.les.basicInfo.pojo.MaterialAssignPosition;
import com.zkzn.les.basicInfo.service.MaterialAssignPositionService;
import com.zkzn.les.basicInfo.util.LesConstants;
import com.zkzn.les.basicInfo.util.PageUtil;


@Service
public class MaterialAssignPositionServiceImpl implements MaterialAssignPositionService{
	
	@Autowired
	private MaterialAssignPositionDao materialAssignPositionDao;

	@Override
	public List<MaterialAssignPosition> list(MaterialAssignPosition materialAssignPosition) {
		return materialAssignPositionDao.list(materialAssignPosition);
	}

	@Override
	public PageInfo<MaterialAssignPosition> listByPage(MaterialAssignPosition materialAssignPosition) {
		 PageHelper.startPage(materialAssignPosition.getPage(),materialAssignPosition.getLimit());
		List<MaterialAssignPosition> list=materialAssignPositionDao.list(materialAssignPosition);
		PageInfo<MaterialAssignPosition> page = new PageInfo<MaterialAssignPosition>(list);
		 
		return page;
	}

	@Override
	public int insert(MaterialAssignPosition materialAssignPosition) {
		int count = materialAssignPositionDao.countMaterialAssignPosition(materialAssignPosition);
		if(count >0) {
			return LesConstants.CODE_EXIST;
		}
		return materialAssignPositionDao.insert(materialAssignPosition);
	}

	@Override
	public int update(MaterialAssignPosition materialAssignPosition) {
		int count = materialAssignPositionDao.countMaterialAssignPosition(materialAssignPosition);
		if(count >0) {
			return LesConstants.CODE_EXIST;
		}
		return materialAssignPositionDao.update(materialAssignPosition);
	}

	@Override
	public int delete(List<String> id) {
		return materialAssignPositionDao.delete(id);
	}

	@Override
	public int insertOrUpdate(List<MaterialAssignPosition> list) {
		int i=0;
		if(list!=null&&list.size()>0){
			for(MaterialAssignPosition g:list){
				if(g.getId()==null||"".equals(g.getId())){
					materialAssignPositionDao.insert(g);
				}else{
					materialAssignPositionDao.update(g);
				}
				i++;
			}
		}
		return i;
	}

	@Override
	public PageInfo<StoragePosition> listStoragePosition(StoragePosition storagePosition) {
//		 PageHelper.startPage(storagePosition.getPage(),storagePosition.getLimit());
		List<StoragePosition> list=materialAssignPositionDao.listStoragePosition(storagePosition);
		PageInfo<StoragePosition> page = new PageInfo<StoragePosition>(list);
	 
		return page;
	}

	@Override
	public PageInfo<Map<String, Object>> listMaterialInfo(Map<String, Object> map) {
		 PageUtil.setPageParam(map);
		 List<Map<String, Object>> list = this.materialAssignPositionDao.listMaterialInfo(map);
		 PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(list);
		return page;
	}
}

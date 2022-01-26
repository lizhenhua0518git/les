package com.zkzn.les.basicInfo.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.pojo.MaterialAssignPosition;
import com.zkzn.les.basicInfo.pojo.StoragePosition;


public interface MaterialAssignPositionService {

	List<MaterialAssignPosition> list(MaterialAssignPosition materialAssignPositionDao);
	
	PageInfo<MaterialAssignPosition> listByPage(MaterialAssignPosition materialAssignPositionDao);
	
	int insert(MaterialAssignPosition materialAssignPositionDao);
	
	int update(MaterialAssignPosition materialAssignPositionDao);
	
	int delete(List<String> id);
	
	int insertOrUpdate(List<MaterialAssignPosition> list);

	PageInfo<StoragePosition> listStoragePosition(StoragePosition storagePosition);
	
	/**
	 * . 
	 *
	 * 功能描述:物料信息查询
	 * 
	 * @param map
	 * @return
	 * @author  刘松山  
	 *
	 *时间:  2020-03-31 09:43
	 *
	 */
	PageInfo<Map<String, Object>> listMaterialInfo(Map<String, Object> map);
}

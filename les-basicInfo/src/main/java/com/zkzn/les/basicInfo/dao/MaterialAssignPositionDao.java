package com.zkzn.les.basicInfo.dao;

import java.util.List;
import java.util.Map;


import com.zkzn.les.basicInfo.pojo.StoragePosition;
import org.apache.ibatis.annotations.Mapper;

import com.zkzn.les.basicInfo.pojo.MaterialAssignPosition;

@Mapper
public interface MaterialAssignPositionDao {
	
	List<MaterialAssignPosition> list( MaterialAssignPosition materialAssignPosition);
	
	int insert(MaterialAssignPosition materialAssignPosition);
	
	int update(MaterialAssignPosition materialAssignPosition);
	
	int delete(List<String> id);

	List<StoragePosition> listStoragePosition(StoragePosition storagePosition);
	
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
	List<Map<String, Object>> listMaterialInfo(Map<String, Object> map);
	/**
	 * . 
	 *
	 * 功能描述:获取物料固定仓位是否重复
	 * 
	 * @param materialAssignPosition
	 * @return
	 * @author  刘松山  
	 *
	 *时间:  2020-04-09 09:13
	 *
	 */
	int countMaterialAssignPosition(MaterialAssignPosition materialAssignPosition);
}

package com.zkzn.les.basicInfo.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.pojo.MaterialLaunchRemark;

 
 

public interface MaterialLaunchRemarkService {

	List<MaterialLaunchRemark> listMaterialLaunchRemark(MaterialLaunchRemark materialLaunchRemark);
	
	PageInfo<MaterialLaunchRemark> listByPage(MaterialLaunchRemark materialLaunchRemark);
	
	int insert(MaterialLaunchRemark materialLaunchRemark);
	
	int update(MaterialLaunchRemark materialLaunchRemark);
	
	int delete(List<String> id);
	
	int insertOrUpdate(List<MaterialLaunchRemark> list);
	/**
	 * . 
	 *
	 * 功能描述:查询仓库列表
	 * 
	 * @param map
	 * @return
	 * @author  刘松山  
	 *
	 *时间:  2020-03-27 13:49
	 *
	 */
	List<Map<String, Object>> listWarehouse(Map<String, Object> map);
}

package com.zkzn.les.basicInfo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zkzn.les.basicInfo.pojo.MaterialLaunchRemark;

@Mapper
public interface MaterialLaunchRemarkDao {
	
	List<MaterialLaunchRemark> listMaterialLaunchRemark(MaterialLaunchRemark materialLaunchRemark);
	
	/**
	 * . 
	 *
	 * 功能描述:查询是否存在
	 * 
	 * @param materialLaunchRemark
	 * @return
	 * @author  刘松山  
	 *
	 *时间:  2020-04-08 13:35
	 *
	 */
	int countMaterialLaunchRemark(MaterialLaunchRemark materialLaunchRemark);
	
	int insert(MaterialLaunchRemark materialLaunchRemark);
	
	int update(MaterialLaunchRemark materialLaunchRemark);
	
	int delete(List<String> id);
	
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

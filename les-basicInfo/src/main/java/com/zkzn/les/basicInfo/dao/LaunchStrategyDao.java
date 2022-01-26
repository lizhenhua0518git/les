package com.zkzn.les.basicInfo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zkzn.les.basicInfo.pojo.LaunchStrategy;

@Mapper
public interface LaunchStrategyDao {
	
	List<LaunchStrategy> list(LaunchStrategy launchStrategy);
	
	int insert(LaunchStrategy launchStrategy);
	
	int update(LaunchStrategy launchStrategy);
	
	int delete(List<String> list);
	/**
	 * . 
	 *
	 * 功能描述:批量保存
	 * 
	 * @param list
	 * @return
	 * @author  刘松山  
	 *
	 *时间:  2020-04-29 10:49
	 *
	 */
	int saveBatch(List<LaunchStrategy> list);
}

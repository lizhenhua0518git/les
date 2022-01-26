package com.zkzn.les.basicInfo.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.pojo.LaunchStrategy;
import com.zkzn.les.basicInfo.pojo.LaunchStrategyNew;

public interface LaunchStrategyService {

	List<LaunchStrategy> list(LaunchStrategy launchStrategy);
	
	PageInfo<LaunchStrategy> listByPage(LaunchStrategy launchStrategy);
	
	int insert(LaunchStrategy launchStrategy);
	
	int update(LaunchStrategy launchStrategy);
	
	int delete(List<String> list);
	
	int insertOrUpdate(List<LaunchStrategy> list);
    /**
     * . 
     *
     * 功能描述:批量保存
     * 
     * @param list
     * @return
     * @author  刘松山  
     *
     *时间:  2020-04-29 10:52
     *
     */
	int saveBatch(List<LaunchStrategy> list);
}

/*
 * 文 件 名:  OverstorageStrategyService.java
 * 版    权:  
 * 描    述:  描述:
 * 修 改 人:  liusongshan 
 * 修改时间:  2018年8月7日
 * 跟踪单号:  跟踪单号:
 * 修改单号:  修改单号:
 * 修改内容:  修改内容:
 */
package com.zkzn.les.basicInfo.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.pojo.OverstorageStrategy;

/**.
 * 功能描述:
 * 时间:  2018年8月7日
 * @author  liusongshan  
 * 
 */
public interface OverstorageStrategyService {

	/**
	 * . 
	 * 功能描述:查询超储策略列表 分页
	 * @param overstorageStrategy
	 * @return
	 */
	PageInfo<OverstorageStrategy> listByPage(OverstorageStrategy overstorageStrategy);
	/**
	 * . 
	 *
	 * 功能描述:查询超储策略列表
	 * 
	 * @param overstorageStrategy
	 * @return
	 * @author  刘松山  
	 *
	 *时间:  2020-04-30 15:50
	 *
	 */
	List<OverstorageStrategy> listBylist(OverstorageStrategy overstorageStrategy);
	/**
	 * . 
	 * 功能描述:批量编辑超储临界值
	 * @param overstorageStrategy
	 * @return
	 */
	int editBoundCount(OverstorageStrategy overstorageStrategy);
	/**
	 * . 
	 * 功能描述:将导入的excel文件内容持久化数据层
	 * @param data
	 * @return
	 */
	int importExcel(String[][] data);
	/**
	 * . 
	 *
	 * 功能描述:查询要导出的记录数量
	 * 
	 * @param overstorageStrategy
	 * @return
	 * @author  刘松山  
	 *
	 *时间:  2018-11-04 19:49
	 *
	 */
	int queryCount(OverstorageStrategy overstorageStrategy);
}

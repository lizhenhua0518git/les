/*
 * 文 件 名:  OverstorageStrategyDao.java
 * 版    权:  
 * 描    述:  描述:
 * 修 改 人:  liusongshan 
 * 修改时间:  2018年8月7日
 * 跟踪单号:  跟踪单号:
 * 修改单号:  修改单号:
 * 修改内容:  修改内容:
 */
package com.zkzn.les.basicInfo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zkzn.les.basicInfo.pojo.OverstorageStrategy;

/**.
 * 功能描述:超储策略数据库层
 * 时间:  2018年8月7日
 * @author  liusongshan  
 * 
 */
@Mapper
public interface OverstorageStrategyDao {
	
	/**
	 * . 
	 * 功能描述:查询超储策略列表 分页
	 * @param overstorageStrategy
	 * @return
	 */
	List<OverstorageStrategy> list(OverstorageStrategy overstorageStrategy);
	/**
	 * . 
	 * 功能描述:批量编辑超储临界值
	 * @param overstorageStrategy
	 * @return
	 */
	int editBoundCount(List<OverstorageStrategy> list);
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

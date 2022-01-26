/*
 * 文 件 名:  BasicCreceiptStrategyDao.java
 * 版    权:  
 * 描    述:  <描述>
 * 修 改 人:  liusongshan 
 * 修改时间:  2018年8月2日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.zkzn.les.basicInfo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zkzn.les.basicInfo.pojo.StrategyDictionary;

/**.
 * 功能描述: 时间: 2018年8月2日
 * 
 * @author liusongshan
 * see [相关类/方法]
 */
@Mapper
public interface BasicCreceiptStrategyDao {

	/**.
	 * 
	 * 功能描述:批量保存收货基础策略数据记录
	 * 
	 * @param list
	 * @return
	 */
	int saveBasicCreceiptStrategy(List<StrategyDictionary> list);

	/**.
	 * 
	 * 功能描述:删除全部基础策略数据记录
	 * 
	 * @return
	 */
	int deleteBasicCreceiptStrategy(String warehouseCode);

	/**.
	 * 
	 * 功能描述:获取收货基础策略数据
	 * 
	 * @return
	 */
	List<StrategyDictionary> listBasicCreceiptStrategy();
	/**
	 * . 
	 *
	 * 功能描述:获取策略字典列表 
	 * 
	 * @param warehouseCode
	 * @return
	 * @author  刘松山  
	 *
	 *时间:  2018-10-31 23:02 修改
	 *
	 */
	List<StrategyDictionary> listStrategyDictionary();
	/**
	 * . 
	 *
	 * 功能描述:根据仓库查询
	 * 
	 * @param warehouseCode
	 * @return
	 * @author  刘松山  
	 *
	 *时间:  2018-12-20 19:08
	 *
	 */
	List<StrategyDictionary> listStrategyDictionaryByWarehouseCode(String warehouseCode);
}

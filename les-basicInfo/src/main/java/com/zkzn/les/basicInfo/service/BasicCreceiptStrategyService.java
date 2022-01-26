/*
 * 文 件 名:  BasicCreceiptStrategyService.java
 * 版    权:  
 * 描    述:  <描述>
 * 修 改 人:  liusongshan 
 * 修改时间:  2018年8月2日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.zkzn.les.basicInfo.service;

import java.util.List;

import com.zkzn.les.basicInfo.pojo.BasicCreceiptStrategyPage;
import com.zkzn.les.basicInfo.pojo.StrategyDictionary;

/**.
 * 功能描述:收货基础策略业务接口层 时间: 2018年8月2日
 * 
 * @author liusongshan
 * see [相关类/方法]
 */
public interface BasicCreceiptStrategyService {

	/**.
	 * 
	 * 功能描述:保存收货基础策略数据
	 * 
	 * @param basicCreceiptStrategyPage
	 * @return
	 */
	int addRecord(BasicCreceiptStrategyPage basicCreceiptStrategyPage,String currentUid);

	/**.
	 * 
	 * 功能描述:获取收货基础策略数据
	 * 
	 * @return
	 */
	BasicCreceiptStrategyPage listBasicCreceiptStrategy(String currentUid);
	/**
	 * . 
	 * 功能描述:获取策略字典key对应的数据值
	 * @param key
	 * @return
	 */
	int getStrategyDictionaryCache(String key);
	
	/**
	 * . 
	 * 功能描述:获取货基础策略缓存数据列表
	 * @return
	 */
	List<StrategyDictionary> getBasicCreceiptStrategyListCache();
	/**
	 * . 
	 * 功能描述:保存策略字典常量缓存
	 */
	void saveStrategyDictionaryCache();
	
	/**
	 * . 
	 *
	 * 功能描述:根据登录用户查询
	 * 
	 * @param key
	 * @param currentUid
	 * @return
	 * @author  刘松山  
	 *
	 *时间:  2018-12-20 19:06
	 *
	 */
	int getStrategyDictionaryCacheByUser(String key,String currentUid);
}

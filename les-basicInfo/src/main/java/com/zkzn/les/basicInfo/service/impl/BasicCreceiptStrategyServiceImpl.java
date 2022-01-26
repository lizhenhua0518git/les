/*
 * 文 件 名:  BasicCreceiptStrategyServiceImpl.java
 * 版    权:  
 * 描    述:  <描述>
 * 修 改 人:  liusongshan 
 * 修改时间:  2018年8月2日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.zkzn.les.basicInfo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.zkzn.les.basicInfo.dao.BasicCreceiptStrategyDao;
import com.zkzn.les.basicInfo.pojo.BasicCreceiptStrategyPage;
import com.zkzn.les.basicInfo.pojo.StrategyDictionary;
import com.zkzn.les.basicInfo.service.BasicCreceiptStrategyService;
import com.zkzn.les.basicInfo.util.DateUtil;
import com.zkzn.les.basicInfo.util.RedisUtil;
import com.zkzn.les.basicInfo.util.UserUtil;


/**.
 * 功能描述:收货基础策略业务实现层
 * 时间: 2018年8月2日
 * 
 * @author liusongshan
 * see [相关类/方法]
 */
@Service
public class BasicCreceiptStrategyServiceImpl implements BasicCreceiptStrategyService {

	@Autowired
	private BasicCreceiptStrategyDao basicCreceiptStrategyDao;
	@Autowired
	private RedisTemplate<String ,String> redisTemplate;
	/**.
	 * 重载方法
	 * 
	 * @param basicCreceiptStrategyPage
	 * @return
	 */
	@Override
	public int addRecord(BasicCreceiptStrategyPage basicCreceiptStrategyPage,String currentUid) {

		String warehouseCode =UserUtil.getCurrOrgCode(currentUid, redisTemplate, "pc");
		// 先删除表中所有记录
		this.basicCreceiptStrategyDao.deleteBasicCreceiptStrategy(warehouseCode);
		// 批量保存新的数据
		List<StrategyDictionary> list = new ArrayList<>();
		StrategyDictionary strategyDictionary = new StrategyDictionary();
		strategyDictionary.setType(1);
		strategyDictionary.setWarehouseCode(warehouseCode);
		strategyDictionary.setValue(basicCreceiptStrategyPage.getUnloadValueOne());
		strategyDictionary.setCreateTime(DateUtil.getCurrentDateWithFormat("yyyy-MM-dd hh:mm:ss"));
		list.add(strategyDictionary);

		strategyDictionary = new StrategyDictionary();
		strategyDictionary.setType(2);
		strategyDictionary.setWarehouseCode(warehouseCode);
		strategyDictionary.setValue(basicCreceiptStrategyPage.getTimeValueOne());
		strategyDictionary.setCreateTime(DateUtil.getCurrentDateWithFormat("yyyy-MM-dd hh:mm:ss"));
		list.add(strategyDictionary);

		strategyDictionary = new StrategyDictionary();
		strategyDictionary.setType(3);
		strategyDictionary.setWarehouseCode(warehouseCode);
		strategyDictionary.setValue(basicCreceiptStrategyPage.getQualityValueOne());
		strategyDictionary.setCreateTime(DateUtil.getCurrentDateWithFormat("yyyy-MM-dd hh:mm:ss"));
		list.add(strategyDictionary);

		strategyDictionary = new StrategyDictionary();
		strategyDictionary.setType(4);
		strategyDictionary.setWarehouseCode(warehouseCode);
		strategyDictionary.setValue(basicCreceiptStrategyPage.getDumpValueOne());
		strategyDictionary.setCreateTime(DateUtil.getCurrentDateWithFormat("yyyy-MM-dd hh:mm:ss"));
		list.add(strategyDictionary);

		strategyDictionary = new StrategyDictionary();
		strategyDictionary.setType(5);
		strategyDictionary.setWarehouseCode(warehouseCode);
		strategyDictionary.setValue(basicCreceiptStrategyPage.getTimeValueTwo());
		strategyDictionary.setCreateTime(DateUtil.getCurrentDateWithFormat("yyyy-MM-dd hh:mm:ss"));
		list.add(strategyDictionary);

		strategyDictionary = new StrategyDictionary();
		strategyDictionary.setType(6);
		strategyDictionary.setWarehouseCode(warehouseCode);
		strategyDictionary.setValue(basicCreceiptStrategyPage.getQualityValueTwo());
		strategyDictionary.setCreateTime(DateUtil.getCurrentDateWithFormat("yyyy-MM-dd hh:mm:ss"));
		list.add(strategyDictionary);

		strategyDictionary = new StrategyDictionary();
		strategyDictionary.setType(7);
		strategyDictionary.setWarehouseCode(warehouseCode);
		strategyDictionary.setValue(basicCreceiptStrategyPage.getDumpValueTwo());
		strategyDictionary.setCreateTime(DateUtil.getCurrentDateWithFormat("yyyy-MM-dd hh:mm:ss"));
		list.add(strategyDictionary);
		
		strategyDictionary = new StrategyDictionary();
		strategyDictionary.setType(9);
		strategyDictionary.setWarehouseCode(warehouseCode);
		strategyDictionary.setValue(basicCreceiptStrategyPage.getUpFrameValueOne());
		strategyDictionary.setCreateTime(DateUtil.getCurrentDateWithFormat("yyyy-MM-dd hh:mm:ss"));
		list.add(strategyDictionary);
		
		strategyDictionary = new StrategyDictionary();
		strategyDictionary.setType(10);
		strategyDictionary.setWarehouseCode(warehouseCode);
		strategyDictionary.setValue(basicCreceiptStrategyPage.getUpFrameValueTwo());
		strategyDictionary.setCreateTime(DateUtil.getCurrentDateWithFormat("yyyy-MM-dd hh:mm:ss"));
		list.add(strategyDictionary);
		
        int result =this.basicCreceiptStrategyDao.saveBasicCreceiptStrategy(list);
        //保存收货基础策略 配置值到缓存	
        saveStrategyDictionaryCache();
        
		return result;
	}

	/**.
	 * 重载方法 查询所有收货基础策略记录
	 * 
	 * @return
	 */
	@Override
	public BasicCreceiptStrategyPage listBasicCreceiptStrategy(String currentUid) {
		// 将表中数据记录转换为页面对应的数据
		//String warehouseCode =UserUtil.getCurrOrgCode(currentUid, redisTemplate, "pc");
		List<StrategyDictionary> list = this.basicCreceiptStrategyDao.listBasicCreceiptStrategy();
		BasicCreceiptStrategyPage basicCreceiptStrategyPage = new BasicCreceiptStrategyPage();
	
		for (StrategyDictionary basicCreceiptStrategy : list) {
			 

				switch (basicCreceiptStrategy.getType()) {
				case 1:
					basicCreceiptStrategyPage.setUnloadValueOne(basicCreceiptStrategy.getValue());
					break;

				case 2:
					basicCreceiptStrategyPage.setTimeValueOne(basicCreceiptStrategy.getValue());
					break;

				case 3:
					basicCreceiptStrategyPage.setQualityValueOne(basicCreceiptStrategy.getValue());
					break;

				case 4:
					basicCreceiptStrategyPage.setDumpValueOne(basicCreceiptStrategy.getValue());
					break;
					
				case 5:
					basicCreceiptStrategyPage.setTimeValueTwo(basicCreceiptStrategy.getValue());
					break;

				case 6:
					basicCreceiptStrategyPage.setQualityValueTwo(basicCreceiptStrategy.getValue());
					break;

				case 7:
					basicCreceiptStrategyPage.setDumpValueTwo(basicCreceiptStrategy.getValue());
					break;
					
				case 9:
					basicCreceiptStrategyPage.setUpFrameValueOne(basicCreceiptStrategy.getValue());
					break;
					
					
				case 10:
					basicCreceiptStrategyPage.setUpFrameValueTwo(basicCreceiptStrategy.getValue());
					break;
				default:
					basicCreceiptStrategyPage.setUnloadValueOne(basicCreceiptStrategy.getValue());
					break;
				}

			}  

		return basicCreceiptStrategyPage;
	}
	
	public void saveStrategyDictionaryCache(){
        // 将表中数据记录转换为页面对应的数据
		List<StrategyDictionary> list = this.basicCreceiptStrategyDao.listStrategyDictionary();
		RedisUtil.putCache(redisTemplate, "basicCreceiptStrategyList", list, 0);
		for (StrategyDictionary basicCreceiptStrategy : list) {
			 
			RedisUtil.putCache(redisTemplate, "strategyDictionary_"+basicCreceiptStrategy.getType(), basicCreceiptStrategy.getValue(), 0);
		}
	}
	
	public void saveStrategyDictionaryCache(String currentUid){
        // 将表中数据记录转换为页面对应的数据
		 String warehouseCode =UserUtil.getCurrOrgCode(currentUid, redisTemplate, "pc");
        List<StrategyDictionary> list = this.basicCreceiptStrategyDao.listStrategyDictionaryByWarehouseCode(warehouseCode);
		 
		RedisUtil.putCache(redisTemplate, "basicCreceiptStrategyList", list, 0);
		for (StrategyDictionary basicCreceiptStrategy : list) {
			 RedisUtil.putCache(redisTemplate, "strategyDictionary_"+warehouseCode+"_"+basicCreceiptStrategy.getType(), basicCreceiptStrategy.getValue(), 0); 
		}
	}

	/**.
	 * 重载方法
	 * 功能描述:根据键值获取对应的缓存
	 * @param key  在StaticCode定义了
	 *  
	 * @return
	 */
	@Override
	public int getStrategyDictionaryCache(String key) {
		Object value =   RedisUtil.getCache(redisTemplate, key);
		// 没有缓存值 查询数据库获取值在存入缓存
		if(null == value){
			saveStrategyDictionaryCache();
			value =  RedisUtil.getCache(redisTemplate, key);
		}
		// 缓存和数据库都没有值
		if(null == value){
			return 0;
		} 
		return Integer.parseInt(value.toString());
		
	}

	/**.
	 * 重载方法
	 * 功能描述:获取货基础策略缓存数据列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<StrategyDictionary> getBasicCreceiptStrategyListCache() {
		List<StrategyDictionary> list = (List<StrategyDictionary>) RedisUtil.getCache(redisTemplate, "basicCreceiptStrategyList");
		return list;
	}
	
	/**.
	 * 重载方法
	 * 功能描述:根据键值获取对应的缓存
	 * @param key  在StaticCode定义了
	 *  
	 * @return
	 */
	@Override
	public int getStrategyDictionaryCacheByUser(String key,String currentUid) {
		Object value =   RedisUtil.getCache(redisTemplate, key);
		// 没有缓存值 查询数据库获取值在存入缓存
		if(null == value){
			saveStrategyDictionaryCache(currentUid);
			value =  RedisUtil.getCache(redisTemplate, key);
		}
		// 缓存和数据库都没有值
		if(null == value){
			return 0;
		} 
		return Integer.parseInt(value.toString());
		
	}
}

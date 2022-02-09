package com.zkzn.les.basicInfo.util;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;

/**.
 * 
 * @author wangzhou
 * @date 2020年8月12日
 * @Description sap库存地点转换工具类
 */
public class SapStorageLocationTransfrom {

	
	/**.
	 * 
	 * @param storageLocation
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月12日
	 * @Description:将LES的库存地点转换为sap的工厂和库存地点
	 */
	public static Map<String,Object> lesToSapStorageLocation(RedisTemplate<String,String> redisTemplate,String storageLocation){
		
		Map<String,Object> resultMap = null;
		
		List<Map<String,Object>> list = (List<Map<String, Object>>) RedisUtil.getCache(redisTemplate, "sapAndLesStorageLocation");
		String tempStr = null;
		for(Map<String,Object> map:list){
			tempStr = ""+map.get("storageLocation");
			if(storageLocation.equals(tempStr)){
				resultMap = map;
			}
		}
		
		return resultMap;
	}
	
	/**.
	 * 
	 * @param factory
	 * @param storageLocation
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月12日
	 * @Description:将sap的工厂和库存地点转换为LES的库存地点
	 */
	public static String sapToLesStorageLocation(RedisTemplate<String,String> redisTemplate,String factory,String storageLocation){
		List<Map<String,Object>> list = (List<Map<String, Object>>) RedisUtil.getCache(redisTemplate, "sapAndLesStorageLocation");
		String tempfactory = null;
		String tempStorageLocation = null;
		for(Map<String,Object> map:list){
			tempfactory = ""+map.get("factoryCode");
			tempStorageLocation = ""+map.get("sapStorageLocation");
			if(tempfactory.equals(factory) && tempStorageLocation.equals(storageLocation)){
				return ""+map.get("storageLocation");
			}
		}
		return null;
	}
}

package com.zkzn.les.basicInfo.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class UserUtil {
	
	/**
	 * 获取当前登陆用户ID
	 * yzn
	 * 2018年07月26日
	 * @param request
	 * @return
	 */
	public static String getCurrentUserId(HttpServletRequest request,RedisTemplate<String, String> redisTemplate){
		String userid = "";
		try {
			Cookie[] cookies = request.getCookies();
			String token = "";
			for (Cookie cookie : cookies) {
				if ("token".equals(cookie.getName())) {
					token = cookie.getValue();
					userid = (String) redisTemplate.opsForHash().get(token, "userid");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userid;
	}
	/**.
	 * 
	 * 功能描述：获取用户信息
	 * 作者：wangzhou
	 * 时间：2019年6月26日
	 * @param request
	 * @param redisTemplate
	 * @return
	 */
	public static Map<String,Object> getCurrentUser(HttpServletRequest request,RedisTemplate<String, String> redisTemplate){
		Map<String,Object> userMap = null;
		Object user = null;
		try {
			Cookie[] cookies = request.getCookies();
			String token = "";
			for (Cookie cookie : cookies) {
				if ("token".equals(cookie.getName())) {
					token = cookie.getValue();
					user =  redisTemplate.opsForHash().get(token, "currUser");
				}
			}
			if(user!=null){
				userMap = BeanUtil.objectToMap(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userMap;
	}
	/**.
	 * 
	 * 功能描述：获取登录人的名称
	 * 作者：wangzhou
	 * 时间：2018年12月4日
	 * @param request
	 * @param redisTemplate
	 * @return
	 */
	public static String getCurrentUserName(HttpServletRequest request,RedisTemplate<String, String> redisTemplate){
		Object user = null;
		String userName = "";
		try {
			Cookie[] cookies = request.getCookies();
			String token = "";
			for (Cookie cookie : cookies) {
				if ("token".equals(cookie.getName())) {
					token = cookie.getValue();
					user =  redisTemplate.opsForHash().get(token, "currUser");
				}
			}
			if(user!=null){
				Class<? extends Object> clas= user.getClass();
				Field field = clas.getDeclaredField("userName");
				field.setAccessible(true);
				userName = (String) field.get(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userName;
	}
	/**.
	 * 
	 * 功能描述：获取当前仓库对应的仓库地点编码
	 * 作者：wangzhou
	 * 时间：2018年10月17日
	 * @param currUid
	 * @return
	 */
	public static List<String> getCurrStorageCode(String currUid,RedisTemplate<String, String> redisTemplate,String type){
		List<String> resultList = new ArrayList<String>();
		String currentStorage = getCacheStorage(type,redisTemplate,currUid);
		JSONObject jsonObject = JSON.parseObject(currentStorage);
		currentStorage = jsonObject.getString("storageLocation");
		if(currentStorage!=null && currentStorage.length()>0){
			if(currentStorage.indexOf(";")>0){
				resultList.addAll(Arrays.asList(currentStorage.split(";")));
			}else{
				resultList.add(currentStorage);
			}
		}
		return resultList;
	}
	/**.
	 * 
	 * 功能描述：获取当前仓库的仓库名称
	 * 作者：wangzhou 
	 * 时间：2018年10月18日
	 * @param currUid
	 * @param redisTemplate
	 * @param type
	 * @return
	 */
	public static String getCurrStorageName(String currUid,RedisTemplate<String, String> redisTemplate,String type){
		String currentStorage = getCacheStorage(type,redisTemplate,currUid);
		if(currentStorage!=null && currentStorage.length()>0){
			JSONObject jsonObject = JSON.parseObject(currentStorage);
			currentStorage = jsonObject.getString("orgName");
		}else{
			currentStorage="";
		}
		return currentStorage;
	}
	/**.
	 * 
	 * 功能描述：获取当前仓库的编码（组织表的编码）
	 * 作者：wangzhou
	 * 时间：2018年10月18日
	 * @param currUid
	 * @param redisTemplate
	 * @param type
	 * @return
	 */
	public static String getCurrOrgCode(String currUid,RedisTemplate<String, String> redisTemplate,String type){
		String currentStorage = getCacheStorage(type,redisTemplate,currUid);
		if(currentStorage!=null && currentStorage.length()>0){
			JSONObject jsonObject = JSON.parseObject(currentStorage);
			currentStorage = jsonObject.getString("orgCode");
		}else{
			currentStorage="";
		}
		return currentStorage;
	}
	/**
	 * 
	 * 功能描述：获取缓存中的库存地点
	 * 作者：wangzhou
	 * 时间：2018年10月18日
	 * @param type
	 * @param redisTemplate
	 * @param currUid
	 * @return
	 */
	public static String getCacheStorage(String type,RedisTemplate<String, String> redisTemplate,String currUid){
		String storageStr = "";
		if("app".equals(type)){
			storageStr =  (String) RedisUtil.getCache(redisTemplate, currUid+"_app_storage");
		}else if("pc".equals(type)){
			storageStr = (String) RedisUtil.getCache(redisTemplate, currUid+"_pc_storage");
		}
		return storageStr;
	}
}

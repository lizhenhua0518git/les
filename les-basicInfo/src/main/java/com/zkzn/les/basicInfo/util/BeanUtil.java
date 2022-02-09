package com.zkzn.les.basicInfo.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BeanUtil {
	
	private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);
	
	private BeanUtil() {
	    
	}
	
	/**.
	 * 功能描述: Map转对象
	 * 时间:2018年6月28日 上午9:57:15
	 * @param map
	 * @param beanClass
	 * @return
	 */
	public static Object mapToObject(Map<String,Object> map, Class<?> beanClass) {
		
		if	(map == null) {
			return null;
		}
		
		//反射取每个字段
		try {
			Object obj = beanClass.newInstance();
			
			Field[] fields = obj.getClass().getDeclaredFields();
			
			for (Field field : fields ) {
				int mod = field.getModifiers();
				//静态变量和final变量跳过
				if(Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
					continue;
				}
				field.setAccessible(true);    
				field.set(obj, map.get(field.getName()));		
			}
			return obj;
			} catch (InstantiationException | IllegalAccessException e) {
				logger.debug("beanClass:实例化错误"+e.getMessage());
				return null;
			}
	}
	/**.
	 * 
	 * 功能描述：对象转map，包含父类
	 * 作者：wangzhou
	 * 时间：2018年7月9日
	 * @param obj
	 * @return
	 */
	public static Map<String,Object> objectToMap(Object obj){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
		    List<Field> fieldList = new  ArrayList<>() ;
			Class tempClass = obj.getClass();
			while (tempClass != null) {//当父类为null的时候说明到达了最上层的父类(Object类).
			    fieldList.addAll(Arrays.asList(tempClass .getDeclaredFields()));
			    tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
			}
			for(Field field : fieldList){
				field.setAccessible(true);
				resultMap.put(field.getName(), field.get(obj));
			}
		} catch (Exception e) {
			logger.debug("beanClass:实例化错误"+e.getMessage());
			return null;
		}
		return resultMap;
	}
	/**
	 * 方法说明: 按照一定数量分解List
	 * @param list
	 * @param itemSize
	 * @return
	 * 作者: liyan
	 * 2019年3月20日
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public static List<List> splitList(List list,int itemSize){
		List<List> itemList = new ArrayList<List>(); 
		if(itemSize<=0){
			itemList.add(list);
			return itemList;
		}
		int arrSize = list.size()%itemSize==0?list.size()/itemSize:list.size()/itemSize+1;    
        for(int i=0;i<arrSize;i++) {    
        	List item = new ArrayList<>();
            for(int j=i*itemSize;j<=itemSize*(i+1)-1;j++) {    
                if(j<=list.size()-1) {    
                	item.add(list.get(j));    
                }    
            }    
            itemList.add(item);    
        } 
        return itemList;
	}
}

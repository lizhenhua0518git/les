package com.zkzn.les.uas.util;

import com.github.pagehelper.PageHelper;

import java.util.HashMap;
import java.util.Map;

public class PageUtil {

	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年3月24日下午4:48:29
	 * void
	 * @param obj
	 * 功能描述:分页使用
	 */
	@SuppressWarnings("unchecked")
	public static void  setPageParam(Object obj){
		
		Map<String,Object> param = new HashMap<String,Object>();
		if(obj instanceof  Map){
			param = (Map<String, Object>) obj;
		}else{
			param = BeanUtil.objectToMap(obj);
		}
		int startNum =1;
		int endNum = 10;
		if(param!=null && !param.isEmpty()){
			startNum = param.get("page")!=null?Integer.valueOf(""+param.get("page")):1;
			endNum = param.get("limit")!=null?Integer.valueOf(""+param.get("limit")):10;
		}
		
		PageHelper.startPage(startNum,endNum);
	}
}

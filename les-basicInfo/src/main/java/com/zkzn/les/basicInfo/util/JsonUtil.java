package com.zkzn.les.basicInfo.util;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.util.StringUtil;

public final class JsonUtil {
	 private JsonUtil() {
	        
	    }

	public static String toJson(Object object) {
		return JSON.toJSONString(object,
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.PrettyFormat,
				SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.WriteNullNumberAsZero,
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.DisableCircularReferenceDetect);
	}
	
	@SuppressWarnings("rawtypes")
	public static List<Map> jsonStrToList(String jsonStr){
		
		
		JSONObject  jsonObject = JSONObject.parseObject(jsonStr);
		
		
		List<Map> resultList = null;
		
		if(jsonObject.getInteger("code")==0){
			String dataStr = jsonObject.getString("data");
			if(!StringUtil.isEmpty(dataStr)){
				 resultList = 
						JSONObject.parseArray(dataStr, Map.class);
			}
		}
		return resultList;
	}
	
	public static Object jsonStrToObj(String jsonStr){

		JSONObject  jsonObject = JSONObject.parseObject(jsonStr);
		
		Object data = null;
		
		if(jsonObject.getInteger("code")==0){
			data = jsonObject.get("data");
		}
		
		return data;
	}
}

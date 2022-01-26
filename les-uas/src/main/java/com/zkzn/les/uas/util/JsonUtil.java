package com.zkzn.les.uas.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public final class JsonUtil {
	 private JsonUtil() {
	        
	    }

	public static String toJson(Object object) {
		//return "les-uas";
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
	
	
}

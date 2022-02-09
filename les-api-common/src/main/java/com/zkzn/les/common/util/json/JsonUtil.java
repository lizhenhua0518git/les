package com.zkzn.les.common.util.json;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang.StringUtils;
import java.util.List;
import java.util.Map;
/**
 * @Description : jsonObject工具类
 * @Author : leizhang
 * @Date 6:54 下午 2021/6/7 
 **/
public final class JsonUtil {
	private JsonUtil() {}
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
			if(!StringUtils.isEmpty(dataStr)){
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


	/**
	 * 功能描述：把JSON数据转换成指定的java对象
	 * @param jsonData JSON数据
	 * @param clazz 指定的java对象
	 * @return 指定的java对象
	 */
	public static <T> T getJsonToBean(String jsonData, Class<T> clazz) {
		return JSON.parseObject(jsonData, clazz);
	}

	/**
	 * 功能描述：把java对象转换成JSON数据
	 * @param object java对象
	 * @return JSON数据
	 */
	public static String getBeanToJson(Object object) {
		return JSON.toJSONString(object);
	}

	/**
	 * 功能描述：把JSON数据转换成指定的java对象列表
	 * @param jsonData JSON数据
	 * @param clazz 指定的java对象
	 * @return List<T>
	 */
	public static <T> List<T> getJsonToList(String jsonData, Class<T> clazz) {
		return JSON.parseArray(jsonData, clazz);
	}

	/**
	 * 功能描述：把JSON数据转换成较为复杂的List<Map<String, Object>>
	 * @param jsonData JSON数据
	 * @return List<Map<String, Object>>
	 */
	public static List<Map<String, Object>> getJsonToListMap(String jsonData) {
		return JSON.parseObject(jsonData, new TypeReference<List<Map<String, Object>>>() {
		});
	}
}

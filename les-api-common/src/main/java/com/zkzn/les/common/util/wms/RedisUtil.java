package com.zkzn.les.common.util.wms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public final class RedisUtil {


	@Autowired
	private RedisTemplate<String, Object> redisTemplate;



	private static long TIME = 30*60;
	private static String KEY_EMERGENCY_RECORD = "E";
	private static String KEY_ORDINARY_RECORD = "N";
	private static String KEY_RECEIVE_GROUP="Y";
	private static String KEY_VIRTUAL_TRAY="X";
	private static String KEY_DUMP_NUMBER="NUM";
	public static Set<String>set=new HashSet<String>();
    private RedisUtil() {

    }

	/**.
	 *
	 * 功能描述：List Object存入redis缓存
	 * 作者：wangzhou
	 * 时间：2018年7月9日
	 * @param redisTemplate
	 * @param keyStr
	 * @param value
	 * @param expire
	 */

	@SuppressWarnings("unchecked")
	public static void putCache(RedisTemplate redisTemplate,String keyStr,Object value,long expire){
		redisTemplate.execute(new RedisCallback<Long>(){

			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				// TODO Auto-generated method stub

				byte [] key = keyStr.getBytes();

				byte [] valueByte = SerializationUtils.serialize((Serializable) value);

				connection.set(key,valueByte);

				if(expire>0){
					connection.expire(key, expire);
				}
				return 1L;
			}

		});
	}
	/**.
	 *
	 * 功能描述：获取缓存
	 * 作者：wangzhou
	 * 时间：2018年7月9日
	 * @param redisTemplate
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object getCache(RedisTemplate redisTemplate,String key){
		Object obj = redisTemplate.execute(new RedisCallback<Object>(){

			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				// TODO Auto-generated method stub

				byte [] keyByte = key.getBytes();

				byte [] value = connection.get(keyByte);

				if(value==null){
					return null;
				}

				return SerializationUtils.deserialize(value);
			}

		});
		return obj;
	}
	/**
	 *
	 * 功能描述：通过StringRedisSerializer解析值 用于值为数字的值
	 * 作者：wangzhou
	 * 时间：2019年4月14日
	 * @param redisTemplate
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object getRedisSerializer(RedisTemplate redisTemplate,String key){
		Object obj = redisTemplate.execute(new RedisCallback<Object>(){

			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				// TODO Auto-generated method stub

				byte [] keyByte = key.getBytes();

				byte [] value = connection.get(keyByte);

				if(value==null){
					return null;
				}
				StringRedisSerializer serializer = new StringRedisSerializer();
				return serializer.deserialize(value);
			}

		});
		return obj;
	}
	/**
	 * 排队号
	 * @author ljh
	 * @param type
	 * @param redisTemplate
	 * @return
	 */
	public static String lineUpNo(boolean type,RedisTemplate redisTemplate,String warehouse){
		String key ="";
		if(type) {
			key = KEY_EMERGENCY_RECORD;//紧急
		}else {
			key = KEY_ORDINARY_RECORD;//普通
		}
		ValueOperations<String, String> valueOpe = redisTemplate.opsForValue();
		long wait_num = valueOpe.increment(key+warehouse, 1);
		set.add(key+warehouse);
		int num=(int) wait_num;
		redisTemplate.expire(key, 1*24*3600, TimeUnit.SECONDS);
		if(String.valueOf(num).length()<2) {
			return key+"0"+String.valueOf(num);
		}
		return key+(int) wait_num;
	}
	/**
	 * 分组编号
	 * @param redisTemplate
	 * @return
	 */
	public static int getReceiveGroup(RedisTemplate redisTemplate) {
		ValueOperations<String, String> valueOpe = redisTemplate.opsForValue();
		String key=KEY_RECEIVE_GROUP;
		long wait_num = valueOpe.increment(key, 1);
		int num=(int) wait_num;
		redisTemplate.expire(key, 1*24*3600, TimeUnit.SECONDS);
		return num;
	}

	public static String virtualTray(RedisTemplate redisTemplate){
		String key =KEY_VIRTUAL_TRAY;
		ValueOperations<String, String> valueOpe = redisTemplate.opsForValue();
		long wait_num = valueOpe.increment(key, 1);
		int num=(int) wait_num;
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String time=sdf.format(date);
		redisTemplate.expire(key, 1*24*3600, TimeUnit.SECONDS);
		String value="";
		for(int i=String.valueOf(num).length();i<6;i++) {
			value+="0";
		}
		return key+time+value+num;
	}
	/**
	 * 转储单号
	 * @param redisTemplate
	 * @return
	 */
	public static String getDumpNumber(String storageLocation,RedisTemplate redisTemplate) {
		ValueOperations<String, String> valueOpe = redisTemplate.opsForValue();
		String key=KEY_DUMP_NUMBER;
		long wait_num = valueOpe.increment(key, 1);
		int num=(int) wait_num;
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String time=sdf.format(date);
		redisTemplate.expire(key, 1*24*3600, TimeUnit.SECONDS);
		String value="";
		for(int i=String.valueOf(num).length();i<6;i++) {
			value+="0";
		}
		return storageLocation+time+value+num;
	}



	/**
	 * @Description TD: hash递增 如果不存在,就会创建一个 并把新增后的值返回 失效时间为一天
	 * @param key 键
	 * @param by 要增加几(大于0)
	 * @Return double
	 * @Author sangsang
	 * @Date 2020/12/1 13:26
	 **/
	public  Long hincrADay(String key, Long by) {
		boolean flag = hasKey(key);
		Long increment = redisTemplate.opsForValue().increment(key, by);
		if (!flag) {
			redisTemplate.expire(key, 1, TimeUnit.DAYS);
		}
		return increment;
	}

	/**
	 * 判断key是否存在
	 * @param key 键
	 * @return true 存在 false不存在
	 */
	public boolean hasKey(String key) {
		try {
			return redisTemplate.hasKey(key);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	////存搜索记录
	//public static void saveSearchRecord(StringRedisTemplate stringRedisTemplate,String redisKey, String logVal) {
	//	//用ZSet格式存储，有序的去重集合
	//	ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
	//	zSetOperations.add(redisKey, logVal, System.currentTimeMillis());
	//	Long size = zSetOperations.size(redisKey);
	//	//仅保留最近5条
	//	if (size > 5) {
	//		zSetOperations.removeRange(redisKey, 0, size - 5 - 1);
	//	}
	//	//设置缓存时间一个月
	//	stringRedisTemplate.expire(redisKey, 30, TimeUnit.DAYS);
	//}
	//
	////取搜索记录
	//public static List getRecentSearchList(StringRedisTemplate stringRedisTemplate, String key) {
	//	List list = new ArrayList<>();
	//	//String key = organCode + userId;
	//	ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
	//	//取出最近5条,按时间倒序排列
	//	Set<ZSetOperations.TypedTuple<String>> range = zSetOperations.reverseRangeWithScores(key, 0, 5 - 1);
	//	for (ZSetOperations.TypedTuple<String> tuple : range) {
	//		list.add(JSONObject.parse(tuple.getValue()));
	//	}
	//	return list;
	//}


}

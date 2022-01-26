package com.zkzn.les.common.util.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.util.*;

/**
 * @ClassName RedisAliUtil
 * @Description redis 增强工具类,非redisTemplate
 * @Author zhanglei
 * Date 2021/1/8 9:45
 * @Version 1.0
 **/
public class RedisAliUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(RedisAliUtil.class);

    /**
     * 从集群中获取 连接
     * @param key
     * @param value
     * @return
     */
    public static String set(String key, String value) {
        String result = null;
        try (ShardedJedis jedis = RedisManager.getShardeJedisObject()) {
            result = jedis.set(key, value);
        }
        return result;
    }

    /**
     * 单借点放值，指定库
     * @param key
     * @param value
     * @param dbIndex
     * @return
     */
    public static String set(String key, String value, int dbIndex) {
        String result = null;
        try (Jedis jedis = RedisManager.getAliJedisObject(dbIndex)) {
            result = jedis.set(key, value);
        }
        return result;
    }

    /**
     * 集群 map 放值
     * @param key
     * @param field
     * @param value
     * @return
     */
    public static Long hset(String key, String field, String value) {
        Long result = null;
        try (ShardedJedis jedis = RedisManager.getShardeJedisObject()) {
            result = jedis.hset(key, field, value);
        }
        return result;
    }

    /**
     * 获取剩余有效期
     * @param key
     * @param dbIndex
     * @return
     */
    public static Long ttl(String key,int dbIndex) {
        Long result = null;
        try (Jedis jedis = RedisManager.getAliJedisObject(dbIndex)) {
            result = jedis.ttl(key);
        }
        return result;
    }

    /**
     * 单节点 放map 值
     * @param key
     * @param field
     * @param value
     * @param dbIndex
     * @return
     */
    public static Long hset(String key, String field, String value, int dbIndex) {
        Long result = null;
        try (Jedis jedis = RedisManager.getAliJedisObject(dbIndex)) {
            result = jedis.hset(key, field, value);
        }
        return result;
    }

    public static String hmset(String key, Map<String, String> hash) {
        String result = null;
        try (ShardedJedis jedis = RedisManager.getShardeJedisObject()) {
            result = jedis.hmset(key, hash);
        }
        return result;
    }

    public static String hmset(String key, Map<String, String> hash, int dbIndex) {
        String result = null;
        try (Jedis jedis = RedisManager.getAliJedisObject(dbIndex)) {
            result = jedis.hmset(key, hash);
        }
        return result;
    }

    public static String get(String key) {
        String result = null;
        try (ShardedJedis jedis = RedisManager.getShardeJedisObject()) {
            result = jedis.get(key);
        }
        return result;
    }

    public static String get(String key, int dbIndex) {
        String result = null;
        try (Jedis jedis = RedisManager.getAliJedisObject(dbIndex)) {
            result = jedis.get(key);
        }
        return result;
    }

    public static String hget(String key, String field) {
        String result = null;
        try (ShardedJedis jedis = RedisManager.getShardeJedisObject()) {
            result = jedis.hget(key, field);
        }
        return result;
    }

    public static String hget(String key, String field, int dbIndex) {
        String result = null;
        try (Jedis jedis = RedisManager.getAliJedisObject(dbIndex)) {
            result = jedis.hget(key, field);
        }
        return result;
    }

    public static List<String> hmget(String key, String... fields) {
        List<String> list = null;
        try (ShardedJedis jedis = RedisManager.getShardeJedisObject()) {
            list = jedis.hmget(key, fields);
        }
        return list;
    }

    public static List<String> hmget(int indexDb, String key, String... fields) {
        List<String> list = null;
        try (Jedis jedis = RedisManager.getAliJedisObject(indexDb)) {
            list = jedis.hmget(key, fields);
        }
        return list;
    }

    public static Long delKeys(String key, int dbIndex) {
        Long result = null;
        try (Jedis jedis = RedisManager.getAliJedisObject(dbIndex)) {
            result = jedis.del(key);
        }
        return result;
    }

    public static Long delKeys(String key) {
        Long result = null;
        try (ShardedJedis jedis = RedisManager.getShardeJedisObject()) {
            result = jedis.del(key);
        }
        return result;
    }

    public static Long hdel(int dbIndex, String key, String... fields) {
        Long result = null;
        try (Jedis jedis = RedisManager.getAliJedisObject(dbIndex)) {
            result = jedis.hdel(key, fields);
        }
        return result;
    }

    public static Long hdel(String key, String... fields) {
        Long result = null;
        try (ShardedJedis jedis = RedisManager.getShardeJedisObject()) {
            result = jedis.hdel(key, fields);
        }
        return result;
    }

    public static Long expireKey(String key, int seconds, int dbIndex) {
        Long result = null;
        try (Jedis jedis = RedisManager.getAliJedisObject(dbIndex)) {
            result = jedis.expire(key, seconds);
        }
        return result;
    }

    public static boolean exists(String key, int dbIndex) {
        boolean result = false;
        try (Jedis jedis = RedisManager.getAliJedisObject(dbIndex)) {
            result = jedis.exists(key);
        }
        return result;
    }

    public static Map<String, String> hgetAll(String key, int dbIndex) {
        Map<String, String> map = null;
        try (Jedis jedis = RedisManager.getAliJedisObject(dbIndex)) {
            map = jedis.hgetAll(key);
        }
        return map;
    }

    /**
     * 已知key，模糊搜索hashkey
     *
     * @param key
     * @param scanParam
     * @param dbIndex
     * @return
     */
    public static Map<String, String> hscan(String key, String scanParam, int dbIndex) {
        Map<String, String> map = new HashMap<>();
        try (Jedis jedis = RedisManager.getAliJedisObject(dbIndex)) {
            boolean flag = true;
            ScanParams scanParams = new ScanParams();
            scanParams.match(scanParam);
            String cur = ScanParams.SCAN_POINTER_START;
            ScanResult<Map.Entry<String, String>> result = null;
            while (flag) {
                result = jedis.hscan(key, cur, scanParams);
                cur = result.getCursor();
                for (Map.Entry<String, String> set : result.getResult()) {
                    map.put(set.getKey(), set.getValue());
                }
                if (cur.equals("0")) {
                    flag = false;
                }
            }
        }
        return map;
    }

    /**
     * 模糊搜索key
     *
     * @param scanParam
     * @param dbIndex
     * @return
     */
    public static Set<String> scan(String scanParam, int dbIndex) {
        Set<String> map = new HashSet<String>();
        try (Jedis jedis = RedisManager.getAliJedisObject(dbIndex)) {
            boolean flag = true;
            ScanParams scanParams = new ScanParams();
            scanParams.match(scanParam);
            String cur = ScanParams.SCAN_POINTER_START;
            ScanResult<String> result = null;
            while (flag) {
                result = jedis.scan(cur, scanParams);
                cur = result.getCursor();
                for (String set : result.getResult()) {
                    map.add(set);
                }
                if (cur.equals("0")) {
                    flag = false;
                }
            }
        }
        return map;
    }

    /**
     * 高效添加多个key的hashkey、hashval
     *
     * @param map
     * @param dbIndex
     */
    public static void hmsetByPipline(Map<String, Map<String, String>> map, int dbIndex) {
        try (Jedis jedis = RedisManager.getAliJedisObject(dbIndex)) {
            Pipeline pipeline = jedis.pipelined();
            for (Map.Entry<String, Map<String, String>> val : map.entrySet()) {
                pipeline.hmset(val.getKey(), val.getValue());
            }
            pipeline.sync();
        }
    }

    /**
     * 高效删除多个key里的hashkey
     *
     * @param map
     * @param dbIndex
     */
    public static void hdelByPipline(Map<String, List<String>> map, int dbIndex) {
        try (Jedis jedis = RedisManager.getAliJedisObject(dbIndex)) {
            Pipeline pipeline = jedis.pipelined();
            for (Map.Entry<String, List<String>> val : map.entrySet()) {
                pipeline.hdel(val.getKey(), val.getValue().toArray(new String[val.getValue().size()]));
            }
            pipeline.sync();
        }
    }

    /**
     * 通过管道，查询多个key的值
     *
     * @param map     key:key, val:hashkey
     * @param dbIndex
     * @return
     */
    public static List<String> hmgetByPipline(Map<String, String> map, int dbIndex) {
        try (Jedis jedis = RedisManager.getAliJedisObject(dbIndex)) {
            Pipeline pipeline = jedis.pipelined();
            List<String> result = new ArrayList<>();
            List<Response<List<String>>> list = new ArrayList<>();
            for (Map.Entry<String, String> val : map.entrySet()) {
                Response<List<String>> response = pipeline.hmget(val.getKey(), val.getValue());
                list.add(response);
            }
            pipeline.sync();
            for (Response<List<String>> responseList : list) {
                result.addAll(responseList.get());
            }
            return result;
        }
    }

    /**
     * 得到指定分区下所有的hash值
     *
     * @param dbIndex 指定的分区
     * @param hashKey 值
     */
    public static List<String> getDbAll(int dbIndex, String hashKey) {
        try (Jedis jedis = RedisManager.getAliJedisObject(dbIndex)) {
            Set<String> keySet = jedis.keys("*");
            Pipeline pipeline = jedis.pipelined();
            List<String> result = new ArrayList<>();
            List<Response<List<String>>> list = new ArrayList<>();
            for (String key : keySet) {
                Response<List<String>> response = pipeline.hmget(key, hashKey);
                list.add(response);
            }
            pipeline.sync();
            for (Response<List<String>> responseList : list) {
                result.addAll(responseList.get());
            }
            return result;
        }
    }

    /**
     * 递增Key
     *
     * @param key
     * @param dbIndex
     * @return
     */
    public static Long incrKey(String key, int dbIndex) {
        Long result = null;
        try (Jedis jedis = RedisManager.getAliJedisObject(dbIndex)) {
            result = jedis.incr(key);
        }
        return result;
    }

    /**
     * 递减Key
     *
     * @param key
     * @param dbIndex
     * @return
     */
    public static Long decrKey(String key, int dbIndex) {
        Long result = null;
        try (Jedis jedis = RedisManager.getAliJedisObject(dbIndex)) {
            result = jedis.decr(key);
        }
        return result;
    }

    /**
     * @param key
     * @param dbIndex
     * @return
     */
    public static Long incrByKey(String key, Long val, int dbIndex) {
        Long result = null;
        try (Jedis jedis = RedisManager.getAliJedisObject(dbIndex)) {
            result = jedis.incrBy(key, val);
        }
        return result;
    }

    /**
     * 减Key - val
     *
     * @param key
     * @param dbIndex
     * @return
     */
    public static Long decrByKey(String key, Long val, int dbIndex) {
        Long result = null;
        try (Jedis jedis = RedisManager.getAliJedisObject(dbIndex)) {
            result = jedis.decrBy(key, val);
        }
        return result;
    }


    /**
     * 从指定的分区里查找需要的key里的所有数据
     * @param keys  key
     * @param dbIndex
     * @return
     */
    public static Map<String, Map<String, String>> getAllByPiple(Collection<String> keys, int dbIndex) {
        Map<String, Response<Map<String, String>>> responseMap = new HashMap<>();
        try (Jedis jedis = RedisManager.getAliJedisObject(dbIndex)) {
            Pipeline pipeline = jedis.pipelined();
            for (String key : keys) {
                Response<Map<String, String>> response = pipeline.hgetAll(key);
                responseMap.put(key, response);
            }
            pipeline.sync();
        }

        Map<String, Map<String, String>> resultMap = new HashMap<>();
        for (Map.Entry<String, Response<Map<String, String>>> response : responseMap.entrySet()) {
            resultMap.put(response.getKey(), response.getValue().get());
        }
        return resultMap;
    }

    /**
     * 从指定的分区里查找需要的key里的所有数据
     */
    public static Map<String, String> getAllValueByPiple(Collection<String> keys, int dbIndex) {
        Map<String, Response<String>> responseMap = new HashMap<>();
        try (Jedis jedis = RedisManager.getAliJedisObject(dbIndex)) {
            Pipeline pipeline = jedis.pipelined();
            for (String key : keys) {
                Response<String> response = pipeline.get(key);
                responseMap.put(key, response);
            }
            pipeline.sync();
        }

        Map<String, String> resultMap = new HashMap<>();
        for (Map.Entry<String, Response<String>> response : responseMap.entrySet()) {
            resultMap.put(response.getKey(), String.valueOf(response.getValue().get()));
        }
        return resultMap;
    }

    /**
     * List类型，根据指定的list类型的key，对其表头端方向获取指定区间的值
     * @param key
     * @param start 开始位置从0计数
     * @param end   结束位置（既可以从前往后第一个0，第二个1这样顺序计数,<br/>也可以从后向前推算，如-1为最后一个，-2为倒数第二个）
     * @return
     */
    public static List<String> lrange(int index, String key, long start, long end) {
        List<String> msgList = null;
        try (Jedis jedis = RedisManager.getAliJedisObject(index)) {
            msgList = jedis.lrange(key, start, end);
        }
        return msgList;
    }


   /***
    * @Discription: getDbAllKey
    * @param dbIndex
    * @return java.util.List<java.lang.String>
    * @Author: zhanglei on 2021/1/8 13:29
    */
    public static List<String> getDbAllKey(int dbIndex) {
        try (Jedis jedis = RedisManager.getAliJedisObject(dbIndex)) {
            Set<String> keySet = jedis.keys("*");
            Pipeline pipeline = jedis.pipelined();
            List<String> result = new ArrayList<>();
            List<Response<String>> list = new ArrayList<>();
            for (String key : keySet) {
                Response<String> response = pipeline.get(key);
                list.add(response);
            }
            pipeline.sync();
            for (Response<String> responseList : list) {
                result.add(responseList.get());
            }
            return result;
        }
    }
}
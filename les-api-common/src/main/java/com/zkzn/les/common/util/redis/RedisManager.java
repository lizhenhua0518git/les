package com.zkzn.les.common.util.redis;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName RedisManager
 * @Description redis 连接管理类
 * @Author zhanglei
 * Date 2021/1/8 9:25
 * @Version 1.0
 **/
public class RedisManager {
    private static Logger LOGGER = LoggerFactory.getLogger(RedisManager.class);
    //------------redis 连接池配置常量类---------------------------------
    private static final int DB_0 = 0;
    private static final int DB_1 = 1;
    private static final int DB_2 = 2;
    private static final int DB_3 = 3;
    private static final int DB_4 = 4;
    private static final int DB_5 = 5;
    private static final int DB_6 = 6;
    private static final int DB_7 = 7;
    private static final int DB_8 = 8;
    private static final int DB_9 = 9;
    private static final int DB_10 = 10;
    private static final int DB_11 = 11;
    private static final int DB_12 = 12;
    private static final int DB_13 = 13;
    private static final int DB_14 = 14;
    private static final int DB_15 = 15;
    private static final String SPACE = " ";
    private static final String COMMA = ",";
    private static final String EMPTY = "";
    //------------redis 连接池常量类end---------------------------------

    private static JedisPool aliPool;

    private static ShardedJedisPool shardedJedisPool;

    private static final Splitter splitter = Splitter.on(",").omitEmptyStrings();

    static {
        try {
            //创建jedis池配置实例
            JedisPoolConfig config = new JedisPoolConfig();
            List<JedisShardInfo> jdsInfoList = Lists.newArrayList();

            Properties props = new Properties();
            props.load(RedisManager.class.getClassLoader().getResourceAsStream("db.properties"));

            //redis 最大连接数
            config.setMaxTotal(Integer.valueOf(props.getProperty("jedis.pool.maxActive")));
            //最大空闲数
            config.setMinIdle(Integer.valueOf(props.getProperty("jedis.pool.maxIdle")));
            //超时时间
            config.setMaxWaitMillis(Long.valueOf(props.getProperty("jedis.pool.maxWait")));

            config.setTestOnBorrow(Boolean.valueOf(props.getProperty("jedis.pool.testOnBorrow")));

            config.setTestOnReturn(Boolean.valueOf(props.getProperty("jedis.pool.testOnReturn")));
            if (!Boolean.valueOf(props.getProperty("isRedisPool"))) {
                //pwd
                String pwd = props.getProperty("redis.password");
                if (StringUtils.isEmpty(pwd) || pwd == null) {
                    pwd = null;
                }
                aliPool = new JedisPool(config, props.getProperty("redis.ip"), Integer.valueOf(props.getProperty("redis.port")),
                        Integer.valueOf(props.getProperty("redis.timeout")), pwd);
            } else {
                //分片redis
                String ips = props.getProperty("redis.shard.ip");
                String ports = props.getProperty("redis.shard.port");
                String pwds = props.getProperty("redis.shard.password");

                ips = ips.replace(SPACE, EMPTY);
                ports = ports.replace(SPACE, EMPTY);
                pwds = pwds.replace(SPACE, EMPTY);

                List<String> ipArr = splitter.splitToList(ips);
                List<String> portArr = splitter.splitToList(ports);
                List<String> pwdArr = splitter.splitToList(pwds);

                JedisShardInfo jedisShardInfo = null;
                for (int i = 0; i < ipArr.size(); i++) {
                    jedisShardInfo = new JedisShardInfo(ipArr.get(i), Integer.valueOf(portArr.get(i)));
                    jedisShardInfo.setSoTimeout(Integer.valueOf(props.getProperty("redis.timeout")));
                    if (pwdArr != null && pwdArr.size() > 0) {
                        jedisShardInfo.setPassword(pwdArr.get(i));
                    }
                    jdsInfoList.add(jedisShardInfo);
                }
                shardedJedisPool = new ShardedJedisPool(config, jdsInfoList);
            }
        } catch (IOException e) {
            LOGGER.error("redis 配置初始化异常：{}", e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error("-----------redis初始化失败，请检查配置------------------");
        }
    }

    /**
     * 获取单节点资源
     *
     * @param dbIndex
     * @return
     */
    public static Jedis getAliJedisObject(int dbIndex) {
        Jedis je = aliPool.getResource();
        je.select(dbIndex);
        return je;
    }

    /**
     * 释放资源
     *
     * @param jedis
     */
    public static void releaseAliJedisObject(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    public static Jedis getAliJedisObject(){
        return  aliPool.getResource();
    }

    /**
     * 获取资源 集群
     *
     * @return
     */
    public static ShardedJedis getShardeJedisObject() {
        return shardedJedisPool.getResource();
    }
}
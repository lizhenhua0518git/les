package com.zkzn.les.common.util.redis;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: les
 * @Package: com.zkzn.les.wms.util
 * @ClassName: RedisNoUtil
 * @Author: 胡志明
 * @Description:
 * @Date: 2021/5/17 15:22
 */
public class RedisNoUtil {

    //用作存放redis中的key
    public static final String DS_ORDER_KEY = "DS";//点收任务
    //点收主任务编号
    public static final String DS_ORDER_KEY_M = "DS-M";
    //调拨单据号
    public static final String DB_ORDER_KEY = "DB";
    //质检主表任务号
    public static final String ZJ_ORDER_KEY_M = "ZJ-M";
    //质检子表任务号
    public static final String ZJ_ORDER_KEY = "ZJ";

    //拆盘主表任务编码
    public static final String CP_ORDER_KEY_M = "CP-M";

    //拆盘子表任务编号
    public static final String CP_ORDER_KEY = "CP";

    public static final String UPPER_FRAME_KEY = "SJ";

    public static final String PD_ORDER_KEY = "PD";//盘点任务

    //排队号
    public static final String QUEUE_CODE = "queue_code";
    //SequenceService类中公用部分，传入制定的key和prefix
    public static String getSeqNo(String key,RedisTemplate redisTemplate) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        //设置过期时间，这里设置为当天的23:59:59
        Date expireDate = calendar.getTime();
        //返回当前redis中的key的最大值
        Long seq = generate(redisTemplate, key, expireDate);
        //获取当天的日期，格式为yyyyMMdd
        String date = new SimpleDateFormat("yyyyMMdd").format(expireDate);
        //生成六位的序列号，如果seq不够六位，seq前面补0，
        //如果seq位数超过了六位，那么无需补0直接返回当前的seq
        String sequence = StringUtils.leftPad(seq.toString(), 6, "0");
        //拼接业务编号
        String seqNo = key + date + sequence;
        return seqNo;
    }

    /**
     * @param key
     * @param expireTime <i>过期时间</i>
     * @return
     */
    public static long generate(RedisTemplate<?,?> redisTemplate,String key,Date expireTime) {
        //RedisAtomicLong为原子类，根据传入的key和redis链接工厂创建原子类
        RedisAtomicLong counter = new RedisAtomicLong(key,redisTemplate.getConnectionFactory());
        //设置过期时间
        counter.expireAt(expireTime);
        //返回redis中key的值，内部实现下面详细说明
        return counter.incrementAndGet();
    }

    /***
     * @Discription: 获取带有过期时间的自增涨值
     * @param key
     * @param redisTemplate
     * @return java.lang.Long
     * @Author: zhanglei on 2021/6/15 13:27
     */
    public static Long getSeqNoByExpireTime(String key,RedisTemplate redisTemplate,long expiresTime) {
        RedisAtomicLong counter = new RedisAtomicLong(key,redisTemplate.getConnectionFactory());
        counter.expire(expiresTime,TimeUnit.MILLISECONDS);
        return counter.incrementAndGet();
    }

    /***
     * @Discription: TTl到每天凌晨
     * @param
     * @return java.lang.Long
     * @Author: zhanglei on 2021/6/15 13:29
     */
    public static long getSecondsNextEarlyMorning() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis() - System.currentTimeMillis();
    }

    /***
     * @Discription: 排队号时间获取 每天凌晨更新排队序列
     * @param key
     * @param redisTemplate
     * @return long
     * @Author: zhanglei on 2021/6/15 13:31
     */
    public static long getSeqNoByExpireTime(String key,RedisTemplate redisTemplate) {
        long secondsNextEarlyMorning = getSecondsNextEarlyMorning();
        return getSeqNoByExpireTime(key,redisTemplate,secondsNextEarlyMorning);
    }
}

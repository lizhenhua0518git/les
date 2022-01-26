package com.zkzn.les.common.util.idUtil;
/**
 * @ClassName IdUtil
 * @Description id生成策略
 * @Author leizhang
 * Date 2021/6/7 3:33 下午
 * @Version 1.0
 **/
public class IdUtil {
    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUUID() {
        return UUID.randomUUID().toString(true);
    }

    /**
     * 获取随机UUID，使用性能更好的ThreadLocalRandom生成UUID
     */
    public static String fastUUID() {
        return UUID.fastUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 简化的UUID，去掉了横线

     */
    public static String fastSimpleUUID() {
        return UUID.fastUUID().toString(true);
    }

    /**
     * 创建Twitter的Snowflake 算法生成器。
     * @return {@link Snowflake}
     */
    public static Snowflake createSnowflake(long workerId, long datacenterId) {
        return new Snowflake(workerId, datacenterId);
    }
}

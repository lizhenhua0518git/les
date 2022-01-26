package com.zkzn.les.common.util.date;

import java.sql.Timestamp;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SystemClock
 * @Description 系统时钟
 * @Author leizhang
 * Date 2021/6/7 3:45 下午
 * @Version 1.0
 **/
public class SystemClock {
    /** 时钟更新间隔，单位毫秒 */
    private final long period;
    /** 现在时刻的毫秒数 */
    private volatile long now;

    /**
     * 构造
     * @param period 时钟更新间隔，单位毫秒
     */
    public SystemClock(long period) {
        this.period = period;
        this.now = System.currentTimeMillis();
        scheduleClockUpdating();
    }

    /**
     * 开启计时器线程
     */
    private void scheduleClockUpdating() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable, "System Clock");
            thread.setDaemon(true);
            return thread;
        });
        scheduler.scheduleAtFixedRate(() -> now = System.currentTimeMillis(), period, period, TimeUnit.MILLISECONDS);
    }

    /**
     * @return 当前时间毫秒数
     */
    private long currentTimeMillis() {
        return now;
    }

    /**
     * 单例
     * @author Looly
     *
     */
    private static class InstanceHolder {
        public static final SystemClock INSTANCE = new SystemClock(1);
    }

    /**
     * @return 当前时间
     */
    public static long now() {
        return InstanceHolder.INSTANCE.currentTimeMillis();
    }

    /**
     * @return 当前时间字符串表现形式
     */
    public static String nowDate() {
        return new Timestamp(InstanceHolder.INSTANCE.currentTimeMillis()).toString();
    }
}

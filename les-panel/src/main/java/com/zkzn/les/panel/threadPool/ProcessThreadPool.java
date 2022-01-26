package com.zkzn.les.panel.threadPool;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName ProcessThreadPool
 * @Description 线程池对象
 * @Author zhanglei
 * Date 2021/1/11 16:45
 * @Version 1.0
 **/
public class ProcessThreadPool {
    private static final int corePoolSize = 10;            // 核心线程数（默认线程数）
    private static final int maxPoolSize = 20;                // 最大线程数
    private static final int keepAliveTime = 10;            // 允许线程空闲时间（单位：默认为秒）
    private static final int queueCapacity = 500;            // 缓冲队列数
    private static final String threadNamePrefix = "default-async-"; // 线程池名前缀

    private ProcessThreadPool() {
    }

    private volatile static ThreadPoolTaskExecutor instance= null;

    public static ThreadPoolTaskExecutor initTaskExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setThreadNamePrefix(threadNamePrefix+"panel");
        pool.setCorePoolSize(corePoolSize);
        pool.setMaxPoolSize(maxPoolSize);
        pool.setKeepAliveSeconds(keepAliveTime);
        pool.setQueueCapacity(queueCapacity);
        // 直接在execute方法的调用线程中运行,线程拒绝策略
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化
        pool.initialize();
        return pool;
    }

    /**
     * 创建单例对象
     * @return
     */
    public static ThreadPoolTaskExecutor getInstance() {
        if (instance == null) {
           synchronized (ProcessThreadPool.class) {
               if (instance == null) {
                    instance = initTaskExecutor();
               }
           }
        }
        return instance;
    }

    /**
     * 获取线程池对象
     * @return
     */
    public static ThreadPoolTaskExecutor init() {
        return getInstance();
    }
}
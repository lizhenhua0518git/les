package com.zkzn.les.stock.thread;

import com.zkzn.les.stock.thread.request.Request;
import com.zkzn.les.stock.thread.request.RequestProcessThread;
import com.zkzn.les.stock.thread.request.RequestQueue;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName RequestProcessThreadPool
 * @Description 线程池执行对象
 * @Author zhanglei
 * Date 2021/6/30 14:35
 * @Version 1.0
 **/
public class RequestProcessThreadPool {

    private static final int corePoolSize = 10;       		// 核心线程数（默认线程数）
    private static final int maxPoolSize = 20;			    // 最大线程数
    private static final int keepAliveTime = 10;			// 允许线程空闲时间（单位：默认为秒）
    private static final int queueCapacity = 50;			// 缓冲队列数
    private static final String threadNamePrefix = "default-async-"; // 线程池名前缀

    /**
     * @Description : 初始化时创建10个线程任务，每个线程任务维护20个内存队列
     * @Author : leizhang
     **/
    public RequestProcessThreadPool(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = initTaskExecutor();
        //维护十个队列
        RequestQueue requestQueue = RequestQueue.getInstance();
        for(int i=0;i<10;i++){
            ArrayBlockingQueue<Request> queue = new ArrayBlockingQueue<>(100);
            requestQueue.addQueue(queue);
            threadPoolTaskExecutor.submit(new RequestProcessThread(queue));
        }
    }
    /**
     * @Description : 单例创建对象
     **/
    private static class Singleton{
        private static RequestProcessThreadPool instance;
        static {
            instance = new RequestProcessThreadPool();
        }
        private static RequestProcessThreadPool getInstance(){
            return  instance;
        }
    }

    public static RequestProcessThreadPool getInstance(){
        return Singleton.getInstance();
    }

    public static RequestProcessThreadPool init(){
        return getInstance();
    }

    public ThreadPoolTaskExecutor initTaskExecutor(){
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setThreadNamePrefix(threadNamePrefix+"ProductInventCache");
        pool.setCorePoolSize(corePoolSize);
        pool.setMaxPoolSize(maxPoolSize);
        pool.setKeepAliveSeconds(keepAliveTime);
        pool.setQueueCapacity(queueCapacity);
        // 直接在execute方法的调用线程中运行
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化
        pool.initialize();
        return pool;
    }
}
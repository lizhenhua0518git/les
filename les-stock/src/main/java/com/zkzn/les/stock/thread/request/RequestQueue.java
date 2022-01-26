package com.zkzn.les.stock.thread.request;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName RequestQueue
 * @Description 请求队列 一个线程绑定一个队列
 * @Author zhanglei
 * Date 2021/6/30 14:37
 * @Version 1.0
 **/
public class RequestQueue {
    /**
     * @Description : 内存队列
     **/
    private List<ArrayBlockingQueue<Request>> queues = Lists.newArrayList();

    /**
     * @Description : 标识位,全局判断对redis读写请求的顺序
     **/
    private Map<String,Boolean> flagMap = new ConcurrentHashMap<>();
    /**
     * @Description : 单例创建对象
     **/
    private static class Singleton{
        private static RequestQueue instance;
        static {
            instance = new RequestQueue();
        }
        private static RequestQueue getInstance(){
            return  instance;
        }
    }

    public static RequestQueue getInstance(){
        return Singleton.getInstance();
    }

    public void addQueue(ArrayBlockingQueue<Request> queue){
        this.queues.add(queue);
    }

    public int queueSize(){
        return queues.size();
    }

    /**
     * @Description : 获取队列
     * @Author : leizhang
     * @param index
     * @return java.util.concurrent.ArrayBlockingQueue<com.alibaba.eshop.inventory.thread.request.Request>
     **/
    public ArrayBlockingQueue<Request> getQueue(int index){
        return queues.get(index);
    }


    /**
     * @Description :
     * @Author : leizhang
     **/
    public Map<String,Boolean> getFlagMap(){
        return flagMap;
    }
}
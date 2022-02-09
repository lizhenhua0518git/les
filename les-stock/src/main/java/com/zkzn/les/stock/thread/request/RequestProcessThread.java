package com.zkzn.les.stock.thread.request;

import com.zkzn.les.stock.constants.StockConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * @ClassName RequestProcessThread
 * @Description 请求线程处理器
 * @Author zhanglei
 * Date 2021/6/30 14:38
 * @Version 1.0
 **/
public class RequestProcessThread implements Callable {
    //缓存一致问题日志 打印在同一目录下
    private static Logger LOGGER = LoggerFactory.getLogger(RequestProcessThread.class);
    private ArrayBlockingQueue<Request> queue;

    public RequestProcessThread(ArrayBlockingQueue<Request> queue){
        this.queue = queue;
    }
    @Override
    public Boolean call() throws Exception {
        try{
            for(;;){
                //从队列消费Request
                Request request = queue.take();
                boolean forceRefresh = request.forceRefresh();
                Map<String, Object> requestParam = request.getRequestParam();
                String key = requestParam.get(StockConstants.STORAGE_LOCATION).toString().concat(requestParam.get(StockConstants.MATERIAL_CODE).toString());
                //请求去重,是否强制刷新
                if (!forceRefresh) {
                    RequestQueue requestQueue = RequestQueue.getInstance();
                    Map<String, Boolean> flagMap = requestQueue.getFlagMap();
                    if (request instanceof ProductInventoryDBUpdateRequest) {
                        //如果是一个更新请求，请求入队列，更新标识位，true，当前请求类型是更新请求
                      flagMap.put(key,true);
                    }else if (request instanceof ProductInventoryCacheRefreshRequest) {
                        Boolean flag = flagMap.get(key);
                        if (flag == null) {
                            //说明前置节点不存在 读取缓存或者更新的操作
                            flagMap.put(key,false);
                        }
                        if (flag != null && flag) {
                            //说明前置节点存在更新操作，可直接读取缓存
                            flagMap.put(key,false);
                        }
                        //前置节点存在更新请求，和读取缓存请求，直接过滤掉
                        if (flag != null && !flag) {
                            //刷新缓存
//                            return true;
                            continue;
                        }
                    }
                }
                //更新缓存，刷新redis
                request.process();
                /**
                 * @Description :
                 * 假如，执行完了一个读请求之后，假设数据已经刷新到redis中
                 * 但是可能存在redis的LRU算法会把redis中的数据清理掉，此时的flag ！= null and ！flase的
                 * 会直接执行第三种可能，return true  不会刷新缓存，此时缓存中依然没有当前productId对应的数据
                 * 此时标志位依然是false;
                 **/
            }
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
        }
        return true;
    }
}
package com.zkzn.les.stock.service.impl;

import com.zkzn.les.stock.constants.StockConstants;
import com.zkzn.les.stock.service.RequestAsyncProcessService;
import com.zkzn.les.stock.thread.request.Request;
import com.zkzn.les.stock.thread.request.RequestQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @ClassName RequestAsyncProcessServiceImpl
 * @Description 请求异步执行service
 * @Author zhanglei
 * Date 2021/6/30 14:43
 * @Version 1.0
 **/
@Service("RequestAsyncProcessService")
public class RequestAsyncProcessServiceImpl implements RequestAsyncProcessService {

    //缓存一致问题日志 打印在同一目录下
    private Logger logger = LoggerFactory.getLogger(RequestAsyncProcessServiceImpl.class);
    @Override
    public void process(Request request) {
        ArrayBlockingQueue<Request> routingQueue = getRoutingQueue(request.getRequestParam());
        try {
            routingQueue.put(request);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(),e);
        }
    }

    private ArrayBlockingQueue<Request> getRoutingQueue(Map<String,Object> requestParam){
        RequestQueue requestQueue = RequestQueue.getInstance();
        String key = requestParam.get(StockConstants.STORAGE_LOCATION).toString().concat(requestParam.get(StockConstants.MATERIAL_CODE).toString());
        //计算key对应的hash
        int h;
        int hash = (key == null)?0:( h = key.hashCode())^(h>>>16);
        int index = (requestQueue.queueSize()-1) & hash;
        return requestQueue.getQueue(index);
    }
}
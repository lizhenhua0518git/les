package com.zkzn.les.stock.service;

import com.zkzn.les.stock.thread.request.Request;

/**
 * @ClassName RequestAsyncProcessService
 * @Description 请求异步执行
 * @Author zhanglei
 * Date 2021/6/30 14:43
 * @Version 1.0
 **/
public interface RequestAsyncProcessService {
    void process(Request request);
}

package com.zkzn.les.stock.thread.request;

import java.util.Map;

/**
 * @ClassName Request
 * @Description 顶级接口 请求处理对象
 * @Author zhanglei
 * Date 2021/6/30 14:36
 * @Version 1.0
 **/
public interface Request {
    /**
     * @Description : 业务处理
     **/
    void process();

    Map<String,Object> getRequestParam();
    /**
     * @Description : 强制刷新的标识
     **/
    boolean forceRefresh();
}

package com.zkzn.les.stock.thread.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @ClassName ProductInventoryCacheRefreshRequest
 * @Description 库存查询执行对象
 * @Author zhanglei
 * Date 2021/6/30 14:41
 * @Version 1.0
 **/
public class ProductInventoryCacheRefreshRequest implements Request{

    //缓存一致问题日志 打印在同一目录下
    private Logger logger = LoggerFactory.getLogger(ProductInventoryCacheRefreshRequest.class);


    private Boolean forceRefresh;
    /**
     * @Description : 商品库存Service
     **/
    private Map<String,Object> requestParam;

    public ProductInventoryCacheRefreshRequest(Map<String,Object> requestParam,
                                               boolean forceRefresh) {
        this.requestParam = requestParam;
        this.forceRefresh = forceRefresh;
    }

    @Override
    public void process() {
        //从数据库中查询最新的商品库存数量
        try {

        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }

    @Override
    public Map<String, Object> getRequestParam() {
        return requestParam;
    }

    @Override
    public boolean forceRefresh() {
        return forceRefresh;
    }
}
package com.zkzn.les.stock.thread.request;

import com.zkzn.les.stock.service.StorageBinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

/**
 * @ClassName ProductInventoryDBUpdateRequest
 * @Description 库存更新执行器
 * @Author zhanglei
 * Date 2021/6/30 14:39
 * @Version 1.0
 **/
public class ProductInventoryDBUpdateRequest implements Request{
    //缓存一致问题日志 打印在同一目录下
    private Logger logger = LoggerFactory.getLogger(ProductInventoryDBUpdateRequest.class);
    /**
     * @Description : 商品库存
     **/
    private Map<String,Object> storageBinParam;

    private StorageBinService storageBinService;

    public ProductInventoryDBUpdateRequest(Map<String,Object> storageBinParam,StorageBinService storageBinService){
        this.storageBinParam = storageBinParam;
        this.storageBinService = storageBinService;
    }


    @Override
    public void process() {
        try{
            storageBinService.removeProductInventoryCache(storageBinParam);
            storageBinService.updateProductInventory(storageBinParam);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }

    @Override
    public Map<String, Object> getRequestParam() {
        return storageBinParam;
    }

    @Override
    public boolean forceRefresh() {
        return false;
    }
}
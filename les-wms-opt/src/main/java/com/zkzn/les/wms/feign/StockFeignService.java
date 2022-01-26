package com.zkzn.les.wms.feign;

import com.zkzn.les.wms.config.FeignConfig;
import com.zkzn.les.wms.feign.hystrix.FeignServiceFallbackFactory;
import com.zkzn.les.wms.feign.hystrix.StockFeignServiceFallbackFactory;
import com.zkzn.les.wms.upperFrame.pojo.UpperPositionPojo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @ClassName 库存服务
 * @Description
 * @Author zhanglei
 * Date 2021/6/29 10:15
 * @Version 1.0
 **/
@FeignClient(value = "les-stock-providers",configuration = FeignConfig.class)
public interface StockFeignService {
    @RequestMapping("/stock/storageBin/updateProductInventory")
    String updateProductInventory(Map<String,Object> param);

    @RequestMapping("/stock/inStock/updateMaterialInStockList")
    String updateMaterialInStockList(List<Map<String,Object>> param);

    @RequestMapping("/stock/inStock/updateMaterialStorageBin")
    String updateMaterialStorageBin(List<UpperPositionPojo> upperPositionPojos);
}

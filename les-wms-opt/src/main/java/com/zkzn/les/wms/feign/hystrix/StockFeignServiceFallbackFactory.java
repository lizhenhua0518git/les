package com.zkzn.les.wms.feign.hystrix;

import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.wms.feign.StockFeignService;
import com.zkzn.les.wms.feign.StrategyFeignService;
import com.zkzn.les.wms.upperFrame.pojo.UpperPositionPojo;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName StockFeignServiceFallbackFactory
 * @Description 熔断
 * @Author zhanglei
 * Date 2021/7/7 14:54
 * @Version 1.0
 **/
@Component
public class StockFeignServiceFallbackFactory implements FallbackFactory<StockFeignService> {
    private static Logger LOGGER = LoggerFactory.getLogger(StockFeignServiceFallbackFactory.class);
    @Override
    public StockFeignService create(Throwable throwable) {
        return new StockFeignService() {
            @Override
            public String updateProductInventory(Map<String, Object> param) {
                LOGGER.error(throwable.getMessage());
                return Result.toJsonUseApp(Ecode.FAIL, throwable.getMessage());
            }

            @Override
            public String updateMaterialInStockList(List<Map<String, Object>> param) {
                LOGGER.error(throwable.getMessage());
                return Result.toJsonUseApp(Ecode.FAIL, throwable.getMessage());
            }

            @Override
            public String updateMaterialStorageBin(List<UpperPositionPojo> upperPositionPojos) {
                LOGGER.error(throwable.getMessage());
                return Result.toJsonUseApp(Ecode.FAIL, throwable.getMessage());
            }
        };
    }
}

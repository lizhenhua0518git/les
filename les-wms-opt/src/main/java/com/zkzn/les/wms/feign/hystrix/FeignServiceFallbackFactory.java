package com.zkzn.les.wms.feign.hystrix;

import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.wms.feign.StrategyFeignService;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName FeignServiceFallbackFactory
 * @Description feign 熔断
 * @Author zhanglei
 * Date 2021/7/7 14:44
 * @Version 1.0
 **/
@Component
public class FeignServiceFallbackFactory implements FallbackFactory<StrategyFeignService> {
private static Logger LOGGER = LoggerFactory.getLogger(FeignServiceFallbackFactory.class);
    @Override
    public StrategyFeignService create(Throwable throwable) {
        return new StrategyFeignService() {
            @Override
            public String getUploadStrategyResult(Map<String, Object> param) {
                LOGGER.error(throwable.getMessage());
                return Result.toJsonUseApp(Ecode.FAIL, throwable.getMessage());
            }

            @Override
            public String getStoragePositionResult(Map<String, Object> param) {
                LOGGER.error(throwable.getMessage());
                return Result.toJsonUseApp(Ecode.FAIL, throwable.getMessage());
            }
        };
    }
}
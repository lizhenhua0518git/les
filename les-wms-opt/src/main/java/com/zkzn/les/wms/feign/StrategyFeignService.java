package com.zkzn.les.wms.feign;

import com.zkzn.les.wms.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @ClassName StrategyFeignService
 * @Description 策略服务
 * @Author zhanglei
 * Date 2021/6/29 10:15
 * @Version 1.0
 **/
@FeignClient(value = "les-tactics-providers",configuration = FeignConfig.class)
public interface StrategyFeignService {
    /***
     * @Discription: 卸货点信息获取
     * @param param
     * @return java.lang.String
     * @Author: zhanglei on 2021/7/5 14:58
     */
    @RequestMapping("/tactics/uploadStrategy/getUploadStrategyResult")
    String getUploadStrategyResult(Map<String,Object> param);

    /***
     * @Discription: 上架仓位信息获取
     * @param param
     * @return java.lang.String
     * @Author: zhanglei on 2021/7/5 14:59
     */
    @RequestMapping("/tactics/storageStrategy/getStorageStrategyResult")
    String getStoragePositionResult(Map<String,Object> param);
}

package com.zkzn.les.wms.feign;

import com.zkzn.les.wms.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient(value = "les-tactics-providers",configuration = FeignConfig.class)
public interface TacticsFeignService {

    @RequestMapping("/tactics/storageStrategy/getStorageCode")
    String getStorageCode(Map<String,Object> param);

}

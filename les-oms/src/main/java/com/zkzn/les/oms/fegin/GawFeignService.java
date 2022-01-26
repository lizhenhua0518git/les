package com.zkzn.les.oms.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.zkzn.les.oms.config.FeignConfig;



/**.
 * 
 * @author wangzhou
 * @date 2020年8月31日
 * @Description 总装立库远程调用
 */
@FeignClient(value="les-gawApi-provider",configuration = FeignConfig.class,
fallbackFactory=GawFeignServiceFallback.class)
public interface GawFeignService {

	
	/**
	 * 
	 * @param jsonStr
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月31日
	 * @Description:推送生产计划给总装立库
	 */
	@PostMapping("/gaw/pushProcessOrder")
	String pushProcessOrder(@RequestBody String jsonStr);
}

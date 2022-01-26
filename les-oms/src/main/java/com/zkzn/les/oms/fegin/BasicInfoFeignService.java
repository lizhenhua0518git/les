package com.zkzn.les.oms.fegin;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zkzn.les.oms.config.FeignConfig;


/**.
 * 
 * @author wangzhou
 * @date 2020年8月12日
 * @Description:基础信息模块 远程调用类
 */
@FeignClient(value="les-basicInfo-provider",configuration = FeignConfig.class,
fallbackFactory=BasicInfoFeignServiceFallback.class)
public interface BasicInfoFeignService {

	@RequestMapping(value="/basicInfo/dictItems/listDictItemByTypes")
	public String listDictItemByTypes(@RequestBody List<String> dictType);
}

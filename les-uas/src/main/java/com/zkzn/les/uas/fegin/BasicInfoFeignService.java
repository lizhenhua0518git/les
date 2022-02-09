package com.zkzn.les.uas.fegin;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.zkzn.les.uas.config.FeignConfig;
 

/**.
 * 
 * @author wangzhou
 * 基础信息服务的远程调用服务类
 */
@FeignClient(value="les-basicInfo-provider",configuration = FeignConfig.class)
public interface BasicInfoFeignService {

	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年3月31日下午2:12:48
	 * String
	 * @param params
	 * @return
	 * 功能描述:获取载具信息
	 */
	@GetMapping("/basicInfo/dictItems/listDictItemByTypes")
	String listDictItemByTypes(@RequestBody List<String> dictType);
	
	 
}

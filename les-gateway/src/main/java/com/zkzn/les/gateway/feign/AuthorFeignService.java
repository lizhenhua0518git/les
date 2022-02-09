package com.zkzn.les.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zkzn.les.gateway.config.FeignConfig;


/**.
 * 
 * @author wangzhou
 * @date 2020年7月17日
 * @Description：远程调用权限模块获取是否有访问权限
 */
@FeignClient(value="les-uas-provider",configuration = FeignConfig.class)
public interface AuthorFeignService {

	/**.
	 * 
	 * @param url
	 * @return
	 * @Author:wangzhou
	 * @date:2020年7月17日
	 * @Description:通过当前url判断用户是否有权限访问此链接
	 */
	@GetMapping("/auth/user/isPermission")
	String isPermission(@RequestParam("url" )String url,@RequestParam("token" )String token);
}

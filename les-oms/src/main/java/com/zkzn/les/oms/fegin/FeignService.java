package com.zkzn.les.oms.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value="les-uas-provider")
public interface FeignService {

	@GetMapping("/user/pageUser")
	public String pageUser();
}

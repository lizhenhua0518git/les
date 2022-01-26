package com.zkzn.les.uas.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="les-oms-provider")
public interface FeignService {

	@GetMapping(value = "/order/feginService")
    String feginService(@RequestParam("name" )String name);
}

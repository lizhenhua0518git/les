package com.zkzn.les.oms.fegin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class GawFeignServiceFallback implements FallbackFactory<GawFeignService>{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public GawFeignService create(Throwable arg0) {
		// TODO Auto-generated method stub
		logger.debug("gaw服务已经熔断");
		GawFeignService gawFeignService = new GawFeignServiceImpl();
		return gawFeignService;
	}

}

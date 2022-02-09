package com.zkzn.les.oms.fegin;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BasicInfoFeignServiceFallback implements FallbackFactory<BasicInfoFeignService>{

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public BasicInfoFeignService create(Throwable arg0) {
		// TODO Auto-generated method stub
		logger.debug("basicInfo服务已经熔断");
		BasicInfoFeignService basicInfoFeignService = new BasicInfoFeignServiceImpl();
		return basicInfoFeignService;
	}

}

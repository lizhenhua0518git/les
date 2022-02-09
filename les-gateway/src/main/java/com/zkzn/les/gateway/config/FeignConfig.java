package com.zkzn.les.gateway.config;


import com.zkzn.les.gateway.util.StringUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**.
 * 
 * @author wangzhou
 * @date 2020年7月17日
 * @Description:远程调用携带token
 */
public class FeignConfig implements RequestInterceptor{

	private Logger logger =LoggerFactory.getLogger(getClass());
	
	@Override
	public void apply(RequestTemplate requestTemplate) {
		// TODO Auto-generated method stub
		Collection<String> tokens = requestTemplate.queries().get("token");
		for(String str:tokens){
			logger.debug("feignCofing==token====="+str);
			if(StringUtil.isNotEmpty(str)){
				requestTemplate.header("Authorization", "Bearer "+str);
			}
		}
		
	}

}
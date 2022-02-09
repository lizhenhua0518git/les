package com.zkzn.les.uas.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;
/**.
 * 
 * @author wangzhou
 * feign远程调用配置携带token
 */
public class FeignConfig implements RequestInterceptor{

	@Override
	public void apply(RequestTemplate requestTemplate) {
		// TODO Auto-generated method stub
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //添加token
        requestTemplate.header("Authorization", request.getHeader("Authorization"));
	}

}

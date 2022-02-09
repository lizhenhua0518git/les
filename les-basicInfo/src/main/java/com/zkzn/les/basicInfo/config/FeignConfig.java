package com.zkzn.les.basicInfo.config;

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
        String token = request.getHeader("Authorization");
        if(token==null){
        	String paramToken = request.getParameter("token");
        	if(paramToken!=null){
        		token = "Bearer "+paramToken;
        	}
        }
        //添加token
        requestTemplate.header("Authorization", token);
	}

}

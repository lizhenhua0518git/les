package com.zkzn.les.gateway.config;

import com.zkzn.les.gateway.util.Ecode;
import com.zkzn.les.gateway.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 
 * @author wangzhou
 * @date 2020年7月17日
 * @Description:未授权登陆信息处理类
 */
public class AuthenticationFailHandle implements ServerAuthenticationEntryPoint  {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	

	@Override
	public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
		// TODO Auto-generated method stub
		ServerHttpResponse response = exchange.getResponse();
		logger.debug("未获取到登陆信息");
        return writeErrorMessage(response, Ecode.ACCESS_DENIED_ERROR);
	}

	private Mono<Void> writeErrorMessage(ServerHttpResponse response, Ecode ecode) {
		
        String result = Result.toJson(ecode,null);
        DataBuffer buffer= null;
		try {
			buffer = response.bufferFactory().wrap(result.getBytes("UTF-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getHeaders().add("Content-type", "text/html;charset=UTF-8");
		
        return response.writeWith(Mono.just(buffer));
    }
}

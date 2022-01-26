/*package com.zkzn.les.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;


public class LoginFilter implements ReactiveAuthorizationManager<AuthorizationContext>{

	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	

	@Override
	public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authenticationContext) {
		// TODO Auto-generated method stub
		
		ServerHttpRequest request =  authenticationContext.getExchange().getRequest();
		String httpMethod = request.getMethod().name();
		 //如果是OPTIONS的请求直接放过
        if(HttpMethod.OPTIONS.name().equals(httpMethod)){
            return Mono.just(new AuthorizationDecision(true));
        }
        logger.debug("验证token:"+authenticationContext.getExchange().getPrincipal());
        authentication.map(a ->  {
			logger.debug("验证token:"+a.toString());
			logger.debug("验证token:"+a.toString());
			logger.debug("验证token:"+a.toString());
			logger.debug("验证token:"+a.isAuthenticated());
			return null;
        });
        //return Mono.just(new AuthorizationDecision(true));
		return authentication.map(a ->  {
			logger.debug("验证token:"+a.toString());
            if(a.isAuthenticated()){
                return new AuthorizationDecision(true);
            }else{
                return new AuthorizationDecision(false);
            }
        }).defaultIfEmpty(new AuthorizationDecision(false));
	}

}
*/
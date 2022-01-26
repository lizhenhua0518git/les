package com.zkzn.les.gateway.config;
/*package com.zkzn.les.gateway.config;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.server.context.SecurityContextServerWebExchange;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebExchange.Builder;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.zkzn.les.gateway.pojo.SecurityUser;
import com.zkzn.les.gateway.util.BeanUtil;
import com.zkzn.les.gateway.util.RedisUtil;


import io.netty.util.internal.StringUtil;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

@Component
public class AccessTokenFilter implements  WebFilter {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public Mono<Void> filter(ServerWebExchange ctx, WebFilterChain chain) {
		// TODO Auto-generated method stub
	
		ServerHttpRequest request =  ctx.getRequest();
		HttpHeaders headers = request.getHeaders();
		logger.debug("headers:"+headers);
		List<String> tokens = headers.get("token");
		logger.debug("authHeaderValue:"+tokens);
		List<String> authHeaderValues = headers.get("Authorization");
		logger.debug("authHeaderValue1:"+authHeaderValues);
		String authHeaderValue = null;
		if(tokens!=null && tokens.size()>0){
			authHeaderValue = tokens.get(0);
		}
		if(authHeaderValues!=null && authHeaderValues.size()>0){
			authHeaderValue = authHeaderValues.get(0);
			authHeaderValue = authHeaderValue.substring(OAuth2AccessToken.TokenType.BEARER.getValue().length()).trim();
			int commaIndex = authHeaderValue.indexOf(',');
			if (commaIndex > 0) {
				authHeaderValue = authHeaderValue.substring(0, commaIndex);
			}
		}
		if(!StringUtil.isNullOrEmpty(authHeaderValue)){
			Map<String,Object> userMap =  (Map<String,Object>) RedisUtil.getCache(redisTemplate, authHeaderValue);
			if(userMap!=null && !userMap.isEmpty()){//没有获取到登陆信息
				UserDetails userDetails = loadUser(userMap);
				
				UsernamePasswordAuthenticationToken authentication =  
	                     new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());  

				ReactiveSecurityContextHolder.withAuthentication(authentication);
				ServerWebExchange newCtx = new SecurityContextServerWebExchange(ctx, ReactiveSecurityContextHolder.getContext());
				SecurityContextHolder.getContext().setAuthentication(authentication);
				logger.debug("newCtx:"+newCtx.getPrincipal());
				return chain.filter(newCtx);
			}
		}
		
		
		return chain.filter(ctx);
	}

	
	private UserDetails loadUser(Map<String,Object> userMap){
	
		SecurityUser securityUser = (SecurityUser) BeanUtil.mapToObject(userMap, SecurityUser.class);

		return  securityUser;
	}
}
*/
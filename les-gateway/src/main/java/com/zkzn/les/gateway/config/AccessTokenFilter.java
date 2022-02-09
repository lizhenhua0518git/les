package com.zkzn.les.gateway.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.zkzn.les.gateway.util.StringUtil;

import reactor.core.publisher.Mono;

public class AccessTokenFilter implements  WebFilter{

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		// TODO Auto-generated method stub
		String token = exchange.getRequest().getQueryParams().getFirst("token");
		if(StringUtil.isNotEmpty(token)){
			ServerHttpRequest host = exchange.getRequest().mutate().header("Authorization", "Bearer "+token).build();
	        //将现在的request 变成 change对象 
	        ServerWebExchange build = exchange.mutate().request(host).build();
	        return chain.filter(build);

		}
		return chain.filter(exchange);
	}

}

package com.zkzn.les.gateway.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;

@EnableWebFluxSecurity
public class SecurityConfig {

	@Autowired
	private RdbaAccessManager rdbaAccessManager;
	
	@Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf().disable().authorizeExchange()
                .pathMatchers("/auth/login").permitAll()
                .anyExchange()
                .access(rdbaAccessManager);
        http.oauth2ResourceServer().opaqueToken()
        .and().authenticationEntryPoint(serverAuthenticationEntryPoint())//处理登陆信息失效
        .accessDeniedHandler(tokenAccessDenied())//处理登陆用户没有权限
        .and().addFilterAt(new AccessTokenFilter(), SecurityWebFiltersOrder.HTTPS_REDIRECT)//处理通过参数传递token的情况
        ;
        return http.build();
    }

	@Bean
	public ServerAccessDeniedHandler tokenAccessDenied(){
		ServerAccessDeniedHandler tokenAccessDenied = new TokenAccessDenied();
		return tokenAccessDenied;
	}
	@Bean
	public ServerAuthenticationEntryPoint serverAuthenticationEntryPoint(){
		ServerAuthenticationEntryPoint serverAuthenticationEntryPoint = new AuthenticationFailHandle();
		return serverAuthenticationEntryPoint;
	}

}

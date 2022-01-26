package com.zkzn.les.basicInfo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableOAuth2Sso
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private AuthenticationTokenFilter authenticationTokenFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.requestMatchers().antMatchers(HttpMethod.OPTIONS, "/**");
		http.csrf().disable() //关闭csrf保护
        .antMatcher("/**") //使用以任意开头的url
        .authorizeRequests() // 配置路径拦截，表明路径访问所对应的权限，角色，认证信息
        .antMatchers("/","/uploadAddress/**" ,"/login/**","/login**","/druid/**","/swagger-ui.html",
        		"/swagger-resources/**","/webjars/**","/v2/api-docs","/media/upload/**","/basicInfo/**") //控制不同的url接受不同权限的用户访问
        .permitAll()//　允许所有人访问
        .anyRequest()
        .authenticated(); //除了以上请求都需要身份认证
		http.exceptionHandling().authenticationEntryPoint(new CustomizeAuthenticationEntryPoint());
		http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}
}

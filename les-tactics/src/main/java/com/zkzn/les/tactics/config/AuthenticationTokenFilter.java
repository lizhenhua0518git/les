package com.zkzn.les.tactics.config;

import com.zkzn.les.common.util.lang.BeanUtil;
import com.zkzn.les.common.util.redis.RedisUtil;
import com.zkzn.les.tactics.pojo.SecurityUser;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

@SuppressWarnings("unchecked")
@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Enumeration<String> headers = request.getHeaders("Authorization");
		String authHeaderValue = request.getParameter("token");
		while (headers.hasMoreElements()) {
			String value = headers.nextElement();
			authHeaderValue = value.substring(OAuth2AccessToken.BEARER_TYPE.length()).trim();
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
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		filterChain.doFilter(request, response);

	}
	private UserDetails loadUser(Map<String,Object> userMap){
		SecurityUser securityUser = (SecurityUser) BeanUtil.mapToObject(userMap, SecurityUser.class);
		return  securityUser;
	}
}

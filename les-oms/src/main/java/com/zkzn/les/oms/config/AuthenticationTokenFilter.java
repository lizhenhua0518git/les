package com.zkzn.les.oms.config;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zkzn.les.common.pojo.user.SecurityUser;
import com.zkzn.les.common.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.netty.util.internal.StringUtil;

@SuppressWarnings("unchecked")
@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub


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


		String userName = (String) userMap.get("userName");
		String password = (String) userMap.get("password");
		String id =  (String) userMap.get("id");

		Collection<GrantedAuthority> grantedAuths  = (Collection<GrantedAuthority>) userMap.get("authSet");


		SecurityUser securityUser = new SecurityUser();
		securityUser.setAuthorities(grantedAuths);
		securityUser.setPassword(password);
		securityUser.setUserName(userName);
		securityUser.setId(id);

		return  securityUser;
	}


}

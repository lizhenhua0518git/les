package com.zkzn.les.uas.config;

import com.zkzn.les.uas.util.Ecode;
import com.zkzn.les.uas.util.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**.
 * ajax请求拦截未登陆信息
 * @author wangzhou
 *
 */
@Component
public class CustomizeAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(Result.toJson(Ecode.LOGIN_LOSE, "用户未登陆"));
	}

}

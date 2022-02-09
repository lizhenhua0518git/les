package com.zkzn.les.oms.config;

import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


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

		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,OPTIONS");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, content-Type, Accept, Authorization");


		PrintWriter out =  response.getWriter();

		out.write(Result.toJson(Ecode.LOGIN_LOSE, "用户未登陆"));
		out.flush();
        out.close();
	}

}

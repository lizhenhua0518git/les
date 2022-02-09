package com.zkzn.les.uas.config;

import com.zkzn.les.uas.util.Ecode;
import com.zkzn.les.uas.util.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**.
 * 
 * @author wangzhou
 * 登陆失败处理器
 */
@Component
public class FailureHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE"); 
		response.setHeader("Access-Control-Max-Age", "3600");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out =  response.getWriter();
        //登录账号密码错误
        out.write(Result.toJsonUseApp(Ecode.FAIL, "账号/密码错误"));
        out.flush();
        out.close();
	}

}

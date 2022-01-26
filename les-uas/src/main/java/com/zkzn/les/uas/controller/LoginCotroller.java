package com.zkzn.les.uas.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.druid.util.StringUtils;
import com.zkzn.les.uas.pojo.User;
import com.zkzn.les.uas.service.UserService;
import com.zkzn.les.uas.util.Ecode;
import com.zkzn.les.uas.util.MD5;
import com.zkzn.les.uas.util.Result;


@Controller
public class LoginCotroller {

	private Logger logger = LoggerFactory.getLogger(LoginCotroller.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
    public String loginPage() {
        return "login";
    }
	
	@GetMapping("/simpleLogout")
	@ResponseBody
	public String logout(HttpServletRequest request, HttpServletResponse response){
		try{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    if (auth != null) {//清除认证
		      new SecurityContextLogoutHandler().logout(request, response, auth);
		    }
		}catch(Exception e){
			logger.debug("退出登录失败"+e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, "退出登录成功");
	}
	
	/**.
	 * 功能描述: 无登陆状态修改密码
	 * wanghaojie
	 * 时间:  2019年06月05日
	 * @return
	 */
	@RequestMapping(value="/login/changePassWord",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String changePassWordNew(@RequestBody Map<String,Object> params){
		try{
			String phone = params.get("phone")!=null?(String)params.get("phone"):null;
			String oldPassWord = params.get("oldPassWord")!=null?(String)params.get("oldPassWord"):null;
			String userPassword = params.get("userPassword")!=null?(String)params.get("userPassword"):null;
			if(StringUtils.isEmpty(phone)||StringUtils.isEmpty(oldPassWord)||StringUtils.isEmpty(userPassword)){
				return Result.toJsonUseApp(Ecode.PARAMNAMEERROR, null);
			}
			//  /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,16}$/;
			String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,16}$";
			if(!userPassword.matches(pattern)) {
				return Result.toJsonUseApp(Ecode.FAIL, "新密码不符合规则，请由字母大小写,数字组成,不少于8位,不大于16位");
			}
			//验证旧密码是否正确：把用户页面输入的旧密码MD5加密后和数据库的密码比对
			User user = userService.getUserByPhoneAndPwd(phone,MD5.getStringMD5(oldPassWord));
			if(user!=null){
				String stringMD5 = MD5.getStringMD5(userPassword);
				user.setUserPassword(stringMD5);
				userService.updateUser(user);
				return Result.toJsonUseApp(Ecode.SUCCESS, "1");
			}else{
				return Result.toJsonUseApp(Ecode.FAIL, "旧密码错误，请重新输入！");
			}
		}catch(Exception e){
			logger.debug("修改密码失败！"+e.getMessage());
			return Result.toJsonUseApp(Ecode.FAIL, "密码修改失败！");
		}
	}
}

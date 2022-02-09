package com.zkzn.les.uas.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zkzn.les.uas.pojo.SecurityUser;
import com.zkzn.les.uas.service.OrgnizationService;
import com.zkzn.les.uas.service.ResourceService;
import com.zkzn.les.uas.service.UserService;
import com.zkzn.les.uas.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageInfo;
import com.zkzn.les.uas.fegin.FeignService;
import com.zkzn.les.uas.pojo.Resource;
import com.zkzn.les.uas.pojo.User;


@Controller
@RequestMapping(value="/user")
@CrossOrigin
public class UserController {

	@Autowired(required=true)
	private UserService userService;

	@Autowired
	private RedisTemplate redisTemplate;

	@GetMapping(value = "/me",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String getUser(HttpServletResponse response,HttpServletRequest request ) {

        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        Map<String,Object> resultMap = new HashMap<String,Object>();
        String currentUrl = request.getParameter("curHref");
		if(currentUrl!=null && currentUrl.indexOf("/iles")>0){
			currentUrl = currentUrl.substring(currentUrl.indexOf("/iles"));
		}else{
			resultMap.put("loginFlag", "false");
			return Result.toJson(Ecode.SUCCESS, resultMap);
		}
		List<Resource> resourceList = null;
		//判断是否登陆
		if(authentication!=null && authentication.getName()!=null){
			resultMap.put("loginFlag", "true");
			String userId = SecurityUserUtil.getCurrentUserId(request);
			SecurityUser currUser =  (SecurityUser) authentication.getPrincipal();
			resultMap.put("currUser", currUser);

			//先不要菜单权限
			resultMap.put("menuFlag", "true");
		}
       return Result.toJson(Ecode.SUCCESS, resultMap);
    }

	/**
	 * 根据用户仓库查询人员信息
	 * @return
	 */
	@RequestMapping(value="/initUserInfo", produces="application/json;charset=UTF-8")
	@ResponseBody
	public String initUserInfo(HttpServletRequest request){
		try {
			String userId = SecurityUserUtil.getCurrentUserId(request);
			List<String> warehouseCodes = SecurityUserUtil.getUserOrgCode(redisTemplate,userId);
			String warehouseCode = "";
			for (int i = 0; i < warehouseCodes.size(); i++) {
				warehouseCode+=warehouseCodes.get(i)+";";
			}
			List<User> list = userService.initUserInfo(warehouseCode);
			return Result.toJson(Ecode.SUCCESS, list);
		} catch (Exception e) {
//			logger.debug("初始人员信息失败：" + e.getMessage());
			return Result.toJson(Ecode.FAIL, "初始化人员信息失败");
		}
	}

}

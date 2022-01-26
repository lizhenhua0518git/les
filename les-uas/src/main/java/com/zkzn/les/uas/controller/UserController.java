package com.zkzn.les.uas.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zkzn.les.uas.pojo.SecurityUser;
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
import com.zkzn.les.uas.service.OrgnizationService;
import com.zkzn.les.uas.service.ResourceService;
import com.zkzn.les.uas.service.UserService;


@Controller
@RequestMapping(value="/user")
@CrossOrigin
public class UserController {

	@Autowired(required=true)
	private UserService userService;
	@Autowired
	private FeignService feignService;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private OrgnizationService orgnizationService;
	@Autowired
	private ResourceService resourceService;

	Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * 用户信息列表
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String userlist(User user){
		PageInfo<User> pageUser = null;
		try{
			pageUser = userService.listPageUser(user);
		}catch(Exception e){
			logger.debug("查询用户信息失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, pageUser);
	}




























































	/**.
	 *
	 * 创建人: wangzhou
	 * 时间:2020年4月2日下午4:37:29
	 * String
	 * @param params
	 * @return
	 * 功能描述:查询某个角色对应的用户信息列表
	 */
	@RequestMapping(value="/queryNoRoleUser",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String queryNoRoleUser(@RequestParam Map<String,String> params){
		PageInfo<User> userPage = null;
		if(params!=null && !params.isEmpty()){
			try{
				int pageNum = params.get("pageNumber")!=null?Integer.valueOf(params.get("pageNumber")):1;
				int pageSize = params.get("pageSize")!=null?Integer.valueOf(params.get("pageSize")):10;
				User user = new User();
				user.setLimit(pageSize);
				user.setPage(pageNum);
				user.setUserName(params.get("userName"));
				user.setPhone(params.get("phone"));
				if(params.get("status")!=null && params.get("status").length()>0) {
                    user.setStatus(Integer.valueOf(params.get("status")));
                }
				user.setOrgId(params.get("orgId"));
				userPage = userService.listNoRoleUserPage(params.get("roleId"), user);

			}catch(Exception e){
				logger.debug("查询用户信息失败："+e.getMessage());
				return Result.toJson(Ecode.FAIL, "查询用户信息失败："+e.getMessage());
			}
		}
		return Result.toJson(Ecode.SUCCESS, userPage);

	}

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



//			List<Map<String,Object>> roleList = orgnizationService.getUserRole(userId);
//			String type = "P";
//			if(roleList!=null && roleList.size()>0){//班组的角色 查班组的菜单
//				type = "B";
//			}
//			//查询当前用户有权限的所有菜单资源信息
//			resourceList = resourceService.listResourceByUserId(userId, "1",null,type);
//			//通过url查询当前对应的资源信息
//			List<Resource> resourceUrlList = resourceService.listResourceByUrl(currentUrl);
//			if(resourceUrlList==null || resourceUrlList.size()==0){//查不到信息  说明不需要该页面不需要权限控制
//				resultMap.put("menuFlag", "true");
//			}else if( "admin".equals(currUser.getUserName()) || (resourceUrlList!=null && resourceList!=null )){
//				Resource resource  = isAuthorityNew(resourceList,resourceUrlList);
//				resultMap.put("menuFlag", "true");
//				if(resource==null){
//					resource = resourceUrlList.get(0);
//				}
//				//查询该页面对应的按钮资源
//				List<Resource> buttonResource = resourceService.listByParentId(resource.getId());
//				//查询当前用户有权限的所有按钮资源信息
//				List<Resource> userResourceList = resourceService.listResourceByUserId(userId, "2",null,"P");
//				if((userResourceList!=null && !userResourceList.isEmpty() && buttonResource!=null && !buttonResource.isEmpty()) || "admin".equals(currUser.getUserName())){
//					for(Resource item:buttonResource){
//						if("admin".equals(currUser.getUserName()) || isAuthority(userResourceList,item)){
//							resultMap.put(item.getResourceCode(), "true");
//						}
//					}
//				}
//			}else{
//				resultMap.put("menuFlag", "false");
//			}

		}
       return Result.toJson(Ecode.SUCCESS, resultMap);
    }

	public Resource isAuthorityNew(List<Resource> resourceList,List<Resource> resourceUrlList){
		Resource resource = null;
		List<String> resourceIds = new ArrayList<String>();
		for(Resource temp:resourceUrlList){
			resourceIds.add(temp.getId());
		}
		for(Resource item:resourceList){
			if(resourceIds.contains(item.getId())) {
                resource = item;
                return resource;
            }
		}
		return resource;
	}

	/**.
	 *
	 * 功能描述：判断该用户是否拥有资源权限
	 * 作者：wangzhou
	 * 时间:2018年6月20日
	 * @param resourceList
	 * @param resource
	 * @return
	 */
	public boolean isAuthority(List<Resource> resourceList,Resource resource){
		boolean flg = false;
		for(Resource item:resourceList){
			if(item.getId().equals(resource.getId())) {
                flg = true;
            }
		}
		return flg;
	}

	/**.
	 *
	 * 功能描述：新增用户信息
	 * 作者：wangzhou
	 * 时间:2018年6月11日
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/saveUser",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String saveUser(@RequestBody User user){
		try{
			String avatar = user.getAvatar();
			if(avatar!=null && avatar.length()>0){
				avatar = avatar.replaceAll("<000000>", "/");
				user.setAvatar(avatar);
			}
			user.setUserPassword(MD5.getStringMD5(user.getUserPassword()));
			userService.saveUser(user);
		}catch(Exception e){
			logger.debug("新增用户保存失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, "保存失败");
		}
		return Result.toJson(Ecode.SUCCESS, "保存成功");
	}
	/**.
	 *
	 * 功能描述：修改用户信息
	 * 作者：wangzhou
	 * 时间:2018年6月11日
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/updateUser",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String updateUser(@RequestBody User user){

		try{
			User tempUser = userService.getUserById(user.getId());
			String avatar = user.getAvatar();
			if(avatar!=null && avatar.length()>0){
				avatar = avatar.replaceAll("<000000>", "/");
				user.setAvatar(avatar);
			}
			if(!tempUser.getUserPassword().equals(user.getUserPassword())){
				user.setUserPassword(MD5.getStringMD5(user.getUserPassword()));
			}
			userService.updateUser(user);
		}catch(Exception e){
			logger.debug("修改用户保存失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, "修改失败");
		}
		return Result.toJson(Ecode.SUCCESS, "修改成功");
	}
	/**.
	 *
	 * 功能描述：删除用户信息
	 * 作者：wangzhou
	 * 时间:2018年6月11日
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteUser",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String deleteUser(@RequestBody List<String> id){
		try{
			userService.deleteUser(id);
		}catch(Exception e){
			logger.debug("删除用户信息失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, "删除失败");
		}
		return Result.toJson(Ecode.SUCCESS, "删除成功");
	}
	/**.
	 *
	 * 功能描述：添加用户时用于查重
	 * 作者：wangzhou
	 * 时间:2018年6月11日
	 * @param phoneNum
	 * @return
	 */
	@RequestMapping(value="/queryByPhone",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String queryByPhone(String phoneNum,String id){
		User user =null;
		try{
			 user = userService.getUserByPhone(phoneNum,id);
		}catch(Exception e){
			logger.debug("查询用户信息失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, "查询失败");
		}
		return Result.toJson(Ecode.SUCCESS,user);
	}
	/**.
	 *
	 * 功能描述：查询某个角色对应的用户信息列表
	 * 作者：wangzhou
	 * 时间:2018年6月14日
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/listHasRoleUser",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String listHasRoleUser(@RequestParam Map<String,String> params){
		Map<String,Object> result = new HashMap<String,Object>();
		if(params!=null && !params.isEmpty()){
			try{
				int pageNum = params.get("pageNumber")!=null?Integer.valueOf(params.get("pageNumber")):1;
				int pageSize = params.get("pageSize")!=null?Integer.valueOf(params.get("pageSize")):10;
				User user = new User();
				user.setLimit(pageSize);
				user.setPage(pageNum);
				user.setUserName(params.get("userName"));
				user.setPhone(params.get("phone"));
				if(params.get("status")!=null && params.get("status").length()>0) {
                    user.setStatus(Integer.valueOf(params.get("status")));
                }
				user.setOrgId(params.get("orgId"));
				PageInfo<User> userPage = userService.listHasRoleUser(user, params.get("roleId"));
				result.put("code",0);
				result.put("msg", "成功");
				result.put("count", ""+userPage.getTotal());
				result.put("data", userPage.getList());
			}catch(Exception e){
				logger.debug("查询用户信息失败："+e.getMessage());
				result.put("code",-1);
				result.put("msg", "失败");
				result.put("data", null);
			}
		}
		return JsonUtil.toJson(result);
	}

	/**.
	 *
	 * 功能描述：通过用户名查询用户信息
	 * 作者：wangzhou
	 * 时间：2018年6月22日
	 * @param userName
	 * @return
	 */
	@RequestMapping(value="/getByUserName",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String getByUserName(String userName){
		User user =null;
		try{
			 user = userService.getByUserName(userName);
		}catch(Exception e){
			logger.debug("查询用户信息失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, "查询失败");
		}
		return Result.toJson(Ecode.SUCCESS,user);
	}

	/**.
	 * 功能描述: 修改密码
	 * yzn
	 * 时间:  2018年07月26日
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/changePassWord",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String changePassWord(HttpServletRequest request,@RequestBody User user){
		try{
			//获取用户id
			String userid = SecurityUserUtil.getCurrentUserId(request);
			//获取用户的密码
			String passWord = userService.getUserById(userid).getUserPassword();
			String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,16}$";
			if(!user.getUserPassword().matches(pattern)) {
				return Result.toJsonUseApp(Ecode.FAIL, "新密码不符合规则，请由字母大小写,数字组成,不少于8位,不大于16位");
			}
			//验证旧密码是否正确：把用户页面输入的旧密码MD5加密后和数据库的密码比对
			if((MD5.getStringMD5(user.getOldPassWord())).equals(passWord)){
				user.setId(userid);
				updateUser(user);
				return Result.toJsonUseApp(Ecode.SUCCESS, "1");
			}else{
				return Result.toJsonUseApp(Ecode.FAIL, "旧密码错误，请重新输入！");
			}
		}catch(Exception e){
			logger.debug("修改密码失败！"+e.getMessage());
			return Result.toJsonUseApp(Ecode.FAIL, "密码修改失败！");
		}
	}
	/**.
	 * 功能描述: 无登陆状态修改密码
	 * wanghaojie
	 * 时间:  2019年06月05日
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/scan/changePassWordNew",produces="application/json; charset=UTF-8")
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

	/**.
	 *
	 * @param url
	 * @return
	 * @Author:wangzhou
	 * @date:2020年7月17日
	 * @Description:获取此请求是否有权限
	 */
	@GetMapping(value="/isPermission",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String isPermission(String url,String token,HttpServletRequest request){
		try{
			String userId = SecurityUserUtil.getCurrentUserId(request);
			String userName = SecurityUserUtil.getCurrentUserName(request);
			boolean flag = false;
			if("admin".equals(userName)){//管理员可访问所有的
				return Result.toJson(Ecode.SUCCESS, true);
			}
			List<String> apiList = userService.listHasPermissed(userId);
			logger.debug("url："+url);
			logger.debug("apiList："+apiList.toString());
			AntPathMatcher matcher = new AntPathMatcher();
			for(String str:apiList){

				if(matcher.match(str, url)){
					flag = true;
				}
			}

			return Result.toJson(Ecode.SUCCESS, flag);
		}catch(Exception e){
			logger.debug("获取访问权限失败！"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "获取访问权限失败！");
		}
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
			logger.debug("初始人员信息失败：" + e.getMessage());
			return Result.toJson(Ecode.FAIL, "初始化人员信息失败");
		}
	}

}

package com.zkzn.les.basicInfo.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.zkzn.les.basicInfo.user.pojo.User;
import com.zkzn.les.basicInfo.util.SecurityUserUtil;
import com.zkzn.les.common.util.excel.FileUtil;
import com.zkzn.les.common.util.json.JsonUtil;
import com.zkzn.les.common.util.lang.MD5;
import com.zkzn.les.common.util.page.PageUtil;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.pojo.Resource;
import com.zkzn.les.basicInfo.service.OrgnizationService;
import com.zkzn.les.basicInfo.service.ResourceService;
import com.zkzn.les.basicInfo.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import sun.misc.BASE64Encoder;


@Api(tags="用户配置服务")
@RequestMapping(value="/user")
@RestController
@CrossOrigin
public class UserController {

	@Autowired(required=true)
	private UserService userService;

	@Autowired
	private OrgnizationService orgnizationService;
	@Autowired
	private ResourceService resourceService;

	Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * 用户信息分页列表
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String userlist(User user){
		try{
			PageUtil.setPageParam(user);
			List<User> userList = userService.listUser(user);
			PageInfo<User> userPage = new PageInfo<>(userList);
			return Result.toJson(Ecode.SUCCESS, userPage);
		}catch(Exception e){
			logger.debug("查询用户信息失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, e.getMessage());
		}
	}

	/**
	 * 新增用户信息
	 * @param user
	 * @param request
	 * @return
	 */
	@ApiOperation("新增用户信息")
	@ApiResponses({@ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
	@PostMapping(value="/saveUser",produces="application/json;charset=UTF-8")
	public String saveUser(@RequestBody User user,HttpServletRequest request){
		String currentUserId = SecurityUserUtil.getCurrentUserId(request);
		String currentUserName = SecurityUserUtil.getCurrentUserName(request);
		user.setCreaterId(currentUserId);
		user.setCreaterName(currentUserName);
		user.setCreateTime(new Date());
		try{
			String avatar = user.getAvatar();
			if(avatar!=null && avatar.length()>0){
				avatar = avatar.replaceAll("<000000>", "/");
				user.setAvatar(avatar);
			}
			user.setUserPassword(MD5.getStringMD5(user.getUserPassword()));
			int i = userService.saveUser(user);
			if (i==1){//手机号已存在
				return Result.toJson(Ecode.USR_EXISTS, null);
			}
		}catch(Exception e){
			logger.debug("新增用户保存失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, "保存失败");
		}
		return Result.toJson(Ecode.SUCCESS, null);
	}

	/**
	 * 修改用户信息
	 * @param user
	 * @param request
	 * @return
	 */
	@ApiOperation("修改用户信息")
	@ApiResponses({
			@ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
	@PostMapping(value="/updateUser",produces="application/json;charset=UTF-8")
	public String updateUser(@RequestBody User user,HttpServletRequest request){
		String currUid = SecurityUserUtil.getCurrentUserId(request);
		String currentUserName = SecurityUserUtil.getCurrentUserName(request);
		user.setModifierId(currUid);
		user.setModifierName(currentUserName);
		user.setModifiedTime(new Date());
		try{
			String userName = user.getUserName();
			if ("admin".equals(userName)){//admin不能修改
				return Result.toJson(Ecode.USR_DISABLED, null);
			}
			String avatar = user.getAvatar();
			if(avatar!=null && avatar.length()>0){
				avatar = avatar.replaceAll("<000000>", "/");
				user.setAvatar(avatar);
			}
			int i = userService.updateUser(user);
			if (i==1){//手机号已存在
				return Result.toJson(Ecode.USR_EXISTS, null);
			}
		}catch(Exception e){
			logger.debug("修改用户保存失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, "修改失败");
		}
		return Result.toJson(Ecode.SUCCESS, null);
	}

	/**
	 * 删除用户信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteUser",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String deleteUser(@RequestBody List<String> id){
		try{
			for (int i = 0; i < id.size(); i++) {
				String s = id.get(i);
				if ("1".equals(s)){
					return Result.toJson(Ecode.USR_DISABLED, null);
				}
			}
			userService.deleteUser(id);
		}catch(Exception e){
			logger.debug("删除用户信息失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, "删除失败");
		}
		return Result.toJson(Ecode.SUCCESS, "删除成功");
	}

	/**
	 * 用户仓库列表信息查询
	 * @param params
	 * @param request
	 * @return
	 */
	@GetMapping(value="/listUserWareHouse",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String listUserWareHouse(@RequestParam Map<String,Object> params,HttpServletRequest request){
		PageInfo<Map<String,Object>> pageInfo = null;
		try{
			//获取用户id
			String userid = SecurityUserUtil.getCurrentUserId(request);
			params.putIfAbsent("userId",userid);
			pageInfo = userService.listUserWareHouse(params);
		}catch(Exception e){
			logger.debug("用户仓库信息获取失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, "用户仓库信息获取失败");
		}
		return Result.toJson(Ecode.SUCCESS, pageInfo);
	}

	/**
	 * 查询用户组织信息
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/listHasOrgUser",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String listHasOrgUser(@RequestParam Map<String,Object> params){
		PageInfo<Map<String,Object>> pageInfo = null;
		try{
			pageInfo = userService.listUserOrganizationPage(params);
		}catch(Exception e){
			logger.debug("查询用户组织信息信息失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, "查询用户组织信息失败");
		}
		return Result.toJson(Ecode.SUCCESS, pageInfo);
	}

	/**
	 * 用户-仓库信息保存
	 * @param param
	 * @return
	 */
	@ResponseBody
	@PostMapping(value="/saveUserWareHouse",produces="application/json; charset=UTF-8")
	public String saveUserWareHouse(@RequestBody List<Map<String,Object>> param){
		try{
			userService.saveUserWareHouse(param);
		}catch (Exception exception){
			logger.debug("用户-仓库信息保存失败："+exception.getMessage());
			return Result.toJson(Ecode.FAIL, "用户-仓库信息保存失败");
		}
		return Result.toJson(Ecode.SUCCESS, "用户-仓库信息保存成功");
	}

	/**
	 * 删除用户对应仓库关系
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/removeUserOrganization",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String removeUserOrganization(@RequestBody List<String> ids){
		try{
			userService.removeUserOrganization(ids);
		}catch(Exception e){
			logger.debug("删除用户组织信息信息失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, "删除用户组织信息失败");
		}
		return Result.toJson(Ecode.SUCCESS, "删除成功");
	}

	/**
	 * 查询用户角色信息
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/listUserRole",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String listUserRole(@RequestParam Map<String,Object> params){
		PageInfo<Map<String,Object>> pageInfo = null;
		try{
			pageInfo = userService.listUserRolePage(params);
		}catch(Exception e){
			logger.debug("查询用户角色信息失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, "查询用户角色信息失败");
		}
		return Result.toJson(Ecode.SUCCESS, pageInfo);
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
		try{
		     User user = userService.getByUserName(userName);
			 return Result.toJson(Ecode.SUCCESS,user);
		}catch(Exception e){
			logger.debug("查询用户信息失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, "查询失败");
		}
	}

	/**
	 * .
	 * 功能描述:依据ID查询用户信息--提供远程调用
	 * 作者:何闰平
	 * 时间:2020年4月23日 上午11:35:41
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/getUser",produces="application/json; charset=UTF-8")
    @ResponseBody
	public String getUser(@RequestParam Map<String,Object> params) {
	    try{
	        User u = new User();
            Object obj = params.get("id");
            if(obj instanceof String){
                u.setId(obj.toString());
            }
           List<User> userList = userService.listUser(u);
           return Result.toJson(Ecode.SUCCESS,userList);
	    }catch(Exception e) {
	        logger.debug("getUser 查询用户信息失败:"+e.getMessage());
            return Result.toJson(Ecode.FAIL, e.getMessage());
	    }
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
				updateUser(user,request);
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
	 * @param
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

	/**
	 * .
	 *
	 * 功能描述:根据用户id获取用户信息
	 *
	 * @param params
	 * @return
	 * @author  刘松山
	 *
	 *时间:  2020-04-24 10:52
	 *
	 */
	@RequestMapping(value="/getUserById",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String getUserById(@RequestBody Map<String,Object> params){
		User user =null;
		try{
			 String id = params.get("id")+"";
			 user = userService.getUserById(id);
		}catch(Exception e){
			logger.debug("查询用户信息失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, "查询失败");
		}
		return Result.toJson(Ecode.SUCCESS,user);
	}



	/**.
	 *
	 * 创建人: wangzhou
	 * 时间:2020年6月1日下午5:12:23
	 * String
	 * @param list
	 * @return
	 * 功能描述:保存用户对应组织信息
	 */
	@RequestMapping(value="/saveUserOrganization",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String saveUserOrganization(@RequestBody List<Map<String,Object>> list){
		try{
			userService.saveUserOrganization(list);
		}catch(Exception e){
			logger.debug("保存用户组织信息信息失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, "保存用户组织信息失败");
		}
		return Result.toJson(Ecode.SUCCESS, "保存成功");
	}


	/**
	 * 功能描述：查看用户头像
	 * 作者：luozhihong
	 * 时间:2020年12月21日
	 * @param fileName
	 * @return
	 */
	@RequestMapping(value="/getUserAvatarImg",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String getUserAvatarImg(String fileName,HttpServletResponse response)throws IOException {
		// TODO Auto-generated method stub
		String imgStr = "";
		String filePath="";//文件保存路径
		JSONObject object = new JSONObject();
		try{
			filePath = FileUtil.getDefualtPath();
			logger.debug("imgPath:"+filePath+fileName);
			File imgFile = new File(filePath+fileName);
			//设置返回的文件属性
			response.setContentType("image/jpeg");
			response.setContentLength((int) imgFile.length());
			//打开文件并输出
			FileInputStream inputStream = new FileInputStream(imgFile);
			byte[] buffer = new byte[(int) imgFile.length()];
			int offset = 0;
			int numRead = 0;
			while (offset < buffer.length && (numRead = inputStream.read(buffer, offset, buffer.length - offset)) >= 0) {
				offset += numRead;
			}

			if (offset != buffer.length) {
				throw new IOException("Could not completely read file "
						+ imgFile.getName());
			}
			inputStream.close();
			BASE64Encoder encoder = new BASE64Encoder();
			imgStr = encoder.encode(buffer);
			String img_path="data:image/jpeg;base64,"+imgStr;
			System.out.print("img_path==="+img_path+"\n");
		}catch(Exception e){
			logger.debug("getImg error:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "查询失败");
		}
		return Result.toJson(Ecode.SUCCESS,imgStr);
	}
}

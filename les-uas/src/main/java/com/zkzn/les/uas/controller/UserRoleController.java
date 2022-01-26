package com.zkzn.les.uas.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zkzn.les.uas.pojo.UserRole;
import com.zkzn.les.uas.service.UserRoleService;
import com.zkzn.les.uas.util.Ecode;
import com.zkzn.les.uas.util.RedisUtil;
import com.zkzn.les.uas.util.Result;

@Controller
@RequestMapping("/userRole")
public class UserRoleController {

	private Logger logger = LoggerFactory.getLogger(UserRoleController.class);
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private RedisTemplate<String ,String> redisTemplate;
	/**.
	 * 
	 * 功能描述：保存用户和角色关联
	 * 作者：wangzhou
	 * 时间:2018年6月13日
	 * @param userRoleList
	 * @return
	 */
	@RequestMapping(value="/saveUserRole",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String saveUserRole(@RequestBody List<UserRole> userRoleList){
		try{
			userRoleService.saveUserRole(userRoleList);
			delRedisKeys(userRoleList.get(0).getRoleId());
		}catch(Exception e){
			logger.debug("保存用户和角色关联失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, "保存失败");
		}
		
		return Result.toJson(Ecode.SUCCESS, "保存成功");
	}
	/**.
	 * 
	 * 功能描述：删除该角色对应的用户存储的redis缓存
	 * 作者：wangzhou
	 * 时间:2018年6月20日
	 * @param roleId
	 */
	public void delRedisKeys(String roleId){
		Object userResourceObj = RedisUtil.getCache(redisTemplate,"userResource");
		List<String> userResourceList = null;
		if(userResourceObj!=null){
			userResourceList = (List<String>) userResourceObj;
			redisTemplate.delete(userResourceList);
			redisTemplate.delete("userResource");
		}
	}
	/**.
	 * 
	 * 功能描述：删除用户和角色关联
	 * 作者：wangzhou
	 * 时间:2018年6月14日
	 * @param userRoleList
	 * @return
	 */
	@RequestMapping(value="/deleteUserRole",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String deleteUserRole(@RequestBody List<UserRole> userRoleList){
		try{
			userRoleService.deleteUserRole(userRoleList);
			delRedisKeys(userRoleList.get(0).getRoleId());
		}catch(Exception e){
			logger.debug("删除用户和角色关联失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, "删除失败");
		}
		
		return Result.toJson(Ecode.SUCCESS, "删除成功");
	}
	/**.
	 * 
	 * 功能描述：检查roleId中是否存绑定了用户
	 * 作者：wangzhou
	 * 时间：2018年7月5日
	 * @param roleIds
	 * @return
	 */
	@RequestMapping(value="/checkUserRole",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String checkUserRole(@RequestBody List<String> roleIds){
		int result =0;
		try{
			result = userRoleService.countUserRoleByRoleIds(roleIds);
		}catch(Exception e){
			logger.debug("查询用户和角色关联失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, "查询失败");
		}
		return Result.toJson(Ecode.SUCCESS, result);
	}
}

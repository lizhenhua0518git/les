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

import com.zkzn.les.uas.pojo.Privilege;
import com.zkzn.les.uas.service.PrivilegeService;
import com.zkzn.les.uas.service.UserRoleService;
import com.zkzn.les.uas.util.Ecode;
import com.zkzn.les.uas.util.RedisUtil;
import com.zkzn.les.uas.util.Result;

@Controller
@RequestMapping("/privilege")
public class PrivilegeController {

	
	private Logger logger = LoggerFactory.getLogger(PrivilegeController.class);
	
	@Autowired
	private PrivilegeService privilegeService;
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private RedisTemplate<String ,String> redisTemplate;
	
	/**.
	 * 
	 * 功能描述：保存资源和角色关联
	 * 作者：wangzhou
	 * 时间:2018年6月14日
	 * @param privilegeList
	 * @return
	 */
	@RequestMapping(value="savePrivilege",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String savePrivilege(@RequestBody List<Privilege> privilegeList){
		try{
			privilegeService.savePrivilege(privilegeList);
			delRedisKeys(privilegeList.get(0).getRoleId());
		}catch(Exception e){
			logger.debug("保存资源和角色关联失败："+e.getMessage());
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
	 * 功能描述：删除资源和角色关联
	 * 作者：wangzhou
	 * 时间:2018年6月14日
	 * @param privilegeList
	 * @return
	 */
	@RequestMapping(value="deletePrivilege",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String deletePrivilege(@RequestBody List<Privilege> privilegeList){
		try{
			privilegeService.deletePrivilege(privilegeList);
			delRedisKeys(privilegeList.get(0).getRoleId());
		}catch(Exception e){
			logger.debug("删除资源和角色关联失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, "删除失败");
		}
		return Result.toJson(Ecode.SUCCESS, "删除成功");
	}
	/**.
	 * 
	 * 功能描述：
	 * 作者：wangzhou
	 * 时间:2018年6月14日
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="listPrivilegeByRoleId",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String listPrivilegeByRoleId(String roleId){
		List<Privilege>  privilegeList = null;
		try{
			privilegeList = privilegeService.listPrivilegeByRoleId(roleId);
		}catch(Exception e){
			logger.debug("查询资源id失败:"+e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, privilegeList);
	}
}

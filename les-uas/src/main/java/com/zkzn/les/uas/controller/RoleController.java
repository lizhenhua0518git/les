package com.zkzn.les.uas.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.uas.pojo.Role;
import com.zkzn.les.uas.service.RoleService;
import com.zkzn.les.uas.util.Ecode;
import com.zkzn.les.uas.util.JsonUtil;
import com.zkzn.les.uas.util.Result;

@Controller
@RequestMapping("/role")
public class RoleController {

	private Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleService;
	/**.
	 * 
	 * 功能描述：分页查询角色信息
	 * 作者：wangzhou
	 * 时间:2018年6月12日
	 * @param  role
	 * @return
	 */
	@RequestMapping(value="/",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String roleList(Role role){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		 
			PageInfo<Role> rolePage = null;
			try{
				rolePage =  roleService.listRolePage(role);
				resultMap.put("code",0);
				resultMap.put("msg", "成功");
				resultMap.put("count", ""+rolePage.getTotal());
				resultMap.put("data", rolePage.getList());
			}catch(Exception e){
				logger.debug("查询角色信息失败："+e.getMessage());
				resultMap.put("code",1);
				resultMap.put("msg", "失败");
				resultMap.put("data", e.getMessage());
			}
			
		return JsonUtil.toJson(resultMap);
	}
	/**.
	 * 
	 * 功能描述：新增角色
	 * 作者：wangzhou
	 * 时间:2018年6月12日
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/saveRole",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String saveRole(@RequestBody Role role){
		int result = 0;
		try{
			result = roleService.saveRole(role);
		}catch(Exception e){
			logger.debug("保存角色信息失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, result);
		}
		return Result.toJson(Ecode.SUCCESS, result);
	}
	
	/**.
	 * 
	 * 
	 * 功能描述：修改角色信息
	 * 作者：wangzhou
	 * 时间:2018年6月12日
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/updateRole",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String updateRole(@RequestBody Role role){
		int roleId;
		try{
			roleId = roleService.updateRole(role);
		}catch(Exception e){
			logger.debug("修改角色信息失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, "修改失败");
		}
		return Result.toJson(Ecode.SUCCESS, roleId);
	}
	/**.
	 * 
	 * 功能描述：删除角色信息
	 * 作者：wangzhou
	 * 时间:2018年6月12日
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="/deleteRole",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String deleteRole(@RequestBody List<String> roleId){
		int resut;
		try{
			resut = roleService.deleteRole(roleId);
		}catch(Exception e){
			logger.debug("删除角色信息失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, "删除失败");
		}
		return Result.toJson(Ecode.SUCCESS, resut);
	}
	/**.
	 * 
	 * 功能描述：新增和修改角色信息时，对角色名称查重
	 * 作者：wangzhou
	 * 时间:2018年6月12日
	 * @param roleName
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="/queryByRoleName",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String queryByRoleName(String roleName,String roleId){
		Role role = null ;
		try{
			role = roleService.getByRoleName(roleName, roleId);
		}catch(Exception e){
			logger.debug("查询角色信息失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, "查询失败");
		}
		return Result.toJson(Ecode.SUCCESS,role);
	}
}

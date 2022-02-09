package com.zkzn.les.basicInfo.controller;

import com.zkzn.les.basicInfo.pojo.ResourceApiAdress;
import com.zkzn.les.basicInfo.service.ResourceApiAddressService;
import com.zkzn.les.basicInfo.util.BeanUtil;
import com.zkzn.les.basicInfo.util.Ecode;
import com.zkzn.les.basicInfo.util.Result;
import com.zkzn.les.basicInfo.util.SecurityUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api(description="菜单api接口配置服务")
@RequestMapping(value="/resourceApiAddress")
@RestController
public class ResourceApiAddressController {

	private Logger logger = LoggerFactory.getLogger(ResourceApiAddressController.class);
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
 
	
	@Autowired
	private ResourceApiAddressService resourceApiAddressService;
	
	@ApiOperation(value="修改菜单api接口地址")
	@ApiResponses({
		@ApiResponse(code=200, message = "返回信息")
	})
	@PostMapping(value="/updateResourceApiAdress",produces="application/json;charset=UTF-8")
	public String updateResourceApiAdress(@RequestBody Map<String,Object> param,HttpServletRequest request){
		try{
			String userId = SecurityUserUtil.getCurrentUserId(request);
			String userName = SecurityUserUtil.getCurrentUserName(request);
			
			List<Map<String,Object>> items = (List<Map<String, Object>>) param.get("item");
			String resourceId= param.get("resourceId").toString();
			List<ResourceApiAdress> saveList = new ArrayList<ResourceApiAdress>();
			List<ResourceApiAdress> updateList = new ArrayList<ResourceApiAdress>();
			ResourceApiAdress tempResourceApiAdress = null;
			for(Map<String,Object> map:items){
				tempResourceApiAdress = (ResourceApiAdress) BeanUtil.mapToObject(map,ResourceApiAdress.class);
				tempResourceApiAdress.setModifierId(userId);
				tempResourceApiAdress.setModifierName(userName);
				tempResourceApiAdress.setModifiedTime(new Date());
				if(tempResourceApiAdress.getId()!=null){
					updateList.add(tempResourceApiAdress);
				}else{
					tempResourceApiAdress.setCreaterId(userId);
					tempResourceApiAdress.setCreaterName(userName);
					tempResourceApiAdress.setCreateTime(new Date());
					tempResourceApiAdress.setResourceId(resourceId);
					saveList.add(tempResourceApiAdress);
				}
				
			}
			List<String> deleteIds = (List<String>) param.get("deleteIds");
			if(deleteIds!=null && deleteIds.size()>0){
				resourceApiAddressService.deleteResourceApiAdress(deleteIds);
			}
			if(updateList.size()>0){
				resourceApiAddressService.updateResourceApiAdresss(updateList);
			}
			if(saveList.size()>0){
				resourceApiAddressService.saveResourceApiAdresss(saveList);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("修改菜单api地址失败:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "修改菜单api地址失败:"+e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, "修改成功");
	}
}

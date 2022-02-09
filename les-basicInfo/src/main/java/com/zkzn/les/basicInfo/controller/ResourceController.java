package com.zkzn.les.basicInfo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zkzn.les.basicInfo.pojo.Resource;
import com.zkzn.les.basicInfo.service.ResourceService;
import com.zkzn.les.basicInfo.util.Ecode;
import com.zkzn.les.basicInfo.util.JsonUtil;
import com.zkzn.les.basicInfo.util.Result;
import com.zkzn.les.basicInfo.util.SecurityUserUtil;
import com.zkzn.les.basicInfo.pojo.DictItems;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(description="菜单配置服务")
@RequestMapping(value="/resource")
@RestController
public class ResourceController {

	private Logger logger = LoggerFactory.getLogger(ResourceController.class);

	@Autowired
	private RedisTemplate<String, String> redisTemplate;


	@Autowired
	private ResourceService resourceService;

	@RequestMapping(value="/",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String resourceList(Resource resource){
		Map<String,Object> resultMap = new HashMap<>();
		try{
			List<Resource> list = resourceService.listresource(resource);
			resultMap.put("code",0);
			resultMap.put("msg", "成功");
			resultMap.put("count", ""+list.size());
			resultMap.put("data", list);
		}catch(Exception e){
			logger.debug("资源列表信息接口异常:"+e.getMessage());
			resultMap.put("code",1);
			resultMap.put("msg", "失败");
			resultMap.put("data", e.getMessage());
		}
		return JsonUtil.toJson(resultMap);
	}

	/**.
	 *
	 * 功能描述：新增资源信息
	 * 作者：wangzhou
	 * 时间:2018年6月12日
	 * @param resource
	 * @return
	 */
	@ApiOperation("新增资源信息")
	@ApiResponses({
			@ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
	@PostMapping(value="/saveResource",produces="application/json;charset=UTF-8")
	public String saveResource(@RequestBody Resource resource,HttpServletRequest request){
		String currUid = SecurityUserUtil.getCurrentUserId(request);
		String currentUserName = SecurityUserUtil.getCurrentUserName(request);
		resource.setCreaterId(currUid);
		resource.setCreaterName(currentUserName);
		resource.setCreateTime(new Date());
		int result = 0;
		try{
			result = resourceService.saveResource(resource);
		}catch(Exception e){
			logger.debug("保存资源信息失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, result);
		}
		return Result.toJson(Ecode.SUCCESS, result);

	}
	/**.
	 *
	 * 功能描述：修改资源信息
	 * 作者：wangzhou
	 * 时间:2018年6月12日
	 * @param resource
	 * @return
	 */
	@ApiOperation("修改资源信息")
	@ApiResponses({
			@ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
	@PostMapping(value="/updateResource",produces="application/json;charset=UTF-8")
	public String updateResource(@RequestBody Resource resource,HttpServletRequest request){
		String currUid = SecurityUserUtil.getCurrentUserId(request);
		String currentUserName = SecurityUserUtil.getCurrentUserName(request);
		resource.setModifierId(currUid);
		resource.setModifierName(currentUserName);
		resource.setModifiedTime(new Date());
		int result = 0;
		try{
			result = resourceService.updateResource(resource);
		}catch(Exception e){
			logger.debug("修改资源信息失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, result);
		}
		return Result.toJson(Ecode.SUCCESS, result);

	}
	/**.
	 *
	 * 功能描述：删除资源信息
	 * 作者：wangzhou
	 * 时间:2018年6月12日
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteResource",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String deleteResource(@RequestBody List<String> id){
		int result = 0;
		try{
			List<String> childIds = findChildId(id);
			if(childIds!=null && childIds.size()>0) {
                id.addAll(childIds);
            }
			result = resourceService.removeResource(id);

		}catch(Exception e){
			logger.debug("删除资源信息失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, result);
		}
		return Result.toJson(Ecode.SUCCESS, result);
	}

	/**.
	 *
	 * 创建人: wangzhou
	 * 时间:2020年4月8日下午2:06:33
	 * String
	 * @param request
	 * @param response
	 * @return
	 * 功能描述:获取用户菜单
	 */
	@RequestMapping(value="/getResoureList",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String getResoureList(HttpServletRequest request, HttpServletResponse response){
		response.setCharacterEncoding("utf-8");

		List<Resource> resourceList = null;

		StringBuffer dataBuffer = new StringBuffer();
		dataBuffer.append("[");
		String userId = SecurityUserUtil.getCurrentUserId(request);
		String userName = SecurityUserUtil.getCurrentUserName(request);
		String type = "P";
		if("admin".equals(userName)){//超级管理员，无视权限
			resourceList = resourceService.listResourceByType("1","1","P");
		}else{
			resourceList = resourceService.listResourceByUserId(userId, "1","1",type);
		}
		//当前用户对应的模块资源
		List<Resource> blockResList = null;
		if("admin".equals(userName)){//超级管理员，无视权限
			blockResList = resourceService.listResourceByType("0","1","P");
		}else{
			blockResList = resourceService.listResourceByUserId(userId,"0","1",type);
		}
		if(blockResList!=null && !blockResList.isEmpty()){
			for(int i=0;i<blockResList.size();i++){
				if(i>0) {
                    dataBuffer.append(",");
                }
				dataBuffer.append("{\"id\":"+(i+1)+",\"title\":\""+blockResList.get(i).getResourceName()+"\""
						+ ",\"icon\":\""+blockResList.get(i).getResourceIcon()+"\"");
				insterDataBuffer(dataBuffer,resourceList,blockResList.get(i).getId());
				dataBuffer.append("}");
			}
		}
		String tempStr = dataBuffer.toString();
		tempStr = tempStr.replaceAll(",]", "]");//去除字符中多余的，
		StringBuffer resutBuffer = new StringBuffer(tempStr);
		resutBuffer.append("]");
		return Result.toJson(Ecode.SUCCESS, resutBuffer.toString());
	}

	/* 创建人: wangzhou
	 * 时间:2020年4月9日上午9:31:35
	 * String
	 * @param request
	 * @param response
	 * @return
	 * 功能描述:app端获取菜单
	 */
	@RequestMapping(value="/getAppResourceList",produces="application/json; charset=UTF-8")
	@ResponseBody
	public String getAppResourceList(HttpServletRequest request, HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		try{
			List<Map<String,String>> teturnList = new ArrayList<>();
			Map<String,Object> availableMap = new HashMap<>();
			String userId = SecurityUserUtil.getCurrentUserId(request);
			String userName = SecurityUserUtil.getCurrentUserName(request);
			List<Resource> blockResList = resourceService.listResourceByType("0","1","M");
			List<Resource> availableList = new ArrayList<>();
			if(!"admin".equals(userName)){//超级管理员，无视权限
				availableList = resourceService.listResourceByUserId(userId,"0","1","M");
			}
			if(availableList!=null && !availableList.isEmpty()){
				for (int i = 0; i < availableList.size(); i++) {
					Resource resource = availableList.get(i);
					availableMap.put(resource.getResourceNum(),"");
				}
			}
			for(int i=0;i<blockResList.size();i++){
				Map<String,String> map = new HashMap<>();
				Resource resource = blockResList.get(i);
				map.put("menuId",resource.getResourceNum());
				map.put("menuName",resource.getResourceName());
				map.put("msgCount","0");
				if (availableMap.containsKey(resource.getResourceNum()) || availableList.size()<1){
					map.put("availableStatus","0");//0表示用户可用当前资源
				}else {
					map.put("availableStatus","1");//1表示不可用
				}
				teturnList.add(map);
			}
			return Result.toJsonUseApp(Ecode.SUCCESS,teturnList);
		}catch(Exception e){
			logger.debug("app端获取菜单失败："+e.getMessage());
			return Result.toJsonUseApp(Ecode.FAIL, "用户未登陆");
		}
	}

	/**.
	 *
	 * 创建人: wangzhou
	 * 时间:2020年4月8日下午2:06:02
	 * void
	 * @param buffer
	 * @param resourceList
	 * @param parentId
	 * 功能描述:递归拼接菜单
	 */
	public void insterDataBuffer(StringBuffer buffer,List<Resource> resourceList,String parentId){
		int tempNum = 0;
		for(int i=0;i<resourceList.size();i++){
			if(parentId.equals(resourceList.get(i).getResourceParentId())){
				if(tempNum==0){
					buffer.append(",\"children\":[");
					tempNum++;
				}
				buffer.append("{\"id\":\""+resourceList.get(i).getId()+"\",\"title\":\""+resourceList.get(i).getResourceName()+"\"")
				.append(",\"url\":\""+resourceList.get(i).getResourceUrl()+"\",\"icon\":\""+resourceList.get(i).getResourceIcon()+"\"");
				insterDataBuffer(buffer,resourceList,resourceList.get(i).getId());
				buffer.append("},");
			}
		}
		if(tempNum>0){
			buffer.append("]");
		}
	}

	/**.
	 *
	 * 功能描述：查找子节点（删除时需要删除节点下所有子节点）
	 * 作者：wangzhou
	 * 时间：2018年7月5日
	 * @param id
	 * @return
	 */
	public List<String> findChildId(List<String> id){
		Resource resource = new Resource();

		List<Resource> resourceList = resourceService.listresource(resource);
		List<String> resultList = new ArrayList<String>();
		for(String idItem:id){
			recursonFind(idItem,resourceList,resultList);
		}
		return resultList;
	}

	/**.
	 *
	 * 功能描述：递归查找子节点
	 * 作者：wangzhou
	 * 时间：2018年7月5日
	 * @param id
	 * @param resourceList
	 * @param resultList
	 */
	public void recursonFind(String id,List<Resource> resourceList,List<String> resultList){
		for(Resource resourceItem:resourceList){
			if(id.equals(resourceItem.getResourceParentId())){
				resultList.add(resourceItem.getId());
				recursonFind(resourceItem.getId(),resourceList,resultList);
			}
		}
	}

}

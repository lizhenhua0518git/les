package com.zkzn.les.uas.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zkzn.les.uas.pojo.Orgnization;
import com.zkzn.les.uas.service.OrgnizationService;
import com.zkzn.les.uas.util.Ecode;
import com.zkzn.les.uas.util.JsonUtil;
import com.zkzn.les.uas.util.RedisUtil;
import com.zkzn.les.uas.util.Result;
import com.zkzn.les.uas.util.SecurityUserUtil;

@Controller
@RequestMapping(value="/orgnization")
public class OrgnizationController {

	private Logger logger = LoggerFactory.getLogger(OrgnizationController.class);
	
	@Autowired
	private OrgnizationService orgnizationService;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	
	/**.
	 * 
	 * 功能描述：查询组织结构信息
	 * 作者：wangzhou
	 * 时间:2018年6月7日
	 * @return
	 */
	@RequestMapping(value="/",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String orgnizationTree(){
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("{'text':'唐车组织结构','id':'0'");
		
		List<Orgnization> orginationList = orgnizationService.listAll();
		
		if(orginationList!=null && orginationList.size()>0){
			orginTreeList(orginationList,"0",buffer);
			String tmp = buffer.toString();
			tmp = tmp.replaceAll(",]", "]");//去除字符中多余的，
			buffer = new StringBuffer(tmp);
		}
		buffer.append("}");
		return Result.toJson(Ecode.SUCCESS, buffer.toString());
	}
	
	/**.
	 * 
	 * 功能描述：递推拼接树结构
	 * 作者：wangzhou
	 * 时间:2018年6月7日
	 * @param list
	 * @param parentId
	 * @param buffer
	 */
	public void orginTreeList(List<Orgnization> list,String parentId,StringBuffer buffer){
		Orgnization tempOrgination = null;
		int tempNum = 0;
		for(int i=0;i<list.size();i++){
			tempOrgination = list.get(i);
			
			if(parentId.equals(tempOrgination.getParentId()) ){
				if(tempNum==0){
					buffer.append(",'children':[");	
				}
				buffer.append("{'text':'"+tempOrgination.getOrgName()+"','id':'"+tempOrgination.getId()+"'");
				orginTreeList(list,tempOrgination.getId(),buffer);
				buffer.append("},");
				tempNum++;
			}
			
		}
		if(tempNum>0){
			buffer.append("]");
		}
	}
	/**.
	 * 
	 * 功能描述：新增组织结构
	 * 作者：wangzhou
	 * 时间:2018年6月7日
	 * @param orgnizatioin
	 * @return
	 */
	@RequestMapping(value="/saveOrgination",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String saveOrgination(@RequestBody Orgnization orgnizatioin){
		
		try{
			orgnizationService.saveOrgnization(orgnizatioin);
		}catch(Exception e){
			logger.debug(e.getMessage());
			return Result.toJson(Ecode.FAIL, e.getMessage());
		}
		
		return Result.toJson(Ecode.SUCCESS, "添加成功");
	}
	/**.
	 * 
	 * 功能描述：修改组织结构信息
	 * 作者：wangzhou
	 * 时间:2018年6月8日
	 * @param orgnizatioin
	 * @return
	 */
	@RequestMapping(value="/updateOrgination",produces="application/json;charset=UTF-8",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String updateOrgination(@RequestBody Orgnization orgnizatioin){
		try{
			orgnizationService.updateOrgnization(orgnizatioin);
		}catch(Exception e){
			logger.debug(e.getMessage());
			return Result.toJson(Ecode.FAIL, e.getMessage());
		}
		
		return  Result.toJson(Ecode.SUCCESS, "修改成功");
	}
	
	/**.
	 * 
	 * 功能描述：删除组织结构
	 * 作者：wangzhou
	 * 时间:2018年6月7日
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteOrgination",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String deleteOrgination(@RequestBody List<String>  id){
		try{
			orgnizationService.deleteOrgnization(id);
		}catch(Exception e){
			logger.debug(e.getMessage());
			return Result.toJson(Ecode.FAIL, e.getMessage());
		}
		
		
		return Result.toJson(Ecode.SUCCESS, "删除成功");
	}
	/**.
	 * 
	 * 功能描述：通过id获取组织信息
	 * 作者：wangzhou
	 * 时间:2018年6月7日
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getOrgination",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getOrgination(String id){
		Orgnization orgnizatioin = null;
		try{
			 orgnizatioin=orgnizationService.getById(id);
		}catch(Exception e){
			logger.debug(e.getMessage());
			return Result.toJson(Ecode.FAIL, e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, orgnizatioin);
		
	}
	
	/**.
	 * 
	 * 功能描述：组织结构列表查询
	 * 作者：wangzhou
	 * 时间:2018年6月8日
	 * @param orgnizatioin
	 * @return
	 */
	@RequestMapping(value="/orgnizationList",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String orgnizationList(Orgnization orgnizatioin){
		PageInfo<Orgnization> orgPage = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			
				orgPage = orgnizationService.listByParam(orgnizatioin);
				
				map.put("code",0);
				map.put("msg", "成功");
				map.put("count", ""+orgPage.getTotal());
				map.put("data", orgPage.getList());
		}catch(Exception e){
			logger.debug(e.getMessage());
			map.put("code",1);
			map.put("msg", "失败");
			map.put("data", null);
			return JsonUtil.toJson(map);
		}
		
		return JsonUtil.toJson(map);
		
	}
	/**.
	 * 
	 * 功能描述：批量修改组织信息的状态
	 * 作者：wangzhou
	 * 时间：2018年7月2日
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/updateStateById",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String updateStateById(@RequestBody List<String> id,@RequestParam("status")int status){
		int result =0;
		try{
			result = orgnizationService.updateStateById(id, status);
		}catch(Exception e){
			logger.debug("批量修改组织状态失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL,e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, result);
	}
	/**.
	 * 
	 * 功能描述：根据组织类型查询组织信息
	 * 作者：wangzhou
	 * 时间：2018年7月27日
	 * @param orgType
	 * @return
	 */
	@RequestMapping(value="/listOrgnizaByType",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String listOrgnizaByType(String orgType){
		Orgnization orgnization = new Orgnization();
		orgnization.setOrgType(orgType);
		try{
			List<Orgnization> orgnizaList = orgnizationService.listListByParam(orgnization);
			return Result.toJson(Ecode.SUCCESS, orgnizaList);
		}catch(Exception e){
			logger.debug("查询组织信息失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL,e.getMessage());
		}
		
	}
	/**.
	 * 
	 * 功能描述：查询仓位名和仓库编码
	 * 作者：wangzhou
	 * 时间：2018年10月17日
	 * @return
	 */
	@RequestMapping(value="/listWarehouse",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String listWarehouse(HttpServletRequest request){
		try{
			//获取当前用户id
			String currentUid = SecurityUserUtil.getCurrentUserId(request);
			//查询当前登录人的组织是什么类型
			Map<String,Object> orgMap = orgnizationService.listUserOrgType(currentUid);
			//查询仓库名称和对应的仓库编号
			List<Map<String,Object>> wareHouseList = null;
			if("4".equals(""+orgMap.get("orgType"))){//当前登录人对应的组织是仓库类型
				wareHouseList = orgnizationService.listWarehouse(""+orgMap.get("orgId"));
			}else{
				wareHouseList = orgnizationService.listWarehouse(null);
			}
			List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
			Map<String,String> resultMap = null;
			String tempName="";
			int i=0;
			for(Map<String,Object> map:wareHouseList){
				tempName = (String) map.get("orgName");
				if(resultMap!=null && tempName.equals(resultMap.get("orgName"))){
					resultMap.put("storageLocation", resultMap.get("storageLocation")+";"+map.get("storageLocation"));
				}else{
					if(resultMap!=null){
						resultList.add(resultMap);
					}
					resultMap = new HashMap<String,String>();
					resultMap.put("orgName", tempName);
					resultMap.put("orgCode", ""+map.get("orgCode"));
					resultMap.put("storageLocation", (String)map.get("storageLocation"));
				}
				if(wareHouseList.size()-1==i){
					resultList.add(resultMap);
				}
				i++;
			}
			if("4".equals(""+orgMap.get("orgType")) && resultList.size()==1){//组织是仓库类型的
				RedisUtil.putCache(redisTemplate, currentUid+"_pc_storage",JSON.toJSONString(resultList.get(0)), 0);
			}else{//组织是非仓库类型的 先默认为中心库
				String currWareHose = SecurityUserUtil.getCacheStorage("pc", redisTemplate, currentUid);
				if(currWareHose==null ){
					for(Map<String,String> map:resultList){
						if("中心仓库".equals(map.get("orgName"))){
							RedisUtil.putCache(redisTemplate, currentUid+"_pc_storage", JSON.toJSONString(map), 0);
							break;
						}
					}
				}
				
			}
			String currWareHose = SecurityUserUtil.getCacheStorage("pc", redisTemplate, currentUid);
			JSONObject jsonObject = JSON.parseObject(currWareHose);
			currWareHose = jsonObject.getString("storageLocation");
			Map<String,Object> warehoseMap = new HashMap<String,Object>();
			warehoseMap.put("currWareHose", currWareHose);
			warehoseMap.put("wareHoseList", resultList);
			return Result.toJsonUseApp(Ecode.SUCCESS,warehoseMap);
		}catch(Exception e){
			logger.debug("查询仓库信息失败："+e.getMessage());
			return Result.toJsonUseApp(Ecode.FAIL,e.getMessage());
		}
	}
	/**.
	 * 
	 * 功能描述：查询仓位名和仓库编码
	 * 作者：wangzhou
	 * 时间：2018年10月17日
	 * @return
	 */

	@RequestMapping(value="/queryAppWarehouse",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String listAppWarehouse(HttpServletRequest request){
		try{
			//获取当前用户id
			String currentUid = SecurityUserUtil.getCurrentUserId(request);
			//查询当前登录人的组织是什么类型
			Map<String,Object> orgMap = orgnizationService.listUserOrgType(currentUid);
			//查询仓库名称和对应的仓库编号
			List<Map<String,Object>> wareHouseList = null;
			if("4".equals(""+orgMap.get("orgType"))){//当前登录人对应的组织是仓库类型
				wareHouseList = orgnizationService.listWarehouse(""+orgMap.get("orgId"));
			}else{
				wareHouseList = orgnizationService.listWarehouse(null);
			}
			List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
			Map<String,String> resultMap = null;
			String tempName="";
			int i=0;
			for(Map<String,Object> map:wareHouseList){
				tempName = (String) map.get("orgName");
				if(resultMap!=null && tempName.equals(resultMap.get("orgName"))){
					resultMap.put("storageLocation", resultMap.get("storageLocation")+";"+map.get("storageLocation"));
				}else{
					if(resultMap!=null){
						resultList.add(resultMap);
					}
					resultMap = new HashMap<String,String>();
					resultMap.put("orgName", tempName);
					resultMap.put("orgCode", ""+map.get("orgCode"));
					resultMap.put("storageLocation", (String)map.get("storageLocation"));
				}
				if(wareHouseList.size()-1==i){
					resultList.add(resultMap);
				}
				i++;
			}
			if("4".equals(""+orgMap.get("orgType")) && resultList.size()==1){
				RedisUtil.putCache(redisTemplate, currentUid+"_app_storage", JSON.toJSONString(resultList.get(0)), 0);
			}else{//组织是非仓库类型的 先默认为中心库
				String currWareHose = SecurityUserUtil.getCacheStorage("app", redisTemplate, currentUid);
				if(currWareHose==null){
					for(Map<String,String> map:resultList){
						if("中心仓库".equals(map.get("orgName"))){
							RedisUtil.putCache(redisTemplate, currentUid+"_app_storage", JSON.toJSONString(map), 0);
							break;
						}
					}
				}
			}
			return Result.toJsonUseApp(Ecode.SUCCESS,resultList);
		}catch(Exception e){
			logger.debug("查询仓库信息失败："+e.getMessage());
			return Result.toJsonUseApp(Ecode.FAIL,e.getMessage());
		}
	}
	/**.
	 * 
	 * 功能描述：选择仓库
	 * 作者：wangzhou
	 * 时间：2018年10月17日
	 * @param storageLocation
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/chooseWarehouse",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String chooseWarehouse(@RequestBody Map<String,String> params,HttpServletRequest request){
		try{
			String storageLocation = params.get("storageLocation")!=null? params.get("storageLocation"):"";
			if(storageLocation.length()==0){
				return Result.toJsonUseApp(Ecode.FAIL,"选择仓库失败!编码为空");
			}
			//获取当前用户id
			String currentUid = SecurityUserUtil.getCurrentUserId(request);
			String userAgent = request.getHeader("user-agent");
			if(userAgent.contains("Android") || userAgent.contains("iPhone") || userAgent.contains("iPod")
					|| userAgent.contains("userAgent") || userAgent.contains("Windows Phone")){
				RedisUtil.putCache(redisTemplate, currentUid+"_app_storage",JSON.toJSONString(params), 0);
			}else{
				RedisUtil.putCache(redisTemplate, currentUid+"_pc_storage",JSON.toJSONString(params), 0);
			}
			
			return Result.toJsonUseApp(Ecode.SUCCESS, "选择仓库成功");
		}catch(Exception e){
			logger.debug("选择仓库失败："+e.getMessage());
			return Result.toJsonUseApp(Ecode.FAIL,e.getMessage());
		}
		
	}
	/**.
	 * 
	 * 功能描述：获取当前所选择仓库
	 * 作者：wangzhou
	 * 时间：2018年10月17日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getCurrWareHose",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getCurrWareHose(HttpServletRequest request){
		try{
			//获取当前用户id
			String currentUid = SecurityUserUtil.getCurrentUserId(request);
			String currWareHose = (String) RedisUtil.getCache(redisTemplate, currentUid+"_storage");
			return Result.toJson(Ecode.SUCCESS, currWareHose);
		}catch(Exception e){
			logger.debug("选择仓库失败："+e.getMessage());
			return Result.toJsonUseApp(Ecode.FAIL,e.getMessage());
		}
	}
	
	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年3月24日下午5:29:43
	 * String
	 * @return
	 * 功能描述:获取所有的仓库列表信息
	 */
	@GetMapping(value="/getWarehouse",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getWarehouse(){
		try{
			//获取当前用户id
			List<Map<String,Object>>  wareHouseList = orgnizationService.listWarehouse(null);
			return Result.toJson(Ecode.SUCCESS, wareHouseList);
		}catch(Exception e){
			logger.debug("查询失败:"+e.getMessage());
			return Result.toJsonUseApp(Ecode.FAIL,e.getMessage());
		}
	}
}

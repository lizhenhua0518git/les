package com.zkzn.les.basicInfo.controller;

import com.alibaba.fastjson.JSON;
import com.zkzn.les.basicInfo.service.OrgnizationService;
import com.zkzn.les.basicInfo.util.Ecode;
import com.zkzn.les.basicInfo.util.RedisUtil;
import com.zkzn.les.basicInfo.util.Result;
import com.zkzn.les.basicInfo.util.SecurityUserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * shu'sh
 */
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
	 * 功能描述：查询仓位名和仓库编码
	 * 作者：wangzhou
	 * 时间：2018年10月17日
	 * @return
	 */
	@RequestMapping(value="/listAppWarehouse",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String listAppWarehouse(HttpServletRequest request){
		try{
			//获取当前用户id
			String currentUid = SecurityUserUtil.getCurrentUserId(request);
			//查询仓库名称和对应的仓库编号
			List<Map<String,Object>> warehousePojoList = orgnizationService.initWarehouseSelect(Integer.parseInt(currentUid));
			Object cache = RedisUtil.getCache(redisTemplate, currentUid + "_app_storage");
			if(cache==null){
				//默认选中第一个仓库
				RedisUtil.putCache(redisTemplate, currentUid+"_app_storage", JSON.toJSONString(warehousePojoList.get(0)), 0);
			}
			return Result.toJsonUseApp(Ecode.SUCCESS,warehousePojoList);
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
//			String storageLocation = params.get("storageLocation")!=null? params.get("storageLocation"):"";
//			if(storageLocation.length()==0){
//				return Result.toJsonUseApp(Ecode.FAIL,"选择仓库失败!编码为空");
//			}
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
}

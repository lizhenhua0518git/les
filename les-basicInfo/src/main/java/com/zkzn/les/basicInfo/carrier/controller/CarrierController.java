package com.zkzn.les.basicInfo.carrier.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zkzn.les.basicInfo.carrier.pojo.CarrierPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.carrier.service.CarrierService;
import com.zkzn.les.basicInfo.util.Ecode;
import com.zkzn.les.basicInfo.util.PageUtil;
import com.zkzn.les.basicInfo.util.Result;
import com.zkzn.les.basicInfo.util.SecurityUserUtil;
import com.zkzn.les.basicInfo.pojo.Carrier;

/**
 * 载具管理
 */
@RestController
@RequestMapping(value="/carrier")
public class CarrierController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CarrierService carrierService;

	 @Autowired
     private RedisTemplate<String, String> redisTemplate;

	/**
	 * 载具列表查询
	 * @param carrierPojo
	 * @return
	 */
	@GetMapping(value="/",produces="application/json;charset=UTF-8")
	public String query(CarrierPojo carrierPojo, HttpServletRequest request){
		try {
			String currentUserId = SecurityUserUtil.getCurrentUserId(request);
			String warehouse = SecurityUserUtil.getCurrOrgCode(currentUserId, redisTemplate, "pc");
			PageUtil.setPageParam(carrierPojo);
			List<CarrierPojo> carrierList =carrierService.listCarrierByCarrier(carrierPojo);
			PageInfo<CarrierPojo> carrierPage = new PageInfo<>(carrierList);
			return Result.toJson(Ecode.SUCCESS, carrierPage);
		} catch (Exception e) {
			logger.info("载具列表查询失败"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "载具列表查询失败");
		}
	}

	/**
	 * 保存载具信息
	 * @param carrierPojo
	 * @param request
	 * @return
	 */
	@PostMapping(value="/save",produces="application/json;charset=UTF-8")
	public String save(CarrierPojo carrierPojo,HttpServletRequest request){
		try {
			String currUid = SecurityUserUtil.getCurrentUserId(request);
			String currUName = SecurityUserUtil.getCurrentUserName(request);
			carrierPojo.setCreaterId(currUid);
			carrierPojo.setCreaterName(currUName);
			carrierPojo.setCreateTime(new Date());
			carrierPojo.setUseStatus(0);
			int i = carrierService.saveCarrier(carrierPojo);
			if (1 == i){
				return Result.toJson(Ecode.CARRIER_EXIST, null);
			}
			return Result.toJson(Ecode.SUCCESS, "载具新增成功");
		} catch (Exception e) {
			logger.info("载具新增失败"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "载具新增失败");
		}
	}

	/**
	 * 删除载具信息
	 * @param carrierIds
	 * @return
	 */
	@RequestMapping(value="/deleteCarrier",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String deleteCarrier(@RequestBody List<Integer> carrierIds){
		try{
			carrierService.deleteCarrier(carrierIds);
			return Result.toJson(Ecode.SUCCESS, "删除载具成功");
		}catch(Exception e){
			logger.debug("删除载具信息失败！"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "删除载具信息失败！");
		}
	}

	/**
	 * 更新载具
	 * @param carrier
	 * @param request
	 * @return
	 */
	@PostMapping(value="/update",produces="application/json;charset=UTF-8")
	public String upddate(CarrierPojo carrierPojo,HttpServletRequest request){
		try {
			String currUid = SecurityUserUtil.getCurrentUserId(request);
			String currUName = SecurityUserUtil.getCurrentUserName(request);
			carrierPojo.setModifierId(currUid);
			carrierPojo.setModifierName(currUName);
			carrierPojo.setModifiedTime(new Date());
			carrierService.updateCarrier(carrierPojo);
			return Result.toJson(Ecode.SUCCESS, "更新载具成功");
		} catch (Exception e) {
			logger.info("载具信息更新失败"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "载具信息更新失败");
		}
	}




























    /**
     * .
     * 功能描述:修改载具状态
     * 作者:何闰平
     * 时间:2020年4月9日 上午9:20:21
     * @param map
     * @return
     */
    @SuppressWarnings("unchecked")
	@PostMapping(value = "/changeStatus", produces = "application/json;charset=UTF-8")
    public String changeStatus(@RequestBody Map<String,Object> map,HttpServletRequest request) {
       List<String> ids = (List<String>) map.get("ids");
       String remark = map.get("remark").toString();
       int status = Integer.parseInt(map.get("status").toString() !=null?map.get("status").toString():"1");
       String currUid = SecurityUserUtil.getCurrentUserId(request);
       String currUName = SecurityUserUtil.getCurrentUserName(request);

       int result = 0;
        try {
            result = carrierService.changeStatus(ids,remark,status,currUid,currUName,new Date());
            return Result.toJson(Ecode.SUCCESS, result);
        } catch (Exception e) {
            logger.debug("修改区域状态失败！" + e.getMessage());
            return Result.toJson(Ecode.FAIL, "修改区域状态失败！");
        }
    }
	/**.
	 *
	 *
	 * 创建人: wangzhou
	 * 时间:2020年3月31日上午10:55:37
	 * String
	 * @param params
	 * @return
	 * 功能描述:查询载具信息 提供远程调用
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value="/getCarrier",produces="application/json;charset=UTF-8")
	public String getCarrier(@RequestParam Map<String,Object> params){
		List<Carrier> carrierList = null;
		try{
			Object obj = params.get("storageList");
			List<String> storageList = new ArrayList<String>();
			if(obj!=null && obj instanceof String){
				storageList.add(obj.toString());
				params.put("warehouseCode", obj.toString());
				params.remove("storageList");
			}else if(obj instanceof List){
				storageList = (List<String>) params.get("storageList");
			}

			obj = params.get("warehouseCode");
			if(obj!=null &&  obj instanceof String){
				params.put("warehouseCode", obj.toString());

			}
			obj = params.get("carrierCodes");
			if(obj!=null &&  obj instanceof String){
				params.put("carrierCode", obj.toString());
				params.remove("carrierCodes");
			}
			obj = params.get("carrierIds");
			if(obj!=null &&  obj instanceof String){
				params.put("carrierId", obj.toString());
				params.remove("carrierIds");
			}
			carrierList = carrierService.listCarrierInfo(params);
		}catch(Exception e){
			logger.debug("查询载具信息失败:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "查询载具信息失败");
		}
		return Result.toJson(Ecode.SUCCESS, carrierList);
	}

	/**.
	 *
	 * 创建人: wangzhou
	 * 时间:2020年3月31日下午2:37:53
	 * String
	 * @param carrierIds
	 * @return
	 * 功能描述:批量更新载具的使用状态  提供远程调用
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value="/updateUseStatusByIds",produces="application/json;charset=UTF-8")
	public String updateUseStatusByIds(@RequestParam Map<String,Object> params,HttpServletRequest request){
		List<String> carrierIds = new ArrayList<String>();
		try{
			Object obj = params.get("carrierIds");
			if(obj instanceof String){
				carrierIds.add(obj.toString());
				params.put("carrierId", obj.toString());
				params.remove("carrierIds");
			}else if(obj instanceof List){
				carrierIds = (List<String>) obj;
			}

			 String currUid = SecurityUserUtil.getCurrentUserId(request);
             String currUName = SecurityUserUtil.getCurrentUserName(request);

            params.put("modifierId", currUid);
            params.put("modifierName", currUName);
            params.put("modifiedTime", new Date());
			carrierService.updateUseStatusByIds(params);
		}catch(Exception e){
			logger.debug("修改载具使用状态失败:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "修改载具使用状态失败");
		}
		return Result.toJson(Ecode.SUCCESS, "修改成功");
	}

	/**
	 * .
	 *
	 * 功能描述:根据载具编号更新状态  提供远程调用
	 *
	 * @param params
	 * @return
	 * @author  刘松山
	 *
	 *时间:  2020-04-20 16:57
	 *
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value="/updateUseStatusByCode",produces="application/json;charset=UTF-8")
	public String updateUseStatusByCode(@RequestParam  Map<String,Object>  map){

		try{

			List<Map<String,Object>> list = (List<Map<String, Object>>) map.get("list");

			 carrierService.updateUseStatusByCode(list);
		}catch(Exception e){
			logger.debug("根据载具编号更新状态 失败:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "根据载具编号更新状态 失败");
		}
		return Result.toJson(Ecode.SUCCESS, "修改成功");
	}

	/**
	 * @Author 胡志明
	 * @Description //TODO 根据载具号校验当前载具是否可用
	 * @Date 11:27 2020/5/14
	 * @Param [map, request]
	 * @return java.lang.String
	 **/
	@PostMapping(value = "/checkCarrierByCode", produces = "application/json;charset=UTF-8")
	public String checkCarrierByCode(@RequestBody Map<String,Object> map,HttpServletRequest request){
		String currUid = SecurityUserUtil.getCurrentUserId(request);
		String warehouse = SecurityUserUtil.getCurrOrgCode(currUid, redisTemplate, "app");// 库存地点
		try{
			Boolean carrierSatus = carrierService.checkCarrierByCode(map,warehouse);
			if (carrierSatus){
				return Result.toJson(Ecode.verify_Carrier, "");
			}
		}catch(Exception e){
			logger.debug("验证载具失败:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "验证载具失败");
		}
		return Result.toJson(Ecode.SUCCESS, "");
	}
}

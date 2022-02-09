package com.zkzn.les.basicInfo.controller;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.pojo.Area;
import com.zkzn.les.basicInfo.service.AreaService;
import com.zkzn.les.basicInfo.util.Ecode;
import com.zkzn.les.basicInfo.util.Result;
import com.zkzn.les.basicInfo.util.SecurityUserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**.
 * 
 *
 * 功能描述：区域信息的controller层
 *              增、删、改、查（ID、分页）、导入、导出
 * @author wangzhou
 * 时间：2018年7月3日
 */
@Controller
@RequestMapping("/area")
public class AreaController {

	
	private Logger logger = LoggerFactory.getLogger(AreaController.class);
	
	@Autowired
	private AreaService areaService;

	/**.
	 * 
	 * 功能描述：新增区域信息
	 * 作者：wangzhou
	 * 时间：2018年7月3日
	 * @param area
	 * @return
	 */
	@RequestMapping(value="/saveArea",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String saveArea(@RequestBody Area area,HttpServletRequest request){
		int result = 0;
		try{
			if(area.getAreaCode()!=null && area.getWarehouse()!=null){
				Area tempAra = areaService.getAreaByCode(area.getAreaCode(), area.getWarehouse());
				if(tempAra!=null){
					return Result.toJson(Ecode.FAIL, "保存失败！区域编码已存在");
				}
			}
			String currUid = SecurityUserUtil.getCurrentUserId(request);
			String currName = SecurityUserUtil.getCurrentUserName(request);
			area.setCreaterId(currUid);
			area.setCreaterName(currName);
			area.setCreateTime(new Date());
			area.setStatus(1);
			result = areaService.saveArea(area);
		}catch(Exception e){
			logger.debug("保存区域信息失败！"+e.getMessage());
			return Result.toJson(Ecode.FAIL, e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, result);
	}
	/**.
	 * 
	 * 功能描述：批量删除区域信息
	 * 作者：wangzhou
	 * 时间：2018年7月3日
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteArea",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String deleteArea(@RequestBody List<String> id){
		int result = 0;
		try{
			result = areaService.deleteArea(id);
		}catch(Exception e){
			logger.debug("删除区域信息失败！"+e.getMessage());
			return Result.toJson(Ecode.FAIL, e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, result);
	}
	/**.
	 * 
	 * 功能描述：修改区域信息
	 * 作者：wangzhou
	 * 时间：2018年7月3日
	 * @param area
	 * @return
	 */
	@RequestMapping(value="/updateArea",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String updateArea(@RequestBody Area area,HttpServletRequest request){

	    String currUid = SecurityUserUtil.getCurrentUserId(request);
	    String currUserName = SecurityUserUtil.getCurrentUserName(request);
	    area.setModifierId(currUid);
	    area.setModifiedTime(new Date());
	    area.setModifierName(currUserName);
		int result = 0;
		try{
			if(area.getAreaCode()!=null && area.getWarehouse()!=null){
				Area tempAra = areaService.getAreaByCode(area.getAreaCode(), area.getWarehouse());
				if(tempAra!=null && !tempAra.getId().equals(area.getId())){
					return Result.toJson(Ecode.FAIL, "保存失败！区域编码已存在");
				}
			}
			result = areaService.updateArea(area);
		}catch(Exception e){
			logger.debug("修改区域信息失败！"+e.getMessage());
			return Result.toJson(Ecode.FAIL, e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, result);
	}
	/**.
	 * 
	 * 功能描述：查询区域信息
	 * 作者：wangzhou
	 * 时间：2018年7月3日
	 * @param area
	 * @return
	 */
	@RequestMapping(value="/",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String queryArea(Area area,HttpServletRequest request){
	    try{
	        
	        PageInfo<Area> areaPage = areaService.listAreaByPage(area);
			return Result.toJson(Ecode.SUCCESS, areaPage);
		}catch(Exception e){
			logger.debug("查询区域信息失败！"+e.getMessage());
			return Result.toJson(Ecode.FAIL, e.getMessage());
		}
	}

}

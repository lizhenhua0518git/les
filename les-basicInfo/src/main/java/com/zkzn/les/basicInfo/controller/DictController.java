package com.zkzn.les.basicInfo.controller;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.pojo.Dict;
import com.zkzn.les.basicInfo.pojo.DictItems;
import com.zkzn.les.basicInfo.service.DictService;
import com.zkzn.les.basicInfo.util.Ecode;
import com.zkzn.les.basicInfo.util.Result;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**.
 *
 *
 * 功能描述：数据字典类型controller
 * @author wangzhou
 * 时间：2018年6月27日
 */
@Api(description="数据字典类型信息服务")
@RequestMapping(value="/dict")
@RestController
public class DictController {

	private Logger logger = LoggerFactory.getLogger(DictController.class);
	@Autowired
	private DictService dictService;

	/**.
	 *
	 * 功能描述：保存数据字典类型
	 * 作者：wangzhou
	 * 时间：2018年6月27日
	 * @param dict
	 * @return
	 */
	@RequestMapping(value="/saveDict",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String saveDict(@RequestBody Dict dict){
		int result = 0 ;
		try{
			result = dictService.saveDict(dict);
		}catch(Exception e){
			logger.debug("保存数据字典类型失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, result);
		}
		return Result.toJson(Ecode.SUCCESS, result);
	}

	/**.
	 *
	 * 功能描述：删除数据字典类型信息
	 * 作者：wangzhou
	 * 时间：2018年6月27日
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteDict",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String deleteDict(@RequestBody List<String> id){
		int result = 0 ;
		try{
			result = dictService.deleteDict(id);
		}catch(Exception e){
			logger.debug("删除数据字典类型失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, result);
		}
		return Result.toJson(Ecode.SUCCESS, result);
	}
	/**.
	 *
	 * 功能描述：修改数据字典信息
	 * 作者：wangzhou
	 * 时间：2018年6月27日
	 * @param dict
	 * @return
	 */
	@RequestMapping(value="/updateDict",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String updateDict(@RequestBody Dict dict){
		int result = 0 ;
		try{
			result = dictService.updateDict(dict);
		}catch(Exception e){
			logger.debug("修改数据字典类型失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, result);
		}
		return Result.toJson(Ecode.SUCCESS, result);
	}
	/**.
	 *
	 * 功能描述：数据词典类型列表查询
	 * 作者：wangzhou
	 * 时间：2018年6月27日
	 * @param dict
	 * @return
	 */
	@RequestMapping(value="/",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String listDictList(Dict dict){
		PageInfo<Dict> dictPageInfo = null;
		try{
			 dictPageInfo = dictService.listDictList(dict);
		}catch(Exception e){
			logger.debug("查询数据字典类型失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, dictPageInfo);
	}
	/**.
	 *
	 * 功能描述：字典新增和编辑查重处理
	 * 作者：wangzhou
	 * 时间：2018年6月28日
	 * @param dict
	 * @return
	 */
	@RequestMapping(value="/getDict",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getDict(@RequestBody Dict dict){
		Dict dictResult = null;
		try{
			dictResult = dictService.getDict(dict);
		}catch(Exception e){
			logger.debug("查询数据字典失败："+e.getMessage());
			return Result.toJson(Ecode.FAIL, e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, dictResult);
	}

	/**
	 * 根据字典类型名称查询字典信息
	 * @param request
	 * @param dictName
	 * @return
	 */
	@GetMapping(value="/initDictItemList", produces="application/json;charset=UTF-8")
	public String initDictItemList(String dictType){
		try {
			List<DictItems> list = dictService.initDictItemList(dictType);
			return Result.toJson(Ecode.SUCCESS, list);
		} catch (Exception e) {
			logger.debug("初始化区域类型失败：" + e.getMessage());
			return Result.toJson(Ecode.FAIL, "初始化区域类型失败");
		}
	}
}

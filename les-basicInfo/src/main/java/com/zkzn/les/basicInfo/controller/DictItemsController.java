package com.zkzn.les.basicInfo.controller;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.pojo.DictItems;
import com.zkzn.les.basicInfo.service.DictItemsService;
import com.zkzn.les.basicInfo.util.Ecode;
import com.zkzn.les.basicInfo.util.RedisUtil;
import com.zkzn.les.basicInfo.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**.
 * 
 * @author wangzhou
 * 数据字典 controller
 */
@RestController
@RequestMapping(value="/dictItems")
public class DictItemsController {

	
	private Logger logger = LoggerFactory.getLogger(DictItemsController.class);
	@Autowired
	private DictItemsService dictItemsService;
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	
	
	/**.
	 * 
	 * 功能描述：新增数据字典信息
	 * 作者：wangzhou
	 * 时间：2018年6月28日
	 * @param dictItems
	 * @return
	 */
	@RequestMapping(value="/saveDictItems",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String saveDictItems(@RequestBody DictItems dictItems){
		int result =0;
		try{
			result = dictItemsService.saveDictItems(dictItems);
		}catch(Exception e){
			logger.debug("保存数据字典失败！"+e.getMessage());
			return Result.toJson(Ecode.FAIL, e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, result);
	}
	/**.
	 * 
	 * 功能描述：删除数据字典信息
	 * 作者：wangzhou
	 * 时间：2018年6月28日
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteDictItems",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String deleteDictItems(@RequestBody List<String> id){
		int result =0;
		try{
			result = dictItemsService.deleteDictItems(id);
		}catch(Exception e){
			logger.debug("删除数据字典失败！"+e.getMessage());
			return Result.toJson(Ecode.FAIL, e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, result);
	}
	/**.
	 * 
	 * 功能描述：修改数据字典信息
	 * 作者：wangzhou
	 * 时间：2018年6月28日
	 * @param dictItems
	 * @return
	 */
	@RequestMapping(value="/updateDictItems",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String updateDictItems(@RequestBody DictItems dictItems){
		int result =0;
		try{
			result = dictItemsService.updateDictItems(dictItems);
		}catch(Exception e){
			logger.debug("修改数据字典失败！"+e.getMessage());
			return Result.toJson(Ecode.FAIL, e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, result);
	}
	/**.
	 * 
	 * 功能描述：数据字典列表信息
	 * 作者：wangzhou
	 * 时间：2018年6月28日
	 * @param dictItems
	 * @return
	 */
	@RequestMapping(value="/",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String listDictItemsList(DictItems dictItems){
		PageInfo<DictItems> dictItemsPage = null;
		try{
			dictItemsPage = dictItemsService.listDictItemsList(dictItems);
		}catch(Exception e){
			logger.debug("查询数据字典失败！"+e.getMessage());
			return Result.toJson(Ecode.FAIL, e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, dictItemsPage);
	}

	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年3月31日上午9:31:29
	 * String
	 * @param dictType
	 * @return
	 * 功能描述:数
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/listDictItemByTypes",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String listDictItemByTypes(@RequestBody List<String> dictType){
		List<DictItems> itemList=null;//所有的item数据
		List<String> tempList = new ArrayList<String>();//缓存中没有找到的字典code
		List<DictItems> tempItemList  = new ArrayList<DictItems>();//返回dictType对应的字典数据
		try{
			Object dictItemObj = RedisUtil.getCache(redisTemplate,"dictItems");
			if(dictItemObj!=null){
				itemList = (List<DictItems>) dictItemObj;
				int tempNum=0;
				for(String str:dictType){
					tempNum = 0;
					for(DictItems dictItem:itemList){
						if(dictItem.getDictTypeId().equals(str)){
							tempItemList.add(dictItem);
							tempNum++;
						}
					}
					if(tempNum==0) {
                        tempList.add(str);
                    }
				}
				if(tempList.size()>0){
					List<DictItems> tempItemList1 = dictItemsService.listDictItemsByType(tempList);
					itemList.addAll(tempItemList1);
					tempItemList.addAll(tempItemList1);
					//往缓存中存入所有的数据
					RedisUtil.putCache(redisTemplate,"dictItems",itemList,3600);
					itemList.clear();
					itemList = tempItemList;//保证返回的数据只是对应ajax请求对应的字典
				}else{
					itemList.clear();
					itemList = tempItemList;
				}
			}else{
				itemList = dictItemsService.listDictItemsByType(dictType);
				RedisUtil.putCache(redisTemplate,"dictItems",itemList,3600);
			}
		}catch(Exception e){
			logger.debug("查询数据字典失败！"+e.getMessage());
			return Result.toJson(Ecode.FAIL, e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, itemList);
	}

}

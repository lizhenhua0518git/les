package com.zkzn.les.oms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zkzn.les.common.util.json.JsonUtil;
import com.zkzn.les.common.util.page.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.oms.dao.SingleAllocateDao;
import com.zkzn.les.oms.fegin.BasicInfoFeignService;
import com.zkzn.les.oms.pojo.SingleAllocate;
import com.zkzn.les.oms.service.SingleAllocateService;

@Service
public class SingleAllocateServiceImpl implements SingleAllocateService{

	@Autowired
	private SingleAllocateDao singleAllocateDao;

	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	@Autowired
	private BasicInfoFeignService basicInfoFeignService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public int saveSingleAllocate(SingleAllocate singleAllocate) {
		// TODO Auto-generated method stub
		return singleAllocateDao.saveSingleAllocate(singleAllocate);
	}

	@Override
	public int updateSingleAllocate(SingleAllocate singleAllocate) {
		// TODO Auto-generated method stub
		return singleAllocateDao.updateSingleAllocate(singleAllocate);
	}

	@Override
	public int deleteSingleAllocate(List<String> ids) {
		// TODO Auto-generated method stub
		return singleAllocateDao.deleteSingleAllocate(ids);
	}

	@Override
	public List<SingleAllocate> listSingleAllocate(SingleAllocate singleAllocate) {
		// TODO Auto-generated method stub
		reAssembleParam(singleAllocate);
		List<SingleAllocate> list = singleAllocateDao.listSingleAllocate(singleAllocate);
		if(list!=null && list.size()>0 ){
			//获取字典表的字典信息
			List<String> dictType = new ArrayList<String>();
			dictType.add("allocate_status");//调拨订单状态
			dictType.add("allocate_check_status");//调拨审核订单状态
			String resultInfo = basicInfoFeignService.listDictItemByTypes(dictType);
			logger.debug("字典信息:"+resultInfo);
			List<Map> dictItem = JsonUtil.jsonStrToList(resultInfo);
			//替换字典信息和SAP库存地点
			String tempInStorageLocation = null;
			String tempOutStorageLocation = null;
			Map<String,Object> tempMap = null;
			for(SingleAllocate tempSingleAllocate:list){

				if(dictItem!=null && dictItem.size()>0){
					for(Map tempDict :dictItem){
						if("allocate_status".equals(""+tempDict.get("dictTypeId")) && (""+tempDict.get("itemValue")).equals(""+tempSingleAllocate.getStatus()) ){
							tempSingleAllocate.setStatusStr(""+tempDict.get("itemName"));
						}
						if("allocate_check_status".equals(""+tempDict.get("dictTypeId")) && (""+tempDict.get("itemValue")).equals(""+tempSingleAllocate.getStockCheckStatus()) ){
							tempSingleAllocate.setStockCheckStatusStr(""+tempDict.get("itemName"));
						}
					}
				}
				//转换调入sap库存地点
				tempInStorageLocation = tempSingleAllocate.getInStorageLocation();
				//tempMap = SapStorageLocationTransfrom.lesToSapStorageLocation(redisTemplate, tempInStorageLocation);
				if(tempMap!=null && !tempMap.isEmpty()){
					tempSingleAllocate.setSapInStorageLocation(""+tempMap.get("sapStorageLocation"));
				}
				//转换调出sap库存地点
				tempOutStorageLocation = tempSingleAllocate.getOutStorageLocation();
				//tempMap = SapStorageLocationTransfrom.lesToSapStorageLocation(redisTemplate, tempOutStorageLocation);
				if(tempMap!=null && !tempMap.isEmpty()){
					tempSingleAllocate.setSapOutStorageLocation(""+tempMap.get("sapStorageLocation"));
				}
			}
		}
		return list;
	}

	@Override
	public PageInfo<SingleAllocate> listSingleAllocatePage(SingleAllocate singleAllocate) {
		// TODO Auto-generated method stub
		PageUtil.setPageParam(singleAllocate);
		List<SingleAllocate> list = listSingleAllocate(singleAllocate);
		PageInfo<SingleAllocate> pageInfo = new PageInfo<SingleAllocate>(list);
		return pageInfo;
	}


	/**.
	 *
	 * @param singleAllocate
	 * @Author:wangzhou
	 * @date:2020年8月12日
	 * @Description:参数封装
	 */
	public void reAssembleParam( SingleAllocate singleAllocate ){
		String sapInStorageLocation = singleAllocate.getSapInStorageLocation();
		String inFactory = singleAllocate.getInFactory();
		List<String> inStorageLocations = new ArrayList<String>();
		String tempStorageLocation = null;
		String [] tempStorageLocations = null;
		//sap调入库存地点转换
		if(inFactory!=null && sapInStorageLocation!=null && inFactory.length()>0 && sapInStorageLocation.length()>0){

			tempStorageLocations = sapInStorageLocation.split(";");
			String inStorageLocation =null;
			for(int i=0;i<tempStorageLocations.length;i++){
				//inStorageLocation = SapStorageLocationTransfrom.sapToLesStorageLocation(redisTemplate, inFactory, tempStorageLocations[i]);
				if(inStorageLocation!=null){
					inStorageLocations.add(inStorageLocation);
				}
			}


		}
		if(singleAllocate.getInStorageLocation()!=null && singleAllocate.getInStorageLocation().length()>0){

			tempStorageLocation = singleAllocate.getInStorageLocation();
			tempStorageLocations = tempStorageLocation.split(";");
			for(int i=0;i<tempStorageLocations.length;i++){
				inStorageLocations.add(tempStorageLocations[i]);
			}
		}
		singleAllocate.setInStorageLocations(inStorageLocations);


		String sapOutStorageLocation = singleAllocate.getSapOutStorageLocation();
		String outFactory = singleAllocate.getOutFactory();
		List<String> outStorageLocations = new ArrayList<String>();
		//sap调出库存地点转换
		if(outFactory!=null && sapOutStorageLocation!=null && outFactory.length()>0 && sapOutStorageLocation.length()>0){
			tempStorageLocations = sapOutStorageLocation.split(";");
			String inStorageLocation =null;
			for(int i=0;i<tempStorageLocations.length;i++){
				//inStorageLocation = SapStorageLocationTransfrom.sapToLesStorageLocation(redisTemplate, inFactory, tempStorageLocations[i]);
				if(inStorageLocation!=null){
					outStorageLocations.add(inStorageLocation);
				}
			}


		}
		if(singleAllocate.getOutStorageLocation()!=null && singleAllocate.getOutStorageLocation().length()>0){
			tempStorageLocation = singleAllocate.getOutStorageLocation();
			tempStorageLocations = tempStorageLocation.split(";");
			for(int i=0;i<tempStorageLocations.length;i++){
				outStorageLocations.add(tempStorageLocations[i]);
			}

		}
		singleAllocate.setOutStorageLocations(outStorageLocations);
	}

	@Override
	public int updateSingleAllocates(List<SingleAllocate> singleAllocates) {
		// TODO Auto-generated method stub
		return singleAllocateDao.updateSingleAllocates(singleAllocates);
	}

	@Override
	public List<Map<String, Object>> checkDocument(Set<String> setStr) {
		// TODO Auto-generated method stub
		return singleAllocateDao.checkDocument(setStr);
	}

	@Override
	public List<Map<String, Object>> checkOrgCode(Set<String> orgCodes) {
		// TODO Auto-generated method stub
		return singleAllocateDao.checkOrgCode(orgCodes);
	}

	@Override
	public List<Map<String, Object>> checkStorageLocation(Set<String> storageLocations) {
		// TODO Auto-generated method stub
		return singleAllocateDao.checkStorageLocation(storageLocations);
	}

	@Override
	public List<Map<String, Object>> checkMaterialCode(Set<String> materialCodes) {
		// TODO Auto-generated method stub
		return singleAllocateDao.checkMaterialCode(materialCodes);
	}

	@Override
	public int saveSingleAllocates(List<SingleAllocate> singleAllocates) {
		// TODO Auto-generated method stub
		return singleAllocateDao.saveSingleAllocates(singleAllocates);
	}

}

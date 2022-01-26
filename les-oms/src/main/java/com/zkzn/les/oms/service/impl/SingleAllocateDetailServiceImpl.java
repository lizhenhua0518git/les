package com.zkzn.les.oms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zkzn.les.common.util.json.JsonUtil;
import com.zkzn.les.common.util.page.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.oms.dao.SingleAllocateDetailDao;
import com.zkzn.les.oms.fegin.BasicInfoFeignService;
import com.zkzn.les.oms.pojo.SingleAllocateDetail;
import com.zkzn.les.oms.service.SingleAllocateDetailService;


/**.
 *
 * @author wangzhou
 * @date 2020年8月10日
 * @Description :调拨订单详情serivce实现类
 */
@Service
public class SingleAllocateDetailServiceImpl implements SingleAllocateDetailService{

	@Autowired
	private SingleAllocateDetailDao singleAllocateDetailDao;
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	@Autowired
	private BasicInfoFeignService basicInfoFeignService;

	@Override
	public int saveSingleAllocateDetail(SingleAllocateDetail singleAllocateDetail) {
		// TODO Auto-generated method stub
		return singleAllocateDetailDao.saveSingleAllocateDetail(singleAllocateDetail);
	}

	@Override
	public int updateSingleAllocateDetail(SingleAllocateDetail singleAllocateDetail) {
		// TODO Auto-generated method stub
		return singleAllocateDetailDao.updateSingleAllocateDetail(singleAllocateDetail);
	}

	@Override
	public int deleteSingleAllocateDetail(List<String> ids) {
		// TODO Auto-generated method stub
		return singleAllocateDetailDao.deleteSingleAllocateDetail(ids);
	}

	@Override
	public List<Map<String,Object>> listSingleAllocateDetail(Map<String,Object> param) {
		// TODO Auto-generated method stub
		reAssembleParam(param);
		List<Map<String,Object>>  list = singleAllocateDetailDao.listSingleAllocateDetail(param);
		if(list!=null && list.size()>0 ){
			//获取字典表的字典信息
			List<String> dictType = new ArrayList<String>();
			dictType.add("allocate_status");//调拨订单状态
			dictType.add("allocate_check_status");//调拨审核订单状态
			dictType.add("stock_type");//库存类型
			dictType.add("stock_status");//库存状态
			String resultInfo = basicInfoFeignService.listDictItemByTypes(dictType);
			List<Map> dictItem = JsonUtil.jsonStrToList(resultInfo);
			//替换字典信息和SAP库存地点
			String tempInStorageLocation = null;
			String tempOutStorageLocation = null;
			Map<String,Object> tempMap = null;
			for(Map<String,Object> tempParam:list){

				if(dictItem!=null && dictItem.size()>0){
					for(Map tempDict :dictItem){
						if("allocate_status".equals(""+tempDict.get("dictTypeId")) && (""+tempDict.get("itemValue")).equals(""+tempParam.get("status")) ){
							tempParam.put("statusStr",""+tempDict.get("itemName") );
						}
						if("allocate_check_status".equals(""+tempDict.get("dictTypeId")) && (""+tempDict.get("itemValue")).equals(""+tempParam.get("stockCheckStatus") ) ){
							tempParam.put("stockCheckStatusStr",""+tempDict.get("itemName") );
						}
						if("stock_type".equals(""+tempDict.get("dictTypeId")) && (""+tempDict.get("itemValue")).equals(""+tempParam.get("stockType") ) ){
							tempParam.put("stockTypeStr",""+tempDict.get("itemName") );
						}
						if("stock_status".equals(""+tempDict.get("dictTypeId")) && (""+tempDict.get("itemValue")).equals(""+tempParam.get("stockStatus") ) ){
							tempParam.put("stockStatusStr",""+tempDict.get("itemName") );
						}
					}
				}
				//转换调入sap库存地点
				tempInStorageLocation = ""+tempParam.get("inStorageLocation");
				//tempMap = SapStorageLocationTransfrom.lesToSapStorageLocation(redisTemplate, tempInStorageLocation);
				if(tempMap!=null && !tempMap.isEmpty()){
					tempParam.put("sapInStorageLocation",""+tempMap.get("sapStorageLocation"));
				}
				//转换调出sap库存地点
				tempOutStorageLocation = ""+tempParam.get("outStorageLocation");
				//tempMap = SapStorageLocationTransfrom.lesToSapStorageLocation(redisTemplate, tempOutStorageLocation);
				if(tempMap!=null && !tempMap.isEmpty()){
					tempParam.put("sapOutStorageLocation",""+tempMap.get("sapStorageLocation"));
				}
			}
		}
		return list;
	}

	@Override
	public PageInfo<Map<String,Object>> listSingleAllocateDetailPage(Map<String,Object> param) {
		// TODO Auto-generated method stub
	    PageUtil.setPageParam(param);
		List<Map<String,Object>>  list =   listSingleAllocateDetail(param);
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(list);
		return pageInfo;
	}

	@Override
	public int saveSingleAllocateDetails(List<SingleAllocateDetail> singleAllocateDetails) {
		// TODO Auto-generated method stub
		return singleAllocateDetailDao.saveSingleAllocateDetails(singleAllocateDetails);
	}

	@Override
	public int updateSingleAllocateDetails(List<SingleAllocateDetail> singleAllocateDetails) {
		// TODO Auto-generated method stub
		return singleAllocateDetailDao.updateSingleAllocateDetails(singleAllocateDetails);
	}

	/**.
	 *
	 * @param singleAllocate
	 * @Author:wangzhou
	 * @date:2020年8月12日
	 * @Description:参数封装
	 */
	public void reAssembleParam( Map<String,Object> param ){
		String sapInStorageLocation = ""+param.get("sapInStorageLocation");
		String inFactory = ""+param.get("inFactory");
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
		if(param.get("inStorageLocation")!=null && (""+param.get("inStorageLocation")).length()>0){

			tempStorageLocation = ""+param.get("inStorageLocation");
			tempStorageLocations = tempStorageLocation.split(";");
			for(int i=0;i<tempStorageLocations.length;i++){
				inStorageLocations.add(tempStorageLocations[i]);
			}
		}
		param.put("inStorageLocations", inStorageLocations);


		String sapOutStorageLocation = ""+param.get("sapOutStorageLocation");
		String outFactory = ""+param.get("outFactory");
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
		if(param.get("outStorageLocation")!=null && (""+param.get("outStorageLocation")).length()>0){
			tempStorageLocation = ""+param.get("outStorageLocation");
			tempStorageLocations = tempStorageLocation.split(";");
			for(int i=0;i<tempStorageLocations.length;i++){
				outStorageLocations.add(tempStorageLocations[i]);
			}

		}
		param.put("outStorageLocations", outStorageLocations);
	}


}

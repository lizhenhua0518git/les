package com.zkzn.les.basicInfo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.dao.OrgnizationDao;
import com.zkzn.les.basicInfo.pojo.Orgnization;
import com.zkzn.les.basicInfo.service.OrgnizationService;
import com.zkzn.les.basicInfo.util.RedisUtil;
import com.zkzn.les.basicInfo.util.UserUtil;



@Service
public class OrgnizationServiceImpl implements OrgnizationService{

	@Autowired
	private OrgnizationDao orgnizationDao;
	@Autowired
	private RedisTemplate<String,String> redisTemplate;

	@Override
	public int saveOrgnization(Orgnization orgn) {
		// TODO Auto-generated method stub
		return orgnizationDao.saveOrgnization(orgn);
	}
	@Override
	public int updateOrgnization(Orgnization orgn) {
		// TODO Auto-generated method stub
		return orgnizationDao.updateOrgnization(orgn);
	}
	@Override
	public int deleteOrgnization(List<String> id) {
		// TODO Auto-generated method stub
		return orgnizationDao.deleteOrgnization(id);
	}
	@Override
	public List<Orgnization> listAll() {
		// TODO Auto-generated method stub
		return orgnizationDao.listAll();
	}
	@Override
	public Orgnization getById(String id) {
		// TODO Auto-generated method stub
		return orgnizationDao.getById(id);
	}
	@Override
	public PageInfo<Orgnization> listByParam(Orgnization orgn){
		// TODO Auto-generated method stub
		//PageHelper.startPage(orgn.getPage(), orgn.getLimit());
		List<Orgnization> orgnizationList =orgnizationDao.listByParam(orgn);
		PageInfo<Orgnization> pageOrgnization = new PageInfo<Orgnization>(orgnizationList);

		return pageOrgnization;
	}

	@Override
	public int updateStateById(List<String> id, int status) {
		// TODO Auto-generated method stub
		return orgnizationDao.updateStateById(id, status);
	}

	@Override
	public List<Orgnization> listListByParam(Orgnization orgn) {
		// TODO Auto-generated method stub
		return orgnizationDao.listByParam(orgn);
	}
	@Override
	public List<Map<String, Object>> listWarehouse(String orgCode) {
		// TODO Auto-generated method stub
		return orgnizationDao.listWarehouse(orgCode);
	}
	@Override
	public Map<String,Object> listUserOrgType(String id) {
		// TODO Auto-generated method stub
		return orgnizationDao.listUserOrgType(id);
	}
	@Override
	public List<Map<String, Object>> getUserRole(String userid) {
		// TODO Auto-generated method stub
		return orgnizationDao.getUserRole(userid);
	}
	@Override
	public void defualtWarehouse(String currentUid,String type) {
		// TODO Auto-generated method stub
		//查询当前登录人的组织是什么类型
		Map<String,Object> orgMap = this.listUserOrgType(currentUid);
		//查询仓库名称和对应的仓库编号
		List<Map<String,Object>> wareHouseList = null;
		if("4".equals(""+orgMap.get("orgType"))){//当前登录人对应的组织是仓库类型
			wareHouseList = this.listWarehouse(""+orgMap.get("orgId"));
		}else{
			wareHouseList = this.listWarehouse(null);
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
			RedisUtil.putCache(redisTemplate, currentUid+"_"+type+"_storage",JSON.toJSONString(resultList.get(0)), 0);
		}else{//组织是非仓库类型的 先默认为中心库
			String currWareHose = UserUtil.getCacheStorage(type, redisTemplate, currentUid);
			if(currWareHose==null ){
				for(Map<String,String> map:resultList){
					if("中心仓库".equals(map.get("orgName"))){
						RedisUtil.putCache(redisTemplate, currentUid+"_"+type+"_storage", JSON.toJSONString(map), 0);
						break;
					}
				}
			}

		}
	}
	@Override
	public List<Map<String, Object>> listWarehouseByUserId(String userId) {
		// TODO Auto-generated method stub
		return orgnizationDao.listWarehouseByUserId(userId);
	}

	@Override
	public List<Map<String, Object>> initWarehouseSelect(Integer userId) {
		return orgnizationDao.initWarehouseSelect(userId);
	}

	@Override
	public List<Map<String, Object>> listWarehouseTwo(String orgCode) {
		// TODO Auto-generated method stub
		return orgnizationDao.listWarehouseTwo(orgCode);
	}


}

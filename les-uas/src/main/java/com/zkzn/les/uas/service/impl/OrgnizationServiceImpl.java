package com.zkzn.les.uas.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zkzn.les.uas.dao.OrgnizationDao;
import com.zkzn.les.uas.pojo.Orgnization;
import com.zkzn.les.uas.service.OrgnizationService;
import com.zkzn.les.uas.util.RedisUtil;



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
	public List<Map<String, Object>> listWarehouse(String orgId) {
		// TODO Auto-generated method stub
		return orgnizationDao.listWarehouse(orgId);
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
	public void defualtWarehouse(String currentUid) {
		// TODO Auto-generated method stub
		//查询当前登录人对应的 仓库名称和仓库编号
		List<Map<String,Object>> wareHouseList = listWarehouseByUserId(currentUid);
		Map<String,String> resultMap = null;
		resultMap = new HashMap<String,String>();
		String orgCode = null;
		String orgName = null;
		for(Map<String,Object> map:wareHouseList){
				if(resultMap.containsKey("orgName")){
					orgName = resultMap.get("orgName");
					if(!orgName.contains(""+map.get("orgName"))){
						resultMap.put("orgName", resultMap.get("orgName")+";"+map.get("orgName"));
					}
				}else{
					resultMap.put("orgName", ""+map.get("orgName"));
				}
				if(resultMap.containsKey("orgCode")){
					orgCode = resultMap.get("orgCode");
					if(!orgCode.contains(""+map.get("orgCode"))){
						resultMap.put("orgCode", resultMap.get("orgCode")+";"+map.get("orgCode"));
					}
				}else{
					resultMap.put("orgCode", ""+map.get("orgCode"));
				}

				if(resultMap.containsKey("storageLocation")){
					resultMap.put("storageLocation", resultMap.get("storageLocation")+";"+map.get("storageLocation"));
				}else{
					resultMap.put("storageLocation", ""+map.get("storageLocation"));
				}
		}
		//存放当前登录人对应仓库信息
		RedisUtil.putCache(redisTemplate, currentUid+"_warehouse",JSON.toJSONString(resultMap), 0);
	}
	@Override
	public List<Map<String, Object>> listWarehouseByUserId(String userId) {
		// TODO Auto-generated method stub
		return orgnizationDao.listWarehouseByUserId(userId);
	}




}

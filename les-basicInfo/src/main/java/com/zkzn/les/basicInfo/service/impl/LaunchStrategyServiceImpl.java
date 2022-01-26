package com.zkzn.les.basicInfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.dao.LaunchStrategyDao;
import com.zkzn.les.basicInfo.dao.MaterialLaunchRemarkDao;
import com.zkzn.les.basicInfo.pojo.LaunchStrategy;
import com.zkzn.les.basicInfo.pojo.LaunchStrategyNew;
import com.zkzn.les.basicInfo.pojo.MaterialLaunchRemark;
import com.zkzn.les.basicInfo.service.LaunchStrategyService;

@Service
public class LaunchStrategyServiceImpl implements LaunchStrategyService{
	
	@Autowired
	private LaunchStrategyDao launchStrategyDao;
	@Autowired
	private MaterialLaunchRemarkDao materialLaunchRemarkDao;

	@Override
	public List<LaunchStrategy> list(LaunchStrategy launchStrategy) {
		return launchStrategyDao.list(launchStrategy);
	}

	@Override
	public PageInfo<LaunchStrategy> listByPage(LaunchStrategy launchStrategy) {
		 PageHelper.startPage(launchStrategy.getPage(),launchStrategy.getLimit());
		List<LaunchStrategy> list=launchStrategyDao.list(launchStrategy);
		PageInfo<LaunchStrategy> page = new PageInfo<LaunchStrategy>(list);
		 
		return page;
	}

	@Override
	public int insert(LaunchStrategy launchStrategy) {
		return launchStrategyDao.insert(launchStrategy);
	}

	@Override
	public int update(LaunchStrategy launchStrategy) {
		return launchStrategyDao.update(launchStrategy);
	}

	@Override
	public int delete(List<String> list) {
		return launchStrategyDao.delete(list);
	}

	@Override
	public int insertOrUpdate(List<LaunchStrategy> list) {
		int i=0;
		if(list!=null&&list.size()>0){
			for(LaunchStrategy g:list){
				if(g.getId()==null||"".equals(g.getId())){
					launchStrategyDao.insert(g);
				}else{
					launchStrategyDao.update(g);
				}
				i++;
			}
		}
		return i;
	}
	@Override
	public int saveBatch(List<LaunchStrategy> list) {
		
		int resultInt =launchStrategyDao.saveBatch(list);
		/*LaunchStrategy launchStrategy = new LaunchStrategy();
		MaterialLaunchRemark materialLaunchRemark = new MaterialLaunchRemark(); 
		for (LaunchStrategy launchStrategyNew : list) {
			launchStrategy.setOrderType(launchStrategyNew.getOrderType());
			launchStrategy.setMaterialStatus(launchStrategyNew.getMaterialStatus());
			launchStrategy.setWarehouseCode(launchStrategyNew.getWarehouseCode());
			launchStrategy.setArea(launchStrategyNew.getArea());
			launchStrategy.setStationCode(launchStrategyNew.getStationCode());
			launchStrategy.setPositionNumber(launchStrategyNew.getPositionNumber());
			launchStrategy.setPositionCode(launchStrategyNew.getPositionCode());
			List<LaunchStrategy> launchStrategyList = launchStrategyDao.list(launchStrategy);
			if(launchStrategyList.size()==0) {
				launchStrategy.setCreateUserId(currUid);
				launchStrategyDao.insert(launchStrategy);
			}
			materialLaunchRemark.setMaterialCode(launchStrategyNew.getMaterialCode());
			materialLaunchRemark.setMaterialDesc(launchStrategyNew.getMaterialDesc());
			materialLaunchRemark.setWarehouseCode(launchStrategyNew.getWarehouseCode());
			materialLaunchRemark.setArea(launchStrategyNew.getArea());
			materialLaunchRemark.setStationCode(launchStrategyNew.getStationCode());
			List<MaterialLaunchRemark> materialLaunchRemarkList = materialLaunchRemarkDao.listMaterialLaunchRemark(materialLaunchRemark);
			if(materialLaunchRemarkList.size()==0) {
				materialLaunchRemark.setRemarks(launchStrategyNew.getRemarks());
				materialLaunchRemarkDao.insert(materialLaunchRemark);
			}
		}
		//T_LAUNCH_STRATEGY去重新增
		//t_material_LAUNCH_REMARK去重新增
		  */
		 
		return resultInt; 
	}
}

/*
 * 文 件 名:  OverstorageStrategyServiceImpl.java
 * 版    权:  
 * 描    述:  描述:
 * 修 改 人:  liusongshan 
 * 修改时间:  2018年8月7日
 * 跟踪单号:  跟踪单号:
 * 修改单号:  修改单号:
 * 修改内容:  修改内容:
 */
package com.zkzn.les.basicInfo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.dao.OverstorageStrategyDao;
import com.zkzn.les.basicInfo.pojo.OverstorageStrategy;
import com.zkzn.les.basicInfo.service.OverstorageStrategyService;
import com.zkzn.les.basicInfo.util.PageUtil;
import com.zkzn.les.basicInfo.util.StringUtil;

/**.
 * 功能描述:
 * 时间:  2018年8月7日
 * @author  liusongshan  
 * 
 */
@Service
public class OverstorageStrategyServiceImpl implements OverstorageStrategyService {
	
	@Autowired
	private OverstorageStrategyDao overstorageStrategyDao;

	/**.
	 * 重载方法
	 * 功能描述:查询超储策略列表 分页
	 * @param overstorageStrategy
	 * @return
	 */
	@Override
	public PageInfo<OverstorageStrategy> listByPage(OverstorageStrategy overstorageStrategy) {
		PageUtil.setPageParam(overstorageStrategy);
		List<OverstorageStrategy> list = this.overstorageStrategyDao.list(overstorageStrategy);
		 PageInfo<OverstorageStrategy> page = new PageInfo<OverstorageStrategy>(list);
	 
		return  page;
	}

	/**.
	 *
	 * 重载方法
	 * 
	 * 功能描述:查询超储策略列表 
	 * 
	 * @param overstorageStrategy
	 * @return
	 * @author  刘松山
	 *
	 * 时间:  2020-04-30 15:50
	 */
	@Override
	public List<OverstorageStrategy> listBylist(OverstorageStrategy overstorageStrategy) {
		List<OverstorageStrategy> list = this.overstorageStrategyDao.list(overstorageStrategy);
		return list;
	}

	 
	/**.
	 * 重载方法
	 * 功能描述:
	 * @param overstorageStrategy
	 * @return
	 */
	@Override
	public int editBoundCount(OverstorageStrategy overstorageStrategy) {
		 List<OverstorageStrategy> list =  new ArrayList<>();
		 String materialCodes= overstorageStrategy.getMaterialCode();
		 String factory =overstorageStrategy.getFactory();
		 String storageLocation =overstorageStrategy.getStorageLocation();
		 double boundCount =overstorageStrategy.getBoundCount();
		 if(StringUtil.isBlankOrEmpty(materialCodes) || StringUtil.isBlankOrEmpty(factory)){
			return 0; 
		 }
		 String[]  materialCodeArray=materialCodes.split(",");
		 String[]  factoryArray=factory.split(",");
		 String[]  storageLocationArray=storageLocation.split(",");
		 
		 for(int  i=0;i<materialCodeArray.length;i++){
			 OverstorageStrategy o = new OverstorageStrategy();
			 o.setBoundCount(boundCount);
			 o.setMaterialCode(materialCodeArray[i]);
			 o.setFactory(factoryArray[i]);
			 o.setStorageLocation(storageLocationArray[i]);
			 list.add(o);
		 }
		return this.overstorageStrategyDao.editBoundCount(list);
	}

	/**.
	 * 重载方法
	 * 功能描述:
	 * @param data
	 * @return
	 */
	@Override
	public int importExcel(String[][] data) {
		
	for(int i=0;i<data.length;i++){	
		List<OverstorageStrategy> list = new ArrayList<>();
		String[] subData = data[i];
		for(int j=0;j<subData.length;j++){
			OverstorageStrategy overstorageStrategy = new OverstorageStrategy();
			overstorageStrategy.setMaterialCode(subData[2]);
			overstorageStrategy.setFactory(subData[5]);
			overstorageStrategy.setStorageLocation(subData[7]);
			overstorageStrategy.setBoundCount(Integer.parseInt(subData[8]));
			list.add(overstorageStrategy);
		}
		this.overstorageStrategyDao.editBoundCount(list);
	}
		return -1;
	}

	/**.
	 *
	 * 重载方法
	 * 
	 * 功能描述:查询要导出的数据数量
	 * 
	 * @param overstorageStrategy
	 * @return
	 * @author  刘松山
	 *
	 * 时间:  2018-11-04 19:50
	 */
	@Override
	public int queryCount(OverstorageStrategy overstorageStrategy) {
		 
		return this.overstorageStrategyDao.queryCount(overstorageStrategy);
	}

	
}

/*
 * 文 件 名:  CostCenterServiceImpl.java
 * 版    权:  
 * 描    述:  描述:
 * 修 改 人:  刘松山 
 * 修改时间:  2020年8月11日
 * 跟踪单号:  跟踪单号:
 * 修改单号:  修改单号:
 * 修改内容:  修改内容:
 */
package com.zkzn.les.basicInfo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkzn.les.basicInfo.dao.CostCenterDao;
import com.zkzn.les.basicInfo.pojo.CostCenter;
import com.zkzn.les.basicInfo.service.CostCenterService;
import com.zkzn.les.basicInfo.service.DictItemsService;
import com.zkzn.les.basicInfo.pojo.DictItems;

/**.
 *
 * 功能描述:
 * 
 * 时间:  2020-08-11 15:01
 *
 * @author  刘松山  
 * 
 */
@Service
public class CostCenterServiceImpl implements CostCenterService {

	@Autowired
	private CostCenterDao costCenterDao;

	@Autowired
	private DictItemsService dictItemsService;
	/**.
	 *
	 * 重载方法
	 * 
	 * 功能描述:
	 * 
	 * @param costCenter
	 * @return
	 * @author  刘松山
	 *
	 * 时间:  2020-08-11 15:07
	 */
	@Override
	public List<CostCenter> listCostCenter(CostCenter costCenter) {
		 
		List<CostCenter> list = costCenterDao.listCostCenter(costCenter);
		
		List<String> dicts = new ArrayList<String>();
	 
		dicts.add("costCenter_type");//成本中心类型
		 
		List<DictItems> dictItems = dictItemsService.listDictItemsByType(dicts);
		
		if(list!=null && dictItems!=null ){
			for(CostCenter tempCostCenter:list){
				for(DictItems dictItem:dictItems){
					 
					if("costCenter_type".equals(dictItem.getDictTypeId()) && dictItem.getItemValue().equals(""+tempCostCenter.getCostCenterType()) ){
						tempCostCenter.setCostCenterTypeName(dictItem.getItemName());
					}
					 
				}
			}
		}
		return list;
	}
}

/*
 * 文 件 名:  CostCenterService.java
 * 版    权:  
 * 描    述:  描述:
 * 修 改 人:  刘松山 
 * 修改时间:  2020年8月11日
 * 跟踪单号:  跟踪单号:
 * 修改单号:  修改单号:
 * 修改内容:  修改内容:
 */
package com.zkzn.les.basicInfo.service;

import java.util.List;

import com.zkzn.les.basicInfo.pojo.CostCenter;

/**.
 *
 * 功能描述:成本中心管理业务逻辑层
 * 
 * 时间:  2020-08-11 15:00
 *
 * @author  刘松山  
 * 
 */
public interface CostCenterService {

	/**
	 * . 
	 *
	 * 功能描述:获取成本中心列表
	 * 
	 * @param costCenter
	 * @return
	 * @author  刘松山  
	 *
	 *时间:  2020-08-11 15:05
	 *
	 */
	List<CostCenter> listCostCenter(CostCenter costCenter);
	
	 
}

/*
 * 文 件 名:  StrategyInfoServiceImpl.java
 * 描    述:  <描述>
 * 修 改 人:  liusongshan  
 * 修改时间:  2018年7月31日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.zkzn.les.basicInfo.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.zkzn.les.basicInfo.dao.StrategyInfoDao;

import com.zkzn.les.basicInfo.service.StrategyInfoService;

import javax.annotation.Resource;

/* *
 * @Author 刘松山
 * @Description 策略配置
 * @Date 13:34 2021/6/18
 * @Param 
 * @return 
 **/

@Service
public class StrategyInfoServiceImpl implements StrategyInfoService {

	@Resource
	private StrategyInfoDao strategyInfoDao;

	/**.
	 * 重载方法
	 * @param map
	 * @return
	 */
	@Override
	public int save(Map<String,Object> map) {
		String dictId = map.get("dictId")+"";
		 this.strategyInfoDao.remove(dictId);
		map.put("createTime",new Date());
		return this.strategyInfoDao.save(map);
	}




	/**.
	 * 重载方法
	 * @return
	 */
	@Override
	public List<Map<String,Object>>  listStrategyInfoList(Map<String,Object> map) {

		return this.strategyInfoDao.listStrategyInfoList(map);
	}

}

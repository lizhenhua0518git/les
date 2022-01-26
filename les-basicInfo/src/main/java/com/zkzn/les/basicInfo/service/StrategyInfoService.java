/*
 * 文 件 名:  StrategyInfoService.java
 * 描    述:  <描述>
 * 修 改 人:  liusongshan
 * 修改时间:  2018年7月31日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.zkzn.les.basicInfo.service;

import java.util.List;
import java.util.Map;



/**.
 * 功能描述:模式配置维护接口层
 * 
 * @author  Administrator  
 * see  [相关类/方法]
 */
public interface StrategyInfoService {

	/**.
	 * 
	 * 功能描述:保存策略配置
	 * @param map
	 * @return
	 */
	int save(Map<String,Object> map);
	


	List<Map<String,Object>> listStrategyInfoList(Map<String,Object> map);
	

	
}

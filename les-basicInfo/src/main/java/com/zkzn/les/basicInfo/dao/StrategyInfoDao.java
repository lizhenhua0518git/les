/*
 * 文 件 名:  StrategyInfoFDao.java
 * 描    述:  <描述>
 * 修 改 人:  liusongshan
 * 修改时间:  2018年7月31日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.zkzn.les.basicInfo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


/**.
 * 功能描述:模式配置维护的数据层
 * 
 * 
 * @author  Administrator  
 * see  [相关类/方法]
 */
@Mapper
public interface StrategyInfoDao {

	/**.
	 * 
	 * 功能描述:保存模式配置 
	 * @param map
	 * @return
	 */
	int save(Map<String,Object> map);
	

	int remove (String id);

	/**.
	 * 
	 * 功能描述:获取模式配置列表 没有分页
	 * @return
	 */
	List<Map<String,Object>> listStrategyInfoList(Map<String,Object> map);

	
}

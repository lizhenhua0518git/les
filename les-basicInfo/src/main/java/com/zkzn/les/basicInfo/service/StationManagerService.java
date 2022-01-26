package com.zkzn.les.basicInfo.service;

import java.util.List;
import java.util.Map;

/**.
 * 
 * @author wangzhou
 *	工位管理
 */
public interface StationManagerService {

	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年3月26日上午10:04:18
	 * List<Map<String,Object>>
	 * @return
	 * 功能描述:查询工位信息
	 */
	List<Map<String,Object>> queryStationCode();
}

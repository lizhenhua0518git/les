package com.zkzn.les.basicInfo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
/**.
 * 
 * @author wangzhou
 * 工位
 */

@Mapper
public interface StationManagerDao {

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

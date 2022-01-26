package com.zkzn.les.basicInfo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zkzn.les.basicInfo.pojo.OutsideApiLog;


/**.
 * 
 * @author wangzhou
 * @date 2020年8月5日
 * @Description 外部系统调用LES系统接口日志
 */
@Mapper
public interface OutsideApiLogDao {

	
	
	/**.
	 * 
	 * @param outsideApiLog
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月5日
	 * @Description:查询接口调用日志
	 */
	List<OutsideApiLog> listOutsideApiLog(OutsideApiLog outsideApiLog);
}

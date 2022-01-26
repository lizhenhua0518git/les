package com.zkzn.les.basicInfo.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.pojo.OutsideApiLog;

/**.
 * 
 * @author wangzhou
 * @date 2020年8月5日
 * @Description :外部系统接口调用日志
 */
public interface OutsideApiLogService {

	/**.
	 * 
	 * @param outsideApiLog
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月5日
	 * @Description:查询外部系统接口调用日志
	 */
	List<OutsideApiLog> listOutsideApiLog(OutsideApiLog outsideApiLog) throws Exception;
	
	/**.
	 * 
	 * @param outsideApiLog
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月5日
	 * @Description:分页查询外部系统接口调用日志
	 */
	PageInfo<OutsideApiLog> listOutsideApiLogPage(OutsideApiLog outsideApiLog) throws Exception;
}

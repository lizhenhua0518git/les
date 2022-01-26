package com.zkzn.les.basicInfo.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.dao.OutsideApiLogDao;
import com.zkzn.les.basicInfo.pojo.OutsideApiLog;
import com.zkzn.les.basicInfo.service.OutsideApiLogService;
import com.zkzn.les.basicInfo.util.PageUtil;


/**.
 * 
 * @author wangzhou
 * @date 2020年8月5日
 * @Description:外部系统调用接口日志Service
 */
@Service
public class OutsideApiLogServiceImpl implements OutsideApiLogService {

	@Autowired
	private OutsideApiLogDao  outsideApiLogDao;
	
	@Override
	public List<OutsideApiLog> listOutsideApiLog(OutsideApiLog outsideApiLog) throws Exception {
		// TODO Auto-generated method stub
		
		List<OutsideApiLog> list = outsideApiLogDao.listOutsideApiLog(outsideApiLog);
		for(OutsideApiLog tempOutsideApiLog:list){
			if(tempOutsideApiLog.getRequestParamByte()!=null){
				tempOutsideApiLog.setRequestParam(new String(tempOutsideApiLog.getRequestParamByte(),"UTF-8"));
			}
			if(tempOutsideApiLog.getResultInfoByte()!=null){
				tempOutsideApiLog.setResultInfo(new String(tempOutsideApiLog.getResultInfoByte(),"UTF-8"));
			}
		}
		return list;
	}

	@Override
	public PageInfo<OutsideApiLog> listOutsideApiLogPage(OutsideApiLog outsideApiLog) throws Exception {
		// TODO Auto-generated method stub
		PageUtil.setPageParam(outsideApiLog);
		List<OutsideApiLog> list = listOutsideApiLog(outsideApiLog);
		PageInfo<OutsideApiLog> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

}

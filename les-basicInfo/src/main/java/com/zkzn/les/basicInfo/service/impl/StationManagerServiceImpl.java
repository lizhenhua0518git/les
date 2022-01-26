package com.zkzn.les.basicInfo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkzn.les.basicInfo.dao.StationManagerDao;
import com.zkzn.les.basicInfo.service.StationManagerService;
/**.
 * 
 * @author wangzhou
 *	工位管理
 */
@Service
public class StationManagerServiceImpl implements StationManagerService{

	@Autowired
	private StationManagerDao stationManagerDao;
	
	@Override
	public List<Map<String, Object>> queryStationCode() {
		// TODO Auto-generated method stub
		return stationManagerDao.queryStationCode();
	}

}

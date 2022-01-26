package com.zkzn.les.basicInfo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.dao.StrategyCarDao;
import com.zkzn.les.basicInfo.pojo.StrategyCar;
import com.zkzn.les.basicInfo.service.StrategyCarService;
import com.zkzn.les.basicInfo.util.PageUtil;
/**.
 * 
 *
 * 功能描述：载具策略service实现层
 * @author wangzhou
 * 时间：2018年9月2日
 */
@Service
public class StrategyCarServiceImpl implements StrategyCarService{

	@Autowired
	private StrategyCarDao strategyCarDao;
	
	@Override
	public List<StrategyCar> listStrategyCar(StrategyCar strategyCar) {
		// TODO Auto-generated method stub
		return strategyCarDao.listStrategyCar(strategyCar);
	}

	@Override
	public PageInfo<StrategyCar> listStrategyCarPage(StrategyCar strategyCar) {
		// TODO Auto-generated method stub
		PageUtil.setPageParam(strategyCar);
		List<StrategyCar> strategyCarList = listStrategyCar(strategyCar);
		PageInfo<StrategyCar> page = new PageInfo<StrategyCar>(strategyCarList);
	 
		return page;
	}

	@Override
	public int insertStrategy(List<Map<String, Object>> param) {
		// TODO Auto-generated method stub
		return strategyCarDao.insertStrategy(param);
	}

	@Override
	public List<Map<String, Object>> listHasStrategy(List<String> substrList) {
		// TODO Auto-generated method stub
		return strategyCarDao.listHasStrategy(substrList);
	}

	@Override
	public int insertStrategySing(StrategyCar strategyCar) {
		// TODO Auto-generated method stub
		return strategyCarDao.insertStrategySing(strategyCar);
	}

	@Override
	public int updateStrategy(StrategyCar strategyCar) {
		// TODO Auto-generated method stub
		return strategyCarDao.updateStrategy(strategyCar);
	}

	@Override
	public int deleteStrategy(List<String> ids) {
		// TODO Auto-generated method stub
		return strategyCarDao.deleteStrategy(ids);
	}

	@Override
	public int updateStrategyList(List<Map<String, Object>> strageyList) {
		// TODO Auto-generated method stub
		return strategyCarDao.updateStrategyList(strageyList);
	}

	@Override
	public int countStrategy(StrategyCar strategyCar) {
		// TODO Auto-generated method stub
		return strategyCarDao.countStrategy(strategyCar);
	}

	@Override
	public int insertSapBom(List<Map<String, Object>> sapBomList) {
		// TODO Auto-generated method stub
		return strategyCarDao.insertSapBom(sapBomList);
	}

	/**.
	 *
	 * 重载方法
	 * 
	 * 功能描述:
	 * 
	 * @param strategyCar
	 * @return
	 * @author  刘松山
	 *
	 * 时间:  2020-05-22 17:22
	 */
	@Override
	public int countByInfo(StrategyCar strategyCar) {
		 
		return strategyCarDao.countByInfo(strategyCar);
	}

}

package com.zkzn.les.basicInfo.pojo;

import java.util.List;

import org.apache.poi.xssf.streaming.SXSSFSheet;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.service.StrategyCarService;
import com.zkzn.les.basicInfo.util.ExcelHandleUtil;
/**
 * 
 *
 * 功能描述：载具策略导出线程
 * @author wangzhou
 * 时间：2018年10月29日
 */
public class StrategyThread implements Runnable{

	private StrategyCarService strategyCarService;
	
	private StrategyCar strategyCar;
	
	private SXSSFSheet sxssSheet;
	
	private List<String> title;
	
	private List<String> filedNames;
	
	public StrategyThread(StrategyCarService strategyCarService,StrategyCar strategyCar,SXSSFSheet sxssSheet){
		this.strategyCarService = strategyCarService;
		this.strategyCar = strategyCar;
		this.sxssSheet = sxssSheet;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		PageInfo<StrategyCar> strategyPage =  strategyCarService.listStrategyCarPage(strategyCar);
		List<StrategyCar> strategyList  = strategyPage.getList();
		try {
			ExcelHandleUtil.addDatatoRow(strategyList, title, filedNames, sxssSheet);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public StrategyCarService getStrategyCarService() {
		return strategyCarService;
	}

	public void setStrategyCarService(StrategyCarService strategyCarService) {
		this.strategyCarService = strategyCarService;
	}

	public StrategyCar getStrategyCar() {
		return strategyCar;
	}

	public void setStrategyCar(StrategyCar strategyCar) {
		this.strategyCar = strategyCar;
	}

	public SXSSFSheet getSxssSheet() {
		return sxssSheet;
	}

	public void setSxssSheet(SXSSFSheet sxssSheet) {
		this.sxssSheet = sxssSheet;
	}

	public List<String> getTitle() {
		return title;
	}

	public void setTitle(List<String> title) {
		this.title = title;
	}

	public List<String> getFiledNames() {
		return filedNames;
	}

	public void setFiledNames(List<String> filedNames) {
		this.filedNames = filedNames;
	}
	
	

}

/*
 * 文 件 名:  OverstorageStrategyThead.java
 * 版    权:  
 * 描    述:  描述:
 * 修 改 人:  刘松山 
 * 修改时间:  2018年11月4日
 * 跟踪单号:  跟踪单号:
 * 修改单号:  修改单号:
 * 修改内容:  修改内容:
 */
package com.zkzn.les.basicInfo.pojo;

import java.util.List;

import org.apache.poi.xssf.streaming.SXSSFSheet;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.service.OverstorageStrategyService;
import com.zkzn.les.basicInfo.util.ExcelHandleUtil;

 

/**
 * .
 *
 * 功能描述:超储策略大数据导出线程
 * 
 * 时间: 2018-11-04 20:11
 *
 * @author 刘松山
 * 
 */
public class OverstorageStrategyThead implements Runnable {

	private OverstorageStrategyService overstorageStrategyService;

	private OverstorageStrategy overstorageStrategy;

	private SXSSFSheet sxssSheet;

	private List<String> title;

	private List<String> filedNames;

	 

	/** 
	 * <默认构造函数>
	 */
	public OverstorageStrategyThead(OverstorageStrategyService overstorageStrategyService,
			OverstorageStrategy overstorageStrategy, SXSSFSheet sxssSheet) {
		super();
		this.overstorageStrategyService = overstorageStrategyService;
		this.overstorageStrategy = overstorageStrategy;
		this.sxssSheet = sxssSheet;
	}

	/**
	 * .
	 *
	 * 重载方法
	 * 
	 * 功能描述:
	 * 
	 * @author 刘松山
	 *
	 *         时间: 2018-11-04 20:11
	 */
	@Override
	public void run() {
	 
		List<OverstorageStrategy> list =this.overstorageStrategyService.listBylist(overstorageStrategy);
		try {
			ExcelHandleUtil.addDatatoRow(list, title, filedNames, sxssSheet);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public OverstorageStrategyService getOverstorageStrategyService() {
		return overstorageStrategyService;
	}

	public void setOverstorageStrategyService(OverstorageStrategyService overstorageStrategyService) {
		this.overstorageStrategyService = overstorageStrategyService;
	}

	public OverstorageStrategy getOverstorageStrategy() {
		return overstorageStrategy;
	}

	public void setOverstorageStrategy(OverstorageStrategy overstorageStrategy) {
		this.overstorageStrategy = overstorageStrategy;
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

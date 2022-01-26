package com.zkzn.les.basicInfo.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 
import com.zkzn.les.basicInfo.service.StrategyCarService;
import com.zkzn.les.basicInfo.util.StringUtil;

public class StrategySyncThread implements Runnable{

	/**.
	 *
	 * 重载方法
	 * 
	 * 功能描述:
	 * 
	 * @author  刘松山
	 *
	 * 时间:  2020-05-25 10:46
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

/*	
	private Logger logger = LoggerFactory.getLogger(StrategySyncThread.class); 
	
	private JCoTable tb;
	
	private StrategyCarService strategyCarService;
	
	private int rowCount;//结束的值
	
	private int startCount;//开始的数字
	
	private String productLine;
	private String factory;
	private String subFactory;
	private String cartTypeCode;
	private String bomuse;
	
	public StrategySyncThread(JCoTable tb,StrategyCarService strategyCarService,int startCount,int rowCount){
		this.tb = tb;
		this.strategyCarService = strategyCarService;
		this.rowCount = rowCount;
		this.startCount = startCount;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//将excle中的内容以
		Map<String,Object> excleObjec = new HashMap<String,Object>();
		//存放 产品线-分厂代码-工位代码-车型物料号-工位物料-工序物料 拼接字符
		List<String> strConcatList = new ArrayList<String>();
		Map<String,Object> rowMap = null;
		//插入的list集合
		List<Map<String,Object>> insertList = new ArrayList<Map<String,Object>>();
		//更新的list集合
		List<Map<String,Object>> updateList = new ArrayList<Map<String,Object>>();
		//bom记录表
		List<Map<String,Object>> bomList = new ArrayList<Map<String,Object>>();
		StringBuffer strBuffer=null;
		int count = tb.getNumRows();
		if(count<rowCount){
			rowCount = count;
		}
		for(int i=startCount;i<rowCount;i++){
			tb.setRow(i);
			strBuffer = new StringBuffer();
			strBuffer.append( tb.getString("ZLSCPX"))//产品线
			.append("-")
			.append( tb.getString("ZFCDM"))//分厂代码
			.append("-")
			.append( tb.getString("ZGWDM"))//工位代码
			.append("-")
			.append(StringUtil.replaceFirstZero(tb.getString("MATNR1")))//车型物料号
			.append("-")
			.append(StringUtil.replaceFirstZero(tb.getString("MATNR2")))//工位物料
			.append("-")
			.append(StringUtil.replaceFirstZero(tb.getString("MATNR3")));//工序物料
			if(!strConcatList.contains(strBuffer.toString())){
				strConcatList.add(strBuffer.toString());
			}
			rowMap = new HashMap<String,Object>();
			rowMap.put("productLine", tb.getString("ZLSCPX"));
			rowMap.put("subFactory", tb.getString("ZFCDM"));
			rowMap.put("stationCode",tb.getString("ZGWDM"));
			rowMap.put("cartTypeCode", StringUtil.replaceFirstZero(tb.getString("MATNR1")));
			rowMap.put("cartTypeDesc",tb.getString("MAKTX1"));
			rowMap.put("stationModuleCode", StringUtil.replaceFirstZero(tb.getString("MATNR2")));
			rowMap.put("stationDesc", tb.getString("MAKTX2"));
			rowMap.put("processCode", StringUtil.replaceFirstZero(tb.getString("MATNR3")));
			rowMap.put("processDesc", tb.getString("MAKTX3"));
			rowMap.put("projectCode", "X");//项目号默认X
			rowMap.put("cartCode", cartTypeCode);
			rowMap.put("type","null");
			rowMap.put("carrierType","null");
			rowMap.put("carrierCode","null");
			rowMap.put("putLevel","null");
			rowMap.put("secondCarrierType", "null");
			rowMap.put("superCarrier", "null");
			rowMap.put("factory", factory);
			rowMap.put("bomuse", bomuse);
			rowMap.put("materialCode", tb.getString("MATNR4"));//物料编码
			rowMap.put("materialDesc", tb.getString("MAKTX4"));//物料描述
			rowMap.put("sublineCode", tb.getString("ZFXBS"));//辅线工位
			rowMap.put("sublineDesc", tb.getString("ZFXBSMS"));//辅线工位描述
			rowMap.put("pipeLine", "null");
			bomList.add(rowMap);
			excleObjec.put(strBuffer.toString(), rowMap);
		}
		if(bomList.size()>0){
			strategyCarService.insertSapBom(bomList);
		}
		handleData(excleObjec,insertList,updateList,strConcatList);
		
	}
	
	public synchronized void  handleData(Map<String,Object> excleObjec,List<Map<String,Object>> insertList,List<Map<String,Object>> updateList,List<String> strConcatList){
		
		logger.debug(excleObjec.size()+"===================excleObjec==="+Thread.currentThread().getName());
		
		if(strConcatList.size()>0){
			List<Map<String,Object>> hasList = new ArrayList<Map<String,Object>> ();
					//strategyCarService.queryHasStrategy(strConcatList);//先去掉查重功能
			disassemblyList(hasList,excleObjec,insertList,updateList);
		}
		logger.debug(insertList.size()+"===================insertList=="+Thread.currentThread().getName());
		if(insertList.size()>0){
			strategyCarService.insertStrategy(insertList);
		}
		logger.debug(updateList.size()+"===================updateList=="+Thread.currentThread().getName());
		if(updateList.size()>0){
			strategyCarService.updateStrategyList(updateList);
		}
	}
	
	*//**.
	 * 
	 * 功能描述：查找出更新和插入的数据
	 * 作者：wangzhou
	 * 时间：2018年10月25日
	 * @param hasList
	 * @param excleObjec
	 * @param insertList
	 * @param updateList
	 *//*
	@SuppressWarnings("unchecked")
	public synchronized void disassemblyList(List<Map<String,Object>> hasList,Map<String,Object> excleObjec,List<Map<String,Object>> insertList,List<Map<String,Object>> updateList){
		logger.debug(hasList.size()+"===================hasList==="+Thread.currentThread().getName());
		if(hasList!=null && hasList.size()>0){
			String tempStr = "";
			Map<String,Object> tempMap = null;
			for(Map<String,Object> map:hasList){
				tempStr = (String) map.get("SUBSTR");
				if(tempStr!=null && excleObjec.get(tempStr)!=null){
					tempMap = (Map<String,Object>)excleObjec.get(tempStr);
					tempMap.put("id", map.get("id"));
					updateList.add(tempMap);
					excleObjec.remove(tempStr);
				}
			}

			if(excleObjec!=null && excleObjec.size()>0){
				Set<Entry<String, Object>>  set = excleObjec.entrySet();
				Iterator<Entry<String, Object>>  iterator = set.iterator();
				Entry<String, Object> entry = null;
				while(iterator.hasNext()){
					entry = iterator.next();
					insertList.add((Map<String,Object>)entry.getValue());
				}
			}
		}else{
			if(excleObjec!=null && excleObjec.size()>0){
				Set<Entry<String, Object>>  set = excleObjec.entrySet();
				Iterator<Entry<String, Object>>  iterator = set.iterator();
				Entry<String, Object> entry = null;
				while(iterator.hasNext()){
					entry = iterator.next();
					insertList.add((Map<String,Object>)entry.getValue());
				}
			}
		}
	}
	public JCoTable getTb() {
		return tb;
	}
	public void setTb(JCoTable tb) {
		this.tb = tb;
	}
	public StrategyCarService getStrategyCarService() {
		return strategyCarService;
	}
	public void setStrategyCarService(StrategyCarService strategyCarService) {
		this.strategyCarService = strategyCarService;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getStartCount() {
		return startCount;
	}
	public void setStartCount(int startCount) {
		this.startCount = startCount;
	}
	public String getProductLine() {
		return productLine;
	}
	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public String getSubFactory() {
		return subFactory;
	}
	public void setSubFactory(String subFactory) {
		this.subFactory = subFactory;
	}
	public String getCartTypeCode() {
		return cartTypeCode;
	}
	public void setCartTypeCode(String cartTypeCode) {
		this.cartTypeCode = cartTypeCode;
	}
	public String getBomuse() {
		return bomuse;
	}
	public void setBomuse(String bomuse) {
		this.bomuse = bomuse;
	}*/
	
}

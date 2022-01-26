package com.zkzn.les.basicInfo.util;

import java.util.ArrayList;
import java.util.List;

public final class InstoreUtil {
	
	public final static String OVERSTOCK_NOTICE = "overStock.notice";//超储通知
	public final static String URGENT_NOTICE = "urgent.notice";//紧急单通知
	public final static String CENTRAL_WAREHOUSE = "00500100";//中心仓库
	public final static String PREASSEMBLE_WAREHOUSE = "00500400";//预组装系统部件仓库
	public final static String GENERAL_ASSEMBLY_WAREHOUSE = "00500300";//总组装系统部件仓库
	public final static String DONGJIAN_WAREHOUSE = "00500500";//动检总库
	public final static String DYNAMIC__WAREHOUSE = "00500600";//动检集约库
	public final static String EASTERN_WAREHOUSE = "00500200";//东区系统部件仓库
	public final static String TANGSHAN_LOCOMOTIVE = "00000000";//唐山机车有限公司
	public final static String LOGISTICS_DEPARTMENT = "00500000";//物流部
	public final static String PREASSEMBLE_LINEPIPE__WAREHOUSE="00500700";//预组装管线材库
	public final static String GENERAL_LINEPIPE_WAREHOUSE="00500800";//总组装管线材库
	public final static String LONG_SECTION_BAR_WAREHOUSE="00500900";//长型材库
	public final static String SORT_SECTION_BAR_WAREHOUSE="00501000";//短型材库
	public final static String LARGE_WAREHOUSE="00501100";//小部件库
	public final static String CARBON_STEEL_WAREHOUSE="00501200";//碳钢库
	public final static String BOGIE_ENDITEMS_WAREHOUSE="00501300";//转向架成品库
	public final static String BOGIE_ACCESSORIES_WAREHOUSE="00501400";//转向架配件库
	
	public final static String MR_SAP = "MR";//默认sap管理工
	
	public final static String XBJQ = "12";//默认sap管理工
	public final static String DBJQ = "11";//默认sap管理工
	public final static String LDQ = "19";//默认sap管理工
	
	public final static List<String> AreaTypeXBJQ=new ArrayList<String>();//小部件区
	public final static List<String> AreaTypeDBJQ=new ArrayList<String>();//大部件区
	public final static List<String> AreaTypeLDQ=new ArrayList<String>();//料斗区
	
	public final static String ZJQ="03";//质检区类型编码
	public final static String ZCQ="04";//转储区类型编码
	public final static String HDK="13";//巷道口类型编码
	
	public final static List<String> getAreaTypeXBJQ() {
		AreaTypeXBJQ.add("T10");
		AreaTypeXBJQ.add("T31");
		AreaTypeXBJQ.add("L10");
		AreaTypeXBJQ.add("L20");
		return AreaTypeXBJQ;
	}
	
	public final static List<String> getAreaTypeDBJQ() {
		AreaTypeDBJQ.add("T20");
		AreaTypeDBJQ.add("T32");
		return AreaTypeDBJQ;
	}
	public final static List<String> getAreaTypeLDQ() {
		AreaTypeLDQ.add("D00");
		AreaTypeLDQ.add("Z00");
		return AreaTypeLDQ;
	}
}

/*
 * 文 件 名:  StaticCode.java
 * 版    权:  
 * 描    述:  描述:
 * 修 改 人:  liusongshan 
 * 修改时间:  2018年8月23日
 * 跟踪单号:  跟踪单号:
 * 修改单号:  修改单号:
 * 修改内容:  修改内容:
 */
package com.zkzn.les.basicInfo.pojo;

/**
 * . 功能描述:常用参数配置类 
 * 时间: 2018年8月23日
 * 
 * @author liusongshan
 * 
 */
public class StaticCode {

	// 普通物料平均标准卸货时长
	public final static String unloadValueOne = "strategyDictionary_1";
	// 普通平均标准点收时长
	public final static String timeValueOne = "strategyDictionary_2";

	// 普通物料 平均标准质检时长
	public final static String qualityValueOne = "strategyDictionary_3";

	// 普通物料平均标准转储时长:
	public final static String dumpValueOne = "strategyDictionary_4";
	// 普通物料平均标准上架时长:
	public final static String upperValueOne = "strategyDictionary_9";

	// 紧急物料平均标准点收时长
	public final static String timeValueTwo = "strategyDictionary_5";
	// 紧急物料 平均标准质检时长
	public final static String qualityValueTwo = "strategyDictionary_6";
	// 紧急物料平均标准转储时长:
	public final static String dumpValueTwo = "strategyDictionary_7";
	// 紧急物料平均标准上架时长:
	public final static String upperValueTwo = "strategyDictionary_10";
 
	//平均标准收货时长 24小时
	public final static String STANDARD_GET_GOODS ="1440";//standard_get_goods
	//平均标准发货时长
	public final static String STANDARD_SEND_OUT_GOODS ="1440";//standard_send_out_goods
	// 拣料标准时长
	public final static String STANDARD_PICK_GOODS ="30";//standard_pick_goods
	//集配标准时长
	public final static String STANDARD_MATCH_GOODS ="30";//standard_match_goods
	//发货（节点）标准时长
    public final static String STANDARD_OUT_GOODS ="30";//standard_match_goods
	//合格品转冻结标准时长
	 public final static String TRANSFER_QUALIFIED_TO_FREEZING  ="30";//transfer_qualified_to_freezing
	// 冻结转合格品标准时长 
	 public final static String TRANSFER_FREEZING_TO_QUALIFIED  ="30";//transfer_freezing_to_qualified
	
	// 载具策略
	public final static String packMarshallingStrategy = "strategyDictionary_8";
	
	//盘点计划
	public final static String STORE_CHECK_PLAN = "store_check_plan";
	//盘点任务
	public final static String STORE_CHECK_TASK = "store_check_task";
	//盘点报告
	public final static String STORE_CHECK_REPORT = "store_check_report";
	//损益单
	public final static String PROFIT_AND_LOSS = "profit_and_loss";
	//移库单
    public final static String STORE_TRANSFER = "store_transfer";
    //并库单
    public final static String STORE_MERGE = "store_merge";
    //定保基础维护版本号
    public final static String FIXED_MAINTENANCE = "fixed_maintenance";
  //定保维护计划版本号
    public final static String FIXED_MAINTENANCE_PLAN = "fixed_maintenance_plan";
	
	
	//策略类型
	public final static String STRATEGY_TYPE = "strategy_type";
	//上架策略
	public final static String PUT_ON_STRATEGY = "put_on_strategy";
	//下架策略
	public final static String PUT_DOWN_STRATEGY = "put_down_strategy";
	//存储策略
	public final static String STORE_STRATEGY = "store_strategy";
	//作业模式策略
	public final static String WORK_MODULE_STRATEGY = "work_module_strategy";
	//超储策略
	public final static String OVERSTORAGE_STRATEGY = "overstorage_strategy"; 
	//仓位转移策略
	public final static String TRANSFER_STRATEGY = "transfer_strategy"; 
	//仓位合并策略
	public final static String MERGE_STRATEGY = "merge_strategy"; 
	
	//集配策略列表
	public final static String ASSEMBLE_PLAN_STRATEGY_LIST = "assemble_plan_strategy_list"; 
	
	
	
	
}

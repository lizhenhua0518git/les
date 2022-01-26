/*
 * 文 件 名:  StrategyInfo.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  Administrator 工号
 * 修改时间:  2018年7月31日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.zkzn.les.basicInfo.pojo;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import com.zkzn.les.basicInfo.util.PageCondition;

/**.
 * 功能描述:模式配置实体类
 * 
 * @author Administrator
 * see [相关类/方法]
 */
public class StrategyInfo extends PageCondition {

	/**.
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	// 主键id
	private String id;
	// 策略名称
	private String strategyName;
	// 策略编码
	private String strategyCode;
	//策略描述
	private String strategyDesc;
	//策略类型
	private String strategyType;
	//策略类型名称
	private String strategyTypeName;
	//顺序号
	private String sortNo;
	
	//仓库地点
	private String storageLocation;
	//仓库名称
	private String storageLocationName;
 
	// 状态 0 停用 1 启用
	private Integer status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStrategyName() {
		return strategyName;
	}
	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}
	public String getStrategyCode() {
		return strategyCode;
	}
	public void setStrategyCode(String strategyCode) {
		this.strategyCode = strategyCode;
	}
	public String getStrategyDesc() {
		return strategyDesc;
	}
	public void setStrategyDesc(String strategyDesc) {
		this.strategyDesc = strategyDesc;
	}
	public String getStrategyType() {
		return strategyType;
	}
	public void setStrategyType(String strategyType) {
		this.strategyType = strategyType;
	}
	public String getSortNo() {
		return sortNo;
	}
	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}
	public String getStorageLocation() {
		return storageLocation;
	}
	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}
	public String getStorageLocationName() {
		return storageLocationName;
	}
	public void setStorageLocationName(String storageLocationName) {
		this.storageLocationName = storageLocationName;
	}
	 
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getStrategyTypeName() {
		return strategyTypeName;
	}
	public void setStrategyTypeName(String strategyTypeName) {
		this.strategyTypeName = strategyTypeName;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}

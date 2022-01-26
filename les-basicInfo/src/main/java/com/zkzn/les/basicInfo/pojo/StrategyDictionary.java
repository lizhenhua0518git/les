/*
 * 文 件 名:  BasicCreceiptStrategy.java
 * 版    权:  
 * 描    述:  <描述>
 * 修 改 人:  liusongshan 
 * 修改时间:  2018年8月2日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.zkzn.les.basicInfo.pojo;

import java.io.Serializable;

/**.
 * 功能描述:策略字典
 * 时间:  2018年8月2日
 * @author  liusongshan  
 *
 */
public class StrategyDictionary implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	// 主键Id
	private String  id;
	 
	// 1 普通物料平均标准卸货时长  2  普通物料平均标准点收时长 3普通物料平均标准质检时长 4 普通物料平均标准转储时长
	// 5  紧急物料平均标准点收时长 6 紧急物料平均标准质检时长 7 紧急物料平均标准转储时长  
	// 8 工装小车编组数量 9 普通物料平均标上架时长 10 紧急物料平均标上架时长
	private Integer type;
	// 数据值
	private Integer value;
	// 创建时间
	private String createTime;
	// 仓库编码
	private String warehouseCode;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	 
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
}

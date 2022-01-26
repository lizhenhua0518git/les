/*
 * 文 件 名:  BasicCreceiptStrategyPage.java
 * 版    权:  
 * 描    述:  <描述>
 * 修 改 人:  liusongshan 
 * 修改时间:  2018年8月2日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.zkzn.les.basicInfo.pojo;

/**.
 * 功能描述:收货基础策略页面数据实体类
 * 时间:  2018年8月2日
 * @author  liusongshan  
 *
 */
public class BasicCreceiptStrategyPage {

	// 普通物料平均标准卸货时长
	private Integer unloadValueOne;
	// 普通平均标准点收时长
	private Integer timeValueOne;
	// 紧急物料平均标准点收时长
	private Integer timeValueTwo;
	// 普通物料 平均标准质检时长
	private Integer qualityValueOne;
	// 紧急物料 平均标准质检时长
	private Integer qualityValueTwo;
	// 普通物料平均标准转储时长:
	private Integer dumpValueOne;
	// 紧急物料平均标准转储时长:
	private Integer dumpValueTwo;
	//普通物料平均标上架时长
	private Integer upFrameValueOne;
	//紧急物料平均标上架时长
	private Integer upFrameValueTwo;
	
	
	public Integer getUnloadValueOne() {
		return unloadValueOne;
	}
	public void setUnloadValueOne(Integer unloadValueOne) {
		this.unloadValueOne = unloadValueOne;
	}
	public Integer getTimeValueOne() {
		return timeValueOne;
	}
	public void setTimeValueOne(Integer timeValueOne) {
		this.timeValueOne = timeValueOne;
	}
	public Integer getTimeValueTwo() {
		return timeValueTwo;
	}
	public void setTimeValueTwo(Integer timeValueTwo) {
		this.timeValueTwo = timeValueTwo;
	}
	public Integer getQualityValueOne() {
		return qualityValueOne;
	}
	public void setQualityValueOne(Integer qualityValueOne) {
		this.qualityValueOne = qualityValueOne;
	}
	public Integer getQualityValueTwo() {
		return qualityValueTwo;
	}
	public void setQualityValueTwo(Integer qualityValueTwo) {
		this.qualityValueTwo = qualityValueTwo;
	}
	public Integer getDumpValueOne() {
		return dumpValueOne;
	}
	public void setDumpValueOne(Integer dumpValueOne) {
		this.dumpValueOne = dumpValueOne;
	}
	public Integer getDumpValueTwo() {
		return dumpValueTwo;
	}
	public void setDumpValueTwo(Integer dumpValueTwo) {
		this.dumpValueTwo = dumpValueTwo;
	}
	public Integer getUpFrameValueOne() {
		return upFrameValueOne;
	}
	public void setUpFrameValueOne(Integer upFrameValueOne) {
		this.upFrameValueOne = upFrameValueOne;
	}
	public Integer getUpFrameValueTwo() {
		return upFrameValueTwo;
	}
	public void setUpFrameValueTwo(Integer upFrameValueTwo) {
		this.upFrameValueTwo = upFrameValueTwo;
	}
 
}

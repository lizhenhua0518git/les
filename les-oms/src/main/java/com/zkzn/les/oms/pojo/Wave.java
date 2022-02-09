package com.zkzn.les.oms.pojo;

import com.zkzn.les.common.util.page.PageCondition;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.util.Date;


/**.
 *
 *
 * 功能描述：波次表实体类
 * @author wangzhou
 * 时间：2018年8月30日
 */
public class Wave extends PageCondition {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String id;//主键
	private String waveNum;//波次序号
	private String waveCode;//波次号
	private Date activeTime;//激活日期
	private Integer runningTrack;//运行节点 "0-待激活、1-进行中、2-已完成"
	private Date createTime;//创建时间
	private String createTimeS;//创建时间
	private String storageLocation;//库存地点
	private Integer type;//类型：1节拍化 2非节拍
	private String activeUid;//波次激活人
	private String tab;//tab
	private Date requiredTime;//需求时间
	private String requiredTimeS;//需求时间
	private String carCode;//车号代码


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getWaveNum() {
		return waveNum;
	}


	public void setWaveNum(String waveNum) {
		this.waveNum = waveNum;
	}


	public String getWaveCode() {
		return waveCode;
	}


	public void setWaveCode(String waveCode) {
		this.waveCode = waveCode;
	}


	public Date getActiveTime() {
		return activeTime;
	}


	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}


	public Integer getRunningTrack() {
		return runningTrack;
	}


	public void setRunningTrack(Integer runningTrack) {
		this.runningTrack = runningTrack;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getStorageLocation() {
		return storageLocation;
	}


	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public String getActiveUid() {
		return activeUid;
	}


	public void setActiveUid(String activeUid) {
		this.activeUid = activeUid;
	}

	public String getTab() {
		return tab;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}


	public Date getRequiredTime() {
		return requiredTime;
	}


	public void setRequiredTime(Date requiredTime) {
		this.requiredTime = requiredTime;
	}


	public String getCreateTimeS() {
		return createTimeS;
	}


	public void setCreateTimeS(String createTimeS) {
		this.createTimeS = createTimeS;
	}


	public String getRequiredTimeS() {
		return requiredTimeS;
	}


	public void setRequiredTimeS(String requiredTimeS) {
		this.requiredTimeS = requiredTimeS;
	}


	public String getCarCode() {
		return carCode;
	}


	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ReflectionToStringBuilder.toString(this);
	}

}

package com.zkzn.les.uas.pojo;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.zkzn.les.uas.util.PageCondition;
/**.
 * 用户登录日志管理
 * @author wangzhou
 *
 */
public class Login extends PageCondition{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4103604088904889675L;
	
	
	private String id;//id
	private String userId; 	//用户id
	private Date  logTime;	//操作时间
	private Integer type;		//操作类型 0-登出 1-登入
	private String userName; //追加 用户名
	private Date startTime;
	private Date endTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date  getLogTime() {
		return logTime;
	}
	public void setLogTime(Date  logTime) {
		this.logTime = logTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ReflectionToStringBuilder.toString(this);
	}

}

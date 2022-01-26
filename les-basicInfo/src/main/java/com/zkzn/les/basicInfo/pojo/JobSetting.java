package com.zkzn.les.basicInfo.pojo;

import com.zkzn.les.basicInfo.util.PageCondition;

/**.
 * 
 *
 * 功能描述：任务调度实体
 * @author wangzhou
 * 时间：2018年7月9日
 */
public class JobSetting extends PageCondition{

	/**.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;//主键id
	
	private String jobName;//任务名称
	
	private String jobCode;//任务编号
	
	private String jobClassName;//任务接口类
	
	private Integer jobGroup;//任务调用方式：0-循环间隔 1-定时
	
	private String intervalTime;//任务间隔时间
	
	private String cron;//任务定时时间
	
	private String jobDesc;//任务描述
	
	private Integer status;//任务状态 0-禁用 1-启用

	private Integer type;

	private String type1;

	private String url;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getJobClassName() {
		return jobClassName;
	}

	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}

	public Integer getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(Integer jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getIntervalTime() {
		return intervalTime;
	}

	public void setIntervalTime(String intervalTime) {
		this.intervalTime = intervalTime;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("JobSetting:[")
		.append("id:"+getId())
		.append(",jobName:"+getJobName())
		.append(",jobCode:"+getJobCode())
		.append(",jobGroup:"+getJobGroup())
		.append(",intervalTime:"+getIntervalTime())
		.append(",cron:"+getCron())
		.append(",jobDesc:"+getJobDesc())
		.append(",status:"+getStatus())
		.append(",type:"+getType())
		.append(",type1:"+getType1())
		.append(",url:"+getUrl())
		.append("]");
		
		return buffer.toString();
	}

	
}

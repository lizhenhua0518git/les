package com.zkzn.les.common.pojo;

import com.zkzn.les.common.util.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**.
 * 
 *
 * 功能描述：任务调度实体
 * @author wangzhou
 * 时间：2018年7月9日
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
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
}

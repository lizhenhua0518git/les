package com.zkzn.les.common.pojo;

import com.zkzn.les.common.util.PageCondition;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**.
 * 
 * @author wangzhou
 * @date 2020年8月4日
 * @Description 外部系统调用接口日志
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OutsideApiLog extends PageCondition{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2185297953487734366L;
	@ApiModelProperty(name="requestUrl", value="请求路径")
	private String requestUrl;//请求路径
	@ApiModelProperty(name="requestParam", value="请求参数")
	private String requestParam;//请求参数
	@ApiModelProperty(name="requestParamByte", value="请求参数字节信息")
	private byte [] requestParamByte;//请求参数字节信息
	@ApiModelProperty(name="takeTime", value="接口时长")
	private Long takeTime;//接口时长
	@ApiModelProperty(name="souceOfSystem", value="调用系统")
	private String souceOfSystem;//调用系统
	@ApiModelProperty(name="resultInfo", value="返回信息")
	private String resultInfo;//返回信息
	@ApiModelProperty(name="resultInfoByte", value="返回字节信息")
	private byte [] resultInfoByte;//返回字节信息
	@ApiModelProperty(name="createTimeStart", value="创建开始时间 --用于时间区间查询使用")
	private String createTimeStart;//创建开始时间 --用于时间区间查询使用
	@ApiModelProperty(name="createTimeEnd", value="创建结束时间 --用于时间区间查询使用")
	private String createTimeEnd;//创建结束时间 --用于时间区间查询使用

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getRequestParam() {
		return requestParam;
	}

	public void setRequestParam(String requestParam) {
		this.requestParam = requestParam;
	}

	public Long getTakeTime() {
		return takeTime;
	}

	public void setTakeTime(Long takeTime) {
		this.takeTime = takeTime;
	}

	

	public String getSouceOfSystem() {
		return souceOfSystem;
	}

	public void setSouceOfSystem(String souceOfSystem) {
		this.souceOfSystem = souceOfSystem;
	}

	public String getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
	}
	
	
	
	public byte[] getRequestParamByte() {
		return requestParamByte;
	}

	public void setRequestParamByte(byte[] requestParamByte) {
		this.requestParamByte = requestParamByte;
	}

	public byte[] getResultInfoByte() {
		return resultInfoByte;
	}

	public void setResultInfoByte(byte[] resultInfoByte) {
		this.resultInfoByte = resultInfoByte;
	}

	public String getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

}

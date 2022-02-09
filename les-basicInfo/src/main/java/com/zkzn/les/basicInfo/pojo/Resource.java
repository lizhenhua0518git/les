package com.zkzn.les.basicInfo.pojo;

import com.zkzn.les.basicInfo.util.PageCondition;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class Resource extends PageCondition{

	/**
	 * 创建人: wangzhou
	 * 时间:20202020年4月5日下午7:19:00
	 * 功能描述:
	 */
	private static final long serialVersionUID = -2404819768481378599L;
	
	
	private String id;//主键id
	private String resourceName;//资源名称
	private String resourceDesc;//资源描述
	private String resourceType;//资源类型
	private String resourceParentId;//上级资源id
	private String resourceUrl;//资源url
	private String resourceIcon;//资源图标
	private String resourceAttach;//P-桌面端  M-手持端
	private String resourceStatus;//资源状态 0-禁用 1-开启
	private String resourceCode;//按钮的code
	private Integer resourceOrderNo;//资源顺序
	private String resourceNum;//资源编码
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getResourceDesc() {
		return resourceDesc;
	}
	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public String getResourceParentId() {
		return resourceParentId;
	}
	public void setResourceParentId(String resourceParentId) {
		this.resourceParentId = resourceParentId;
	}
	public String getResourceUrl() {
		return resourceUrl;
	}
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	public String getResourceIcon() {
		return resourceIcon;
	}
	public void setResourceIcon(String resourceIcon) {
		this.resourceIcon = resourceIcon;
	}
	public String getResourceAttach() {
		return resourceAttach;
	}
	public void setResourceAttach(String resourceAttach) {
		this.resourceAttach = resourceAttach;
	}
	
	
	public String getResourceStatus() {
		return resourceStatus;
	}
	public void setResourceStatus(String resourceStatus) {
		this.resourceStatus = resourceStatus;
	}
	
	
	public String getResourceCode() {
		return resourceCode;
	}
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	
	public Integer getResourceOrderNo() {
		return resourceOrderNo;
	}
	public void setResourceOrderNo(Integer resourceOrderNo) {
		this.resourceOrderNo = resourceOrderNo;
	}
	
	public String getResourceNum() {
		return resourceNum;
	}
	public void setResourceNum(String resourceNum) {
		this.resourceNum = resourceNum;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		
		return ReflectionToStringBuilder.toString(this);
	}

}

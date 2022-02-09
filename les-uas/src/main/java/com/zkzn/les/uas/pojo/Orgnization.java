package com.zkzn.les.uas.pojo;


import com.zkzn.les.uas.util.PageCondition;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;



public class Orgnization extends PageCondition  {

	/**
	 *
	 */
	private static final long serialVersionUID = 1976259277169614992L;

	private String id;//主键id
	
	private String orgCode;//组织编码
	
	private String orgName;//组织名称
	
	private String orgType;//分类：0-公司总部  1-分公司 2-部门 3-工厂 4-仓库
	
	private String parentId;//上级组织id
	
	private Integer status;//状态：0-禁用 1-启用

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		
		
		return ReflectionToStringBuilder.toString(this);
	}

}

package com.zkzn.les.uas.pojo;

import com.zkzn.les.uas.util.PageCondition;

/**.
 *  角色实体
 * @author wangzhou
 *
 */
public class Role extends PageCondition{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8415530531030658322L;
	
	
	private String id;//主键id
	private String roleName;//角色名称
	private String roleDesc;//角色描述
	private String roleCode;//角色代码
	private Integer status;//状态 0禁用 1启用
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}

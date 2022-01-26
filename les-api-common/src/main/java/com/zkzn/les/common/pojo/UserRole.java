package com.zkzn.les.common.pojo;

import com.zkzn.les.common.util.PageCondition;

public class UserRole extends PageCondition {

	/**.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;//主键id
	
	private String userId;//用户的id
	
	private String roleId;//角色的id

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

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("id:").append(getId()).append(",userId:").append(getUserId()).append(",roleId:").append(getRoleId());
		return buffer.toString();
	}

	
}

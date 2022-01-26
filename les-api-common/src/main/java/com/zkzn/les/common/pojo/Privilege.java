package com.zkzn.les.common.pojo;

import com.zkzn.les.common.util.PageCondition;

public class Privilege extends PageCondition {

	/**.
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;//主键id
	
	private String roleId;//角色id
	
	private String resId;//资源id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}
	
	@Override
	public String toString() {

		StringBuffer buffer = new StringBuffer();
		
		buffer.append("id:").append(getId()).append(",roleId:").append(getRoleId()).append(",resId:").append(getResId());
		
		return buffer.toString();
	}
	
	
}

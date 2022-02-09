package com.zkzn.les.basicInfo.pojo;

import com.zkzn.les.basicInfo.util.PageCondition;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

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
	
	@ApiModelProperty(name="id", value="主键")
	private String id; 
	
	@ApiModelProperty(name="roleName", value="角色名称")
	private String roleName; 
	
	@ApiModelProperty(name="roleDesc", value="角色描述")
	private String roleDesc; 
	
	@ApiModelProperty(name="roleCode", value="角色代码")
	private String roleCode; 
	
	
	@ApiModelProperty(name="status", value="状态 0禁用 1启用")
	private Integer status;
	
	@ApiModelProperty(name="userId", value="用户id")
	private String userId;
	
	@ApiModelProperty(name="statusName", value="状态名称")
	private String statusName;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
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
	/**
	 * 获取 statusName
	 * @return 返回 statusName
	 */
	public String getStatusName() {
		return statusName;
	}
	/**
	 * 设置 statusName
	 * @param statusName 对statusName进行赋值
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ReflectionToStringBuilder.toString(this);
	}

}

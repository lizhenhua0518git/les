package com.zkzn.les.common.pojo;

import com.zkzn.les.common.util.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class User  extends PageCondition {

	/**      */
	private static final long serialVersionUID = 1L;

	private String id;//用户Id
	private String userName;//用户名
	private String userPassword;//密码
	private String oldPassWord;//旧密码
	private String phone; //手机号码
	private String orgId;//所属组织ID
	private String orgCode;//所属组织编号
	private String orgName;//所属组织名称
	private String avatar;//用户头像
	private Integer status;//用户状态 0-禁用 1-启用
	private String receiveStorageLocation;//接收哪些仓库的物料(车间交接人使用)
	private String loginAccount;//用户名(登陆账号)

	public String getPhone() {
		return phone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getOldPassWord() {
		return oldPassWord;
	}
	public void setOldPassWord(String oldPassWord) {
		this.oldPassWord = oldPassWord;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
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
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return id;
	}
	public void setUserId(String id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}



	public String getLoginAccount() {
		return loginAccount;
	}
	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}
	public String getReceiveStorageLocation() {
		return receiveStorageLocation;
	}
	public void setReceiveStorageLocation(String receiveStorageLocation) {
		this.receiveStorageLocation = receiveStorageLocation;
	}

}
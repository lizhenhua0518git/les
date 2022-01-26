package com.zkzn.les.common.pojo;

import com.zkzn.les.common.util.PageCondition;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * .
 *
 * 功能描述:仓库实体类
 * 
 * 时间:  2020-07-31 10:00
 *
 * @author  刘松山  
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Warehouse extends PageCondition{

    /**      */
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(name="id", value="主键")
    private String id;
    @ApiModelProperty(name="orgCode", value="仓库编码")
    private String orgCode; 
    @ApiModelProperty(name="orgName", value="仓库名称")
    private String orgName; 
    @ApiModelProperty(name="orgAddress", value="仓库地址")
    private String orgAddress; 
    @ApiModelProperty(name="orgType", value="仓库类型")
    private Integer orgType; 
    @ApiModelProperty(name="status", value="状态：0-禁用 1-启用")
    private Integer status; 
    @ApiModelProperty(name="contacts", value="联系人")
    private String contacts; 
 
    @ApiModelProperty(name="phone", value="联系电话")         
    private String phone; 
    
    @ApiModelProperty(name="supplierCode", value="供货商编码")
    private String supplierCode;
	@ApiModelProperty(name="supplierName", value="供货商名称")
    private String supplierName;
 
    /**************************业务需要字段*********************/
    @ApiModelProperty(name="permission", value="仓库权限 ")
    private Integer permission; 
    
    @ApiModelProperty(name="statusName", value="状态名称 启用和禁用")
    private String statusName; 
    
    @ApiModelProperty(name="orgTypeName", value="仓库类型名称  枚举")
    private String orgTypeName; 
    
    @ApiModelProperty(name="remark", value="备注")
    private  String  remark;

    /**
	 * 获取 id
	 * @return 返回 id
	 */
	public String getId() {
		return id;
	}




	/**
	 * 获取 orgCode
	 * @return 返回 orgCode
	 */
	public String getOrgCode() {
		return orgCode;
	}




	/**
	 * 获取 orgName
	 * @return 返回 orgName
	 */
	public String getOrgName() {
		return orgName;
	}




	/**
	 * 获取 orgAddress
	 * @return 返回 orgAddress
	 */
	public String getOrgAddress() {
		return orgAddress;
	}




	/**
	 * 获取 orgType
	 * @return 返回 orgType
	 */
	public Integer getOrgType() {
		return orgType;
	}




	/**
	 * 获取 status
	 * @return 返回 status
	 */
	public Integer getStatus() {
		return status;
	}




	/**
	 * 获取 contacts
	 * @return 返回 contacts
	 */
	public String getContacts() {
		return contacts;
	}




	 
	/**
	 * 获取 phone
	 * @return 返回 phone
	 */
	public String getPhone() {
		return phone;
	}




	/**
	 * 设置 id
	 * @param id 对id进行赋值
	 */
	public void setId(String id) {
		this.id = id;
	}




	/**
	 * 设置 orgCode
	 * @param orgCode 对orgCode进行赋值
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}




	/**
	 * 设置 orgName
	 * @param orgName 对orgName进行赋值
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}




	/**
	 * 设置 orgAddress
	 * @param orgAddress 对orgAddress进行赋值
	 */
	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}




	/**
	 * 设置 orgType
	 * @param orgType 对orgType进行赋值
	 */
	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}




	/**
	 * 设置 status
	 * @param status 对status进行赋值
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}




	/**
	 * 设置 contacts
	 * @param contacts 对contacts进行赋值
	 */
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}




	 

	/**
	 * 设置 phone
	 * @param phone 对phone进行赋值
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}




	/**
	 * 获取 permission
	 * @return 返回 permission
	 */
	public Integer getPermission() {
		return permission;
	}




	/**
	 * 设置 permission
	 * @param permission 对permission进行赋值
	 */
	public void setPermission(Integer permission) {
		this.permission = permission;
	}




	/**
	 * 获取 statusName
	 * @return 返回 statusName
	 */
	public String getStatusName() {
		return statusName;
	}




	/**
	 * 获取 orgTypeName
	 * @return 返回 orgTypeName
	 */
	public String getOrgTypeName() {
		return orgTypeName;
	}




	/**
	 * 设置 statusName
	 * @param statusName 对statusName进行赋值
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}




	/**
	 * 设置 orgTypeName
	 * @param orgTypeName 对orgTypeName进行赋值
	 */
	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
	}




	/**
	 * 获取 supplierCode
	 * @return 返回 supplierCode
	 */
	public String getSupplierCode() {
		return supplierCode;
	}




	/**
	 * 获取 supplierName
	 * @return 返回 supplierName
	 */
	public String getSupplierName() {
		return supplierName;
	}




	/**
	 * 设置 supplierCode
	 * @param supplierCode 对supplierCode进行赋值
	 */
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}




	/**
	 * 设置 supplierName
	 * @param supplierName 对supplierName进行赋值
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}




	/**
	 * 获取 remark
	 * @return 返回 remark
	 */
	public String getRemark() {
		return remark;
	}




	/**
	 * 设置 remark
	 * @param remark 对remark进行赋值
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
}

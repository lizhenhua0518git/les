package com.zkzn.les.basicInfo.pojo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import io.swagger.annotations.ApiModelProperty;

public class Customer implements Serializable {

	@ApiModelProperty(name = "id", value = "主键")
	private String id;

	@ApiModelProperty(name = "customerCode", value = "客户编号")
	private String customerCode;

	@ApiModelProperty(name = "customerName", value = "客户名称")
	private String customerName;

	@ApiModelProperty(name = "customerAddress", value = "客户联系地址")
	private String customerAddress;

	@ApiModelProperty(name = "isDel", value = "删除标记 0-删除 1 未删除")
	private Short isDel;

	@ApiModelProperty(name = "createTime", value = "创建时间")
	private Date createTime;

	@ApiModelProperty(name = "province", value = "省份编码")
	private String province;

	@ApiModelProperty(name = "phone", value = "客户电话")
	private String phone;

	@ApiModelProperty(name = "contacts", value = "联系人")
	private String contacts;

	@ApiModelProperty(name = "city", value = "城市")
	private String city;

	@ApiModelProperty(name = "statusName", value = "删除标记名称")
	private String statusName;

	private static final long serialVersionUID = 1L;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public Short getIsDel() {
		return isDel;
	}

	public void setIsDel(Short isDel) {
		this.isDel = isDel;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 获取 statusName
	 * 
	 * @return 返回 statusName
	 */
	public String getStatusName() {
		return statusName;
	}

	/**
	 * 设置 statusName
	 * 
	 * @param statusName
	 *            对statusName进行赋值
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
package com.zkzn.les.common.pojo;

import java.util.Date;

import com.zkzn.les.common.util.PageCondition;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
/**
 * .
 *
 * 功能描述:供货商信息实体类
 * 
 * 时间:  2020-07-07 13:38
 *
 * @author  刘松山  
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Supplier extends PageCondition {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(name="id", value="主键")
	private String id;
 
	@ApiModelProperty(name="supplierCode", value="供货商编码")
    private String supplierCode;
	@ApiModelProperty(name="supplierName", value="供货商名称")
    private String supplierName;
  
	@ApiModelProperty(name="supplierAddress", value="供货商地址")
    private String supplierAddress;
 
	@ApiModelProperty(name="isDel", value="删除标记")
    private int isDel;
 
	@ApiModelProperty(name="createTime", value="创建时间")
    private Date createTime;
 
	@ApiModelProperty(name="province", value="省份")
    private String province;
 
	@ApiModelProperty(name="city", value="城市")
    private String city;
 
	@ApiModelProperty(name="phone", value="联系方式")
    private String phone;
    
	@ApiModelProperty(name="statusName", value="删除标记名称")
    private String statusName;
     
	/**
	 * 获取 city
	 * @return 返回 city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 设置 city
	 * @param city 对city进行赋值
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 获取 id
	 * @return 返回 id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 id
	 * @param id 对id进行赋值
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 supplierCode
	 * @return 返回 supplierCode
	 */
	public String getSupplierCode() {
		return supplierCode;
	}

	/**
	 * 设置 supplierCode
	 * @param supplierCode 对supplierCode进行赋值
	 */
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	/**
	 * 获取 supplierName
	 * @return 返回 supplierName
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * 设置 supplierName
	 * @param supplierName 对supplierName进行赋值
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * 获取 supplierAddress
	 * @return 返回 supplierAddress
	 */
	public String getSupplierAddress() {
		return supplierAddress;
	}

	/**
	 * 设置 supplierAddress
	 * @param supplierAddress 对supplierAddress进行赋值
	 */
	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	/**
	 * 获取 isDel
	 * @return 返回 isDel
	 */
	public int getIsDel() {
		return isDel;
	}

	/**
	 * 设置 isDel
	 * @param isDel 对isDel进行赋值
	 */
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	/**
	 * 获取 createTime
	 * @return 返回 createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置 createTime
	 * @param createTime 对createTime进行赋值
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取 province
	 * @return 返回 province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * 设置 province
	 * @param province 对province进行赋值
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * 获取 phone
	 * @return 返回 phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置 phone
	 * @param phone 对phone进行赋值
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
}
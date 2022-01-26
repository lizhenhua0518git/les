package com.zkzn.les.basicInfo.pojo;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import com.zkzn.les.basicInfo.util.PageCondition;

import io.swagger.annotations.ApiModelProperty;

public class CostCenter extends PageCondition {
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(name="id", value="主键")
	private String id;
    
	// 成本中心编号
    @ApiModelProperty(name="costCenterCode", value="成本中心编号")
	private String costCenterCode;
    
	 
    @ApiModelProperty(name="costCenterDesc", value="成本中心描述")
	private String costCenterDesc;
    
 
    @ApiModelProperty(name="companyCode", value="公司代码")
	private String companyCode;
    
  
    @ApiModelProperty(name="costCenterType", value="成本中心类型 E-开发 F-生产 G-后勤 L-管理 V-销售")
	private String costCenterType;
    
    @ApiModelProperty(name="costCenterTypeName", value="成本中心类型名称 E-开发 F-生产 G-后勤 L-管理 V-销售")
    private String costCenterTypeName;
    
 
    @ApiModelProperty(name="personLiable", value="负责人")
	private String personLiable;
    
	// 
    @ApiModelProperty(name="startDate", value="开始生效日期")
	private String startDate;
    
	// 
    @ApiModelProperty(name="endDate", value="有效截至日期")
	private String endDate;

    @ApiModelProperty(name="createTime", value="创建时间")
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCostCenterCode() {
		return costCenterCode;
	}

	public void setCostCenterCode(String costCenterCode) {
		this.costCenterCode = costCenterCode;
	}

	public String getCostCenterDesc() {
		return costCenterDesc;
	}

	public void setCostCenterDesc(String costCenterDesc) {
		this.costCenterDesc = costCenterDesc;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCostCenterType() {
		return costCenterType;
	}

	public void setCostCenterType(String costCenterType) {
		this.costCenterType = costCenterType;
	}

	public String getPersonLiable() {
		return personLiable;
	}

	public void setPersonLiable(String personLiable) {
		this.personLiable = personLiable;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 获取 costCenterTypeName
	 * @return 返回 costCenterTypeName
	 */
	public String getCostCenterTypeName() {
		return costCenterTypeName;
	}

	/**
	 * 设置 costCenterTypeName
	 * @param costCenterTypeName 对costCenterTypeName进行赋值
	 */
	public void setCostCenterTypeName(String costCenterTypeName) {
		this.costCenterTypeName = costCenterTypeName;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
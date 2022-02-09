package com.zkzn.les.common.pojo;

import java.util.Date;

import com.zkzn.les.common.util.PageCondition;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
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

}
/*
 * 文 件 名:  WorkArea.java
 * 版    权:  
 * 描    述:  描述:
 * 修 改 人:  刘松山 
 * 修改时间:  2020年5月13日
 * 跟踪单号:  跟踪单号:
 * 修改单号:  修改单号:
 * 修改内容:  修改内容:
 */
package com.zkzn.les.common.pojo;

import com.zkzn.les.common.util.PageCondition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**.
 *
 * 功能描述:作业区域实体类
 * 
 * 时间:  2020-05-13 11:45
 *
 * @author  刘松山  
 * 
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WorkArea extends PageCondition {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6215126514138934600L;
	private String id;//主键id
	private String workAreaCode;//作业区域编号
	private String workAreaName;//作业区域名称
	private String areaType;//区域类型
	 
	private String warehouseCode;//所属仓库编码
	private String warehouseName;//所属仓库名称
	private Integer status;//状态：0-禁用 1-启用
	private Integer useStatus;//使用状态 0-空闲 1-占用
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWorkAreaCode() {
		return workAreaCode;
	}
	public void setWorkAreaCode(String workAreaCode) {
		this.workAreaCode = workAreaCode;
	}
	public String getWorkAreaName() {
		return workAreaName;
	}
	public void setWorkAreaName(String workAreaName) {
		this.workAreaName = workAreaName;
	}
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getUseStatus() {
		return useStatus;
	}
	public void setUseStatus(Integer useStatus) {
		this.useStatus = useStatus;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	
}

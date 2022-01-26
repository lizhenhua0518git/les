package com.zkzn.les.basicInfo.pojo;

import java.util.List;

import com.zkzn.les.basicInfo.util.PageCondition;

import net.logstash.logback.encoder.org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**.
 * 
 * @author wangzhou
 *	下架规则配置 实体
 */
public class LowerRule extends PageCondition {

	/**
	 * 创建人: wangzhou
	 * 时间:20202020年5月22日上午11:44:18
	 * 功能描述:
	 */
	private static final long serialVersionUID = 5192161845422292316L;
	
	private String id;
	
	private String warehouseCode;//仓库编码
	
	private String warehouseName;//仓库名称
	
	private Integer lowerRule;//下架规则 0-任务数优先，1-仓位库存优先
	
	private Integer status;//启用状态 0-禁用，1-启用
	
	private List<String> warehouseCodes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Integer getLowerRule() {
		return lowerRule;
	}

	public void setLowerRule(Integer lowerRule) {
		this.lowerRule = lowerRule;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
	public List<String> getWarehouseCodes() {
		return warehouseCodes;
	}

	public void setWarehouseCodes(List<String> warehouseCodes) {
		this.warehouseCodes = warehouseCodes;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ReflectionToStringBuilder.toString(this);
	}
	

}

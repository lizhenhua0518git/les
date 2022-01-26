package com.zkzn.les.common.pojo.panel;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**.
 * 
 *
 * 功能描述：区域信息实体类
 * @author wangzhou
 * 时间：2018年7月2日
 */
@Data
public class Area  implements Serializable {

	/**.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;//主键id
	private String areaCode;//区域编号
	private String areaName;//区域名称
	private String areaType;//区域类型
	private String parentId;//父节点id
	private String warehouse;//所属仓库
	private Integer status;//状态：0-禁用 1-启用
	private Integer useStatus;//使用状态 0-空闲 1-占用
	private List<String>areaTypeList;
	
	private Integer storageType;//是否地堆物料 0是地堆 1非地堆
	private String arrivalDetailId;//到货通知单明细id
	
	private String stationCode;//工位编码（多个工位，使用分号分隔开）
	
	
	
	public Integer getStorageType() {
		return storageType;
	}
	public void setStorageType(Integer storageType) {
		this.storageType = storageType;
	}
	public String getArrivalDetailId() {
		return arrivalDetailId;
	}
	public void setArrivalDetailId(String arrivalDetailId) {
		this.arrivalDetailId = arrivalDetailId;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public List<String> getAreaTypeList() {
		return areaTypeList;
	}
	public void setAreaTypeList(List<String> areaTypeList) {
		this.areaTypeList = areaTypeList;
	}
	public Integer getUseStatus() {
		return useStatus;
	}
	public void setUseStatus(Integer useStatus) {
		this.useStatus = useStatus;
	}
	
	
	public String getStationCode() {
		return stationCode;
	}
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}


}

package com.zkzn.les.common.pojo;

import com.zkzn.les.common.util.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;


/**.
 * 
 * 功能描述：区域信息实体类
 * @author wangzhou
 * 时间：2018年7月2日
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Area extends PageCondition{

	private static final long serialVersionUID = 1L;
	
	private String id;//主键id
	private String areaCode;//区域编号
	private String areaName;//区域名称
	private String areaType;//区域类型
	private String areaTypeName;//区域类型对应文字描述
	private String parentId;//父节点id
	private String warehouse;//所属仓库编码
	private String warehouseName;//所属仓库名称
	private Integer status;//状态：0-禁用 1-启用
	private Integer useStatus;//使用状态 0-空闲 1-占用
	
	private String statusStr;//状态：0-禁用 1-启用--导出使用
	private String useStatusStr;//使用状态 0-空闲 1-占用--导出使用
	
	private List<String> areaTypeList;
	
	private List<Area> children; //设置子区域
	
	private int areaLevel;  // 区域等级
	
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
}

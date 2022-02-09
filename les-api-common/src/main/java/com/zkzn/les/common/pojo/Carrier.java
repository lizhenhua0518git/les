package com.zkzn.les.common.pojo;

import com.zkzn.les.common.util.PageCondition;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * 
 * @author wangzhou
 * 载具管理
 */

@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Carrier extends PageCondition {

	/**
	 * 创建人: wangzhou
	 * 时间:20202020年3月31日上午10:39:02
	 * 功能描述:
	 */
	private static final long serialVersionUID = 8859057786180657550L;
	@ApiModelProperty(name="id", value="主键")
	private String id;
	
	@ApiModelProperty(name="isOneLevel", value="是否是一级载具0-不是 1-是")
	private Integer isOneLevel; 
	
	@ApiModelProperty(name="parentIdList", value="父节点列表")
	private List<String> parentIdList;
	
	@ApiModelProperty(name="carrierName", value="载具名称")
	private String carrierName; 
	
	@ApiModelProperty(name="carrierCode", value="载具编号")
	private String carrierCode; 
	
	@ApiModelProperty(name="carrierType", value="载具分类")
	private String carrierType;
	
	@ApiModelProperty(name="carrierTypeName", value="载具分类名称")
	private String carrierTypeName;//
	
	@ApiModelProperty(name="parentId", value="上级组织id")
	private String parentId; 
	
	@ApiModelProperty(name="parentName", value="父级载具名称")
	private String parentName; 
	
	@ApiModelProperty(name="status", value="是否有效;0-禁用 1-启用")
	private Integer status; 
	
	@ApiModelProperty(name="useStatus", value="载具状态;0-空闲 1-占用 ")
	private Integer useStatus; 
	
	@ApiModelProperty(name="isLeaf", value="是否为叶子节点:0-不是 1-是")
	private Integer isLeaf; 
	
	@ApiModelProperty(name="warehouseCode", value="所属仓库")
	private String warehouseCode; 
	
	@ApiModelProperty(name="warehouseName", value="所属仓库名称")
	private String warehouseName; 

	@ApiModelProperty(name="isDel", value="是否报废 0 不是 1 是")
	private Integer isDel; 
	
	@ApiModelProperty(name="startTime", value="创建时间 开始")
	private String startTime; 
	
	@ApiModelProperty(name="endTime", value="创建时间 结束")
	private String endTime; 
 
	@ApiModelProperty(name="carrierNum", value="载具数量")
	private Integer  carrierNum; 
	
	@ApiModelProperty(name="warehouseCodeList", value="所属仓库list")
	private List<String> warehouseCodeList; 
	
	@ApiModelProperty(name="statusName", value="状态名称 禁用 启用")
	private String statusName;
	
	@ApiModelProperty(name="useStatusName", value="载具状态名称; 空闲  占用 ")
	private String useStatusName;

}

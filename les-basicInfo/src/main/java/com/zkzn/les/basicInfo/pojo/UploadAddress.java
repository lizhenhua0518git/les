package com.zkzn.les.basicInfo.pojo;

import java.util.List;

import com.zkzn.les.basicInfo.util.PageCondition;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**.
 * 
 * @author wangzhou
 * @date 2020年8月22日
 * @Description：卸货地点实体类
 */
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class UploadAddress extends PageCondition{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1578260696903723981L;
	
	@ApiModelProperty(name="主键id",value="id")
	private String id;
	@ApiModelProperty(name="卸货地点编码",value="uploadCode")
	private String uploadCode;
	@ApiModelProperty(name="卸货地点名称",value="uploadName")
	private String uploadName;
	@ApiModelProperty(name="使用状态 0-空闲 1-占用",value="useStatus")
	private Integer useStatus;
	@ApiModelProperty(name="仓库编码",value="warehouseCode")
	private String warehouseCode;
	@ApiModelProperty(name="仓库名称",value="warehouseName")
	private String warehouseName;
	@ApiModelProperty(name="仓库编码 用于查询",value="warehouseCodes")
	private List<String> warehouseCodes;

}

package com.zkzn.les.basicInfo.pojo;

import com.zkzn.les.basicInfo.util.PageCondition;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class SapProcessOrderDetail extends PageCondition {
	@ApiModelProperty(name="id", value="主键")
    private String id;

	@ApiModelProperty(name="reserveCode", value="预留号")
    private String reserveCode;

	@ApiModelProperty(name="reserveRow", value="预留行")
    private String reserveRow;

	@ApiModelProperty(name="materialRow", value="组件行项号")
    private String materialRow;

	@ApiModelProperty(name="materialCode", value="组件物料号")
    private String materialCode;

	@ApiModelProperty(name="requiredNum", value="需求数量")
    private Double requiredNum;

	@ApiModelProperty(name="itemUnit", value="条目单位")
    private String itemUnit;

	@ApiModelProperty(name="virtualMaterialTab", value="虚拟物料标识")
    private String virtualMaterialTab;

	@ApiModelProperty(name="recoilMaterialTab", value="反冲物料标识")
    private String recoilMaterialTab;

	@ApiModelProperty(name="bulkMaterialTab", value="散装物料标识")
    private String bulkMaterialTab;

	@ApiModelProperty(name="upperMaterialTab", value="上层物料编码")
    private String upperMaterialTab;

	@ApiModelProperty(name="status", value="状态 0-下达，1-锁定，2-已生成集配单，9-删除")
    private int status;
    
	@ApiModelProperty(name="statusName", value="状态名称 0-下达，1-锁定，2-已生成集配单，9-删除")
    private String statusName;

	@ApiModelProperty(name="storageLocation", value="库存地点")
    private String storageLocation;

	@ApiModelProperty(name="batchNo", value="批次号")
    private String batchNo;

	@ApiModelProperty(name="doneActiveNo", value="操作活动编号")
    private String doneActiveNo;

	@ApiModelProperty(name="stationCode", value="工位码")
    private String stationCode;

	@ApiModelProperty(name="stationDesc", value="工位描述")
    private String stationDesc;

	@ApiModelProperty(name="orderCode", value="订单号")
    private String orderCode;

	@ApiModelProperty(name="factory", value="工厂")
    private String factory;

	@ApiModelProperty(name="sequenceNo", value="顺序")
    private String sequenceNo;

    private static final long serialVersionUID = 1L;

  
}
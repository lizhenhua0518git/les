package com.zkzn.les.oms.pojo;

import java.util.Date;

import com.zkzn.les.common.util.page.PageCondition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**.
 *
 * @author wangzhou
 * @date 2020年9月3日
 * @Description 将生产订单 按照工位合并的工位订单实体
 */
@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class StationOrder extends PageCondition {

	/**
	 *
	 */
	private static final long serialVersionUID = -4742209465953069668L;

	@ApiModelProperty(name="id", value="主键id")
	private String id;
	@ApiModelProperty(name="workshopCode", value="车间")
	private String workshopCode;
	@ApiModelProperty(name="lineCode", value="产线")
	private String lineCode;
	@ApiModelProperty(name="stationCode", value="工位号")
	private String stationCode;
	@ApiModelProperty(name="warehouseCode", value="仓库编码")
	private String warehouseCode;
	@ApiModelProperty(name="warehouseName", value="仓库名称")
	private String warehouseName;
	@ApiModelProperty(name="requiredDate", value="需求日期")
	private Date requiredDate;
	@ApiModelProperty(name="orderCode", value="订单号")
	private String orderCode;
	@ApiModelProperty(name="priority", value="订单优先级")
	private Integer priority;
	@ApiModelProperty(name="vinCode", value="vin号")
	private String vinCode;
	@ApiModelProperty(name="factory", value="工厂")
	private String factory;
	@ApiModelProperty(name="createTime", value="创建时间")
	private Date createTime;
	@ApiModelProperty(name="orderType", value="订单类型0-生产出库 1-调拨出库")
	private Integer orderType;
	@ApiModelProperty(name="waveId", value="波次表id")
	private String waveId;
	@ApiModelProperty(name="processOrderId", value="生产订单表id")
	private String processOrderId;
	@ApiModelProperty(name="carModel", value="车型号")
	private String carModel;
	@ApiModelProperty(name="status", value="状态")
	private Integer status;
	@ApiModelProperty(name="stationDesc", value="工位描述")
	private String stationDesc;
	@ApiModelProperty(name="busiType", value="业务类型")
	private Integer busiType;
	@ApiModelProperty(name="costCenterOrder", value="任务单号")
	private String costCenterOrder;
}

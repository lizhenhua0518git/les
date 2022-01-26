package com.zkzn.les.oms.pojo;

import java.util.Date;
import java.util.List;

import com.zkzn.les.common.util.page.PageCondition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
/**
 *
 * @author wangzhou
 * @date 2020年8月28日
 * @Description mes锁定订单后 拆分订单详情实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProcessOrderDetail extends PageCondition {

	/**
	 * 创建人: wangzhou
	 * 时间:20202020年4月6日下午4:35:38
	 * 功能描述:
	 */
	private static final long serialVersionUID = -5136570247216516451L;

	@ApiModelProperty(name="id", value="主键id")
	private String id;
	@ApiModelProperty(name="reserveCode", value="预留号")
	private String reserveCode;
	@ApiModelProperty(name="reserveRow", value="预留行项目")
	private String reserveRow;
	@ApiModelProperty(name="materialRow", value="组件行项目")
	private String materialRow;
	@ApiModelProperty(name="materialCode", value="组件物料号")
	private String materialCode;
	@ApiModelProperty(name="requiredNum", value="需求数量")
	private Double requiredNum;
	@ApiModelProperty(name="itemUnit", value="单位")
	private String itemUnit;
	@ApiModelProperty(name="recoilMaterialTab", value="反冲物料标识")
	private String recoilMaterialTab;
	@ApiModelProperty(name="bulkMaterialTab", value="散装物料标识")
	private String bulkMaterialTab;
	@ApiModelProperty(name="specialCode", value="特殊库存标识")
	private String specialCode;
	@ApiModelProperty(name="storageLocation", value="库存地点")
	private String storageLocation;
	@ApiModelProperty(name="doneActiveNo", value="操作活动编号")
	private String doneActiveNo;
	@ApiModelProperty(name="stationCode", value="配送工位码")
	private String stationCode;
	@ApiModelProperty(name="stationDesc", value="工位描述")
	private String stationDesc;
	@ApiModelProperty(name="processOrderId", value="锁定订单表id")
	private String processOrderId;
	@ApiModelProperty(name="orderId", value="生产订单表id")
	private String orderId;
	@ApiModelProperty(name="orderDetailId", value="生产订单详情表id")
	private String orderDetailId;
	@ApiModelProperty(name="orderCode", value="订单号")
	private String orderCode;
	@ApiModelProperty(name="factory", value="工厂")
	private String factory;
	@ApiModelProperty(name="status", value="状态")
	private Integer status;
	@ApiModelProperty(name="matchNum", value="分配库存数量")
	private Double matchNum;
	@ApiModelProperty(name="surplusNum", value="剩余分配数量")
	private Double surplusNum;
	@ApiModelProperty(name="storageLocations", value="库存地点集合 列表查询用")
	private List<String> storageLocations;
	@ApiModelProperty(name="storageLocationName", value="库存地点名称")
	private String storageLocationName;
	@ApiModelProperty(name="callTime", value="呼叫时间")
	private Date  callTime;
	@ApiModelProperty(name="callNum", value="呼叫数量")
	private Double  callNum;
    @ApiModelProperty(name = "stationCodes", value = "工位号集合 列表查询用")
    private List<String> stationCodes;
    @ApiModelProperty(name = "specialType",value = "特殊库存标记")
	private String specialType;
}

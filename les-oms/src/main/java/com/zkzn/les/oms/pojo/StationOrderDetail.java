package com.zkzn.les.oms.pojo;

import com.zkzn.les.common.util.page.PageCondition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**.
 *
 * @author wangzhou
 * @date 2020年9月3日
 * @Description 将生产订单 按照工位合并的工位订单详情实体
 */
@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class StationOrderDetail extends PageCondition {/**
	 *
	 */
	private static final long serialVersionUID = -9143382764450084796L;

	@ApiModelProperty(name="id", value="主键id")
	private String id;
	@ApiModelProperty(name="materialCode", value="物料编码")
	private String materialCode;
	@ApiModelProperty(name="materialDesc", value="物料描述")
	private String materialDesc;
	@ApiModelProperty(name="materialUnit", value="单位")
	private String materialUnit;
	@ApiModelProperty(name="reserveCode", value="预留号")
	private String reserveCode;
	@ApiModelProperty(name="reserveRow", value="预留行")
	private String reserveRow;
	@ApiModelProperty(name="pickNum", value="拣配数量")
	private Double pickNum;
	@ApiModelProperty(name="specialType", value="特殊库存标记")
	private String specialType;
	@ApiModelProperty(name="specialCode", value="特殊库存编码")
	private String specialCode;
	@ApiModelProperty(name="specialName", value="特殊库存名称")
	private String specialName;
	@ApiModelProperty(name="stationLocation", value="库存地点")
	private String stationLocation;
	@ApiModelProperty(name="positionCode", value="仓位号")
	private String positionCode;
	@ApiModelProperty(name="storageBinId", value="仓库库存表id")
	private String storageBinId;
	@ApiModelProperty(name="storageId", value="仓位id")
	private String storageId;
	@ApiModelProperty(name="createTime", value="创建时间")
	private Date createTime;
	@ApiModelProperty(name="processDetialId", value="生产订单详情表id")
	private String processDetialId;
	@ApiModelProperty(name="status", value="状态")
	private Integer status;
	@ApiModelProperty(name="stationOrderId", value="工位订单id")
	private String stationOrderId;
	@ApiModelProperty(name="busiType", value="业务类型")
	private Integer busiType;
	@ApiModelProperty(name="materialRow", value="物料行号")
	private String materialRow;
    @ApiModelProperty(name="sellOrder",value = "销售订单")
	private String sellOrder;
    @ApiModelProperty(name="sellOrderItem",value = "销售订单行项目")
    private String sellOrderItem;
}

package com.zkzn.les.oms.pojo;

import com.zkzn.les.common.util.page.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * @author wangzhou
 * @date 2020年9月4日
 * @Description 工位详细表下架数量和仓位库存表关联表
 */
@Data
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class StorageOrderDetail extends PageCondition {

	/**
	 *
	 */
	private static final long serialVersionUID = 2952210019879721347L;

	private String id;
	private String positionCode;
	private String storageBinId;
	private String storageId;
	private String stationDetailId;
	private Double pickNum;


}

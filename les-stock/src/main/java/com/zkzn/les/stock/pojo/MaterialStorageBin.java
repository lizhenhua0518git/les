package com.zkzn.les.stock.pojo;

import com.zkzn.les.common.util.page.PageCondition;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 仓位库存实体
 */
@Data
@ToString(callSuper = true)
public class MaterialStorageBin extends PageCondition {

	private static final long serialVersionUID = 1L;

	private Integer storageBinId;//仓位库存表主键id
	private Integer storagePositionId;//仓位表id
	private String positionCode;//仓位编号
	private String materialDesc;//货物名称
	private String materialUnit;//货物单位
	private Double stockCount;//非限制数量
	private Double frozenCount;//冻结数量
	private Double preUseCount;//占用数量
	private String batchNo;//批次号
	private String warehouseCode;//仓库编号
	private String warehouseName;//仓库名称
	private String clientName;//客户名称
	private Integer stockStatus;//库存状态:0：合格；1：不合格
	private Date createTime;//创建时间

	private String positionCarCode;//仓位载具编号

	private Double numberOfMoves;
}

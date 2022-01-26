package com.zkzn.les.wms.arrivalNotice.pojo;

import com.zkzn.les.common.util.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 送货单详情
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ArrivalNoticeDetailPojo extends PageCondition {

	private static final long serialVersionUID = 1L;
	private Integer arrivalDetailId;//送货单详情ID
	private Integer arrivalNoticeId;//到货通知单主表id
	private Integer arrivalItemNo;//行项目号
	private String materialDesc;//物料名称
	private String materialUnit;//单位
	private Double arrivalCount;//到货数量
	private Double receiveNum;//收货数量
	private Double upperNum;//上架数量

	private Integer receivedStatus;//收货状态(0-待收货 1-部分收货、2-收货完成)
	private Integer upperStatus;//上架状态(0-待上架、1-部分上架、2-上架完成)

	private String receivedName;//点收状态名称
	private String upperName;//上架状态名称

	private String warehouseName;//仓库名称
	private String arrivalCode;//到货通知单号
	private String clientName;//客户名称
	private String billName;//单据类型名称
	private Date createTime;//创建日期

	private Integer createUserId;
	private String createName;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArrivalNoticeDetailPojo other = (ArrivalNoticeDetailPojo) obj;
		if (arrivalDetailId == null) {
			if (other.arrivalDetailId != null)
				return false;
		} else if (!arrivalDetailId.equals(other.arrivalDetailId))
			return false;
		return true;
	}
}

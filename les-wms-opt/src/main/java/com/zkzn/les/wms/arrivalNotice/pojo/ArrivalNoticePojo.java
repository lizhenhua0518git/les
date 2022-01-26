package com.zkzn.les.wms.arrivalNotice.pojo;

import com.zkzn.les.common.util.PageCondition;
import lombok.Data;

import java.sql.Date;

@Data
public class ArrivalNoticePojo extends PageCondition {

	private static final long serialVersionUID = 1L;

    private Integer arrivalNoticeId;//到货通知主表ID
    private String arrivalCode;//到货通知单号
    private String warehouseName;//仓库编码
    private String warehouseCode;//仓库名称
    private Integer billType;//单据类型编码
    private String billName;//单据类型名称
    private String receivedName;//点收状态名称
    private Integer receivedStatus;//点收状态
    private String upperName;//上架状态名称
    private Integer upperStatus;//上架状态
    private String clientName;//客户名称
    private Integer clientManageId;//客户id
    private Integer operateUserId;//操作人
    private String operateUserName;//操作人名称
    private Integer issueStatus;//下发状态
    private Date arrivalTime;//到货时间
    private String responsible;//经办人
    private String carCode;//车牌号
    private String createStr;//创建时间查询条件
    private String startCreate;//开始创建时间
    private String endCreate;//结束创建时间

    private Date createTime;
    private Integer createUserId;
    private String createName;

}

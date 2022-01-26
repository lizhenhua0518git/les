package com.zkzn.les.wms.arrivalNotice.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class AddReceiveDataPojo {

    private static final long serialVersionUID = 1L;

    private Integer arrivalNoticeId;//到货通知单主表id
    private String arrivalCode;//任务编号
    private String warehouseName;//仓库名称
    private String warehouseCode;//仓库编号
    private Integer billType;//订单类型
    private String billName;//订单名称
    private String clientName;//客户名称
    private Integer clientManageId;//客户id
    private Integer operateUserId;//操作人
    private String operateUserName;//操作人名称

    private Integer arrivalDetailId;//到货通知单详情id
    private String materialDesc;//货物名称
    private String materialUnit;//货物单位
    private Double actualNum;//到货数量


}

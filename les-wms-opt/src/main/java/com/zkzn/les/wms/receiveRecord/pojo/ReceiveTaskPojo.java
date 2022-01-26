package com.zkzn.les.wms.receiveRecord.pojo;

import com.zkzn.les.common.util.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.sql.Date;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ReceiveTaskPojo extends PageCondition {

    private static final long serialVersionUID = 1L;

    private Integer receiveTaskId;//点收任务主表id
    private Integer arrivalNoticeId;//到货通知单主表id
    private String arrivalCode;//到货通知单编码
    private String warehouseName;//仓库名称
    private String warehouseCode;//仓库号
    private Integer billType;//单据类型 ：0、送货单入库、1、退货入库、2、调拨入库
    private String billName;//订单名称
    private String clientName;//客户名称
    private Integer clientManageId;//客户id
    private Integer operateUserId;//操作人
    private String operateUserName;//操作人名称
    private Integer receiveStatus;//点收状态 15-待收货  20-点收中 25-部分收货（收货异常） 30-收货完成(待移库) 35-移库完成
    private String receiveTotalTime;//点收总时长
    private Date receiveStartTime;//收货开始时间
    private Date receiveEndTime;//收货结束时间
    private Date createTime;//创建时间
    private Integer createUserId;//创建用户id
    private String createName;//创建用户名称
    private String updateName;//修改用户名称

    private String startTimeBeginStr;//点收开始时间开始
    private String startTimeEndStr;//点收开始时间结束
    private String endTimeBeginStr;//点收结束时间开始
    private String endTimeEndStr;//点收结束时间结束




}

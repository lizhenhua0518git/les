package com.zkzn.les.wms.receiveRecord.pojo;

import com.zkzn.les.common.util.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.sql.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ReceiveTaskDetailPojo extends PageCondition {

    private static final long serialVersionUID = 1L;

    private Integer receiveDetailId;//点收详情表Id
    private Integer receiveTaskId;//点收主表id
    private Integer arrivalDetailId;//到货通知单详情id
    private Integer itemNo;//行项目号
    private String materialDesc;//物料描述
    private String materialUnit;//物料单位
    private Double actualNum;//到货数量
    private Double receiveNum;//点收数量
    private Integer receiveDetailStatus;//点收状态 15-待点收 20-点收中 25-部分点收（收货异常） 30-点收完成(待移库) 35-移库完成
    private String pointerName;//点收人名称
    private Integer pointerId;//点收人id
    private Date receiveTime;//点收时间
    private Integer pointPositionId;//点收区域id
    private String pointPositionCode;//点收区域编码
    private String remarks;//备注
    private Date createTime;//任务创建时间
    private Integer createUserId;//创建人id
    private String createName;//创建人名称

    private Double carryNumber;//载具承载数量
    private String carrierCode;//载具编号
    private String batchNo;//批次号
    private String areaCode;//点收区
    private Double unqualifiedNum;//不合格品数量
    private Double qualifiedNum;//合格品数量

    private Integer qualifiedType;//物料合格类型0：合格、1：不合格

    private String warehouseName;//仓库名称
    private String arrivalCode;//到货通知单编码

}

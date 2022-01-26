package com.zkzn.les.wms.receiveRecord.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * app点收详情点收完成实体
 */
@Data
@ToString(callSuper = true)
public class SaveReceiveDetailPojo {

    private Integer receiveDetailId;//点收详情id
    private Double receiveNum;//实际点收数量
    private String clientName;//客户名称
    private Integer receiveDetailStatus;//点收状态
    private String remarks;//备注
    private String warehouseName;//仓库名称
    private String warehouseCode;//仓库号
    private String pointerName;//点收人名称
    private Integer pointerId;//点收人id
    private Date receiveTime;//点收时间
    private List<ReceiveCarrierPojo> carrierList;//载具实体

}

package com.zkzn.les.wms.receiveRecord.pojo;

import lombok.Data;
import lombok.ToString;

/**
 * 点收载具实体
 */
@Data
@ToString(callSuper = true)
public class ReceiveCarrierPojo {

    private Integer receiveDetailId;//点收详情id
    private Double carryNumber;//载具物料数量
    private String carrierCode;//载具编码
    private String areaCode;//点收区域编码
    private String batchNo;//批次号
    private String materialDesc;//物料名称
    private String materialUnit;//物料单位
    private Integer qualifiedType; //物料合格类型0：合格、1：不合格
}

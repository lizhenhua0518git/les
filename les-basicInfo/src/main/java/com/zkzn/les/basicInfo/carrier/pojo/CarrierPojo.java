package com.zkzn.les.basicInfo.carrier.pojo;

import com.zkzn.les.common.util.page.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 载具实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CarrierPojo extends PageCondition {

    private Integer carrierId;//载具表id
    private String carrierName;//载具名称
    private String carrierCode;//载具编号
    private Integer carrierType;//载具类型
    private String carrierTypeName;//载具类型名称
    private String warehouseCode;//仓库编码
    private String warehouseName;//仓库名称
    private Integer useStatus;//0-空闲 1-占用

}

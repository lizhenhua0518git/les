package com.zkzn.les.basicInfo.warehouse.pojo;

import com.zkzn.les.basicInfo.util.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 仓库实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WarehousePojo extends PageCondition {

    private Integer warehouseId;//仓库表id
    private String warehouseCode;//仓库编码
    private String warehouseName;//仓库名称
    private Integer warehouseType;//仓库类型

    private Integer belongUserId;//库内人员id

}

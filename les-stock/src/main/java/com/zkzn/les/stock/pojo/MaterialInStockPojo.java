package com.zkzn.les.stock.pojo;

import com.zkzn.les.common.util.page.PageCondition;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 仓库库存实体
 */
@Data
@ToString(callSuper = true)
public class MaterialInStockPojo extends PageCondition {

    private Integer materialInStockId;//仓库库存id
    private String materialDesc;//物料名称
    private String materialUnit;//物料单位
    private String clientName;//客户名称
    private String batchNo;//批次号
    private String warehouseName;//仓库名称
    private String warehouseCode;//仓库编号
    private Integer qualifiedType;//物料合格类型0：合格、1：不合格
    private Double unlimitedNumber;//库存数量
    private Date createTime;//创建时间

}

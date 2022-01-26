package com.zkzn.les.basicInfo.storagePosition.pojo;

import com.zkzn.les.common.util.page.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 库位实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StoragePositionPojo extends PageCondition {

    private Integer storagePositionId;//库位id
    private String positionCode;//库位编码
    private String positionName;//库位名称
    private Integer clientManageId;//客户id
    private String clientName;//客户名称
    private String warehouseCode;//仓库编号
    private String warehouseName;//仓库名称
    private Integer binType;//仓位类型  1-地堆 2-货架  3-点收 4-质检 5-下架 6-交接
    private Integer status;//0-禁用 1-启用
    private String statusName;//状态名称
    private Integer occupyStatus;//0-占用 1-空闲
    private String occupyStatusName;//占用状态
    private Integer createrUserId;//创建用户id

}

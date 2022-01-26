package com.zkzn.les.wms.upperFrame.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 生成上架任务实体
 */
@Data
@ToString(callSuper = true)
public class UpperFrameData {

    private Integer upperFrameId;//上架任务表主键id
    private Integer receiveTaskId;//入库任务主表id
    private Integer receiveDetailId;//入库任务子表id
    private Integer recommendedPositionId;//推荐仓位号id
    private String recommendedPositionCode;//推荐仓位号
    private String positionCode;//上架仓位号
    private Integer positionId;//上架仓位id
    private String materialDesc;//物料名称
    private String materialUnit;//物料单位
    private Double materialNum;//物料数量
    private Integer operateUserId;//操作人id
    private String operateUserName;//操作人名称
    private Integer upperType;//0-合格品，1-不合格品
    private Date upperUpperTime;//上架时间
    private String upperUpperName;//上架人名
    private Integer upperUserId;//上架人
    private String carrierCode;//载具号
    private String upperTaskCode;//上架任务号
    private String batchNo;//批次号
    private Integer clientManageId;//客户id
    private String clientName;//客户名称
    private String warehouseName;//仓库名称
    private String warehouseCode;//仓库编号
    private Integer upperOrigin;//上架来源 1：采购上架 2：生产退料上架 3：调拨上架 4：其他上架
    private Integer status;//0-待上架，1-上架完成
}

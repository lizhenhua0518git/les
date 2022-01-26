package com.zkzn.les.wms.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ProjectName: lesGroup
 * @Package: com.zkzn.les.wms.app.pojo
 * @ClassName: DeliveryPojo
 * @Author: 胡志明
 * @Description:
 * @Date: 2020/9/22 13:31
 */
 @Data
public class DeliveryPojo {

    
	private String deliveryId;//交接任务id
    private String stationOrderId;//生产订单id
    private String taskNumber;//交接任务号
    private String deliveryType;//交接类型
    private String deliveryName;//交接状态 0、车间；2、领料；1、调拨
    private String lineCode;//产线
    private String stationCode;//工位编号
    private String stationDesc;//工位名称
    private String carrierName;//载具名称
    private String carrierCode;//载具编号
    private String orderCode;//生产订单号/调拨订单号
    private Integer materialNumber;//物料种类
    private String requiredDate;//订单生产日期
    private String workshopCode;//车间
    private String busiType;//业务类型  5成本中心领料  6 成本中心退料 7内部订单领料 8 内部订单退料
    private String costCenterOrder; //领料单

    private String warehouseCode;

    @ApiModelProperty(name = "sourceEntrepot", value = "源仓库")
    private String sourceEntrepot;
    @ApiModelProperty(name = "goalEntrepot", value = "目的仓库")
    private String goalEntrepot;

    
}

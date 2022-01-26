package com.zkzn.les.wms.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName StorageOrderDetial
 * @Description TODO
 * @Author zhanglei
 * Date 2020/9/23 17:11
 * @Version 1.0
 **/
@Data
public class StorageOrderDetail {
    //主键id
    private String id;
    //仓位号
    private String positionCode;
    //仓位库存表id
    private String storageBinId;
    //仓位id
    private String storageId;
    //工位订单详情id
    private String stationDetailId;
    //下架数量
    private Double pickNum;
    //创建日期
    private Date createTime;
    //库位可用库存
    private Double enableCount;
}
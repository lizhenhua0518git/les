package com.zkzn.les.wms.pojo;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


/**
 * @ClassName pojo 包装类
 * @Author zhanglei
 * Date 2020/9/18 11:41
 * @Version 1.0
 **/
@Setter
@Getter
public class StationOrderDetail {
    String id;
    String materialCode;
    String materialDesc;
    String materialUnit;
    String reserveCode;
    String reserveRow;
    Double pickNum;
    String specialType;
    String specialCode;
    String specialName;
    String stationLocation;
    String positionCode;
    String storageBinId;
    Date createTime;
    String storageId;
    String processDetialId;
    Integer status;
    String stationOrderId;
    String materialRow;
    String originCarrierCode;
    String carrierCode;
    String billName;
    Integer isSerial;
    Integer busiType;
    String modifierName;
    String orderCode;
    String tasksId;
    String costCenterOrder;
    String costCenterDesc;
    int orderType;
    Date modifiedTime;
//源仓库
    String sourceEntrepot;
//    目的仓库
    String goalEntrepot;
//    物料种类
    String num;
    /***
     * @Discription: 物料对应序列号
     * @Author: zhanglei on 2020/9/18 13:21
     */
//    List<String> materialSerials;
    /**
     *  待上架列表子单
     */
    List<TransferSlipInfo> infoList;
}
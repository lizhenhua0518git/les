package com.zkzn.les.common.pojo;

import com.zkzn.les.common.util.PageCondition;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @ClassName 库存信息
 * @Author zhanglei
 * Date 2020/9/17 18:00
 * @Version 1.0
 **/
@Setter
@Getter
public class MaterialStorage extends PageCondition {
    /***
     * @Discription: 库位编码
     */
    private String positionCode;

    private String id;
    /***
     * @Discription: 库存数量 非限制库存
     */
    private Integer stockCount;

    private String materialStorageId;

    /**
     * @Discription: 可用库存
     */
    private Double enableCount;

    private Double preUseCount;

    private String supplierCode;

    private String supplierName;

    private Date receiveDate;

    private String batchNo;

    private Date createTime;

    private String storageLocation;

    private String factory;

    private String wareHouseCode;

    private String wareHouseName;

    private String orderCode;

    private Integer orderItemNo;

    private Integer stockStatus;

    private String customerCode;

    private String customerName;

    private Date modifiedTime;

    private Integer stockType;

    private String stationCode;

    private Integer inspectionCount;

    private Double unEnableCount;

    /***
     * @Discription: 仓位表id
     */
    private String storageId;

    /***
     * @Discription: 物料编码
     */
    private String materialCode;

    /***
     * @Discription: 物料描述
     */
    private String materialDesc;

    /***
     * @Discription: 物料单位
     */
    private String materialUnit;

    /***
     * @Discription: 库位名称
     */
    private String positionName;

    /***
     * @Discription: 下架数量
     */
    private Double pickNum;
}
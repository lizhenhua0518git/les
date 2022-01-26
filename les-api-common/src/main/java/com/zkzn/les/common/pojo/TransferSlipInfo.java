package com.zkzn.les.common.pojo;

import com.zkzn.les.common.util.PageCondition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author lty
 * @version 1.0.0
 * @ClassName TransferSlipInfo.java
 * @Description 调拨单详情实体类
 * @createTime 2020年10月13日 14:08:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TransferSlipInfo extends PageCondition {
    private static final long serialVersionUID = -8407327435591451813L;

    @ApiModelProperty(name = "id", value = "调拨单id")
    private String id;
    @ApiModelProperty(name = "taskId", value = "调拨任务编号")
    private String taskId;
    @ApiModelProperty(name = "getPeople", value = "领取人")
    private String getPeople;
    @ApiModelProperty(name = "getTime", value = "领取时间")
    private Date getTime;
    @ApiModelProperty(name = "materialId", value = "物料id")
    private String materialId;
    @ApiModelProperty(name = "materialCode", value = "物料号")
    private String materialCode;
    @ApiModelProperty(name = "materialDesc", value = "物料描述")
    private String materialDesc;
    @ApiModelProperty(name = "storageId", value = "仓位表id")
    private String storageId;
    @ApiModelProperty(name = "storageName", value = "仓位名称")
    private String storageName;
    @ApiModelProperty(name = "enableCount", value = "仓位储存数量")
    private String enableCount;

    @ApiModelProperty(name = "materialUnit", value = "单位")
    private String materialUnit;
    @ApiModelProperty(name = "batchNo", value = "批次")
    private String batchNo;
    @ApiModelProperty(name = "outPeople", value = "下架人")
    private String outPeople;
    @ApiModelProperty(name = "outTime", value = "下架时间")
    private Date outTime;
    @ApiModelProperty(name = "res", value = "预留字段")
    private String res;
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;
    @ApiModelProperty(name = "isDel", value = "是否删除(0-删除 1 未删除)")
    private String isDel;

    @ApiModelProperty(name = "supplierCode", value = "供应商编码")
    private String supplierCode;
    @ApiModelProperty(name = "supplierName", value = "供应商名称")
    private String supplierName;
    @ApiModelProperty(name = "stationCode", value = "工位编码")
    private String stationCode;
    @ApiModelProperty(name = "carrierCode", value = "载具编码")
    private String carrierCode;
    @ApiModelProperty(name = "carrierName", value = "载具名称")
    private String carrierName;
    @ApiModelProperty(name = "status", value = "状态")
    private String status;
    @ApiModelProperty(name = "stationName", value = "工位名称")
    private String stationName;
    @ApiModelProperty(name = "upStorageName", value = "上架仓位")
    private String upStorageName;
    @ApiModelProperty(name = "upNumber", value = "上架数量")
    private String upNumber;
    @ApiModelProperty(name = "receiveNum", value = "上架数量")
    private Double receiveNum;
    @ApiModelProperty(name = "upNumberOver", value = "已上架数量")
    private String upNumberOver;
    @ApiModelProperty(name = "outNumber", value = "下架数量")
    private String outNumber;
    @ApiModelProperty(name = "pickNum", value = "通用下架数量")
    private Double pickNum;
    @ApiModelProperty(name = "outNumberOver", value = "已下架数量")
    private String outNumberOver;

    @ApiModelProperty(name = "serialNum", value = "是否是序列号 0否 1 是")
    private String serialNum;

    @ApiModelProperty(name = " materials", value = "仓位列表")
    private List<TransferSlipMaterial>  materials;

    @ApiModelProperty(name = "storageList", value = "仓位列表")
    private TransferSlipMaterial storageList;

    @ApiModelProperty(name = " materialSerialNumList", value = "序列号列表")
    private List<String> materialSerialNumList;
    @ApiModelProperty(name = " goalStorageLocation", value = "入库地点")
    private String goalStorageLocation;
}

package com.zkzn.les.common.pojo.panel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class QueuePanel  implements Serializable {

    private static final long serialVersionUID = 5261192058006602073L;

    @ApiModelProperty(name = "status", value = "状态")
    private String status;
    @ApiModelProperty(name = "statusStr", value = "状态字符")
    private String statusStr;
    @ApiModelProperty(name = "queueCode", value = "排队号")
    private String queueCode;
    @ApiModelProperty(name = "uplodPlat", value = "卸货点")
    private String uplodPlat;
    @ApiModelProperty(name = "supplierName", value = "供应商")
    private String supplierName;
    @ApiModelProperty(name = "carCode", value = "车牌号")
    private String carCode;
    @ApiModelProperty(name = "queueTime", value = "排队时间")
    private String queueTime;
    @ApiModelProperty(name = "queueNum", value = "排队序号")
    private String queueNum;
    @ApiModelProperty(name = "warehouseName", value = "仓库名称")
    private String warehouseName;
    private Date createTime;
    private String arriveTime;
}

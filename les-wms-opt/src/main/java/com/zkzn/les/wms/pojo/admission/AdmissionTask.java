package com.zkzn.les.wms.pojo.admission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName AdmissionTask
 * @Description 排号任务表
 * @Author zhanglei
 * Date 2021/6/11 13:05
 * @Version 1.0
 **/
@ApiModel(value = "排号任务表")
@Data
public class AdmissionTask {
    @ApiModelProperty(value = "排号任务主表id")
    private String id;
    @ApiModelProperty(value = "司机姓名")
    private String driverName;
    @ApiModelProperty(value = "司机联系方式")
    private String driverPhone;
    @ApiModelProperty(value = "车辆入厂时间")
    private Date inFactoryTime;
    @ApiModelProperty(value = "车牌号")
    private String carCode;
    @ApiModelProperty(value = "车辆类型")
    private int carTye;
    @ApiModelProperty(value = "车辆出厂时间")
    private Date outFactoryTime;
    @ApiModelProperty(value = "到货通知单表主键id")
    private String arrivalNoticeId;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "在场总时长")
    private long totalTime;
    @ApiModelProperty(value = "卸货点编码")
    private String uploadPlatCode;
    @ApiModelProperty(value = "车辆位置(1-厂内、0-厂外)")
    private int admissionStatus;
    @ApiModelProperty(value = "卸货完成时间")
    private Date uploadFinishTime;
    @ApiModelProperty(value = "排队号")
    private String queueCode;
    @ApiModelProperty(value = "紧急状态(默认0 数字越大越紧急)")
    private int emergencyStatus;
    @ApiModelProperty(value = "排号状态  0 已排号 1 叫号 2 过号 3 取消排号 4 已完成")
    private int status;
}
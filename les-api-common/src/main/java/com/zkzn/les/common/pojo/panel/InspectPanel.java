package com.zkzn.les.common.pojo.panel;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class InspectPanel implements Serializable {

    /**
     * 质检区域ID
     */
    private String areaId;
    /**
     * 物料描述
     */
    private String materialDesc;
    /**
     * 合格数量
     */
    private Double qualifiedCount;
    /**
     * 总量
     */
    private Double totalCount;
    /**
     * 0-合格、1-不合格、2-部分合格
     */
    private String vote;
    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 生产订单
     */
    private String billCode;
    /**
     * 未完成质检数量
      */
    private Double unInspectNum;

    /**
     * 已完成质检数量
     */
    private Double  insepectedNum;

    /**
     * 合格数量
     */
    private  Double qualifiedNum;

    /**
     * 不合格数量
     */
    private Double unqualifiedNum;

    /**
     * 开始质检时间
     */
    private Date inspectionTime;

    /**
     * 状态
     */
    private String statusStr;

    /**
     * 质检进度
     */
    private String inspectSchedule;

    /**
     * 质检时长
     */
    private String time;
}

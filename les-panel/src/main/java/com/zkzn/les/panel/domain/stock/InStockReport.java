package com.zkzn.les.panel.domain.stock;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 入库日报表 </p>
 *
 * @author Hush.
 * @since 2022/01/04 9:36
 */
@Data
@ToString
public class InStockReport {
    /**
     * 仓库名称
     */
    private String warehouseName;
    /**
     * 客户名称
     */
    private String clientName;
    /**
     * 任务编号
     */
    private String arrivalCode;
    /**
     * 货物名称
     */
    private String materialDesc;
    /**
     * 到货数量
     */
    private String arrivalCount;
    /**
     * 单位
     */
    private String materialUnit;
    /**
     * 批次号
     */
    private String batchNo;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")//出参格式化
    private Date createTime;
    /**
     * 点收开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")//出参格式化
    private Date receiveStartTime;
    /**
     * 点收结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")//出参格式化
    private Date receiveEndTime;
    /**
     * 点收时长
     */
    private String receiveTotalTime;
    /**
     * 上架开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")//出参格式化
    private Date upStart;
    /**
     * 上架结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")//出参格式化
    private Date upEnd;
    /**
     * 上架时长
     */
    private String upTotal;
    /**
     * 上架仓位
     */
    private String positionCode;
    /**
     * 仓位载具
     */
    private String carrierCode;
}

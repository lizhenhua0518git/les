package com.zkzn.les.common.pojo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @ProjectName: lesGroup
 * @Package: com.zkzn.les.wms.app.pojo
 * @ClassName: MaterialData
 * @Author: 胡志明
 * @Description: 上架物料信息
 * @Date: 2020/9/9 15:15
 */
public class MaterialData {

    @ApiModelProperty(name="materialCode", value="物料编号")
    private String materialCode;//物料编号

    @ApiModelProperty(name="materialDesc", value="物料名称")
    private String materialDesc;//物料名称

    @ApiModelProperty(name="serialNum", value="序列号")
    private String serialNum;//序列号

    @ApiModelProperty(name="receiveNum", value="物料收货数量")
    private Double receiveNum;//物料收货数量

    @ApiModelProperty(name="orderCode", value="订单号")
    private String orderCode;//订单号

    @ApiModelProperty(name="stationCode", value="工位")
    private String stationCode;//工位

    @ApiModelProperty(name="inspectionStatus", value="合格不合格状态")
    private Integer inspectionStatus;//合格不合格状态

    @ApiModelProperty(name="upperId", value="上架id")
    private String upperId;//上架id

    @ApiModelProperty(name="infoId", value="详情id")
    private String infoId;//详情id

    @ApiModelProperty(name="batchNo", value="批次号")
    private String batchNo;//批次号

    @ApiModelProperty(name="flag", value="0全部1调拨2其它")
    private String flag;//类型

    @ApiModelProperty(name="upperOrigin", value="数据表格字段")
    private String upperOrigin;//类型

    private String clientName;//客户名称

    public String getUpperOrigin() {
        return upperOrigin;
    }

    public void setUpperOrigin(String upperOrigin) {
        this.upperOrigin = upperOrigin;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialDesc() {
        return materialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public Double getReceiveNum() {
        return receiveNum;
    }

    public void setReceiveNum(Double receiveNum) {
        this.receiveNum = receiveNum;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public Integer getInspectionStatus() {
        return inspectionStatus;
    }

    public void setInspectionStatus(Integer inspectionStatus) {
        this.inspectionStatus = inspectionStatus;
    }

    public String getUpperId() {
        return upperId;
    }

    public void setUpperId(String upperId) {
        this.upperId = upperId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}

package com.zkzn.les.wms.pojo;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: lesGroup
 * @Package: com.zkzn.les.wms.app.pojo
 * @ClassName: MaterialSerial
 * @Author: 胡志明
 * @Description: 序列号管理实体
 * @Date: 2020/9/5 14:52
 */
public class MaterialSerial {
    private String materialSerialID;//主键Id
    private String createrId;//创建人id
    private Date createTime;//创建时间
    private String createrName;//创建人名
    private String materialCode;//物料号
    private String batchNo;//批次号
    private String serialNum;//序列号
    private String receivedTaskCode;//任务号
    private String taskId;//详情任务id
    private Integer taskType;//任务类型  1-点收；2-拆盘；3-上架 4,下架
    private String carrierCode;//载具编码
    private String carrierId;//载具Id
    private String carrierType;//载具类型
    private Integer invalidStatus;//作废状态 0、正常  1、作废
    private List<String> serialNums;
   private String carrierName;

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public List<String> getSerialNums() {
        return serialNums;
    }

    public void setSerialNums(List<String> serialNums) {
        this.serialNums = serialNums;
    }

    public String getMaterialSerialID() {
        return materialSerialID;
    }

    public void setMaterialSerialID(String materialSerialID) {
        this.materialSerialID = materialSerialID;
    }

    public String getCreaterId() {
        return createrId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getReceivedTaskCode() {
        return receivedTaskCode;
    }

    public void setReceivedTaskCode(String receivedTaskCode) {
        this.receivedTaskCode = receivedTaskCode;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    public String getCarrierType() {
        return carrierType;
    }

    public void setCarrierType(String carrierType) {
        this.carrierType = carrierType;
    }

    public Integer getInvalidStatus() {
        return invalidStatus;
    }

    public void setInvalidStatus(Integer invalidStatus) {
        this.invalidStatus = invalidStatus;
    }
}

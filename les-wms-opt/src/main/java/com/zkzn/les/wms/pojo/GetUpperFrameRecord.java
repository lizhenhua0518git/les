package com.zkzn.les.wms.pojo;

import com.zkzn.les.common.pojo.MaterialData;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ProjectName: lesGroup
 * @Package: com.zkzn.les.wms.app.pojo
 * @ClassName: GetUpperFrameRecord
 * @Author: 胡志明
 * @Description: 上架列表查询结果
 * @Date: 2020/9/9 15:11
 */
public class GetUpperFrameRecord {

    @ApiModelProperty(name="stationCode", value="工位号")
    private String stationCode;//工位号

    @ApiModelProperty(name="taskId", value="调拨任务号")
    private String taskId;

    @ApiModelProperty(name="receiveNumber", value="任务数量")
    private Integer receiveNumber;//任务数量

    @ApiModelProperty(name="materialDataList", value="物料信息")
    private List<MaterialData> materialDataList;//物料信息

    @ApiModelProperty(name="flag", value="类型0 所有 1调拨单 2其它")
    private String flag;

    /**
     *  待上架列表子单
     */
    @ApiModelProperty(name="infoList", value="待上架列表子单")
    List<TransferSlipInfo> infoList;

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public Integer getReceiveNumber() {
        return receiveNumber;
    }

    public void setReceiveNumber(Integer receiveNumber) {
        this.receiveNumber = receiveNumber;
    }

    public List<MaterialData> getMaterialDataList() {
        return materialDataList;
    }

    public void setMaterialDataList(List<MaterialData> materialDataList) {
        this.materialDataList = materialDataList;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List<TransferSlipInfo> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<TransferSlipInfo> infoList) {
        this.infoList = infoList;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}

package com.zkzn.les.wms.pojo;

import java.util.List;

/**
 * @ProjectName: lesGroup
 * @Package: com.zkzn.les.wms.app.pojo
 * @ClassName: DeliveryMaterialPojo
 * @Author: 胡志明
 * @Description:
 * @Date: 2020/9/22 15:55
 */
public class DeliveryMaterialPojo {

    private String stationOrderDetailId;//生产详情订单id
    private String materialCode;//物料编码
    private String materialDesc;//物料名称
    private String materialUnit;//物料单位
    private Double pickNum;//减配数量
    private String serialNum;//是否序列号管理  0、否  1、是
    private String serialCode;//序列号
    private List<String> materialSerialList;//序列号集合

    public String getStationOrderDetailId() {
        return stationOrderDetailId;
    }

    public void setStationOrderDetailId(String stationOrderDetailId) {
        this.stationOrderDetailId = stationOrderDetailId;
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

    public String getMaterialUnit() {
        return materialUnit;
    }

    public void setMaterialUnit(String materialUnit) {
        this.materialUnit = materialUnit;
    }

    public Double getPickNum() {
        return pickNum;
    }

    public void setPickNum(Double pickNum) {
        this.pickNum = pickNum;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getSerialCode() {
        return serialCode;
    }

    public void setSerialCode(String serialCode) {
        this.serialCode = serialCode;
    }

    public List<String> getMaterialSerialList() {
        return materialSerialList;
    }

    public void setMaterialSerialList(List<String> materialSerialList) {
        this.materialSerialList = materialSerialList;
    }
}

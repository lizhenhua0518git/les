package com.zkzn.les.oms.pojo;

/**
 * @ClassName 波次表 T_WAVE
 * @Author zhanglei
 * Date 2020/9/9 15:44
 * @Version 1.0
 **/
public class StationWave extends Wave {
    //配送台套数
    private String sendAddress;
    //配送台套数
    private Integer platNum;
    //车间
    private String wareShopCode;
    //产线
    // LINE_CODE
    private String lineCode;
    //工位
    private String stationCode;
    //仓库编码
    private String wareHouseCode;
    //仓库名称

    private String wareHouseName;
    //状态
    private Integer status;

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public Integer getPlatNum() {
        return platNum;
    }

    public void setPlatNum(Integer platNum) {
        this.platNum = platNum;
    }

    public String getWareShopCode() {
        return wareShopCode;
    }

    public void setWareShopCode(String wareShopCode) {
        this.wareShopCode = wareShopCode;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getWareHouseCode() {
        return wareHouseCode;
    }

    public void setWareHouseCode(String wareHouseCode) {
        this.wareHouseCode = wareHouseCode;
    }

    public String getWareHouseName() {
        return wareHouseName;
    }

    public void setWareHouseName(String wareHouseName) {
        this.wareHouseName = wareHouseName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
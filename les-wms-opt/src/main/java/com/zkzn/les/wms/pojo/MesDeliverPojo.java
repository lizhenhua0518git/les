package com.zkzn.les.wms.pojo;


import java.util.List;

/**
 * @ClassName MesDeliverPojo
 * @Description mes 对接物料配送接口数据封装
 * @Author zhanglei
 * Date 2020/12/15 9:42
 * @Version 1.0
 **/

public class MesDeliverPojo {

    /***
     * @Discription: 主键
     */
    private String stationId;
    /***
     * @Discription: 工厂编号
     */
    private String factory;
    /***
     * @Discription: 车间编号
     */
    private String workshopcode;

    /***
     * @Discription:  工位编号
     */
    private String stationCode;

    /***
     * @Discription:  物料配送单号
     */
    private String assembleOrderCode;

    /***
     * @Discription: 订单编号
     */
    private String orderCode;

    /***
     * @Discription: 车辆VIN号
     */
    private String VINCode;
    /* *
     * @Author 刘松山
     * @Description  业务类型 1 生产出库 3
     * @Date 16:11 2021/5/20
     * @Param
     * @return
     **/

    private String  busiType;

    /***
     * @Discription:   物料配送列表
     */
    private List<DeliveryInf> deliveryInfoList;

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getWorkshopcode() {
        return workshopcode;
    }

    public void setWorkshopcode(String workshopcode) {
        this.workshopcode = workshopcode;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getAssembleOrderCode() {
        return assembleOrderCode;
    }

    public void setAssembleOrderCode(String assembleOrderCode) {
        this.assembleOrderCode = assembleOrderCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getVINCode() {
        return VINCode;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public void setVINCode(String VINCode) {
        this.VINCode = VINCode;
    }

    public List<DeliveryInf> getDeliveryInfoList() {
        return deliveryInfoList;
    }

    public void setDeliveryInfoList(List<DeliveryInf> deliveryInfoList) {
        this.deliveryInfoList = deliveryInfoList;
    }
}
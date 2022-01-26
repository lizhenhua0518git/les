package com.zkzn.les.wms.pojo;

/**
 * @ClassName OrderInfo
 * @Description 对接mes 物料配送接口
 * @Author zhanglei
 * Date 2020/12/15 9:40
 * @Version 1.0
 **/
public class OrderInfo {
    /***
     * @Discription: 订单编号
     */
    private  String orderCode;
    /***
     * @Discription: 车辆VIN号
     */
    private String VINCode;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getVINCode() {
        return VINCode;
    }

    public void setVINCode(String VINCode) {
        this.VINCode = VINCode;
    }
}
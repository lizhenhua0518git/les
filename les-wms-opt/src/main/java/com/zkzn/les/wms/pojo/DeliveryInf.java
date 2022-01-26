package com.zkzn.les.wms.pojo;


import java.util.List;

/**
 * @ClassName MesDeliverList
 * @Description 对接mes 物料配送接口 实体类封装
 * @Author zhanglei
 * Date 2020/12/15 9:37
 * @Version 1.0
 **/
public class DeliveryInf {
    /***
     * @Discription: 详情id
     */
    private String stationDetailId;
    /***
     * @Discription: 物料编码
     */
    private String materialCode;
    /***
     * @Discription: 物料名称
     */
    private String materialName;
    /***
     * @Discription: 供应商名称
     */
    private  String supplierName;

    /***
     * @Discription: 物料单位
     */
    private String materailUnit;
    /***
     * @Discription: 物料数量
     */
    private Double materialNum;
    /* *
     * @Author 刘松山
     * @Description 库存地点
     * @Date 9:52 2021/6/2
     * @Param 
     * @return 
     **/
    
    private String  storageLocation;
    
    private String reserveCode;
    
    private String reserveRow;

    /***
     * @Discription: 订单列表
     */
    private List<OrderInfo> orderInfoList;

    public String getStationDetailId() {
        return stationDetailId;
    }

    public void setStationDetailId(String stationDetailId) {
        this.stationDetailId = stationDetailId;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getMaterailUnit() {
        return materailUnit;
    }

    public void setMaterailUnit(String materailUnit) {
        this.materailUnit = materailUnit;
    }

    public Double getMaterialNum() {
        return materialNum;
    }

    public void setMaterialNum(Double materialNum) {
        this.materialNum = materialNum;
    }

	/**
	 * 获取 orderInfoList
	 * @return 返回 orderInfoList
	 */
	public List<OrderInfo> getOrderInfoList() {
		return orderInfoList;
	}

	/**
	 * 获取 reserveCode
	 * @return 返回 reserveCode
	 */
	public String getReserveCode() {
		return reserveCode;
	}

	/**
	 * 获取 reserveRow
	 * @return 返回 reserveRow
	 */
	public String getReserveRow() {
		return reserveRow;
	}

	/**
	 * 设置 reserveCode
	 * @param reserveCode 对reserveCode进行赋值
	 */
	public void setReserveCode(String reserveCode) {
		this.reserveCode = reserveCode;
	}

	/**
	 * 设置 reserveRow
	 * @param reserveRow 对reserveRow进行赋值
	 */
	public void setReserveRow(String reserveRow) {
		this.reserveRow = reserveRow;
	}

	/**
	 * 设置 orderInfoList
	 * @param orderInfoList 对orderInfoList进行赋值
	 */
	public void setOrderInfoList(List<OrderInfo> orderInfoList) {
		this.orderInfoList = orderInfoList;
	}

     
}
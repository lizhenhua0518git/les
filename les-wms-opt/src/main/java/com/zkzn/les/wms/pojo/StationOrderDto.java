package com.zkzn.les.wms.pojo;

import com.zkzn.les.common.pojo.MaterialStorage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ClassName StationOrderDetail + material_storage_bin 包装对象
 * @Author zhanglei
 * Date 2020/9/17 17:51
 * @Version 1.0
 **/
@Setter
@Getter
public class StationOrderDto {

    private String id;
    /***
     * @Discription: 物料编码
     */
    private String materialCode;
    /***
     * @Discription: 物料名称
     */
    private String  materialDesc;

    /***
     * @Discription: 生成订单，t_process_order  VEHICLE_DESC 中的物料描述
     */
    private String billName;

    /***
     * @Discription: 生成订单，t_process_order  SELL_ORDER 销售订单
     */
    private String orderCode;
    /***
     * @Discription: 下架数量
     */
    private Integer pickNum;

    private String waveId;

    private String stationOrderId;

    private Integer isSerial;

    /***
     * @Discription: 物料单位
     */
    private String materialUnit;
    /***
     * @Discription: 一对多关联库位信息
     * @param null
     * @return null
     * @Author: zhanglei on 2020/9/18 9:11
     */
   private List<MaterialStorage> materials;

   /***
    * @Discription: 关联库存匹配信息
    * @param null
    * @return null
    * @Author: zhanglei on 2020/9/23 17:16
    */
   private List<StorageOrderDetailDto> storageOrderDetails;

}
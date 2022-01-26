package com.zkzn.les.wms.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @ProjectName: lesGroup
 * @Package: com.zkzn.les.wms.app.pojo
 * @ClassName: UpdateAssemble
 * @Author: 胡志明
 * @Description:
 * @Date: 2020/9/22 17:30
 */
@Data
public class UpdateAssemble {

    private String stationOrderId;//生产订单id
    private String handlerName;//交接人员名称
    private String remarks;//备注
    private String userId;//交接人员id
    private Date sendTime;//交接时间
    private int status;
    private String busiType; //业务类型

    private String supplierCode;//供应商编码
    private String supplierName;//供应商名称


}

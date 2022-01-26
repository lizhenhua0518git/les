package com.zkzn.les.common.pojo;

import com.zkzn.les.common.util.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @ClassName 供应商载具回收pojo -> T_SUPPLIER_APPLIANCES
 * @Description TODO
 * @Author zhanglei
 * Date 2020/8/31 14:43
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SupplierAppliances extends PageCondition {
    //供应商名称
    private String supplierName;
    //供应商编码
    private String supplierCode;
    //器具编码
    private String appliancesCode;
    //器具名称
    private String appliancesName;
    //存放位置
    private String location;
    //存放数量
    private int storageNum;
    //流水数量
    private int flowNum;
    //标记(0 老记录   1 新记录)
    private int newFlg;
   //数据记录行标识
    private String id;
}
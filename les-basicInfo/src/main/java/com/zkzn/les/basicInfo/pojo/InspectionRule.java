package com.zkzn.les.basicInfo.pojo;

import com.zkzn.les.basicInfo.util.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @ClassName 自检规则 -> T_INSPECTION_RULE
 * @Author zhanglei
 * Date 2020/9/2 11:09
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InspectionRule extends PageCondition {
    //id
    private String id;
    //仓库编码
    private String wareHoseCode;
    //仓库名称
    private String wareHoseName;
    //单据类型编码
    private String billCode;
    //单据名称
    private String billName;
    //发货方编码
    private String supplierCode;
    //发货方名称
    private String supplierName;
    //是否需要质检 0-不质检 1-质检
    private int isInspect;
    //是否质检字符串
    private String isInspectStr;
    //多选值查询
    private String warehouseCodes;
    //多选值查询
    private String billCodes;

    public String getWarehouseCodes() {

        return warehouseCodes;
    }

    public void setWarehouseCodes(String warehouseCodes) {
        this.warehouseCodes = warehouseCodes;
    }

    public String getBillCodes() {
        return billCodes;
    }

    public void setBillCodes(String billCodes) {
        this.billCodes = billCodes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWareHoseCode() {
        return wareHoseCode;
    }

    public void setWareHoseCode(String wareHoseCode) {
        this.wareHoseCode = wareHoseCode;
    }

    public String getWareHoseName() {
        return wareHoseName;
    }

    public void setWareHoseName(String wareHoseName) {
        this.wareHoseName = wareHoseName;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public int getIsInspect() {

        return isInspect;
    }

    public void setIsInspect(int isInspect) {
        this.isInspect = isInspect;
    }

    public String getIsInspectStr() {
        return isInspect == 1 ?"是":"否";
    }

    public void setIsInspectStr(String isInspectStr) {
        this.isInspectStr = isInspectStr;
    }
}
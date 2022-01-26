package com.zkzn.les.basicInfo.pojo;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import com.zkzn.les.basicInfo.util.PageCondition;

/**
 * 创建人: wangzhou
 * 时间:20202020年3月26日上午9:51:07
 * 功能描述:工位管理实体类
 */
public class StationManager extends PageCondition {

    private static final long serialVersionUID = -6579023355988055978L;

    private String id;//id
    private String productLine;//产品线
    private String subFactory;//分厂
    private String stationCode;//工位
    private String managerName;//负责人

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public String getSubFactory() {
        return subFactory;
    }

    public void setSubFactory(String subFactory) {
        this.subFactory = subFactory;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

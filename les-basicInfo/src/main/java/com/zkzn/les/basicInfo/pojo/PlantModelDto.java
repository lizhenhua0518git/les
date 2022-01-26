package com.zkzn.les.basicInfo.pojo;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName PlantStruByPid.java
 * @Description TODO
 * @createTime 2020年11月05日 15:01:00
 */
public class PlantModelDto {
    //类型:1工厂、2车间、3产线、4工位
    private String type;
    //父级模型编码
    private List<String> modelCode;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getModelCode() {
        return modelCode;
    }

    public PlantModelDto setModelCode(List<String> modelCode) {
        this.modelCode = modelCode;
        return this;
    }
}

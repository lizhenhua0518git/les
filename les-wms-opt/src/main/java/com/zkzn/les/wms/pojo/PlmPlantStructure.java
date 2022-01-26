package com.zkzn.les.wms.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName PlmPlantStructure
 * @Description TODO
 * @Author zhanglei
 * Date 2020/9/21 13:43
 * @Version 1.0
 **/
@Data
@ToString
public class PlmPlantStructure implements Serializable {
    private static final long serialVersionUID = -906357110051689484L;
    private String id;
    //plm系统主键id
    private String plmUid;
    //bom的唯一标识
    private String bomUid;
    //父节点id
    private String plantItemId;
    //类型  1 工厂 2 车间 3 产线 4 工位
    private String modelType;
    //模型编码
    private String modelCode;
    //模型名称
    private String modelName;
    //创建时间
    private Date ctreateTime;
}
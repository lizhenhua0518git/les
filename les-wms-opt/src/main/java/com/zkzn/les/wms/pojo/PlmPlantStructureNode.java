package com.zkzn.les.wms.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @ClassName PlmPlantStructureNode
 * @Description TODO
 * @Author zhanglei
 * Date 2020/9/21 13:47
 * @Version 1.0
 **/
@Data
@ToString
public class PlmPlantStructureNode extends PlmPlantStructure {
    List<PlmPlantStructureNode> children;
}
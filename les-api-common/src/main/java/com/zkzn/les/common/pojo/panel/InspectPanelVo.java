package com.zkzn.les.common.pojo.panel;

import lombok.Data;

import java.util.List;

/**
 * @ClassName InspectPanelVo
 * @Description 质检大屏包装类
 * @Author zhanglei
 * Date 2021/3/11 18:00
 * @Version 1.0
 **/
@Data
public class InspectPanelVo {
    //当日总数量
    private double totalCount;
    //今日已完成数量
    private double inspectedNum;
    //当日未完成数量
    private double unInspectedNum;
    //总合格数量
    private double totalQualifiedNum;
    //总不合格数量
    private double totalUnQualifiedNum;
    //数据列表
    private List<InspectPanel> listInspect;
}
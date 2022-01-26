package com.zkzn.les.panel.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StationPanelDao {

    List<Map<String, Object>> listStationPanel();
    /* *
     * @Author 刘松山
     * @Description 当天获取物料信息 工位信息
     * @Date 17:28 2021/3/23
     * @Param
     * @return
     **/

    List<Map<String, Object>> listCurrentDayInfo(String currentDay);
}

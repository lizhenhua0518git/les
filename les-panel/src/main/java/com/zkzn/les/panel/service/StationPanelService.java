package com.zkzn.les.panel.service;

import java.util.Map;

public interface StationPanelService {
    /* *
     * @Author 刘松山
     * @Description 获取配送列表
     * @Date 14:13 2021/3/12
     * @Param
     * @return
     **/

    Map<String, Object> listStationPanel();
    /* *
     * @Author 刘松山
     * @Description 当天获取物料信息 工位信息
     * @Date 17:28 2021/3/23
     * @Param
     * @return
     **/

    Map<String, Object> listCurrentDayInfo();
}

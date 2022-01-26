package com.zkzn.les.panel.service;

import com.zkzn.les.common.pojo.panel.InspectPanelVo;

import java.util.Map;

public interface InspectPanelService {

    Map<String, Object>  listInspectPanel();

    InspectPanelVo listOfInspectInfoPanel();
}

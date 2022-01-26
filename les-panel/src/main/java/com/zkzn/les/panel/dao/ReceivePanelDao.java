package com.zkzn.les.panel.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReceivePanelDao {

    List<Map<String, Object>> listReceivePanel();

    List<Map<String,Object>> listReceivePanelDetail(@Param("idList") List<String> idList);
}

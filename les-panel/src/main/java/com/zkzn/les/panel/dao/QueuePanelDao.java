package com.zkzn.les.panel.dao;


import com.zkzn.les.common.pojo.panel.QueuePanel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QueuePanelDao {

    List<QueuePanel> listQueuePanel(QueuePanel queuePanel);
}

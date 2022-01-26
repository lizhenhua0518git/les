package com.zkzn.les.panel.service.impl;

import com.zkzn.les.common.pojo.panel.QueuePanel;
import com.zkzn.les.panel.dao.QueuePanelDao;
import com.zkzn.les.panel.service.QueuePanelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class QueuePanelServiceImpl implements QueuePanelService {

    @Resource
    QueuePanelDao queuePanelDao;

    /**
     * 查询排队入场大屏信息
     * @param queuePanel
     * @return
     */
    @Override
    public List<QueuePanel> listQueuePanel(QueuePanel queuePanel) {
        List<QueuePanel> queuePanels = queuePanelDao.listQueuePanel(queuePanel);
        DateFormat df = new SimpleDateFormat(" '%Y%m%d HH:mm");
        for (QueuePanel panel : queuePanels) {
           // panel.setArriveTime(df.format(panel.getCreateTime()));
            int queueNum = Integer.parseInt(panel.getQueueNum());
            if(queueNum>=100){
                panel.setQueueCode(panel.getQueueCode()+"-"+queueNum);
            }else if(queueNum>=10){
                panel.setQueueCode(panel.getQueueCode()+"-0"+queueNum);
            }else{
                panel.setQueueCode(panel.getQueueCode()+"-00"+queueNum);
            }
        }
        return  queuePanels;
    }
}

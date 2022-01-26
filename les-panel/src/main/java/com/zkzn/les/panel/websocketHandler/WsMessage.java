package com.zkzn.les.panel.websocketHandler;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName WsMessage
 * @Description websocket 消息格式拟定
 * @Author zhanglei
 * Date 2021/1/8 17:41
 * @Version 1.0
 **/
public abstract class WsMessage implements Serializable {


    /**
     * 业务类型 1 排号 2 3 4
     */
    int busiType = 1 ;

    /**
     * 1 pc端 2 app
     */
    int device = 1;

    /**
     * 数据
     */
    Object data = null;

    /**
     * 数据推送的时间
     */
    String sendTime = LocalDateTime.now().toString();

    public int getBusiType() {
        return busiType;
    }

    public void setBusiType(int busiType) {
        this.busiType = busiType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }
}
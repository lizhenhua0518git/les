package com.zkzn.les.stock.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RabbitMqMessage
 * @Description rabbitMq队列消息推送类 业务类型 1-点收 2-质检 3-上架 4-库存状态转换
 * @Author zhanglei
 * Date 2021/7/6 10:44
 * @Version 1.0
 **/
public class RabbitMqMessage {

    public RabbitMqMessage(int busiType, String taskCode, String taskId) {
        this.busiType = busiType;
        this.taskCode = taskCode;
        this.taskId = taskId;
    }

    public RabbitMqMessage(String taskId){
        this.taskId = taskId;
    }

    public RabbitMqMessage(int busiType, String taskId){
        this.busiType = busiType;
        this.taskId = taskId;
    }
    public RabbitMqMessage(int busiType){
        this.busiType = busiType;
    }
    /***
     * @Discription: 业务类型 1-点收 2-质检 3-上架
     */
    private int busiType = 1;

    /***
     * @Discription: 任务编号
     */
    private String taskCode;

    /***
     * @Discription: 任务主表id
     */
    private String taskId;

    /***
     * @Discription: 数据推送时间
     */
    private String sendDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    private Map<String,Object> data;

    public int getBusiType() {
        return busiType;
    }

    public void setBusiType(int busiType) {
        this.busiType = busiType;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }


    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }
}
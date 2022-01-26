package com.zkzn.les.wms.receiveRecord.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString(callSuper = true)
public class UpdateReceiveTaskPojo {

    private Integer receiveId;//点收主表id  前端参数
    private Integer receiveTaskId;//点收主表id
    private Integer receiveStatus;//点收状态
    private Date receiveStartTime;//点收开始时间
    private Date receiveEndTime;//点收结束时间
    private Date receiveTotalTime;//点收时长

    private Date updateTime;
    private Integer updateUserId;
    private String updateName;

}

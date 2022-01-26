package com.zkzn.les.wms.pojo.arrivalNotice;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName ArrivalNoticePojo
 * @Description 到货通知单包装类
 * @Author zhanglei
 * Date 2021/6/11 16:25
 * @Version 1.0
 **/
@Data
public class ArrivalNoticePojo extends ArrivalNotice{
    private String queueCode;
    private Date queueTime;
    private String timeValue;
    //卸货地点编码
    private String uploadPlatCode;
    //送货联系人
    private String driverName;
    //卸货地点id
    private String uploadPlatId;
    //排号任务id
    private String admissionId;
    private String arrivalNoticeId;
    private String uploadPlatName;
    private List<ArrivalNoticeDetails> arrivalNoticeDetailsList;
}
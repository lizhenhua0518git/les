package com.zkzn.les.wms.arrivalNotice.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class UserInfoPojo {

    private Integer userId;//用户id
    private String userName;//用户名称

}

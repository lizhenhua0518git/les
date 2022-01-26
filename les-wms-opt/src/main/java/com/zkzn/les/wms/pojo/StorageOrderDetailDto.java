package com.zkzn.les.wms.pojo;

import lombok.Data;

/**
 * @ClassName StorageOrderDetailDto
 * @Description TODO
 * @Author zhanglei
 * Date 2020/9/23 17:19
 * @Version 1.0
 **/
@Data
public class StorageOrderDetailDto extends StorageOrderDetail{
    /***
     * @Discription: STATION_DETAIL_ID 一对一
     * @param null
     * @return null
     * @Author: zhanglei on 2020/9/23 17:21
     */
    private MaterialStorage materialStorage;
}
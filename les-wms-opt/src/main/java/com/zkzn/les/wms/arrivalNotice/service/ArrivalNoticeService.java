package com.zkzn.les.wms.arrivalNotice.service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.wms.arrivalNotice.pojo.ArrivalNoticeDetailPojo;
import com.zkzn.les.wms.arrivalNotice.pojo.ArrivalNoticePojo;

import java.util.List;


public interface ArrivalNoticeService {

    /**
     * 送货单管理列表查询
     * @param arrivalNotice
     * @return
     */
    PageInfo<ArrivalNoticePojo> getArrivalNotice(ArrivalNoticePojo arrivalNotice);

    /**
     * 送货单详情管理列表查询
     * @param arrivalNoticeDetailPojo
     * @return
     */
    PageInfo<ArrivalNoticeDetailPojo> arrivalNoticeDetailList(ArrivalNoticeDetailPojo arrivalNoticeDetailPojo);

    int updateArrivalNoticeDetail(ArrivalNoticeDetailPojo arrivalNoticeDetailPojo);

    /**
     * 新增送货单数据
     * @param arrivalNoticePojo
     * @return
     */
    int arriveNoticeAdd(ArrivalNoticePojo arrivalNoticePojo);

    /**
     * 删除送货单数据
     * @param arrivalNoticeIds
     * @return
     */
    int deleteArriveNotice(List<Integer> arrivalNoticeIds);

    /**
     * 下发送货单数据
     * @param arrivalNoticeIds
     * @return
     */
    int issueArriveNotice(List<Integer> arrivalNoticeIds,Integer userId,String currentUserName);

    /**
     * 新增到货通知单详情
     * @param arrivalNoticeDetailPojo
     * @return
     */
    int arriveDetailAdd(ArrivalNoticeDetailPojo arrivalNoticeDetailPojo);

    /**
     * 删除送货单数据
     * @param arrivalDetailIds
     * @return
     */
    int deleteArriveDetail(List<Integer> arrivalDetailIds);

}

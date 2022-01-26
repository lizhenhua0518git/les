package com.zkzn.les.wms.arrivalNotice.dao;

import com.zkzn.les.wms.arrivalNotice.pojo.AddReceiveDataPojo;
import com.zkzn.les.wms.arrivalNotice.pojo.ArrivalNoticeDetailPojo;
import com.zkzn.les.wms.arrivalNotice.pojo.ArrivalNoticePojo;
import com.zkzn.les.wms.arrivalNotice.pojo.UserInfoPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArrivalNoticeDao {

    /**
     * 送货单管理列表查询
     * @param arrivalNotice
     * @return
     */
    List<ArrivalNoticePojo> listArrivalNotice(ArrivalNoticePojo arrivalNotice);

    /**
     * 送货单详情管理列表查询
     * @param arrivalNoticeDetailPojo
     * @return
     */
    List<ArrivalNoticeDetailPojo> arrivalNoticeDetailList(ArrivalNoticeDetailPojo arrivalNoticeDetailPojo);

    int updateArrivalNoticeDetail(ArrivalNoticeDetailPojo arrivalNoticeDetailPojo);

    /**
     * 新增送货单数据
     * @param arrivalNoticePojo
     * @return
     */
    int arriveNoticeAdd(ArrivalNoticePojo arrivalNoticePojo);

    /**
     * 删除送货单数据
     * @param list
     * @return
     */
    int deleteArriveNotice(List<Integer> list);

    /**
     * 删除送货单详情数据
     * @param list
     * @return
     */
    int deleteArriveDetail(List<Integer> list);

    /**
     * 下发送货单数据
     * @param list
     * @return
     */
    int issueArriveNotice(List<Integer> list);

    /**
     * 查询新增点收表信息
     * @param arrivalNoticeId
     * @return
     */
    List<AddReceiveDataPojo> getAddReceiveData(@Param("arrivalNoticeId") Integer arrivalNoticeId);

    /**
     * 新增送货单详情
     * @param arrivalNoticeDetailPojo
     * @return
     */
    int arriveDetailAdd(ArrivalNoticeDetailPojo arrivalNoticeDetailPojo);

    /**
     * 删除送货单详情数据
     * @param list
     * @return
     */
    int deleteDetailByIds(List<Integer> list);

    /**
     * 获取当前客户绑定的管理人员
     * @param clientManageId
     * @return
     */
    List<UserInfoPojo> getUserInfoById(@Param("clientManageId") Integer clientManageId);

}

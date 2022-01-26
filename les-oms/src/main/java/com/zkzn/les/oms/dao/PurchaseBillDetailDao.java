package com.zkzn.les.oms.dao;

import com.zkzn.les.common.pojo.ArrivalNoticeDetailPojo;
import com.zkzn.les.common.pojo.ArrivalNoticePojo;
import com.zkzn.les.common.pojo.ReceiveDetailRecord;
import com.zkzn.les.common.pojo.ReceiveRecord;
import com.zkzn.les.oms.pojo.PurchaseBillDetail;
import com.zkzn.les.oms.pojo.PurchaseBillRefund;
import com.zkzn.les.oms.pojo.vo.DetailList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**.
 *
 * @author luozhihong
 * @date 2020年9月27日
 * @Description 采购入库订单明细Dao
 */
@Mapper
public interface PurchaseBillDetailDao {
    int insert(PurchaseBillDetail record);
    List<PurchaseBillDetail> selectAll();
    /**
     * @param purchaseBillDetail
     * @return
     * @Author:luozhihong
     * @date:2020年9月27日
     * @Description:查询采购入库订单明细
     */
    List<PurchaseBillDetail> listPurchaseBillDetail(@Param("purchaseBillDetail") PurchaseBillDetail purchaseBillDetail);
    /**
     * @param purchaseBillRefund
     * @return
     * @Author:luozhihong
     * @date:2020年12月7日
     * @Description:分页查询退货采购入库订单明细
     */
    List<PurchaseBillRefund> listPurchaseBillDetailTH(@Param("purchaseBillRefund") PurchaseBillRefund purchaseBillRefund);


    /**
     * 到货通知单主表
     * @param arrPojo
     * @return
     */
    int saveArrMain(ArrivalNoticePojo arrPojo);

    /**
     * 入库任务单主表
     * @param recPojo
     * @return
     */
    int saveRecMain(ReceiveRecord recPojo);

    /**
     * 批量保存到货单详情表
     * @param arrList
     * @return
     */
    int saveArrDetailList(List<ArrivalNoticeDetailPojo> arrList);

    /**
     * 批量保存入库任务单详情表
     * @param recList
     * @return
     */
    int saveRecDetailList(List<ReceiveDetailRecord> recList);

    /**
     * 采购单点收数量信息更新
     * @param detailList
     * @return
     */
    int updateBatchDetails(@Param("detailList") List<DetailList> detailList);
}
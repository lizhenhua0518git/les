package com.zkzn.les.oms.dao;

import com.zkzn.les.oms.pojo.ProcessOrder;
import com.zkzn.les.oms.pojo.PurchaseBillRefund;
import org.apache.ibatis.annotations.Mapper;
import com.zkzn.les.oms.pojo.PurchaseBill;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**.
 *
 * @author luozhihong
 * @date 2020年9月27日
 * @Description 采购入库订单Dao
 */
@Mapper
public interface PurchaseBillDao {
    int insert(PurchaseBill record);
    List<PurchaseBill> selectAll();

    /**
     * @param purchaseBill
     * @return
     * @Author:luozhihong
     * @date:2020年9月27日
     * @Description:查询采购入库订单
     */
    List<PurchaseBill> listPurchaseBill(@Param("purchaseBill") PurchaseBill purchaseBill);


    /**
     * @param purchaseBillRefund
     * @return
     * @Author:luozhihong
     * @date:2020年12月2日
     * @Description:分页查询退货采购订单
     */
    List<PurchaseBillRefund> listPurchaseBillRefund(@Param("obj") PurchaseBillRefund purchaseBillRefund);
}
package com.zkzn.les.oms.service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.oms.pojo.ProcessOrder;
import com.zkzn.les.oms.pojo.PurchaseBill;
import com.zkzn.les.oms.pojo.PurchaseBillRefund;

import java.util.List;


/**.
 * 
 * @author luozhihong
 * @date 2020年9月27日
 * @Description : 采购入库订单 service
 */
public interface PurchaseBillService {

	
	/**
	 * @param purchaseBill
	 * @return
	 * @Author:luozhihong
	 * @date:2020年9月2日
	 * @Description:分页查询物料需求清单
	 */
	PageInfo<PurchaseBill> listPurchaseBill(PurchaseBill purchaseBill);

	/**
	 * @param purchaseBillRefund
	 * @return
	 * @Author:luozhihong
	 * @date:2020年12月2日
	 * @Description:分页查询退货采购订单
	 */
	PageInfo<PurchaseBillRefund> listPurchaseBillRefund(PurchaseBillRefund purchaseBillRefund);

}

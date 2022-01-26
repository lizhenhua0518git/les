package com.zkzn.les.oms.service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.oms.pojo.ProcessOrder;
import com.zkzn.les.oms.pojo.PurchaseBillDetail;
import com.zkzn.les.oms.pojo.PurchaseBillRefund;
import com.zkzn.les.oms.pojo.vo.PurchaseBillVO;

import java.util.List;
import java.util.Map;


/**.
 * 
 * @author luozhihong
 * @date 2020年9月27日
 * @Description : 采购入库订单明细 service
 */
public interface PurchaseBillDetailService {

	
	/**
	 * @param purchaseBillDetail
	 * @return
	 * @Author:luozhihong
	 * @date:2020年9月2日
	 * @Description:分页采购入库订单明细
	 */
	PageInfo<PurchaseBillDetail> listPurchaseBillDetail(PurchaseBillDetail purchaseBillDetail);
	/**
	 * @param purchaseBillRefund
	 * @return
	 * @Author:luozhihong
	 * @date:2020年12月7日
	 * @Description:分页查询退货采购入库订单明细
	 */
	PageInfo<PurchaseBillRefund> listPurchaseBillDetailTH(PurchaseBillRefund purchaseBillRefund);

	/**
	 * 保存采购订单-PC
	 * @param data 采购单数据
	 * @return
	 */
	String saveOrder(PurchaseBillVO data) throws Exception;
}

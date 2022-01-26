package com.zkzn.les.oms.service.impl;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.common.util.page.PageUtil;
import com.zkzn.les.oms.dao.PurchaseBillDao;
import com.zkzn.les.oms.pojo.PurchaseBill;
import com.zkzn.les.oms.pojo.PurchaseBillRefund;
import com.zkzn.les.oms.service.PurchaseBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@SuppressWarnings("unchecked")
@Service
public class PurchaseBillServiceImpl implements PurchaseBillService {

	@Autowired
	private PurchaseBillDao purchaseBillDao;

	/**
	 * @param purchaseBill
	 * @return
	 * @Author:luozhihong
	 * @date:2020年9月27日
	 * @Description:分页查询采购入库订单
	 */
	public PageInfo<PurchaseBill> listPurchaseBill(PurchaseBill purchaseBill){

		PageUtil.setPageParam(purchaseBill);
		reAssembleParam(purchaseBill);
		List<PurchaseBill> list = purchaseBillDao.listPurchaseBill(purchaseBill);
		PageInfo<PurchaseBill> pageInfo = new PageInfo<PurchaseBill>(list);
		return pageInfo;
	}

	/**
	 * @param purchaseBillRefund
	 * @return
	 * @Author:luozhihong
	 * @date:2020年12月2日
	 * @Description:分页查询退货采购订单
	 */
	public PageInfo<PurchaseBillRefund> listPurchaseBillRefund(PurchaseBillRefund purchaseBillRefund){

		PageUtil.setPageParam(purchaseBillRefund);
		List<PurchaseBillRefund> list = purchaseBillDao.listPurchaseBillRefund(purchaseBillRefund);
		PageInfo<PurchaseBillRefund> pageInfo = new PageInfo<PurchaseBillRefund>(list);
		return pageInfo;
	}

	/**
	 * @param purchaseBill
	 * @Author:luozhihong
	 * @date:2020年9月27日
	 * @Description:参数封装
	 */
	public void reAssembleParam(PurchaseBill purchaseBill) {


		String requiredTimeStr = purchaseBill.getRequiredTimeStr();//创建时间
		if (!"".equals(requiredTimeStr) && requiredTimeStr != null){
			String[] split = requiredTimeStr.split(" - ");
			purchaseBill.setStartRequiredTime(split[0]);
			purchaseBill.setEndRequiredTime(split[1]);
		}
	}



}

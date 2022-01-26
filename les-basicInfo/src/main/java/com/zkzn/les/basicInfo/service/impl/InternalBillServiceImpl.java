package com.zkzn.les.basicInfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.dao.InternalBillDao;
import com.zkzn.les.basicInfo.pojo.InternalBill;
import com.zkzn.les.basicInfo.service.InternalBillService;
import com.zkzn.les.basicInfo.util.PageUtil;


@Service
public class InternalBillServiceImpl implements InternalBillService{

	@Autowired
	private InternalBillDao internalBillDao;
	
	@Override
	public List<InternalBill> listInternalBill(InternalBill internalBill) {
		// TODO Auto-generated method stub
		return internalBillDao.listInternalBill(internalBill);
	}

	@Override
	public PageInfo<InternalBill> listInternalBillPage(InternalBill internalBill) {
		// TODO Auto-generated method stub
		
		PageUtil.setPageParam(internalBill);
		List<InternalBill> list = listInternalBill(internalBill);
		PageInfo<InternalBill> pageInfo = new PageInfo<InternalBill>(list);
		return pageInfo;
	}
	@Override
	public List<InternalBill> getInternalBillList(InternalBill internalBill) {
		// TODO Auto-generated method stub
		List<InternalBill> list = listInternalBill(internalBill);
		return list;
	}
}

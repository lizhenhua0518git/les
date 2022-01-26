package com.zkzn.les.oms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkzn.les.oms.dao.AssembleOrderDao;
import com.zkzn.les.oms.pojo.AssembleOrder;
import com.zkzn.les.oms.service.AssembleOrderService;


@Service
public class AssembleOrderServiceImpl implements AssembleOrderService {

	@Autowired
	private AssembleOrderDao assembleOrderDao;
	
	@Override
	public List<AssembleOrder> listAssembleOrder(List<String> storageList) {
		// TODO Auto-generated method stub
		return assembleOrderDao.listAssembleOrder(storageList);
	}

}

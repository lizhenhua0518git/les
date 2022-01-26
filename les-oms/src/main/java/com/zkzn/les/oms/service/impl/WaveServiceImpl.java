package com.zkzn.les.oms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkzn.les.oms.dao.WaveDao;
import com.zkzn.les.oms.pojo.Wave;
import com.zkzn.les.oms.service.WaveService;

@Service
public class WaveServiceImpl implements WaveService{

	@Autowired
	private WaveDao waveDao;
	
	@Override
	public List<Wave> listWave(List<String> storageLocation) {
		// TODO Auto-generated method stub
		return waveDao.listWave(storageLocation);
	}

}

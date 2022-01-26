package com.zkzn.les.basicInfo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.dao.UploadAddressDao;
import com.zkzn.les.basicInfo.pojo.UploadAddress;
import com.zkzn.les.basicInfo.service.UploadAddressService;
import com.zkzn.les.basicInfo.util.PageUtil;

@Service
public class UploadAddressServiceImpl implements UploadAddressService{

	@Autowired
	private UploadAddressDao uploadAddressDao;
	
	@Override
	public int saveUploadAddress(UploadAddress uploadAddress) {
		// TODO Auto-generated method stub
		
		UploadAddress tempUploadAddress = new UploadAddress();
		List<String> warehouseCodes = new ArrayList<>();
		warehouseCodes.add(uploadAddress.getWarehouseCode());
		tempUploadAddress.setWarehouseCodes(warehouseCodes);
		tempUploadAddress.setUploadCode(uploadAddress.getUploadCode());
		List<UploadAddress>  list = uploadAddressDao.listUploadAddress(tempUploadAddress);
		if(list!=null && list.size()>0){
			return -1;
		}
		return uploadAddressDao.saveUploadAddress(uploadAddress);
	}

	@Override
	public int deleteUploadAddress(List<String> ids) {
		// TODO Auto-generated method stub
		return uploadAddressDao.deleteUploadAddress(ids);
	}

	@Override
	public int updateUploadAddress(UploadAddress uploadAddress) {
		// TODO Auto-generated method stub
		UploadAddress tempUploadAddress = new UploadAddress();
		List<String> warehouseCodes = new ArrayList<>();
		warehouseCodes.add(uploadAddress.getWarehouseCode());
		tempUploadAddress.setWarehouseCodes(warehouseCodes);
		tempUploadAddress.setUploadCode(uploadAddress.getUploadCode());
		List<UploadAddress>  list = uploadAddressDao.listUploadAddress(tempUploadAddress);
		if(list!=null ){
			for(UploadAddress temp:list){
				if(!temp.getId().equals(uploadAddress.getId()) && temp.getUploadCode().equals(uploadAddress.getUploadCode())){
					return -1;
				}
			}
			
		}
		return uploadAddressDao.updateUploadAddress(uploadAddress);
	}

	@Override
	public List<UploadAddress> listUploadAddress(UploadAddress uploadAddress) {
		// TODO Auto-generated method stub
		
		String warehouseCode = uploadAddress.getWarehouseCode();
		if(warehouseCode!=null && warehouseCode.length()>0){
			String [] tempArrays = warehouseCode.split(";");
			List<String> warehouseCodes = new ArrayList<String>();
			for(String str :tempArrays){
				warehouseCodes.add(str);
			}
			uploadAddress.setWarehouseCodes(warehouseCodes);	
		}
		List<UploadAddress> list = uploadAddressDao.listUploadAddress(uploadAddress);
		
		return list;
	}

	@Override
	public PageInfo<UploadAddress> listUploadAddressPage(UploadAddress uploadAddress) {
		// TODO Auto-generated method stub
		
		PageUtil.setPageParam(uploadAddress);
		List<UploadAddress> list =  listUploadAddress(uploadAddress);
		
		PageInfo<UploadAddress> pageInfo = new PageInfo<UploadAddress>(list);
		
		return pageInfo;
	}

}

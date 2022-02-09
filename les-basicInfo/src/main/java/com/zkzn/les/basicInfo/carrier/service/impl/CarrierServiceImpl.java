package com.zkzn.les.basicInfo.carrier.service.impl;

import com.zkzn.les.basicInfo.carrier.dao.CarrierDao;
import com.zkzn.les.basicInfo.carrier.pojo.CarrierPojo;
import com.zkzn.les.basicInfo.carrier.service.CarrierService;
import com.zkzn.les.basicInfo.pojo.Carrier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class CarrierServiceImpl implements CarrierService{

	@Autowired
	private CarrierDao carrierDao;

	@Override
	public List<CarrierPojo> listCarrierByCarrier(CarrierPojo carrierPojo) {
		List<CarrierPojo> list = carrierDao.listCarrierByCarrier(carrierPojo);
		return list;
	}

	@Override
	public int saveCarrier(CarrierPojo carrierPojo) {
		int number = carrierDao.checkCarrierByCode(carrierPojo.getCarrierCode(),carrierPojo.getWarehouseCode());
		if (number>0){
			return 1;
		}else {
			carrierDao.saveCarrier(carrierPojo);
		}
		return 0;
	}

	@Override
	public int deleteCarrier(List<Integer> carrierIds) {
		return carrierDao.deleteCarrier(carrierIds);
	}

	@Override
	public int updateCarrier(CarrierPojo carrierPojo) {
		return carrierDao.updateCarrier(carrierPojo);
	}






























    @Override
    public int changeStatus(List<String> ids, String remark, int status,String modifierId,String modifierName,Date modifiedTime) {
        return carrierDao.changeStatus(ids,remark,status,modifierId,modifierName,modifiedTime);
    }

	@Override
	public List<Carrier> listCarrier(String carrierType, int status, int userStatus, List<String> storageList) {
		// TODO Auto-generated method stub
		return carrierDao.listCarrier(carrierType, status, userStatus, storageList);
	}

	@Override
	public int updateUseStatusByIds(Map<String,Object> params) {
		// TODO Auto-generated method stub
		return carrierDao.updateUseStatusByIds(params);
	}
	@Override
	public List<Carrier> listCarrierInfo(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return carrierDao.listCarrierInfo(params);
	}

    @Override
    public Boolean checkCarrierByCode(Map<String,Object> map, String warehouse) {
        Boolean status = false;
        String carrierCode = map.get("carrierCode")+"";//载具号
        Integer number = carrierDao.checkCarrierByCode(carrierCode,warehouse);
        if (number == 0){
            status = true;
        }
        return status;
    }

    @Override
	public int updateUseStatusByCode(List<Map<String,Object>> map) {

		return carrierDao.updateUseStatusByCode(map);
	}

	@Override
	public List<Map<String, Object>> listCarrierCreater() {
		// TODO Auto-generated method stub
		return carrierDao.listCarrierCreater();
	}

}

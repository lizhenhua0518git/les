package com.zkzn.les.basicInfo.storagePosition.service.impl;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.pojo.Area;
import com.zkzn.les.basicInfo.pojo.StoragePosition;
import com.zkzn.les.basicInfo.pojo.WorkArea;
import com.zkzn.les.basicInfo.storagePosition.dao.StoragePositionDao;
import com.zkzn.les.basicInfo.storagePosition.pojo.StoragePositionPojo;
import com.zkzn.les.basicInfo.storagePosition.service.StoragePositionService;
import com.zkzn.les.basicInfo.util.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**.
 *
 * 功能描述：储位信息service实现类
 * @author wangzhou
 * 时间：2018年7月4日
 */
@Service
public class StoragePositionServiceImpl implements StoragePositionService{

	@Resource
	private StoragePositionDao storagePositionDao;

	@Override
	public List<StoragePositionPojo> listStoragePosition(StoragePositionPojo storagePositionPojo) {
		List<StoragePositionPojo> storageList = storagePositionDao.listStoragePosition(storagePositionPojo);
		return storageList;
	}

	@Override
	public int saveStoragePosition(StoragePositionPojo storagePositionPojo) {
		int checkNumber = storagePositionDao.checkStoragePosition(storagePositionPojo);
		if (checkNumber>0){
			return 1;
		}else {
			storagePositionDao.saveStoragePosition(storagePositionPojo);
		}
		return 0;
	}

	@Override
	public int updateStoragePosition(StoragePositionPojo storagePositionPojo) {
		return storagePositionDao.updateStoragePosition(storagePositionPojo);
	}

	@Override
	public int deleteStoragePosition(List<Integer> storagePositionIds) {
		return storagePositionDao.deleteStoragePosition(storagePositionIds);
	}














	@Override
	public StoragePosition getStorageById(String id) {
		return storagePositionDao.queryStorageById(id);
	}

	@Override
	public List<StoragePosition> queryStorageByIdStationCode(StoragePosition storagePosition) {
		return storagePositionDao.queryStorageByIdStationCode(storagePosition);
	}

	/**
	 * 查询工位信息
	 * yzn
	 * 2018-09-28
	 */
	@Override
	public PageInfo<StoragePosition> getStationCodeList(StoragePosition storagePosition) {
	    PageUtil.setPageParam(storagePosition);
		List<StoragePosition> stationList = storagePositionDao.getStationCodeList(storagePosition);
		PageInfo<StoragePosition> storagePage = new PageInfo<StoragePosition>(stationList);
		return storagePage;
	}

	@Override
	public List<StoragePosition> queryStorage(StoragePosition storagePosition) {
		return storagePositionDao.queryStorage(storagePosition);
	}

	@Override
	public List<StoragePosition> findByMaterialCodeAndFactory(Map<String, Object> map) {
		return storagePositionDao.findByMaterialCodeAndFactory(map);
	}

	@Override
	public List<StoragePosition> getPosition(StoragePosition storagePosition) {
		return storagePositionDao.getPosition(storagePosition);
	}

	@Override
	public List<Map<String, Object>> getStorage(StoragePosition storagePosition) {
		return storagePositionDao.getStorage(storagePosition);
	}

	@Override
	public List<Map<String, Object>> getStorageBinId(StoragePosition storagePosition) {
		return storagePositionDao.getStorageBinId(storagePosition);
	}

	@Override
	public List<Map<String, Object>> queryStorageBin(List<String> list,String warehouse,String orderType) {
		return storagePositionDao.queryStorageBin(list,warehouse,orderType);
	}

	@Override
	public List<Map<String, Object>> queryEmptyPosition(Map<String,Object> params) {
		return storagePositionDao.queryEmptyPosition(params);
	}

	@Override
	public StoragePosition queryPositionByCode(String warehouse, String positionCode) {
		return storagePositionDao.queryPositionByCode(warehouse, positionCode);
	}

	@Override
	public List<Map<String, Object>> queryStorageBinCount(List<String> list, String warehouse, String orderType) {
		return storagePositionDao.queryStorageBinCount(list,warehouse,orderType);
	}

    @Override
    public List<String> hasChildren(List<StoragePosition> originalStoragePositionList) {
        return storagePositionDao.hasChildren(originalStoragePositionList);
    }

    @Override
    public List<Map<String, Object>> formatPositionTree(List<StoragePosition> storagePositionList, List<String> storagePositionIds) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for(StoragePosition storagePosition : storagePositionList ) {
            Map<String, Object> result = new HashMap<String, Object>();
//            result.put("id", storagePosition.getId());
//            result.put("name", storagePosition.getPositionName());
//            result.put("pId", storagePosition.getParentId());
            result.put("nodeType","storage");
            result.put("code", storagePosition.getPositionCode());
            for(String positionId : storagePositionIds) {
//                if(positionId.equalsIgnoreCase(storagePosition.getId())){
//                    result.put("isParent", true);
//                    break;
//                }
            }
            resultList.add(result);
        }
        return resultList;
    }

    @Override
    public int changeStatus(List<String> ids, String remark, int status) {
        return storagePositionDao.changeStatus(ids, remark, status);
    }

    @Override
    public int importStorage(List<Map<String, Object>> areaMap) {
        return storagePositionDao.importStorage(areaMap);
    }

    @Override
    public int updateStorageBatch(List<Map<String, Object>> areaMap) {
        return storagePositionDao.updateStorageBatch(areaMap);
    }


	@Override
	public List<StoragePosition> getStoragePositionByCode(StoragePosition storagePosition) {
		List<StoragePosition> list = storagePositionDao.getStoragePositionByCode(storagePosition);
		return list;
	}

	@Override
	public List<String> getWareHouse() {
		List wareHouseList = storagePositionDao.getWareHouse();
		return wareHouseList;
	}

	@Override
	public List<StoragePosition> getStoragePosition() {
		List<StoragePosition> storagePositionList = storagePositionDao.getStoragePosition();
		return storagePositionList;
	}

	@Override
	public List<Area> getArea() {
		List<Area> areaList = storagePositionDao.getArea();
		return areaList;
	}

	@Override
	public List<WorkArea> getWorkArea() {
		List<WorkArea> workAreaList = storagePositionDao.getWorkArea();
		return workAreaList;
	}

	@Override
	public int updateStorageositionList(List<StoragePosition> storagePositionList) {
		return storagePositionDao.updateStoragePositionList(storagePositionList);
	}

	@Override
	public int saveStoragePositionList(List<StoragePosition> storagePositionList) {
		return storagePositionDao.saveStoragePositionList(storagePositionList);
	}

}

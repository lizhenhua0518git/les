package com.zkzn.les.basicInfo.warehouse.service.impl;

import com.zkzn.les.basicInfo.pojo.DictItems;
import com.zkzn.les.basicInfo.service.DictItemsService;
import com.zkzn.les.basicInfo.warehouse.dao.WarehouseDao;
import com.zkzn.les.basicInfo.warehouse.pojo.Warehouse;
import com.zkzn.les.basicInfo.warehouse.pojo.WarehousePojo;
import com.zkzn.les.basicInfo.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * .
 *
 * 功能描述:仓库业务实现层
 *
 * 时间:  2020-08-03 08:36
 *
 * @author  刘松山
 *
 */
@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseDao  warehouseDao;
	@Autowired
	private DictItemsService dictItemsService;


    @Override
    public List<WarehousePojo> initWarehouseSelect(WarehousePojo warehousePojo) {
        List<WarehousePojo> warehousePojoList = warehouseDao.initWarehouseSelect(warehousePojo);
        return warehousePojoList;
    }

    @Override
    public int saveWarehouse(Warehouse warehouse) {
    	int count = warehouseDao.countWarehouse(warehouse);
    	if(count >0) {
    		return  -10;
    	}else {
    		return warehouseDao.saveWarehouse(warehouse);
    	}

    }

    @Override
    public int deleteWarehouse(List<String> id) {
        return warehouseDao.deleteWarehouse(id);
    }

    @Override
    public int updateWarehouse(Warehouse warehouse) {
        return warehouseDao.updateWarehouse(warehouse);
    }

    /**
     * .
     *
     * 重载方法
     *
     * 功能描述:查询仓库列表
     *
     * @param warehouse
     * @return
     * @author  刘松山
     *
     * 时间:  2020-08-03 08:37
     */
    @Override
    public List<Warehouse> listWarehouse(Warehouse warehouse) {
        List<Warehouse> list= warehouseDao.listWarehouse(warehouse);

        List<String> dicts = new ArrayList<String>();
		dicts.add("enable");//启用禁用状态
		dicts.add("warehouse_type");//仓库类型

		List<DictItems> dictItems = dictItemsService.listDictItemsByType(dicts);

		if(list!=null && dictItems!=null ){
			for(Warehouse tempWarehouse:list){
				for(DictItems dictItem:dictItems){
					if("enable".equals(dictItem.getDictTypeId()) && dictItem.getItemValue().equals(""+tempWarehouse.getStatus()) ){
						tempWarehouse.setStatusName(dictItem.getItemName());
					}
					if("warehouse_type".equals(dictItem.getDictTypeId()) && dictItem.getItemValue().equals(""+tempWarehouse.getOrgType()) ){
						tempWarehouse.setOrgTypeName(dictItem.getItemName());
					}

				}
			}
		}
        return list;
    }


    /**
     * .
     *
     * 重载方法
     *
     * 功能描述:获取某个仓库信息
     *
     * @param id
     * @return
     * @author  刘松山
     *
     * 时间:  2020-08-03 08:39
     */
    @Override
    public Warehouse getWarehouseById(String id) {
      return warehouseDao.getWarehouseById(id);
    }

    /**
     * .
     *
     * 重载方法
     *
     * 功能描述:修改仓库状态
     *
     * @param ids
     * @param status
     * @param currUid
     * @param modifiedTime
     * @return
     * @author  刘松山
     *
     * 时间:  2020-08-03 08:40
     */
    @Override
    public int changeStatus(List<String> ids,   int status,String remark,String currUid,String currUName, Date modifiedTime) {
        return warehouseDao.changeStatus(ids, status,remark,currUid,currUName,modifiedTime);
    }





}

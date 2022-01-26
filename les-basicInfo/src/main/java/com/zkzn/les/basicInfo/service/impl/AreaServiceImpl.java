package com.zkzn.les.basicInfo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.dao.AreaDao;
import com.zkzn.les.basicInfo.service.AreaService;
import com.zkzn.les.basicInfo.service.DictItemsService;
import com.zkzn.les.basicInfo.util.PageUtil;
import com.zkzn.les.basicInfo.util.SecurityUserUtil;
import com.zkzn.les.basicInfo.warehouse.pojo.Warehouse;
import com.zkzn.les.basicInfo.pojo.Area;
import com.zkzn.les.basicInfo.pojo.DictItems;

import javax.annotation.Resource;


/**.
 *
 * 功能描述：区域信息service实现类
 * @author wangzhou
 * 时间：2018年7月3日
 */
@Service
public class AreaServiceImpl implements AreaService{

	@Resource
	private AreaDao areaDao;
    @Autowired
    private RedisTemplate<String ,String> redisTemplate;
    @Autowired
    private DictItemsService dictItemsService;
	
	@Override
	public int saveArea(Area area) {
		return areaDao.saveArea(area);
	}

	@Override
	public int deleteArea(List<String> id) {
		return areaDao.deleteArea(id);
	}

	@Override
	public int updateArea(Area area) {
		return areaDao.updateArea(area);
	}

	@Override
	public PageInfo<Area> listAreaByPage(Area area) {
	    PageUtil.setPageParam(area);
		List<Area> areaList = listArea(area);
		PageInfo<Area> areaPage = new PageInfo<Area>(areaList);
		return areaPage;
	}

	@Override
	public Area getAreaByCode(String areaCode, String warehouse) {
		return areaDao.getAreaByCode(areaCode, warehouse);
	}

    @Override
    public List<Area> listArea(Area area) {
    	
    	List<Area> areaList = areaDao.listArea(area);
    	
    	List<String> dictItems = new ArrayList<String>();
    	dictItems.add("area_type");
    	List<DictItems> dictItemList = dictItemsService.listDictItemsByType(dictItems);
    	if(areaList!=null && dictItemList!=null){
    		for(Area tempArea:areaList){
    			for(DictItems dictItem:dictItemList){
    				if(tempArea.getAreaType().equals(dictItem.getItemValue())){
    					tempArea.setAreaTypeName(dictItem.getItemName());
    				}
    			}
    		}
    	}
        return areaList;
    }
    
    @Override
    public int changeStatus(List<String> ids, String remark, int status,String modifier,Date modifiedTime) {
        return areaDao.changeStatus(ids,remark,status,modifier,modifiedTime);
    }

	public  List<Map<String, Object>> formatAreaTree(List<Area> areaList, List<String> areaIds ){
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (Area area : areaList) {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("id", area.getId());
            result.put("name", area.getAreaName());
            result.put("level", area.getAreaLevel()+1);
            result.put("pId", area.getParentId());
            result.put("nodeType","area");
            result.put("code", area.getAreaCode());
            for(String areaId : areaIds) {
                if(areaId.equalsIgnoreCase(area.getId())) {
                    result.put("isParent", true);
                    break;
                }
            }
           
            resultList.add(result);
        }
        return resultList;
	}

    @Override
    public List<String> getWarehouse() {
        return areaDao.getWarehouse();
    }

    @Override
    public List<Map<String, Object>> formatTreeRoot(List<Warehouse> originalWarehouseList, List<String> warehouseCodes) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for(Warehouse warehouse : originalWarehouseList ) {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("id", warehouse.getOrgCode());
            result.put("name", warehouse.getOrgName());
            result.put("level", "0");
            result.put("pId", "ROOT");
            result.put("nodeType","warehouse");
            result.put("code", warehouse.getOrgCode());
            for(String warehouseCode : warehouseCodes) {
                if(warehouseCode.equalsIgnoreCase(warehouse.getOrgCode())){
                    result.put("isParent", true);
                    break;
                }
            }
            resultList.add(result);
        }
        return resultList;
    }

    /**
     * .
     * 功能描述:查询有子区域的区域ID
     * 作者:何闰平
     * 时间:2020年4月2日 下午1:52:57
     * @param originalStoragePositionList
     * @return
     */
    @Override
    public List<String> hasChildren(List<Area> originalAreaList) {
        List<String> areaIds = areaDao.hasChildren(originalAreaList);
        return areaIds;
    }

}

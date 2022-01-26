package com.zkzn.les.stock.service.impl;

import com.zkzn.les.stock.dao.MaterialInStockDao;
import com.zkzn.les.stock.pojo.MaterialStorageBin;
import com.zkzn.les.stock.service.MaterialInStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MaterialInStockServiceImpl implements MaterialInStockService {

    @Autowired
    private MaterialInStockDao materialInStockDao;

    @Override
    @Transactional
    public void updateMaterialInStockList(List<Map<String, Object>> param) {
        for (int i = 0; i < param.size(); i++) {
            Map<String, Object> map = param.get(i);
            String materialDesc = map.get("materialDesc")+"";
            String clientName = map.get("clientName")+"";
            String batchNo = map.get("batchNo")+"";
            String warehouseCode = map.get("warehouseCode")+"";
            Integer qualifiedType = Integer.parseInt(map.get("qualifiedType")+"");
            Integer materialInStockId = materialInStockDao.getMaterialInStock(materialDesc,clientName,batchNo,warehouseCode,qualifiedType);
            if (materialInStockId != null){
                map.put("materialInStockId",materialInStockId);
                materialInStockDao.updateMaterialInStock(map);
                param.remove(i);
            }
        }
        if (param.size()>0){
            materialInStockDao.addMaterialInStockList(param);
        }
    }

    @Override
    public void updateMaterialStorageBin(List<Map<String, Object>> param) {
        List<Map<String,Object>> addList = new ArrayList<>();
        List<Map<String,Object>> updateList = new ArrayList<>();
        for (int i = 0; i < param.size(); i++) {
            Map<String, Object> map = param.get(i);
            int positionStatus = Integer.parseInt(map.get("positionStatus") + "");
            if (positionStatus != 2){//新增
                addList.add(map);
            }else {
                updateList.add(map);
            }
        }
        if (addList.size()>0){
            materialInStockDao.addMaterialStorageBin(addList);
        }
        if (updateList.size()>0){
            for (int i = 0; i < updateList.size(); i++) {
                materialInStockDao.updateMaterialStorageBin(updateList.get(i));
            }
        }
    }

    @Override
    public void minusStorageBinStock(Map<String, Object> param) {
        materialInStockDao.minusStorageBinStock(param);
    }

    @Override
    public void reducesMaterialInStock(List<Map<String, Object>> param) {
        if (param.size()>0){
            for (int i = 0; i < param.size(); i++) {
                materialInStockDao.reducesMaterialInStock(param.get(i));
            }
        }

    }

    @Override
    public void storeBinStockStatusChange(Map<String, Object> param) {
        materialInStockDao.storeBinStockStatusChange(param);
    }

    @Override
    @Transactional
    public int storeBinStockTypeChange(MaterialStorageBin materialStorageBin) {
        String warehouseCode = materialStorageBin.getWarehouseCode();
        Integer storageBinId = materialStorageBin.getStorageBinId();
        double stockCount = materialStorageBin.getStockCount();
        double numberOfMoves = materialStorageBin.getNumberOfMoves();
        Double frozenCount = materialStorageBin.getFrozenCount();
        Double preUseCount = materialStorageBin.getPreUseCount();
        String positionCarCode = materialStorageBin.getPositionCarCode();
        int sumCount = materialInStockDao.getCarCode(warehouseCode,positionCarCode);
        if (sumCount < 1){
            return 2;
        }
        int storagePositionId = materialInStockDao.getPositionCode(materialStorageBin);
        if (storagePositionId < 1){
            return 1;
        }
        Map<String, Object> positionData = materialInStockDao.getPositionData(materialStorageBin);
        //处理原有仓位库存
        if (numberOfMoves==stockCount&&frozenCount == 0D&&preUseCount == 0D){
            materialInStockDao.deleteMaterialStorageBinById(storageBinId);
        }else {
            materialInStockDao.updateMaterialStorageBinById(storageBinId,stockCount-numberOfMoves);
        }
        //处理转移后的仓位库存
        //if (positionData.get("storageBinId")!= null&&!"".equals(positionData.get("storageBinId"))){
        if (positionData != null){
            Double sum = Double.parseDouble(positionData.get("stockCount")+"")+numberOfMoves;
            materialInStockDao.updateMaterialStorageBinById(Integer.parseInt(positionData.get("storageBinId")+""),sum);
        }else {
            materialStorageBin.setStoragePositionId(storagePositionId);
            materialStorageBin.setStockCount(numberOfMoves);
            materialInStockDao.addMaterialStorageBinPojo(materialStorageBin);
        }
        return 0;
    }
}

package com.zkzn.les.stock.service;

import com.zkzn.les.stock.pojo.MaterialStorageBin;

import java.util.List;
import java.util.Map;

public interface MaterialInStockService {

    void updateMaterialStorageBin(List<Map<String, Object>> param);

    void storeBinStockStatusChange(Map<String, Object> param);

    int storeBinStockTypeChange(MaterialStorageBin materialStorageBin);
}

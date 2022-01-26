package com.zkzn.les.stock.dao;

import com.zkzn.les.stock.pojo.MaterialStorageBin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MaterialInStockDao {

    void addMaterialInStockList(List<Map<String, Object>> param);

    void updateMaterialInStock(Map<String, Object> param);

    Integer getMaterialInStock(@Param("materialDesc") String materialDesc, @Param("clientName") String clientName,@Param("batchNo") String batchNo,@Param("warehouseCode") String warehouseCode,@Param("qualifiedType") Integer qualifiedType);

    void addMaterialStorageBin(@Param("addList") List<Map<String,Object>> addList);

    void updateMaterialStorageBin(Map<String,Object> map);

    void minusStorageBinStock(Map<String, Object> param);

    void reducesMaterialInStock(Map<String, Object> param);

    void storeBinStockStatusChange(Map<String, Object> param);

    Map<String,Object> getPositionData(MaterialStorageBin materialStorageBin);

    /**
     * 校验仓位
     * @param materialStorageBin
     * @return
     */
    int getPositionCode(MaterialStorageBin materialStorageBin);

    /**
     * 校验载具
     * @param warehouseCode
     * @param positionCarCode
     * @return
     */
    int getCarCode(@Param("warehouseCode") String warehouseCode,@Param("positionCarCode") String positionCarCode);

    void updateMaterialStorageBinById(@Param("storageBinId") Integer storageBinId,@Param("stockCount") Double stockCount);

    void addMaterialStorageBinPojo(MaterialStorageBin materialStorageBin);

    void deleteMaterialStorageBinById(@Param("storageBinId") Integer storageBinId);
}

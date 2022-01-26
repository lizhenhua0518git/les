package com.zkzn.les.tactics.service.impl;

import com.zkzn.les.common.exception.EmptyExamine;
import com.zkzn.les.tactics.constant.TacticsConstants;
import com.zkzn.les.tactics.dao.StorageStrategyDao;
import com.zkzn.les.tactics.dao.UploadStrategyDao;
import com.zkzn.les.tactics.factory.storageStrategy.DefaultStorageStrategy;
import com.zkzn.les.tactics.factory.storageStrategy.StorageStrategyContext;
import com.zkzn.les.tactics.factory.uploadStrategy.DefaultUploadStrategy;
import com.zkzn.les.tactics.factory.uploadStrategy.UploadPlatStrategyContext;
import com.zkzn.les.tactics.service.StorageStrategyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName StorageStrategyServiceImpl
 * @Description  上架策略获取仓位
 * @Author zhanglei
 * Date 2021/7/2 17:00
 * @Version 1.0
 **/
@Service
public class StorageStrategyServiceImpl implements StorageStrategyService {
   private static Logger LOGGER = LoggerFactory.getLogger(StorageStrategyServiceImpl.class);
    @Autowired
   private  StorageStrategyDao storageStrategyDao;

    @Autowired
    private UploadStrategyDao uploadStrategyDao;
    /***
     * @Discription: 上架策略获取仓位信息
     *            1.上架策略：物料 供应商 工位 可一起存放
     * @param param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @Author: zhanglei on 2021/7/2 17:10
     */
    @Override
    public List<Map<String, Object>> getStorageStrategyResult(Map<String, Object> param) throws Exception{
        // 必传参数校验  物料号 materialCode 供应商编码 shipperCode 工位编码 stationCode 库存地点 storageLocation
        EmptyExamine.examine(param,TacticsConstants.STRATEGY_TYPE,TacticsConstants.MATERIAL_CODE, TacticsConstants.SHIPPER_CODE,TacticsConstants.STATION_CODE,TacticsConstants.STORAGE_LOCATION);
        Map<String, Object> storageStrategy = uploadStrategyDao.listStrategyInfo(param);
        String strategyType = TacticsConstants.DEFAULT;
        if (null !=storageStrategy && !storageStrategy.isEmpty()) {
            //没有启用策略
            strategyType = storageStrategy.get(TacticsConstants.STRATEGY_TYPE).toString();
        }
        StorageStrategyContext storageStrategyContext = null;
        switch (strategyType){
            case "":
            default:
                storageStrategyContext = new StorageStrategyContext(new DefaultStorageStrategy());
        }
        List<Map<String, Object>> strategyResult = storageStrategyContext.getStrategyResult(param);
        return strategyResult;
    }

//    @Override
//    public List<Map<String, Object>> getStorageCode(Map<String, Object> param) {
//        List<Map<String,Object>> storageList = storageStrategyDao.getStorageCodeByData(param);
//        if (!(storageList.size()>0)){
//            storageList = storageStrategyDao.getNullStorage(param);
//            if (storageList.size()>0){
//                Map<String, Object> map = storageList.get(0);
//                storageStrategyDao.updateOccupyStatus(map);
//            }
//        }
//        return storageList;
//    }

    @Override
    public List<Map<String, Object>> getStorageCode(Map<String, Object> param) {
        List<Map<String,Object>> storageList = storageStrategyDao.getStorageCodeByData(param);
        if (!(storageList.size()>0)){
            storageList = storageStrategyDao.getNullStorage(param);
//            if (storageList.size()>0){
//                Map<String, Object> map = storageList.get(0);
//                storageStrategyDao.updateOccupyStatus(map);
//            }
        }
        return storageList;
    }
}

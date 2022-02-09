package com.zkzn.les.tactics.factory.storageStrategy;

import com.google.common.collect.Lists;
import com.zkzn.les.common.util.lang.SpringUtil;
import com.zkzn.les.tactics.constant.TacticsConstants;
import com.zkzn.les.tactics.dao.StorageStrategyDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @ClassName DefaultaStorageStrategy
 * @Description 默认上架策略
 * @Author leizhang
 * Date 2021/7/2 10:30 下午
 * @Version 1.0
 **/
public class DefaultStorageStrategy extends StorageStrategyAbstract {

    private static Logger LOGGER = LoggerFactory.getLogger(DefaultStorageStrategy.class);
    private StorageStrategyDao storageStrategyDao = SpringUtil.getBean(StorageStrategyDao.class);

    /**
     * @Description : 默认获取上架策略信息
     * @Author : leizhang
     * @Date 10:31 下午 2021/7/2
     * @param param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @Override
    public List<Map<String, Object>> getStrategyResult(Map<String, Object> param) {
        List<Map<String, Object>> storageInfo = storageStrategyDao.getPositionByWarehouseCode(param);
        if (!storageInfo.isEmpty()) {
            //如果不为空则说明存在数据,TODO 需要判断最大使用重量
           return storageInfo;
        }else {
            //返回一个空的仓位信息
            List<Map<String, Object>> positions = storageStrategyDao.getPositions(param);
            List<Map<String,Object>> result = Lists.newArrayList();
            result.add(positions.get(TacticsConstants.NUMBER_0));
            return result;
        }
    }
}

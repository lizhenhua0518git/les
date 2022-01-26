package com.zkzn.les.tactics.factory.uploadStrategy;

import com.zkzn.les.common.util.lang.SpringUtil;
import com.zkzn.les.tactics.config.UploadAddressQueue;
import com.zkzn.les.tactics.dao.UploadStrategyDao;
import com.zkzn.les.tactics.util.RedissonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @ClassName RandomStorageStrategy
 * @Description TODO
 * @Author zhanglei
 * Date 2021/7/5 15:41
 * @Version 1.0
 **/
public class RandomUploadStorageStrategy extends UploadPlatStrategyAbstract{

    private static Logger LOGGER = LoggerFactory.getLogger(DefaultUploadStrategy.class);

    private UploadStrategyDao uploadStrategyDao = SpringUtil.getBean(UploadStrategyDao.class);
    @Override
    public Map<String, Object> getStrategyResult(Map<String, Object> param) {

        List<Map<String, Object>> maps = uploadStrategyDao.listUploadAddress(param);

        return maps.get(new Random().nextInt(maps.size()));
    }
}
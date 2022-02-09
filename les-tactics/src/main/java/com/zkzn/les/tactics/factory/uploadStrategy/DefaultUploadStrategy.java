package com.zkzn.les.tactics.factory.uploadStrategy;

import com.google.common.collect.Maps;
import com.zkzn.les.common.util.lang.SpringUtil;
import com.zkzn.les.tactics.config.UploadAddressQueue;
import com.zkzn.les.tactics.util.RedissonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @ClassName DefaultUploadStrategy
 * @Description 默认卸货点获取方式
 * @Author zhanglei
 * Date 2021/6/25 17:29
 * @Version 1.0
 **/
public class DefaultUploadStrategy extends UploadPlatStrategyAbstract{

    private static Logger LOGGER = LoggerFactory.getLogger(DefaultUploadStrategy.class);

    private static final String UPLOAD_REDISSON_KEY = "upload_strategy";
    //获取daoBean
     private UploadAddressQueue uploadAddressQueue = SpringUtil.getBean(UploadAddressQueue.class);

     private RedissonUtil redissonUtil = SpringUtil.getBean(RedissonUtil.class);


    /***
     * @Discription: 默认卸货点信息获取
     *              1.获取数据库中最少使用的卸货点
     *              2.问题：并发情况下，存在获取的卸货点为同一卸货点（非分布式部署可使用jvm锁控制）
     *              3.使用分布式redisson锁控制卸货点信息的操作 保证多并发下访问的卸货点信息不会错乱
     *              4.项目启动加载卸货点列表到内存队列中，循环获取队列中的卸货点信息 TODO 需要优化
     * @param param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @Author: zhanglei on 2021/6/25 17:30
     */
    @Override
    public Map<String, Object> getStrategyResult(Map<String, Object> param) {
        Map<String,Object> uploadResultMap = Maps.newHashMap();
        try{
            LOGGER.info("============={} 线程访问开始============",Thread.currentThread().getName());
            //尝试获取锁，等待3秒，自己获得锁后一直不解锁则5秒后自动解锁
            //boolean lock = redissonUtil.tryLock(UPLOAD_REDISSON_KEY, TimeUnit.SECONDS, 7L, 3L);
//            if (lock) {
//                LOGGER.info("线程:{}，获取到了锁",Thread.currentThread().getName());
//                uploadResultMap= uploadAddressQueue.getQueueInfo();
//                LOGGER.info("=============================" + Thread.currentThread().getName());
//            }
        }catch (Exception e){
            LOGGER.info("错误信息：{}",e.toString());
            LOGGER.info("线程：{} 获取锁失败",Thread.currentThread().getName());
        }finally {
            //redissonUtil.unlock(UPLOAD_REDISSON_KEY);
        }
        return uploadResultMap;
    }
}

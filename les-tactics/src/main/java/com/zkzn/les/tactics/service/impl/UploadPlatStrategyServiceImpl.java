package com.zkzn.les.tactics.service.impl;

import com.zkzn.les.common.exception.EmptyExamine;
import com.zkzn.les.tactics.constant.TacticsConstants;
import com.zkzn.les.tactics.dao.UploadStrategyDao;
import com.zkzn.les.tactics.factory.uploadStrategy.DefaultUploadStrategy;
import com.zkzn.les.tactics.factory.uploadStrategy.RandomUploadStorageStrategy;
import com.zkzn.les.tactics.factory.uploadStrategy.UploadPlatStrategyContext;
import com.zkzn.les.tactics.service.UploadPlatStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName UploadPlatStrategyServiceImpl
 * @Author zhanglei
 * Date 2021/6/25 17:51
 * @Version 1.0
 **/
@Service
public class UploadPlatStrategyServiceImpl implements UploadPlatStrategyService {

    @Autowired
    private UploadStrategyDao uploadStrategyDao;
    /***
     * @Discription: 启动策略信息获取 by 策略类型（字典表类型）
     * @param param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @Author: zhanglei on 2021/6/25 17:53
     */
    @Override
    public Map<String, Object> listStrategyInfo(Map<String, Object> param) throws Exception {
        EmptyExamine.examine(param, TacticsConstants.STRATEGY_TYPE);
        return uploadStrategyDao.listStrategyInfo(param);
    }

    /***
     * @Discription: 卸货点策略卸货点信息获取
     * @param param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @Author: zhanglei on 2021/6/25 18:04
     */
    @Override
    public Map<String, Object> getUploadStrategyResult(Map<String, Object> param) throws Exception {
        //1.获取卸货点策略
        Map<String, Object> uploadStrategyMap = this.listStrategyInfo(param);
        //启用的策略类型
        String strategyType = TacticsConstants.DEFAULT;
        if ( null != uploadStrategyMap && !uploadStrategyMap.isEmpty()) {
            //没有启用策略
            strategyType = uploadStrategyMap.get(TacticsConstants.STRATEGY_TYPE).toString();
        }
        //策略执行者上下文
        UploadPlatStrategyContext uploadPlatStrategyContext = null;
        switch (strategyType){
            case "xhd_01":
                uploadPlatStrategyContext = new UploadPlatStrategyContext(new DefaultUploadStrategy());
                break;
            default:
                uploadPlatStrategyContext = new UploadPlatStrategyContext(new RandomUploadStorageStrategy());

        }
        Map<String, Object> strategyResult = uploadPlatStrategyContext.getStrategyResult(param);
        if (strategyResult.isEmpty()) {
            throw new Exception("卸货点策略执行结果为空");
        }
        return strategyResult;
    }
}
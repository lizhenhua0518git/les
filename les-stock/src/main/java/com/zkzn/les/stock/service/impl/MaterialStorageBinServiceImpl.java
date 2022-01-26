package com.zkzn.les.stock.service.impl;

import com.zkzn.les.common.exception.EmptyExamine;
import com.zkzn.les.common.util.json.JsonUtil;
import com.zkzn.les.common.util.rabbitMq.RabbitMqUtil;
import com.zkzn.les.stock.constants.StockConstants;
import com.zkzn.les.stock.dao.MaterialStorageBinDao;
import com.zkzn.les.stock.service.MaterialStorageBinService;
import com.zkzn.les.stock.util.RabbitMqMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName MaterialStorageBinServiceImpl
 * @Description TODO
 * @Author zhanglei
 * Date 2021/7/21 14:06
 * @Version 1.0
 **/
@Service
public class MaterialStorageBinServiceImpl implements MaterialStorageBinService {
    private static Logger LOGGER = LoggerFactory.getLogger(MaterialStorageBinServiceImpl.class);

    @Autowired
    private MaterialStorageBinDao materialStorageBinDao;

    @Override
    public void storeBinStockStatusChange(Map<String, Object> requestMap) throws Exception {
        //参数校验
        EmptyExamine.examine(requestMap, StockConstants.CHANG_LISTS,StockConstants.CHANGE_STATUS);
        List<Map<String,Object>> changLists =requestMap.get(StockConstants.CHANG_LISTS) instanceof List ?  (List)requestMap.get(StockConstants.CHANG_LISTS) : null;
        String changStatus = requestMap.get(StockConstants.CHANGE_STATUS).toString();
        try {
            //更新storageBin的
            materialStorageBinDao.updateMaterialStorageBinStatus(changLists, changStatus);
            //记录修改流水
            materialStorageBinDao.saveBinStorageBinRecord(changLists,changStatus);
            //sap过账
            RabbitMqMessage rabbitMqMessage = new RabbitMqMessage(StockConstants.NUM_4);
            rabbitMqMessage.setData(requestMap);
            RabbitMqUtil.INSTANCE.sendMessage(JsonUtil.getBeanToJson(rabbitMqMessage),StockConstants.SAP_QUEUE);
        }catch (Exception exception){
            LOGGER.error("库存转换异常信息：%s",exception.getMessage(),exception);
            throw  new Exception("库存转换异常信息");
        }
    }
}
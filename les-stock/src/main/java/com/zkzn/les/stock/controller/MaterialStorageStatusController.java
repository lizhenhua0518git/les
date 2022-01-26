package com.zkzn.les.stock.controller;

import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.stock.service.MaterialStorageBinService;
import com.zkzn.les.stock.service.impl.MaterialStorageBinServiceImpl;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName MaterialStorageStatus
 * @Description 库存状态
 * @Author zhanglei
 * Date 2021/7/21 14:02
 * @Version 1.0
 **/
@Api(description="仓位库存管理基础信息服务")
@RequestMapping(value="/materialStorageBin")
@RestController
public class MaterialStorageStatusController {
    private static Logger LOGGER = LoggerFactory.getLogger(MaterialStorageStatusController.class);
    @Autowired
    private MaterialStorageBinService materialStorageBinService;
    /***
     * @Discription: 库存状态转换
     * @param requestMap
     * @param request
     * @return java.lang.String
     * @Author: zhanglei on 2020/12/9 10:18
     */
    @PostMapping(value = "/storeBinStockStatusChange" , produces="application/json;charset=UTF-8")
    public String storeBinStockStatusChange(@RequestBody Map<String,Object> requestMap, HttpServletRequest request){
        String result = null;
        try{
            materialStorageBinService.storeBinStockStatusChange(requestMap);
            result = Result.toJson(Ecode.SUCCESS, "库存状态转换成功");
        }catch (Exception e){
            LOGGER.error("库存状态转换错误信息：%s",e.getMessage(),e);
            result =Result.toJson(Ecode.FAIL, "库存状态转换错误");
        }
        return result;
    }
}
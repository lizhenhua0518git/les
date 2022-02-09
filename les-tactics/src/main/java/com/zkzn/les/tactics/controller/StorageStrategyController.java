package com.zkzn.les.tactics.controller;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicParameters;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.tactics.constant.TacticsConstants;
import com.zkzn.les.tactics.service.StorageStrategyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName StorageStrategyController
 * @Description 仓位获取
 * @Author zhanglei
 * Date 2021/7/2 16:58
 * @Version 1.0
 **/
@Api(tags = "仓位策略获取")
@RestController
@RequestMapping("/storageStrategy")
public class StorageStrategyController {
    private static Logger LOGGER = LoggerFactory.getLogger(StorageStrategyController.class);

    @Autowired
    private StorageStrategyService storageStrategyService;

    @ApiOperation(value = "上架仓位信息获取")
    @DynamicParameters(name = "",properties = {
            @DynamicParameter(name = "strategyType",value = "上架策略策略编码(字典表中类型)",example = "xhd_strategy",required = true,dataTypeClass = String.class),
            @DynamicParameter(name = "materialCode",value = "物料编码",required = true,dataTypeClass = String.class),
            @DynamicParameter(name = "shipperCode",value = "供应商编码",required = true,dataTypeClass = String.class),
            @DynamicParameter(name = "storageLocation",value = "库存地点",required = true,dataTypeClass = String.class),
            @DynamicParameter(name = "stationCode",value = "工位编码",required = true,dataTypeClass = String.class)
    })
    @DynamicResponseParameters(name = "",properties = {
            @DynamicParameter(name = "positionCode",value = "仓位编码",dataTypeClass = String.class),
            @DynamicParameter(name = "positionName",value = "库位名称",dataTypeClass = String.class),
            @DynamicParameter(name = "stationCode",value = "工位编码",dataTypeClass = String.class),
            @DynamicParameter(name = "storageLocation",value = "库存地点",dataTypeClass = String.class)
    })
    @PostMapping(value = "/getStorageStrategyResult", produces = "application/json;charset=UTF-8")
    public String getUploadStrategyResult(@RequestBody Map<String, Object> param) {
        List<Map<String, Object>> result = null;
        try {
            param.putIfAbsent(TacticsConstants.STRATEGY_TYPE,TacticsConstants.UPPER_FRAME_STRATEGY);
            result = storageStrategyService.getStorageStrategyResult(param);
        } catch (Exception e) {
            LOGGER.error("卸货点策略调用结果信息获取失败:{}" , e.getMessage(),e);
            return Result.toJsonUseApp(Ecode.FAIL, e.getMessage());
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, result);
    }

    @PostMapping("/getStorageCode")
    public String getStorageCode(@RequestBody Map<String, Object> param) {
        List<Map<String,Object>> stringObjectMapList = null;
        try {
            stringObjectMapList = storageStrategyService.getStorageCode(param);
        } catch (Exception e) {
            LOGGER.error("查询仓位失败:{}", e.getMessage(), e);
            return Result.toJsonUseApp(Ecode.FAIL, "查询仓位失败");
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, stringObjectMapList);
    }
}

package com.zkzn.les.tactics.controller;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicParameters;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.tactics.service.UploadPlatStrategyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName UploadStrategyController
 * @Author zhanglei
 * Date 2021/6/25 17:56
 * @Version 1.0
 **/
@Api(tags = "卸货点策略")
@RestController
@RequestMapping("/uploadStrategy")
public class UploadStrategyController {
    private static Logger LOGGER = LoggerFactory.getLogger(UploadStrategyController.class);

    @Autowired
    private UploadPlatStrategyService uploadPlatStrategyService;

    @ApiOperation(value = "卸货点信息获取")
    @DynamicParameters(name = "",properties = {
            @DynamicParameter(name = "strategyType",value = "卸货点策略编码(字典表中类型)",example = "xhd_strategy",required = true,dataTypeClass = String.class)
    })
    @DynamicResponseParameters(name = "",properties = {
            @DynamicParameter(name = "uploadPlat",value = "卸货点编码",dataTypeClass = String.class),
            @DynamicParameter(name = "id",value = "卸货点id",dataTypeClass = Integer.class),
            @DynamicParameter(name = "status",value = "卸货点状态",dataTypeClass = Integer.class),
            @DynamicParameter(name = "uploadPlatName",value = "卸货点名称",dataTypeClass = String.class)
    })
    @PostMapping(value = "/getUploadStrategyResult", produces = "application/json;charset=UTF-8")
    public String getUploadStrategyResult(@RequestBody Map<String, Object> param) {
        Map<String, Object> result = null;
        try {
            result = uploadPlatStrategyService.getUploadStrategyResult(param);
        } catch (Exception e) {
            LOGGER.error("卸货点策略调用结果信息获取失败:{}" , e.getMessage(),e);
            return Result.toJsonUseApp(Ecode.FAIL, e.getMessage());
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, result);
    }


}
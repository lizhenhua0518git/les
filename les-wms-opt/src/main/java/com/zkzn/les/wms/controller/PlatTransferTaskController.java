package com.zkzn.les.wms.controller;

import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicParameters;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.zkzn.les.common.util.lang.SecurityUserUtil;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.wms.constant.WmsConstants;
import com.zkzn.les.wms.service.PlatTransferTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PlatTransferController
 * @Description 拆盘任务
 * @Author zhanglei
 * Date 2021/6/23 11:11
 * @Version 1.0
 **/
@Api(tags = "拆盘任务")
@RequestMapping("/platTransferTask")
@RestController
public class PlatTransferTaskController {
    private static Logger log = LoggerFactory.getLogger(PlatTransferTaskController.class);
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private PlatTransferTaskService platTransferTaskService;
    @ApiOperation(value = "拆盘主表详情")
    @DynamicResponseParameters(name = "",properties = {
            @DynamicParameter(name = "id",value = "拆盘任务主表",dataTypeClass = Integer.class),
            @DynamicParameter(name = "arrivalCode",value = "到货通知单编码"),
            @DynamicParameter(name = "createTime",value = "创建时间"),
            @DynamicParameter(name = "finishTime",value = "完成时间"),
            @DynamicParameter(name = "orderCode",value = "生成订单号"),
            @DynamicParameter(name = "inspectionTaskId",value = "质检任务id"),
            @DynamicParameter(name = "status",value = "状态 0-未拆盘 1-拆盘完成"),
            @DynamicParameter(name = "transferTaskCode",value = "拆盘任务号"),
            @DynamicParameter(name = "materialType",value = "物料种类")
    })
    @PostMapping(value = "/listTransferInfos", produces = "application/json;charset=UTF-8")
    public String listTransferInfo(Map<String,Object> param, HttpServletRequest request) {
        List<Map<String, Object>> list = null;
        try {
            param.put(WmsConstants.LT_STATUS,0);
            String userId = SecurityUserUtil.getCurrentUserId(request);
            String warehouseCode = SecurityUserUtil.getCurrOrgCode(userId, redisTemplate, "app");
            param.putIfAbsent(WmsConstants.WAREHOUSE_CODE,warehouseCode);
            list = platTransferTaskService.listTransferInfo(param);
        } catch (Exception e) {
            log.error("查询拆盘列表异常:{}" , e.getMessage(),e);
            return Result.toJsonUseApp(Ecode.FAIL, e.getMessage());
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, list);
    }

    @ApiOperation(value = "拆盘子表详情")
    @DynamicParameters(name = "",properties = {
            @DynamicParameter(name = "transferTaskId",value = "拆盘主表id",dataTypeClass = Integer.class)
    })
    @DynamicResponseParameters(name = "",properties = {
            @DynamicParameter(name = "transferDetailId",value = "拆盘任务子表Id",dataTypeClass = Integer.class),
            @DynamicParameter(name = "carrierCode",value = "原载具编码"),
            @DynamicParameter(name = "carrierId",value = "原载具id"),
            @DynamicParameter(name = "newCarrierId",value = "现载具id"),
            @DynamicParameter(name = "newCarrierCode",value = "现载具编码"),
            @DynamicParameter(name = "materialCode",value = "物料编码"),
            @DynamicParameter(name = "materialNum",value = "载具数量"),
            @DynamicParameter(name = "materialDesc",value = "物料描述"),
            @DynamicParameter(name = "materialUnit",value = "物料单位"),
            @DynamicParameter(name = "batchNo",value = "物料批次号"),
            @DynamicParameter(name = "inspectionDetailId",value = "质检表主表id"),
            @DynamicParameter(name = "inspectionTaskCode",value = "质检任务号"),
            @DynamicParameter(name = "transferCode",value = "拆盘任务号"),
            @DynamicParameter(name = "status",value = "状态"),
            @DynamicParameter(name = "transferTaskId",value = "拆盘主表Id"),
            @DynamicParameter(name = "isKey",value = "是否关键物料")
    })
    @PostMapping(value = "/listTransferDetailInfos", produces = "application/json;charset=UTF-8")
    public String listTransferDetailInfo(Map<String,Object> param) {
        List<Map<String, Object>> list = null;
        try {
            list = platTransferTaskService.listTransferDetailInfo(param);
        } catch (Exception e) {
            log.error("查询拆盘列表异常:{}",e.getMessage(),e);
            return Result.toJsonUseApp(Ecode.FAIL, e.getMessage());
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, list);
    }

    @ApiOperation(value = "拆盘任务提交")
    @DynamicParameters(name = "",properties = {
            @DynamicParameter(name = "carrierCode",value = "载具编码",dataTypeClass = Integer.class),
            @DynamicParameter(name = "transferNum",value = "拆盘数量"),
            @DynamicParameter(name = "transferDetailId",value = "拆盘任务子表id"),
            @DynamicParameter(name = "serialList",value = "[{\"serialNum\":\"\"}]")
    })
    @PostMapping(value = "/updatePlatTransferDetailInfo", produces = "application/json;charset=UTF-8")
    public String updatePlatTransferDetailInfo(@RequestBody Map<String,Object> param) {
        try {
             platTransferTaskService.updatePlatTransferDetailInfo(param);
        } catch (Exception e) {
            log.error("拆盘任务提交完成异常:{}" , e.getMessage(),e);
            return Result.toJsonUseApp(Ecode.FAIL, e.getMessage());
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, "拆盘任务提交完成");
    }


}
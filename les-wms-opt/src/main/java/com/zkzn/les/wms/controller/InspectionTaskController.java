package com.zkzn.les.wms.controller;
import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicParameters;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.zkzn.les.common.util.lang.SecurityUserUtil;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.wms.constant.WmsConstants;
import com.zkzn.les.wms.service.InspectionTaskService;
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
 * @ClassName InspectionController
 * @Description TODO
 * @Author zhanglei
 * Date 2021/6/22 9:40
 * @Version 1.0
 **/
@Api(tags = "质检任务")
@RequestMapping("/inspection")
@RestController
public class InspectionTaskController {
    private static Logger log = LoggerFactory.getLogger(InspectionTaskController.class);

    @Autowired
    private InspectionTaskService inspectionService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @ApiOperation(value = "质检任务列表查询")
    @DynamicResponseParameters(name = "",properties = {
            @DynamicParameter(name = "inspectionTaskId",value = "质检主表ID",dataTypeClass = Integer.class),
            @DynamicParameter(name = "billCode",value = "送货单号"),
            @DynamicParameter(name = "billName",value = "订单名称"),
            @DynamicParameter(name = "orderCode",value = "订单号"),
            @DynamicParameter(name = "storageLocation",value = "库存地点"),
            @DynamicParameter(name = "warehouseName",value = "仓库名称"),
            @DynamicParameter(name = "warehouseCode",value = "仓库编码"),
            @DynamicParameter(name = "inspectionTaskCode",value = "质检任务号"),
            @DynamicParameter(name = "billType",value = "订单类型"),
            @DynamicParameter(name = "arrivalId",value = "到货通知单Id"),
            @DynamicParameter(name = "receiveId",value = "点收主表ID"),
            @DynamicParameter(name = "factory",value = "工厂"),
            @DynamicParameter(name = "status",value = "质检状态 15-待质检  20-质检中 25-质检完成"),
            @DynamicParameter(name = "inspectionTotalTime",value = "质检总时长"),
            @DynamicParameter(name = "inspectionEndTime",value = "质检结束时间"),
            @DynamicParameter(name = "createTime",value = "任务创建时间"),
            @DynamicParameter(name = "inspectionStartTime",value = "质检开始时间"),
            @DynamicParameter(name = "materialType",value = "物料种类"),
            @DynamicParameter(name = "shipperName",value = "供应商名称"),
            @DynamicParameter(name = "inspectionTaskId",value = "质检主表id"),
            @DynamicParameter(name = "arrivalCode",value = "到货通知单号")

    })
    @GetMapping(value="/listInspectionInfo",produces = "application/json;charset=UTF-8")
    public String listInspectionInfo(Map<String,Object> param, HttpServletRequest request) {
        List<Map<String, Object>> list = null;
        try {
            String userId = SecurityUserUtil.getCurrentUserId(request);
            String warehouseCode = SecurityUserUtil.getCurrOrgCode(userId, redisTemplate, "app");
            param.put(WmsConstants.LT_STATUS,WmsConstants.NUMBER_20);
            param.putIfAbsent(WmsConstants.WAREHOUSE_CODE,warehouseCode);
            list = inspectionService.listInspectionInfo(param);
        } catch (Exception e) {
            log.error("查询质检列表异常:{}" , e.getMessage(),e);
            return Result.toJsonUseApp(Ecode.FAIL, e.getMessage());
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, list);
    }

    @ApiOperation(value = "质检任务详情列表查询")
    @DynamicParameters(name = "",properties = {
            @DynamicParameter(name = "inspectionId",value = "质检主表ID",dataTypeClass = Integer.class)
            }
    )
    @DynamicResponseParameters(name = "",properties = {
            @DynamicParameter(name = "inspectionDetailId",value = "质检详情表ID",dataTypeClass = Integer.class),
            @DynamicParameter(name = "materialCode",value = "物料编码"),
            @DynamicParameter(name = "materialDesc",value = "物料名称"),
            @DynamicParameter(name = "materialUnit",value = "物料单位"),
            @DynamicParameter(name = "materialNum",value = "物料数量"),
            @DynamicParameter(name = "inspectionNum",value = "质检总数量"),
            @DynamicParameter(name = "qualifiedNum",value = "质检合格数量"),
            @DynamicParameter(name = "unQualifiedNum",value = "质检不合格数量"),
            @DynamicParameter(name = "storageLocation",value = "库存地点"),
            @DynamicParameter(name = "batchNo",value = "任务状态 15-待收货 20-部分收货 25-收货完成"),
            @DynamicParameter(name = "printStatus",value = "打印状态"),
            @DynamicParameter(name = "inspectionResult",value = "质检结果"),
            @DynamicParameter(name = "itemNo",value = "详情行项目号"),
            @DynamicParameter(name = "inspectionId",value = "质检详情任务号"),
            @DynamicParameter(name = "inspectionPositionCode",value = "质检区编码"),
            @DynamicParameter(name = "status",value = "质检状态 15-待质检 20-质检中 25-质检完成"),
            @DynamicParameter(name = "shipperCode",value = "供应商编码"),
            @DynamicParameter(name = "shipperName",value = "供应商名称"),
            @DynamicParameter(name = "arrivalCode",value = "到货通知单编码")
    })
    @PostMapping(value = "/listInspectionDetailInfo", produces = "application/json;charset=UTF-8")
    public String listInspectionDetailInfo( @RequestBody Map<String, Object> param) {
        List<Map<String, Object>> list = null;
        try {
            list = inspectionService.listInspectionDetailInfo(param);
        } catch (Exception e) {
            log.error("质检任务详情列表查询异常:{}" , e.getMessage(),e);
            return Result.toJsonUseApp(Ecode.FAIL, e.getMessage());
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, list);
    }

    @ApiOperation(value = "质检任务提交")
    @DynamicParameters(name = "",properties = {
            @DynamicParameter(name = "inspectionId",value = "质检主表ID",dataTypeClass = Integer.class),
            @DynamicParameter(name = "inspectionDetailId",value = "质检子表id"),
            @DynamicParameter(name = "inspectionNum",value = "(抽检)物料质检总数"),
            @DynamicParameter(name = "qualifiedNum",value = "(抽检)质检合格数"),
            @DynamicParameter(name = "unQualifiedNum",value = "(抽检)质检不合格数"),
            @DynamicParameter(name = "inspectionResult",value = "质检结果 15-质检不合格 20-质检合格 25-质检部分合格",dataTypeClass = Integer.class),
            @DynamicParameter(name = "batchNo",value = "批次号(部分合格的时候必传)"),
            @DynamicParameter(name = "arrivalCode",value = "到货通知单"),
            @DynamicParameter(name = "serialList",value = "序列号列表 \"serialList\":[{\"serialNum\":\"\"},{\"serialNum\":\"\"}]")
    })
    @PostMapping(value = "/updateInspectionInfo", produces = "application/json;charset=UTF-8")
    public String updateInspectionInfo(@RequestBody Map<String, Object> param) {

        try {
            inspectionService.updateInspectionInfo(param);
        } catch (Exception e) {
            log.error("质检任务提交异常:{}" , e.getMessage(),e);
            return Result.toJsonUseApp(Ecode.FAIL, e.getMessage());
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, "质检作业提交成功");
    }

    @ApiOperation(value = "质检任务开始")
    @DynamicParameters(name = "",properties = {
            @DynamicParameter(name = "inspectionId",value = "质检主表ID",dataTypeClass = Integer.class)
    })
    @PostMapping(value = "/updateInspectionStartInfo", produces = "application/json;charset=UTF-8")
    public String updateInspectionStartInfo(@RequestBody Map<String, Object> param) {

        try {
            inspectionService.updateInspectionStartInfo(param);
        } catch (Exception e) {
            log.error("质检任务开始提交异常:{}" , e.getMessage(),e);
            return Result.toJsonUseApp(Ecode.FAIL, e.getMessage());
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, "质检作业开始提交成功");
    }

    @ApiOperation(value = "质检任务完成")
    @DynamicParameters(name = "",properties = {
            @DynamicParameter(name = "inspectionId",value = "质检主表ID",dataTypeClass = Integer.class)
    })
    @PostMapping(value = "/updateInspectionEndInfo", produces = "application/json;charset=UTF-8")
    public String updateInspectionEndInfo(@RequestBody Map<String, Object> param) {

        try {
            inspectionService.updateInspectionEndInfo(param);
        } catch (Exception e) {
            log.error("质检任务结束提交异常:{}" , e.getMessage(),e);
            return Result.toJsonUseApp(Ecode.FAIL, e.getMessage());
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, "质检作业结束提交成功");
    }
}
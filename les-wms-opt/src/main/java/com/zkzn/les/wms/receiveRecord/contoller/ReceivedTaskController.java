package com.zkzn.les.wms.receiveRecord.contoller;
import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicParameters;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.google.common.collect.Lists;
import com.zkzn.les.common.util.lang.SecurityUserUtil;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.wms.constant.WmsConstants;
import com.zkzn.les.wms.receiveRecord.pojo.SaveReceiveDetailPojo;
import com.zkzn.les.wms.receiveRecord.pojo.UpdateReceiveTaskPojo;
import com.zkzn.les.wms.receiveRecord.service.ReceiveTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ReceivedController
 * @Description TODO
 * @Author zhanglei
 * Date 2021/6/15 14:02
 * @Version 1.0
 **/
@Api(tags = "点收任务")
@RequestMapping("/receiveRecord")
@RestController
public class ReceivedTaskController {
    private static Logger LOGGER = LoggerFactory.getLogger(ReceivedTaskController.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ReceiveTaskService receiveTaskService;


    @ApiOperation(value = "点收任务列表查询")
    @DynamicResponseParameters(name = "",properties = {
            @DynamicParameter(name = "materialType",value = "物料种类数量",dataTypeClass = Integer.class),
            @DynamicParameter(name = "orderCode",value = "订单号"),
            @DynamicParameter(name = "billType",value = "点收任务类型"),
            @DynamicParameter(name = "receiveId",value = "点收任务主键id"),
            @DynamicParameter(name = "receiveTaskCode",value = "点收任务号"),
            @DynamicParameter(name = "shipperName",value = "供货商"),
            @DynamicParameter(name = "billCode",value = "送货单号"),
            @DynamicParameter(name = "uploadPlatId",value = "卸货点id"),
            @DynamicParameter(name = "status",value = "任务状态 15-待收货 20-收货中 25-部分收货 30-收货完成 35-已移库质检区"),
    })
    @GetMapping(value = "/listReceiveRecord", produces = "application/json;charset=UTF-8")
    public String listReceiveRecord(Map<String, Object> param, HttpServletRequest request) {
        List<Map<String, Object>> list = null;
        try {
            String userId = SecurityUserUtil.getCurrentUserId(request);
            String warehouseCode = SecurityUserUtil.getCurrOrgCode(userId, redisTemplate, "app");
            param.putIfAbsent(WmsConstants.LT_STATUS,WmsConstants.NUMBER_30);
            param.putIfAbsent(WmsConstants.WAREHOUSE_CODE,warehouseCode);
            param.put("userId",userId);
            list = receiveTaskService.listReceiveRecord(param);
        } catch (Exception e) {
            LOGGER.error("查询待点收列表异常:{}" , e.getMessage(),e);
            return Result.toJsonUseApp(Ecode.FAIL, e.getMessage());
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, list);
    }

    @ApiOperation(value = "点收任务详情列表查询")
    @DynamicParameters(name = "",properties = {
            @DynamicParameter(name = "receiveId",value = "点收主表id",dataTypeClass = String.class,required = true)
    })
    @DynamicResponseParameters(name = "",properties = {
            @DynamicParameter(name = "receiveDetailId",value = "点收详情表Id",dataTypeClass = Integer.class),
            @DynamicParameter(name = "materialCode",value = "物料编码"),
            @DynamicParameter(name = "materialDesc",value = "物料名称"),
            @DynamicParameter(name = "materialUnit",value = "物料单位"),
            @DynamicParameter(name = "actualNum",value = "实际到货数量"),
            @DynamicParameter(name = "receiveNum",value = "点收数量"),
            @DynamicParameter(name = "storageLocation",value = "库存地点"),
            @DynamicParameter(name = "batchNo",value = "批次号"),
            @DynamicParameter(name = "printStatus",value = "打印状态"),
            @DynamicParameter(name = "receiveId",value = "点收任务主表id"),
            @DynamicParameter(name = "itemNo",value = "详情行项目号"),
            @DynamicParameter(name = "receiveTaskCode",value = "点收详情任务号"),
            @DynamicParameter(name = "shipperName",value = "供应商名称"),
            @DynamicParameter(name = "shipperCode",value = "供应商编码"),
            @DynamicParameter(name = "isKey",value = "是否21类关键件  0  否 1 是"),
            @DynamicParameter(name = "status",value = "任务状态 15-待收货 20-收货中 25-部分收货 30-收货完成 35-已移库质检区")
    })
    @PostMapping(value = "/listReceiveRecordDetail", produces = "application/json;charset=UTF-8")
    public String listReceiveRecordDetail(@RequestBody Map<String, Object> param) {
        List<Map<String, Object>> list = null;
        try {
            list = receiveTaskService.listReceiveRecordDetail(param);
        } catch (Exception e) {
            LOGGER.error("点收任务详情列表查询异常:{}" , e.getMessage(),e);
            return Result.toJsonUseApp(Ecode.FAIL, e.getMessage());
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, list);
    }

    /**
     * 开始点收
     * @param updateReceiveTaskPojo
     * @return
     */
    @PostMapping(value = "/saveReceiveInfoStart", produces = "application/json;charset=UTF-8")
    public String saveReceiveInfoStart(@RequestBody UpdateReceiveTaskPojo updateReceiveTaskPojo, HttpServletRequest request) {
        try {
            String userId = SecurityUserUtil.getCurrentUserId(request);
            String currentUserName = SecurityUserUtil.getCurrentUserName(request);
            updateReceiveTaskPojo.setUpdateUserId(Integer.parseInt(userId));
            updateReceiveTaskPojo.setUpdateName(currentUserName);
            updateReceiveTaskPojo.setUpdateTime(new Date());
            receiveTaskService.saveReceiveInfoStart(updateReceiveTaskPojo);
        } catch (Exception e) {
            LOGGER.error("点收开始信息保存失败:{}" , e.getMessage(),e);
            return Result.toJsonUseApp(Ecode.FAIL, e.getMessage());
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, "点收开始信息保存成功");
    }

    /**
     * 点收详情保存
     * @param saveReceiveDetailPojo
     * @param request
     * @return
     */
    @PostMapping(value = "/saveReceiveRecordInfo", produces = "application/json;charset=UTF-8")
    public String saveReceiveRecordInfo(@RequestBody SaveReceiveDetailPojo saveReceiveDetailPojo, HttpServletRequest request) {
        try {
            String userId = SecurityUserUtil.getCurrentUserId(request);
            String currentUserName = SecurityUserUtil.getCurrentUserName(request);
            String warehouseCode = SecurityUserUtil.getAppWarehouseCodeByUserId(redisTemplate, userId);
            String warehouseName = SecurityUserUtil.getAppWarehouseNameByUserId(redisTemplate, userId);
            saveReceiveDetailPojo.setWarehouseCode(warehouseCode);
            saveReceiveDetailPojo.setWarehouseName(warehouseName);
            saveReceiveDetailPojo.setPointerId(Integer.parseInt(userId));
            saveReceiveDetailPojo.setPointerName(currentUserName);
            saveReceiveDetailPojo.setReceiveTime(new Date());
            receiveTaskService.saveReceiveRecordInfo(saveReceiveDetailPojo);
        } catch (Exception e) {
            LOGGER.error("点收记录保存失败:{}" , e.getMessage(),e);
            return Result.toJsonUseApp(Ecode.FAIL, e.getMessage());
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, "点收记录保存成功");
    }

    /**
     * 点收完成
     * @param updateReceiveTaskPojo
     * @return
     */
    @PostMapping(value = "/saveReceiveInfoEnd", produces = "application/json;charset=UTF-8")
    public String saveReceiveInfoEnd(@RequestBody UpdateReceiveTaskPojo updateReceiveTaskPojo) {
        try {
            receiveTaskService.saveReceiveInfoEnd(updateReceiveTaskPojo);
        } catch (Exception e) {
            LOGGER.error("点收结束信息保存失败:{}" , e.getMessage(),e);
            return Result.toJsonUseApp(Ecode.FAIL, e.getMessage());
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, "点收结束信息保存成功");
    }

    @ApiOperation(value = "移库质检区")
    @DynamicParameters(name = "",properties = {
            @DynamicParameter(name = "receiveId",value = "点收主表id",dataTypeClass = String.class,required = true),
            @DynamicParameter(name = "inspectionList",value = "\\\"inspectionList\\\": [{\\r\\n\" +\n" +
                    "     * \t\t\t\"\t    \\\"useDefaultInspect\\\": \\\"是否使用默认质检区地标\\\",\\r\\n\" +\n" +
                    "     * \t\t\t\"\t\t\\\"inspectPositionId\\\": \\\"地标id\\\",\\r\\n\" +\n" +
                    "     * \t\t\t\"\t\t\\\"receiveCarrierId\\\": \\\"物料托盘id\\\",\\r\\n\" +\n" +
                    "     * \t\t\t\"\t\t\\\"areaCode\\\": \\\"地标编码\\\"\\r\\n\" +\n" +
                    "     * \t\t\t\"\t}]\\r\\n\"",dataTypeClass = String.class,required = true)
    })
    @PostMapping(value = "/saveInspectionInfo", produces = "application/json;charset=UTF-8")
    public String saveInspectionInfo(@RequestBody Map<String, Object> param) {
        try {
            receiveTaskService.saveInspectionInfo(param);
        } catch (Exception e) {
            LOGGER.error("移库质检区信息保存失败:{}" , e.getMessage(),e);
            return Result.toJsonUseApp(Ecode.FAIL, e.getMessage());
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, "移库质检区信息保存成功");
    }

    @ApiOperation(value = "移库质检区信息查询")
    @DynamicParameters(name = "",properties = {
            @DynamicParameter(name = "receiveId",value = "点收主表id",dataTypeClass = String.class,required = true)
    })
    @DynamicResponseParameters(name = "",properties = {
            @DynamicParameter(name = "carrierCode",value = "载具编码",dataTypeClass = Integer.class),
            @DynamicParameter(name = "carryNumber",value = "物料数量"),
            @DynamicParameter(name = "orderCode",value = "生产订单号"),
            @DynamicParameter(name = "receiveCarrierId",value = "物料载具表id"),
            @DynamicParameter(name = "batchNo",value = "物料批次号")
    })
    @PostMapping(value = "/listReceiveCarrierInfo", produces = "application/json;charset=UTF-8")
    public String listReceiveCarrierInfo(@RequestBody Map<String, Object> param) {
        List<Map<String,Object>> result = Lists.newArrayList();
        try {
            result = receiveTaskService.listReceiveCarrierInfo(param);
        } catch (Exception e) {
            LOGGER.error("移移库质检区信息查询失败:{}" , e.getMessage(),e);
            return Result.toJsonUseApp(Ecode.FAIL, e.getMessage());
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, result);
    }

}

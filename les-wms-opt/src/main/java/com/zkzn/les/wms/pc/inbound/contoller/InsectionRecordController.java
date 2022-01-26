package com.zkzn.les.wms.pc.inbound.contoller;


import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicParameters;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.zkzn.les.common.constans.Constants;
import com.zkzn.les.common.util.lang.SecurityUserUtil;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.wms.pc.inbound.service.InsectionRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName InsectionRecordController
 * @Description TODO
 * @Author 刘松山
 * @date 2021/6/23 18:33
 **/
@Api(tags = "质检页面")
@RequestMapping("/inspectionRecordPc")
@RestController
@Slf4j
public class InsectionRecordController {

    @Resource
    private InsectionRecordService insectionRecordService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @ApiOperation(value = "质检查询")
    @DynamicParameters(name = "listInspectionInfo", properties = {
            @DynamicParameter(name = "statusStr", value = "质检状态：15-待质检 20-质检中 25-质检完成", example = "X000111", required = true, dataTypeClass = String.class),
            @DynamicParameter(name = "inspectiondTaskCode", value = "质检任务号"),
            @DynamicParameter(name = "billCode", value = "送货单号"),
            @DynamicParameter(name = "billType", value = "单据类型"),
            @DynamicParameter(name = "materialCode", value = "物料号"),
            @DynamicParameter(name = "billCode", value = "送货单号"),
            @DynamicParameter(name = "startTimeBeginStr", value = "质检开始日期"),
            @DynamicParameter(name = "startTimeEndStr", value = "质检结束日期")
    })
    @DynamicResponseParameters(name = "", properties = {
            @DynamicParameter(name = "billCode", value = "送货单号", example = "X000111", dataTypeClass = String.class),
            @DynamicParameter(name = "billName", value = "送货单"),
            @DynamicParameter(name = "billType", value = "订单类型"),
            @DynamicParameter(name = "status", value = "质检状态"),
            @DynamicParameter(name = "inspectionStartTime", value = "质检开始时间"),
            @DynamicParameter(name = "inspectionEndTime", value = "质检结束时间"),
            @DynamicParameter(name = "inspectionTaskCode", value = "质检任务号"),
            @DynamicParameter(name = "warehouseCode", value = "仓库编码"),
            @DynamicParameter(name = "warehouseName", value = "仓库名称"),
            @DynamicParameter(name = "inspectionName", value = "质检人名称"),
            @DynamicParameter(name = "inspectionNum", value = "质检数量"),
            @DynamicParameter(name = "qualifiedNum", value = "合格数量"),
            @DynamicParameter(name = "inspectionResult", value = "质检结果：15-质检不合格 20-质检合格 25-质检部分合格"),
            @DynamicParameter(name = "inspectionPositionCode", value = "质检域编码"),
            @DynamicParameter(name = "inspectionTime", value = "质检时间"),
            @DynamicParameter(name = "materialCode", value = "物料号"),
            @DynamicParameter(name = "materialDesc", value = "物料名称"),
            @DynamicParameter(name = "materialUnit", value = "物料单位"),
            @DynamicParameter(name = "itemNo", value = "行号"),
            @DynamicParameter(name = "storageLocation", value = "库存地点")


    })
    @GetMapping(value = "/listInspectionInfo", produces = "application/json;charset=UTF-8")
    public String listInspectionInfo(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        PageInfo<Map<String, Object>> pageInfo = null;

        try {
            if(params.get("wareHouseCode") ==null || Objects.equals("",params.get("wareHouseCode").toString())){
                //当前登录人对应有权限的仓库
                String userId = SecurityUserUtil.getCurrentUserId(request);
                List<String> warehouseCodes = SecurityUserUtil.getUserOrgCode(redisTemplate, userId);
                params.put(Constants.WAREHOUSECODES, warehouseCodes);
            }else{
                String warehouseCode =params.get("wareHouseCode")+"";
                String[]  warehouseCodeArray= warehouseCode.split(",");
                List<String> warehouseCodes= Arrays.asList(warehouseCodeArray);
                params.put(Constants.WAREHOUSECODES, warehouseCodes);
            }

            pageInfo = insectionRecordService.listReceiveDetailRecordOfInSpect(params);
            return Result.toJson(Ecode.SUCCESS, pageInfo);
        } catch (Exception e) {
            log.debug("质检查询异常:{}", e.getMessage(), e);
            return Result.toJson(Ecode.FAIL, e.getMessage());
        }
    }
}

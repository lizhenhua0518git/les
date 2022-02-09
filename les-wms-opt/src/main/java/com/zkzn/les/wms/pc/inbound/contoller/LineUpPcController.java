package com.zkzn.les.wms.pc.inbound.contoller;

import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicParameters;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.zkzn.les.common.util.lang.SecurityUserUtil;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.wms.pc.inbound.service.LineUpPcService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

/**
 * @ClassName LineUpController
 * @Description  pc端排号查询
 * @Author 刘松山
 * @date 2021/6/17 17:52
 **/
@Api(tags = "排号页面")
@RequestMapping("/lineUpPc")
@RestController
@Slf4j
public class LineUpPcController {
    @Autowired
    private LineUpPcService lineUpPcService;

    @ApiOperation(value = "排号机扫描送货单接口")
    @DynamicParameters(name = "listLineUpInfo",properties = {
            @DynamicParameter(name = "billCode",value = "送货单编码",example = "X000111",required = true,dataTypeClass = String.class),
            @DynamicParameter(name = "queueCode",value = "排队号"),
            @DynamicParameter(name = "status",value = "排队状态： 0 已排号 1 叫号 2 过号 3 取消排号 4 已完成"),
            @DynamicParameter(name = "startDate",value = "排队开始日期"),
            @DynamicParameter(name = "endDate",value = "排队结束日期")
    })
    @DynamicResponseParameters(name = "",properties = {
            @DynamicParameter(name = "factory",value = "工厂",example = "X000111",dataTypeClass = String.class),
            @DynamicParameter(name = "billName",value = "送货单"),
            @DynamicParameter(name = "billType",value = "订单类型"),
            @DynamicParameter(name = "status",value = "排队状态"),
            @DynamicParameter(name = "uploadPlatName",value = "卸货月台"),
            @DynamicParameter(name = "driveName",value = "司机"),
            @DynamicParameter(name = "carCode",value = "车牌号"),
            @DynamicParameter(name = "orderCode",value = "送货单编码")
    })
    @GetMapping(value = "/listLineUpInfo", produces = "application/json;charset=UTF-8")
    public String listLineUpInfo(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        PageInfo<Map<String,Object>> pageInfo =null;

        try {
            pageInfo = lineUpPcService.listLineUpInfo(params);
            return Result.toJson(Ecode.SUCCESS,pageInfo);
        } catch (Exception e) {
            log.debug("排号查询异常:{}" , e.getMessage(),e);
            return Result.toJson(Ecode.FAIL, e.getMessage());
        }
    }

    @ApiOperation(value="分页查询排号卸货任务详情信息")
    @ApiResponses({
            @ApiResponse(code=200,message="")
    })
    @GetMapping(value="/listLineUpDetailRecord",produces="application/json;charset=UTF-8")
    public String listLineUpDetailRecord(@RequestParam Map<String,Object> param,HttpServletRequest request){

        PageInfo<Map<String,Object>> pageInfo = null;

        try{

            pageInfo = lineUpPcService.listLineUpDetailRecord(param);
        }catch(Exception e){
            log.debug("查询排号卸货任务详情失败"+e.getMessage());
            return Result.toJson(Ecode.FAIL, "查询排号卸货任务详情失败");
        }
        return Result.toJson(Ecode.SUCCESS, pageInfo);
    }

}

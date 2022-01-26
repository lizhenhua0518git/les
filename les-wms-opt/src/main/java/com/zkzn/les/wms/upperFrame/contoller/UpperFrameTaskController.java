package com.zkzn.les.wms.upperFrame.contoller;

import com.zkzn.les.common.pojo.MaterialData;
import com.zkzn.les.common.util.lang.SecurityUserUtil;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.wms.pojo.GetUpperFrameRecord;
import com.zkzn.les.wms.upperFrame.pojo.BreakUpperPojo;
import com.zkzn.les.wms.upperFrame.pojo.UpperPositionPojo;
import com.zkzn.les.wms.upperFrame.pojo.VerifyPositionPojo;
import com.zkzn.les.wms.upperFrame.service.UpperFrameTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UpperFrameTaskController
 * @Description 上架服务controller
 * @Author zhanglei
 * Date 2021/7/3 15:39
 * @Version 1.0
 **/
@Api(tags = "点收任务")
@RequestMapping("/receiving")
@RestController
public class UpperFrameTaskController {
    private Logger logger = LoggerFactory.getLogger(UpperFrameTaskController.class);
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UpperFrameTaskService upperFrameTaskService;
    @ApiOperation(value = "查询上架列表接口(flag 0 查询所有 1调拨单 2其它 3 生产上架)")
    @ApiResponses({@ApiResponse(code = 200, message = "{\"msg\":\"消息提示\",\"code\":\"0-成功，-1失败\",\"data\":[]}")})
    @GetMapping(value = "/listUpperFrameRecord", produces = "application/json;charset=UTF-8")
    public String listUpperFrameRecord(String flag, HttpServletRequest request) {
        String userId = SecurityUserUtil.getCurrentUserId(request);
        String warehouseCode = SecurityUserUtil.getAppWarehouseCodeByUserId(redisTemplate, userId);
        try {
            Map<String, List<GetUpperFrameRecord>> map = upperFrameTaskService.listUpperFrameRecord(userId,warehouseCode);
            return Result.toJsonUseApp(Ecode.SUCCESS, map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("查询上架列表失败:" + e.getMessage());
            return Result.toJsonUseApp(Ecode.FAIL, "查询上架列表失败");
        }
    }

    @ApiOperation(value = "查询上架操作接口")
    @ApiResponses({@ApiResponse(code = 200, message = "{\"msg\":\"消息提示\",\"code\":\"0-成功，-1失败\",\"data\":[]}")})
    @PostMapping(value = "/breakUpperFrameRecord", produces = "application/json;charset=UTF-8")
    public String breakUpperFrameRecord(@RequestBody MaterialData materialData, HttpServletRequest request) {
        try {
            BreakUpperPojo breakUpperPojo = upperFrameTaskService.breakUpperFrameRecord(materialData);
            return Result.toJsonUseApp(Ecode.SUCCESS, breakUpperPojo);
        } catch (Exception e) {
            logger.debug("查询上架操作失败:" + e.getMessage(),e);
            return Result.toJsonUseApp(Ecode.FAIL, "查询上架操作失败");
        }
    }

    @ApiOperation(value = "校验仓位接口")
    @ApiResponses({@ApiResponse(code = 200, message = "{\"msg\":\"消息提示\",\"code\":\"0-成功，-1失败\",\"data\":[]}")})
    @PostMapping(value = "/verifyPosition", produces = "application/json;charset=UTF-8")
    public String verifyPosition(@RequestBody VerifyPositionPojo verifyPositionPojo, HttpServletRequest request) {
        String userId = SecurityUserUtil.getCurrentUserId(request);
        String warehouse = SecurityUserUtil.getCurrOrgCode(userId, redisTemplate, "app");
        verifyPositionPojo.setWarehouseCode(warehouse);
        try {
            return upperFrameTaskService.verifyPosition(verifyPositionPojo);
        } catch (Exception e) {
            logger.debug("校验仓位接口:" + e.getMessage(),e);
            return Result.toJsonUseApp(Ecode.FAIL, "校验仓位接口");
        }
    }

    @ApiOperation(value = "校验载具接口")
    @ApiResponses({@ApiResponse(code = 200, message = "{\"msg\":\"消息提示\",\"code\":\"0-成功，-1失败\",\"data\":[]}")})
    @PostMapping(value = "/verifyCar", produces = "application/json;charset=UTF-8")
    public String verifyCar(@RequestBody VerifyPositionPojo verifyPositionPojo, HttpServletRequest request) {
        String userId = SecurityUserUtil.getCurrentUserId(request);
        String warehouse = SecurityUserUtil.getCurrOrgCode(userId, redisTemplate, "app");
        verifyPositionPojo.setWarehouseCode(warehouse);
        try {
            return upperFrameTaskService.verifyCar(verifyPositionPojo);
        } catch (Exception e) {
            logger.debug("校验仓位接口:" + e.getMessage(),e);
            return Result.toJsonUseApp(Ecode.FAIL, "校验仓位接口");
        }
    }

    @ApiOperation(value = "提交上架数据接口")
    @ApiResponses({@ApiResponse(code = 200, message = "{\"msg\":\"消息提示\",\"code\":\"0-成功，-1失败\",\"data\":[]}")})
    @PostMapping(value = "/submitUpperFrameRecord", produces = "application/json;charset=UTF-8")
    public String submitUpperFrameRecord(@RequestBody List<UpperPositionPojo> upperPositionPojos, HttpServletRequest request) {
        String userId = SecurityUserUtil.getCurrentUserId(request);
        String currentUserName = SecurityUserUtil.getCurrentUserName(request);
        String warehouseCode = SecurityUserUtil.getAppWarehouseCodeByUserId(redisTemplate, userId);
        String warehouseName = SecurityUserUtil.getAppWarehouseNameByUserId(redisTemplate, userId);
        try {
            upperFrameTaskService.submitUpperFrameRecord(upperPositionPojos, userId, currentUserName, warehouseCode, warehouseName);
            return Result.toJsonUseApp(Ecode.SUCCESS, "");
        } catch (Exception e) {
            logger.debug("上架失败:" + e.getMessage());
            return Result.toJsonUseApp(Ecode.FAIL, "上架失败");
        }
    }
}

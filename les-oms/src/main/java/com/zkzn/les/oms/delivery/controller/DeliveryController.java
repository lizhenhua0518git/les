package com.zkzn.les.oms.delivery.controller;

import com.zkzn.les.common.util.lang.SecurityUserUtil;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.oms.delivery.pojo.DeliveryPojo;
import com.zkzn.les.oms.delivery.service.DeliveryService;
import com.zkzn.les.oms.stationOrder.pojo.AppShipmentTaskDetailPojo;
import com.zkzn.les.oms.stationOrder.pojo.AppShipmentTaskPojo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
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
 * app交接
 */
@Api(tags = "发货过程:app端车间交接接口服务")
@RequestMapping("/delivery")
@RestController
@Slf4j
public class DeliveryController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostMapping(value = "/listAssembleCarrier", produces = "application/json;charset=UTF-8")
    public String listAssembleCarrier(HttpServletRequest request) {
        List<AppShipmentTaskPojo> appShipmentTaskPojoList = null;
        try{
            AppShipmentTaskPojo appShipmentTaskPojo = new AppShipmentTaskPojo();
            String userId = SecurityUserUtil.getCurrentUserId(request);
            String warehouseCode = SecurityUserUtil.getAppWarehouseCodeByUserId(redisTemplate,userId);
            appShipmentTaskPojo.setOperateUserId(Integer.parseInt(userId));
            appShipmentTaskPojo.setWarehouseCode(warehouseCode);
            appShipmentTaskPojoList = deliveryService.listAssembleCarrier(appShipmentTaskPojo);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.toJsonUseApp(Ecode.FAIL,"查询待交接列表请求异常");
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, appShipmentTaskPojoList);
    }

    @ApiOperation(value = "交接详情")
    @PostMapping(value = "/listDeliveryMaterialPojo", produces = "application/json;charset=UTF-8")
    public String listDeliveryMaterialPojo(@RequestBody AppShipmentTaskDetailPojo appShipmentTaskDetailPojo){
        try {
            List<AppShipmentTaskDetailPojo> list = deliveryService.listDeliveryMaterialPojo(appShipmentTaskDetailPojo.getShipmentTaskId());
            return Result.toJsonUseApp(Ecode.SUCCESS, list);
        } catch (Exception e) {
            logger.debug("获取交接信息失败:" + e.getMessage());
            return Result.toJsonUseApp(Ecode.FAIL, "获取交接信息失败");
        }
    }

    @ApiOperation(value = "交接提交接口")
    @PostMapping(value = "/submitDelivery", produces = "application/json;charset=UTF-8")
    public String submitDelivery(HttpServletRequest request, @RequestBody DeliveryPojo deliveryPojo){
        String userId = SecurityUserUtil.getCurrentUserId(request);
        deliveryPojo.setUpdateUserId(Integer.parseInt(userId));
        try {
            deliveryService.submitDelivery(deliveryPojo);
            return Result.toJsonUseApp(Ecode.SUCCESS, "");
        } catch (Exception e) {
            logger.debug("交接提交失败:" + e.getMessage());
            return Result.toJsonUseApp(Ecode.FAIL, "交接提交失败");
        }
    }

}

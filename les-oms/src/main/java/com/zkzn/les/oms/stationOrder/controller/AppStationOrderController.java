package com.zkzn.les.oms.stationOrder.controller;

import com.zkzn.les.common.util.lang.SecurityUserUtil;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.oms.stationOrder.pojo.AppShipmentTaskDetailPojo;
import com.zkzn.les.oms.stationOrder.pojo.AppShipmentTaskPojo;
import com.zkzn.les.oms.stationOrder.pojo.UpdateShipmentTaskDetail;
import com.zkzn.les.oms.stationOrder.service.AppStationOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName AppStationOrderController
 * @Description TODO
 * @Author zhanglei
 * Date 2020/9/16 15:39
 * @Version 1.0
 **/
@Api(tags = "收货过程:app端下架任务")
@RequestMapping("/appStationOrder")
@RestController
@Slf4j
public class AppStationOrderController {

    @Autowired
    private AppStationOrderService appStationOrderService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 待下架列表查询
     * @param request
     * @return
     */
    @ApiOperation(value = "待下架列表查询")
    @GetMapping("/findStationOrder")
    public String findStationOrder(HttpServletRequest request){
        List<AppShipmentTaskPojo> appShipmentTaskPojoList = null;
        try{
            AppShipmentTaskPojo appShipmentTaskPojo = new AppShipmentTaskPojo();
            String userId = SecurityUserUtil.getCurrentUserId(request);
            String warehouseCode = SecurityUserUtil.getAppWarehouseCodeByUserId(redisTemplate,userId);
            appShipmentTaskPojo.setOperateUserId(Integer.parseInt(userId));
            appShipmentTaskPojo.setWarehouseCode(warehouseCode);
            appShipmentTaskPojoList = appStationOrderService.findStationOrder(appShipmentTaskPojo);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.toJsonUseApp(Ecode.FAIL,"查询待下架列表请求异常");
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, appShipmentTaskPojoList);
    }

    /**
     * 查询下架任务详情
     * @param shipmentTaskId
     * @param request
     * @return
     */
    @ApiOperation(value = "查询下架任务详情")
    @GetMapping(value="/outTransferSlipInfoById",produces = "application/json;charset=UTF-8")
    public String outTransferSlipInfoById(Integer shipmentTaskId){
        List<AppShipmentTaskDetailPojo> appShipmentTaskDetailPojoList = null;
        try{
            appShipmentTaskDetailPojoList = appStationOrderService.findMaterialById(shipmentTaskId);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.toJsonUseApp(Ecode.FAIL,"查询下架任务详情请求异常");
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, appShipmentTaskDetailPojoList);
    }

    /**
     * 下架完成
     * @param updateShipmentTaskDetail
     * @param request
     * @return
     */
    @ApiOperation(value = "下架完成操作")
    @PostMapping("/updateStationOrder")
    public String updateStationOrder(@RequestBody UpdateShipmentTaskDetail updateShipmentTaskDetail, HttpServletRequest request){
        try{
            String userId = SecurityUserUtil.getCurrentUserId(request);
            String warehouseCode = SecurityUserUtil.getCurrOrgCode(userId, redisTemplate, "app");
            updateShipmentTaskDetail.setUpdateUserId(Integer.parseInt(userId));
            updateShipmentTaskDetail.setWarehouseCode(warehouseCode);
            appStationOrderService.updateStationOrder(updateShipmentTaskDetail);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return Result.toJsonUseApp(Ecode.FAIL, "下架完成操作失败");
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, null);
    }

}

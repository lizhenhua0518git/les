package com.zkzn.les.stock.controller;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.common.util.lang.SecurityUserUtil;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.stock.pojo.MaterialInStockPojo;
import com.zkzn.les.stock.pojo.MaterialStorageBin;
import com.zkzn.les.stock.service.RequestAsyncProcessService;
import com.zkzn.les.stock.service.StorageBinService;
import com.zkzn.les.stock.thread.request.ProductInventoryDBUpdateRequest;
import com.zkzn.les.stock.thread.request.Request;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 库存
 */
@Api(tags = "库存服务")
@RequestMapping("/storageBin")
@RestController
public class StorageBinController {
    private Logger logger = LoggerFactory.getLogger(StorageBinController.class);
    @Autowired
    private RequestAsyncProcessService requestAsyncProcessService;
    @Autowired
    private StorageBinService storageBinService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @ApiOperation("库位库存列表查询")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
    @GetMapping(value="/listMaterialStorageBin", produces="application/json;charset=UTF-8")
    public String listMaterialStorageBin(MaterialStorageBin materialStorageBin, HttpServletRequest request ){
        try {
            String userId = SecurityUserUtil.getCurrentUserId(request);
            String wCode = materialStorageBin.getWarehouseCode();
            if ("".equals(wCode)||wCode==null){
                String warehouseCode = SecurityUserUtil.getWarehouseCodeByUserId(redisTemplate,userId);
                materialStorageBin.setWarehouseCode(warehouseCode);
            }
            PageInfo<MaterialStorageBin> data = storageBinService.listMaterialStorageBin(materialStorageBin);
            return Result.toJson(Ecode.SUCCESS, data);
        } catch (Exception e) {
            logger.debug("库位库存查询失败：" + e.getMessage());
            return Result.toJson(Ecode.FAIL, "库位库存查询失败");
        }
    }

    @ApiOperation("仓库库存列表查询")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
    @GetMapping(value="/listMaterialInstock", produces="application/json;charset=UTF-8")
    public String listMaterialInstock(MaterialInStockPojo  materialInStockPojo, HttpServletRequest request ){
        try {
            String userId = SecurityUserUtil.getCurrentUserId(request);
            String wCode = materialInStockPojo.getWarehouseCode();
            if ("".equals(wCode)||wCode==null){
                String warehouseCode = SecurityUserUtil.getWarehouseCodeByUserId(redisTemplate,userId);
                materialInStockPojo.setWarehouseCode(warehouseCode);
            }
            PageInfo<MaterialInStockPojo> data = storageBinService.listMaterialInstock(materialInStockPojo);
            return Result.toJson(Ecode.SUCCESS, data);
        } catch (Exception e) {
            logger.debug("仓库库存查询失败：" + e.getMessage());
            return Result.toJson(Ecode.FAIL, "仓库库存查询失败");
        }
    }

    @PostMapping("/updateProductInventory")
    public String updateProductInventory(@RequestBody Map<String, Object> param) {
        try {
            Request request = new ProductInventoryDBUpdateRequest(param, storageBinService);
            requestAsyncProcessService.process(request);
        } catch (Exception e) {
            logger.debug("库存更新失败:{}", e.getMessage(), e);
            return Result.toJsonUseApp(Ecode.FAIL, "库存更新失败");
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, "库存更新成功");
    }

    /**
     * APP载具库存
     * @param param
     * @return
     */
    @GetMapping("/carInStock")
    public String carInStock(String positionCarCode,HttpServletRequest request) {
        try {
            Map<String,Object> param = new HashMap<>();
            String userId = SecurityUserUtil.getCurrentUserId(request);
            String warehouseCode = SecurityUserUtil.getAppWarehouseCodeByUserId(redisTemplate,userId);
            param.put("warehouseCode",warehouseCode);
            param.put("positionCarCode",positionCarCode);
            List<Map<String,Object>> returnList = storageBinService.carInStock(param);
            return Result.toJsonUseApp(Ecode.SUCCESS, returnList);
        } catch (Exception e) {
            logger.debug("库存更新失败:{}", e.getMessage(), e);
            return Result.toJsonUseApp(Ecode.FAIL, "库存更新失败");
        }
    }

}

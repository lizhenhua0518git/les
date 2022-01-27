package com.zkzn.les.stock.controller;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.common.util.lang.SecurityUserUtil;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.stock.pojo.MaterialInStockPojo;
import com.zkzn.les.stock.pojo.MaterialStorageBin;
import com.zkzn.les.stock.service.StorageBinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 库存
 */
@Api(tags = "库存服务")
@RequestMapping("/storageBin")
@RestController
public class StorageBinController {
    private Logger logger = LoggerFactory.getLogger(StorageBinController.class);
  
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

}

package com.zkzn.les.stock.controller;

import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.stock.pojo.MaterialStorageBin;
import com.zkzn.les.stock.service.MaterialInStockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 仓库库存
 */
@RequestMapping("/inStock")
@RestController
public class MaterialInStockController {

    private static Logger LOGGER = LoggerFactory.getLogger(StorageBinController.class);

    @Autowired
    private MaterialInStockService materialInStockService;
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/updateMaterialInStockList")
    public String updateMaterialInStockList(@RequestBody List<Map<String, Object>> param) {
        try {
            materialInStockService.updateMaterialInStockList(param);
        } catch (Exception e) {
            LOGGER.error("库存更新失败:{}", e.getMessage(), e);
            return Result.toJsonUseApp(Ecode.FAIL, "仓库库存更新失败");
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, "仓库库存更新成功");
    }

    @PostMapping("/updateMaterialStorageBin")
    public String updateMaterialStorageBin(@RequestBody List<Map<String, Object>> param) {
        try {
            materialInStockService.updateMaterialStorageBin(param);
        } catch (Exception e) {
            LOGGER.error("库存更新失败:{}", e.getMessage(), e);
            return Result.toJsonUseApp(Ecode.FAIL, "仓位库存更新失败");
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, "仓位库存更新成功");
    }

    @PostMapping("/minusStorageBinStock")
    public String minusStorageBinStock(@RequestBody Map<String, Object> param) {
        try {
            materialInStockService.minusStorageBinStock(param);
        } catch (Exception e) {
            LOGGER.error("库存更新失败:{}", e.getMessage(), e);
            return Result.toJsonUseApp(Ecode.FAIL, "仓位库存更新失败");
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, "仓位库存更新成功");
    }

    @PostMapping("/reducesMaterialInStock")
    public String reducesMaterialInStock(@RequestBody List<Map<String, Object>> param) {
        try {
            materialInStockService.reducesMaterialInStock(param);
        } catch (Exception e) {
            LOGGER.error("库存更新失败:{}", e.getMessage(), e);
            return Result.toJsonUseApp(Ecode.FAIL, "仓库库存更新失败");
        }
        return Result.toJsonUseApp(Ecode.SUCCESS, "仓库库存更新成功");
    }

    /**
     * 非限制转冻结  冻结转非限制
     * @param param
     * @return
     */
    @PostMapping(value="/storeBinStockStatusChange",produces="application/json;charset=UTF-8")
    public String storeBinStockStatusChange(@RequestBody Map<String, Object> param) {
        try {
            materialInStockService.storeBinStockStatusChange(param);
            return Result.toJson(Ecode.SUCCESS, "库存更新成功");
        } catch (Exception e) {
            LOGGER.error("库存更新失败:{}", e.getMessage(), e);
            return Result.toJson(Ecode.FAIL, "库存更新失败");
        }
    }

    /**
     * 仓位转移
     * @param materialStorageBin
     * @return
     */
    @PostMapping(value="/storeBinStockTypeChange",produces="application/json;charset=UTF-8")
    public String storeBinStockTypeChange(@RequestBody MaterialStorageBin materialStorageBin, HttpServletRequest request) {
        try {
            int i = materialInStockService.storeBinStockTypeChange(materialStorageBin);
            if (i==1){
                return Result.toJson(Ecode.FAIL, "当前库位不存在");
            }
            if (i==2){
                return Result.toJson(Ecode.FAIL, "当前载具不存在");
            }
            return Result.toJson(Ecode.SUCCESS, "库存更新成功");
        } catch (Exception e) {
            LOGGER.error("库存更新失败:{}", e.getMessage(), e);
            return Result.toJson(Ecode.FAIL, "库存更新失败");
        }
    }

}

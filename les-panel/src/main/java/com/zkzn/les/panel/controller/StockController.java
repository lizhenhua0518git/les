package com.zkzn.les.panel.controller;

import com.zkzn.les.panel.domain.stock.StockReport;
import com.zkzn.les.panel.service.StockPanelService;
import com.zkzn.les.panel.util.Ecode;
import com.zkzn.les.panel.util.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 库存报表API </p>
 *
 * @author Hush.
 * @since 2021/12/31 17:06
 */
@Slf4j
@RestController
public class StockController {

    @Resource
    private StockPanelService stockPanelService;

    @ApiOperation(value = "仓库库存统计报表展示")
    @GetMapping(value = "/stockPanel", produces = "application/json;charset=UTF-8")
    public String stockPanelScreen(@RequestParam Map<String, Object> dto) {
        try {
            List<StockReport> result = stockPanelService.listStockPanel(dto);
            return Result.toJson(Ecode.SUCCESS, result);
        } catch (Exception e) {
            log.error("仓库库存统计报表展示错误:{}", e.getMessage(), e);
            return Result.toJson(Ecode.FAIL, null);
        }
    }

    @ApiOperation(value = "初始化仓库")
    @GetMapping(value = "/common/initWarehouseSelect", produces = "application/json;charset=UTF-8")
    public String initWarehouseSelect() {
        try {
            List<Map<String,Object>> result = stockPanelService.initWarehouseSelect();
            return Result.toJson(Ecode.SUCCESS, result);
        } catch (Exception e) {
            log.error("初始化仓库错误:{}", e.getMessage(), e);
            return Result.toJson(Ecode.FAIL, null);
        }
    }

    @ApiOperation(value = "初始化客户")
    @GetMapping(value = "/common/initClientSelect", produces = "application/json;charset=UTF-8")
    public String initClientSelect() {
        try {
            List<Map<String,Object>> result = stockPanelService.initClientSelect();
            return Result.toJson(Ecode.SUCCESS, result);
        } catch (Exception e) {
            log.error("初始化客户错误:{}", e.getMessage(), e);
            return Result.toJson(Ecode.FAIL, null);
        }
    }
}

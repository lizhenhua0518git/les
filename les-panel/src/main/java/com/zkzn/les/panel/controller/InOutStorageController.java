package com.zkzn.les.panel.controller;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.common.util.page.PageUtil;
import com.zkzn.les.panel.domain.stock.InStockReport;
import com.zkzn.les.panel.domain.stock.OutStockReport;
import com.zkzn.les.panel.service.StorageInOutService;
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
 * 入库出口报表API </p>
 *
 * @author Hush.
 * @since 2021/12/31 17:06
 */
@Slf4j
@RestController
public class InOutStorageController {

    @Resource
    private StorageInOutService storageInOutService;

    @ApiOperation(value = "入库报表")
    @GetMapping(value = "/inboundReport", produces = "application/json;charset=UTF-8")
    public String inboundReport(@RequestParam Map<String, Object> dto) {
        try {
            PageUtil.setPageParam(dto);
            List<InStockReport> result = storageInOutService.listInStockReport(dto);
            return Result.toJson(Ecode.SUCCESS, new PageInfo<>(result));
        } catch (Exception e) {
            log.error("仓库库存统计报表展示错误:{}", e.getMessage(), e);
            return Result.toJson(Ecode.FAIL, null);
        }
    }

    @ApiOperation(value = "出库报表")
    @GetMapping(value = "/outboundReport", produces = "application/json;charset=UTF-8")
    public String outboundReport(@RequestParam Map<String, Object> dto) {
        try {
            PageUtil.setPageParam(dto);
            List<OutStockReport> result = storageInOutService.listOutStockReport(dto);
            return Result.toJson(Ecode.SUCCESS, new PageInfo<>(result));
        } catch (Exception e) {
            log.error("仓库库存统计报表展示错误:{}", e.getMessage(), e);
            return Result.toJson(Ecode.FAIL, null);
        }
    }

}

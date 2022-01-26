package com.zkzn.les.stock.controller;

import com.zkzn.les.common.util.lang.SecurityUserUtil;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.stock.constants.StockConstants;
import com.zkzn.les.stock.pojo.SCheckInventoryDetail;
import com.zkzn.les.stock.pojo.SCheckInventoryTask;
import com.zkzn.les.stock.pojo.vo.SCheckInventoryDetailVO;
import com.zkzn.les.stock.service.ISCheckInventoryTaskService;
import com.zkzn.les.stock.util.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * APP库存盘点API服务 </p>
 *
 * @author Hush.
 * @since 2021/12/22 13:22
 */
@Api(tags = "App库存盘点API")
@RequestMapping("/app/checkInventory")
@RestController
@Slf4j
public class AppCheckInventoryController {
    @Autowired
    private ISCheckInventoryTaskService taskService;

    /**
     * 盘点任务列表(盘点状态未结束)
     */
    @GetMapping(value = "/list")
    public String taskList(SCheckInventoryTask dto) {

        dto.setCheckStatus(StockConstants.TO_BE_COUNTED);
        return Result.toJson(Ecode.SUCCESS, taskService.listTask(dto));
    }

    /**
     * 获取仓位及对应物料信息
     * 根据仓位分组展示
     */
    @GetMapping(value = "/materialsGroup")
    public String materialsGroupByPositionId(SCheckInventoryDetailVO dto) {

        if (StringUtils.isEmpty(dto.getTaskId())) {
            Result.toErrorJson("盘点任务id为空", null);
        }
        return Result.toJsonUseApp(Ecode.SUCCESS,taskService.materialsGroup(dto).getList());
    }

    /**
     * 手持机提交盘点数据
     */
    @PostMapping(value = "/submitCheckData")
    public String submitInventoryData(HttpServletRequest request, @Valid @RequestBody List<SCheckInventoryDetail> dto, BindingResult result) {

        //校验
        if (result.hasErrors()) {
            return Result.toErrorJson(ValidatorUtils.getValidationMessByBindingResult(result).toString(), null);
        }
        for (SCheckInventoryDetail detail : dto) {
            detail.setHandledTime(new Date()).setHandledBy(SecurityUserUtil.getCurrentUserName(request));
        }
        return Result.toJson(Ecode.SUCCESS, taskService.saveOrUpdateInventoryData(dto));
    }

    /**
     * 结束盘点
     */
    @PostMapping(value = "/endInventory")
    public String endInventory(HttpServletRequest request,@RequestBody SCheckInventoryTask dto) {

        if (StringUtils.isEmpty(dto.getTaskId())){
            Result.toErrorJson("盘点任务id为空", null);
        }
        SCheckInventoryTask task = new SCheckInventoryTask();
        task.setTaskId(dto.getTaskId()).setEndTime(new Date()).setCheckStatus(StockConstants.CHECKED);
        return Result.toJson(Ecode.SUCCESS, taskService.updateTask(task));
    }

}

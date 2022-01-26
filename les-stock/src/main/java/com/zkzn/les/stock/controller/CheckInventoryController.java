package com.zkzn.les.stock.controller;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.common.util.lang.SecurityUserUtil;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.stock.constants.StockConstants;
import com.zkzn.les.stock.pojo.SCheckInventoryTask;
import com.zkzn.les.stock.pojo.vo.SCheckInventoryDetailVO;
import com.zkzn.les.stock.service.ISCheckInventoryTaskService;
import com.zkzn.les.stock.util.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * web端库存盘点API服务 </p>
 *
 * @author Hush.
 * @since 2021/12/22 13:22
 */
@Api(tags = "web端库存盘点API")
@RequestMapping("/web/checkInventory")
@RestController
@Slf4j
public class CheckInventoryController {

    @Autowired
    private ISCheckInventoryTaskService taskService;

    /**
     * 新建盘点任务
     *
     * @param request: 客户端请求
     * @param dto:     任务
     * @param result:  binding results
     * @return java.lang.String
     * @author Hush.
     * @since 2021/12/22 20:29
     */
    @PostMapping("/save")
    public String saveCheckInventoryTask(HttpServletRequest request, @RequestBody @Valid SCheckInventoryTask dto, BindingResult result) {

        //校验
        if (result.hasErrors()) {
            return Result.toErrorJson(ValidatorUtils.getValidationMessByBindingResult(result).toString(), null);
        }
        dto.setCreateBy(SecurityUserUtil.getCurrentUserName(request)).setCreateTime(new Date());
        try {
            int task = taskService.saveCheckInventoryTask(dto);
            return task > 0 ? Result.toJson(Ecode.SUCCESS, null) : Result.toJson(Ecode.FAIL, null);
        } catch (Exception e) {
            log.error("新建盘点任务失败:{}", e.getMessage());
            return Result.toErrorJson(e.getMessage(), null);
        }
    }

    /**
     * 任务分页检索
     *
     * @param dto 检索条件 {@link SCheckInventoryTask}
     * @return java.lang.String
     * @author Hush.
     * @since 2021/12/22 20:31
     */
    @GetMapping(value = "/pageResult")
    public String taskPage(@RequestParam Map<String, Object> dto) {

        PageInfo<SCheckInventoryTask> pageInfo = null;
        try {
            pageInfo = taskService.listTaskPagePC(dto);
        } catch (Exception e) {
            log.error("任务分页检索:" + e.getMessage());
            return Result.toJson(Ecode.FAIL, "盘点任务分页检索失败");
        }
        return Result.toJson(Ecode.SUCCESS, pageInfo);
    }

    /**
     * 盘点审核人员检索
     *
     * @return java.lang.String
     * @author Hush.
     * @since 2021/12/22 14:31
     */
    @GetMapping(value = "/verifyUserSelect")
    public String verifyUserSelect() {

        return Result.toJson(Ecode.SUCCESS, taskService.verifyUserSelectPC());
    }

    /**
     * 盘点物料详情查看
     *
     * @param dto:
     * @return java.lang.String
     * @author Hush.
     * @since 2021/12/22 21:12
     */
    @GetMapping(value = "detail/page")
    public String taskPage(SCheckInventoryDetailVO dto) {

        PageInfo<SCheckInventoryDetailVO> pageInfo = null;
        try {
            pageInfo = taskService.listDetailPage(dto);
        } catch (Exception e) {
            log.error("任务详情分页检索:" + e.getMessage());
            return Result.toJson(Ecode.FAIL, "盘点任务分页检索失败");
        }
        return Result.toJson(Ecode.SUCCESS, pageInfo);
    }

    /**
     * 任务审核
     *
     * @param request: 客户端请求
     * @param dto:     提交参数
     * @return java.lang.String
     * @author Hush.
     * @since 2021/12/22 21:12
     */
    @PostMapping("/verify")
    public String verifyCheckInventoryTask(HttpServletRequest request, @RequestBody SCheckInventoryTask dto) {

        if (StringUtils.isEmpty(dto.getVerifyStatus())) {
            return Result.toErrorJson("审核状态为空", dto);
        }
        String userId = SecurityUserUtil.getCurrentUserId(request);
        SCheckInventoryTask task = taskService.getCheckInventoryTaskById(dto.getTaskId());
        if (StringUtils.isEmpty(task.getVerifyUserId()) && userId.equals(task.getVerifyUserId().toString())) {
            return Result.toErrorJson("审核人员校验失败", task.getVerifyUserName());
        }
        //task.getCheckStatus()
        if (task.getVerifyStatus() != StockConstants.CHECK_VERIFY_STATUS_NO) {
            return Result.toErrorJson("审核状态错误", dto);
        }
        if (!task.getCheckStatus().equals(StockConstants.CHECKED)) {
            return Result.toErrorJson("盘点任务未结束", dto);
        }
        task.setVerifyStatus(dto.getVerifyStatus());

        int i = 0;
        try {
            i = taskService.verifyTask(task);
            return Result.toJson(i > 0 ? Ecode.SUCCESS : Ecode.FAIL, i);
        } catch (Exception e) {
            return Result.toErrorJson(e.getMessage(),null);
        }


    }

    /**
     * 任务移除
     *
     * @param request: 客户端请求
     * @param id:      盘点任务主键
     * @return java.lang.String
     * @author Hush.
     * @since 2021/12/22 21:12
     */
    @PostMapping("/removeTask")
    public String removeTask(HttpServletRequest request, @RequestParam(name = "id") Long id) {

        if (StringUtils.isEmpty(id)) {
            return Result.toErrorJson("请选中盘点任务后操作", null);
        }
        return taskService.removeTask(id);
    }
}

package com.zkzn.les.wms.arrivalNotice.controller;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.common.util.lang.SecurityUserUtil;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.wms.arrivalNotice.pojo.ArrivalNoticeDetailPojo;
import com.zkzn.les.wms.arrivalNotice.pojo.ArrivalNoticePojo;
import com.zkzn.les.wms.arrivalNotice.service.ArrivalNoticeService;
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
import java.util.Date;
import java.util.List;

@Api(tags="到货通知单信息服务")
@RequestMapping(value="/arrivalPC")
@RestController
public class ArrivalNoticePCController {

    private Logger logger = LoggerFactory.getLogger(ArrivalNoticePCController.class);

    @Autowired
    private ArrivalNoticeService arrivalNoticeService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 送货单管理列表查询
     * @param request
     * @param arrivalNoticePojo
     * @return
     */
    @ApiOperation("送货单管理列表查询")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
    @GetMapping(value="/arrivalList", produces="application/json;charset=UTF-8")
    public String getArrivalNoticeServiceList(HttpServletRequest request, ArrivalNoticePojo arrivalNoticePojo) {
        String userId = SecurityUserUtil.getCurrentUserId(request);
        String wCode = arrivalNoticePojo.getWarehouseCode();
        if ("".equals(wCode)||wCode==null){
            String warehouseCode = SecurityUserUtil.getWarehouseCodeByUserId(redisTemplate,userId);
            arrivalNoticePojo.setWarehouseCode(warehouseCode);
        }
        try {
            PageInfo<ArrivalNoticePojo> arrivalNoticeList = arrivalNoticeService.getArrivalNotice(arrivalNoticePojo);
            return Result.toJson(Ecode.SUCCESS, arrivalNoticeList);
        }catch (Exception e) {
            logger.debug("送货单管理查询失败：" + e.getMessage());
            return Result.toJson(Ecode.FAIL, "送货单管理查询失败");
        }
    }

    @ApiOperation("送货单详情列表查询")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
    @GetMapping(value="/arrivalNoticeDetailList", produces="application/json;charset=UTF-8")
    public String arrivalNoticeDetailList(HttpServletRequest request, ArrivalNoticeDetailPojo arrivalNoticeDetailPojo){
        try {
            PageInfo<ArrivalNoticeDetailPojo> arrivalNoticeDetailList = arrivalNoticeService.arrivalNoticeDetailList(arrivalNoticeDetailPojo);
            return Result.toJson(Ecode.SUCCESS, arrivalNoticeDetailList);
        }catch (Exception e) {
            logger.debug("送货单详情查询失败：" + e.getMessage());
            return Result.toJson(Ecode.FAIL, "送货单详情查询失败");
        }
    }

    @ApiOperation("送货单详情修改")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
    @PostMapping(value="/updateArrivalNoticeDetail", produces="application/json;charset=UTF-8")
    public String updateArrivalNoticeDetail(HttpServletRequest request,@RequestBody ArrivalNoticeDetailPojo arrivalNoticeDetailPojo){
        String userID = SecurityUserUtil.getCurrentUserId(request);
        try {
            int i = arrivalNoticeService.updateArrivalNoticeDetail(arrivalNoticeDetailPojo);
            return Result.toJson(Ecode.SUCCESS, i);
        }catch (Exception e) {
            logger.debug("送货单详情修改失败：" + e.getMessage());
            return Result.toJson(Ecode.FAIL, "送货单详情修改失败");
        }
    }

    @ApiOperation("新增送货单数据")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
    @PostMapping(value="/arriveNoticeAdd", produces="application/json;charset=UTF-8")
    public String arriveNoticeAdd(HttpServletRequest request,@RequestBody ArrivalNoticePojo arrivalNoticePojo){
        String userID = SecurityUserUtil.getCurrentUserId(request);
        String currentUserName = SecurityUserUtil.getCurrentUserName(request);
        arrivalNoticePojo.setCreateUserId(Integer.parseInt(userID));
        arrivalNoticePojo.setCreaterName(currentUserName);
        try {
            int i = arrivalNoticeService.arriveNoticeAdd(arrivalNoticePojo);
            return Result.toJson(Ecode.SUCCESS, "新增送货单成功");
        }catch (Exception e) {
            logger.debug("送货单详情修改失败：" + e.getMessage());
            return Result.toJson(Ecode.FAIL, "新增送货单数据失败");
        }
    }

    @ApiOperation("删除送货单数据")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
    @PostMapping(value="/deleteArriveNotice", produces="application/json;charset=UTF-8")
    public String deleteArriveNotice(HttpServletRequest request,@RequestBody List<Integer> arrivalNoticeIds){
        try {
            int i = arrivalNoticeService.deleteArriveNotice(arrivalNoticeIds);
            return Result.toJson(Ecode.SUCCESS, i);
        }catch (Exception e) {
            logger.debug("删除送货单数据失败：" + e.getMessage());
            return Result.toJson(Ecode.FAIL, "删除送货单数据失败");
        }
    }

    @ApiOperation("下发送货单数据")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
    @PostMapping(value="/issueArriveNotice", produces="application/json;charset=UTF-8")
    public String issueArriveNotice(HttpServletRequest request,@RequestBody List<Integer> arrivalNoticeIds){
        String i1 = SecurityUserUtil.getCurrentUserId(request);
        int userId = Integer.parseInt(i1);
        String currentUserName = SecurityUserUtil.getCurrentUserName(request);
        try {
            int i = arrivalNoticeService.issueArriveNotice(arrivalNoticeIds,userId,currentUserName);
            if (i==0){
                return Result.toJson(Ecode.FAIL, "单据详情没有数据");
            }
            return Result.toJson(Ecode.SUCCESS, "下发送货单数据成功");
        }catch (Exception e) {
            logger.debug("下发送货单数据失败：" + e.getMessage());
            return Result.toJson(Ecode.FAIL, "下发送货单数据失败");
        }
    }

    @ApiOperation("新增送货单详情数据")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
    @PostMapping(value="/arriveDetailAdd", produces="application/json;charset=UTF-8")
    public String arriveDetailAdd(HttpServletRequest request,@RequestBody ArrivalNoticeDetailPojo arrivalNoticeDetailPojo){
        String userID = SecurityUserUtil.getCurrentUserId(request);
        String currentUserName = SecurityUserUtil.getCurrentUserName(request);
        arrivalNoticeDetailPojo.setCreateUserId(Integer.parseInt(userID));
        arrivalNoticeDetailPojo.setCreateName(currentUserName);
        arrivalNoticeDetailPojo.setCreateTime(new Date());
        try {
            int i = arrivalNoticeService.arriveDetailAdd(arrivalNoticeDetailPojo);
            return Result.toJson(Ecode.SUCCESS, i);
        }catch (Exception e) {
            logger.debug("新增送货单详情失败：" + e.getMessage());
            return Result.toJson(Ecode.FAIL, "新增送货单详情失败");
        }
    }

    @ApiOperation("删除送货单详情数据")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
    @PostMapping(value="/deleteArriveDetail", produces="application/json;charset=UTF-8")
    public String deleteArriveDetail(HttpServletRequest request,@RequestBody List<Integer> arrivalDetailIds){
        try {
            int i = arrivalNoticeService.deleteArriveDetail(arrivalDetailIds);
            return Result.toJson(Ecode.SUCCESS, i);
        }catch (Exception e) {
            logger.debug("删除送货单数据失败：" + e.getMessage());
            return Result.toJson(Ecode.FAIL, "删除送货单数据失败");
        }
    }


}

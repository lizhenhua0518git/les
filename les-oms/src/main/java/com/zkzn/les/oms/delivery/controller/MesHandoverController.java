package com.zkzn.les.oms.delivery.controller;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.common.util.excel.ExcelHandleUtil;
import com.zkzn.les.common.util.lang.SecurityUserUtil;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.oms.delivery.service.MesHandoverService;
import com.zkzn.les.oms.stationOrder.pojo.PcShipmentTaskPojo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 交接PC端
 */
@RestController
@RequestMapping("/handover")
public class MesHandoverController {

    @Resource
    private MesHandoverService mesHandoverService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    Logger logger = LoggerFactory.getLogger(MesHandoverController.class);

    /**
     * 交接主表
     *
     * @param pcShipmentTaskPojo
     * @return
     */
    @GetMapping(value = "/listHandoverOrderList", produces = "application/json;charset=UTF-8")
    public String listHandoverOrderList(PcShipmentTaskPojo pcShipmentTaskPojo, HttpServletRequest request) {
        PageInfo<PcShipmentTaskPojo> pageInfo = null;
        try {
            String userId = SecurityUserUtil.getCurrentUserId(request);
            String wCode = pcShipmentTaskPojo.getWarehouseCode();
            if ("".equals(wCode) || wCode == null) {
                String warehouseCode = SecurityUserUtil.getWarehouseCodeByUserId(redisTemplate, userId);
                pcShipmentTaskPojo.setWarehouseCode(warehouseCode);
            }
            pageInfo = mesHandoverService.listHandoverOrderList(pcShipmentTaskPojo);
        } catch (Exception e) {
            logger.debug("交接信息查询:" + e.getMessage());
            return Result.toJson(Ecode.FAIL, "交接信息查询异常");
        }
        return Result.toJson(Ecode.SUCCESS, pageInfo);
    }

    /**
     * 交接明细
     *
     * @param pcShipmentTaskPojo
     * @return
     */
    @GetMapping(value = "/listHandoverMaterialList", produces = "application/json;charset=UTF-8")
    public String listHandoverMaterialList(PcShipmentTaskPojo pcShipmentTaskPojo) {
        PageInfo<PcShipmentTaskPojo> pageInfo = null;
        try {
            pageInfo = mesHandoverService.listHandoverMaterialList(pcShipmentTaskPojo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("交接物料信息查询:" + e.getMessage());
            return Result.toJson(Ecode.FAIL, "交接物料信息查询异常");
        }
        return Result.toJson(Ecode.SUCCESS, pageInfo);
    }


    /**
     * 导出交接物料信息数据
     *
     * @param param:查询参数
     * @return java.lang.String
     * @author Hush.
     * @since 2022/1/13 10:11
     */
    @GetMapping(value = "/exportHandoverOrderDetail", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String exportHandoverOrderDetail(PcShipmentTaskPojo param, HttpServletRequest request, HttpServletResponse response) {

        OutputStream output = null;
        try {
            List<PcShipmentTaskPojo> result = mesHandoverService.listHandoverMaterial(param);
            String[] title = {"任务编号", "仓库名称", "客户名称", "货物名称", "单位", "下架数量", "批次号", "合格状态"};
            String[] name = {"orderCode", "warehouseName", "clientName", "materialDesc", "materialUnit", "shipmentCount", "batchNo", "stockDesc"};
            List<String> titleList = Arrays.asList(title);
            List<String> filedNames = Arrays.asList(name);
            output = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename=" + System.currentTimeMillis() + "_HandoverMaterialInformation.xls");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            HSSFWorkbook workbook = ExcelHandleUtil.exportExcel(titleList, result, filedNames);
            workbook.write(output);
        } catch (Exception e) {
            logger.error("导出失败：" + e.getMessage());
            return Result.toJson(Ecode.FAIL, e.getMessage());
        } finally {
            try {
                if (output != null) {
                    output.flush();
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Result.toJson(Ecode.SUCCESS, "导出成功");

    }
}

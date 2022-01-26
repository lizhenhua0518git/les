package com.zkzn.les.wms.receiveRecord.contoller;


import com.github.pagehelper.PageInfo;
import com.zkzn.les.common.util.lang.SecurityUserUtil;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.wms.receiveRecord.pojo.ReceiveTaskDetailPojo;
import com.zkzn.les.wms.receiveRecord.pojo.ReceiveTaskPojo;
import com.zkzn.les.wms.receiveRecord.service.ReceiveRecordService;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Api(description = "收货记录主表 controller层")
@RestController
@RequestMapping(value = "/pcReceiveRecord")
public class ReceiveRecordPCController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ReceiveRecordService receiveRecordService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 点收任务管理列表查询
     * @param param
     * @param request
     * @return
     */
    @GetMapping(value = "/listPcReceiveRecord", produces = "application/json;charset=UTF-8")
    public String listPcReceiveRecord(ReceiveTaskPojo receiveTaskPojo, HttpServletRequest request) {
        PageInfo<ReceiveTaskPojo> pageInfo = null;
        try {
            //当前登录人对应有权限的仓库
            String userId = SecurityUserUtil.getCurrentUserId(request);
            String wCode = receiveTaskPojo.getWarehouseCode();
            if ("".equals(wCode)||wCode==null){
                String warehouseCode = SecurityUserUtil.getWarehouseCodeByUserId(redisTemplate,userId);
                receiveTaskPojo.setWarehouseCode(warehouseCode);
            }
            pageInfo = receiveRecordService.listReceiveRecordPage(receiveTaskPojo);
        } catch (Exception e) {
            logger.debug("查询点收记录失败:" + e.getMessage());
            return Result.toJson(Ecode.FAIL, "查询点收记录失败");
        }
        return Result.toJson(Ecode.SUCCESS, pageInfo);
    }


    @ApiOperation(value = "分页查询点收记录详细信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "")
    })
    @GetMapping(value = "/listPcReceiveDetailRecord", produces = "application/json;charset=UTF-8")
    public String listPcReceiveDetailRecord(ReceiveTaskDetailPojo receiveTaskDetailPojo, HttpServletRequest request) {
        PageInfo<ReceiveTaskDetailPojo> pageInfo = null;
        try {
            pageInfo = receiveRecordService.listReceiveDetailRecordPage(receiveTaskDetailPojo);
        } catch (Exception e) {
            logger.debug("查询点收记录详细信息" + e.getMessage());
            return Result.toJson(Ecode.FAIL, "查询点收记录详细信息");
        }
        return Result.toJson(Ecode.SUCCESS, pageInfo);
    }
}

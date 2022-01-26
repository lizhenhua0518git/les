package com.zkzn.les.panel.controller;

import com.zkzn.les.common.pojo.panel.QueuePanel;
import com.zkzn.les.common.util.redis.RedisAliUtil;
import com.zkzn.les.panel.service.QueuePanelService;
import com.zkzn.les.panel.util.Ecode;
import com.zkzn.les.panel.util.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/***
 * @Discription: 大屏排号信息展示
 * @Author: zhanglei on 2021/1/8 13:20
 */
@RestController
@Slf4j
public class QueuePanelController {

    @Resource
    QueuePanelService queuePanelService;

    @ApiOperation(value = "查询排队入场大屏信息")
    @ApiResponses({
            @ApiResponse(code = 200, message ="")
    })
    @GetMapping(value = "/queuePanel", produces = "application/json;charset=UTF-8")
    public String listReceiveRecordScreen(QueuePanel queuePanel, HttpServletRequest request) {
        List<QueuePanel> queueList = null;
        try {
            queueList = queuePanelService.listQueuePanel(queuePanel);
            return Result.toJson(Ecode.SUCCESS, queueList);
        } catch (Exception e) {
            log.debug("查询排队入场大屏信息失败:" + e.getMessage());
            e.printStackTrace();
            return Result.toJson(Ecode.FAIL, "查询排队入场实时信息失败:" + e.getMessage());
        }
    }

    /***
     * @Discription: 接口访问 第一次列表信息从redis中查询数据
     * @param
     * @return java.lang.String
     * @Author: zhanglei on 2021/1/8 13:21
     */
    @GetMapping("/rowNumberScreen")
    public  String listRowNumberScreen(@RequestParam Map<String,Object> requestMap){
        //从redis1 分区中获取对应的排号列表信息
        try {
            List<String> rowNumbers = RedisAliUtil.getDbAllKey(1);
            return Result.toJson(Ecode.SUCCESS,rowNumbers);
        }catch (Exception e){
            log.error("排号大屏信息查询错误：{}",e.getMessage(),e);
            return Result.toJson(Ecode.FAIL,"排号信息查询错误".concat(e.getMessage()));
        }
    }

}

package com.zkzn.les.panel.controller;

import com.zkzn.les.panel.service.ReceivePanelService;
import com.zkzn.les.panel.util.Ecode;
import com.zkzn.les.panel.util.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class ReceivePanelController {

    @Resource
    ReceivePanelService receivePanelService;

    /***
     * @Discription: 点收记录大屏数据展示
     * @param
     * @return java.lang.String
     * @Author: zhanglei on 2020/10/19 14:16
     */
    @ApiOperation(value = "点收记录大屏数据展示")
    @PostMapping(value = "/receivePanel",produces = "application/json;charset=UTF-8")
    public String pointListRecordCollectionScreen() {
        try{
            List<Map<String, Object>> list = receivePanelService.listReceivePanel();
            return Result.toJson(Ecode.SUCCESS, list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.toJson(Ecode.FAIL, null);
        }
    }
}

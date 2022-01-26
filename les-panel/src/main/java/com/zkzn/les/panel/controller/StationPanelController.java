package com.zkzn.les.panel.controller;

import com.zkzn.les.panel.service.StationPanelService;
import com.zkzn.les.panel.util.Ecode;
import com.zkzn.les.panel.util.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@Slf4j
public class StationPanelController {
    @Resource
    StationPanelService sationPanelService;

    /***
     * @Discription: 出库配送大屏数据展示
     * @param
     * @return java.lang.String
     * @Author: zhanglei on 2020/10/19 14:16
     */
    @ApiOperation(value = "出库配送大屏数据展示")
    @PostMapping(value = "/stationPanel",produces = "application/json;charset=UTF-8")
    public String pointListRecordCollectionScreen() {
        try{
            Map<String, Object>  map = sationPanelService.listStationPanel();
            return Result.toJson(Ecode.SUCCESS, map);
        }catch (Exception e){
            e.printStackTrace();
            return Result.toJson(Ecode.FAIL, null);
        }
    }
}

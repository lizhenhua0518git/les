package com.zkzn.les.panel.controller;
import com.zkzn.les.common.pojo.panel.InspectPanelVo;
import com.zkzn.les.panel.service.InspectPanelService;
import com.zkzn.les.panel.util.Ecode;
import com.zkzn.les.panel.util.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
@RestController
@Slf4j
public class InspectPanelController {

    @Resource
    InspectPanelService inspectPanelService;

    /**
     * @Description TD: 质检进度大屏
     * @param
     * @Return java.lang.String
     * @Author sangsang
     * @Date 2020/10/20 9:11
     **/
    @ApiOperation(value = "质检进度大屏")
    @GetMapping(value = "/inspectPanel",produces = "application/json;charset=UTF-8")
    public String listQualityRecordCollectionScreen() {
        try{
            InspectPanelVo  resultMap = inspectPanelService.listOfInspectInfoPanel();
            return Result.toJson(Ecode.SUCCESS, resultMap);
        }catch (Exception e){
            log.debug("查询质检大屏信息失败:" + e.getMessage());
            e.printStackTrace();
            return Result.toJson(Ecode.FAIL, "查询质检大屏信息失败:" + e.getMessage());
        }
    }
}

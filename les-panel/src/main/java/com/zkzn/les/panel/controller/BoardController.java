package com.zkzn.les.panel.controller;

import com.zkzn.les.panel.service.BoardService;
import com.zkzn.les.panel.util.Ecode;
import com.zkzn.les.panel.util.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName InBoundBoardController
 * @Description 看板管理
 * @Author 刘松山
 * @date 2021/3/15 14:57
 **/
@RestController
@Slf4j
public class BoardController {

    @Resource
    private BoardService boardService;

    @ApiOperation(value = "入库看板展示")
    @PostMapping(value = "/inBoundBoard",produces = "application/json;charset=UTF-8")
    public String inBoundBoard() {
        try{
            List<Map<String, Object>> list = boardService.listAllInBoundInfo();
            return Result.toJson(Ecode.SUCCESS, list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.toJson(Ecode.FAIL, null);
        }
    }

    @ApiOperation(value = "出库看板展示")
    @PostMapping(value = "/outBoundBoard",produces = "application/json;charset=UTF-8")
    public String listAllOutBoundInfo() {
        try{
            List<Map<String, Object>> list = boardService.listAllOutBoundInfo();
            return Result.toJson(Ecode.SUCCESS, list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.toJson(Ecode.FAIL, null);
        }
    }

    @ApiOperation(value = "库内看板展示")
    @PostMapping(value = "/inventory",produces = "application/json;charset=UTF-8")
    public String listInventoryInfo() {
        try{
            List<Map<String, Object>> list = boardService.listInventory();
            return Result.toJson(Ecode.SUCCESS, list);
        }catch (Exception e){
            e.printStackTrace();
            return Result.toJson(Ecode.FAIL, null);
        }
    }
}

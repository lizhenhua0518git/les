package com.zkzn.les.basicInfo.controller;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.pojo.ClientManage;
import com.zkzn.les.basicInfo.service.ClientManageService;
import com.zkzn.les.basicInfo.util.SecurityUserUtil;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 客户管理
 */
@RestController
@RequestMapping("/clientManage")
public class ClientManageController {

    private Logger logger = LoggerFactory.getLogger(DictController.class);
    @Autowired
    private ClientManageService clientManageService;

    /**
     * 查询客户信息
     * @return
     */
    @GetMapping(value="/initClientManageList", produces="application/json;charset=UTF-8")
    public String initClientManageList(HttpServletRequest request){
        try {
            List<ClientManage> list = clientManageService.initClientManageList();
            return Result.toJson(Ecode.SUCCESS, list);
        } catch (Exception e) {
            logger.debug("初始化区域类型失败：" + e.getMessage());
            return Result.toJson(Ecode.FAIL, "初始化区域类型失败");
        }
    }

    @GetMapping(value="/clientManageList", produces="application/json;charset=UTF-8")
    public String clientManageList(HttpServletRequest request,ClientManage clientManage){
        try {
            PageInfo<ClientManage> list = clientManageService.clientManageList(clientManage);
            return Result.toJson(Ecode.SUCCESS, list);
        } catch (Exception e) {
            logger.debug("初始化区域类型失败：" + e.getMessage());
            return Result.toJson(Ecode.FAIL, "初始化区域类型失败");
        }
    }

    /**
     * 新增客户信息
     * @param request
     * @param clientManage
     * @return
     */
    @PostMapping(value="/clientManageAdd", produces="application/json;charset=UTF-8")
    public String clientManageAdd(HttpServletRequest request,@RequestBody ClientManage clientManage){
        try {
            String currentUserId = SecurityUserUtil.getCurrentUserId(request);
            clientManage.setCreateUserId(Integer.parseInt(currentUserId));
            clientManage.setCreateTime(new Date());
            int i = clientManageService.clientManageAdd(clientManage);
            if (i == 1){
                return Result.toJson(Ecode.CLIENT_EXIST, null);
            }
            return Result.toJson(Ecode.SUCCESS, "新增客户成功");
        } catch (Exception e) {
            logger.debug("新增客户失败：" + e.getMessage());
            return Result.toJson(Ecode.FAIL, "新增客户失败");
        }
    }

    /**
     * 删除客户信息
     * @param request
     * @param clientManageIds
     * @return
     */
    @PostMapping(value="/deleteClientInfo", produces="application/json;charset=UTF-8")
    public String deleteClientInfo(HttpServletRequest request,@RequestBody List<Integer> clientManageIds){
        try {
            clientManageService.deleteClientInfo(clientManageIds);
            return Result.toJson(Ecode.SUCCESS, "删除客户成功");
        } catch (Exception e) {
            logger.debug("删除客户失败：" + e.getMessage());
            return Result.toJson(Ecode.FAIL, "删除客户失败");
        }
    }

    /**
     * 编辑客户信息
     * @param request
     * @param clientManage
     * @return
     */
    @PostMapping(value="/clientManageUpdate", produces="application/json;charset=UTF-8")
    public String clientManageUpdate(HttpServletRequest request,@RequestBody ClientManage clientManage){
        try {
            String currentUserId = SecurityUserUtil.getCurrentUserId(request);
            clientManage.setUpdateUserId(Integer.parseInt(currentUserId));
            clientManage.setUpdateTime(new Date());
            clientManageService.clientManageUpdate(clientManage);
            return Result.toJson(Ecode.SUCCESS, "编辑客户成功");
        } catch (Exception e) {
            logger.debug("编辑客户失败：" + e.getMessage());
            return Result.toJson(Ecode.FAIL, "编辑客户失败");
        }
    }
}

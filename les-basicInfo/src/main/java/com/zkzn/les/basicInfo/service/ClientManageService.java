package com.zkzn.les.basicInfo.service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.pojo.ClientManage;

import java.util.List;

public interface ClientManageService {

    /**
     * 初始化客户信息列表
     * @return
     */
    List<ClientManage> initClientManageList();

    /**
     * 查询客户信息列表
     * @param clientManage
     * @return
     */
    PageInfo<ClientManage> clientManageList(ClientManage clientManage);

    /**
     * 新增客户
     * @param clientManage
     */
    int clientManageAdd(ClientManage clientManage);

    /**
     * 删除客户
     * @param clientManageIds
     * @return
     */
    void deleteClientInfo(List<Integer> clientManageIds);

    /**
     * 编辑客户
     * @param clientManage
     * @return
     */
    void clientManageUpdate(ClientManage clientManage);
}

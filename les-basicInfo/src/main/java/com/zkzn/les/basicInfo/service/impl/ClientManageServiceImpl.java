package com.zkzn.les.basicInfo.service.impl;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.dao.ClientManageDao;
import com.zkzn.les.basicInfo.pojo.ClientManage;
import com.zkzn.les.basicInfo.service.ClientManageService;
import com.zkzn.les.common.util.page.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClientManageServiceImpl implements ClientManageService {

    @Resource
    private ClientManageDao clientManageDao;

    @Override
    public List<ClientManage> initClientManageList() {
        ClientManage clientManage = new ClientManage();
        List<ClientManage> clientManageList = getClientManageList(clientManage);
        return clientManageList;
    }

    @Override
    public PageInfo<ClientManage> clientManageList(ClientManage clientManage) {
        PageUtil.setPageParam(clientManage);
        List<ClientManage> clientManageList = getClientManageList(clientManage);
        PageInfo<ClientManage> clientManagePageInfo = new PageInfo<>(clientManageList);
        return clientManagePageInfo;
    }

    @Override
    public int clientManageAdd(ClientManage clientManage) {
        int checkNumber = clientManageDao.checkClient(clientManage);
        if (checkNumber>0){
            return 1;
        }
        clientManageDao.clientManageAdd(clientManage);
        return 0;
    }

    @Override
    public void deleteClientInfo(List<Integer> clientManageIds) {
        clientManageDao.deleteClientInfo(clientManageIds);
    }

    @Override
    public void clientManageUpdate(ClientManage clientManage) {
        clientManageDao.clientManageUpdate(clientManage);
    }

    public List<ClientManage> getClientManageList(ClientManage clientManage){
        List<ClientManage> clientManageList = clientManageDao.getClientManageList(clientManage);
        return clientManageList;
    }
}

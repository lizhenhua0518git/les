package com.zkzn.les.basicInfo.dao;

import com.zkzn.les.basicInfo.pojo.ClientManage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientManageDao {

    /**
     * 查询客户信息
     * @param clientManage
     * @return
     */
    List<ClientManage> getClientManageList(ClientManage clientManage);

    /**
     * 新增客户
     * @param clientManage
     * @return
     */
    void clientManageAdd(ClientManage clientManage);

    /**
     * 校验客户
     * @param clientManage
     * @return
     */
    int checkClient(ClientManage clientManage);

    /**
     * 删除客户
     * @param clientManageIds
     * @return
     */
    void deleteClientInfo(List<Integer> clientManageIds);

    /**
     * 修改客户
     * @param clientManage
     * @return
     */
    void clientManageUpdate(ClientManage clientManage);
}

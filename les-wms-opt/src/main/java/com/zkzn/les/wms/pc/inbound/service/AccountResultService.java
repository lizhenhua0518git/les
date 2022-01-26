package com.zkzn.les.wms.pc.inbound.service;


import com.github.pagehelper.PageInfo;
import com.zkzn.les.wms.pojo.AccountResult;

/**
 * .
 *
 * @author luozhihong
 * @date 2020年9月9日
 * @Description : 过账记录 service
 */
public interface AccountResultService {


    /**
     * .
     *
     * @param accountResult
     * @return
     * @Author:luozhihong
     * @date:2020年9月9日
     * @Description:分页查询过账记录
     */
    PageInfo<AccountResult> listAccountResult(AccountResult accountResult);


}

package com.zkzn.les.wms.pc.inbound.service.impl;





import com.github.pagehelper.PageInfo;
import com.zkzn.les.common.util.page.PageUtil;
import com.zkzn.les.wms.pc.inbound.dao.AccountResultDao;
import com.zkzn.les.wms.pc.inbound.service.AccountResultService;

import com.zkzn.les.wms.pojo.AccountResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@SuppressWarnings("unchecked")
@Service
public class AccountResultServiceImpl implements AccountResultService {

	Logger logger = LoggerFactory.getLogger(AccountResultServiceImpl.class);

	@Resource
	private AccountResultDao accountResultDao;


	/**.
	 * 
	 * @param accountResult
	 * @return
	 * @Author:luozhihong
	 * @date:2020年9月9日
	 * @Description:分页查询过账记录
	 */
	@Override
	public PageInfo<AccountResult> listAccountResult(AccountResult accountResult){
		PageUtil.setPageParam(accountResult);
		List<AccountResult> list = accountResultDao.listAccountResult(accountResult);
		AccountResult item=null;
		String accountParam=null;
//		for(int i=0;i<list.size();i++){
//			item = list.get(i);
//			accountParam=item.getAccountParam();
//			JSONObject josnObject = JSONObject.parseObject(accountParam);
//			JSONObject itemParentObject=josnObject.getJSONObject("INPUT");
//			JSONObject itemObject=itemParentObject.getJSONObject("ITEM");
//			JSONArray itemObjectValue=itemObject.getJSONArray("item");
//			JSONObject j = itemObjectValue.getJSONObject(0);
//			if (!"".equals(j)&&j!=null){
//				String materialCode=j.getString("MATNR");
//				Integer arrivalCount=j.getInteger("MENGE");
//				item.setMaterialCode(materialCode);
//				item.setArrivalCount(arrivalCount);
//			}
//		}
		PageInfo<AccountResult> pageInfo = new PageInfo<AccountResult>(list);
		return pageInfo;
	}



}

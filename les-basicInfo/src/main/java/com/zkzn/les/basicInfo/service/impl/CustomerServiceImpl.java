/*
 * 文 件 名:  CustomerServiceImpl.java
 * 版    权:  
 * 描    述:  描述:
 * 修 改 人:  刘松山 
 * 修改时间:  2020年8月3日
 * 跟踪单号:  跟踪单号:
 * 修改单号:  修改单号:
 * 修改内容:  修改内容:
 */
package com.zkzn.les.basicInfo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkzn.les.basicInfo.dao.CustomerDao;
import com.zkzn.les.basicInfo.pojo.Customer;
import com.zkzn.les.basicInfo.service.CustomerService;
import com.zkzn.les.basicInfo.service.DictItemsService;
import com.zkzn.les.basicInfo.pojo.DictItems;

/**.
 *
 * 功能描述:客户信息展示业务实现层
 * 
 * 时间:  2020-08-03 09:19
 *
 * @author  刘松山  
 * 
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private DictItemsService dictItemsService;
	/**.
	 *
	 * 重载方法
	 * 
	 * 功能描述:查询客户列表
	 * 
	 * @param customer
	 * @return
	 * @author  刘松山
	 *
	 * 时间:  2020-08-03 10:05
	 */
	@Override
	public List<Customer> listCustomer(Customer customer) {
		List<Customer> list = customerDao.listCustomer(customer);
		 List<String> dicts = new ArrayList<String>();
			dicts.add("enable");//启用禁用状态
			 
			List<DictItems> dictItems = dictItemsService.listDictItemsByType(dicts);
			
			if(list!=null && dictItems!=null ){
				for(Customer tempCustomer:list){
					for(DictItems dictItem:dictItems){
						if("enable".equals(dictItem.getDictTypeId()) && dictItem.getItemValue().equals(""+tempCustomer.getIsDel()) ){
							tempCustomer.setStatusName(dictItem.getItemName());
						}
						 
						 
					}
				}
			}
	        return list;
	}
}

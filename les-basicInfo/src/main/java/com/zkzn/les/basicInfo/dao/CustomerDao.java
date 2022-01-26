/*
 * 文 件 名:  CustomerDao.java
 * 版    权:  
 * 描    述:  描述:
 * 修 改 人:  刘松山 
 * 修改时间:  2020年8月3日
 * 跟踪单号:  跟踪单号:
 * 修改单号:  修改单号:
 * 修改内容:  修改内容:
 */
package com.zkzn.les.basicInfo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zkzn.les.basicInfo.pojo.Customer;

/**.
 *
 * 功能描述:
 * 
 * 时间:  2020-08-03 09:20
 *
 * @author  刘松山  
 * 
 */
@Mapper
public interface CustomerDao {
	/**
	 * . 
	 *
	 * 功能描述:查询客户列表
	 * 
	 * @param customer
	 * @return
	 * @author  刘松山  
	 *
	 *时间:  2020-08-03 09:21
	 *
	 */
	List<Customer> listCustomer(Customer customer);

}

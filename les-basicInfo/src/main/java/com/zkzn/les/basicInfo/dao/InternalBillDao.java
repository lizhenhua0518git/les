package com.zkzn.les.basicInfo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zkzn.les.basicInfo.pojo.InternalBill;

@Mapper
public interface InternalBillDao {

	/**.
	 * 
	 * @param internalBill
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月22日
	 * @Description:查询内部订单
	 */
	List<InternalBill> listInternalBill(InternalBill internalBill);
}

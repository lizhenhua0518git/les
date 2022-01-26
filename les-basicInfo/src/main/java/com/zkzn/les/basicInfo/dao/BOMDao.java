/*
 * 文 件 名:  BOMDao.java
 * 版    权:  
 * 描    述:  描述:
 * 修 改 人:  刘松山 
 * 修改时间:  2020年8月22日
 * 跟踪单号:  跟踪单号:
 * 修改单号:  修改单号:
 * 修改内容:  修改内容:
 */
package com.zkzn.les.basicInfo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zkzn.les.basicInfo.pojo.SapProcessOrder;
import com.zkzn.les.basicInfo.pojo.SapProcessOrderDetail;

/**.
 *
 * 功能描述:BOM管理数据层
 * 
 * 时间:  2020-08-22 10:12
 *
 * @author  刘松山  
 * 
 */
@Mapper
public interface BOMDao {

	/**
	 * . 
	 *
	 * 功能描述:查询BOM主表记录
	 * 
	 * @param sapProcessOrder
	 * @return
	 * @author  刘松山  
	 *
	 *时间:  2020-08-22 10:15
	 *
	 */
	List<SapProcessOrder> listBOMMainList(SapProcessOrder sapProcessOrder);
	/**
	 * . 
	 *
	 * 功能描述:查询BOM字表记录
	 * 
	 * @param sapProcessOrderDetail
	 * @return
	 * @author  刘松山  
	 *
	 *时间:  2020-08-22 10:16
	 *
	 */
	List<SapProcessOrderDetail> listBOMDetailList(SapProcessOrderDetail sapProcessOrderDetail);
}

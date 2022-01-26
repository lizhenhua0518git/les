/*
 * 文 件 名:  BOMServiceImpl.java
 * 版    权:  
 * 描    述:  描述:
 * 修 改 人:  刘松山 
 * 修改时间:  2020年8月22日
 * 跟踪单号:  跟踪单号:
 * 修改单号:  修改单号:
 * 修改内容:  修改内容:
 */
package com.zkzn.les.basicInfo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkzn.les.basicInfo.dao.BOMDao;
import com.zkzn.les.basicInfo.pojo.SapProcessOrder;
import com.zkzn.les.basicInfo.pojo.SapProcessOrderDetail;
import com.zkzn.les.basicInfo.service.BOMService;
import com.zkzn.les.basicInfo.service.DictItemsService;
import com.zkzn.les.basicInfo.pojo.DictItems;

import javax.annotation.Resource;

/**.
 *
 * 功能描述:BOM管理业务实现层
 * 
 * 时间:  2020-08-22 10:13
 *
 * @author  刘松山  
 * 
 */
@Service
public class BOMServiceImpl implements BOMService {

	@Resource
	private BOMDao bomDao;
	@Autowired
	private DictItemsService dictItemsService;

	/**.
	 *
	 * 重载方法
	 * 
	 * 功能描述:
	 * 
	 * @param sapProcessOrder
	 * @return
	 * @author  刘松山
	 *
	 * 时间:  2020-08-22 10:17
	 */
	@Override
	public List<SapProcessOrder> listBOMMainList(SapProcessOrder sapProcessOrder) {
		List<SapProcessOrder> list =bomDao.listBOMMainList(sapProcessOrder);
		List<String> dicts = new ArrayList<>();
		dicts.add("bom_type");//bom订单类型状态
		dicts.add("bom_status");//bom订单状态
		List<DictItems> dictItems = dictItemsService.listDictItemsByType(dicts);
		if(list!=null && dictItems!=null ){
			for(SapProcessOrder tempSapProcessOrder:list){
				for(DictItems dictItem:dictItems){
					if("bom_status".equals(dictItem.getDictTypeId()) && dictItem.getItemValue().equals(""+tempSapProcessOrder.getOrderStatus()) ){
						tempSapProcessOrder.setOrderStatusName(dictItem.getItemName());
					}
					if("bom_type".equals(dictItem.getDictTypeId()) && dictItem.getItemValue().equals(""+tempSapProcessOrder.getOrderType()) ){
						tempSapProcessOrder.setOrderTypeName(dictItem.getItemName());
					}
				}
			}
		}
		return list;
	}

	/**.
	 *
	 * 重载方法
	 * 
	 * 功能描述:
	 * 
	 * @param sapProcessOrderDetail
	 * @return
	 * @author  刘松山
	 *
	 * 时间:  2020-08-22 10:17
	 */
	@Override
	public List<SapProcessOrderDetail> listBOMDetailList(SapProcessOrderDetail sapProcessOrderDetail) {
		List<SapProcessOrderDetail> list = bomDao.listBOMDetailList(sapProcessOrderDetail);
		List<String> dicts = new ArrayList<String>();
		 
		dicts.add("bom_status");//bom订单状态
		 
		List<DictItems> dictItems = dictItemsService.listDictItemsByType(dicts);
		
		if(list!=null && dictItems!=null ){
			for(SapProcessOrderDetail tempSapProcessOrderDetail:list){
				for(DictItems dictItem:dictItems){
					if("bom_status".equals(dictItem.getDictTypeId()) && dictItem.getItemValue().equals(""+tempSapProcessOrderDetail.getStatus()) ){
						tempSapProcessOrderDetail.setStatusName(dictItem.getItemName());
					}
					 
					 
				}
			}
		}
		return list;
	 
	}
	
}

/*
 * 文 件 名:  WorkAreaService.java
 * 版    权:  
 * 描    述:  描述:
 * 修 改 人:  刘松山 
 * 修改时间:  2020年5月13日
 * 跟踪单号:  跟踪单号:
 * 修改单号:  修改单号:
 * 修改内容:  修改内容:
 */
package com.zkzn.les.basicInfo.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import com.zkzn.les.basicInfo.pojo.WorkArea;
import com.zkzn.les.basicInfo.pojo.StoragePosition;


/**.
 *
 * 功能描述:作业区域业务逻辑层
 * 
 * 时间:  2020-05-13 11:52
 *
 * @author  刘松山  
 * 
 */
public interface WorkAreaService {

	/**
	 * 作业区域列表查询
	 * @param workArea
	 * @return
	 */
	PageInfo<WorkArea> listWorkArea(WorkArea workArea);

	PageInfo<WorkArea> queryWorkAreaByWareHouseCode(WorkArea w);

	/**
	 * 新增作业区域信息
	 * @param workArea
	 * @return
	 */
	int saveWorkArea(WorkArea workArea);

	/**
	 * 修改作业区域信息
	 * @param workArea
	 * @return
	 */
	int  updateWorkArea(WorkArea workArea);

	/**
	 * 删除作业区域
	 * @param ids
	 * @return
	 */
	int deleteWorkArea(List<String>  ids);

	/**
	 * 根据区域编码查询区域信息
	 * @param workAreaCodeIds
	 * @return
	 */
	List<Map<String,String>> getWorkAreaByCodeList(List<String> workAreaCodeIds);

	/**
	 * 批量修改仓位区域关系
	 * @param storagePositionList
	 * @return
	 */
	int updateStoragePositionList(List<StoragePosition> storagePositionList);

	/**
	 * . 
	 *
	 * 功能描述:保存作业区域 批量
	 * 
	 * @param list
	 * @return
	 * @author  刘松山  
	 *
	 *时间:  2020-05-13 14:22
	 *
	 */
	int saveWorkAreaBatch(List<Map<String,Object>> list);
	/**
	 * . 
	 *
	 * 功能描述:根据作业区域编码查询区域作业
	 * 
	 * @param w
	 * @return
	 * @author  刘松山  
	 *
	 *时间:  2020-05-13 14:23
	 *
	 */
	WorkArea getWorkAreaByCode(WorkArea w);
	
}

package com.zkzn.les.basicInfo.dao;

import java.util.List;
import java.util.Map;


import com.zkzn.les.basicInfo.pojo.StoragePosition;
import org.apache.ibatis.annotations.Mapper;

import com.zkzn.les.basicInfo.pojo.WorkArea;


/**.
 *
 * 功能描述:作业区域数据层
 * 
 * 时间:  2020-05-13 11:51
 *
 * @author  刘松山  
 * 
 */
@Mapper
public interface WorkAreaDao {

	/**
	 * 作业区域列表查询
	 * @param workArea
	 * @return
	 */
	List<WorkArea> listWorkArea(WorkArea workArea);

	List<WorkArea> queryWorkAreaByWareHouseCode(WorkArea w);

	/**
	 * 保存作业区域
	 * @param workArea
	 * @return
	 */
	int saveWorkArea(WorkArea workArea);

	/**
	 * 修改作业区域
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
	 * @param codeList
	 * @return
	 */
	List<Map<String, String>> getWorkAreaByCodeList(List<String> list);

	/**
	 * 批量修改仓位区域关系
	 * @param list
	 * @return
	 */
	int updateStoragePositionList(List<StoragePosition> list);

	/**
	 * . 
	 *
	 * 功能描述:修改作业区域
	 * 
	 * @param list
	 * @return
	 * @author  刘松山  
	 *
	 *时间:  2020-05-13 14:22
	 *
	 */
	int updateWorkAreaByList(List<Map<String,Object>> list);
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

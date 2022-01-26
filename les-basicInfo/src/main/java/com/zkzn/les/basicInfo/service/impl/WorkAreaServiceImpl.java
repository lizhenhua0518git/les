package com.zkzn.les.basicInfo.service.impl;

import java.util.List;
import java.util.Map;


import com.zkzn.les.basicInfo.pojo.StoragePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.dao.WorkAreaDao;
import com.zkzn.les.basicInfo.pojo.WorkArea;
import com.zkzn.les.basicInfo.service.WorkAreaService;
import com.zkzn.les.basicInfo.util.PageUtil;


/**.
 *
 * 功能描述:
 * 
 * 时间:  2020-05-13 11:52
 *
 * @author  刘松山  
 * 
 */
@Service
public class WorkAreaServiceImpl implements WorkAreaService {
 
	@Autowired
	private WorkAreaDao workAreaDao;

	@Override
	public PageInfo<WorkArea> listWorkArea(WorkArea workArea) {
		PageUtil.setPageParam(workArea);
		List<WorkArea> list = workAreaDao.listWorkArea(workArea);
		PageInfo<WorkArea> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public PageInfo<WorkArea> queryWorkAreaByWareHouseCode(WorkArea w) {
		PageUtil.setPageParam(w);
		List<WorkArea> list = this.workAreaDao.queryWorkAreaByWareHouseCode(w);
		PageInfo<WorkArea> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	public int saveWorkArea(WorkArea workArea) {
//		String workAreaCode = "";
//		if (workArea.getWarehouseCode().equals("00500100")){
//			workAreaCode += "ZXK";
//		}
		workArea.setWorkAreaCode(workArea.getWarehouseCode()+"-"+workArea.getWorkAreaName()+"-001");
		workArea.setAreaType("ZYQ");
		int resultInt = workAreaDao.saveWorkArea(workArea);
		return resultInt;
	}

	@Override
	public int updateWorkArea(WorkArea workArea) {
		int resultInt = workAreaDao.updateWorkArea(workArea);
		return resultInt;
	}

	@Override
	public int deleteWorkArea(List<String> ids) {
		int resultInt = this.workAreaDao.deleteWorkArea(ids);
		return resultInt;
	}

	@Override
	public List<Map<String, String>> getWorkAreaByCodeList(List<String> workAreaCodeIds) {
		List<Map<String, String>> list = workAreaDao.getWorkAreaByCodeList(workAreaCodeIds);
		return list;
	}

	@Override
	public int updateStoragePositionList(List<StoragePosition> storagePositionList) {
		int i = workAreaDao.updateStoragePositionList(storagePositionList);
		return i;
	}

	/**.
	 *
	 * 重载方法
	 * 
	 * 功能描述:
	 * 
	 * @param list
	 * @return
	 * @author  刘松山
	 *
	 * 时间:  2020-05-13 14:34
	 */
	@Override
	public int saveWorkAreaBatch(List<Map<String,Object>>list) {
		int resultInt = this.workAreaDao.saveWorkAreaBatch(list);
		return resultInt;
	}

	/**.
	 *
	 * 重载方法
	 * 
	 * 功能描述:
	 * 
	 * @param w
	 * @return
	 * @author  刘松山
	 *
	 * 时间:  2020-05-13 14:34
	 */
	@Override
	public WorkArea getWorkAreaByCode(WorkArea w) {
		WorkArea workArea = this.workAreaDao.getWorkAreaByCode(w);
		return workArea;
	}
}

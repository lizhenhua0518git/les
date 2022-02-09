package com.zkzn.les.wms.receiveRecord.service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.wms.receiveRecord.pojo.ReceiveTaskDetailPojo;
import com.zkzn.les.wms.receiveRecord.pojo.ReceiveTaskPojo;

/**
 * 点收任务
 */
public interface ReceiveRecordService {


	/**
	 * 点收任务管理列表查询
	 * @param receiveTaskPojo
	 * @return
	 */
	PageInfo<ReceiveTaskPojo> listReceiveRecordPage(ReceiveTaskPojo receiveTaskPojo);

	/**
	 * 点收任务详情查询
	 * @param receiveTaskDetailPojo
	 * @return
	 */
	PageInfo<ReceiveTaskDetailPojo> listReceiveDetailRecordPage(ReceiveTaskDetailPojo receiveTaskDetailPojo);

	}

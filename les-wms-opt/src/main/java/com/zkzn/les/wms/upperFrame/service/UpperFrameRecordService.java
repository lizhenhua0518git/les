package com.zkzn.les.wms.upperFrame.service;


import com.github.pagehelper.PageInfo;
import com.zkzn.les.wms.upperFrame.pojo.UpperFrameRecord;

/**
 * 上架任务
 */
public interface UpperFrameRecordService {


	/**
	 * 查询上架列表
	 * @param upperFrameRecord
	 * @return
	 */
	PageInfo<UpperFrameRecord> listUpperFrameRecord(UpperFrameRecord upperFrameRecord);

}

package com.zkzn.les.wms.upperFrame.service.impl;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.common.util.page.PageUtil;
import com.zkzn.les.wms.upperFrame.dao.UpperFrameRecordDao;
import com.zkzn.les.wms.upperFrame.pojo.UpperFrameRecord;
import com.zkzn.les.wms.upperFrame.service.UpperFrameRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UpperFrameRecordServiceImpl implements UpperFrameRecordService {

	@Autowired
	private UpperFrameRecordDao upperFrameRecordDao;

	public PageInfo<UpperFrameRecord> listUpperFrameRecord(UpperFrameRecord upperFrameRecord){
		PageUtil.setPageParam(upperFrameRecord);
		reAssembleParam(upperFrameRecord);
		List<UpperFrameRecord> list = upperFrameRecordDao.listUpperFrameRecord(upperFrameRecord);
		PageInfo<UpperFrameRecord> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	/**
	 * @param upperFrameRecord
	 * @Author:luozhihong
	 * @date:2020年9月29日
	 * @Description:参数封装
	 */
	public void reAssembleParam(UpperFrameRecord upperFrameRecord) {
		String upperTimeStr = upperFrameRecord.getUpperTimeStr();//创建时间
		if (!"".equals(upperTimeStr) && upperTimeStr != null){
			String[] split = upperTimeStr.split(" - ");
			upperFrameRecord.setStartUpperTime(split[0]);
			upperFrameRecord.setEndUpperTime(split[1]);
		}
	}



}

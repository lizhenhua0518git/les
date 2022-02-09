package com.zkzn.les.wms.receiveRecord.dao;


import com.zkzn.les.wms.receiveRecord.pojo.ReceiveTaskDetailPojo;
import com.zkzn.les.wms.receiveRecord.pojo.ReceiveTaskPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 收货信息
 */
@Mapper
public interface ReceiveRecordDao {

	/**
	 * 点收任务主表查询
	 * @param receiveTaskPojo
	 * @return
	 */
	List<ReceiveTaskPojo> listReceiveRecord(ReceiveTaskPojo receiveTaskPojo);

	/**
	 * 点收任务详情查询
	 * @param receiveTaskDetailPojo
	 * @return
	 */
	List<ReceiveTaskDetailPojo> listReceiveDetailRecord(ReceiveTaskDetailPojo receiveTaskDetailPojo);

	/**
	 * 新增点收主表数据
	 * @param receiveTaskPojo
	 * @return
	 */
	int addReceiveTaskPojo(ReceiveTaskPojo receiveTaskPojo);

	/**
	 * 新增点收详情数据
	 * @param receiveTaskDetailPojoList
	 * @return
	 */
	int addReceiveTaskDetailPojoList(@Param("receiveTaskDetailPojoList") List<ReceiveTaskDetailPojo> receiveTaskDetailPojoList);

}

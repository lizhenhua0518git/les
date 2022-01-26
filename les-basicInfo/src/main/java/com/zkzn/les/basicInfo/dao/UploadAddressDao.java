package com.zkzn.les.basicInfo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zkzn.les.basicInfo.pojo.UploadAddress;

/**
 * 
 * @author wangzhou
 * @date 2020年8月22日
 * @Description 卸货地点 dao
 */
@Mapper
public interface UploadAddressDao {

	/**.
	 * 
	 * @param uploadAddress
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月22日
	 * @Description:保存卸货地点信息
	 */
	int saveUploadAddress(UploadAddress uploadAddress);
	/**.
	 * 
	 * @param ids
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月22日
	 * @Description:删除卸货地点
	 */
	int deleteUploadAddress(List<String> ids);
	
	/**.
	 * 
	 * @param uploadAddress
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月22日
	 * @Description:更新卸货地点
	 */
	int updateUploadAddress(UploadAddress uploadAddress);
	
	/**.
	 * 
	 * @param uploadAddress
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月22日
	 * @Description:查询卸货地点清单
	 */
	List<UploadAddress> listUploadAddress(UploadAddress uploadAddress);
}

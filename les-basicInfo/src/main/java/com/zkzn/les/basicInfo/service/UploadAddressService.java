package com.zkzn.les.basicInfo.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.pojo.UploadAddress;

/**.
 * 
 * @author wangzhou
 * @date 2020年8月22日
 * @Description 卸货地点 service
 */
public interface UploadAddressService {

	
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
	/**.
	 * 
	 * @param uploadAddress
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月22日
	 * @Description:分页查询卸货地点清单
	 */
	PageInfo<UploadAddress> listUploadAddressPage(UploadAddress uploadAddress);
}

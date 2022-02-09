package com.zkzn.les.basicInfo.service;

import java.util.List;


import com.github.pagehelper.PageInfo;

import com.zkzn.les.basicInfo.pojo.ResourceApiAdress;

public interface ResourceApiAddressService {
	/**.
	 * 
	 * 功能描述：删除菜单apid接口地址
	 * 作者：luozhihong
	 * 时间:2020年9月1日
	 * @param ids
	 * @return
	 */
	int deleteResourceApiAdress(List<String> ids);
	
	/**.
	 * 
	 * 功能描述：新增菜单apid接口地址
	 * 作者：luozhihong
	 * 时间:2020年9月1日
	 * @param resourceApiAdresss
	 * @return
	 */
	int saveResourceApiAdresss(List<ResourceApiAdress> resourceApiAdresss); 

	/**.
	 * 
	 * 功能描述：修改菜单apid接口地址
	 * 作者：luozhihong
	 * 时间:2020年9月1日
	 * @param resourceApiAdresss
	 * @return
	 */
	int updateResourceApiAdresss(List<ResourceApiAdress> resourceApiAdresss);
	
	/**.
	 * 
	 * 功能描述：分页查询菜单api接口地址
	 * 作者：luozhihong
	 * 时间:2020年9月1日
	 * @param resourceApiAdress
	 * @return
	 */
	PageInfo<ResourceApiAdress> listResourceApiAddressPage(ResourceApiAdress resourceApiAdress);
	
	
}

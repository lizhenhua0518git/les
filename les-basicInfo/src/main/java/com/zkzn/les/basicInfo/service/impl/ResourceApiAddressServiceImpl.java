package com.zkzn.les.basicInfo.service.impl;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.dao.ResourceApiAdressDao;

import com.zkzn.les.basicInfo.service.ResourceApiAddressService;

import com.zkzn.les.basicInfo.util.PageUtil;

import com.zkzn.les.basicInfo.pojo.ResourceApiAdress;

@Service
public class ResourceApiAddressServiceImpl implements ResourceApiAddressService{

	@Autowired
	private ResourceApiAdressDao resourceApiAdressDao;
	
	/**.
	 * 
	 * 功能描述：删除菜单apid接口地址
	 * 作者：luozhihong
	 * 时间:2020年9月1日
	 * @param ids
	 * @return
	 */
	@Override
	public int deleteResourceApiAdress(List<String> ids) {
		// TODO Auto-generated method stub
		return resourceApiAdressDao.deleteResourceApiAdress(ids);
	}
	/**.
	 * 
	 * 功能描述：新增菜单apid接口地址
	 * 作者：luozhihong
	 * 时间:2020年9月1日
	 * @param resourceApiAdresss
	 * @return
	 */
	@Override
	public int saveResourceApiAdresss(List<ResourceApiAdress> resourceApiAdresss) {
		// TODO Auto-generated method stub
		return resourceApiAdressDao.saveResourceApiAdresss(resourceApiAdresss);
	}
	/**.
	 * 
	 * 功能描述：修改菜单apid接口地址
	 * 作者：luozhihong
	 * 时间:2020年9月1日
	 * @param resourceApiAdresss
	 * @return
	 */
	@Override
	public int updateResourceApiAdresss(List<ResourceApiAdress> resourceApiAdresss) {
		// TODO Auto-generated method stub
		return resourceApiAdressDao.updateResourceApiAdresss(resourceApiAdresss);
	}

	
	/**.
	 * @Description:分页查询菜单api接口地址
	 * @Author:luozhihong
	 * @date:2020年9月1日
	 * @param resourceApiAdress
	 * @return
	 */
	@Override
	public PageInfo<ResourceApiAdress> listResourceApiAddressPage(ResourceApiAdress resourceApiAdress){
		PageUtil.setPageParam(resourceApiAdress);
		List<ResourceApiAdress> list = resourceApiAdressDao.listResourceApiAddressPage(resourceApiAdress);
		PageInfo<ResourceApiAdress> pageInfo = new PageInfo<ResourceApiAdress>(list);
		return pageInfo;
	}
	


	
	
}

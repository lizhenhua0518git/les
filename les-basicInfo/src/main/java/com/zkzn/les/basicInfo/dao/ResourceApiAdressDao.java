package com.zkzn.les.basicInfo.dao;


import com.zkzn.les.basicInfo.pojo.ResourceApiAdress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ResourceApiAdressDao {
	
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
	
    int deleteByPrimaryKey(String id);

    int insert(ResourceApiAdress record);

    ResourceApiAdress selectByPrimaryKey(String id);

    /**.
	 * @Description:分页查询菜单api接口地址
	 * @Author:luozhihong
	 * @date:2020年9月1日
	 * @param resourceApiAdress
	 * @return
	 */
    List<ResourceApiAdress> listResourceApiAddressPage(ResourceApiAdress resourceApiAdress);

    int updateByPrimaryKey(ResourceApiAdress record);
}
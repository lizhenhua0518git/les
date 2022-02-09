package com.zkzn.les.uas.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.uas.pojo.Resource;

public interface ResourceService {


	/**.
	 *
	 * 功能描述：通过id查询资源信息
	 * 作者：wangzhou
	 * 时间:2018年6月12日
	 * @param id
	 * @return
	 */
	Resource getById(String id);

	/**.
	 *
	 * 功能描述：通过资源类型查询资源信息
	 * 作者：wangzhou
	 * 时间：2018年10月12日
	 * @param type  资源类型
	 * @param status 资源状态
	 * @param attach 手持或PC
	 * @return
	 */
	List<Resource> listResourceByType(String type,String status,String attach);
}

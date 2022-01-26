package com.zkzn.les.uas.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.uas.pojo.Orgnization;



/**.
 * 
 * @author wangzhou
 *
 */
public interface OrgnizationService {

	/**.
	 *
	 * 功能描述：登录时默认存仓库
	 * 作者：wangzhou
	 * 时间：2018年11月6日
	 * @param currentUid
	 * @param type
	 */
	void defualtWarehouse(String currentUid);

}

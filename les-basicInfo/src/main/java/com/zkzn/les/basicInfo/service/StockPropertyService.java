package com.zkzn.les.basicInfo.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.pojo.StockProperty;

/**.
 * 
 * @author wangzhou
 * 盘点配置 service
 */
public interface StockPropertyService {

	
	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年4月2日下午2:34:36
	 * List<StockProperty>
	 * @param stockProperty
	 * @return
	 * 功能描述:盘点配置列表查询
	 */
	List<StockProperty> listStockProperty(StockProperty stockProperty);
	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年4月2日下午2:37:16
	 * PageInfo<StockProperty>
	 * @param stockProperty
	 * @return
	 * 功能描述:盘点配置分页查询
	 */
	PageInfo<StockProperty> listStockPropertyPage(StockProperty stockProperty);
	
	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年4月2日下午3:10:48
	 * int
	 * @param stockProperty
	 * @return
	 * 功能描述:保存盘点配置信息
	 */
	int saveStockProperty(StockProperty stockProperty);
	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年4月2日下午3:41:37
	 * int
	 * @param stockProperty
	 * @return
	 * 功能描述:修改盘点配置信息
	 */
	int updateStockProperty(StockProperty stockProperty);
	
	
	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年4月3日下午12:44:35
	 * int
	 * @param ids
	 * @return
	 * 功能描述:删除盘点配置信息
	 */
	int removeStockProperty(List<String> ids);
	
	
}

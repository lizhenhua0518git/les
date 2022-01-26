package com.zkzn.les.basicInfo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.pojo.DictItems;

/**.
 * 
 * @author wangzhou
 *  数据 字典Service
 */
public interface DictItemsService {
	
	/**.
	 * 
	 * 功能描述：新增数据字典
	 * 作者：wangzhou
	 * 时间：2018年6月28日
	 * @param dictItems
	 * @return
	 */
	int saveDictItems(DictItems dictItems);
	
	/**.
	 * 
	 * 功能描述：删除数据字典
	 * 作者：wangzhou
	 * 时间：2018年6月28日
	 * @param id
	 * @return
	 */
	int deleteDictItems(List<String> id);
	/**.
	 * 
	 * 功能描述：修改数据字典
	 * 作者：wangzhou
	 * 时间：2018年6月28日
	 * @param dictItems
	 * @return
	 */
	int updateDictItems(DictItems dictItems);
	
	/**.
	 * 
	 * 功能描述：查询数据字典列表
	 * 作者：wangzhou
	 * 时间：2018年6月28日
	 * @param dictItems
	 * @return
	 */
	PageInfo<DictItems> listDictItemsList(DictItems dictItems);
	
	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年3月31日上午9:19:23
	 * List<DictItems>
	 * @param type
	 * @return
	 * 功能描述:通过字典类型查询字典信息 
	 */
	List<DictItems> listDictItemsByType(List<String> type);
	
}

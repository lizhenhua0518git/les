package com.zkzn.les.basicInfo.dao;

import com.zkzn.les.basicInfo.pojo.DictItems;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**.
 * 
 * @author wangzhou
 * 字典的实体 dao
 */
@Mapper
public interface DictItemsDao {

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
	List<DictItems> listDictItemsList(DictItems dictItems);
	/**.
	 * 
	 * 功能描述：通过id查询字典信息
	 * 作者：wangzhou
	 * 时间：2018年6月28日
	 * @param id
	 * @return
	 */
	DictItems getDictItemsById(String id);
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
	
	   /**.
     * 
     * 功能描述：通过字典名称查询字典信息
     * 作者：wangzhou
     * 时间：2018年7月27日
     * @param itemName
     * @param dictName
     * @return
     */
    DictItems getDictItemByName(@Param("itemName")String itemName,@Param("dictName")String dictName);
}

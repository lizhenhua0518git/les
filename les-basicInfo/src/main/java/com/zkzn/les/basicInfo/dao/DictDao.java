package com.zkzn.les.basicInfo.dao;

import com.zkzn.les.basicInfo.pojo.Dict;
import com.zkzn.les.basicInfo.pojo.DictItems;
import feign.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**.
 *
 *
 * 功能描述：数据字典类型dao
 * @author wangzhou
 * 时间：2018年6月27日
 */
@Mapper
public interface DictDao {

	/**.
	 *
	 * 功能描述：新增数据字典类型
	 * 作者：wangzhou
	 * 时间：2018年6月27日
	 * @param dict
	 * @return
	 */
	int saveDict(Dict dict);

	/**.
	 *
	 * 功能描述：删除数据字典类型
	 * 作者：wangzhou
	 * 时间：2018年6月27日
	 * @param id
	 * @return
	 */
	int deleteDict(List<String> id);

	/**.
	 *
	 * 功能描述：修改数据字典类型
	 * 作者：wangzhou
	 * 时间：2018年6月27日
	 * @param dict
	 * @return
	 */
	int updateDict(Dict dict);
	/**.
	 *
	 * 功能描述：查询数据字典类型
	 * 作者：wangzhou
	 * 时间：2018年6月27日
	 * @param dict
	 * @return
	 */
	List<Dict> listDictList(Dict dict);
	/**.
	 *
	 * 功能描述：用于查重的功能
	 * 作者：wangzhou
	 * 时间：2018年6月28日
	 * @param dict
	 * @return
	 */
	Dict getDict(Dict dict);

	/**
	 * 初始化库位库存流水库存类型
	 * @return
	 */
	List<Map<String, String>> initStockType(@Param("dictType") String dictType);
	/* *
	 * @Author 刘松山
	 * @Description 根据名称标记 查询一组字典类型
	 * @Date 9:48 2021/6/16
	 * @Param
	 * @return
	 **/

	List<Dict> listDict(Dict dict);

	List<DictItems> initDictItemList(@Param("dictType") String dictType);
}

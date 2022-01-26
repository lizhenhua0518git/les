package com.zkzn.les.basicInfo.service;


import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.pojo.Dict;
import com.zkzn.les.basicInfo.pojo.DictItems;

/**.
 *
 *
 * 功能描述：数据字典类型service
 * @author wangzhou
 * 时间：2018年6月27日
 */
public interface DictService {

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
	PageInfo<Dict> listDictList(Dict dict);

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
	List<Map<String,String>> initStockType();

	/**
	 * 初始化库位库存流水库存状态
	 * @return
	 */
	List<Map<String,String>> initStockStatus();
    /* *
     * @Author 刘松山
     * @Description 根据名称标记 查询一组字典类型
     * @Date 9:48 2021/6/16
     * @Param
     * @return
     **/

	List<Dict> listDict(Dict dict);

	List<DictItems> initDictItemList(String dictType);
}

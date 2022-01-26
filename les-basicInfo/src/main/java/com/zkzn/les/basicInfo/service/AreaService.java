package com.zkzn.les.basicInfo.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.warehouse.pojo.Warehouse;
import com.zkzn.les.basicInfo.pojo.Area;

/**.
 * 
 *
 * 功能描述：区域信息的service
 * @author wangzhou
 * 时间：2018年7月3日
 */
public interface AreaService {

	/**.
	 * 
	 * 功能描述：新增区域信息
	 * 作者：wangzhou
	 * 时间：2018年7月2日
	 * @param area
	 * @return
	 */
	int saveArea(Area area);
	
	/**.
	 * 
	 * 功能描述：批量删除
	 * 作者：wangzhou
	 * 时间：2018年7月2日
	 * @param id
	 * @return
	 */
	int deleteArea(List<String> id);
	
	/**.
	 * 
	 * 功能描述：修改区域信息
	 * 作者：wangzhou
	 * 时间：2018年7月2日
	 * @param area
	 * @return
	 */
	int updateArea(Area area);
	
	/**.
	 * 功能描述：查询区域信息
	 * 作者：wangzhou
	 * 时间：2018年7月2日
	 * @param area
	 * @return
	 */
	PageInfo<Area> listAreaByPage(Area area);
	
	/**.
	 * 
	 * 功能描述：查询区域列表信息
	 * 作者：wangzhou
	 * 时间：2018年9月5日
	 * @param area
	 * @return
	 */
	List<Area> listArea(Area area);

	/**.
	 * 
	 * 功能描述：通过区域编号查询区域信息
	 * 作者：wangzhou
	 * 时间：2018年9月28日
	 * @param areaCode
	 * @param warehouse
	 * @return
	 */
	Area getAreaByCode(@Param("areaCode")String areaCode,@Param("warehouse")String warehouse);

	   /**
     * .
     * 功能描述: 批量更新区域状态
     * 作者:何闰平
     * 时间:2020年3月29日 下午1:03:33
     * @param ids
     * @param remark
     * @param status
     * @return
     */
   int changeStatus(List<String> ids, String remark, int status,String modifier,Date modifiedTime);

	/**
	 * .
	 * 功能描述:格式化区域树
	 * 作者:何闰平
	 * 时间:2020年4月1日 下午5:59:44
	 * @param areaList
	 * @return
	 */
	List<Map<String, Object>> formatAreaTree(List<Area> areaList, List<String> areaIds );
	
	/**
	 * 功能描述:查询仓位的所有仓库编码
	 * 作者:何闰平
	 * 时间:2020年4月2日 下午1:56:04
	 * @return
	 */
	List<String> getWarehouse();
	
	/**
	 * .
	 * 功能描述:格式化区域-仓库树
	 * 作者:何闰平
	 * 时间:2020年4月2日 下午1:56:25
	 * @param originalWarehouseList
	 * @param warehouseCode
	 * @return
	 */
	List<Map<String, Object>> formatTreeRoot(List<Warehouse> originalWarehouseList, List<String> warehouseCode);
	
	 /**
     * .
     * 功能描述:查询有子区域的区域ID
     * 作者:何闰平
     * 时间:2020年4月2日 下午1:52:57
     * @param originalAreaList
     * @return
     */
	List<String> hasChildren(List<Area> originalAreaList);

}

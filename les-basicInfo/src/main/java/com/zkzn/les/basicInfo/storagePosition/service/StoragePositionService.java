package com.zkzn.les.basicInfo.storagePosition.service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.pojo.Area;
import com.zkzn.les.basicInfo.pojo.StoragePosition;
import com.zkzn.les.basicInfo.pojo.WorkArea;
import com.zkzn.les.basicInfo.storagePosition.pojo.StoragePositionPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;



/**.
 *
 *
 * 功能描述：储位信息service层
 * @author wangzhou
 * 时间：2018年7月4日
 */
public interface StoragePositionService {

	/**
	 * 查询库位信息
	 * @param storagePositionPojo
	 * @return
	 */
	List<StoragePositionPojo> listStoragePosition(StoragePositionPojo storagePositionPojo);

	/**
	 * 保存库位信息
	 * @param storagePositionPojo
	 * @return
	 */
	int saveStoragePosition(StoragePositionPojo storagePositionPojo);

	/**
	 * 修改库位信息
	 * @param storagePositionPojo
	 * @return
	 */
	int updateStoragePosition(StoragePositionPojo storagePositionPojo);

	/**
	 * 批量删除库位信息
	 * @param storagePositionIds
	 * @return
	 */
	int deleteStoragePosition(List<Integer> storagePositionIds);





















	/**.
	 *
	 * 功能描述：通过id查询储位信息
	 * 作者：wangzhou
	 * 时间：2018年7月4日
	 * @param id
	 * @return
	 */
	StoragePosition getStorageById(String id);

	/**
	 * 功能描述：通过工位号查找仓位
	 * @param stationCode
	 * @return
	 */
	List<StoragePosition> queryStorageByIdStationCode(StoragePosition storagePosition);

	/**
	 * 查询工位信息
	 * yzn
	 * 2018-09-28
	 * @return
	 */
	PageInfo<StoragePosition> getStationCodeList(StoragePosition storagePosition);
	/**
	 * 查找空仓位
	 * @param storagePosition
	 * @return
	 */
	List<StoragePosition> queryStorage(StoragePosition storagePosition);
	/**
	 * 方法说明: 根据物料编号和工厂查询仓位信息
	 * @param map
	 * @return
	 * 作者: liyan
	 * 2018年11月15日
	 */
	List<StoragePosition> findByMaterialCodeAndFactory(Map<String,Object> map);

	/**
	 * 通过仓位号，查询
	 * @param storagePosition
	 * @return
	 */
	List<StoragePosition>getPosition(StoragePosition storagePosition);

	/**
	 * 功能描述：通过工位号查找空仓位
	 * @param stationCode
	 * @return
	 */
	List<Map<String,Object>> getStorage(StoragePosition storagePosition);

	/**
	 * 功能描述：通过工位号查找空仓位
	 * @param stationCode
	 * @return
	 */
	List<Map<String,Object>> getStorageBinId(StoragePosition storagePosition);
	/**.
	 *
	 * 功能描述：查找仓位
	 * 作者：wangzhou
	 * 时间：2019年9月3日
	 * @param list
	 * @return
	 */
	List<Map<String,Object>> queryStorageBin(@Param("list")List<String> list,@Param("warehouse")String warehouse,String orderType);
	/**.
	 *
	 * 功能描述：查找空仓位
	 * 作者：wangzhou
	 * 时间：2019年9月3日
	 * @param warehouse
	 * @return
	 */
	List<Map<String,Object>> queryEmptyPosition(Map<String,Object> params);
	/**.
	 *
	 * 功能描述：查找可用仓位
	 * 作者：wangzhou
	 * 时间：2019年10月16日
	 * @param list
	 * @param warehouse
	 * @param orderType
	 * @return
	 */
	List<Map<String,Object>> queryStorageBinCount(@Param("list")List<String> list,@Param("warehouse")String warehouse,@Param("orderType")String orderType);
	/**.
	 *
	 * 功能描述：通过仓位编码查询仓位信息
	 * 作者：wangzhou
	 * 时间：2019年9月11日
	 * @param warehouse
	 * @param positionCode
	 * @return
	 */
	StoragePosition queryPositionByCode(@Param("warehouse")String warehouse,@Param("positionCode")String positionCode);

	/**
     * .
     * 功能描述:查询有子仓位的仓位ID
     * 作者:何闰平
     * 时间:2020年4月2日 下午1:52:57
     * @param originalStoragePositionList
     * @return
     */
	List<String> hasChildren(List<StoragePosition> originalStoragePositionList);

	/**
     * .
     * 功能描述:格式化仓位树
     * 作者:何闰平
     * 时间:2020年4月1日 下午5:59:44
     * @param areaList
     * @return
     */
	List<Map<String, Object>> formatPositionTree(List<StoragePosition> storagePositionList, List<String> storagePositionIds );

	   /**
     * .
     * 功能描述: 批量更新仓位状态
     * 作者:何闰平
     * 时间:2020年3月29日 下午1:03:33
     * @param ids
     * @param remark
     * @param status
     * @return
     */
   int changeStatus(List<String> ids, String remark, int status);

   /**
    * .
    * 功能描述:导入仓位信息
    * 作者:何闰平
    * 时间:2020年4月20日 下午2:43:57
    * @param areaMap
    * @return
    */
   int importStorage(List<Map<String,Object>> areaMap);

   /**
    * .
    * 功能描述:批量更新仓位信息
    * 作者:何闰平
    * 时间:2020年4月20日 下午2:44:10
    * @param areaMap
    * @return
    */
   int updateStorageBatch(List<Map<String,Object>> areaMap);

	/**
	 * 根据仓位号+仓库号查询仓位信息
	 * @param map
	 * @return
	 */
	List<StoragePosition> getStoragePositionByCode(StoragePosition storagePosition);

	/**
	 * 获取仓库编码信息
	 * @return
	 */
	List<String> getWareHouse();

	/**
	 * 获取库位编码，库位仓库编码
	 * @return
	 */
	List<StoragePosition> getStoragePosition();

	/**
	 * 获取区域编码，区域仓库编码
	 * @return
	 */
	List<Area> getArea();

	/**
	 * 获取作业区域编码，作业区仓库编码
	 * @return
	 */
	List<WorkArea> getWorkArea();

	/**
	 * 批量更新库位表数据
	 * @param storagePositionList
	 * @return
	 */
	int updateStorageositionList(List<StoragePosition> storagePositionList);

	/**
	 * 批量新增库位表数据
	 * @param storagePositionList
	 * @return
	 */
	int saveStoragePositionList(List<StoragePosition> storagePositionList);
}

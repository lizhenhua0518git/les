package com.zkzn.les.basicInfo.warehouse.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zkzn.les.basicInfo.warehouse.pojo.WarehousePojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zkzn.les.basicInfo.warehouse.pojo.Warehouse;

@Mapper
public interface WarehouseDao {

	/**
	 * 初始化仓库下拉框
	 * @param warehousePojo
	 * @return
	 */
	List<WarehousePojo> initWarehouseSelect(WarehousePojo warehousePojo);

    /**
     * .
     * 功能描述:新增仓库信息
     * 作者:何闰平
     * 时间:2020年3月29日 下午1:59:59
     * @param warehouse
     * @return
     */
    int saveWarehouse(Warehouse warehouse);

    /**
     * .
     * 功能描述:批量删除仓库信息
     * 作者:何闰平
     * 时间:2020年3月29日 下午1:59:48
     * @param id
     * @return
     */
    int deleteWarehouse(List<String> id);

    /**
     * .
     * 功能描述:更新仓库信息
     * 作者:何闰平
     * 时间:2020年3月29日 下午1:59:35
     * @param warehouse
     * @return
     */
    int updateWarehouse(Warehouse warehouse);

    /**
     * .
     * 功能描述:查询仓库信息
     * 作者:何闰平
     * 时间:2020年3月29日 下午1:59:18
     * @param warehouse
     * @return
     */
    List<Warehouse> listWarehouse(@Param("warehouse") Warehouse warehouse);

   /**
    * .
    * 功能描述:通过id查询仓库信息
    * 作者:何闰平
    * 时间:2020年3月29日 下午1:59:12
    * @param id
    * @return
    */
    Warehouse getWarehouseById(String id);

   /**
    * .
    *
    * 功能描述:批量修改仓库状态
    *
    * @param ids
    * @param status
    * @param modifierId
    * @param modifierName
    * @param modifiedTime
    * @return
    * @author  刘松山
    *
    *时间:  2020-08-11 09:33
    *
    */
    int changeStatus(@Param(value="ids") List<String> ids,   @Param(value="status") int status,  @Param(value="remark")String remark,@Param(value="modifierId")String modifierId,@Param(value="modifierName")String modifierName, @Param(value="modifiedTime")Date modifiedTime);

    /**.
	 *
	 * 功能描述：查询仓库名称及对应的编码
	 * 作者：wangzhou
	 * 时间：2018年10月17日
	 * @return
	 */
	List<Map<String,Object>> listUserWarehouse(@Param("currentUid")String orgId);


	/**.
	 *
	 * 创建人: wangzhou
	 * 时间:2020年6月3日下午2:47:57
	 * List<Map<String,Object>>
	 * @param userId
	 * @return
	 * 功能描述:通过用户查询对应仓库权限
	 */
	List<Map<String, Object>> listWarehouseByUserId(String userId);
	/**
	 * .
	 *
	 * 功能描述:根据库存编码或者名称查询是否已经存在
	 *
	 * @param warehouse
	 * @return
	 * @author  刘松山
	 *
	 *时间:  2020-08-05 16:05
	 *
	 */
	int countWarehouse(Warehouse warehouse);
}

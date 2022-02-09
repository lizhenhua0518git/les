package com.zkzn.les.basicInfo.carrier.dao;

import com.zkzn.les.basicInfo.carrier.pojo.CarrierPojo;
import com.zkzn.les.basicInfo.pojo.Carrier;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**.
 *
 * @author wangzhou
 * 载具管理 Dao
 */
@Mapper
public interface CarrierDao {

	/**
	 * 查询载具
	 * @param carrierPojo
	 * @return
	 */
	List<CarrierPojo> listCarrierByCarrier(CarrierPojo carrierPojo);

	/**
	 * 新增载具
	 * @param carrierPojo
	 * @return
	 */
	int saveCarrier (CarrierPojo carrierPojo);

	/**
	 * 删除载具信息
	 * @param carrierIds
	 * @return
	 */
	int deleteCarrier(List<Integer> carrierIds);

	/**
	 * 更新载具信息
	 * @param carrierPojo
	 * @return
	 */
	int updateCarrier (CarrierPojo carrierPojo);

	/**
	 * @Author 胡志明
	 * @Description //TODO 根据载具号校验当前载具是否可用
	 * @Date 11:32 2020/5/14
	 * @Param [carrierCode, warehouse]
	 * @return java.lang.Integer
	 **/
	Integer checkCarrierByCode(@Param(value="carrierCode") String carrierCode, @Param(value="warehouse") String warehouse);




































    /**
     * .
     * 功能描述: 批量修改载具状态
     * 作者:何闰平
     * 时间:2020年3月29日 下午1:57:43
     * @param ids
     * @param remark
     * @param status
     * @return
     */
    int changeStatus(@Param(value="ids") List<String> ids, @Param(value="remark") String remark, @Param(value="status") int status,@Param(value="modifierId")String modifierId,@Param(value="modifierName")String modifierName,@Param(value="modifiedTime")Date modifiedTime);

	/**.
	 *
	 * 创建人: wangzhou
	 * 时间:2020年3月31日上午10:42:47
	 * List<CarrierPojo>
	 * @param carrierType
	 * @param status
	 * @param userStatus
	 * @param storageList
	 * @return
	 * 功能描述:查询载具信息
	 */
	List<Carrier> listCarrier(@Param("carrierType")String carrierType,@Param("status")int status,@Param("useStatus")int userStatus,@Param("storageList")List<String> storageList);
	/**.
	 *
	 *
	 * 创建人: wangzhou
	 * 时间:2020年3月31日下午2:34:31
	 * int
	 * @param carrierIds
	 * @return
	 * 功能描述:批量更新载具的使用状态
	 */
	int updateUseStatusByIds(Map<String,Object> params);

	/**
	 * 根据载具编号更新状态
	 * @param carrier
	 * @return
	 */
	int updateUseStatusByCode(List<Map<String,Object>> map);
	/**.
	 *
	 * 创建人: wangzhou
	 * 时间:2020年4月9日上午11:50:42
	 * List<CarrierPojo>
	 * @param params
	 * @return
	 * 功能描述:查询载具信息
	 */
	List<Carrier> listCarrierInfo(Map<String,Object> params);


	/**.
	 *
	 * 创建人: wangzhou
	 * 时间:2020年5月28日下午1:45:43
	 * List<Map<String,Object>>
	 * @return
	 * 功能描述:查询载具创建人
	 */
	List<Map<String,Object>> listCarrierCreater();
}

package com.zkzn.les.basicInfo.carrier.service;

import com.zkzn.les.basicInfo.carrier.pojo.CarrierPojo;
import com.zkzn.les.basicInfo.pojo.Carrier;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**.
 *
 * @author wangzhou
 * 载具管理
 */
public interface CarrierService {

	/**
	 * 载具列表查询
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
     * .
     * 功能描述: 批量更新载具状态
     * 作者:何闰平
     * 时间:2020年3月29日 下午1:03:33
     * @param ids
     * @param remark
     * @param status
     * @return
     */
   int changeStatus(List<String> ids, String remark, int status,String modifierId,String modifierName,Date modifiedTime);

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
	int updateUseStatusByCode(List<Map<String,Object>> list);
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

	/**
	 * @Author 胡志明
	 * @Description //TODO 根据载具编号校验载具是否可用
	 * @Date 11:29 2020/5/14
	 * @Param [map, warehouse]
	 * @return java.lang.Boolean
	 **/
	Boolean checkCarrierByCode(Map<String,Object> map,String warehouse);

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

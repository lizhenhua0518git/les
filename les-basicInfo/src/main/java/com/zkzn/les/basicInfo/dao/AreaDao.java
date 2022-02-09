package com.zkzn.les.basicInfo.dao;

import com.zkzn.les.basicInfo.pojo.Area;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**.
 * 
 * 功能描述：区域信息dao层
 * @author wangzhou
 * 时间：2018年7月2日
 */
@Mapper
public interface AreaDao {

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
	 * 
	 * 功能描述：查询区域信息
	 * 作者：wangzhou
	 * 时间：2018年7月2日
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
	 * 功能描述: 批量修改区域状态
	 * 作者:何闰平
	 * 时间:2020年3月29日 下午1:57:43
	 * @param ids
	 * @param remark
	 * @param status
	 * @return
	 */
	int changeStatus(@Param(value="ids") List<String> ids, @Param(value="remark") String remark, @Param(value="status") int status,@Param(value="modifier")String modifier,@Param(value="modifiedTime")Date modifiedTime);

	/**
	 * .
	 * 功能描述: 查询仓位的所有仓库编码
	 * 作者:何闰平
	 * 时间:2020年4月2日 下午1:54:35
	 * @return
	 */
	List<String> getWarehouse();
	
	 /**
     * .
     * 功能描述:查询有子区域的区域ID
     * 作者:何闰平
     * 时间:2020年4月2日 下午1:52:57
     * @param originalStoragePositionList
     * @return
     */
	List<String>  hasChildren(@Param("originalAreaList")List<Area> originalAreaList);

}

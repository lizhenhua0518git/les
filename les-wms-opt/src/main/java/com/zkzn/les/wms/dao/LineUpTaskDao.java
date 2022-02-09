package com.zkzn.les.wms.dao;

import com.zkzn.les.wms.pojo.UploadAddress;
import com.zkzn.les.wms.pojo.arrivalNotice.ArrivalNoticePojo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/***
 * @Discription: 排号dao
 * @Author: zhanglei on 2021/6/10 11:03
 */
@Mapper
public interface LineUpTaskDao {

	/***
	 * @Discription: 获取送货单信息Bybillcode
	 * @param param
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 * @Author: zhanglei on 2021/6/10 11:18
	 */
	Map<String,Object> listArrivalNotice(Map<String,Object> param);



	/***
	 * @Discription: 修改到货通知单信息
	 * @param map
	 * @return int
	 * @Author: zhanglei on 2021/6/11 13:06
	 */
	int modifyArrival(Map<String,Object> map);

	/***
	 * @Discription: 更新点收数据
	 * @param param
	 * @return int
	 * @Author: zhanglei on 2021/6/11 13:56
	 */
	int modifyUploadPlat(Map<String,Object> param);
	/***
	 * @Discription: 修改卸货点信息
	 * @param param
	 * @return int
	 * @Author: zhanglei on 2021/6/12 0:06
	 */
	int modifyUploadPlatInfo(Map<String,Object> param);

	/***
	 * @Discription: 查询卸货地点列表
	 * @param uploadAddress
	 * @return java.util.List<com.zkzn.les.wms.pojo.UploadAddress>
	 * @Author: zhanglei on 2021/6/12 0:33
	 */
	List<UploadAddress> listUploadAddress(UploadAddress uploadAddress);

	/**
	 * @Description : 排号主表信息查询
	 * @Author : leizhang
	 * @Date 8:24 下午 2021/6/10
	 * @param param
	 * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 **/
	List<Map<String,Object>> listLineUpMainInfo(Map<String,Object> param);

	/***
	 * @Discription: 获取到货通知单详情
	 * @param param
	 * @return com.zkzn.les.wms.pojo.arrivalNotice.ArrivalNoticePojo
	 * @Author: zhanglei on 2021/6/14 14:59
	 */
	ArrivalNoticePojo getLineUpMainInfo(Map<String,Object> param);

	/***
	 * @Discription: 获取到货通知单明细
	 * @param requestParams
	 * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 * @Author: zhanglei on 2021/6/10 17:08
	 */
	List<Map<String,Object>> getListArrivalNoticeDetail(Map<String,Object> requestParams);

	/***
	 * @Discription: 库存地点信息获取
	 * @param params
	 * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 * @Author: zhanglei on 2021/7/8 10:21
	 */
    List<Map<String, Object>> listStorageLocations(Map<String, Object> params);
}

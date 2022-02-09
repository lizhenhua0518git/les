/*
 * 文 件 名:  LineUpService.java
 * 版    权:  
 * 描    述:  描述:
 * 修 改 人:  刘松山 
 * 修改时间:  2020年8月25日
 * 跟踪单号:  跟踪单号:
 * 修改单号:  修改单号:
 * 修改内容:  修改内容:
 */
package com.zkzn.les.wms.service;
import com.zkzn.les.wms.pojo.UploadAddress;
import com.zkzn.les.wms.pojo.arrivalNotice.ArrivalNoticePojo;
import java.util.List;
import java.util.Map;

/***
 * @Discription: 排号服务
 * @Author: zhanglei on 2021/6/10 15:35
 */
public interface LineUpTaskService {

	/***
	 * @Discription: 到货通知单信息查询
	 * @param param
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 * @Author: zhanglei on 2021/6/10 15:36
	 */
	Map<String,Object> listArrivalNotice(Map<String,Object> param) throws Exception;


	/***
	 * @Discription: 创建排号信息
	 * @param param
	 * @return java.lang.String
	 * @Author: zhanglei on 2021/6/10 15:36
	 */
	long  saveLineUpInfo(Map<String,Object> param) throws Exception;

	/**
	 * @Description : 排号主表信息查询
	 * @Author : leizhang
	 * @Date 8:27 下午 2021/6/10
	 * @param param
	 * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 **/
	List<Map<String,Object>> listLineUpMainInfo(Map<String,Object> param);
    
	/***
	 * @Discription: 排号详情信息获取
	 * @param param
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 * @Author: zhanglei on 2021/6/11 9:31
	 */
	ArrivalNoticePojo getLineUpInfo(Map<String,Object> param);

	/***
	 * @Discription: 叫号
	 * @param param
	 * @return int
	 * @Author: zhanglei on 2021/6/11 10:51
	 */
	void callNum(Map<String,Object> param) throws Exception;

	/***
	 * @Discription: 卸货点查询
	 * @param uploadAddress
	 * @return java.util.List<com.zkzn.les.common.pojo.UploadAddress>
	 * @Author: zhanglei on 2021/6/11 23:59
	 */
	List<UploadAddress> listUploadAddress(UploadAddress uploadAddress);

	/***
	 * @Discription: 取消排号
	 * @param params
	 * @return int
	 * @Author: zhanglei on 2021/6/12 0:30
	 */
	void  delLineUpInfo(Map<String, Object> params) throws Exception;

	/***
	 * @Discription: 卸货完成
	 * @param param
	 * @return void
	 * @Author: zhanglei on 2021/6/14 16:45
	 */
	void saveReceiveTaskInfo(Map<String,Object> param) throws Exception;

	/***
	 * @Discription: 库存地点信息获取
	 * @param params
	 * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 * @Author: zhanglei on 2021/7/8 11:42
	 */
    List<Map<String, Object>> listStorageLocations(Map<String, Object> params);
}

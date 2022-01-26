package com.zkzn.les.wms.dao;


import com.zkzn.les.common.pojo.BinInOutRecordPojo;
import com.zkzn.les.wms.pojo.MaterialStorageBin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**.
 * 
 * @author wangzhou
 * 仓位信息 dao层
 */
@Mapper
public interface MaterialStorageBinDao {

	/**
	 * 库位库存列表查询
	 * @param materialStorageBin
	 * @return
	 */
	List<MaterialStorageBin> listMaterialStorageBin(MaterialStorageBin materialStorageBin);

	/**
	 * 库位库存流水列表查询
	 * @param binInOutRecordPojo
	 * @return
	 */
	List<BinInOutRecordPojo> listStoreBinStockDetail(BinInOutRecordPojo binInOutRecordPojo);

	/**
	 * 初始化区域类型
	 * @return
	 */
	List<String> initAreaTypeName();
	
	   /**
     * 方法说明: 移库准备SAP过账数据
     * @param map
     * @return
     * 作者: liyan
     * 2019年10月30日
     */
    Map<String,Object> transferToSap(Map<String, Object> map);
    
    /**
     * . 
     *
     * 功能描述:修改物料仓位库存
     * 
     * @param map
     * @return
     * @author  刘松山  
     *
     *时间:  2018-12-11 10:21
     *
     */
    int updateStockCount(Map<String, Object> map);
    
    /**
     * . 
     *
     * 功能描述:保存物料仓位存储记录
     * 
     * @param map
     * @return
     * @author  刘松山  
     *
     *时间:  2020-09-05 16:00
     *
     */
    int saveMaterialStorageBin(Map<String, Object> map);

	/**
	 * 根据旧的仓位id更新为新的
	 * @param newStorageBinId
	 * @param oldStorageBinIdDouble
	 * @return
	 */
	int updateMaterialStorageBin(@Param("newStorageBinId")String newStorageBinId,@Param("oldStorageBinId")String oldStorageBinId,
			@Param("storageCout")Double storageCout,@Param("inStockCount")Double inStockCount);

	/**
	 * 删除信息
	 * @param materialStorageBin
	 * @return
	 */
	int deleById(MaterialStorageBin materialStorageBin);

	/**
	 * 分组更新
	 * @param list
	 * @return
	 */
	int updateMaterialStorageBinList(List<MaterialStorageBin> list);

	/**
	 * 通过相关信息查找信息
	 * @param materialStorageBin
	 * @return
	 */
	List<MaterialStorageBin> getMaterialStorageBin(MaterialStorageBin materialStorageBin);

	/**
	 * 批量新增
	 * @param list
	 * @return
	 */
	int addListMaterialStorageBin(List<MaterialStorageBin> list);

	/**
	 * 更新库存
	 * @param list
	 * @return
	 */
	int updateStorageCout(List<MaterialStorageBin> list);

	/**
	 * 同一物料同一批次同供货商
	 * @param materialStorageBin
	 * @return
	 */
	List<MaterialStorageBin> queryMaterialStorageBin(MaterialStorageBin materialStorageBin);

	/**
	 * @param v_action_type
	 * @param v_action_status
	 * @param v_receive_detail_id
	 * @param v_carrier_code
	 */
	void changStatus(@Param("v_action_type")String v_action_type,
					 @Param("v_action_status")String v_action_status,
					 @Param("v_receive_detail_id")String v_receive_detail_id,
					 @Param("v_carrier_code")String v_carrier_code
	);

	/**
	 * 仓位更新
	 * @param materialStorageBin
	 * @return
	 */
	int updateStorageBinListForAllot(MaterialStorageBin materialStorageBin);

	/**
	 *
	 * 根据仓库、工厂、物料、批号、特殊标志、入库数量 查找符合条件的库存记录
	 * @param materialStorageBin
	 * @return
	 */
	List<MaterialStorageBin> queryMaterialStorageBinFree(MaterialStorageBin materialStorageBin);
	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年5月6日下午4:27:57
	 * List<Map<String,Object>>
	 * @param ids
	 * @param stockType
	 * @return
	 * 功能描述:查询移动类型（343,43Y）to源仓位数据 
	 */
	List<Map<String,Object>> listTO343And43YS(@Param("ids")List<String> ids,@Param("stockType")String stockType);
	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年5月6日下午4:27:57
	 * List<Map<String,Object>>
	 * @param ids
	 * @param stockType
	 * @return
	 * 功能描述:查询移动类型（343,43Y）to目的仓位数据
	 */
	List<Map<String,Object>> listTO343And43YP(@Param("ids")List<String> ids,@Param("stockType")String stockType);
	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年5月6日下午5:42:49
	 * int
	 * @param list
	 * @return
	 * 功能描述:批量修改仓位库存信息
	 */
	int updateStockCounts(List<Map<String, Object>> list);
	
	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年5月6日下午8:30:54
	 * int
	 * @param list
	 * @return
	 * 功能描述:批量修改入库库存数
	 */
	int updateInstockCount(List<Map<String,Object>> list);
	/**
	 * . 
	 *
	 * 功能描述: 批量修改仓位库存
	 * 
	 * @param list
	 * @return
	 * @author  刘松山  
	 *
	 *时间:  2020-09-17 10:34
	 *
	 */
	int modifyMaterialStorageBin(List<Map<String,Object>> list);

	/**
	 * 根据id查询库存信息
	 */
	MaterialStorageBin findMaterialStorageBinById(@Param("id") String id);

	/***
	 * @Discription: 库存状态改变
	 * @param changLists
 	 * @param changStatus
	 * @return void
	 * @Author: zhanglei on 2020/12/9 13:29
	 */
    void updateMaterialStorageBinStatus(@Param("list")List<Map<String, Object>> changLists, @Param("changStatus") String changStatus);

	void saveBinStorageBinRecord(@Param("list") List<Map<String,Object>> requestList,@Param("changStatus") String changStatus);
    /***
     * @Discription: 库存类型变更
     * @param changLists
     * @param stockType
     * @return void
     * @Author: zhanglei on 2021/5/24 15:30
     */
	void updateMaterialStorageBinStockType(@Param("list")List<Map<String, Object>> changLists, @Param("stockType") int stockType ,@Param("sellOrder")String sellOrder,@Param("sellOrderItem")String sellOrderItem);

	/***
	 * @Discription: 库存类型变更记录保存
	 * @param requestList
	 * @param stockType
	 * @return void
	 * @Author: zhanglei on 2021/5/24 15:34
	 */
	void saveBinStorageBinRecordOfStockType(@Param("list") List<Map<String,Object>> requestList,@Param("stockType") int stockType);


	/**
	 * 库位库存流水列表查询
	 * @return
	 */
	List<Map<String,Object>> listOfPshStoreBinStock(@Param("requestMap") Map<String,Object> requestMap);
}

package com.zkzn.les.wms.upperFrame.dao;

import com.zkzn.les.common.pojo.MaterialData;
import com.zkzn.les.wms.pojo.MaterialSerial;
import com.zkzn.les.wms.pojo.MaterialStorageBin;
import com.zkzn.les.wms.pojo.StoragePosition;
import com.zkzn.les.wms.pojo.UpperFrameRecord;
import com.zkzn.les.wms.upperFrame.pojo.BreakUpperPojo;
import com.zkzn.les.wms.upperFrame.pojo.UpperFrameData;
import com.zkzn.les.wms.upperFrame.pojo.UpperPositionPojo;
import com.zkzn.les.wms.upperFrame.pojo.VerifyPositionPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UpperFrameTaskDao
 * @Description 上架任务dao
 * @Author zhanglei
 * Date 2021/7/3 10:19
 * @Version 1.0
 **/
@Mapper
public interface UpperFrameTaskDao {
    /***
     * @Discription: 上架任务保存
     * @param param
     * @return void
     * @Author: zhanglei on 2021/7/3 10:49
     */
    void addUpperFrameRecordList(List<Map<String,Object>> param) ;

    /**
     * 批量新增上架表数据
     * @param list
     * @return
     */
    int addUpperFrameRecordLists(List<UpperFrameRecord> list);

    /**
     * 获取上架任务数
     * @param userId
     * @param warehouseCode
     * @return
     */
    List<MaterialData> selectUpperFrameRecord(@Param("userId") String userId,@Param("warehouseCode") String warehouseCode);


    /**
     * 查询不合格物料的拆盘状态
     * @param materialData
     * @return
     */
    Integer selectDetailStatusByUpperData(@Param("dto")MaterialData materialData);

    /**
     * 根据上架表Id获取上架数据
     * @param upperId
     * @return
     */
    BreakUpperPojo breakUpperFrameRecord(@Param("upperId") Integer upperId);

    /**
     * 获取上架分配仓位数据
     * @param upperId
     * @return
     */
    List<UpperPositionPojo> getUpperPositionById(@Param("upperId") Integer upperId);

    /**
     * 查询仓位库存信息
     * @param positionCode  '库位编号'  from t_storage_position
     * @param warehouse '仓库编号' from t_storage_position
     * @return
     */
    List<StoragePosition> getPositionByCodeHouse(@Param("positionCode") String positionCode, @Param("warehouse") String warehouse);

    /**
     * 根据批次号+合格状态获取上架信息
     * @param
     * @return
     */
    List<UpperFrameRecord> getUpperFrameRecordById(@Param("batchNo") String batchNo, @Param("inspectionStatus") Integer inspectionStatus, @Param("upperId") String upperId);


    /**
     * 修改到货通知单详情上架状态
     * @param recevieDetailId
     * @return
     */
    int updateArrivalNoticeDetail(@Param("recevieDetailId") String recevieDetailId,@Param("upperNum") Double upperNum);

    /**
     * 查询到货通知单下所有物料是否上架完成
     * @param recevieDetailId
     * @return
     */
    int getArrivalNoticeDetailStatus(@Param("recevieDetailId") String recevieDetailId);

    /**
     * 修改到货通知单主表的上架状态
     * @param recevieDetailId
     * @return
     */
    int updateArrivalNotice(@Param("recevieDetailId") String recevieDetailId,@Param("upperStatus") String upperStatus);

    /**
     * 查询仓位库存数据
     * @param materialStorageBin
     * @return
     */
    MaterialStorageBin getMaterialStorageBinByData(MaterialStorageBin materialStorageBin);


    /**
     * 删除上架数据
     * @param batchNo
     * @param inspectionStatus
     * @return
     */
    int deleteUpperFrameRecord(@Param("batchNo") String batchNo,@Param("inspectionStatus") Integer inspectionStatus);

    /**
     * 根据仓位库存id删除仓位库存数据
     * @param materialStorageBinId
     * @return
     */
    int deleteMaterialStorageBinById(@Param("materialStorageBinId") String materialStorageBinId);

    /**
     * 修改上架信息
     * @param upperFrameRecord
     * @return
     */
    int updateUpperFrameRecordById(UpperFrameRecord upperFrameRecord);

    /**
     * 根据materialCode batchNo storageId 和 stockStatus 修改仓位库存(上架)
     * @param materialStorageBin
     * @return
     */
    int modifyMaterialStorageBin(MaterialStorageBin materialStorageBin);

    /**
     * 批量新增序列号信息
     * @param list
     * @return
     */
    int addSerialList(List<MaterialSerial> list);

    /**
     * 批量新增仓位库存
     * @param list
     * @return
     */
    int addMaterialStorageBinList(List<MaterialStorageBin> list);

    /**
     * 查询仓位库存信息
     * @param verifyPositionPojo
     * @return
     */
    VerifyPositionPojo getPositionByPositionCode(VerifyPositionPojo verifyPositionPojo);

    /**
     * 校验载具信息
     * @param verifyPositionPojo
     * @return
     */
    VerifyPositionPojo verifyCar(VerifyPositionPojo verifyPositionPojo);

    /**
     * 占用仓位
     * @param storagePositionId
     */
    void updatePosition(@Param("storagePositionId") Integer storagePositionId,@Param("occupyStatus") Integer occupyStatus);

    /**
     * 修改上架数据
     * @param upperFrameData
     */
    void updateUpperFrameRecord(UpperFrameData upperFrameData);

    /**
     * 新增上架实际使用仓位
     * @param list
     */
    void addActualPosition(@Param("upperPositionPojos") List<UpperPositionPojo> upperPositionPojos);

}

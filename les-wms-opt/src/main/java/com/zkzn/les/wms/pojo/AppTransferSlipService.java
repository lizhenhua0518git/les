package com.zkzn.les.wms.pojo;




import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author lty
 * @version 1.0.0
 * @ClassName AppTransferSlipService.java
 * @Description APP端 调拨单上下架交接
 * @createTime 2020年10月22日 14:11:00
 */
public interface AppTransferSlipService {
    /**
     *@Description 查询调拨单待下架接口
     */
    List<StationOrderDetail> listOutTransferSlip(String status, String warehouseCode);

    /**
     *@Description 查询调拨单待上架接口
     */
    List<StationOrderDetail> listUpTransferSlip(String status,String warehouseCode);

    /**
     *@Description 查询调拨单待下架详情接口
     */
    AppTransferSlipResponse outTransferSlipInfoList(String id);

    /**
     * @Description 查询调拨单待下架/上架子单详情接口
     * @param id 详情单主键
     * @param warehouseCode 仓库编码
     * @param flag down 下架;up 上架
     * @return
     */
    TransferSlipInfo outTransferSlipInfoById(String id, String warehouseCode, String flag);

    /**
     *@Description 完成下架任务
     */
    String finishOutTransferSlip(Map<String,Object> paraMap, HttpServletRequest request) throws Exception;

    /**
     * @Description 调拨单上架任务提交
     * @param storagePositions 上架数据
     * @param warehouseCode 仓库Code
     * @param warehouseName 仓库名称
     * @param storage 库存地点
     * @return
     * @throws Exception
     */
    String finishUpTransferSlip(List<StoragePosition> storagePositions, String warehouseCode, String warehouseName, String storage) throws Exception;
    
    /**
     * 功能描述调拨交接详情
     * 
     * @param id
     * @return
     * @author  刘松山  
     *
     *时间:  2020-10-23 14:44
     *
     */
    Map<String, Object> transferSlipConnectInfo(String id);
    /**
     * . 
     *
     * 功能描述:交接物料列表
     * 
     * @param tasksId
     * @return
     * @author  刘松山  
     *
     *时间:  2020-10-23 15:03
     *
     */
    List<Map<String, Object>> transferSlipMaterialList(String tasksId);
    /**
     * . 
     *
     * 功能描述:完成调拨交接
     * 
     * @param pamas
     * @return
     * @author  刘松山  
     *
     *时间:  2020-10-23 15:27
     *
     */
    int finishTransferSlip(Map<String, Object> pamas);
}

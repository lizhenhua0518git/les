package com.zkzn.les.basicInfo.warehouse.service;

import com.zkzn.les.basicInfo.warehouse.pojo.Warehouse;
import com.zkzn.les.basicInfo.warehouse.pojo.WarehousePojo;

import java.util.Date;
import java.util.List;

/**.
 *
 * 功能描述：仓库信息的service
 * @author 何闰平
 * 时间：2020年3月27日
 */
public interface WarehouseService {

    /**
     * 初始化仓库下拉框
     * @param warehousePojo
     * @return
     */
    List<WarehousePojo> initWarehouseSelect(WarehousePojo warehousePojo);



    /**
     * 功能描述: 新增仓库信息
     * 作者:何闰平
     * 时间:2020年3月27日 下午2:41:22
     * @param warehouse
     * @return
     */
    int saveWarehouse(Warehouse warehouse);

    /**
     * .
     * 功能描述: 批量删除仓库
     * 作者:何闰平
     * 时间:2020年3月27日 下午2:41:41
     * @param id
     * @return
     */
    int deleteWarehouse(List<String> id);

    /**
     * .
     * 功能描述: 修改仓库信息
     * 作者:何闰平
     * 时间:2020年3月27日 下午2:41:58
     * @param warehouse
     * @return
     */
    int updateWarehouse(Warehouse warehouse);

    /**
     * .
     * 功能描述: 分页查询仓库信息
     * 作者:何闰平
     * 时间:2020年3月27日 下午2:42:11
     * @param warehouse
     * @return
     */
    List<Warehouse> listWarehouse(Warehouse warehouse);

    /**
     * .
     * 功能描述: 依据id查询仓库信息
     * 作者:何闰平
     * 时间:2020年3月27日 下午2:42:34
     * @param id
     * @return
     */
    Warehouse getWarehouseById(String id);

   /**
    * .
    *
    * 功能描述:批量更新仓库状态
    *
    * @param ids
    * @param status
    * @param currUid
    * @param currUName
    * @param modifiedTime
    * @return
    * @author  刘松山
    *
    *时间:  2020-08-11 09:33
    *
    */
   int changeStatus(List<String> ids, int status,String remark,String currUid,String currUName, Date modifiedTime);



}

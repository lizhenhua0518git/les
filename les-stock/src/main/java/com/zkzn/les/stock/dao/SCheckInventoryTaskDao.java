package com.zkzn.les.stock.dao;

import com.zkzn.les.stock.constants.StockConstants;
import com.zkzn.les.stock.pojo.SCheckInventoryTask;
import com.zkzn.les.stock.pojo.vo.MaterialsGroupVO;
import com.zkzn.les.stock.pojo.vo.SCheckInventoryDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 库存盘点任务主表 Mapper 接口
 * </p>
 *
 * @author Hush.
 * @since 2021-12-22
 */
@Mapper
public interface SCheckInventoryTaskDao {

    /**
     * 仓库中的仓位状态变更
     * 混合存储,只冻结库存数据
     * @param warehouseCode:仓库编码
     * @param status             状态值 {@link StockConstants#OCCUPY_STATUS_0}
     * @return 更新行数
     * @author Hush.
     * @since 2021/12/22 19:08
     */
    @Deprecated
    int updateStoragePosition(@Param("warehouseCode") String warehouseCode, @Param("status") Integer status);

    /**
     * 锁定仓库物料
     *
     * @param warehouseCode :仓库编码
     * @param clientName:客户名称
     * @return 更新行数
     * @author Hush.
     * @since 2021/12/22 19:40
     */
    int updateStorageBinAtCheck(@Param("warehouseCode")String warehouseCode,@Param("clientName") String clientName);

    /**
     * 保存盘点任务
     *
     * @param dto:库存盘点任务
     * @return 更新行数
     * @author Hush.
     * @since 2021/12/22 19:44
     */
    int saveTask(SCheckInventoryTask dto);

    /**
     * 检索盘点任务
     *
     * @param dto:条件
     * @return 任务结果集
     * @author Hush.
     * @since 2021/12/22 20:21
     */
    List<SCheckInventoryTask> listTask(SCheckInventoryTask dto);

    /**
     * 检索任务详情
     *
     * @param dto:条件
     * @return 详情结果集
     * @author Hush.
     * @since 2021/12/22 21:07
     */
    List<SCheckInventoryDetailVO> listTaskDetail(SCheckInventoryDetailVO dto);

    /**
     * 根据主键查询
     *
     * @param taskId: 任务主键
     * @return com.zkzn.les.stock.pojo.SCheckInventoryTask
     * @author Hush.
     * @since 2021/12/23 11:25
     */
    SCheckInventoryTask selectByPrimaryKey(Long taskId);

    /**
     * 获取待盘点物料集合
     *
     * @param dto:条件
     * @return 待盘点物料集合
     * @author Hush.
     * @since 2021/12/23 18:50
     */
    List<MaterialsGroupVO> selectMaterialsToBeCounted(SCheckInventoryTask dto);

    /**
     * 主键更新任务
     *
     * @param task: 任务
     * @return 更新行数
     * @author Hush.
     * @since 2021/12/24 10:21
     */
    int updateByPrimaryKeySelective(SCheckInventoryTask task);

    /**
     * PC端盘点任务分页数据
     *
     * @param dto:检索条件
     * @return 分页对象
     * @author Hush.
     * @since 2021/12/26 19:40
     */
    List<SCheckInventoryTask> listTaskPC(Map<String, Object> dto);

    /**
     * 查询盘点审核人员
     *
     * @return List<id, name>
     * @author Hush.
     * @since 2021/12/22 14:31
     */
    List<Map<String, Object>> selectVerifyUserList();

    /**
     * 移除盘点任务
     *
     * @param id : 任务主键
     * @return 影响行数
     * @author Hush.
     * @since 2021/12/28 10:07
     */
    int deleteTask(Long id);

    /**
     * 检索盘点任务
     *
     * @param dto: 条件
     * @return task
     * @author Hush.
     * @since 2021/12/28 14:58
     */
    SCheckInventoryTask selectTaskByEntity(SCheckInventoryTask dto);

    /**
     * 获取待盘点的仓位数量
     *
     * @param dto :盘点任务
     * @return 待盘点的仓位数量
     * @author Hush.
     * @since 2021/12/29 16:07
     */
    int countToBeCheckedPositionNum(SCheckInventoryTask dto);
}

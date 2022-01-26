package com.zkzn.les.stock.dao;

import com.zkzn.les.stock.pojo.SCheckInventoryDetail;
import com.zkzn.les.stock.pojo.SCheckInventoryTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 库存盘点物料详情表 Mapper 接口
 * </p>
 *
 * @author Hush.
 * @since 2021-12-22
 */
@Mapper
public interface SCheckInventoryDetailDao {

    /**
     * 批量保存盘点物料
     *
     * @param record:物料集合
     * @return 影响行数
     * @author Hush.
     * @since 2021/12/23 19:57
     */
    int insertBatch(@Param("list") List<SCheckInventoryDetail> record);

    /**
     * 批量更新盘点数据
     *
     * @param updateList:更新数据
     * @return 影响行数
     * @author Hush.
     * @since 2021/12/24 13:28
     */
    int updateBatch(@Param("list") List<SCheckInventoryDetail> updateList);

    /**
     * <p>接受盘点结果损益</p>
     * <p>1. 库位冻结盘点归零</p>
     * <p>2. 仓位非限制库存为盘点提交值</p>
     * @param dto:盘点任务
     * @return 影响行数
     * @author Hush.
     * @since 2021/12/24 15:27
     */
    int acceptTheProfitAndLoss(SCheckInventoryTask dto);

    /**
     * 恢复盘点仓库中未盘点的仓位库存
     *
     * @param dto:更新条件
     * @return 更新行数
     * @author Hush.
     * @since 2021/12/24 15:45
     */
    int updateNotCountedStock(SCheckInventoryTask dto);

    /**
     * 客户平仓
     *
     * @param dto: 盘点任务
     * @return 影响行数
     * @author Hush.
     * @since 2021/12/24 16:37
     */
    int updateClientCloses(SCheckInventoryTask dto);
}

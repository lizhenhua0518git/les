package com.zkzn.les.stock.service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.stock.pojo.SCheckInventoryDetail;
import com.zkzn.les.stock.pojo.SCheckInventoryTask;
import com.zkzn.les.stock.pojo.vo.MaterialsGroupVO;
import com.zkzn.les.stock.pojo.vo.SCheckInventoryDetailVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 库存盘点任务主表 服务类
 * </p>
 *
 * @author Hush.
 * @since 2021-12-22
 */
public interface ISCheckInventoryTaskService {

    /**
     * 保存盘点任务
     *
     * @param dto :任务详情
     * @return java.lang.String
     * @author Hush.
     * @since 2021/12/22 18:28
     */
    int saveCheckInventoryTask(SCheckInventoryTask dto);


    /**
     * 盘点物料详情查看
     *
     * @param dto 检索条件
     * @return com.github.pagehelper.PageInfo<com.zkzn.les.stock.pojo.vo.SCheckInventoryDetailVO>
     * @author Hush.
     * @since 2021/12/22 20:45
     */
    PageInfo<SCheckInventoryDetailVO> listDetailPage(SCheckInventoryDetailVO dto);

    /**
     * 提交审核结果
     *
     * @param dto : 提交参数
     * @return java.lang.String
     * @author Hush.
     * @since 2021/12/22 21:14
     */
    int verifyTask(SCheckInventoryTask dto);

    /**
     * 根据主键查询
     *
     * @param taskId: 任务主键
     * @return com.zkzn.les.stock.pojo.SCheckInventoryTask
     * @author Hush.
     * @since 2021/12/23 11:25
     */
    SCheckInventoryTask getCheckInventoryTaskById(Long taskId);

    /**
     * 盘点任务列表(盘点状态未结束)
     *
     * @param dto: 检索条件
     * @return java.util.List<com.zkzn.les.stock.pojo.SCheckInventoryTask>
     * @author Hush.
     * @since 2021/12/23 15:46
     */
    List<SCheckInventoryTask> listTask(SCheckInventoryTask dto);

    /**
     * 分组展示仓位中的物料信息
     *
     * @param dto:检索条件
     * @return java.util.List<com.zkzn.les.stock.pojo.vo.MaterialsGroupVO>
     * @author Hush.
     * @since 2021/12/23 16:47
     */
    PageInfo<MaterialsGroupVO> materialsGroup(SCheckInventoryDetailVO dto);

    /**
     * 保存或更新盘点数据
     *
     * @param dto:盘点数据
     * @return int
     * @author Hush.
     * @since 2021/12/24 10:16
     */
    int saveOrUpdateInventoryData(List<SCheckInventoryDetail> dto);


    /**
     * 更新任务
     *
     * @param dto:参数
     * @return int
     * @author Hush.
     * @since 2021/12/24 10:19
     */
    int updateTask(SCheckInventoryTask dto);

    /**
     * PC端盘点任务分页数据
     *
     * @param dto:检索条件
     * @return 分页对象
     * @author Hush.
     * @since 2021/12/26 19:40
     */
    PageInfo<SCheckInventoryTask> listTaskPagePC(Map<String, Object> dto);

    /**
     * 盘点审核人员检索
     *
     * @return java.lang.String
     * @author Hush.
     * @since 2021/12/22 14:31
     */
    List<Map<String, Object>> verifyUserSelectPC();

    /**
     * 移除盘点任务
     *
     * @param id: 任务主键
     * @return 结果
     * @author Hush.
     * @since 2021/12/28 10:00
     */
    String removeTask(Long id);
}

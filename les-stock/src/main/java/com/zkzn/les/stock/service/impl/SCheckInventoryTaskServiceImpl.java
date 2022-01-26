package com.zkzn.les.stock.service.impl;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.common.exception.NoMappingParamException;
import com.zkzn.les.common.util.page.PageUtil;
import com.zkzn.les.common.util.redis.RedisNoUtil;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.stock.config.exception.CustomException;
import com.zkzn.les.stock.config.exception.ExceptionCasts;
import com.zkzn.les.stock.constants.StockConstants;
import com.zkzn.les.stock.dao.SCheckInventoryDetailDao;
import com.zkzn.les.stock.dao.SCheckInventoryTaskDao;
import com.zkzn.les.stock.pojo.SCheckInventoryDetail;
import com.zkzn.les.stock.pojo.SCheckInventoryTask;
import com.zkzn.les.stock.pojo.vo.BeCountedMaterialsVO;
import com.zkzn.les.stock.pojo.vo.MaterialsGroupVO;
import com.zkzn.les.stock.pojo.vo.SCheckInventoryDetailVO;
import com.zkzn.les.stock.service.ISCheckInventoryTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 库存盘点任务主表 服务实现类
 * </p>
 *
 * @author Hush.
 * @since 2021-12-22
 */
@Service
@Slf4j
public class SCheckInventoryTaskServiceImpl implements ISCheckInventoryTaskService {
    @Autowired
    private SCheckInventoryTaskDao taskMapper;
    @Autowired
    private SCheckInventoryDetailDao detailMapper;
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 保存盘点任务
     *
     * @param dto :任务详情
     * @return java.lang.String
     * @author Hush.
     * @since 2021/12/22 18:28
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveCheckInventoryTask(SCheckInventoryTask dto) {
        String mess;
        //检查该仓库中有无盘点任务未审核
        SCheckInventoryTask task = taskMapper.selectTaskByEntity(
                new SCheckInventoryTask()
                        .setClientName(dto.getClientName())
                        .setWarehouseCode(dto.getWarehouseCode())
                        .setVerifyStatus(StockConstants.CHECK_VERIFY_STATUS_NO)
        );
        if (task != null) {
            mess = String.format("该仓库中存在未结束的盘点任务,仓库:%s,客户:%s", dto.getWarehouseName(), dto.getClientName());
            log.error(mess);
            throw new CustomException(mess);
        }
        //获取待盘点的仓位数量
        int positionNum = taskMapper.countToBeCheckedPositionNum(dto);
        //锁定选中仓库中的仓位
        /*int lockedNumP = taskMapper.updateStoragePosition(dto.getWarehouseCode(), StockConstants.OCCUPY_STATUS_0);
        if (lockedNumP <= 0) {
            mess = "该仓库中的仓位数量为:0,创建失败";
            log.error(mess);
            throw new CustomException(mess);
        }*/

        //锁定该客户在仓库中的物料
        int lockedNumM = taskMapper.updateStorageBinAtCheck(dto.getWarehouseCode(),dto.getClientName());
        if (lockedNumM <= 0) {
            mess = "该仓库中的仓位中非限制库存物料数量为:0,创建失败";
            log.error(mess);
            throw new CustomException(mess);
        }
        //保存盘点任务
        dto.setPositionNum(positionNum)
                .setTaskNumber(RedisNoUtil.getSeqNo(RedisNoUtil.PD_ORDER_KEY, redisTemplate))
                .setCheckStatus(StockConstants.TO_BE_COUNTED)
                .setVerifyStatus(StockConstants.CHECK_VERIFY_STATUS_NO)
                .setCreateTime(new Date());
        int taskNum = taskMapper.saveTask(dto);
        String result = String.format("保存任务成功,锁定仓库物料行数:%s,taskNum:%s", lockedNumM, taskNum);
        log.info(result);
        return taskNum;
    }


    /**
     * 盘点物料详情查看
     *
     * @param dto 检索条件
     * @return com.github.pagehelper.PageInfo<com.zkzn.les.stock.pojo.vo.SCheckInventoryDetailVO>
     * @author Hush.
     * @since 2021/12/22 20:45
     */
    @Override
    public PageInfo<SCheckInventoryDetailVO> listDetailPage(SCheckInventoryDetailVO dto) {
        PageUtil.setPageParam(dto);
        return new PageInfo<>(taskMapper.listTaskDetail(dto));

    }

    /**
     * <p>提交审核结果</p>
     * detail {@linkplain SCheckInventoryTask#verifyStatus} 状态
     * </p>
     *
     * @param dto : 提交参数
     * @return java.lang.String
     * @author Hush.
     * @since 2021/12/22 21:14
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int verifyTask(SCheckInventoryTask dto) {

        switch (dto.getVerifyStatus()) {
            case StockConstants.CHECK_VERIFY_STATUS_ACCEPT:
                //接受结果损益
                int i = this.acceptTheProfitAndLoss(dto);
                log.info("接受结果损益,影响行数{}", i);
                break;
            case StockConstants.CHECK_VERIFY_STATUS_CLIENT_ADD:
                //客户平仓
                int c = this.clientCloses(dto);
                log.info("客户平仓,影响行数{}", c);
                break;
            case StockConstants.CHECK_VERIFY_STATUS_CANCEL:
                int d = this.cancelTheProfitAndLoss(dto);
                log.info("取消损益,影响行数{}", d);
                break;
            default:
                throw new CustomException("审核状态校验失败!");
        }
        //恢复仓位状态
        //int unlockedNumP = taskMapper.updateStoragePosition(dto.getWarehouseCode(), StockConstants.OCCUPY_STATUS_1);
        //log.info("恢复仓位状态,仓位恢复数量:{}", unlockedNumP);
        //更新盘点任务状态
        SCheckInventoryTask task = new SCheckInventoryTask()
                .setTaskId(dto.getTaskId())
                .setVerifyTime(new Date())
                .setVerifyStatus(dto.getVerifyStatus());
        return taskMapper.updateByPrimaryKeySelective(task);
    }


    /**
     * 根据主键查询
     *
     * @param taskId: 任务主键
     * @return com.zkzn.les.stock.pojo.SCheckInventoryTask
     * @author Hush.
     * @since 2021/12/23 11:25
     */
    @Override
    public SCheckInventoryTask getCheckInventoryTaskById(Long taskId) {

        return taskMapper.selectByPrimaryKey(taskId);
    }

    /**
     * 盘点任务列表(盘点状态未结束)
     *
     * @param dto: 检索条件
     * @return java.util.List<com.zkzn.les.stock.pojo.SCheckInventoryTask>
     * @author Hush.
     * @since 2021/12/23 15:46
     */
    @Override
    public List<SCheckInventoryTask> listTask(SCheckInventoryTask dto) {

        return taskMapper.listTask(dto);
    }

    /**
     * 分组展示仓位中的物料信息
     *
     * @param dto:检索条件
     * @return java.util.List<com.zkzn.les.stock.pojo.vo.MaterialsGroupVO>
     * @author Hush.
     * @since 2021/12/23 16:47
     */
    @Override
    public PageInfo<MaterialsGroupVO> materialsGroup(SCheckInventoryDetailVO dto) {

        SCheckInventoryTask task = this.taskMapper.selectByPrimaryKey(dto.getTaskId());
        PageUtil.setPageParam(dto);
        List<MaterialsGroupVO> voList = taskMapper.selectMaterialsToBeCounted(task);
        for (MaterialsGroupVO key : voList) {
            List<BeCountedMaterialsVO> mList = key.getMaterials();
            //获取该仓位已盘点的物料
            long count = mList.stream().filter(one -> !StringUtils.isEmpty(one.getDetailId())).count();
            int flag = (count == 0) ? -1 : (count == mList.size() ? 1 : 0);
            key.setCheckFlag(flag);
        }
        return new PageInfo<>(voList);
    }

    /**
     * 保存或更新盘点数据
     *
     * @param dto:盘点数据
     * @return int
     * @author Hush.
     * @since 2021/12/24 10:16
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveOrUpdateInventoryData(List<SCheckInventoryDetail> dto) {
        if (CollectionUtils.isEmpty(dto)) {
            return 0;
        }
        List<SCheckInventoryDetail> saveList = new ArrayList<>();
        List<SCheckInventoryDetail> updateList = new ArrayList<>();
        for (SCheckInventoryDetail detail : dto) {
            if (detail.getDetailId() == 0 || StringUtils.isEmpty(detail.getDetailId())) {
                saveList.add(detail);
            } else {
                updateList.add(detail);
            }
        }
        int insertIndex = saveList.size() > 0 ? detailMapper.insertBatch(saveList) : 0;
        int updateIndex = updateList.size() > 0 ? detailMapper.updateBatch(updateList) : 0;
        log.info("保存或更新盘点数据,更新数量:{},保存数量{}", updateIndex, insertIndex);
        SCheckInventoryDetail detail = dto.get(0);
        taskMapper.updateByPrimaryKeySelective(new SCheckInventoryTask().setTaskId(detail.getTaskId()).setCheckStatus(StockConstants.CHECKING));
        return insertIndex + updateIndex;
    }

    /**
     * 更新任务
     *
     * @param dto:参数
     * @return int
     * @author Hush.
     * @since 2021/12/24 10:19
     */
    @Override
    public int updateTask(SCheckInventoryTask dto) {

        return taskMapper.updateByPrimaryKeySelective(dto);
    }

    /**
     * PC端盘点任务分页数据
     *
     * @param dto:检索条件
     * @return 分页对象
     * @author Hush.
     * @since 2021/12/26 19:40
     */
    @Override
    public PageInfo<SCheckInventoryTask> listTaskPagePC(Map<String, Object> dto) {
        PageUtil.setPageParam(dto);
        return new PageInfo<>(taskMapper.listTaskPC(dto));
    }

    /**
     * 盘点审核人员检索
     *
     * @return java.lang.String
     * @author Hush.
     * @since 2021/12/22 14:31
     */
    @Override
    public List<Map<String, Object>> verifyUserSelectPC() {
        return taskMapper.selectVerifyUserList();
    }

    /**
     * 移除盘点任务
     *
     * @param id : 任务主键
     * @return 结果
     * @author Hush.
     * @since 2021/12/28 10:00
     */
    @Override
    public String removeTask(Long id) {
        SCheckInventoryTask task = this.getCheckInventoryTaskById(id);
        if (!StockConstants.TO_BE_COUNTED.equals(task.getCheckStatus())) {
            return Result.toErrorJson("该盘点任务已经开始,不可删除", null);
        }
        Ecode ecode = taskMapper.deleteTask(id) > 0 ? Ecode.SUCCESS : Ecode.FAIL;
        return Result.toJson(ecode, id);
    }

    /**
     * 接受结果损益
     *
     * @param dto:任务对象
     * @return int 影响行数
     * @author Hush.
     * @since 2021/12/24 13:54
     */
    private int acceptTheProfitAndLoss(SCheckInventoryTask dto) {
        int i = detailMapper.acceptTheProfitAndLoss(dto);
        int i1 = detailMapper.updateNotCountedStock(dto);
        return i1 + i;
    }


    /**
     * 客户平仓
     *
     * @param dto:任务对象
     * @return int 影响行数
     * @author Hush.
     * @since 2021/12/24 13:54
     */
    private int clientCloses(SCheckInventoryTask dto) {
        int i = detailMapper.updateClientCloses(dto);
        int i1 = detailMapper.updateNotCountedStock(dto);
        return i1 + i;
    }

    /**
     * 取消损益
     *
     * @param dto: 盘点任务
     * @return int 影响行数
     * @author Hush.
     * @since 2021/12/27 15:03
     */
    private int cancelTheProfitAndLoss(SCheckInventoryTask dto) {
        return detailMapper.updateNotCountedStock(dto);
    }
}

package com.zkzn.les.wms.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zkzn.les.common.exception.EmptyExamine;
import com.zkzn.les.common.util.redis.RedisNoUtil;
import com.zkzn.les.wms.constant.WmsConstants;
import com.zkzn.les.wms.receiveRecord.dao.CarrierDao;
import com.zkzn.les.wms.dao.PlatTransferTaskDao;
import com.zkzn.les.wms.upperFrame.dao.UpperFrameTaskDao;
import com.zkzn.les.wms.service.PlatTransferTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PlatTransferServiceImpl
 * @Description 拆盘任务
 * @Author zhanglei
 * Date 2021/6/23 11:13
 * @Version 1.0
 **/
@Service
public class PlatTransferTaskServiceImpl implements PlatTransferTaskService {
    @Autowired
    private PlatTransferTaskDao platTransferTaskDao;

    @Autowired
    private CarrierDao carrierDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UpperFrameTaskDao upperFrameTaskDao;
    /***
     * @Discription: 拆盘任务主表信息查询
     * @param param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zhanglei on 2021/6/23 13:46
     */
    @Override
    public List<Map<String, Object>> listTransferInfo(Map<String, Object> param) throws Exception {
        return platTransferTaskDao.listPlatTransferInfos(param);
    }

    /***
     * @Discription: 拆盘任务子表信息查询
     * @param param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author: zhanglei on 2021/6/23 13:46
     */
    @Override
    public List<Map<String, Object>> listTransferDetailInfo(Map<String, Object> param) throws Exception {
        return platTransferTaskDao.listPlatTransferDetailInfos(param);
    }

    /**
     * @Description : 拆盘任务提交
     *              1.校验拆盘是否可用
     *              2.更新拆盘子表任务 记录拆盘数量 TRANSFER_NUM 拆盘数量 若果拆盘数量等于不合格品数量 则更新拆盘状态 0 未拆盘 1 部分拆盘 2 已拆盘
     *              3.更新物料载具表 arrivalCode materialCode carrierId batchNo 三者判断条件 没有则新增 有则更新载具上的数量
     *              //TODO 物料载具表需要新增 物料状态 (合格 不合格 部分合格) 合格数量 不合格数量
     *        数据格式：[{"carrierCode":"载具编码","transferNum":拆盘数量,"transferDetailId":"拆盘任务子表id","serialList":[{"serialNum":""}]}]
     * @Author : leizhang
     * @Date 7:28 下午 2021/6/23
     * @param params
     * @return void
     **/
    @Override
    public void updatePlatTransferDetailInfo(Map<String, Object> params) throws Exception {
        //必传参数校验
        EmptyExamine.examine(params,WmsConstants.TRANSFER_TASK_ID, WmsConstants.CARRIER_CODE,WmsConstants.TRANSFER_NUM,WmsConstants.UNQUALIFIED_NUM,WmsConstants.TRANSFER_DETAIL_ID);
        //1.载具信息验证
        List<Map<String,Object>> carrierCodes = Lists.newArrayList(params);
        List<Map<String, Object>> listCarrier = carrierDao.listCarrier(carrierCodes);
        if (listCarrier.isEmpty()) {
            throw new Exception("拆盘载具不存在");
        }
        Map<String, Object> carrierInfo = listCarrier.get(WmsConstants.NUMBER_0);
        params.put(WmsConstants.NEW_CARRIER_ID,carrierInfo.get(WmsConstants.ID));
        params.put(WmsConstants.NEW_CARRIER_CODE,carrierInfo.get(WmsConstants.CARRIER_CODE));

        //2.更新拆盘信息
        Map<String, Object> transferDetailInfo = platTransferTaskDao.listPlatTransferDetailInfos(params).get(WmsConstants.NUMBER_0);
        if ( ! (params.get(WmsConstants.TRANSFER_NUM) instanceof Double) ) {
            throw new Exception("拆盘数量/不合格品数量数据格式错误");
        }
        int status = WmsConstants.NUMBER_1;
        if ((double)params.get(WmsConstants.TRANSFER_NUM)  != (double)transferDetailInfo.get(WmsConstants.UNQUALIFIED_NUM) ) {
            throw  new Exception("拆盘数量错误");
        }
        //更行行项目
        params.put(WmsConstants.STATUS,status);
        params.put(WmsConstants.TRANSFER_TIME,new Date());
        params.put(WmsConstants.MATERIAL_CODE,transferDetailInfo.get(WmsConstants.MATERIAL_CODE));
        params.put(WmsConstants.BATCH_NO,transferDetailInfo.get(WmsConstants.BATCH_NO));
        params.put(WmsConstants.ORDER_CODE,transferDetailInfo.get(WmsConstants.ORDER_CODE));
        platTransferTaskDao.updatePlatTransferDetailInfo(params);
        platTransferTaskDao.updatePlatTransferInfo(params);
        //3.更新物料载具信息
        params.put(WmsConstants.OLD_CARRIER_ID,transferDetailInfo.get(WmsConstants.CARRIER_ID));
        carrierDao.updateOldCarrierInfo(params);

        Map<String, Object> carrierInfoMap = carrierDao.listReceiveCarrierInfo(params);
        if (null == carrierInfoMap || carrierInfoMap.isEmpty()) {
            //新增
            params.put(WmsConstants.CARRIER_ID,carrierInfo.get(WmsConstants.ID));
            params.put(WmsConstants.CARRIER_CODE,carrierInfo.get(WmsConstants.CARRIER_CODE));
            params.put(WmsConstants.CARRIER_TYPE,carrierInfo.get(WmsConstants.CARRIER_TYPE));
            params.put(WmsConstants.RECEIVE_NUM,params.get(WmsConstants.TRANSFER_NUM));
            //carrierDao.saveCarrierInfo(Lists.newArrayList(params));
        } else {
            //更新
            carrierDao.updateNewCarrierInfo(params);
        }
        //TODO 推送合格品 与不合品上架信息
        List<Map<String, Object>> upperFrameInfo = getUpperFrameInfo(transferDetailInfo, params);
        upperFrameTaskDao.addUpperFrameRecordList(upperFrameInfo);
    }

    private List<Map<String,Object>> getUpperFrameInfo (Map<String, Object> transferDetailInfo,Map<String,Object> params) {
        List<Map<String,Object>> upperFrameInfo = Lists.newArrayList();
        //拆盘不合格品
        Map<String,Object> qualifiedMaterial = Maps.newHashMap(params);
        //不合格品数量
        qualifiedMaterial.put(WmsConstants.MATERIAL_NUM,params.get(WmsConstants.TRANSFER_NUM));
        //不合格品上架
        qualifiedMaterial.put(WmsConstants.UPPER_TYPE,WmsConstants.NUMBER_1);
        //上架类型
        qualifiedMaterial.put(WmsConstants.UPPER_TASK_TYPE,WmsConstants.NUMBER_2);
        //上架任务号
        qualifiedMaterial.put(WmsConstants.UPPER_TASK_CODE, RedisNoUtil.getSeqNo(RedisNoUtil.UPPER_FRAME_KEY,redisTemplate));
        qualifiedMaterial.put(WmsConstants.RECEIVE_DETAIL_ID,transferDetailInfo.get(WmsConstants.TRANSFER_DETAIL_ID));
        qualifiedMaterial.put(WmsConstants.RECEIVE_RECORD_ID,transferDetailInfo.get(WmsConstants.TRANSFER_TASK_ID));
        qualifiedMaterial.put(WmsConstants.MATERIAL_UNIT,transferDetailInfo.get(WmsConstants.MATERIAL_UNIT));
        qualifiedMaterial.put(WmsConstants.STORAGE_LOCATION,transferDetailInfo.get(WmsConstants.STORAGE_LOCATION));
        qualifiedMaterial.put(WmsConstants.POSITION_ID,transferDetailInfo.get(WmsConstants.INSPECTION_POSITION_ID));
        upperFrameInfo.add(qualifiedMaterial);

        //拆盘合格品
        Map<String,Object> unQualifiedMaterial = Maps.newHashMap(params);
        unQualifiedMaterial.put(WmsConstants.MATERIAL_NUM,(double)transferDetailInfo.get(WmsConstants.MATERIAL_NUM)-(double)params.get(WmsConstants.TRANSFER_NUM));
        unQualifiedMaterial.put(WmsConstants.UPPER_TASK_TYPE,WmsConstants.NUMBER_2);
        unQualifiedMaterial.put(WmsConstants.UPPER_TASK_CODE, RedisNoUtil.getSeqNo(RedisNoUtil.UPPER_FRAME_KEY,redisTemplate));
        unQualifiedMaterial.put(WmsConstants.RECEIVE_DETAIL_ID,transferDetailInfo.get(WmsConstants.TRANSFER_DETAIL_ID));
        unQualifiedMaterial.put(WmsConstants.RECEIVE_RECORD_ID,transferDetailInfo.get(WmsConstants.TRANSFER_TASK_ID));
        unQualifiedMaterial.put(WmsConstants.MATERIAL_UNIT,transferDetailInfo.get(WmsConstants.MATERIAL_UNIT));
        unQualifiedMaterial.put(WmsConstants.STORAGE_LOCATION,transferDetailInfo.get(WmsConstants.STORAGE_LOCATION));
        unQualifiedMaterial.put(WmsConstants.POSITION_ID,transferDetailInfo.get(WmsConstants.INSPECTION_POSITION_ID));
        upperFrameInfo.add(unQualifiedMaterial);
        return upperFrameInfo;
    }
}

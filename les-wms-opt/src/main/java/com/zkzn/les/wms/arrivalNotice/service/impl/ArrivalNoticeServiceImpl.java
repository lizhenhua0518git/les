package com.zkzn.les.wms.arrivalNotice.service.impl;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.common.util.page.PageUtil;
import com.zkzn.les.common.util.redis.RedisNoUtil;
import com.zkzn.les.wms.arrivalNotice.dao.ArrivalNoticeDao;
import com.zkzn.les.wms.arrivalNotice.pojo.AddReceiveDataPojo;
import com.zkzn.les.wms.arrivalNotice.pojo.ArrivalNoticeDetailPojo;
import com.zkzn.les.wms.arrivalNotice.pojo.ArrivalNoticePojo;
import com.zkzn.les.wms.arrivalNotice.pojo.UserInfoPojo;
import com.zkzn.les.wms.arrivalNotice.service.ArrivalNoticeService;
import com.zkzn.les.wms.receiveRecord.dao.ReceiveRecordDao;
import com.zkzn.les.wms.receiveRecord.pojo.ReceiveTaskDetailPojo;
import com.zkzn.les.wms.receiveRecord.pojo.ReceiveTaskPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArrivalNoticeServiceImpl implements ArrivalNoticeService {

    @Autowired
    private ArrivalNoticeDao arrivalNoticeDao;
    @Autowired
    private ReceiveRecordDao receiveRecordDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public PageInfo<ArrivalNoticePojo> getArrivalNotice(ArrivalNoticePojo arrivalNotice) {
        String createStr = arrivalNotice.getCreateStr();//创建时间
        if (!"".equals(createStr) && createStr != null){
            String[] split = createStr.split(" - ");
            arrivalNotice.setStartCreate(split[0]);
            arrivalNotice.setEndCreate(split[1]);
        }
        PageUtil.setPageParam(arrivalNotice);
        List<ArrivalNoticePojo> arrivalNoticeList = arrivalNoticeDao.listArrivalNotice(arrivalNotice);
        PageInfo<ArrivalNoticePojo> pageInfo = new PageInfo<>(arrivalNoticeList);
        return pageInfo;
    }

    @Override
    public PageInfo<ArrivalNoticeDetailPojo> arrivalNoticeDetailList(ArrivalNoticeDetailPojo arrivalNoticeDetailPojo) {
        PageUtil.setPageParam(arrivalNoticeDetailPojo);
        List<ArrivalNoticeDetailPojo> arrivalNoticeDetailPojoList = arrivalNoticeDao.arrivalNoticeDetailList(arrivalNoticeDetailPojo);
        PageInfo<ArrivalNoticeDetailPojo> pageInfo = new PageInfo<>(arrivalNoticeDetailPojoList);
        return pageInfo;
    }

    @Override
    public int updateArrivalNoticeDetail(ArrivalNoticeDetailPojo arrivalNoticeDetailPojo) {
        int i = arrivalNoticeDao.updateArrivalNoticeDetail(arrivalNoticeDetailPojo);
        return i;
    }

    @Override
    public int arriveNoticeAdd(ArrivalNoticePojo arrivalNoticePojo) {
        Integer clientManageId = arrivalNoticePojo.getClientManageId();
        List<UserInfoPojo> userInfoPojoList = arrivalNoticeDao.getUserInfoById(clientManageId);
        if (userInfoPojoList.size()>0){
            UserInfoPojo userInfoPojo = userInfoPojoList.get(0);
            arrivalNoticePojo.setOperateUserId(userInfoPojo.getUserId());
            arrivalNoticePojo.setOperateUserName(userInfoPojo.getUserName());
        }
        String rk = RedisNoUtil.getSeqNo("RK", redisTemplate);
        arrivalNoticePojo.setArrivalCode(rk);
        int i = arrivalNoticeDao.arriveNoticeAdd(arrivalNoticePojo);
        return i;
    }

    @Override
    public int deleteArriveNotice(List<Integer> arrivalNoticeIds) {
        int i = arrivalNoticeDao.deleteArriveNotice(arrivalNoticeIds);
        int n = arrivalNoticeDao.deleteArriveDetail(arrivalNoticeIds);
        return i;
    }

    @Override
    @Transactional
    public int issueArriveNotice(List<Integer> arrivalNoticeIds,Integer userId,String currentUserName) {
        for (int j = 0; j < arrivalNoticeIds.size(); j++) {
            Integer arrivalNoticeId = arrivalNoticeIds.get(j);
            List<AddReceiveDataPojo> addReceiveDataPojoList = arrivalNoticeDao.getAddReceiveData(arrivalNoticeId);
            if (addReceiveDataPojoList.size()>0){
                AddReceiveDataPojo oneAddReceiveDataPojo = addReceiveDataPojoList.get(0);
                ReceiveTaskPojo receiveTaskPojo = new ReceiveTaskPojo();
                receiveTaskPojo.setCreateName(currentUserName);
                receiveTaskPojo.setCreateUserId(userId);
                receiveTaskPojo.setCreateTime(new Date());
                receiveTaskPojo.setArrivalNoticeId(oneAddReceiveDataPojo.getArrivalNoticeId());
                receiveTaskPojo.setArrivalCode(oneAddReceiveDataPojo.getArrivalCode());
                receiveTaskPojo.setWarehouseName(oneAddReceiveDataPojo.getWarehouseName());
                receiveTaskPojo.setWarehouseCode(oneAddReceiveDataPojo.getWarehouseCode());
                receiveTaskPojo.setBillName(oneAddReceiveDataPojo.getBillName());
                receiveTaskPojo.setBillType(oneAddReceiveDataPojo.getBillType());
                receiveTaskPojo.setClientName(oneAddReceiveDataPojo.getClientName());
                receiveTaskPojo.setClientManageId(oneAddReceiveDataPojo.getClientManageId());
                receiveTaskPojo.setOperateUserId(oneAddReceiveDataPojo.getOperateUserId());
                receiveTaskPojo.setOperateUserName(oneAddReceiveDataPojo.getOperateUserName());
                receiveRecordDao.addReceiveTaskPojo(receiveTaskPojo);
                List<ReceiveTaskDetailPojo> receiveTaskDetailPojoList = new ArrayList<>();
                for (int k = 0; k < addReceiveDataPojoList.size(); k++) {
                    AddReceiveDataPojo addReceiveDataPojo = addReceiveDataPojoList.get(k);
                    ReceiveTaskDetailPojo receiveTaskDetailPojo = new ReceiveTaskDetailPojo();
                    receiveTaskDetailPojo.setReceiveTaskId(receiveTaskPojo.getReceiveTaskId());
                    receiveTaskDetailPojo.setArrivalDetailId(addReceiveDataPojo.getArrivalDetailId());
                    receiveTaskDetailPojo.setItemNo(k+1);
                    receiveTaskDetailPojo.setMaterialDesc(addReceiveDataPojo.getMaterialDesc());
                    receiveTaskDetailPojo.setMaterialUnit(addReceiveDataPojo.getMaterialUnit());
                    receiveTaskDetailPojo.setActualNum(addReceiveDataPojo.getActualNum());
                    receiveTaskDetailPojo.setCreateTime(new Date());
                    receiveTaskDetailPojo.setCreateName(currentUserName);
                    receiveTaskDetailPojo.setCreateUserId(userId);
                    receiveTaskDetailPojoList.add(receiveTaskDetailPojo);
                }
                receiveRecordDao.addReceiveTaskDetailPojoList(receiveTaskDetailPojoList);
            }else {
                return 0;
            }
        }
        int i = arrivalNoticeDao.issueArriveNotice(arrivalNoticeIds);
        return i;
    }

    @Override
    public int arriveDetailAdd(ArrivalNoticeDetailPojo arrivalNoticeDetailPojo) {
        int i = arrivalNoticeDao.arriveDetailAdd(arrivalNoticeDetailPojo);
        return i;
    }

    @Override
    public int deleteArriveDetail(List<Integer> arrivalDetailIds) {
        int i = arrivalNoticeDao.deleteDetailByIds(arrivalDetailIds);
        return i;
    }
}

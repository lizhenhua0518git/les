package com.zkzn.les.wms.receiveRecord.service.impl;


import com.github.pagehelper.PageInfo;
import com.zkzn.les.common.util.page.PageUtil;
import com.zkzn.les.wms.receiveRecord.dao.ReceiveRecordDao;
import com.zkzn.les.wms.receiveRecord.pojo.ReceiveTaskDetailPojo;
import com.zkzn.les.wms.receiveRecord.pojo.ReceiveTaskPojo;
import com.zkzn.les.wms.receiveRecord.service.ReceiveRecordService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class ReceiveRecordServiceImpl implements ReceiveRecordService {

    Logger logger = LoggerFactory.getLogger(ReceiveRecordServiceImpl.class);

    @Resource
    private ReceiveRecordDao receiveRecordDao;

    @Override
    public PageInfo<ReceiveTaskPojo> listReceiveRecordPage(ReceiveTaskPojo receiveTaskPojo) {
        PageUtil.setPageParam(receiveTaskPojo);
        List<ReceiveTaskPojo> list = receiveRecordDao.listReceiveRecord(receiveTaskPojo);
        PageInfo<ReceiveTaskPojo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public List<ReceiveTaskDetailPojo> listReceiveDetailRecord(ReceiveTaskDetailPojo receiveTaskDetailPojo) {
        List<ReceiveTaskDetailPojo> list = receiveRecordDao.listReceiveDetailRecord(receiveTaskDetailPojo);
        return list;
    }

    @Override
    public PageInfo<ReceiveTaskDetailPojo> listReceiveDetailRecordPage(ReceiveTaskDetailPojo receiveTaskDetailPojo) {
        PageUtil.setPageParam(receiveTaskDetailPojo);
        List<ReceiveTaskDetailPojo> list = listReceiveDetailRecord(receiveTaskDetailPojo);
        PageInfo<ReceiveTaskDetailPojo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

}

package com.zkzn.les.wms.pc.inbound.service.impl;

import com.github.pagehelper.PageInfo;

import com.zkzn.les.wms.pc.inbound.dao.LineUpPcDao;
import com.zkzn.les.wms.pc.inbound.service.LineUpPcService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LineUpServiceImpl
 * @Description TODO
 * @Author 刘松山
 * @date 2021/6/17 17:43
 **/
@Service
public class LineUpPcServiceImpl implements LineUpPcService {

    @Resource
    private LineUpPcDao lineUpPcDao;

    @Override
    public PageInfo<Map<String, Object>> listLineUpInfo(Map<String, Object> param) {
//        PageUtil.setPageParam(param);
        List<Map<String, Object>> list =lineUpPcDao.listLineUpInfo(param);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);

        return pageInfo;
    }

    @Override
    public PageInfo<Map<String, Object>> listLineUpDetailRecord(Map<String, Object> param) {
        // TODO Auto-generated method stub
//        PageUtil.setPageParam(param);
        List<Map<String, Object>> list = lineUpPcDao.listLineUpDetailRecord(param);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
        return pageInfo;
    }
}

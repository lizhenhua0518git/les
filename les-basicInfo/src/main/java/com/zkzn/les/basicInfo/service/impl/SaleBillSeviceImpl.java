package com.zkzn.les.basicInfo.service.impl;

import com.zkzn.les.basicInfo.dao.SaleBillDao;
import com.zkzn.les.basicInfo.service.SaleBillSevice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SaleBillSeviceImpl
 * @Description TODO
 * @Author 刘松山
 * @date 2021/5/26 14:26
 **/
@Service
public class SaleBillSeviceImpl implements SaleBillSevice {
    @Resource
    private SaleBillDao saleBillDao;

    @Override
    public List<Map<String, Object>> listSaleBill(Map<String, Object> map) {
        List<Map<String, Object>> list = saleBillDao.listSaleBill(map);
        return list;
    }

    @Override
    public List<Map<String, Object>> listSaleBillDetail(Map<String, Object> map) {
        List<Map<String, Object>> list = saleBillDao.listSaleBillDetail(map);
        return list;
    }
}

package com.zkzn.les.wms.pc.inbound.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.zkzn.les.common.constans.Constants;
import com.zkzn.les.common.util.page.PageUtil;
import com.zkzn.les.wms.pc.inbound.dao.InsectionRecordDao;
import com.zkzn.les.wms.pc.inbound.service.InsectionRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName InsectionRecordServiceImpl
 * @Description  pc端质检查询
 * @Author 刘松山
 * @date 2021/6/23 18:32
 **/
@Service
public class InsectionRecordServiceImpl implements InsectionRecordService {
    Logger logger = LoggerFactory.getLogger(InsectionRecordServiceImpl.class);


    @Resource
    private InsectionRecordDao insectionRecordDao;

    /***
     * @Discription: 质检记录查询 质检状态是 待质检 质检中 质检审核 质检完成
     * 质检冻结数量 = 是指不合格品状态的不合格品数量；
     * 冻结品序列号 = 针对有序列号的物料来说，不合格品物料对应的序列号
     * @param paraMap
     * @return com.github.pagehelper.PageInfo<java.util.Map < java.lang.String, java.lang.Object>>
     * @Author: zhanglei on 2020/9/5 16:19
     */
    @Override
    public PageInfo<Map<String, Object>> listReceiveDetailRecordOfInSpect(Map<String, Object> paraMap) {

        PageUtil.setPageParam(paraMap);
        logger.info("质检记录sql paraMap>>>{}", JSONObject.toJSONString(paraMap));
        List<Map<String, Object>> list = insectionRecordDao.listReceiveDetailRecordOfInSpect(paraMap);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
        return pageInfo;
    }
}

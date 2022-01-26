package com.zkzn.les.basicInfo.service.impl;


import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.zkzn.les.basicInfo.config.exception.ExceptionCasts;
import com.zkzn.les.basicInfo.dao.InspectionDao;
import com.zkzn.les.basicInfo.service.InspectionService;
import com.zkzn.les.basicInfo.util.Ecode;
import com.zkzn.les.basicInfo.util.PageUtil;
import com.zkzn.les.basicInfo.pojo.InspectionRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import java.util.List;
import java.util.Map;

/**
 * @ClassName InspectionServiceImpl
 * @Description TODO
 * @Author zhanglei
 * Date 2020/9/2 11:17
 * @Version 1.0
 **/
@Service
public class InspectionServiceImpl implements InspectionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InspectionServiceImpl.class);
    @Autowired
    private InspectionDao inspectionDao;

    /***
     * @Discription: 质检规则新增  仓库编码+单据类型编码+发货方编码，不能重复；是否质检字段值均为是
     * @return void
     * @Author: zhanglei on 2020/9/2 14:39
     */
    @Override
    public void saveInspect(InspectionRule inspectionRule) {
        if (inspectionRule == null) {
            ExceptionCasts.cast(Ecode.PARAM);
        }
        //仓库编码
        String wareHoseCode;
        //单据类型编码
        String billCode;
        //发货编码
        String supplierCode;
        if ( null ==(billCode=inspectionRule.getBillCode()) || StringUtils.isEmpty(billCode)) {
            ExceptionCasts.cast(Ecode.PARAM);
        }
        //根据条件查询数据唯一性
        List<InspectionRule> listsByCodes = inspectionDao.findListsByCodes(inspectionRule);
        if (listsByCodes !=null && listsByCodes.size() > 0) {
            //唯一性重复
            ExceptionCasts.cast(Ecode.INSPECT_RULE_ERROR);
        }
        //是否质检全为是
        inspectionRule.setIsInspect(1);
        //判断发货方编码是否存在
        if (inspectionRule.getSupplierCode() !=null && !StringUtils.isEmpty(inspectionRule.getSupplierCode())) {
            Map<String, Object> supp = inspectionDao.findSuppCode(inspectionRule);
            if (supp == null || supp.isEmpty()) {
                //编码不存在
                Map<String, Object> ware = inspectionDao.findWareCode(inspectionRule);
                if (ware == null || ware.isEmpty()) {
                    //不存在 异常，编码填写错误
                    ExceptionCasts.cast(Ecode.SUPPLIER_CODE_ERROR);
                }else{
                    inspectionRule.setSupplierName(String.valueOf(ware.get("ORG_NAME")));
                }
            }else{
                inspectionRule.setSupplierName(String.valueOf(supp.get("SUPPLIER_NAME")));
            }
        }
        //保存
        inspectionDao.saveInspect(inspectionRule);
    }

    /***
     * @Discription: 质检规则查询 货方编码支持模糊查询
     * @return java.util.List<com.zkzn.les.common.pojo.InspectionRule>
     * @Author: zhanglei on 2020/9/3 11:34
     */
    @Override
    public PageInfo findLists(InspectionRule inspectionRule) {
        if (inspectionRule == null) {
            ExceptionCasts.cast(Ecode.PARAM);
        }
        PageUtil.setPageParam(inspectionRule);
        List<InspectionRule> lists = inspectionDao.findLists(inspectionRule);
        PageInfo<InspectionRule> pageInfo = new PageInfo(lists);
        return pageInfo;
    }

    /***
     * @Discription: 批量删除
     * @param id
     * @return void
     * @Author: zhanglei on 2020/9/3 13:08
     */
    @Override
    public void deleteById(List<String> id) {
        if (id == null || StringUtils.isEmpty(id)) {
            ExceptionCasts.cast(Ecode.PARAM);
        }
        inspectionDao.deleteById(id);
    }

    /***
     * @Discription: 更新
     * @param inspectionRule
     * @return void
     * @Author: zhanglei on 2020/9/3 13:09
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(InspectionRule inspectionRule) {
        //更新先删除后增加
        if (null == inspectionRule) {
            ExceptionCasts.cast(Ecode.PARAM);
        }
        String id;
        if ((id =inspectionRule.getId()) == null || StringUtils.isEmpty(id)) {
            ExceptionCasts.cast(Ecode.PARAM);
        }
        try {
            //维护创建时间
            InspectionRule inspectionById = inspectionDao.findById(inspectionRule);
            if (inspectionById == null) {
               //数据不存在
               ExceptionCasts.cast(Ecode.FAIL);
            }
            inspectionRule.setCreateTime(inspectionById.getCreateTime());
            inspectionRule.setCreaterName(inspectionById.getCreaterName());
            inspectionRule.setCreaterId(inspectionById.getCreaterId());
            List<String> ids = Lists.newArrayList(id);
            //先删除
            inspectionDao.deleteById(ids);
            //新增
            saveInspect(inspectionRule);
        }catch (Exception e){
            LOGGER.error("error_msg:%s",e.getMessage(),e);
            ExceptionCasts.cast(Ecode.FAIL);
        }
    }
}
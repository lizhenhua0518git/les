package com.zkzn.les.basicInfo.service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.pojo.InspectionRule;


import java.util.List;


/**
 * @ClassName 质检Service
 * @Author zhanglei
 * Date 2020/9/2 11:17
 * @Version 1.0
 **/
public interface InspectionService {

    /***
     * @Discription: 新增质检规则
     * @return void
     * @Author: zhanglei on 2020/9/2 11:44
     */
    void saveInspect(InspectionRule inspectionRule);

    /***
     * @Discription: 质检规则列表查询
     * @return java.util.List<com.zkzn.les.basicInfo.pojo.InspectionRule>
     * @Author: zhanglei on 2020/9/2 13:58
     */
    PageInfo findLists(InspectionRule inspectionRule);

    /***
     * @Discription: 质检规则删除byId
     * @return void
     * @Author: zhanglei on 2020/9/2 13:58
     */
    void deleteById(List<String> id);

    /***
     * @Discription: 质检规则修改
     * @return void
     * @Author: zhanglei on 2020/9/2 14:00
     */
    void updateById(InspectionRule inspectionRule);

}

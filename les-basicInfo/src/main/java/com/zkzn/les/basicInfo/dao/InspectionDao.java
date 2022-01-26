package com.zkzn.les.basicInfo.dao;

import com.zkzn.les.basicInfo.pojo.InspectionRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName 质检Dao
 * @Author zhanglei
 * Date 2020/9/2 11:16
 * @Version 1.0
 **/
@Mapper
public interface InspectionDao {
    /***
     * @Discription: 新增质检规则
     * @return void
     * @Author: zhanglei on 2020/9/2 11:44
     */
    void saveInspect(@Param("inspectionRule")InspectionRule inspectionRule);

    /***
     * @Discription: 质检规则列表查询
     * @return java.util.List<com.zkzn.les.basicInfo.pojo.InspectionRule>
     * @Author: zhanglei on 2020/9/2 13:58
     */
    List<InspectionRule> findLists(@Param("inspectionRule")InspectionRule inspectionRule);

    /***
     * @Discription: 质检规则删除byId
     * @return void
     * @Author: zhanglei on 2020/9/2 13:58
     */
    void deleteById(List<String> list);

    /***
     * @Discription: 质检规则修改
     * @return void
     * @Author: zhanglei on 2020/9/2 14:00
     */
    void updateById(@Param("inspectionRule")InspectionRule inspectionRule);

    /***
     * @Discription: 仓库编码+单据类型编码+发货方编码 查询 非模糊查询
     * @return java.util.List<com.zkzn.les.basicInfo.pojo.InspectionRule>
     * @Author: zhanglei on 2020/9/3 10:53
     */
    List<InspectionRule> findListsByCodes(@Param("inspectionRule") InspectionRule inspectionRule);

    /***
     * @Discription: 供应商编码查询
     * @param inspectionRule
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @Author: zhanglei on 2020/9/3 11:24
     */
    Map<String,Object> findSuppCode(@Param("inspectionRule") InspectionRule inspectionRule);

    /***
     * @Discription: 仓库编码查询
     * @param inspectionRule
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @Author: zhanglei on 2020/9/3 13:30
     */
    Map<String,Object> findWareCode(@Param("inspectionRule")InspectionRule inspectionRule);

    /***
     * @Discription: findById
     * @param inspectionRule
     * @return com.zkzn.les.basicInfo.pojo.InspectionRule
     * @Author: zhanglei on 2020/9/3 13:55
     */
    InspectionRule findById(@Param("inspectionRule")InspectionRule inspectionRule);
}

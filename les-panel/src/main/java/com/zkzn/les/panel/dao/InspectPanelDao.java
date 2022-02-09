package com.zkzn.les.panel.dao;

import com.zkzn.les.common.pojo.panel.Area;
import com.zkzn.les.common.pojo.panel.InspectPanel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface InspectPanelDao {

    /**
     * 查询所有需要展现的质检区域
     * @return
     */
    List<Area> listShowArea();

    /**
     * @Description TD: 获取所有区域下已完成的任务
     * @param parameter
     * @Return
     * @Author sangsang
     * @Date 2020/10/20 10:03
     **/
    List<InspectPanel> listInspectResultPanel(@Param("parameter")Map<String,Object> parameter);
    
    /***
     * @Discription: 质检大屏数据展示
     * @param 
     * @return java.util.List<com.zkzn.les.common.pojo.panel.InspectPanel>
     * @Author: zhanglei on 2021/3/11 18:02
     */
   List<InspectPanel> inspectPanel(); 
}

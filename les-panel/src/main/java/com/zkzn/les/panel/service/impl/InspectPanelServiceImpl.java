package com.zkzn.les.panel.service.impl;

import com.google.common.collect.Maps;
import com.zkzn.les.common.pojo.panel.Area;
import com.zkzn.les.common.pojo.panel.InspectPanel;
import com.zkzn.les.common.pojo.panel.InspectPanelVo;
import com.zkzn.les.common.pojo.panel.InspectScreenModel;
import com.zkzn.les.panel.dao.InspectPanelDao;
import com.zkzn.les.panel.service.InspectPanelService;
import com.zkzn.les.panel.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class InspectPanelServiceImpl implements InspectPanelService {

    @Resource
    InspectPanelDao inspectPanelDao;

    @Override
    public Map<String, Object> listInspectPanel() {

        //TODO　只查询需要展示的仓库的质检区域 || 固定的质检区域
        List<Area> areas = inspectPanelDao.listShowArea();

        //获取所有质检区域下的任务量
        Map<String, Object> parameter = Maps.newHashMap();
        parameter.put("pointTime", DateUtil.getFormatDate(new Date(), "yyyy-MM-dd"));
        //TODO 过滤非展示区域
        List<InspectPanel> areaResult = inspectPanelDao.listInspectResultPanel(parameter);
        //已质检数量
        Integer finishedCount = 0;
        Integer totalCount = 0;
        //根据质检区域ID分组
        Map<String, List<InspectPanel>> task = Maps.newHashMap();
        for (InspectPanel result : areaResult) {
            if (task.containsKey(result.getAreaId())) {
                List<InspectPanel> subList = task.get(result.getAreaId());
                //subList.add(result);
                dedReceiveDetailRecord(subList, result);
            } else {
                List<InspectPanel> subList = task.get(result.getAreaId());
                if (subList == null) {
                    subList = new ArrayList<>();
                }
                subList.add(result);
                task.put(result.getAreaId(), subList);
            }
            //加入正在质检中状态
            if (StringUtils.isEmpty(result.getVote())) {
                result.setVote("-1");
            }
        }
        List<InspectScreenModel> modelList = new ArrayList<>();
        for (Area area : areas) {
            InspectScreenModel model = new InspectScreenModel();
            model.setArea(area.getAreaCode());
            model.setAreaName(area.getAreaName());
            for (String areaId : task.keySet()) {
                if (areaId.equals(area.getId())) {
                    List<InspectPanel> inspectResults = task.get(areaId);
                    model.setInspectPanel(inspectResults);
                    finishedCount += getFinishedCount(inspectResults);
                    totalCount += inspectResults.size();
                }
            }
            modelList.add(model);
        }

        //封装结果集
        Map<String, Object> mapResult = Maps.newHashMap();
        mapResult.put("finishedCount", finishedCount);
        mapResult.put("totalCount", totalCount);
        mapResult.put("task", modelList);
        return mapResult;
    }

    /***
     * @Discription: 质检大屏数据展示
     * @param
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Author: zhanglei on 2021/3/11 15:19
     */
    @Override
    public InspectPanelVo listOfInspectInfoPanel() {
        List<InspectPanel> inspectPanels = inspectPanelDao.inspectPanel();
        Map<String, Object> resultMap = Maps.newHashMap();
        if (inspectPanels.isEmpty()) {
            return null;
        }


        //不为空去求和后的数据计算
        //1.计算当日总数量
        double totalCount = inspectPanels.stream().mapToDouble(InspectPanel::getTotalCount).sum();
        //2.计算今日已完成数量
        double inspectedNum = inspectPanels.stream().mapToDouble(InspectPanel::getInsepectedNum).sum();
        //3.计算当日未完成数量
        double unInspectedNum = inspectPanels.stream().mapToDouble(InspectPanel::getUnInspectNum).sum();
        //4.计算总合格数量
        double totalQualifiedNum = inspectPanels.stream().mapToDouble(InspectPanel::getQualifiedNum).sum();
        //5.计算总不合格数量
        double totalUnQualifiedNum = inspectPanels.stream().mapToDouble(InspectPanel::getUnqualifiedNum).sum();


        List<InspectPanel> list = new ArrayList<>();
        for (InspectPanel inspectPanel : inspectPanels) {

            if (inspectPanel.getTotalCount().equals(inspectPanel.getInsepectedNum())) {
                inspectPanel.setStatusStr("质检完成");
            } else {
                if(inspectPanel.getInsepectedNum()==0){
                    inspectPanel.setStatusStr("待质检");
                }else{
                    inspectPanel.setStatusStr("质检中");
                }
                list.add(inspectPanel);

            }

        }
        for (InspectPanel inspectPanel : list) {
            BigDecimal totalValue   =   new   BigDecimal(inspectPanel.getTotalCount());
            BigDecimal   finishValue   =   new   BigDecimal(inspectPanel.getInsepectedNum());
            Double value =0.00;
            if(totalValue.compareTo(BigDecimal.ZERO) !=0){
                value = finishValue.divide(totalValue, 4, BigDecimal.ROUND_HALF_UP).doubleValue();
            }

            String rateValue =new   BigDecimal(Double.toString(value)).multiply(new   BigDecimal(100)).doubleValue()+"";
            inspectPanel.setInspectSchedule(rateValue);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            inspectPanel.setTime(DateUtil.getHourMin(df.format(inspectPanel.getInspectionTime()),df.format(System.currentTimeMillis())));
        }
            InspectPanelVo inspectPanelVo = new InspectPanelVo();
        inspectPanelVo.setListInspect(list);
        inspectPanelVo.setInspectedNum(inspectedNum);
        inspectPanelVo.setUnInspectedNum(unInspectedNum);
        inspectPanelVo.setTotalCount(totalCount);
        inspectPanelVo.setTotalUnQualifiedNum(totalUnQualifiedNum);
        inspectPanelVo.setTotalQualifiedNum(totalQualifiedNum);
        return inspectPanelVo;
    }

    /**
     * @param subList
     * @param result
     * @Description TD: 根据result.batchNo判断是否将该对象添加到subList
     * @Return void
     * @Author sangsang
     * @Date 2020/11/4 15:50
     **/
    public void dedReceiveDetailRecord(List<InspectPanel> subList, InspectPanel result) {
        boolean flag = true;
        for (InspectPanel item : subList) {
            //批次号相等,质检状态相等 累加
            if (item.getBatchNo().equals(result.getBatchNo()) && !item.getVote().equals(result.getVote())) {
                item.setQualifiedCount(item.getQualifiedCount() + result.getQualifiedCount());
                item.setVote("2");
                flag = false;
                break;
            }
        }
        if (flag) {
            subList.add(result);
        }
    }

    /**
     * @param inspectResults
     * @Description TD: 获取集合中质检完成的数量
     * @Return java.lang.Integer
     * @Author sangsang
     * @Date 2020/11/4 17:16
     **/
    public Integer getFinishedCount(List<InspectPanel> inspectResults) {

        Integer finishedCount = 0;
        for (InspectPanel inspectResult : inspectResults) {
            if (!inspectResult.getVote().equals("-1")) {
                finishedCount++;
            }
        }
        return finishedCount;
    }


}

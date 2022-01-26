package com.zkzn.les.panel.service.impl;

import com.zkzn.les.panel.dao.StationPanelDao;
import com.zkzn.les.panel.service.StationPanelService;
import com.zkzn.les.panel.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class StationPanelServiceImpl implements StationPanelService {

    @Resource
    private StationPanelDao stationPanelDao;

    @Override
    public  Map<String, Object>  listStationPanel() {

        List<Map<String, Object>> list  = stationPanelDao.listStationPanel();

        for(Map<String, Object> map:list){
            String taskNumber = map.get("taskNumber")+"";
            map.put("taskNumber",taskNumber.substring(0,3)+"***"+taskNumber.substring(taskNumber.length()-3,taskNumber.length()));
            map.put("lengthTime", DateUtil.getHourMin(map.get("createTime")+"",map.get("sendTime")+""));
            String createTime =map.get("createTime")+"";
            map.put("createTime",createTime.substring(0,16));
        }
         Map<String, Object> currentMap =this.listCurrentDayInfo();

        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("list",list);
        resultMap.put("currentMap",currentMap);
        return resultMap;
    }

    @Override
    public  Map<String, Object>  listCurrentDayInfo() {
        String currentDay = DateUtil.getCurrentDateWithFormat("yyyy-MM-dd");
        List<Map<String, Object>> list = stationPanelDao.listCurrentDayInfo(currentDay);


        double materialTotalNum =0;
        double materialFinishNum =0;
        double materialUnfinishNum =0;

        double stationTotalNum =0;
        double stationFinishNum =0;
        double stationUnFinishNum =0;

        for(Map<String, Object> map:list){
            String status = map.get("status")+"";

            if(Objects.equals("5",status) || Objects.equals("10",status) || Objects.equals("15",status) || Objects.equals("20",status)){
                BigDecimal pickNum =  new BigDecimal(map.get("pickNum")+"");
                BigDecimal value =pickNum.add(new BigDecimal(String.valueOf(materialUnfinishNum)));
                materialUnfinishNum=   value.doubleValue();
            }else{
                BigDecimal pickNum =  new BigDecimal(map.get("pickNum")+"");
                BigDecimal value =pickNum.add(new BigDecimal(String.valueOf(materialFinishNum)));
                materialFinishNum=   value.doubleValue();
            }


            if(Objects.equals("5",status) || Objects.equals("10",status) || Objects.equals("15",status) || Objects.equals("20",status)){
                BigDecimal pickNum =  new BigDecimal(map.get("stationCodeNum")+"");
                BigDecimal value =pickNum.add(new BigDecimal(String.valueOf(stationUnFinishNum)));
                stationUnFinishNum=   value.doubleValue();
            }else{
                BigDecimal pickNum =  new BigDecimal(map.get("stationCodeNum")+"");
                BigDecimal value =pickNum.add(new BigDecimal(String.valueOf(stationFinishNum)));
                stationFinishNum=   value.doubleValue();
            }


        }
        materialTotalNum =materialUnfinishNum+materialFinishNum;
        stationTotalNum =stationUnFinishNum+stationFinishNum;
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("stationUnFinishNum",stationUnFinishNum);
        resultMap.put("stationFinishNum",stationFinishNum);
        resultMap.put("stationTotalNum",stationTotalNum);
        resultMap.put("materialUnfinishNum",materialUnfinishNum);
        resultMap.put("materialFinishNum",materialFinishNum);
        resultMap.put("materialTotalNum",materialTotalNum);

        return resultMap;
    }
}

package com.zkzn.les.panel.service.impl;

import com.zkzn.les.panel.dao.BoardDao;
import com.zkzn.les.panel.service.BoardService;
import com.zkzn.les.panel.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName InBoundBoardServiceImpl
 * @Description 入库看板
 * @Author 刘松山
 * @date 2021/3/15 14:59
 **/
@Service
public class BoardServiceImpl implements BoardService {

    @Resource
    private BoardDao boardDao;

    @Override
    public List<Map<String, Object>> listAllInBoundInfo() {
        String  currentDate = DateUtil.getCurrentDateWithFormat("yyyy-MM-dd");
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("currentDate",currentDate);
        List<Map<String, Object>> list = boardDao.listAllInBoundInfo(paraMap);

//        List<Map<String, Object>> warehouseList = inBoundBoardDao.listWarehouseList();
//        List<Map<String, Object>> nothadWarehouseList = inBoundBoardDao.listWarehouseList();

//        for(Map<String, Object> map:list){
//            int flag =0;
//            String  warehouseName = map.get("warehouseName")+"";
//            Map<String, Object> resultMap =null;
//            for(Map<String, Object> warehouseMap:list){
//                resultMap =warehouseMap;
//               if(Objects.equals(warehouseName,warehouseMap.get("warehouseName")+"")){
//                   flag =1;
//               }
//            }
//            if(flag == 0){
//                nothadWarehouseList.add(resultMap);
//            }
//        }



            for(Map<String, Object> map:list){

            double receiveNum =Double.parseDouble(map.get("receiveNum")+"");
            double qualifiedNum =Double.parseDouble(map.get("qualifiedNum")+"");

            if(receiveNum==0){
                map.put("rate", "100%");
                map.put("otherRate", "0%");
            }else{
                BigDecimal b1 = new BigDecimal(Double.toString(receiveNum));
                BigDecimal b2 = new BigDecimal(Double.toString(qualifiedNum));
                BigDecimal divide = b2 .divide(b1,4, RoundingMode.HALF_UP);
                BigDecimal subtract = new BigDecimal("1").subtract(divide);
                map.put("rate", divide.multiply(new BigDecimal("100") )+"%");
                map.put("otherRate", subtract.multiply(new BigDecimal("100") )+"%");
            }

        }
//        for(Map<String, Object> map:nothadWarehouseList){
//            map.put("rate", "100%");
//            map.put("otherRate", "0%");
//            map.put("qualifiedNum", "0");
//            map.put("surplusNum", "0");
//            map.put("receiveNum", "0");
//            list.add(map);
//            }
        return list;
    }

    @Override
    public List<Map<String, Object>> listAllOutBoundInfo() {
        String  currentDate = DateUtil.getCurrentDateWithFormat("yyyy-MM-dd");
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("currentDate",currentDate);
        List<Map<String, Object>> list = boardDao.listAllOutBoundInfo(paraMap);
        for(Map<String, Object> map:list){

            double totalNum =Double.parseDouble(map.get("totalNum")+"");
            double finishNum =Double.parseDouble(map.get("finishNum")+"");

            if(totalNum==0){
                map.put("rate", "100%");
                map.put("otherRate", "0%");
            }else{
                BigDecimal b1 = new BigDecimal(Double.toString(totalNum));
                BigDecimal b2 = new BigDecimal(Double.toString(finishNum));
                BigDecimal divide = b2 .divide(b1,4, RoundingMode.HALF_UP);
                BigDecimal subtract = new BigDecimal("1").subtract(divide);
                map.put("rate", divide.multiply(new BigDecimal("100") )+"%");
                map.put("otherRate", subtract.multiply(new BigDecimal("100") )+"%");
            }


        }
        return list;
    }

    @Override
    public List<Map<String, Object>> listInventory() {
        List<Map<String, Object>> list = boardDao.listInventory();

        for(Map<String, Object> map:list){

            double stockCount =Double.parseDouble(map.get("stockCount")+"");
            double enableCount =Double.parseDouble(map.get("enableCount")+"");

            if(stockCount==0){
                map.put("rate", "100%");
                map.put("otherRate", "0%");
            }else{
                BigDecimal b1 = new BigDecimal(Double.toString(stockCount));
                BigDecimal b2 = new BigDecimal(Double.toString(enableCount));
                BigDecimal divide = b2 .divide(b1,4, RoundingMode.HALF_UP);
                BigDecimal subtract = new BigDecimal("1").subtract(divide);
                map.put("rate", divide.multiply(new BigDecimal("100") )+"%");
                map.put("otherRate", subtract.multiply(new BigDecimal("100") )+"%");
            }

        }
        return list;
    }
}

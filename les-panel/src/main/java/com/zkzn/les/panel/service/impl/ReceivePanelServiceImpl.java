package com.zkzn.les.panel.service.impl;

import com.zkzn.les.panel.dao.ReceivePanelDao;
import com.zkzn.les.panel.service.ReceivePanelService;
import com.zkzn.les.panel.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class ReceivePanelServiceImpl  implements ReceivePanelService {

    @Resource
    ReceivePanelDao receivePanelDao;

    @Override
    public List<Map<String, Object>> listReceivePanel() {

        List<String>  idList = new ArrayList<>();

       //TODO 查询所有的仓库点收数据
        List<Map<String, Object>> maps = receivePanelDao.listReceivePanel( );
        for (Map<String, Object> map : maps) {
            idList.add(map.get("id")+"");
        }
        //点收明细
        List<Map<String, Object>> detailList = receivePanelDao.listReceivePanelDetail(idList);

        for(Map<String, Object> map : maps){
            String  id=map.get("id")+"";
            String pointerName ="";

            String supplierName =map.get("supplierName")+"";

//            if(supplierName.length() >12){
//                supplierName=supplierName.substring(0,12)+"..";
//                map.put("supplierName",supplierName);
//            }
            int total =0;
            int finish =0;

            for(Map<String, Object> detailMap:detailList){

                if(Objects.equals(id,detailMap.get("receiveId")+"")){
                    total ++;

                    if(Objects.equals(pointerName,"")){
                        pointerName = detailMap.get("pointerName")+"";
                    }else if(!pointerName.contains(detailMap.get("pointerName")+"")){
                        if(null !=detailMap.get("pointerName") && Objects.equals("",detailMap.get("pointerName")+"")){
                            pointerName=pointerName+";"+ detailMap.get("pointerName");
                        }

                    }
                    if(pointerName.length() >10){
                        pointerName=pointerName.substring(0,10)+"..";
                    }
                    if(Integer.parseInt(detailMap.get("status")+"") >15){
                        finish++;
                    }
                }

            }


            map.put("pointerName",pointerName);
            map.put("lengthTime", DateUtil.getHourMin(map.get("startTime")+"",map.get("endTime")+"sendTime"));
           log.info("========finish:"+finish+"==========total:"+total);
            String rateValue= "0";
            if(finish==total){
                rateValue="100";
            } else
            {
                if(finish !=0){
                    BigDecimal   totalValue   =   new   BigDecimal(total);
                    BigDecimal   finishValue   =   new   BigDecimal(finish);
                    Double value = finishValue.divide(totalValue, 4, BigDecimal.ROUND_HALF_UP).doubleValue();
                     rateValue =new   BigDecimal(Double.toString(value)).multiply(new   BigDecimal(100)).doubleValue()+"";

                }

            }
            map.put("rateValue",rateValue+"%");
        }


        return maps;
    }
}

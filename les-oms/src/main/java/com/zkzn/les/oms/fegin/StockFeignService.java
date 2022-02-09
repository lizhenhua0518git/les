package com.zkzn.les.oms.fegin;

import com.zkzn.les.oms.config.FeignConfig;
import com.zkzn.les.oms.stationOrder.pojo.AppShipmentTaskDetailPojo;
import com.zkzn.les.oms.stationOrder.pojo.UpdateShipmentTaskDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName 库存服务
 * @Description
 * @Author zhanglei
 * Date 2021/6/29 10:15
 * @Version 1.0
 **/
@FeignClient(value = "les-stock-providers",configuration = FeignConfig.class)
public interface StockFeignService {

    @RequestMapping("/stock/inStock/minusStorageBinStock")
    String minusStorageBinStock(UpdateShipmentTaskDetail updateShipmentTaskDetail);

    @RequestMapping("/stock/inStock/reducesMaterialInStock")
    String reducesMaterialInStock(List<AppShipmentTaskDetailPojo> appShipmentTaskDetailPojoList);
}

package com.zkzn.les.basicInfo.feign;

import com.zkzn.les.basicInfo.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**.
 * 
 * @author wangzhou
 * 接口服务的远程调用服务类
 */
@FeignClient(value="les-sap-provider",configuration = FeignConfig.class)
public interface SapFeignService {

	
	/**
	 * .
	 * 功能描述:库内仓位转移
	 * 作者:何闰平
	 * 时间:2020年4月12日 下午4:26:56
	 * @param inParamList
	 * @return
	 */
	@PostMapping("/sap/push/transferWarehouse")
	List<Map<String, Object>> transferWarehouse(List<Map<String, Object>>  inParamList);
	
	/**
	 * .
	 * 功能描述:按物料索引区间进行拉取物料主数据
	 * 作者:何闰平
	 * 时间:2020年4月21日 上午10:31:25
	 * @param inParamList
	 * @return
	 */
	@PostMapping("/sap/push/pullMaterial")
    List<Map<String, Object>> pullMaterialList(Map<String,Object> map);
    /**
     * . 
     *
     * 功能描述:从sap拉取供货商数据
     * 
     * @param map
     * @return
     * @author  刘松山  
     *
     *时间:  2020-07-30 09:15
     *
     */
    @PostMapping("/sap/push/getSupplier")
    String saveSupplier(@RequestParam("params") Map<String, Object> params);
    /**
     * . 
     *
     * 功能描述:同步客户信息
     * 
     * @param map
     * @return
     * @author  刘松山  
     *
     *时间:  2020-08-03 10:27
     *
     */
    @PostMapping("/sap/push/getCustomer")
    String saveCustomer(@RequestParam("params")Map<String, Object> params);
    /**
     * . 
     *
     * 功能描述:同步成本中心数据
     * 
     * @param map
     * @return
     * @author  刘松山  
     *
     *时间:  2020-08-12 08:56
     *
     */
    @PostMapping("/sap/push/getCostCenter")
    String saveCostCenter();
    /**
     * . 
     *
     * 功能描述:同步物料数据
     * 
     * @param map
     * @return
     * @author  刘松山  
     *
     *时间:  2020-08-12 15:52
     *
     */
    @PostMapping("/sap/push/getMaterial")
    String getMaterial(@RequestParam("params")Map<String, Object> params);
    /**
     * . 
     *
     * 功能描述:拉取生产订单
     * 
     * @param map
     * @return
     * @author  刘松山  
     *
     *时间:  2020-08-22 16:34
     *
     */
    @PostMapping("/sap/push/getSapProcessOrder")
  String getSapProcessOrder(@RequestParam("params")Map<String, Object> params);
    
    /**.
     * 
     * @param params
     * @return
     * @Author:wangzhou
     * @date:2020年8月22日
     * @Description:同步内部订单
     */
    @PostMapping("/sap/push/getInternalOrder")
    String getInternalOrder(@RequestParam("params") Map<String, Object> params);
    
}

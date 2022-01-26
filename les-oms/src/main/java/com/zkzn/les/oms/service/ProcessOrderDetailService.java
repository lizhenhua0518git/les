package com.zkzn.les.oms.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.oms.pojo.AssembleOrder;
import com.zkzn.les.oms.pojo.AssembleOrderDetail;
import com.zkzn.les.oms.pojo.ProcessOrder;
import com.zkzn.les.oms.pojo.ProcessOrderDetail;
import com.zkzn.les.oms.pojo.Wave;


/**.
 * 
 * @author luozhihong
 * @date 2020年9月2日
 * @Description :物料需求清单明细 service
 */
public interface ProcessOrderDetailService {


	/**.
	 * 
	 * @param processOrderDetail
	 * @return
	 * @Author:luozhihong
	 * @date:2020年9月2日
	 * @Description:分页查询物料需求清单明细
	 */
	PageInfo<ProcessOrderDetail> listProcessOrderDetail(ProcessOrderDetail processOrderDetail);
	
	/**.
	 * 
	 * @param param
	 * @return
	 * @Author:wangzhou
	 * @date:2020年9月1日
	 * @Description:匹配仓位库存
	 */
	boolean matchStorageStock(Map<String,Object> param);

	/**
	 * 根据生产订单的详情id(物料) 匹配库存数量，生成下架任务
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	boolean matchStorageStockByProcessDetail(List<Map<String,Object>> requestMap) throws Exception;
	/**.
	 * 
	 * @param param
	 * @return
	 * @Author:luozhihong
	 * @date:2020年9月18日
	 * @Description:匹配库存之前，判断是否满足匹配条件
	 */
	boolean beforeMatchStorageStock(Map<String,Object> param);
}

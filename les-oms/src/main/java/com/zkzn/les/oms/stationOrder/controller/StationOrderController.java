package com.zkzn.les.oms.stationOrder.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zkzn.les.common.util.lang.SecurityUserUtil;
import com.zkzn.les.common.util.page.PageUtil;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.oms.stationOrder.pojo.OrderTaskDetailPojo;
import com.zkzn.les.oms.stationOrder.pojo.OrderTaskPojo;
import com.zkzn.les.oms.stationOrder.pojo.PcShipmentTaskPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.oms.pojo.StationOrderWithDetail;
import com.zkzn.les.oms.stationOrder.service.StationOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**.
 *
 * @author luozhihong
 * 出库任务派发
 */
@Api(description="出库任务派发")
@RestController
@RequestMapping(value="/stationOrder")
public class StationOrderController {

	Logger logger = LoggerFactory.getLogger(StationOrderController.class);

	@Autowired
	private StationOrderService stationOrderService;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * 下架清单列表
	 * @param pcShipmentTaskPojo
	 * @param request
	 * @return
	 */
    @ApiOperation("分页查询出库任务派发列表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
    @GetMapping(value="/listStationOrder", produces="application/json;charset=UTF-8")
    public String listStationOrder(PcShipmentTaskPojo pcShipmentTaskPojo, HttpServletRequest request) {
    	PageInfo<PcShipmentTaskPojo> pageInfo = null;
		try{
			String userId = SecurityUserUtil.getCurrentUserId(request);
			String wCode = pcShipmentTaskPojo.getWarehouseCode();
			if ("".equals(wCode)||wCode==null){
				String warehouseCode = SecurityUserUtil.getWarehouseCodeByUserId(redisTemplate,userId);
				pcShipmentTaskPojo.setWarehouseCode(warehouseCode);
			}
			pageInfo = stationOrderService.listStationOrder(pcShipmentTaskPojo);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("查询出库任务派发列表:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "查询出库任务派发列表:"+e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, pageInfo);
    }

	@ApiOperation("查询出库任务派发列表")
	@ApiResponses({
			@ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
    @GetMapping(value ="/findAllOrders", produces="application/json;charset=UTF-8")
    public String findAllOrders(StationOrderWithDetail stationOrderWithDetail,HttpServletRequest request){
		List<StationOrderWithDetail> result = null;
    	try{
			result = stationOrderService.findAllOrders(stationOrderWithDetail);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("查询出库任务派发列表:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "查询出库任务派发列表:"+e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, result);
	}
	@ApiOperation("拣料下发")
	@ApiResponses({
			@ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
	@PostMapping(value = "/taskStationOrder", produces="application/json;charset=UTF-8")
	public  String taskStationOrder(@RequestBody List<String> ids){
		try{
			 stationOrderService.taskStationOrder(ids);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("工位订单下发:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "工位订单下发:"+e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, null);
	}

	@ApiOperation("退货采购订单出库任务派发")
	@ApiResponses({
			@ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
	@PostMapping(value = "/savePurchaseBillRefundStationOrder", produces="application/json;charset=UTF-8")
	public  String savePurchaseBillRefundStationOrder(@RequestBody List<String> ids){
		try{
			String returnString = stationOrderService.savePurchaseBillRefundStationOrder(ids);
			if (!"".equals(returnString)&&returnString!=null){
				return Result.toJson(Ecode.FAIL, returnString);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("退货采购订单出库任务派发:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "退货采购订单出库任务派发:"+e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, null);
	}


	@ApiOperation(value="查询BOM主订单列表")
	@GetMapping(value="/listBOMMainList",produces="application/json;charset=UTF-8")
	public String listBOMMainList(OrderTaskPojo orderTaskPojo,HttpServletRequest request){
		try{
			String userId = SecurityUserUtil.getCurrentUserId(request);
			String wCode = orderTaskPojo.getWarehouseCode();
			if ("".equals(wCode)||wCode==null){
				String warehouseCode = SecurityUserUtil.getWarehouseCodeByUserId(redisTemplate,userId);
				orderTaskPojo.setWarehouseCode(warehouseCode);
			}
			PageUtil.setPageParam(orderTaskPojo);
			List<OrderTaskPojo> list =stationOrderService.listBOMMainList(orderTaskPojo);
			PageInfo<OrderTaskPojo> page = new PageInfo<>(list);
			return Result.toJson(Ecode.SUCCESS, page);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("查询出库订单失败:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "查询出库订单失败");
		}
	}

	@ApiOperation("匹配库存数据")
	@ApiResponses({
			@ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
	@PostMapping(value="/matchingInventory", produces="application/json;charset=UTF-8")
	public String matchingInventory(HttpServletRequest request,@RequestBody List<Integer> orderTaskIds){
		String i1 = SecurityUserUtil.getCurrentUserId(request);
		int userId = Integer.parseInt(i1);
		String currentUserName = SecurityUserUtil.getCurrentUserName(request);
		try {
			int i = stationOrderService.matchingInventory(orderTaskIds.get(0),userId,currentUserName);
			return Result.toJson(Ecode.SUCCESS, "下发送货单数据成功");
		}catch (Exception e) {
			logger.debug("下发送货单数据失败：" + e.getMessage());
			return Result.toJson(Ecode.FAIL, "下发送货单数据失败");
		}
	}

	@ApiOperation("新增出库单数据")
	@ApiResponses({
			@ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
	@PostMapping(value="/bommAdd", produces="application/json;charset=UTF-8")
	public String bommAdd(HttpServletRequest request,@RequestBody OrderTaskPojo orderTaskPojo){
		String userID = SecurityUserUtil.getCurrentUserId(request);
		orderTaskPojo.setCreaterUserId(Integer.parseInt(userID));
		try {
			int i = stationOrderService.bommAdd(orderTaskPojo);
			return Result.toJson(Ecode.SUCCESS, "新增出库单成功");
		}catch (Exception e) {
			logger.debug("新增出库单失败：" + e.getMessage());
			return Result.toJson(Ecode.FAIL, "新增出库单失败");
		}
	}

	@ApiOperation("删除出库单数据")
	@ApiResponses({
			@ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
	@PostMapping(value="/deleteOrderTask", produces="application/json;charset=UTF-8")
	public String deleteOrderTask(HttpServletRequest request,@RequestBody List<Integer> orderTaskIds){
		try {
			int i = stationOrderService.deleteOrderTask(orderTaskIds);
			return Result.toJson(Ecode.SUCCESS, i);
		}catch (Exception e) {
			logger.debug("删除送货单数据失败：" + e.getMessage());
			return Result.toJson(Ecode.FAIL, "删除送货单数据失败");
		}
	}

	/**
	 * 查询出库单详情
	 * @param orderTaskDetailPojo
	 * @return
	 */
	@GetMapping(value="/listBOMDetailList")
	public String listBOMDetailList(OrderTaskDetailPojo orderTaskDetailPojo){
		try{
			PageUtil.setPageParam(orderTaskDetailPojo);
			List<OrderTaskDetailPojo> list =stationOrderService.listBOMDetailList(orderTaskDetailPojo);
			PageInfo<OrderTaskDetailPojo> page = new PageInfo<>(list);
			return Result.toJson(Ecode.SUCCESS, page);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("查询BOM订单失败:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "查询BOM订单明细失败");
		}
	}

    @ApiOperation("新增出库详情数据")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
    @PostMapping(value="/orderTaskDetailAdd", produces="application/json;charset=UTF-8")
    public String orderTaskDetailAdd(HttpServletRequest request,@RequestBody OrderTaskDetailPojo orderTaskDetailPojo){
        String userID = SecurityUserUtil.getCurrentUserId(request);
        orderTaskDetailPojo.setCreaterUserId(Integer.parseInt(userID));
        try {
            int i = stationOrderService.orderTaskDetailAdd(orderTaskDetailPojo);
            return Result.toJson(Ecode.SUCCESS, "新增出库单详情成功");
        }catch (Exception e) {
            logger.debug("新增出库单详情失败：" + e.getMessage());
            return Result.toJson(Ecode.FAIL, "新增出库单详情失败");
        }
    }

	@ApiOperation("删除出库详情数据")
	@ApiResponses({
			@ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
	@PostMapping(value="/deleteOrderTaskDetail", produces="application/json;charset=UTF-8")
	public String deleteOrderTaskDetail(@RequestBody List<Integer> orderTaskDetailIds){
		try {
			int i = stationOrderService.deleteOrderTaskDetail(orderTaskDetailIds);
			return Result.toJson(Ecode.SUCCESS, i);
		}catch (Exception e) {
			logger.debug("删除送货单数据失败：" + e.getMessage());
			return Result.toJson(Ecode.FAIL, "删除送货单数据失败");
		}
	}
}

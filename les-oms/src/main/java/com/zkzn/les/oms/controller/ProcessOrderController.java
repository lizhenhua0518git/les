package com.zkzn.les.oms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.oms.pojo.ProcessOrder;
import com.zkzn.les.oms.service.ProcessOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**.
 *
 * @author wangzhou
 * 工序订单
 */
@Api(description="已锁定订单服务类")
@RestController
@RequestMapping(value="/processOrder")
public class ProcessOrderController {

	@Autowired
	private ProcessOrderService processOrderService;


	Logger logger = LoggerFactory.getLogger(ProcessOrderController.class);


	@Autowired
	private RedisTemplate<String, String> redisTemplate;



	@ApiOperation(value="接收到mes锁定计划后，触发此接口进行锁定订单")
	@ApiResponses({
		@ApiResponse(code=200,message="{\"msg\":\"提示消息\",\"code\":0,\"data\":[]}")
	})
	@ApiImplicitParam(name="params",value="消息携带参数值(此处是MES锁定订单表id)")
	@GetMapping(value="/message/lockOrder")
	public String lockOrder(@RequestParam Map<String,Object> param){
		try{
			String id = param.get("params")!=null?""+param.get("params"):null;
			logger.debug("接收到锁定订单消息："+id);
			if(id==null || id.length()==0){
				return Result.toJson(Ecode.FAIL, "参数错误");
			}
			processOrderService.doLockOrder(id);
		}catch(Exception e){
			logger.debug("锁定订单失败:"+e.getMessage());
			e.printStackTrace();
			return Result.toJson(Ecode.FAIL, "锁定订单失败:");
		}
		return Result.toJson(Ecode.SUCCESS, "锁定订单成功");
	}

	@ApiOperation(value="接收到mes补货信息后，触发此接口生成补货订单信息")
	@ApiResponses({
			@ApiResponse(code=200,message="{\"msg\":\"提示消息\",\"code\":0,\"data\":[]}")
	})
	@ApiImplicitParam(name="params",value="消息携带参数值(此处是异常物料列表)")
	@GetMapping(value="/message/fillMaterialOrder")
	public String fillMaterialOrder(@RequestParam Map<String,Object> param){
		try{
			processOrderService.doFillMaterialOrder(param);
		}catch(Exception e){
			logger.debug("生成补货订单失败:"+e.getMessage());
			e.printStackTrace();
			return Result.toJson(Ecode.FAIL, "生成补货失败:");
		}
		return Result.toJson(Ecode.SUCCESS, "生成补货成功");
	}

	 /**
     * .
     * 功能描述:分页查询物料需求清单
     * 作者:luozhihong
     * 时间:2020年9月2日 下午
     * @param processOrder
     * @param request
     * @return
     */
    @ApiOperation("分页查询物料需求清单")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
    @GetMapping(value="/listProcessOrder", produces="application/json;charset=UTF-8")
    public String listProcessOrder(ProcessOrder processOrder,HttpServletRequest request) {
    	PageInfo<ProcessOrder> pageInfo = null;
		try{
			pageInfo = processOrderService.listProcessOrder(processOrder);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("查询物料需求清单:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "查询物料需求清单:"+e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, pageInfo);
    }
    /**
     * .
     * 功能描述:查询前80按优先级排序的订单列表，并按车间产线筛选
     * 作者:luozhihong
     * 时间:2020年9月25日 下午
     * @param processOrder
     * @param request
     * @return
     */
    @ApiOperation("查询前80按优先级排序的订单列表，并按车间产线筛选")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
    @GetMapping(value="/listSortProcessOrder", produces="application/json;charset=UTF-8")
    public String listSortProcessOrder(ProcessOrder processOrder,HttpServletRequest request) {
    	List<ProcessOrder> list = null;
		try{
			list = processOrderService.listSortProcessOrder(processOrder);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("查询物料需求清单:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "查询物料需求清单:"+e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, list);
    }
    @ApiOperation("匹配库存筛选订单列表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
    @GetMapping(value="/listAllProcessOrder", produces="application/json;charset=UTF-8")
    public String listAllProcessOrder(ProcessOrder processOrder){
    	List<ProcessOrder> processOrderList = null;
    	try{
    		processOrderList = processOrderService.listAllProcessOrder(processOrder);
    	}catch(Exception e){
    		logger.debug("查询生产订单失败:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "查询生产订单失败:");
    	}
    	return Result.toJson(Ecode.SUCCESS, processOrderList);
    }

	@ApiOperation(value="mes手动锁定订单服务")
	@ApiResponses({
			@ApiResponse(code=200,message="{\"msg\":\"成功\",\"code\":0,\"data\":")
	})
	@GetMapping(value="/orderSplit", produces="application/json;charset=UTF-8")
	public String orderSplit(){
		try{
			processOrderService.orderSplit();
		}catch(Exception e){
			logger.debug("mes手动锁定订单失败:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "mes手动锁定订单失败");
		}
		return Result.toJson(Ecode.SUCCESS, null);
	}
}

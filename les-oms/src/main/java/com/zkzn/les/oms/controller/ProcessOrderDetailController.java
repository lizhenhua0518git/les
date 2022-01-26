package com.zkzn.les.oms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.oms.pojo.ProcessOrderDetail;
import com.zkzn.les.oms.service.ProcessOrderDetailService;

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
@Api(description="物料需求清单明细")
@RestController
@RequestMapping(value="/processOrderDetail")
public class ProcessOrderDetailController {

	@Autowired
	private ProcessOrderDetailService processOrderDetailService;


	Logger logger = LoggerFactory.getLogger(ProcessOrderDetailController.class);


	 /**
     * 功能描述:分页查询物料需求清单明细
     * 作者:luozhihong
     * 时间:2020年9月3日
     * @param processOrderDetail
     * @param request
     * @return
     */
    @ApiOperation("分页查询物料需求清单")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
    @GetMapping(value="/listProcessOrderDetail", produces="application/json;charset=UTF-8")
    public String listProcessOrderDetail(ProcessOrderDetail processOrderDetail,HttpServletRequest request) {
    	PageInfo<ProcessOrderDetail> pageInfo = null;
		try{
			pageInfo = processOrderDetailService.listProcessOrderDetail(processOrderDetail);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("查询物料需求清单:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "查询物料需求清单:"+e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, pageInfo);
    }


    @ApiOperation(value="匹配仓位库存")
	@ApiResponses({
		@ApiResponse(code=200,message="{\"msg\":\"提示消息\",\"code\":0,\"data\":[]}")
	})
    @ApiImplicitParam(name="param",value="{ids:[订单表id]}")
	@PostMapping(value="/matchStock")
	public String  matchStock(@RequestBody Map<String,Object> param){
		try{
			//业务类型为2(缺料呼叫)，当呼叫数量大于【未完成分库位数量】，则提示【呼叫数量大于计划数量】，不能匹配库存
			if(!processOrderDetailService.beforeMatchStorageStock(param)){
				return Result.toJson(Ecode.FAIL, "呼叫数量大于计划数量,不能匹配库存");
			}
			boolean flag=processOrderDetailService.matchStorageStock(param);
			if(!flag){
				logger.debug("匹配库存失败:没有相关库存数据");
				return Result.toJson(Ecode.FAIL, "匹配库存失败:没有相关库存数据");
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("匹配库存失败"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "匹配库存失败");
		}
		return Result.toJson(Ecode.SUCCESS, "匹配成功");
	}

	/**
	 * 根据 生成订单详情 匹配库存
	 * @return
	 */
	@PostMapping(value = "/matchStockByProcessDetail")
	public String matchStockByProcessDetail(@RequestBody List<Map<String,Object>> requestMap) {
		try{
			boolean flag=processOrderDetailService.matchStorageStockByProcessDetail(requestMap);
			if(!flag){
				logger.debug("匹配库存失败:没有相关库存数据");
				return Result.toJson(Ecode.FAIL, "匹配库存失败:没有相关库存数据");
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("匹配库存失败"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "服务器错误：匹配库存失败");
		}
		return Result.toJson(Ecode.SUCCESS, "匹配成功");
	}
}

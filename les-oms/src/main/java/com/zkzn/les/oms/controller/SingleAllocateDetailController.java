package com.zkzn.les.oms.controller;

import java.util.List;
import java.util.Map;

import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.oms.pojo.SingleAllocateDetail;
import com.zkzn.les.oms.service.SingleAllocateDetailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(description="调拨单物料详情管理")
@RestController
@RequestMapping(value="/singleAllocateDetail")
public class SingleAllocateDetailController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SingleAllocateDetailService singleAllocateDetailService;



	@ApiOperation(value="分页查询调拨单物料详")
	@ApiResponses({
		@ApiResponse(code=200, message = "[{"
  +" \"assessmentType\": \"评估类型 string\","
  +" \"batchNo\": \"批次号 string\","
  +" \"createTime\": \"创建时间 2020-08-10T10:38:19.202Z\","
  +" \"createrId\": \"创建人id string\","
  +" \"createrName\": \"创建人名 string\","
  +" \"customer\": \"客户编号 string\","
  +" \"customerName\": \"客户名称 string\","
  +" \"factory\": \"工厂 string\","
  +" \"id\": \"主键id string\","
  +" \"inStorageLocation\": \"调入库存地点 string\","
  +" \"itemNo\": \" 行项目号 string\","
  +" \"limit\": \"单页显示数 int\","
  +" \"materialCode\": \"物料号 string\","
  +" \"materialNum\": \"物料需求数量 double\","
  +" \"modifiedTime\": \"修改时间 2020-08-10T10:38:19.202Z\","
  +" \"modifierId\": \"修改人id string\","
  +" \"modifierName\": \"修改人名 string\","
  +" \"outStorageLocation\": \"调出库存地点 string\","
  +" \"page\": 当前页数 int,"
  +" \"parentId\": \"调拨单id string\","
  +" \"reasonMovement\": \"移动原因 string\","
  +" \"receivedNum\": \"收货数量 double\","
  +" \"remark\": \"备注 string\","
  +" \"sapAllocationOrder\": \"sap调拨单号 string\","
  +" \"sellItem\": \"销售订单行 string\","
  +" \"sellOrder\": \"销售订单 string\","
  +" \"sendNum\": \"发货数量 double\","
  +" \"sortColums\": \"排序 string\","
  +" \"status\": \"状态 int\","
  +" \"stockStatus\": \"库存状态 int\","
  +" \"stockType\": \"库存类型 int\","
  +" \"supplierCode\": \"供应商编码 string\","
  +" \"supplierName\": \"供应商名称 string\","
  +" \"totalCount\": \"总数 int\" "
  +" }]")
	})
	@GetMapping(value="/listSingleAllocateDetail",produces="application/json;charset=UTF-8")
	public String listSingleAllocateDetail(@RequestParam Map<String,Object> param){
		PageInfo<Map<String,Object>> pageInfo = null;
		try{
			pageInfo = singleAllocateDetailService.listSingleAllocateDetailPage(param);
		}catch(Exception e){
			logger.debug("查询调拨单物料详失败:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "查询调拨单物料详失败:");
		}
		return Result.toJson(Ecode.SUCCESS, pageInfo);
	}



	@ApiOperation(value="保存调拨单物料详")
	@ApiResponses({
		@ApiResponse(code=200, message = "返回信息")
	})
	@PostMapping(value="/saveSingleAllocateDetail",produces="application/json;charset=UTF-8")
	public String saveSingleAllocate(@RequestBody SingleAllocateDetail singleAllocateDetail){
		try{
			singleAllocateDetailService.saveSingleAllocateDetail(singleAllocateDetail);
		}catch(Exception e){
			logger.debug("保存调拨单物料详失败:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "保存调拨单物料详失败:");
		}
		return Result.toJson(Ecode.SUCCESS, "保存成功");
	}


	@ApiOperation(value="修改调拨单物料详")
	@ApiResponses({
		@ApiResponse(code=200, message = "返回信息")
	})
	@PostMapping(value="/updateSingleAllocateDetail",produces="application/json;charset=UTF-8")
	public String updateSingleAllocate(@RequestBody SingleAllocateDetail singleAllocateDetail){
		try{
			singleAllocateDetailService.updateSingleAllocateDetail(singleAllocateDetail);
		}catch(Exception e){
			logger.debug("修改调拨单物料详失败:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "修改调拨单物料详失败:");
		}
		return Result.toJson(Ecode.SUCCESS, "修改成功");
	}

	@ApiOperation(value="删除调拨单物料详")
	@ApiResponses({
		@ApiResponse(code=200, message = "返回信息")
	})
	@PostMapping(value="/deleteSingleAllocateDetail",produces="application/json;charset=UTF-8")
	public String deleteSingleAllocate(List<String> ids){
		try{
			singleAllocateDetailService.deleteSingleAllocateDetail(ids);
		}catch(Exception e){
			logger.debug("删除调拨单物料详失败:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "删除调拨单物料详失败:");
		}
		return Result.toJson(Ecode.SUCCESS, "删除成功");
	}

}

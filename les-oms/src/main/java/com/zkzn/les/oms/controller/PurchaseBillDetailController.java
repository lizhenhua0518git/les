package com.zkzn.les.oms.controller;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.oms.pojo.PurchaseBillDetail;
import com.zkzn.les.oms.pojo.PurchaseBillRefund;
import com.zkzn.les.oms.pojo.vo.PurchaseBillVO;
import com.zkzn.les.oms.service.PurchaseBillDetailService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


/**.
 *
 * @author luozhihong
 * 采购入库订单明细
 */
@Api(tags={"oms接口服务，其中包含：采购入库订单明细分页查询接口"})
@RestController
@RequestMapping(value="/purchaseBillDetail")
public class PurchaseBillDetailController {

	@Autowired
	private PurchaseBillDetailService purchaseBillDetailService;

	Logger logger = LoggerFactory.getLogger(PurchaseBillDetailController.class);

	 /**
     * 功能描述:分页查询采购入库订单明细
     * 作者:luozhihong
     * 时间:2020年9月28日
     * @param purchaseBillDetail
     * @param request
     * @return
     */
    @ApiOperation("分页查询采购入库订单明细")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
    @GetMapping(value="/listPurchaseBillDetail", produces="application/json;charset=UTF-8")
    public String listPurchaseBillDetail(PurchaseBillDetail purchaseBillDetail, HttpServletRequest request) {
    	PageInfo<PurchaseBillDetail> pageInfo = null;
		try{
			pageInfo = purchaseBillDetailService.listPurchaseBillDetail(purchaseBillDetail);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("查询采购入库订单明细:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "查询采购入库订单明细:"+e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, pageInfo);
    }

	/**
	 * 功能描述:分页查询退货采购入库订单明细
	 * 作者:luozhihong
	 * 时间:2020年12月7日
	 * @param purchaseBillRefund
	 * @param request
	 * @return
	 */
	@ApiOperation("分页查询退货采购入库订单明细")
	@ApiResponses({
			@ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
	@GetMapping(value="/listPurchaseBillDetailTH", produces="application/json;charset=UTF-8")
	public String listPurchaseBillDetailTH(PurchaseBillRefund purchaseBillRefund, HttpServletRequest request) {
		PageInfo<PurchaseBillRefund> pageInfo = null;
		try{
			pageInfo = purchaseBillDetailService.listPurchaseBillDetailTH(purchaseBillRefund);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("查询退货采购入库订单明细:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "查询退货采购入库订单明细:"+e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, pageInfo);
	}


	@ApiOperation("保存要点收的采购订单")
	@ApiImplicitParam(value=
					"{\n" +
					"    \"id\": \"主表ID\",\n" +
					"    \"orderCode\": \"订单CODE\",\n" +
					"    \"supplierName\": \"供应商\",\n" +
					"    \"supplierCode\": \"供应商\",\n" +
					"    \"warehouseCode\": \"仓库CODE\",\n" +
					"    \"warehouseName\": \"仓库名称\",\n" +
					"    \"storageLocation\": \"库存地点\",\n" +
					"    \"detailList\": [\n" +
					"        {\n" +
					"            \"id\": \"详情表ID\",\n" +
					"            \"itemNo\": \"行项目号\",\n" +
					"            \"materialCode\": \"物料CODE\",\n" +
					"            \"materialDesc\": \"描述\",\n" +
					"            \"materialUnit\": \"单位\",\n" +
					"            \"issuedAmount\": \"下发量\"\n" +
					"        }\n" +
					"    ]\n" +
					"}",
			name="json",dataType="string",required=true)
	@ApiResponses({
			@ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]")})
	@PostMapping(value = "/saveTheImportantPurchaseOrder", produces = "application/json;charset=UTF-8")
	public String saveOrderInfo(@RequestBody @Valid PurchaseBillVO data, HttpServletRequest request, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				logger.error("参数验证错误,[{}]",bindingResult.getAllErrors().get(0).getDefaultMessage());
				return Result.toJson(Ecode.FAIL, "保存要点收的采购订单失败");
			}
			return purchaseBillDetailService.saveOrder(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存要点收的采购订单失败:" + e.getMessage());
			return Result.toJson(Ecode.FAIL, "保存要点收的采购订单失败:" + e.getMessage());
		}

	}
}

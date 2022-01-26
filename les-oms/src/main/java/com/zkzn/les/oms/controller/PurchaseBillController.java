package com.zkzn.les.oms.controller;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.oms.pojo.PurchaseBill;
import com.zkzn.les.oms.pojo.PurchaseBillRefund;
import com.zkzn.les.oms.service.PurchaseBillService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**.
 *
 * @author luozhihong
 * 采购入库订单
 */
@Api(tags={"oms接口服务，其中包含：采购入库订单分页查询接口"})
@RestController
@RequestMapping(value="/purchaseBill")
public class PurchaseBillController {

	@Autowired
	private PurchaseBillService purchaseBillService;

	Logger logger = LoggerFactory.getLogger(PurchaseBillController.class);

	 /**
     * 功能描述:分页查询采购入库订单
     * 作者:luozhihong
     * 时间:2020年9月28日
     * @param purchaseBill
     * @param request
     * @return
     */
    @ApiOperation("分页查询采购入库订单")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
    @GetMapping(value="/listPurchaseBill", produces="application/json;charset=UTF-8")
    public String listPurchaseBill(PurchaseBill purchaseBill, HttpServletRequest request) {
    	PageInfo<PurchaseBill> pageInfo = null;
		try{
			pageInfo = purchaseBillService.listPurchaseBill(purchaseBill);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("查询采购入库订单:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "查询采购入库订单:"+e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, pageInfo);
    }

    /**
     * 功能描述:分页查询退货采购订单
     * 作者:luozhihong
     * 时间:2020年12月2日
     * @param purchaseBillRefund
     * @param request
     * @return
     */
    @ApiOperation("分页查询退货采购订单")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
    @GetMapping(value="/listPurchaseBillRefund", produces="application/json;charset=UTF-8")
    public String listPurchaseBillRefund(PurchaseBillRefund purchaseBillRefund, HttpServletRequest request) {
        PageInfo<PurchaseBillRefund> pageInfo = null;
        try{
            pageInfo = purchaseBillService.listPurchaseBillRefund(purchaseBillRefund);
        }catch(Exception e){
            e.printStackTrace();
            logger.debug("查询采购入库订单:"+e.getMessage());
            return Result.toJson(Ecode.FAIL, "查询采购入库订单:"+e.getMessage());
        }
        return Result.toJson(Ecode.SUCCESS, pageInfo);
    }
}

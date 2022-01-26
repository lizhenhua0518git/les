package com.zkzn.les.wms.controller;
import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicParameters;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.wms.constant.WmsConstants;
import com.zkzn.les.wms.pojo.UploadAddress;
import com.zkzn.les.wms.pojo.arrivalNotice.ArrivalNoticePojo;
import com.zkzn.les.wms.service.LineUpTaskService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
@Api(tags = "排号任务")
@RequestMapping("/lineUp")
@RestController
public class LineUpTaskController {
    private static Logger log = LoggerFactory.getLogger(LineUpTaskController.class);
	@Autowired
	private LineUpTaskService lineUpService;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@ApiOperation(value = "手持机扫描送货单接口")
	@DynamicParameters(name = "listArrivalNotice接口参数",properties = {
			@DynamicParameter(name = "billCode",value = "送货单编码",example = "X000111",required = true,dataTypeClass = String.class),
			@DynamicParameter(name = "wareHouseCode",value = "仓库编码"),
	})
	@DynamicResponseParameters(name = "CreateOrderHashMapModel",properties = {
			@DynamicParameter(name = "factory",value = "工厂",example = "X000111",required = true,dataTypeClass = String.class),
			@DynamicParameter(name = "billName",value = "送货单"),
			@DynamicParameter(name = "billType",value = "订单类型"),
			@DynamicParameter(name = "queueStatus",value = "排队状态"),
			@DynamicParameter(name = "storageLocation",value = "库存地点"),
			@DynamicParameter(name = "shipperName",value = "订单号"),
			@DynamicParameter(name = "orderCode",value = "订单类型"),
			@DynamicParameter(name = "arrivalNoticeId",value = "到货通知单id"),
			@DynamicParameter(name = "materialType",value = "物料种类")
	})
	@PostMapping(value = "/listArrivalNotice", produces = "application/json;charset=UTF-8")
	public String listArrivalNotice(@RequestBody Map<String, Object> params, HttpServletRequest request) {
		try {
			Map<String, Object> resultMap = lineUpService.listArrivalNotice(params);
			return Result.toJsonUseApp(Ecode.SUCCESS,resultMap);
		}catch (Exception e) {
			log.error("排号异常:{}",e.getMessage(),e);
			return Result.toJsonUseApp(Ecode.FAIL,e.getMessage());
		}
	}

	@ApiOperation(value = "排号机扫描送货单接口")
	@DynamicParameters(name = "listArrivalNoticePhj接口参数",properties = {
			@DynamicParameter(name = "billCode",value = "送货单编码",example = "X000111",required = true,dataTypeClass = String.class),
			@DynamicParameter(name = "wareHouseCode",value = "仓库编码"),
	})
	@DynamicResponseParameters(name = "",properties = {
			@DynamicParameter(name = "factory",value = "工厂",example = "X000111",dataTypeClass = String.class),
			@DynamicParameter(name = "billName",value = "送货单"),
			@DynamicParameter(name = "billType",value = "订单类型"),
			@DynamicParameter(name = "queueStatus",value = "排队状态"),
			@DynamicParameter(name = "storageLocation",value = "库存地点"),
			@DynamicParameter(name = "shipperName",value = "订单号"),
			@DynamicParameter(name = "orderCode",value = "订单类型")
	})
	@PostMapping(value = "/listArrivalNoticePhj", produces = "application/json;charset=UTF-8")
	public String listArrivalNoticePhj(@RequestParam Map<String, Object> params, HttpServletRequest request) {
		try {
			Map<String, Object> resultMap = lineUpService.listArrivalNotice(params);
			return Result.toJsonUseApp(Ecode.SUCCESS,resultMap);
		} catch (Exception e) {
			log.debug("排号机排号异常:{}" , e.getMessage(),e);
			return Result.toJsonUseApp(Ecode.FAIL, e.getMessage());
		}
	}

	@ApiOperation(value = "保存排号信息接口")
	@DynamicParameters(name = "",properties = {
			@DynamicParameter(name = "billCode",value = "送货号",dataTypeClass = String.class,required = true),
			@DynamicParameter(name = "arrivalNoticeId",value = "到货通知单主表id",dataTypeClass = String.class,required = true),
			@DynamicParameter(name = "driverName",value = "驾驶员名称"),
			@DynamicParameter(name = "driverPhone",value = "驾驶员电话"),
			@DynamicParameter(name = "carCode",value = "车牌号"),
			@DynamicParameter(name = "carType",value = "汽车类型",dataTypeClass = Integer.class),
			@DynamicParameter(name = "emergencyStatus",value = "紧急状态(默认0 数字越大越紧急)",dataTypeClass = Integer.class)

	})
	@DynamicResponseParameters(name = "",properties = {
			@DynamicParameter(name = "queueNum",value = "排队号",example = "X000111",dataTypeClass = String.class),
	})
	@PostMapping(value = "/lineUpNo", produces = "application/json;charset=UTF-8")
	public String lineUpNo(@RequestBody Map<String, Object> params, HttpServletRequest request) {
		long queueNum;
		//String userId = SecurityUserUtil.getCurrentUserId(request);
//		String warehouseCode = SecurityUserUtil.getCurrOrgCode(userId, redisTemplate, WmsConstants.APP);
//		String warehouseName = SecurityUserUtil.getCurrStorageName(userId, redisTemplate, WmsConstants.APP);
//		params.put(WmsConstants.WAREHOUSE_CODE,warehouseCode);
//		params.put(WmsConstants.WAREHOUSE_NAME,warehouseName);
		try {
			queueNum = lineUpService.saveLineUpInfo(params);
		} catch (Exception e) {
			log.debug("排号异常:{}" ,e.getMessage(),e);
			return Result.toJsonUseApp(Ecode.FAIL, e.getMessage());
		}
		return Result.toJsonUseApp(Ecode.SUCCESS, queueNum);
	}


	@ApiOperation(value = "查询排号列表接口")
	@DynamicParameters(name = "",properties = {
			@DynamicParameter(name = "warehouseCode",value = "仓库编码",dataTypeClass = String.class)
	})
	@DynamicResponseParameters(name = "",properties = {
			@DynamicParameter(name = "queueCode",value = "排队号",example = "X000111",dataTypeClass = String.class),
			@DynamicParameter(name = "emergencyStatus",value = "紧急状态"),
			@DynamicParameter(name = "admissionStatus",value = "排号任务状态 0 已排号 1 叫号 2 过号 3 取消排号 4 已完成"),
			@DynamicParameter(name = "arrivalCode",value = "到货通知单"),
			@DynamicParameter(name = "timeValue",value = "时长"),
			@DynamicParameter(name = "warehouseCode",value = "仓库编码"),
			@DynamicParameter(name = "billType",value = "订单类型3 采购收货、4 生产退料收货、5调拨收货、6内部订单退料收货、7委外入库"),
			@DynamicParameter(name = "admissionId",value = "排号任务主表id"),
			@DynamicParameter(name = "purchaseCode",value = "采购订单号"),
			@DynamicParameter(name = "orderCode",value = "订单号"),
			@DynamicParameter(name = "shipperName",value = "供应商名称")
	})
	@PostMapping(value = "/listLineUpMainInfo", produces = "application/json;charset=UTF-8")
	public String listLineUpMainInfo(HttpServletRequest request) {
		List<Map<String, Object>> list = null;
		try {
			Map<String, Object> params = Maps.newHashMap();
//			String userId = SecurityUserUtil.getCurrentUserId(request);
//			String warehouseCode = SecurityUserUtil.getCurrOrgCode(userId, redisTemplate, WmsConstants.APP);
			//TODO 测试数据
			String warehouseCode ="";
			params.putIfAbsent(WmsConstants.WAREHOUSE_CODE,warehouseCode);
			params.putIfAbsent(WmsConstants.LT_STATUS,WmsConstants.NUMBER_1);
			list = lineUpService.listLineUpMainInfo(params);
		} catch (Exception e) {
			log.debug("查询排号列表异常:{}" , e.getMessage());
			return Result.toJsonUseApp(Ecode.FAIL, e.getMessage());
		}
		return Result.toJsonUseApp(Ecode.SUCCESS, list);
	}

	@ApiOperation(value = "获取排号详情接口")

	@DynamicParameters(name = "",properties = {
			@DynamicParameter(name = "admissionId",value = "排号任务主表id",dataTypeClass = String.class)
	})
	@DynamicResponseParameters(name = "",properties = {
			@DynamicParameter(name = "queueTime",value = "排队日期",dataTypeClass = String.class),
			@DynamicParameter(name = "shipperName",value = "发货单位"),
			@DynamicParameter(name = "orderCode",value = "订单号"),
			@DynamicParameter(name = "timeValue",value = "时长"),
			@DynamicParameter(name = "uploadPlatCode",value = "卸货点编码"),
			@DynamicParameter(name = "warehouseCode",value = "仓库编码"),
			@DynamicParameter(name = "materialDesc",value = "物料名称"),
			@DynamicParameter(name = "materialCode",value = "物料编码"),
			@DynamicParameter(name = "receiveNum",value = "物料收货数量"),
			@DynamicParameter(name = "materialUnit",value = "物料单位"),
			@DynamicParameter(name = "uploadPlatId",value = "卸货点id"),
			@DynamicParameter(name = "supplierName",value = "物料供应商名称"),
			@DynamicParameter(name = "supplierCode",value = "物料供应商编码"),
	})
	@PostMapping(value = "/getLineUpInfo", produces = "application/json;charset=UTF-8")
	public String getLineUpInfo(@RequestBody Map<String, Object> params, HttpServletRequest request) {
		ArrivalNoticePojo arrivalNoticePojo= null;
		try {
			arrivalNoticePojo = lineUpService.getLineUpInfo(params);
		} catch (Exception e) {
			log.debug("获取排号详情异常:{}",e.getMessage(),e);
			return Result.toJsonUseApp(Ecode.FAIL, e.getMessage());
		}
		return Result.toJsonUseApp(Ecode.SUCCESS, arrivalNoticePojo);
	}

	@ApiOperation(value = "叫号接口")
	@DynamicParameters(name = "",properties = {
			@DynamicParameter(name = "admissionId",value = "排号任务主表Id",dataTypeClass = String.class,required = true),
			@DynamicParameter(name = "uploadCode",value = "月台编码",dataTypeClass = String.class),
			@DynamicParameter(name = "uploadPlatId",value = "月台主表Id",dataTypeClass = String.class,required = true)
	})
	@PostMapping(value = "/callNum", produces = "application/json;charset=UTF-8")
	public String callNum(@RequestBody Map<String, Object> params, HttpServletRequest request) {
		try {
			 lineUpService.callNum(params);
		} catch (Exception e) {
			log.debug("叫号异常:{}" , e.getMessage(),e);
			return Result.toJsonUseApp(Ecode.FAIL, e.getMessage());
		}
		return Result.toJsonUseApp(Ecode.SUCCESS, "叫号成功");
	}

	@ApiOperation(value = "获取卸货点列表接口")
	@DynamicParameters(name = "",properties = {
			@DynamicParameter(name = "warehouseCode",value = "排号列表里仓库编码",dataTypeClass = String.class,required = true)})

	@DynamicResponseParameters(name = "",properties = {
			@DynamicParameter(name = "uploadCode",value = "卸货点编码",dataTypeClass = String.class),
			@DynamicParameter(name = "uploadName",value = "卸货点名称"),
			@DynamicParameter(name = "id",value = "卸货点id")
	})
	@PostMapping(value = "/listUploadAddress", produces = "application/json;charset=UTF-8")
	public String listUploadAddress(@RequestBody Map<String,Object> param) {
		List<UploadAddress> list = null;
		try {
			UploadAddress uploadAddress = new UploadAddress();
			uploadAddress.setWarehouseCode(param.get(WmsConstants.WAREHOUSE_CODE).toString());
			list =lineUpService.listUploadAddress(uploadAddress);
		} catch (Exception e) {
			log.debug("获取卸货点列表异常:" + e.getMessage());
			return Result.toJsonUseApp(Ecode.FAIL, "获取卸货点列表异常");
		}
		return Result.toJsonUseApp(Ecode.SUCCESS, list);
	}

	@ApiOperation(value = "取消排号接口")
	@DynamicParameters(name = "",properties = {
			@DynamicParameter(name = "arrivalNoticeId",value = "到货通知单主表id",dataTypeClass = String.class,required = true),
			@DynamicParameter(name = "admissionId",value = "排号任务主表Id",dataTypeClass = String.class,required = true),
			@DynamicParameter(name = "uploadPlatId",value = "卸货月台id",dataTypeClass = String.class,required = true)
	})
	@PostMapping(value = "/delLineUpInfo", produces = "application/json;charset=UTF-8")
	public String delLineUpInfo(@RequestBody Map<String, Object> params, HttpServletRequest request) {
		try {
			 lineUpService.delLineUpInfo(params);
		} catch (Exception e) {
			log.debug("取消排号异常:{}" , e.getMessage(),e);
			return Result.toJsonUseApp(Ecode.FAIL, e.getMessage());
		}
		return Result.toJsonUseApp(Ecode.SUCCESS, "排号取消成功");
	}

	@ApiOperation(value = "卸货完成")
	@DynamicParameters(name = "",properties = {
			@DynamicParameter(name = "arrivalNoticeId",value = "到货通知单主表id",dataTypeClass = String.class,required = true),
			@DynamicParameter(name = "admissionId",value = "排号任务主表Id",dataTypeClass = String.class,required = true),
			@DynamicParameter(name = "uploadPlatId",value = "卸货月台id",dataTypeClass = String.class,required = true),
			@DynamicParameter(name = "uploadPlatCode",value = "卸货月台編碼",dataTypeClass = String.class,required = true),
			@DynamicParameter(name = "orderCode",value = "生成订单",dataTypeClass = String.class,required = true),
			@DynamicParameter(name = "arrivalCode",value = "到货通知单编码",dataTypeClass = String.class,required = true),
			@DynamicParameter(name = "warehouseName",value = "仓库名称",dataTypeClass = String.class,required = true),
			@DynamicParameter(name = "warehouseCode",value = "仓库编码",dataTypeClass = String.class,required = true),
			@DynamicParameter(name = "billName",value = "订单名称",dataTypeClass = String.class,required = true),
			@DynamicParameter(name = "shipperCode",value = "送货供应商编码",dataTypeClass = String.class),
			@DynamicParameter(name = "shipperName",value = "送货供应商名称",dataTypeClass = String.class),
			@DynamicParameter(name = "storageLocations",value = "{\"arrivalNoticeId\":\"\",\"admissionId\":\"\",\"shipperCode\":\"\",\"shipperName\":\"\",\n" +
					"     *                \"storageLocations\":[{\"arrivalItemNo\":,\"materialCode\":\"\",\"storageLocation\":\"\"}]\n" +
					"     *               }",dataTypeClass = String.class,required = true)
	})
	@PostMapping(value = "/saveReceiveTaskInfo", produces = "application/json;charset=UTF-8")
	public String saveReceiveTaskInfo(@RequestBody Map<String, Object> params, HttpServletRequest request) {
		try {
			lineUpService.saveReceiveTaskInfo(params);
		} catch (Exception e) {
			log.debug("卸货完成异常:{}" , e.getMessage(),e);
			return Result.toJsonUseApp(Ecode.FAIL, e.getMessage());
		}
		return Result.toJsonUseApp(Ecode.SUCCESS, "卸货完成");
	}


	@ApiOperation(value = "库存地点信息获取")
	@DynamicParameters(name = "",properties = {
			@DynamicParameter(name = "warehouseCode",value = "仓库编码",example = "SC09",dataTypeClass = String.class),
			@DynamicParameter(name = "storageLocation",value = "库存地点",example = "SC09",dataTypeClass = String.class),
			@DynamicParameter(name = "status",value = "库存状态",example ="1",dataTypeClass = Integer.class)
	})
	@DynamicResponseParameters(name = "",properties = {
			@DynamicParameter(name = "factoryCode",value = "sap工厂编码",example = "X000111",required = true,dataTypeClass = String.class),
			@DynamicParameter(name = "warehouseCode",value = "LES仓库编码"),
			@DynamicParameter(name = "storageLocation",value = "les库存地点"),
			@DynamicParameter(name = "status",value = "0禁用1启用")
	})
	@PostMapping(value = "/listStorageLocations", produces = "application/json;charset=UTF-8")
	public String listStorageLocations(@RequestBody Map<String, Object> params, HttpServletRequest request) {
		List<Map<String,Object>> resultList = Lists.newArrayList();
		try {
			//默认查询启用的状态
			params.putIfAbsent(WmsConstants.STATUS,WmsConstants.NUMBER_1);
			resultList = lineUpService.listStorageLocations(params);
		}catch (Exception e) {
			log.error("排号异常:{}",e.getMessage(),e);
			return Result.toJsonUseApp(Ecode.FAIL,e.getMessage());
		}
		return Result.toJsonUseApp(Ecode.SUCCESS,resultList);
	}
}

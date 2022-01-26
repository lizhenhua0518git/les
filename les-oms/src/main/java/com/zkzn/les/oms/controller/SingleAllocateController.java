package com.zkzn.les.oms.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zkzn.les.common.util.excel.ExcelHandleUtil;
import com.zkzn.les.common.util.json.JsonUtil;
import com.zkzn.les.common.util.lang.BeanUtil;
import com.zkzn.les.common.util.lang.SecurityUserUtil;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.common.util.wms.OrderCodeUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.oms.fegin.BasicInfoFeignService;
import com.zkzn.les.oms.pojo.SingleAllocate;
import com.zkzn.les.oms.pojo.SingleAllocateDetail;
import com.zkzn.les.oms.service.SingleAllocateDetailService;
import com.zkzn.les.oms.service.SingleAllocateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(description="调拨单管理")
@RestController
@RequestMapping(value="/singleAllocate")
public class SingleAllocateController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SingleAllocateService singleAllocateService;
	@Autowired
	private SingleAllocateDetailService singleAllocateDetailService;

	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	@Autowired
	private BasicInfoFeignService basicInfoFeignService;

	@ApiOperation(value="分页查询调拨订单")
	@ApiResponses({
		@ApiResponse(code=200, message = "[{"
				+"\"allocationOrder\": \"订单号string\","
  +"\"allocationType\": \"调拨类型 0-非限制 1-不合格品int\", "
  +"\"billName\": \"订单类型string\","
  +"\"billType\": \"订单名称string\","
  +"\"createTime\": \"创建时间2020-08-10T09:03:30.678Z\","
  +"\"createrId\": \"创建人id string\","
  +"\"createrName\": \"创建人名string\","
  +"\"id\": \"主键id string\","
  +"\"inFactory\": \"调入工厂 string\","
  +"\"inStorageLocation\": \"调入库存地点 string\","
  +"\"inWarehouseCode\": \"调入仓库编码 string\","
  +"\"inWarehouseName\": \"调入仓库名称string\","
  +"\"limit\": \"单页显示数 int\","
  +"\"modifiedTime\": \"修改时间 2020-08-10T09:03:30.678Z\","
  +"\"modifierId\": \"修改人id string\","
  +"\"modifierName\": \"修改人名称 string\","
  +"\"operationType\": \"操作类型 string\","
  +"\"outFactory\": \"调出工厂 string\","
  +"\"outStorageLocation\": \"调出库存地点 string\","
  +"\"outWarehouseCode\": \"调出仓库编码 string\","
  +"\"outWarehouseName\": \"调出仓库名称 string\","
  +"\"page\": \"当前页数 int\","
  +"\"remark\": \"备注 string\","
  +"\"sapAllocationOrder\": \"sap调拨单号 string\","
  +"\"sortColums\": \"排序字段 string\","
  +"\"sourceSystem\": \"来源系统 string\","
  +"\"status\": \"状态 int\","
  +"\"statusStr\": \"状态 string\","
  +"\"stockCheckStatus\": \"库存审核状态 int\","
  +"\"stockCheckStatusStr\": \"库存审核状态 string\","
  +"\"systemAllocationOrder\": \"外围系统调拨单号 string\","
  +"\"systemDemandOrder\": \"外围系统需求单号 string\","
  +" \"totalCount\": \"总数 int\","
  +" \"userName\": \"sap用户名称 string\","
  +" \"voucheAccountDate\": \"凭证过账日期 string\","
  +" \"voucheDate\": \"凭证日期 string\" "
  +"}]")
	})
	@GetMapping(value="/listSingleAllocate",produces="application/json;charset=UTF-8")
	public String listSingleAllocate(SingleAllocate singleAllocate){
		PageInfo<SingleAllocate> pageInfo = null;
		try{
			pageInfo = singleAllocateService.listSingleAllocatePage(singleAllocate);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("查询调拨订单失败:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "查询调拨订单失败:");
		}
		return Result.toJson(Ecode.SUCCESS, pageInfo);
	}



	@ApiOperation(value="保存调拨订单")
	@ApiResponses({
		@ApiResponse(code=200, message = "返回信息")
	})
	@PostMapping(value="/saveSingleAllocate",produces="application/json;charset=UTF-8")
	public String saveSingleAllocate(@RequestBody Map<String,Object> param,HttpServletRequest request){
		try{

			String userId = SecurityUserUtil.getCurrentUserId(request);
			String userName = SecurityUserUtil.getCurrentUserName(request);
			String uuid = null;
			SingleAllocate singleAllocate = (SingleAllocate) BeanUtil.mapToObject(param, SingleAllocate.class);

			List<Map<String,Object>> items = (List<Map<String, Object>>) param.get("item");
			List<SingleAllocateDetail> detailList = new ArrayList<SingleAllocateDetail>();
			SingleAllocateDetail tempSingleAllocateDetail = null;
			Double tempMaterailNum = 0.0;
			for(Map<String,Object> map:items){
				tempMaterailNum = Double.valueOf(""+map.get("materialNum"));
				map.put("materialNum", tempMaterailNum);
				tempSingleAllocateDetail = (SingleAllocateDetail) BeanUtil.mapToObject(map,SingleAllocateDetail.class);
				tempSingleAllocateDetail.setCreaterId(userId);
				tempSingleAllocateDetail.setCreaterName(userName);
				tempSingleAllocateDetail.setCreateTime(new Date());
				tempSingleAllocateDetail.setStatus(0);
				tempSingleAllocateDetail.setParentId(uuid);
				tempSingleAllocateDetail.setStockStatus(singleAllocate.getAllocationType());
				tempSingleAllocateDetail.setStockType(singleAllocate.getStockType());
				tempSingleAllocateDetail.setInStorageLocation(singleAllocate.getInStorageLocation());
				tempSingleAllocateDetail.setOutStorageLocation(singleAllocate.getOutStorageLocation());
				detailList.add(tempSingleAllocateDetail);
			}


			singleAllocate.setCreaterId(userId);
			singleAllocate.setCreaterName(userName);
			singleAllocate.setCreateTime(new Date());
			singleAllocate.setId(uuid);
			singleAllocate.setSourceSystem("LES");
			singleAllocate.setAllocationOrder(OrderCodeUtil.crateOrderCode());
			singleAllocateService.saveSingleAllocate(singleAllocate);

			singleAllocateDetailService.saveSingleAllocateDetails(detailList);

		}catch(Exception e){
			logger.debug("保存调拨订单失败:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "保存调拨订单失败:");
		}
		return Result.toJson(Ecode.SUCCESS, "保存成功");
	}


	@ApiOperation(value="修改调拨订单")
	@ApiResponses({
		@ApiResponse(code=200, message = "返回信息")
	})
	@PostMapping(value="/updateSingleAllocate",produces="application/json;charset=UTF-8")
	public String updateSingleAllocate(@RequestBody Map<String,Object> param,HttpServletRequest request){
		try{
			String userId = SecurityUserUtil.getCurrentUserId(request);
			String userName = SecurityUserUtil.getCurrentUserName(request);

			List<Map<String,Object>> items = (List<Map<String, Object>>) param.get("item");
			SingleAllocate singleAllocate = (SingleAllocate) BeanUtil.mapToObject(param, SingleAllocate.class);
			Double tempMaterailNum = 0.0;
			List<SingleAllocateDetail> saveList = new ArrayList<SingleAllocateDetail>();
			List<SingleAllocateDetail> updateList = new ArrayList<SingleAllocateDetail>();
			SingleAllocateDetail tempSingleAllocateDetail = null;
			for(Map<String,Object> map:items){
				tempMaterailNum = Double.valueOf(""+map.get("materialNum"));
				map.put("materialNum", tempMaterailNum);
				tempSingleAllocateDetail = (SingleAllocateDetail) BeanUtil.mapToObject(map,SingleAllocateDetail.class);
				tempSingleAllocateDetail.setModifierId(userId);
				tempSingleAllocateDetail.setModifierName(userName);
				tempSingleAllocateDetail.setModifiedTime(new Date());
				tempSingleAllocateDetail.setParentId(singleAllocate.getId());
				tempSingleAllocateDetail.setStockStatus(singleAllocate.getAllocationType());
				tempSingleAllocateDetail.setStockType(singleAllocate.getStockType());
				tempSingleAllocateDetail.setInStorageLocation(singleAllocate.getInStorageLocation());
				tempSingleAllocateDetail.setOutStorageLocation(singleAllocate.getOutStorageLocation());
				tempSingleAllocateDetail.setStatus(0);
				if(tempSingleAllocateDetail.getId()!=null){
					updateList.add(tempSingleAllocateDetail);
				}else{
					tempSingleAllocateDetail.setCreaterId(userId);
					tempSingleAllocateDetail.setCreaterName(userName);
					tempSingleAllocateDetail.setCreateTime(new Date());
					saveList.add(tempSingleAllocateDetail);
				}

			}
			List<String> deleteIds = (List<String>) param.get("deleteIds");
			if(deleteIds!=null && deleteIds.size()>0){
				singleAllocateDetailService.deleteSingleAllocateDetail(deleteIds);
			}
			if(updateList.size()>0){
				singleAllocateDetailService.updateSingleAllocateDetails(updateList);
			}
			if(saveList.size()>0){
				singleAllocateDetailService.saveSingleAllocateDetails(saveList);
			}
			singleAllocate.setModifierId(userId);
			singleAllocate.setModifierName(userName);
			singleAllocate.setModifiedTime(new Date());
			singleAllocateService.updateSingleAllocate(singleAllocate);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("修改调拨订单失败:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "修改调拨订单失败:");
		}
		return Result.toJson(Ecode.SUCCESS, "修改成功");
	}

	@ApiOperation(value="删除调拨订单")
	@ApiResponses({
		@ApiResponse(code=200, message = "返回信息")
	})
	@PostMapping(value="/deleteSingleAllocate",produces="application/json;charset=UTF-8")
	public String deleteSingleAllocate(@RequestBody List<String> ids){
		try{
			singleAllocateService.deleteSingleAllocate(ids);
		}catch(Exception e){
			logger.debug("删除调拨订单失败:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "删除调拨订单失败:");
		}
		return Result.toJson(Ecode.SUCCESS, "删除成功");
	}


	@ApiOperation(value="批量处理调拨订单：取消/审核")
	@ApiResponses({
		@ApiResponse(code=200, message = "返回信息")
	})
	@ApiImplicitParam(value="{status:\"订单状态\",idarrys:[\"订单主键id\"]}")
	@PostMapping(value="/batchHandleSingleAllocate",produces="application/json;charset=UTF-8")
	public String batchHandleSingleAllocate(@RequestBody Map<String,Object> params,HttpServletRequest request){
		try{
			int status = (int) params.get("status");
			List<String> ids = (List<String>) params.get("idarrys");
			List<SingleAllocate> list = new ArrayList<SingleAllocate>();
			SingleAllocate singleAllocate=null;
			String userId = SecurityUserUtil.getCurrentUserId(request);
			String userName = SecurityUserUtil.getCurrentUserName(request);
			for(String s : ids){
				singleAllocate = new SingleAllocate();
				singleAllocate.setId(s);
				singleAllocate.setModifierId(userId);
				singleAllocate.setModifierName(userName);
				singleAllocate.setModifiedTime(new Date());
				singleAllocate.setStatus(status);
				list.add(singleAllocate);
			}
			singleAllocateService.updateSingleAllocates(list);
		}catch(Exception e){
			logger.debug("取消调拨订单失败:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "取消调拨订单失败:");
		}
		return Result.toJson(Ecode.SUCCESS, "取消成功");
	}

	@ApiOperation("导出调拨订单")
	@ApiResponses({
		@ApiResponse(code = 200, message = "excel文件") })
	@GetMapping(value="/exportSingleAllocate" ,produces="application/json;charset=UTF-8")
	public String exportSingleAllocate(SingleAllocate singleAllocate,HttpServletResponse response,HttpServletRequest request){
		// 标题list
		String[] title = { "订单号 ", "SAP订单号","调出仓库编码", "调出仓库名称 ", "调出仓库库存地", "调入仓库编码", "调入仓库名称", "调入仓库库存地",
				"调出工厂编码","调出工厂名称","调出工厂库存地","调入工厂编码","调入工厂名称","调入工厂库存地","订单状态","库存审核状态","单据类型编码","单据类型名称"
				,"创建人","创建时间","修改人","修改时间"};
		List<String> titleList = Arrays.asList(title);
		// 名称list
		String[] names = { "allocationOrder", "sapAllocationOrder","outWarehouseCode", "outWarehouseName", "outStorageLocation", "inWarehouseCode",
				"inWarehouseName", "inStorageLocation", "outFactory","outFactoryName","sapOutStorageLocation","inFactory"
				,"inFactoryName","sapInStorageLocation","statusStr","stockCheckStatusStr","billType","billName","createrName","createTime","modifierName","modifiedTime"};
		List<String> titleNames = Arrays.asList(names);
		HSSFWorkbook workbook = null;
		OutputStream out = null;
		List<SingleAllocate> list = null;
		try{
			out = response.getOutputStream();
			response.reset();
			String fileName = "调拨订单.xls";
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
			list = this.singleAllocateService.listSingleAllocate(singleAllocate);
			workbook = ExcelHandleUtil.exportExcel(titleList, list, titleNames);
			workbook.write(out);
			return Result.toJson(Ecode.SUCCESS, "导出成功");
		}catch (Exception e) {
			logger.info("导出调拨订单失败" + e.getMessage());
			return Result.toJson(Ecode.FAIL, "导出失败");

		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
				if (workbook != null) {
					workbook.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	@ApiOperation("导入调拨订单")
	@ApiResponses({
		@ApiResponse(code = 200, message = "导入消息提示") })
	@PostMapping(value="/importSingleAllocate" ,produces="application/json;charset=UTF-8")
	public String importSingleAllocate(@RequestParam(value="file")MultipartFile file,HttpServletRequest request){
		Workbook wb = null;
		try{
			if(file!=null){
				InputStream in =file.getInputStream();
				String fileName = file.getOriginalFilename();
				if(fileName!=null && fileName.endsWith(".xlsx")){
					wb = new XSSFWorkbook(in);
				}else if(fileName!=null && fileName.endsWith(".xls")){
					 wb = new HSSFWorkbook(in);
				}else {
				    throw new Exception("文件格式不正确，不能导入！");
				}

				Row row = null;
				Sheet  st = wb.getSheetAt(0);
				if(st==null){
					return Result.toJson(Ecode.FAIL, "导入文件失败,无数据");
				}
				//单据类型
				Set<String> billTypes = new HashSet<String>();
				//库存地点
				Set<String> storageLocations = new HashSet<String>();
				//库存编码
				Set<String> wareHouseCode = new HashSet<String>();
				//物料编码
				Set<String> materialCodes = new HashSet<String>();
				//用订单号当key存放每个订单的数据
				Map<String,Object> orderMap = new HashMap<String,Object>();
				//存放单个订单的数据
				Map<String,Object> orderDataMap = new HashMap<String,Object>();
				//存放订单下行项目的数据
				Map<String,Object> itemMap = null;
				//存放订单下所有行项目
				List<Map<String,Object>> itemlist = null;
				String tempKey = "",tempStockStatus="",tempStockType="";
				String orderCode = null;
				Map<String,Object> sapFactoryMap = null;
				//循环行
				for (int rowIndex = 1; rowIndex <= st.getLastRowNum(); rowIndex++) {
					row = st.getRow(rowIndex);
					orderCode = row.getCell(0).getStringCellValue();
					if(orderCode==null || orderCode.length()==0){
						return Result.toJson(Ecode.FAIL, "订单号不能为空");

					}
					if(row.getCell(1)==null || row.getCell(1).getStringCellValue().length()==0){
						return Result.toJson(Ecode.FAIL, "单据类型不能为空");
					}
					if(row.getCell(2)==null || row.getCell(2).getStringCellValue().length()==0){
						return Result.toJson(Ecode.FAIL, "调出仓库编码不能为空");
					}
					if(row.getCell(3)==null || row.getCell(3).getStringCellValue().length()==0){
						return Result.toJson(Ecode.FAIL, "调出库存地点不能为空");
					}
					if(row.getCell(4)==null || row.getCell(4).getStringCellValue().length()==0){
						return Result.toJson(Ecode.FAIL, "调入仓库编码不能为空");
					}
					if(row.getCell(5)==null || row.getCell(5).getStringCellValue().length()==0){
						return Result.toJson(Ecode.FAIL, "调入库存地点不能为空");
					}
					if(row.getCell(6)==null ){
						return Result.toJson(Ecode.FAIL, "行项目不能为空");
					}
					if(row.getCell(7)==null || row.getCell(7).getStringCellValue().length()==0){
						return Result.toJson(Ecode.FAIL, "物料编码不能为空");
					}
					if(row.getCell(8)==null ){
						return Result.toJson(Ecode.FAIL, "数量不能为空");
					}

					if(row.getCell(8).getNumericCellValue()<=0){
						return Result.toJson(Ecode.FAIL, "数量不能小于0");
					}
					billTypes.add(row.getCell(1).getStringCellValue());
					wareHouseCode.add(row.getCell(2).getStringCellValue());
					wareHouseCode.add(row.getCell(4).getStringCellValue());
					storageLocations.add(row.getCell(3).getStringCellValue());
					storageLocations.add(row.getCell(5).getStringCellValue());
					//sapFactoryMap = SapStorageLocationTransfrom.lesToSapStorageLocation(redisTemplate, row.getCell(3).getStringCellValue());
					if(sapFactoryMap==null){
						return Result.toJson(Ecode.FAIL, "库存地点不存在");
					}
					tempKey =sapFactoryMap.get("factoryCode")+row.getCell(3).getStringCellValue()+row.getCell(7).getStringCellValue();


					if(orderMap.containsKey(orderCode)){
						orderDataMap = (Map<String, Object>) orderMap.get(orderCode);
						tempStockStatus = ""+orderDataMap.get("allocationTypeStr");
						tempStockType = ""+orderDataMap.get("stockTypeStr");
						if(!tempStockStatus.equals(row.getCell(10).getStringCellValue())){
							return Result.toJson(Ecode.FAIL, "同一个订单出现不同库存状态");
						}
						if(!tempStockType.equals(row.getCell(11).getStringCellValue())){
							return Result.toJson(Ecode.FAIL, "同一个订单出现不同库存类型");
						}
						itemlist = (List<Map<String, Object>>) orderDataMap.get("items");
						itemMap =  new HashMap<String,Object>();
						itemMap.put("lesItemNo", Double.valueOf(row.getCell(6).getNumericCellValue()).intValue() );
						itemMap.put("materialCode", row.getCell(7).getStringCellValue());
						itemMap.put("materialNum", row.getCell(8).getNumericCellValue());
						itemMap.put("materialUnit", row.getCell(9).getStringCellValue());
						if(row.getCell(12)!=null){
							itemMap.put("supplierCode",  row.getCell(12).getStringCellValue());
						}
						if(row.getCell(13)!=null){
							itemMap.put("sellOrder",  row.getCell(13).getStringCellValue());
						}
						if(row.getCell(14)!=null){
							itemMap.put("sellItem",  row.getCell(14).getStringCellValue());
						}
						if(row.getCell(15)!=null){
							itemMap.put("customer",  row.getCell(15).getStringCellValue());
						}
						itemlist.add(itemMap);
						orderDataMap.put("items", itemlist);
					}else{
						orderDataMap = new HashMap<String,Object>();
						orderDataMap.put("allocationTypeStr", row.getCell(10).getStringCellValue());
						orderDataMap.put("stockTypeStr", row.getCell(11).getStringCellValue());
						orderDataMap.put("outWarehouseCode", row.getCell(2).getStringCellValue());
						orderDataMap.put("outStorageLocation", row.getCell(3).getStringCellValue());
						orderDataMap.put("inWarehouseCode", row.getCell(4).getStringCellValue());
						orderDataMap.put("inStorageLocation", row.getCell(5).getStringCellValue());
						orderDataMap.put("billType", row.getCell(1).getStringCellValue());
						itemMap =  new HashMap<String,Object>();
						itemMap.put("lesItemNo",  Double.valueOf(row.getCell(6).getNumericCellValue()).intValue()  );
						itemMap.put("materialCode", row.getCell(7).getStringCellValue());
						itemMap.put("materialNum", row.getCell(8).getNumericCellValue());
						itemMap.put("materialUnit", row.getCell(9).getStringCellValue());
						if(row.getCell(12)!=null){
							itemMap.put("supplierCode",  row.getCell(12).getStringCellValue());
						}
						if(row.getCell(13)!=null){
							itemMap.put("sellOrder",  row.getCell(13).getStringCellValue());
						}
						if(row.getCell(14)!=null){
							itemMap.put("sellItem",  row.getCell(14).getStringCellValue());
						}
						if(row.getCell(15)!=null){
							itemMap.put("customer",  row.getCell(15).getStringCellValue());
						}
						itemlist = new ArrayList<Map<String,Object>>();
						itemlist.add(itemMap);
						orderDataMap.put("items", itemlist);
						orderMap.put(orderCode, orderDataMap);
					}
					materialCodes.add(tempKey);
					tempStockType =  row.getCell(11).getStringCellValue();
					if("订单".equals(tempStockType) && (row.getCell(13)==null || row.getCell(14)==null) ){//订单
						return Result.toJson(Ecode.FAIL, "销售订单号和行不能为空");
					}else if("寄售".equals(tempStockType) && row.getCell(12)==null ){//寄售
						return Result.toJson(Ecode.FAIL, "销售订单号和行不能为空");
					}else if("客户".equals(tempStockType) && row.getCell(15)==null ){//客户
						return Result.toJson(Ecode.FAIL, "销售订单号和行不能为空");
					}
				}
				List<Map<String,Object>> documentList = null;
				List<Map<String,Object>> storageLocationList = null;
				List<Map<String,Object>> orgCodeList = null;
				List<Map<String,Object>> materialCodeList = null;
				if(billTypes.size()>0){
					documentList = singleAllocateService.checkDocument(billTypes);
					if(documentList.size()<billTypes.size()){
						return Result.toJson(Ecode.FAIL, "单据类型不存在");
					}
				}
				if(storageLocations.size()>0){
					storageLocationList = singleAllocateService.checkStorageLocation(storageLocations);
					if(storageLocationList.size()<storageLocations.size()){
						return Result.toJson(Ecode.FAIL, "库存地点不存在");
					}
				}
				if(wareHouseCode.size()>0){
					orgCodeList = singleAllocateService.checkOrgCode(wareHouseCode);
					if(orgCodeList.size()<wareHouseCode.size()){
						return Result.toJson(Ecode.FAIL, "仓库编码不存在");
					}
				}
				if(materialCodes.size()>0){
					materialCodeList = singleAllocateService.checkMaterialCode(materialCodes);
					if(materialCodeList.size()<materialCodes.size()){
						return Result.toJson(Ecode.FAIL, "物料编码不存在");
					}
				}
				saveExcelData(orderMap,documentList,storageLocationList,orgCodeList,materialCodeList,request);
			}
		}catch(IllegalStateException e){
			logger.debug("导入失败!"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "导入失败,数据格式不对");
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("导入失败!"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "导入失败");
		}
		finally {
            try {
                if(wb !=null) {
                    wb.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		return Result.toJson(Ecode.SUCCESS, "导入成功");
	}

	/**.
	 *
	 * @param orderMap
	 * @param documentList
	 * @param storageLocationList
	 * @param orgCodeList
	 * @param materialCodeList
	 * @param request
	 * @Author:wangzhou
	 * @date:2020年8月16日
	 * @Description:保持excel表中数据
	 */
	public void saveExcelData(Map<String,Object> orderMap,List<Map<String,Object>> documentList,List<Map<String,Object>> storageLocationList
			,List<Map<String,Object>> orgCodeList,List<Map<String,Object>> materialCodeList,HttpServletRequest request){


		String userId = SecurityUserUtil.getCurrentUserId(request);
		String userName = SecurityUserUtil.getCurrentUserName(request);
		Set<Entry<String, Object>> set = orderMap.entrySet();
		Iterator<Entry<String, Object>> iterator = set.iterator();
		Entry<String, Object> tempEntry = null;
		Map<String,Object> dataMap = null;
		SingleAllocate singleAllocate = null;
		List<SingleAllocateDetail> detailList = new ArrayList<SingleAllocateDetail>();
		List<SingleAllocate> singleAllocateList = new ArrayList<SingleAllocate>();
		String uuid = null;
		int lesItemNo = 1;
		//获取字典表的字典信息
		List<String> dictType = new ArrayList<String>();
		dictType.add("stock_status");//库存状态
		dictType.add("stock_type");//库存类型
		String resultInfo = basicInfoFeignService.listDictItemByTypes(dictType);
		logger.debug("字典信息:"+resultInfo);
		List<Map> dictItem = JsonUtil.jsonStrToList(resultInfo);
		String tempKey = "";
		while(iterator.hasNext()){
			tempEntry = iterator.next();
			dataMap = (Map<String, Object>) tempEntry.getValue();
			//uuid = UuidUtil.getUUID();
			singleAllocate = (SingleAllocate) BeanUtil.mapToObject(dataMap, SingleAllocate.class);
			fillParam(singleAllocate,documentList,storageLocationList,orgCodeList,dictItem);
			List<Map<String,Object>> items = (List<Map<String, Object>>) dataMap.get("items");
			lesItemNo = 1;
			SingleAllocateDetail tempSingleAllocateDetail = null;
			Double tempMaterailNum = 0.0;
			for(Map<String,Object> map:items){
				tempMaterailNum = Double.valueOf(""+map.get("materialNum"));
				map.put("materialNum", tempMaterailNum);

				tempSingleAllocateDetail = (SingleAllocateDetail) BeanUtil.mapToObject(map,SingleAllocateDetail.class);
				tempKey = singleAllocate.getOutFactory()+singleAllocate.getOutStorageLocation()+tempSingleAllocateDetail.getMaterialCode();
				tempSingleAllocateDetail.setCreaterId(userId);
				tempSingleAllocateDetail.setCreaterName(userName);
				tempSingleAllocateDetail.setCreateTime(new Date());
				tempSingleAllocateDetail.setLesItemNo(lesItemNo);
				tempSingleAllocateDetail.setStatus(0);
				tempSingleAllocateDetail.setParentId(uuid);
				tempSingleAllocateDetail.setFactory(singleAllocate.getOutFactory());
				tempSingleAllocateDetail.setStockStatus(singleAllocate.getAllocationType());
				tempSingleAllocateDetail.setStockType(singleAllocate.getStockType());
				tempSingleAllocateDetail.setInStorageLocation(singleAllocate.getInStorageLocation());
				tempSingleAllocateDetail.setOutStorageLocation(singleAllocate.getOutStorageLocation());
				s:for(Map<String,Object> materialMap:materialCodeList){//补充物料描述
					if(tempKey.equals(""+materialMap.get("key"))){
						tempSingleAllocateDetail.setMaterialName(""+materialMap.get("materialDesc"));
						break s;
					}
				}
				detailList.add(tempSingleAllocateDetail);
				lesItemNo++;
			}


			singleAllocate.setCreaterId(userId);
			singleAllocate.setCreaterName(userName);
			singleAllocate.setCreateTime(new Date());
			singleAllocate.setId(uuid);
			singleAllocate.setSourceSystem("LES");
			singleAllocate.setAllocationOrder(OrderCodeUtil.crateOrderCode());
			singleAllocateList.add(singleAllocate);

		}
		if(singleAllocateList.size()>0){
			singleAllocateService.saveSingleAllocates(singleAllocateList);
		}

		if(detailList.size()>0){
			singleAllocateDetailService.saveSingleAllocateDetails(detailList);
		}



	}
	/**.
	 *
	 * @param singleAllocate
	 * @param list
	 * @param type
	 * @Author:wangzhou
	 * @date:2020年8月16日
	 * @Description:补充调拨订单中参数值
	 */
	public void fillParam(SingleAllocate singleAllocate,List<Map<String,Object>> documentList,List<Map<String,Object>> storageLocationList
			,List<Map<String,Object>> orgCodeList,List<Map> dictItem){
		String outWarehouseCode = singleAllocate.getOutWarehouseCode();
		String inWarehouseCode = singleAllocate.getInWarehouseCode();
		String billType = singleAllocate.getBillType();
		String inStorageLocation = singleAllocate.getInStorageLocation();
		String outStorageLocation = singleAllocate.getOutStorageLocation();

		//补充单据名称
		for(Map<String,Object> map :documentList){
			if(billType!=null && billType.equals(""+map.get("documentCode"))){
				singleAllocate.setBillName(""+map.get("documentName"));
			}
		}
		//补充库存仓库编码
		for(Map<String,Object> map :orgCodeList){
			if(outWarehouseCode!=null && outWarehouseCode.equals(""+map.get("orgCode"))){
				singleAllocate.setOutWarehouseName(""+map.get("orgName"));
			}
			if(inWarehouseCode!=null && inWarehouseCode.equals(""+map.get("orgCode"))){
				singleAllocate.setInWarehouseName(""+map.get("orgName"));
			}
		}
		//补充sap库存地点和工厂
		for(Map<String,Object> map :storageLocationList){
			if(inStorageLocation!=null && inStorageLocation.equals(""+map.get("storageLocation"))){
				singleAllocate.setInFactory(""+map.get("factoryCode"));
				singleAllocate.setInFactoryName(""+map.get("factoryName"));
				singleAllocate.setSapInStorageLocation(""+map.get("sapStorageLocation"));
			}
			if(outStorageLocation!=null && outStorageLocation.equals(""+map.get("storageLocation"))){
				singleAllocate.setOutFactory(""+map.get("factoryCode"));
				singleAllocate.setOutFactoryName(""+map.get("factoryName"));
				singleAllocate.setSapOutStorageLocation(""+map.get("sapStorageLocation"));
			}
		}
		//替换库存状态和库存类型
		if(dictItem!=null && dictItem.size()>0){
			for(Map tempDict :dictItem){
				if("stock_status".equals(""+tempDict.get("dictTypeId")) && (""+tempDict.get("itemName")).equals(""+singleAllocate.getAllocationTypeStr()) ){
					singleAllocate.setAllocationType(Integer.valueOf(""+tempDict.get("itemValue")));
				}
				if("stock_type".equals(""+tempDict.get("dictTypeId")) && (""+tempDict.get("itemName")).equals(""+singleAllocate.getStockTypeStr()) ){
					singleAllocate.setStockType(Integer.valueOf(""+tempDict.get("itemValue")));
				}
			}
		}
		singleAllocate.setStockCheckStatus(0);
		singleAllocate.setStatus(0);
	}

}

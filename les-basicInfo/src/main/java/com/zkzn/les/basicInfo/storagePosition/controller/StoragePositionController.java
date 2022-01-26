package com.zkzn.les.basicInfo.storagePosition.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.zkzn.les.basicInfo.pojo.StoragePosition;
import com.zkzn.les.basicInfo.storagePosition.pojo.StoragePositionPojo;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;

import com.zkzn.les.basicInfo.service.AreaService;
import com.zkzn.les.basicInfo.storagePosition.service.StoragePositionService;
import com.zkzn.les.basicInfo.util.ExcelHandleUtil;
import com.zkzn.les.basicInfo.util.PageUtil;
import com.zkzn.les.basicInfo.util.SecurityUserUtil;
import com.zkzn.les.basicInfo.warehouse.pojo.Warehouse;
import com.zkzn.les.basicInfo.warehouse.service.WarehouseService;
import com.zkzn.les.basicInfo.pojo.Area;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * 库位管理
 */
@Api(tags="库位管理基础信息服务")
@RequestMapping(value="/storagePosition")
@RestController
public class StoragePositionController {

	private Logger logger = LoggerFactory.getLogger(StoragePositionController.class);

	@Autowired
	private StoragePositionService storagePositionService;
   	@Autowired
    private AreaService areaService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

	/**
	 * 库位管理列表查询
	 * @param storagePositionPojo
	 * @param request
	 * @return
	 */
	@ApiOperation("库位管理列表查询")
	@ApiResponses({
			@ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
	@GetMapping(value="/queryStoragePosition",produces="application/json;charset=UTF-8")
	public String queryStoragePosition(StoragePositionPojo storagePositionPojo, HttpServletRequest request){
		try{
			String userId = SecurityUserUtil.getCurrentUserId(request);
			String wCode = storagePositionPojo.getWarehouseCode();
			if ("".equals(wCode)||wCode==null){
				String warehouseCode = com.zkzn.les.common.util.lang.SecurityUserUtil.getWarehouseCodeByUserId(redisTemplate,userId);
				storagePositionPojo.setWarehouseCode(warehouseCode);
			}
			PageUtil.setPageParam(storagePositionPojo);
			List<StoragePositionPojo> list = storagePositionService.listStoragePosition(storagePositionPojo);
			PageInfo<StoragePositionPojo> storagePage = new PageInfo<>(list);
			return Result.toJson(Ecode.SUCCESS, storagePage);
		}catch(Exception e){
			logger.debug("查询库位信息失败！"+e.getMessage());
			return Result.toJson(Ecode.FAIL, e.getMessage());
		}
	}

	/**
	 * 保存库位信息
	 * @param storagePositionPojo
	 * @param request
	 * @return
	 */
	@ApiOperation("保存库位信息")
	@ApiResponses({
			@ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
	@PostMapping(value="/saveStoragePosition",produces="application/json;charset=UTF-8")
	public String saveStoragePosition(@RequestBody StoragePositionPojo storagePositionPojo, HttpServletRequest request){
		try{
			String currUid = SecurityUserUtil.getCurrentUserId(request);
			String currentUserName = SecurityUserUtil.getCurrentUserName(request);
			storagePositionPojo.setCreaterUserId(Integer.parseInt(currUid));
			storagePositionPojo.setCreaterName(currentUserName);
			storagePositionPojo.setCreateTime(new Date());
			int i = storagePositionService.saveStoragePosition(storagePositionPojo);
			if (i == 1){
				return Result.toJson(Ecode.STORAGE_POSITION_EXIST, null);
			}
		}catch(Exception e){
			logger.debug("保存库位信息失败！"+e.getMessage());
			return Result.toJson(Ecode.FAIL, e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, "保存库位信息成功！");
	}

	/**
	 * 修改库位信息
	 * @param storagePositionPojo
	 * @param request
	 * @return
	 */
	@ApiOperation("修改库位信息")
	@ApiResponses({
			@ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
	@PostMapping(value="/updateStoragePosition",produces="application/json;charset=UTF-8")
	public String updateStoragePosition(@RequestBody StoragePositionPojo storagePositionPojo,HttpServletRequest request){
		int result =0;
		try{
			String currUid = SecurityUserUtil.getCurrentUserId(request);
			storagePositionPojo.setModifierId(currUid);
			storagePositionPojo.setModifiedTime(new Date());
			result = storagePositionService.updateStoragePosition(storagePositionPojo);
		}catch(Exception e){
			logger.debug("修改库位信息失败！"+e.getMessage());
			return Result.toJson(Ecode.FAIL, e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, result);
	}

	/**
	 * 批量删除库位信息
	 * @param storagePositionIds
	 * @return
	 */
	@ApiOperation("批量删除库位信息")
	@ApiResponses({
			@ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
	@PostMapping(value="/deleteStoragePosition",produces="application/json;charset=UTF-8")
	public String deleteStoragePosition(@RequestBody List<Integer> storagePositionIds){
		try{
			storagePositionService.deleteStoragePosition(storagePositionIds);
		}catch(Exception e){
			logger.debug("删除库位信息失败！"+e.getMessage());
			return Result.toJson(Ecode.FAIL, e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, "删除库位信息成功");
	}



















	/**.
	 *
	 * 功能描述：通过id查询库位信息
	 * 作者：wangzhou
	 * 时间：2018年7月4日
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/queryStorageById",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String queryStoragePositionById(String id){
		try{
		    StoragePosition storagePosition = storagePositionService.getStorageById(id);
			return Result.toJson(Ecode.SUCCESS, storagePosition);
		}catch(Exception e){
			logger.debug("查询库位信息失败！"+e.getMessage());
			return Result.toJson(Ecode.FAIL, e.getMessage());
		}
	}

	/**
	 * 查询工位信息
	 * yzn
	 * 2018-09-28
	 * @return
	 */
	@RequestMapping(value="/getStationCodeList",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getStationCodeList(StoragePosition storagePosition){
		try{
		    PageInfo<StoragePosition> storagePage = storagePositionService.getStationCodeList(storagePosition);
			return Result.toJson(Ecode.SUCCESS, storagePage);
		}catch(Exception e){
			logger.debug("查询工位信息失败！"+e.getMessage());
			return Result.toJson(Ecode.FAIL, e.getMessage());
		}
	}

	/**
	 * 导出excel
	 * yzn
	 */
	@RequestMapping(value="/exportExcel",produces="application/json;charset=UTF-8")
	@ResponseBody
	public void exportExcel(StoragePosition storagePosition,HttpServletResponse response){
		//标题list
		String[] title={"仓位编号","仓位名称","工位编号","货架编号","货架名称","货架层级","状态"};
		List<String> titleList=Arrays.asList(title);
		//名称list
		String [] names={"positionCode","positionName","stationCode","shelfId","shelfName","shelfFloor","status"};
		List<String> titleNames=Arrays.asList(names);
		HSSFWorkbook workbook=null;
		OutputStream out=null;
		try {
		    //List<StoragePosition> list = this.storagePositionService.listStoragePosition(storagePosition);
			List<StoragePosition> list = new ArrayList<>();
			workbook =ExcelHandleUtil.exportExcel(titleList, list, titleNames);
			out = response.getOutputStream();
			response.reset();
			String fileName = "仓位管理.xls";
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
			workbook.write(out);
		} catch (Exception e) {
			logger.info("导出仓位管理数据失败"+e.getMessage());
			e.printStackTrace();
		}finally {
			try {
			    if(out !=null) {
			        out.flush();
			        out.close();
			    }
			    if(workbook !=null) {
			        workbook.close();
			    }
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

    @RequestMapping(value = "/getTreeData", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getTreeData(String id, String nodeType,int nodeLevel) {
        try {
            if(nodeType.equalsIgnoreCase("warehouse")) {
                Warehouse warehouse = new Warehouse();
                warehouse.setLimit(0);
               return getTreeRoot(warehouse);
            }
            if(nodeType.equalsIgnoreCase("area")) {
                Area area = new Area();
                int level = nodeLevel-1;
                if(level<0) {
                    area.setWarehouse(id);
                    area.setParentId("0");
                }else {
                    area.setParentId(id);
                }
                area.setAreaLevel(nodeLevel+1);
                area.setLimit(0);
                List<Area> originalAreaList = areaService.listArea(area);
                if(originalAreaList.size()<1) {
                    StoragePosition storagePosition = new StoragePosition();
//                    storagePosition.setParentId("0");
//                    storagePosition.setAreaId(id);
//                    storagePosition.setLimit(0);
                    return getStorageTree(storagePosition);
                }
                return getAreaTree(originalAreaList);
            }

            if(nodeType.equalsIgnoreCase("storage")) {
                StoragePosition storagePosition = new StoragePosition();
//                storagePosition.setParentId(id);
//                storagePosition.setLimit(0);
                return getStorageTree(storagePosition);
            }
            return Result.toJson(Ecode.FAIL, "获取tree节点数据失败");
        }catch (Exception e) {
            logger.debug("获取tree节点数据失败：" + e.getMessage());
            return Result.toJson(Ecode.FAIL, "获取tree节点数据失败");
        }
    }

    /**
     * .
     * 功能描述: 获得Tree的根目录-仓库
     * 作者:何闰平
     * 时间:2020年4月2日 上午11:26:58
     * @param warehouse
     * @return
     */
    public String getTreeRoot(Warehouse warehouse) {
        try {
            warehouse.setSortColums("org_code asc");
            List<Warehouse> originalWarehouseList = warehouseService.listWarehouse(warehouse);
            List<String> warehouseCode = areaService.getWarehouse();
            List<Map<String, Object>> formateTreeRootList = areaService.formatTreeRoot(originalWarehouseList,warehouseCode);
            return Result.toJson(Ecode.SUCCESS, formateTreeRootList);
        }catch (Exception e) {
            logger.debug("获取tree节点数据失败：" + e.getMessage());
            return Result.toJson(Ecode.FAIL, "获取tree节点数据失败");
        }
    }

    /**
     * .
     * 功能描述: 获得Tree的目录-区域
     * 作者:何闰平
     * 时间:2020年4月2日 上午11:26:58
     * @param
     * @return
     */
    public String getAreaTree(List<Area> originalAreaList) {
        List<String> areaIds = areaService.hasChildren(originalAreaList);
        List<Map<String, Object>> formateTreeAreaList = areaService.formatAreaTree(originalAreaList,areaIds);
        return Result.toJson(Ecode.SUCCESS, formateTreeAreaList);
    }

    /**
     * .
     * 功能描述: 获得Tree的目录-仓位
     * 作者:何闰平
     * 时间:2020年4月2日 上午11:26:58
     * @param
     * @return
     */
    public String getStorageTree(StoragePosition storagePosition) {
        //List<StoragePosition> originalStoragePositionList = storagePositionService.listStoragePosition(storagePosition);
		List<StoragePosition> originalStoragePositionList = new ArrayList<>();
		List<String> storagePositionIds = storagePositionService.hasChildren(originalStoragePositionList);
       List<Map<String, Object>> formateTreeAreaList = storagePositionService.formatPositionTree(originalStoragePositionList,storagePositionIds);
        return Result.toJson(Ecode.SUCCESS, formateTreeAreaList);
    }

    /**
   * .
   * 功能描述: 修改仓位状态
   * 作者:何闰平
   * 时间:2020年3月29日 下午12:43:37
   * @param
   * @return
   */
  @RequestMapping(value = "/changeStatus", produces = "application/json;charset=UTF-8")
  @ResponseBody
  public String changeStatus(@RequestBody Map<String,Object> map) {
     List<String> ids = (List<String>) map.get("ids");
     String remark = map.get("remark").toString();
     int status = Integer.parseInt(map.get("status").toString() !=null?map.get("status").toString():"1");
     int result = 0;
      try {
          result = storagePositionService.changeStatus(ids,remark,status);
          return Result.toJson(Ecode.SUCCESS, result);
      } catch (Exception e) {
          logger.debug("修改区域状态失败！" + e.getMessage());
          return Result.toJson(Ecode.FAIL, e.getMessage());
      }
  }

	/**
	 * @param file
	 * @param response
	 * @param request
	 * @return
	 */
  @ApiOperation("导入库位信息")
  @ApiResponses({
		  @ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
  @PostMapping(value="/importStorage",produces="application/json;charset=UTF-8")
  public String importStorage(@RequestParam(value="file") MultipartFile file,HttpServletResponse response,HttpServletRequest request){
      Workbook wb = null;
	  int flag = 0;
      try{
          if(file!=null){
			  String currUid = SecurityUserUtil.getCurrentUserId(request);
			  String currUName = SecurityUserUtil.getCurrentUserName(request);
              InputStream in =file.getInputStream();
              String fileName = file.getOriginalFilename();
              if(fileName!=null && fileName.endsWith(".xlsx")){
                  wb = new XSSFWorkbook(in);
              }else if(fileName!=null && fileName.endsWith(".xls")){
                   wb = new HSSFWorkbook(in);
              }else {
                  throw new Exception("文件格式不正确，不能导入！");
              }
              //查询仓库信息
			  Map<String,Object> wareHouseMap = new HashMap<>();
			  List<String> wareHouseList = storagePositionService.getWareHouse();
			  for (int i = 0; i < wareHouseList.size(); i++) {
				  wareHouseMap.put(wareHouseList.get(i),"");
			  }
//			  //查询库位信息
//			  Map<String,Map<String,String>> stringStringMap = new HashMap<>();
//			  List<StoragePosition> storagePositionList = storagePositionService.getStoragePosition();
//			  for (int i = 0; i < storagePositionList.size(); i++) {
//				  StoragePosition storagePosition = storagePositionList.get(i);
////				  String warehouse = storagePosition.getWarehouse();
//				  String positionCode = storagePosition.getPositionCode();
////				  String storageId = storagePosition.getId();
////				  String key = warehouse+positionCode;
////				  if (stringStringMap.containsKey(warehouse)){
////					  Map<String,String> stringObjectMap = stringStringMap.get(warehouse);
////					  stringObjectMap.put(key,storageId);
////					  stringStringMap.put(warehouse,stringObjectMap);
////				  }else {
//					  Map<String,String> stringObjectMap = new HashMap<>();
////					  stringObjectMap.put(key,storageId);
////					  stringStringMap.put(warehouse,stringObjectMap);
////				  }
//			  }
//			  //查询库区信息
//			  Map<String,Map<String,String>> areaMap = new HashMap<>();
//			  List<Area> areaList = storagePositionService.getArea();
//			  for (int i = 0; i < areaList.size(); i++) {
//				  Area area = areaList.get(i);
//				  String warehouse = area.getWarehouse();
//				  String areaCode = area.getAreaCode();
//				  String areaId = area.getId();
//				  String key = warehouse+areaCode;
//				  if (areaMap.containsKey(warehouse)){
//					  Map<String,String> areaObjectMap = areaMap.get(warehouse);
//					  areaObjectMap.put(key,areaId);
//					  areaMap.put(warehouse,areaObjectMap);
//				  }else {
//					  Map<String,String> areaObjectMap = new HashMap<>();
//					  areaObjectMap.put(key,areaId);
//					  areaMap.put(warehouse,areaObjectMap);
//				  }
//			  }
//			  //查询作业区域信息
//			  Map<String,Map<String,String>> workAreaMap = new HashMap<>();
//			  List<WorkArea> workAreaList = storagePositionService.getWorkArea();
//			  for (int i = 0; i < workAreaList.size(); i++) {
//				  WorkArea workArea = workAreaList.get(i);
//				  String warehouseCode = workArea.getWarehouseCode();
//				  String workAreaCode = workArea.getWorkAreaCode();
//				  String workAreaId = workArea.getId();
//				  String key = warehouseCode+workAreaCode;
//				  if (workAreaMap.containsKey(warehouseCode)){
//					  Map<String,String> workAreaObjectMap = workAreaMap.get(warehouseCode);
//					  workAreaObjectMap.put(key,workAreaId);
//					  workAreaMap.put(warehouseCode,workAreaObjectMap);
//				  }else {
//				  	  Map<String,String> workAreaObjectMap = new HashMap<>();
//					  workAreaObjectMap.put(key,workAreaId);
//					  workAreaMap.put(warehouseCode,workAreaObjectMap);
//				  }
//			  }
              List<StoragePosition> insertList = new ArrayList<>();//插入的list集合
              List<StoragePosition> updateList = new ArrayList<>();//更新的list集合
              Sheet st = wb.getSheetAt(0);
              //循环行
              for (int rowIndex = 1; rowIndex <= st.getLastRowNum(); rowIndex++) {
				  Row row = st.getRow(rowIndex);
				  String id = (ExcelHandleUtil.getCellValue(row.getCell(0))+"").replaceAll(" ", "");
				  String positionCode = (ExcelHandleUtil.getCellValue(row.getCell(1))+"").replaceAll(" ", "");//仓位编码
				  String name = (ExcelHandleUtil.getCellValue(row.getCell(2))+"").replaceAll(" ", "");//仓位名称
				  String whosecode = (ExcelHandleUtil.getCellValue(row.getCell(3))+"").replaceAll(" ", "");//所属仓库
				  String status = (ExcelHandleUtil.getCellValue(row.getCell(4))+"").replaceAll(" ", "");//状态
				  //a.	校验导入数据的仓库编码在仓库信息表中是否存在：不存在，该条数据导入失败；存在，继续进行下一校验。
				  if (!wareHouseMap.containsKey(whosecode)){
					  continue;
				  }
				  StoragePosition storagePosition = new StoragePosition();
				  storagePosition.setPositionCode(positionCode);
				  storagePosition.setPositionName(name);
				  storagePosition.setWarehouseCode(whosecode);
				  storagePosition.setStatus(Integer.valueOf(status));
				  if (!id.equals("null")){
					  storagePosition.setId(id);
					  storagePosition.setModifierId(currUid);
					  storagePosition.setModifierName(currUName);
					  storagePosition.setModifiedTime(new Date());
					  updateList.add(storagePosition);
				  }else{
					  storagePosition.setCreaterId(currUid);
					  storagePosition.setCreaterName(currUName);
					  storagePosition.setCreateTime(new Date());
					  insertList.add(storagePosition);
				  }
              }
              if(insertList.size()>0){
              	  flag=1;
				  int i = storagePositionService.saveStoragePositionList(insertList);
				  System.out.println("新增"+i);
			  }
              if(updateList.size()>0){
				  flag=1;
				  int i = storagePositionService.updateStorageositionList(updateList);
				  System.out.println("更新"+i);
			  }
          }
      }catch(Exception e){
          logger.debug("导入失败!"+e.getMessage());
          return Result.toJson(Ecode.FAIL, "导入失败");
      }finally {
          try {
              if(wb !=null) {
                  wb.close();
              }
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
      if (flag==1){
		  return Result.toJson(Ecode.SUCCESS, "导入成功");
	  }else {
		  return Result.toJson(Ecode.FAIL, "导入失败,没有新增或修改");
	  }

  }

  /**
   * .
   * 功能描述: 导出仓位信息
   * 作者:何闰平
   * 时间:2020年4月20日 下午2:33:58
   * @param
   * @param request
   * @param response
   * @return
   */
  @ApiOperation("库位管理列表导出")
  @ApiResponses({
		  @ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
  @GetMapping(value="/exportStorage",produces="application/json;charset=UTF-8")
  public String exportStorage(StoragePosition storagePosition,HttpServletRequest request,HttpServletResponse response){
      OutputStream output=null;
      try{
          PageUtil.setPageParam(storagePosition);
          //List<StoragePosition> storagePositionList = storagePositionService.listStoragePosition(storagePosition);
		  List<StoragePosition> storagePositionList = new ArrayList<>();
//          String [] title = {"ID","'仓库编码'","'仓库名称'","'库位编码'","'库区编码'","'库区名称'","'库区类型'","'作业区域编码'",
//				  "作业区域名称","状态","创建人","创建时间","修改人","修改时间"};
//          String [] name = {"id","warehouseCode","warehouseName","positionCode","areaCode","areaName","areaType","workAreaCode",
//				  "workAreaName","statusName","createrName","createTime","modifierName","modifiedTime"};
		  String [] title = {"ID","'仓库编码'","'仓库名称'","'库位编码'","'作业区域编码'",
				  "作业区域名称","状态","创建人","创建时间","修改人","修改时间"};
		  String [] name = {"id","warehouseCode","warehouseName","positionCode","workAreaCode",
				  "workAreaName","statusName","createrName","createTime","modifierName","modifiedTime"};
          List<String> titleList = Arrays.asList(title);
          List<String> filedNames = Arrays.asList(name);
          output = response.getOutputStream();
          response.reset();
          response.setHeader("Content-disposition", "attachment; filename="+System.currentTimeMillis()+"_storagePosition.xls");
          response.setContentType("application/vnd.ms-excel;charset=UTF-8");
          HSSFWorkbook workbook = ExcelHandleUtil.exportExcel(titleList, storagePositionList, filedNames);
          workbook.write(output);
          return Result.toJson(Ecode.SUCCESS, "导出成功");
      }catch(Exception e){
          logger.debug("导出失败："+e.getMessage());
          return Result.toJson(Ecode.FAIL, e.getMessage());
      }finally{
          if(output!=null){
              try {
                  output.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
  }



	/**
	 * 根据仓位号+仓库号查询仓位信息
	 * @param storagePosition
	 * @return
	 */
	@RequestMapping(value = "/getStoragePositionByCode", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getStoragePositionByCode(@RequestBody StoragePosition storagePosition) {
		try {
			List<StoragePosition> list = storagePositionService.getStoragePositionByCode(storagePosition);
			return Result.toJson(Ecode.SUCCESS, list);
		} catch (Exception e) {
			logger.debug("查询仓位信息失败！" + e.getMessage());
			return Result.toJson(Ecode.FAIL, e.getMessage());
		}
	}

}

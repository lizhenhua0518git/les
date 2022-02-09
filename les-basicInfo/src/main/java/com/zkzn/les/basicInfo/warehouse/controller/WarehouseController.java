package com.zkzn.les.basicInfo.warehouse.controller;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.util.Ecode;
import com.zkzn.les.basicInfo.util.PageUtil;
import com.zkzn.les.basicInfo.util.Result;
import com.zkzn.les.basicInfo.util.SecurityUserUtil;
import com.zkzn.les.basicInfo.warehouse.pojo.Warehouse;
import com.zkzn.les.basicInfo.warehouse.pojo.WarehousePojo;
import com.zkzn.les.basicInfo.warehouse.service.WarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**.
 *
 * 功能描述：仓库信息的controller层
 *              增、删、改、查（ID、分页）、启用、禁用
 * @author 何闰平
 * 时间：2020年3月27日
 */
@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 初始化仓库下拉框
     * @param warehousePojo
     * @param request
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PostMapping(value = "/initWarehouseSelect", produces = "application/json;charset=UTF-8")
    public String initWarehouseSelect(@RequestBody WarehousePojo warehousePojo, HttpServletRequest request) {
        try {
            String currentUserId = SecurityUserUtil.getCurrentUserId(request);
            warehousePojo.setBelongUserId(Integer.parseInt(currentUserId));
            List<WarehousePojo> warehousePojoList = warehouseService.initWarehouseSelect(warehousePojo);
            return Result.toJson(Ecode.SUCCESS, warehousePojoList);
        } catch (Exception e) {
            logger.debug("初始化仓库下拉框失败！" + e.getMessage());
            return Result.toJson(Ecode.FAIL, e.getMessage());
        }
    }



























    /**.
     *
     * 功能描述：新增仓库信息
     * 作者：何闰平
     * 时间：2020年3月27日
     * @param warehouse
     * @return
     */
    @PostMapping(value = "/saveWarehouse", produces = "application/json;charset=UTF-8")
    public String saveWarehouse(@RequestBody Warehouse warehouse, HttpServletRequest request) {
        int result = 0;
        try {

            String currUid = SecurityUserUtil.getCurrentUserId(request);
            String currUName = SecurityUserUtil.getCurrentUserName(request);
            warehouse.setCreaterId(currUid);
            warehouse.setCreaterName(currUName);
            warehouse.setCreateTime(new Date());
            warehouse.setStatus(1);
            result = warehouseService.saveWarehouse(warehouse);
            //判断仓库编码及仓库名称去重

            if(result==-10) {
            	 return Result.toJson(Ecode.FAIL, "保存失败！仓库编码(名称)已存在");
            }
            return Result.toJson(Ecode.SUCCESS, result);
        } catch (Exception e) {
            logger.debug("保存仓库信息失败！" + e.getMessage());
            return Result.toJson(Ecode.FAIL, e.getMessage());
        }
    }

    /**
     * .
     * 功能描述:批量删除仓库信息
     * 作者:何闰平
     * 时间:2020年3月27日 下午2:47:17
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteWarehouse", produces = "application/json;charset=UTF-8")
    public String deleteWarehouse(@RequestBody List<String> id) {
        int result = 0;
        try {
            result = warehouseService.deleteWarehouse(id);
            return Result.toJson(Ecode.SUCCESS, result);
        } catch (Exception e) {
            logger.debug("删除仓库信息失败！" + e.getMessage());
            return Result.toJson(Ecode.FAIL, e.getMessage());
        }
    }

    /**
     * .
     * 功能描述:修改仓库信息
     * 作者:何闰平
     * 时间:2020年3月27日 下午2:59:32
     * @param warehouse
     * @return
     */
    @PostMapping(value = "/updateWarehouse", produces = "application/json;charset=UTF-8")
    public String updateWarehouse(@RequestBody Warehouse warehouse, HttpServletRequest request) {

        int result = 0;
        try {
        	 String currUid = SecurityUserUtil.getCurrentUserId(request);
             String currUName = SecurityUserUtil.getCurrentUserName(request);
            warehouse.setModifierId(currUid);
            warehouse.setModifierName(currUName);
            warehouse.setModifiedTime(new Date());

            result = warehouseService.updateWarehouse(warehouse);
            return Result.toJson(Ecode.SUCCESS, result);
        } catch (Exception e) {
            logger.debug("修改仓库信息失败！" + e.getMessage());
            return Result.toJson(Ecode.FAIL, e.getMessage());
        }
    }

    /**
     * .
     * 功能描述:分页查询仓库信息
     * 作者:何闰平
     * 时间:2020年3月27日 下午3:03:52
     * @param area
     * @param request
     * @return
     */
    @GetMapping(value = "/", produces = "application/json;charset=UTF-8")
    public String queryWarehouse(Warehouse warehouse,HttpServletRequest request) {
        try {
            PageUtil.setPageParam(warehouse);
            if(warehouse.getPermission() !=null && warehouse.getPermission()==1) {
                String currUid = SecurityUserUtil.getCurrentUserId(request);
                String orgCode = SecurityUserUtil.getCurrOrgCode(currUid, redisTemplate, "pc");
               if(orgCode!=null) {
                   warehouse.setOrgCode(orgCode);
               }
            }
            List<Warehouse> list= warehouseService.listWarehouse(warehouse);
            PageInfo<Warehouse> warehouses = new PageInfo<Warehouse>(list);
            return Result.toJson(Ecode.SUCCESS, warehouses);
        } catch (Exception e) {
            logger.debug("查询仓库信息失败！" + e.getMessage());
            return Result.toJson(Ecode.FAIL, e.getMessage());
        }
    }

    /**
     * .
     * 功能描述: 通过id查询仓库信息
     * 作者:何闰平
     * 时间:2020年3月27日 下午3:06:11
     * @param id
     * @return
     */
    @PostMapping(value = "/queryWarehouseById", produces = "application/json;charset=UTF-8")
    public String queryWarehouseById(String id) {
        try {
            Warehouse warehouse = warehouseService.getWarehouseById(id);
            return Result.toJson(Ecode.SUCCESS, warehouse);
        } catch (Exception e) {
            logger.debug("查询仓库信息失败！" + e.getMessage());
            return Result.toJson(Ecode.FAIL, e.getMessage());
        }
    }

    /**
     * .
     * 功能描述: 修改仓库状态
     * 作者:何闰平
     * 时间:2020年3月29日 下午12:43:37
     * @param id
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PostMapping(value = "/changeStatus", produces = "application/json;charset=UTF-8")
    public String changeStatus(@RequestBody Map map, HttpServletRequest request) {
        List<String> ids = (List<String>) map.get("ids");
        String remark = map.get("remark").toString();
        int status = Integer.parseInt(map.get("status").toString() !=null?map.get("status").toString():"1");
        String currUid = SecurityUserUtil.getCurrentUserId(request);
        String currUName = SecurityUserUtil.getCurrentUserName(request);
       int result = 0;
        try {
            result = warehouseService.changeStatus(ids,status,remark,currUid,currUName,new Date());
            return Result.toJson(Ecode.SUCCESS, result);
        } catch (Exception e) {
            logger.debug("修改仓库状态失败！" + e.getMessage());
            return Result.toJson(Ecode.FAIL, e.getMessage());
        }
    }

}

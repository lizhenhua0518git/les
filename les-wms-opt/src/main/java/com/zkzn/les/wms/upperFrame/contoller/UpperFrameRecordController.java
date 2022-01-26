package com.zkzn.les.wms.upperFrame.contoller;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.common.util.lang.SecurityUserUtil;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.wms.upperFrame.pojo.UpperFrameRecord;
import com.zkzn.les.wms.upperFrame.service.UpperFrameRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**.
 *
 * @author luozhihong
 * 上架记录
 */
@Api(tags={"wms接口服务，其中包含：上架记录分页查询接口"})
@RestController
@RequestMapping(value="/upperFrameRecord")
public class UpperFrameRecordController {

	Logger logger = LoggerFactory.getLogger(UpperFrameRecordController.class);

	@Autowired
	private UpperFrameRecordService upperFrameRecordService;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * 查询上架列表
	 * @param upperFrameRecord
	 * @param request
	 * @return
	 */
    @ApiOperation("分页查询上架记录")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
    @GetMapping(value="/listUpperFrameRecord", produces="application/json;charset=UTF-8")
    public String listUpperFrameRecord(UpperFrameRecord upperFrameRecord, HttpServletRequest request) {
    	PageInfo<UpperFrameRecord> pageInfo = null;
		try{
			//当前登录人对应有权限的仓库
			String userId = SecurityUserUtil.getCurrentUserId(request);
			String wCode = upperFrameRecord.getWarehouseCode();
			if ("".equals(wCode)||wCode==null){
				String warehouseCode = SecurityUserUtil.getWarehouseCodeByUserId(redisTemplate,userId);
				upperFrameRecord.setWarehouseCode(warehouseCode);
			}
			pageInfo = upperFrameRecordService.listUpperFrameRecord(upperFrameRecord);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("查询上架记录:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "查询上架记录:"+e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, pageInfo);
    }

}

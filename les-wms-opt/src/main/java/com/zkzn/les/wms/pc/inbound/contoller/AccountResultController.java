package com.zkzn.les.wms.pc.inbound.contoller;



import com.github.pagehelper.PageInfo;
import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;
import com.zkzn.les.wms.pc.inbound.service.AccountResultService;
import com.zkzn.les.wms.pojo.AccountResult;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**.
 * 
 * @author luozhihong
 * 过账记录
 */
@Api(tags="过账记录服务接口类")
@RestController
@RequestMapping(value="/accountResult")
public class AccountResultController {

	@Autowired
	private AccountResultService accountResultService;

	Logger logger = LoggerFactory.getLogger(AccountResultController.class);
	
	
	 /**
     * 功能描述:分页查询过账记录
     * 作者:luozhihong
     * 时间:2020年9月2日 下午
     * @param accountResult
     * @param request
     * @return
     */
    @ApiOperation("分页查询过账记录")
    @ApiResponses({
            @ApiResponse(code = 200, message = "[{\"code\":\"0 成功 -1 失败\",\"msg\":\"成功\",\"data\":\"空对象\"}]") })
    @GetMapping(value="/listAccountResult", produces="application/json;charset=UTF-8")
    public String listAccountResult(AccountResult accountResult, HttpServletRequest request) {
    	PageInfo<AccountResult> pageInfo = null;
		try{
			pageInfo = accountResultService.listAccountResult(accountResult);
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("查询过账记录:"+e.getMessage());
			return Result.toJson(Ecode.FAIL, "查询过账记录:"+e.getMessage());
		}
		return Result.toJson(Ecode.SUCCESS, pageInfo);
    }


    

}

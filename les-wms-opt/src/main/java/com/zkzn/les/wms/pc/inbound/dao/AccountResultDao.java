package com.zkzn.les.wms.pc.inbound.dao;



import com.zkzn.les.wms.pojo.AccountResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface AccountResultDao {

    
    /**.
	 * 
	 * @param accountResult
	 * @return
	 * @Author:luozhihong
	 * @date:2020年9月9日
	 * @Description:分页查询过账记录
	 */
	List<AccountResult> listAccountResult(@Param("accountResult") AccountResult accountResult);


}
package com.zkzn.les.oms.fegin;

import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;

import java.util.List;


public class BasicInfoFeignServiceImpl implements BasicInfoFeignService{

	@Override
	public String listDictItemByTypes(List<String> dictType) {
		// TODO Auto-generated method stub
		return Result.toJson(Ecode.FAIL, "服务发送熔断");
	}

}

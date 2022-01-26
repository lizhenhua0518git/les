package com.zkzn.les.oms.fegin;

import com.zkzn.les.common.util.response.Ecode;
import com.zkzn.les.common.util.response.Result;

public class GawFeignServiceImpl implements GawFeignService{

	@Override
	public String pushProcessOrder(String jsonStr) {
		// TODO Auto-generated method stub
		return Result.toJson(Ecode.FAIL, "服务发送熔断");
	}

}

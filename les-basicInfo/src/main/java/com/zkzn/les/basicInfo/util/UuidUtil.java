package com.zkzn.les.basicInfo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UuidUtil {
	/**
	 * 获取单个UUID
	 * @return
	 */
	public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

	/**
	 * 根据序列值获取UUID集合
	 * @param num
	 * @return
	 */
	public static List<String> getUUID(int num){
		List<String> list = new ArrayList<String>();
		for(int i=1;i<=num;i++){
			String uuid = UUID.randomUUID().toString().replace("-", "");
			list.add(uuid);
		}
        return list;
    }
}

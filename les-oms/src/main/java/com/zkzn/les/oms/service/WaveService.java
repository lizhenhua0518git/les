package com.zkzn.les.oms.service;

import java.util.List;

import com.zkzn.les.oms.pojo.Wave;

public interface WaveService {

	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年4月6日下午5:25:44
	 * List<Wave>
	 * @param storageLocation
	 * @return
	 * 功能描述:：获取已经生成的波次号
	 */
	List<Wave> listWave(List<String> storageLocation);
}

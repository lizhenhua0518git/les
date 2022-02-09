package com.zkzn.les.oms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zkzn.les.oms.pojo.Wave;


/**.
 * 
 * @author wangzhou
 * 波次信息 dao
 */
@Mapper
public interface WaveDao {

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
	/**.
	 * 
	 * 创建人: wangzhou
	 * 时间:2020年4月6日下午5:39:54
	 * int
	 * @param waveList
	 * @return
	 * 功能描述:批量增加波次表数据
	 */
	int saveWaves(List<Wave> waveList);
}

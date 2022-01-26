package com.zkzn.les.basicInfo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zkzn.les.basicInfo.pojo.StrategyCar;

/**.
 * 
 *
 * 功能描述：载具策略dao层
 * @author wangzhou
 * 时间：2018年9月2日
 */
@Mapper
public interface StrategyCarDao {

	/**.
	 * 
	 * 功能描述：查询载具列表
	 * 作者：wangzhou
	 * 时间：2018年9月2日
	 * @param strategyCar
	 * @return
	 */
	List<StrategyCar> listStrategyCar(StrategyCar strategyCar);
	/**.
	 * 
	 * 功能描述：批量插入载具策略
	 * 作者：wangzhou
	 * 时间：2018年10月25日
	 * @param param
	 * @return
	 */
	int insertStrategy(List<Map<String,Object>> param);
	/**.
	 * 
	 * 功能描述：通过分厂-产线-工位-工序物料号-工位物料号查询载具策略
	 * 作者：wangzhou
	 * 时间：2018年10月25日
	 * @param substrList
	 * @return
	 */
	List<Map<String,Object>> listHasStrategy(List<String> substrList);
	
	/**.
	 * 
	 * 功能描述：单条插入载具策略
	 * 作者：wangzhou
	 * 时间：2018年10月26日
	 * @param strategyCar
	 * @return
	 */
	int insertStrategySing(StrategyCar strategyCar);
	/**.
	 * 
	 * 功能描述：更新载具策略
	 * 作者：wangzhou
	 * 时间：2018年10月26日
	 * @param strategyCar
	 * @return
	 */
	int updateStrategy(StrategyCar strategyCar);
	/**.
	 * 
	 * 功能描述：删除载具策略
	 * 作者：wangzhou
	 * 时间：2018年10月26日
	 * @param ids
	 * @return
	 */
	int deleteStrategy(List<String> ids);
	/**.
	 * 
	 * 功能描述：批量更新载具策略
	 * 作者：wangzhou
	 * 时间：2018年10月27日
	 * @param strageyList
	 * @return
	 */
	int updateStrategyList(List<Map<String,Object>> strageyList);
	/**.
	 * 
	 * 功能描述：查询总的数据量
	 * 作者：wangzhou
	 * 时间：2018年10月29日
	 * @param strategyCar
	 * @return
	 */
	int countStrategy(StrategyCar strategyCar);
	/**.
	 * 
	 * 功能描述：批量插入bom信息
	 * 作者：wangzhou
	 * 时间：2019年3月11日
	 * @param sapBomList
	 * @return
	 */
	int insertSapBom(List<Map<String,Object>> sapBomList);
	/**
	 * . 
	 *
	 * 功能描述:分厂+产线+工位号+工序组件号+工位组件号做主键
	 * 
	 * @param strategyCar
	 * @return
	 * @author  刘松山  
	 *
	 *时间:  2020-05-22 17:12
	 *
	 */
	int  countByInfo(StrategyCar strategyCar);
}

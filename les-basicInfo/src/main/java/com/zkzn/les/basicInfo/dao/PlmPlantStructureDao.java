/*
 * 文 件 名:  PlmPlantStructureDao.java
 * 版    权:  
 * 描    述:  描述:
 * 修 改 人:  刘松山 
 * 修改时间:  2020年8月7日
 * 跟踪单号:  跟踪单号:
 * 修改单号:  修改单号:
 * 修改内容:  修改内容:
 */
package com.zkzn.les.basicInfo.dao;

import java.util.List;
import java.util.Map;

import com.zkzn.les.basicInfo.pojo.PlantModelDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zkzn.les.basicInfo.pojo.PlmPlantStructure;

/**.
 *
 * 功能描述:工厂结构数据层
 * 
 * 时间:  2020-08-07 16:17
 *
 * @author  刘松山  
 * 
 */
@Mapper
public interface PlmPlantStructureDao {

	/**
	 * . 
	 *
	 * 功能描述:查询工厂结构数据列表
	 * 
	 * @param plmPlantStructure
	 * @return
	 * @author  刘松山  
	 *
	 *时间:  2020-08-07 16:30
	 *
	 */
	List<Map<String, Object>> listTree(@Param(value="parentIdList")  List<List<String>> parentIdList);
	/**
	 * . 
	 *
	 * 功能描述:查询节点下的信息列表
	 * 
	 * @param plmPlantStructure
	 * @return
	 * @author  刘松山  
	 *
	 *时间:  2020-08-10 09:01
	 *
	 */
	List<PlmPlantStructure> listBypage(PlmPlantStructure plmPlantStructure);
	/**
	 * 
	 * @param modelType
	 * @return
	 * @Author:wangzhou
	 * @date:2020年8月11日
	 * @Description:通过类型查找工厂模型信息
	 */
	List<Map<String,Object>> listFactoryByType(String modelType);

	/***
	 * @Discription: 车间 产线 工位下拉级联查询
	 * @param id
	 * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 * @Author: zhanglei on 2020/9/16 11:04
	 */
	List<Map<String,Object>> selectFactoryById(@Param("param") Map<String,Object> param);

	/**
	 *@params 通过type和pid获取信息
	 *@return 
	 *@Author Lty
	 *@date 2020/11/5
	 *@Description
	 */
	List<Map<String, Object>> listFactoryModelByDto(@Param("dto") PlantModelDto model);
}

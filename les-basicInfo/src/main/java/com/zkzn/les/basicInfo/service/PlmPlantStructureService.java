/*
 * 文 件 名:  PlmPlantStructureService.java
 * 版    权:  
 * 描    述:  描述:
 * 修 改 人:  刘松山 
 * 修改时间:  2020年8月7日
 * 跟踪单号:  跟踪单号:
 * 修改单号:  修改单号:
 * 修改内容:  修改内容:
 */
package com.zkzn.les.basicInfo.service;

import java.util.List;
import java.util.Map;

import com.zkzn.les.basicInfo.pojo.PlantModelDto;
import com.zkzn.les.basicInfo.pojo.PlmPlantStructure;

/**.
 *
 * 功能描述:
 * 
 * 时间:  2020-08-07 16:18
 *
 * @author  刘松山  
 * 
 */
public interface PlmPlantStructureService {
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
	List<Map<String, Object>> listTree(PlmPlantStructure plmPlantStructure);
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
	 * @Discription: 车间，产线，工位级联查询
	 * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 * @Author: zhanglei on 2020/9/16 11:05
	 */
	List<Map<String,Object>> selectFactoryById(Map<String,Object> param);

	/**
	 *@params
	 *@return
	 *@Author Lty
	 *@date 2020/11/5
	 *@Description 通过type 和pid获取数据
	 */
	List<Map<String, Object>> listFactoryModel(PlantModelDto model);
}

/*
 * 文 件 名:  PlmPlantStructureServiceImpl.java
 * 版    权:  
 * 描    述:  描述:
 * 修 改 人:  刘松山 
 * 修改时间:  2020年8月7日
 * 跟踪单号:  跟踪单号:
 * 修改单号:  修改单号:
 * 修改内容:  修改内容:
 */
package com.zkzn.les.basicInfo.service.impl;

import java.util.*;

import com.zkzn.les.basicInfo.pojo.PlantModelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkzn.les.basicInfo.dao.PlmPlantStructureDao;
import com.zkzn.les.basicInfo.pojo.PlmPlantStructure;
import com.zkzn.les.basicInfo.service.DictItemsService;
import com.zkzn.les.basicInfo.service.PlmPlantStructureService;


/**
 * .
 *
 * 功能描述:
 * 
 * 时间: 2020-08-07 16:18
 *
 * @author 刘松山
 * 
 */
@Service
public class PlmPlantStructureServiceImpl implements PlmPlantStructureService {
	@Autowired
	private PlmPlantStructureDao plmPlantStructureDao;

	@Autowired
	private DictItemsService dictItemsService;

	/**
	 * .
	 *
	 * 重载方法
	 * 
	 * 功能描述:
	 * 
	 * @param plmPlantStructure
	 * @return
	 * @author 刘松山
	 *
	 *         时间: 2020-08-07 16:31
	 */
	@Override
	public List<Map<String, Object>> listTree(PlmPlantStructure plmPlantStructure) {
		List<Map<String, Object>> headChildrenList = new ArrayList<>();
		List<Map<String, Object>> firstChildrenList = null;
		List<Map<String, Object>> secondChildrenList = null;
		List<Map<String, Object>> threeChildrenList = null;

		List<String> plantParamList = new ArrayList<>();

		plantParamList.add("0");
		// 查询工厂列表
		List<List<String>> list = new ArrayList<>();
		list.add(plantParamList);
		headChildrenList = plmPlantStructureDao.listTree(list);

		List<String> firstParentIdlist = new ArrayList<String>();

		List<String> secondParentIdlist = new ArrayList<String>();

		List<String> threeIdlist = new ArrayList<String>();

		// 组装成树
		for (Map<String, Object> m : headChildrenList) {

			firstParentIdlist.add(m.get("id") + "");

		}

		// 查询一级分类
		list = new ArrayList<>();
		list.add(firstParentIdlist);
		List<Map<String, Object>> firstList = plmPlantStructureDao.listTree(list);

		for (Map<String, Object> m : firstList) {

			secondParentIdlist.add(m.get("id") + "");

		}

		// 查询二级分类
		list = new ArrayList<>();
		list.add(secondParentIdlist);
		List<Map<String, Object>> secondList = plmPlantStructureDao.listTree(list);

		for (Map<String, Object> m : secondList) {

			threeIdlist.add(m.get("id") + "");

		}

		// 查询三级分类
		list = new ArrayList<>();
		list.add(threeIdlist);
		List<Map<String, Object>> threeList = plmPlantStructureDao.listTree(list);

		// 组装三级树叶

		for (Map<String, Object> s : secondList) {
			threeChildrenList = new ArrayList<>();
			String id = s.get("id") + "";
			for (Map<String, Object> t : threeList) {
				String parentId = t.get("parentId") + "";
				if (Objects.equals(parentId, id)) {
					t.put("disabled", true);
					t.put("level", 3);
					threeChildrenList.add(t);

				}

			}
			s.put("children", threeChildrenList);
		}
		// 组装二级树

		for (Map<String, Object> f : firstList) {
			secondChildrenList = new ArrayList<>();
			String id = f.get("id") + "";
			for (Map<String, Object> s : secondList) {
				String parentId = s.get("parentId") + "";
				if (Objects.equals(parentId, id)) {
					s.put("level", 2);
					if (Objects.equals(plmPlantStructure.getPlantItemId(), s.get("id"))) {
						s.put("spread", true);
						f.put("spread", true);
					}
					secondChildrenList.add(s);

				}

			}
			f.put("children", secondChildrenList);
		}

		// 头部是仓库 组装一级树
		for (Map<String, Object> h : headChildrenList) {
			firstChildrenList = new ArrayList<>();
			String id = h.get("id") + "";
			for (Map<String, Object> f : firstList) {
				String parentId = f.get("parentId") + "";
				if (Objects.equals(parentId, id)) {
					f.put("level", 1);
					if (Objects.equals(plmPlantStructure.getPlantItemId(), f.get("id"))) {
						f.put("spread", true);
						h.put("spread", true);
					}
					firstChildrenList.add(f);

				}

			}
			h.put("children", firstChildrenList);
		}

		return headChildrenList;
	}

	/**
	 * .
	 *
	 * 重载方法
	 * 
	 * 功能描述:查询节点下的信息列表
	 * 
	 * @param plmPlantStructure
	 * @return
	 * @author 刘松山
	 *
	 *         时间: 2020-08-10 09:02
	 */
	@Override
	public List<PlmPlantStructure> listBypage(PlmPlantStructure plmPlantStructure) {

		List<String> dicts = new ArrayList<String>();
		 
		 

		List<PlmPlantStructure> list = plmPlantStructureDao.listBypage(plmPlantStructure);
	
		return list;
	}

	@Override
	public List<Map<String, Object>> listFactoryByType(String modelType) {
		// TODO Auto-generated method stub
		return plmPlantStructureDao.listFactoryByType(modelType);
	}

	public List<Map<String,Object>> selectFactoryById(Map<String,Object> param){
		return plmPlantStructureDao.selectFactoryById(param);
	}

	/**
	 * @Description TD:查询t_plm_plant_structure 数据
	 * @param model
	 * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 * @Author Lty
	 * @Date 2020/12/10 17:21
	 **/
	@Override
	public List<Map<String, Object>> listFactoryModel(PlantModelDto model) {

		return plmPlantStructureDao.listFactoryModelByDto(model);
	}

}

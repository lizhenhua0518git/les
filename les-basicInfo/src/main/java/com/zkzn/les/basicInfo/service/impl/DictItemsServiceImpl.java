package com.zkzn.les.basicInfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.dao.DictItemsDao;
import com.zkzn.les.basicInfo.service.DictItemsService;
import com.zkzn.les.basicInfo.util.PageUtil;
import com.zkzn.les.basicInfo.pojo.DictItems;

import javax.annotation.Resource;

@Service
public class DictItemsServiceImpl implements DictItemsService {

	@Resource
	private DictItemsDao dictItemsDao;
	@Autowired
	private RedisTemplate<String ,String> redisTemplate;
	@Override
	public int saveDictItems(DictItems dictItems) {
		// TODO Auto-generated method stub
		int result = dictItemsDao.saveDictItems(dictItems);
		redisTemplate.delete("dictItems");
		return result;
	}

	@Override
	public int deleteDictItems(List<String> id) {
		// TODO Auto-generated method stub
		int result = dictItemsDao.deleteDictItems(id);
		redisTemplate.delete("dictItems");
		return result;
	}
	@Override
	public int updateDictItems(DictItems dictItems) {
		// TODO Auto-generated method stub
		int result = dictItemsDao.updateDictItems(dictItems);
		redisTemplate.delete("dictItems");
		return result;
	}

	@Override
	public PageInfo<DictItems> listDictItemsList(DictItems dictItems) {
		// TODO Auto-generated method stub
		PageUtil.setPageParam(dictItems);
		List<DictItems> dictItemsList = dictItemsDao.listDictItemsList(dictItems);
		PageInfo<DictItems> dictItemsPage = new PageInfo<DictItems>(dictItemsList);
		 
		return dictItemsPage;
	}

	@Override
	public List<DictItems> listDictItemsByType(List<String> type) {
		// TODO Auto-generated method stub
		return dictItemsDao.listDictItemsByType(type);
	}

}

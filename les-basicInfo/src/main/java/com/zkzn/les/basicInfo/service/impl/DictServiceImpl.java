package com.zkzn.les.basicInfo.service.impl;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.dao.DictDao;
import com.zkzn.les.basicInfo.pojo.Dict;
import com.zkzn.les.basicInfo.pojo.DictItems;
import com.zkzn.les.basicInfo.service.DictService;
import com.zkzn.les.basicInfo.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**.
 *
 *
 * 功能描述：数据字典类型service实现类
 * @author wangzhou
 * 时间：2018年6月27日
 */
@Service
public class DictServiceImpl implements DictService{

	@Autowired
	private DictDao dictDao;
	@Autowired
	private RedisTemplate<String ,String> redisTemplate;

	@Override
	public int saveDict(Dict dict) {
		// TODO Auto-generated method stub
		int result = dictDao.saveDict(dict);
		redisTemplate.delete("dictItems");
		return result;
	}
	@Override
	public int deleteDict(List<String> id) {
		// TODO Auto-generated method stub
		int result = dictDao.deleteDict(id);
		redisTemplate.delete("dictItems");
		return result;
	}
	@Override
	public int updateDict(Dict dict) {
		// TODO Auto-generated method stub
		int result = dictDao.updateDict(dict);
		redisTemplate.delete("dictItems");
		return result;
	}

	@Override
	public PageInfo<Dict> listDictList(Dict dict) {

		PageUtil.setPageParam(dict);
		List<Dict> dictList = dictDao.listDictList(dict);
		PageInfo<Dict> dictPageInfo = new PageInfo<Dict>(dictList);

		return dictPageInfo;
	}

	@Override
	public Dict getDict(Dict dict) {
		// TODO Auto-generated method stub
		return dictDao.getDict(dict);
	}

	@Override
	public List<DictItems> initDictItemList(String dictType) {
		List<DictItems> list = dictDao.initDictItemList(dictType);
		return list;
	}


}

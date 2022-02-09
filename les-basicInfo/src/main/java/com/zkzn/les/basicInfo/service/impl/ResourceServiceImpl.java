package com.zkzn.les.basicInfo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.dao.ResourceDao;
import com.zkzn.les.basicInfo.pojo.Resource;
import com.zkzn.les.basicInfo.service.ResourceService;
import com.zkzn.les.basicInfo.util.PageUtil;
import com.zkzn.les.basicInfo.util.RedisUtil;
import com.zkzn.les.basicInfo.pojo.DictItems;

@Service
public class ResourceServiceImpl implements ResourceService{

	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private RedisTemplate<String ,String> redisTemplate;
	
	@Value("${redis.token.expire}")
	private Long expire;
	
	@Override
	public int saveResource(Resource resource) {
		// TODO Auto-generated method stub
		int result = resourceDao.saveResource(resource);
		delCache();
		return result;
	}

	@Override
	public int removeResource(List<String> id) {
		// TODO Auto-generated method stub
		int result = resourceDao.removeResource(id);
		delCache();
		return result;
	}

	@Override
	public int updateResource(Resource resource) {
		// TODO Auto-generated method stub
		int result = resourceDao.updateResource(resource);
		delCache();
		return result;
	}

	@Override
	public PageInfo<Resource> listresourcePage(Resource resource) {
		// TODO Auto-generated method stub
		PageUtil.setPageParam(resource);
		List<Resource> list = resourceDao.listresource(resource);
		PageInfo<Resource> pageInfo = new PageInfo<Resource>(list);
		return pageInfo;
	}
	
	@Override
	public List<Resource> listresource(Resource resource) {
		// TODO Auto-generated method stub
		List<Resource> list = resourceDao.listresource(resource);
		return list;
	}

	@Override
	public Resource getById(String id) {
		// TODO Auto-generated method stub
		return resourceDao.getById(id);
	}

	@Override
	public List<Resource> listByParentId(String parentId) {
		// TODO Auto-generated method stub
		return resourceDao.listByParentId(parentId);
	}

	@Override
	public Resource getByResourceName(String resourceName, String id) {
		// TODO Auto-generated method stub
		return resourceDao.getByResourceName(resourceName, id);
	}

	@Override
	@Cacheable(value="les-uas",key="#userId+#type+#status+#attach")
	public List<Resource> listResourceByUserId(String userId, String type, String status, String attach) {
		// TODO Auto-generated method stub
		Object userResourceObj = RedisUtil.getCache(redisTemplate,"userResource");
		List<String> userResourceList = null;
		if(userResourceObj!=null){
			userResourceList = (List<String>) userResourceObj;
			if(!userResourceList.contains(userId+type+status+attach)){
				userResourceList.add(userId+type+status+attach);
				RedisUtil.putCache(redisTemplate,"userResource",userResourceList,expire);
			}
		}else{
			userResourceList = new ArrayList<String>();
			userResourceList.add(userId+type+status+attach);
			RedisUtil.putCache(redisTemplate,"userResource",userResourceList,expire);
		}
		return resourceDao.listResourceByUserId(userId, type,status,attach);
	}

	@Override
	public List<Resource> listResourceByUrl(String url) {
		// TODO Auto-generated method stub
		return resourceDao.listResourceByUrl(url);
	}

	@Override
	public List<Resource> listResourceByType(String type, String status, String attach) {
		// TODO Auto-generated method stub
		return resourceDao.listResourceByType(type, status, attach);
	}

	@Override
	public List<DictItems> getEditButtonById(String id) {
		List<DictItems> returnList = new ArrayList<>();
		List<String> resourceButton = resourceDao.getByResourceParentId(id);
		Map<String,Object> map = new HashMap<>();
		for (int i = 0; i < resourceButton.size(); i++) {
			map.put(resourceButton.get(i),"");
		}
		List<DictItems> dictItemsList = resourceDao.getDictButtonData();
		for (int i = 0; i < dictItemsList.size(); i++) {
			DictItems dictItems = dictItemsList.get(i);
			if (map.containsKey(dictItems.getItemValue())){
				dictItems.setSortNo(1);//按钮开启
			}
			returnList.add(dictItems);
		}
		return returnList;
	}

	@Override
	public void addButton(List<Resource> resources, String userId, String userName) {
		List<Resource> addList = new ArrayList<>();
		String resourceParentId = resources.get(0).getResourceParentId();
		List<String> resourceButton = resourceDao.getByResourceParentId(resourceParentId);
		Map<String,Object> map = new HashMap<>();
		for (int i = 0; i < resourceButton.size(); i++) {
			map.put(resourceButton.get(i),"");
		}
		for (int i = 0; i < resources.size(); i++) {
			Resource resource = resources.get(i);
			if (!map.containsKey(resource.getResourceCode())){
				resource.setCreaterId(userId);
				resource.setCreaterName(userName);
				resource.setCreateTime(new Date());
				addList.add(resource);
			}
		}
		if (addList.size()>0){
			resourceDao.addResourceList(addList);
		}
	}

	/**.
	 * 
	 * 功能描述：清除用户资源信息缓存.
	 * 作者：wangzhou
	 * 时间：2018年7月26日
	 */
	public void delCache(){
		Object userResourceObj = RedisUtil.getCache(redisTemplate,"userResource");
		List<String> userResourceList = null;
		if(userResourceObj!=null){
			userResourceList = (List<String>) userResourceObj;
			redisTemplate.delete(userResourceList);
			redisTemplate.delete("userResource");
		}
	}
}

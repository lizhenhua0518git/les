package com.zkzn.les.uas.service.impl;

import com.zkzn.les.uas.dao.ResourceDao;
import com.zkzn.les.uas.pojo.Resource;
import com.zkzn.les.uas.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService{

	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private RedisTemplate<String ,String> redisTemplate;

	@Value("${redis.token.expire}")
	private Long expire;

	@Override
	public Resource getById(String id) {
		// TODO Auto-generated method stub
		return resourceDao.getById(id);
	}

	@Override
	public List<Resource> listResourceByType(String type, String status, String attach) {
		// TODO Auto-generated method stub
		return resourceDao.listResourceByType(type, status, attach);
	}

}

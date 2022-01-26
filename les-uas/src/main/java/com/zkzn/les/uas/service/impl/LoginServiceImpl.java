package com.zkzn.les.uas.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkzn.les.uas.dao.LoginDao;
import com.zkzn.les.uas.pojo.Login;
import com.zkzn.les.uas.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginDao loginDao;
	
	private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Override
	public int insertLogin(Login login) {
		// TODO Auto-generated method stub
		return loginDao.insertLogin(login);
	}

	@Override
	public int recordLoginInfo(String userId, int type){
		Login login = new Login();
		login.setUserId(userId);
		login.setType(type);
		login.setLogTime(new Date());
		this.insertLogin(login);
		if(type==1) {
			logger.info("登录信息写入数据库成功");
		}else {
			logger.info("退出信息写入数据库成功");
		}
		return 0;
	}
}

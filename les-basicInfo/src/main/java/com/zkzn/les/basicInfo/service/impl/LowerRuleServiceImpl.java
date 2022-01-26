package com.zkzn.les.basicInfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.dao.LowerRuleDao;
import com.zkzn.les.basicInfo.pojo.LowerRule;
import com.zkzn.les.basicInfo.service.LowerRuleService;
import com.zkzn.les.basicInfo.util.PageUtil;


@Service
public class LowerRuleServiceImpl implements LowerRuleService {

	@Autowired
	private LowerRuleDao  lowerRuleDao;
	
	@Override
	public List<LowerRule> listLowerRule(LowerRule lowerRule) {
		// TODO Auto-generated method stub
		return lowerRuleDao.listLowerRule(lowerRule);
	}

	@Override
	public int saveLowerRule(LowerRule lowerRule) {
		// TODO Auto-generated method stub
		return lowerRuleDao.saveLowerRule(lowerRule);
	}

	@Override
	public int updateLowerRule(LowerRule lowerRule) {
		// TODO Auto-generated method stub
		return lowerRuleDao.updateLowerRule(lowerRule);
	}

	@Override
	public int removeLowerRule(List<String> id) {
		// TODO Auto-generated method stub
		return lowerRuleDao.removeLowerRule(id);
	}

	@Override
	public PageInfo<LowerRule> listLowerRulePage(LowerRule lowerRule) {
		// TODO Auto-generated method stub
		PageUtil.setPageParam(lowerRule);
		List<LowerRule> list =  listLowerRule(lowerRule);
		PageInfo<LowerRule> pageInfo = new PageInfo<LowerRule>(list);
		return pageInfo;
	}

}

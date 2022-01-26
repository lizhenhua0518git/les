package com.zkzn.les.basicInfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.dao.JobSettingDao;
import com.zkzn.les.basicInfo.pojo.JobSetting;
import com.zkzn.les.basicInfo.service.JobSettingService;
import com.zkzn.les.basicInfo.util.PageUtil;


/**.
 * 
 *
 * 功能描述：任务调度service实现类
 * @author wangzhou
 * 时间：2018年7月9日
 */
@Service
public class JobSettingServiceImpl implements JobSettingService {

	@Autowired
	private JobSettingDao jobSettingDao;
	
	@Override
	public int saveJobSetting(JobSetting jobSetting) {
		// TODO Auto-generated method stub
		return jobSettingDao.saveJobSetting(jobSetting);
	}

	@Override
	public int deleteJobSetting(List<String> id) {
		// TODO Auto-generated method stub
		return jobSettingDao.deleteJobSetting(id);
	}

	@Override
	public int updateJobSetting(JobSetting jobSetting) {
		// TODO Auto-generated method stub
		return jobSettingDao.updateJobSetting(jobSetting);
	}

	@Override
	public PageInfo<JobSetting> listJobSetting(JobSetting jobSettiing) {
		// TODO Auto-generated method stub
		PageUtil.setPageParam(jobSettiing);
		if (jobSettiing.getType()!=null){
			jobSettiing.setType1(jobSettiing.getType().toString());
		}
		List<JobSetting> jobSettingList =  jobSettingDao.listJobSetting(jobSettiing);
		PageInfo<JobSetting> jobSettingPageInfo = new PageInfo<JobSetting>(jobSettingList);
		 
		return jobSettingPageInfo;
	}

	@Override
	public void testMethod() {
		// TODO Auto-generated method stub
		System.out.println("=============testMethod===============");
		System.out.println("=============testMethod===============");
		System.out.println("=============testMethod===============");
		System.out.println("=============testMethod===============");
	}

	@Override
	public void testMethodA() {
		// TODO Auto-generated method stub
		System.out.println("=============testMethodA===============");
		System.out.println("=============testMethodA===============");
		System.out.println("=============testMethodA===============");
		System.out.println("=============testMethodA===============");
	}

	@Override
	public int updateStatusById(String id,int status) {
		// TODO Auto-generated method stub
		return jobSettingDao.updateStatusById(id, status);
	}

	@Override
	public List<JobSetting> listJobSettingList(JobSetting jobSettiing) {
		// TODO Auto-generated method stub
		return jobSettingDao.listJobSetting(jobSettiing);
	}

}

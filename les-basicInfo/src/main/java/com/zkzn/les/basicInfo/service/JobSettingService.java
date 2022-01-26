package com.zkzn.les.basicInfo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.basicInfo.pojo.JobSetting;


/**.
 * 
 *
 * 功能描述：任务调度service
 * @author wangzhou
 * 时间：2018年7月9日
 */
public interface JobSettingService {

	/**.
	 * 
	 * 功能描述：新增任务调度信息
	 * 作者：wangzhou
	 * 时间：2018年7月9日
	 * @param jobSetting
	 * @return
	 */
	int saveJobSetting(JobSetting jobSetting);
	/**.
	 * 
	 * 功能描述：批量删除任务调度信息
	 * 作者：wangzhou
	 * 时间：2018年7月9日
	 * @param id
	 * @return
	 */
	int deleteJobSetting(List<String> id);
	/**.
	 * 
	 * 功能描述：修改任务调度信息
	 * 作者：wangzhou
	 * 时间：2018年7月9日
	 * @param jobSetting
	 * @return
	 */
	int updateJobSetting(JobSetting jobSetting);
	/**.
	 * 
	 * 功能描述：查询任务调度信息（分页）
	 * 作者：wangzhou
	 * 时间：2018年7月9日
	 * @param jobSettiing
	 * @return
	 */
	PageInfo<JobSetting> listJobSetting(JobSetting jobSettiing);
	
	void testMethod();
	
	void testMethodA();
	
	/**.
	 * 
	 * 功能描述：修改任务调度状态
	 * 作者：wangzhou
	 * 时间：2018年7月12日
	 * @param id
	 * @return
	 */
	int updateStatusById(@Param("id")String id,@Param("status")int status);
	/**.
	 * 
	 * 功能描述：查询任务调度信息
	 * 作者：wangzhou
	 * 时间：2018年7月12日
	 * @param jobSettiing
	 * @return
	 */
	List<JobSetting> listJobSettingList(JobSetting jobSettiing);
}

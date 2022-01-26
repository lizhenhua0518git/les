package com.zkzn.les.basicInfo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zkzn.les.basicInfo.pojo.JobSetting;



/**.
 * 
 *
 * 功能描述：任务调度dao层
 * @author wangzhou
 * 时间：2018年7月9日
 */
@Mapper
public interface JobSettingDao {

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
	 * 功能描述：查询任务调度信息
	 * 作者：wangzhou
	 * 时间：2018年7月9日
	 * @param jobSettiing
	 * @return
	 */
	List<JobSetting> listJobSetting(JobSetting jobSettiing);
	/**.
	 * 
	 * 功能描述：修改任务调度状态
	 * 作者：wangzhou
	 * 时间：2018年7月12日
	 * @param id
	 * @return
	 */
	int updateStatusById(@Param("id")String id,@Param("status")int status);
}

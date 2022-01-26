package com.zkzn.les.uas.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zkzn.les.uas.pojo.Login;

/**.
 * 用户登录日志Dao层
 * @author wangzhou
 *
 */
@Mapper
public interface LoginDao {
	
	/**.
	 * 功能描述: 插入登录信息
	 * 作者:何闰平
	 * 时间:2018年6月4日 上午10:55:35
	 * @param login
	 * @return
	 */
	int insertLogin(Login login);
	
	/**.
	 * 功能描述: 删除登录信息
	 * 作者:何闰平
	 * 时间:2018年6月4日 上午10:55:51
	 * @param login
	 * @return
	 */
	int deleteLogin(Login login);
	
	/**.
	 * 功能描述: 查看登录信息
	 * 作者:余祥
	 * 时间:2018年7月2日
	 * @param login
	 * @return
	 */
	List<Login> queryLoginMessage(Login login);

}

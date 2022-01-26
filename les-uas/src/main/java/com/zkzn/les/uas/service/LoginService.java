package com.zkzn.les.uas.service;

import com.github.pagehelper.PageInfo;
import com.zkzn.les.uas.pojo.Login;

public interface LoginService {
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
	PageInfo<Login> queryLogin(Login login);
	/**.
	 * 保存用户登入、登出信息
	 * @param userId
	 * @param type
	 * @return
	 */
	int recordLoginInfo(String userId, int type);

}

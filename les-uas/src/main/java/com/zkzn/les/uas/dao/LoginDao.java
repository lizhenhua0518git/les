package com.zkzn.les.uas.dao;

import com.zkzn.les.uas.pojo.Login;
import org.apache.ibatis.annotations.Mapper;

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

}

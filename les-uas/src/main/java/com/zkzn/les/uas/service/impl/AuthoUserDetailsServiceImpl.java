package com.zkzn.les.uas.service.impl;

import com.zkzn.les.uas.dao.UserDao;
import com.zkzn.les.uas.pojo.SecurityUser;
import com.zkzn.les.uas.pojo.User;
import com.zkzn.les.uas.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class AuthoUserDetailsServiceImpl implements UserDetailsService{

	@Autowired(required=true)
	private UserDao userDao;


	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		if(userName==null || userName.length()==0){
			throw new UsernameNotFoundException("PHONE:"+userName+"用户不存在");
		}
		User user = new User();
		user.setPhone(userName);//因现在登录用的手机号，
		User findUser = userDao.getUser(user);
		if(findUser==null){
			throw new UsernameNotFoundException("PHONE:"+userName+"用户不存在");
		}
		Map<String,Object> userMap = BeanUtil.objectToMap(findUser);
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(findUser);
		SecurityUser userDetial = (SecurityUser) BeanUtil.mapToObject(userMap, SecurityUser.class);
		userDetial.setId(findUser.getUserId()+"");
		userDetial.setUserName(findUser.getUserName());
		userDetial.setUserPassword(findUser.getUserPassword());
		userDetial.setAuthorities(grantedAuths);
		return userDetial;
	}
	/**.
	 * 获取用户对应的角色
	 * @param user
	 * @return
	 */
	private Set<GrantedAuthority> obtionGrantedAuthorities(User user) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		//authSet.add(new SimpleGrantedAuthority("admin"));
		return authSet;
	}

}

package com.zkzn.les.uas.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zkzn.les.uas.dao.UserDao;
import com.zkzn.les.uas.pojo.SecurityUser;
import com.zkzn.les.uas.pojo.User;
import com.zkzn.les.uas.util.BeanUtil;

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
		/*org.springframework.security.core.userdetails.User userDetial
		= new org.springframework.security.core.userdetails.User(findUser.getPhone(),
				findUser.getUserPassword(), grantedAuths);*/

		return userDetial;
	}
	/**.
	 * 获取用户对应的角色
	 * @param user
	 * @return
	 */
	private Set<GrantedAuthority> obtionGrantedAuthorities(User user) {
//        List<Role> roles=roleDAO.findRolesByLoginName(user.getLoginName());
//            Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
//            for (Role res : roles) {
//                // 用户可以访问的资源名称（或者说用户所拥有的权限） 注意：必须"ROLE_"开头
//                authSet.add(new SimpleGrantedAuthority(res.getRoleName()));
//            }
//            return authSet;
//        }
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		//authSet.add(new SimpleGrantedAuthority("admin"));
		return authSet;
	}

}

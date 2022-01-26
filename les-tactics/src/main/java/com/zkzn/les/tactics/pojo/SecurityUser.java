package com.zkzn.les.tactics.pojo;

import com.zkzn.les.common.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class SecurityUser extends User implements UserDetails{
	/**
	 * 创建人: wangzhou
	 * 时间:20202020年3月29日下午8:22:05
	 * 功能描述:
	 */
	private static final long serialVersionUID = -4915326574884411505L;

	private Collection<GrantedAuthority> authorities;

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		return super.getUserPassword();
	}

	@Override
	public String getUsername() {
		return super.getUserName();
	}
}

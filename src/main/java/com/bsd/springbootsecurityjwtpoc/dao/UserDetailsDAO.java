package com.bsd.springbootsecurityjwtpoc.dao;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bsd.springbootsecurityjwtpoc.domain.Role;
import com.bsd.springbootsecurityjwtpoc.domain.User;

public class UserDetailsDAO implements UserDetails {

	private String userName;

	private String password;

	private boolean isEnabled;

	private boolean isAccountLocked;

	private boolean isExpired;

	private boolean areCredentialsExpired;

	private List<GrantedAuthority> authorities;

	public UserDetailsDAO(User user) {
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.isEnabled = user.isEnabled();
		this.isAccountLocked = user.isAccountLocked();
		this.isExpired = user.isExpired();
		this.areCredentialsExpired = user.isCredentialsExpired();
		this.authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRole()))
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.isExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.isAccountLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.areCredentialsExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.isEnabled;
	}

}

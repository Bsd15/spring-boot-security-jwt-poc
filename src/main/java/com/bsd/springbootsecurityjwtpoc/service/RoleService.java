package com.bsd.springbootsecurityjwtpoc.service;

import java.util.List;

import com.bsd.springbootsecurityjwtpoc.domain.Role;
import com.bsd.springbootsecurityjwtpoc.exception.RoleAlreadyExistsException;
import com.bsd.springbootsecurityjwtpoc.exception.RoleNotFoundException;

public interface RoleService {
//	Save Role
	public Role save(Role role) throws RoleAlreadyExistsException;
//	Get Role by role name
	public Role getRoleByName(String role) throws RoleNotFoundException;
//	Get all roles
	public List<Role> getAllRoles() throws Exception;}
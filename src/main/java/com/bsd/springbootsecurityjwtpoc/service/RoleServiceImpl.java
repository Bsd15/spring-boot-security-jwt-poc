package com.bsd.springbootsecurityjwtpoc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsd.springbootsecurityjwtpoc.domain.Role;
import com.bsd.springbootsecurityjwtpoc.exception.RoleAlreadyExistsException;
import com.bsd.springbootsecurityjwtpoc.exception.RoleNotFoundException;
import com.bsd.springbootsecurityjwtpoc.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepository;

	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	/**
	 * Save role to database after checking if the role name exists or not.
	 */
	@Override
	public Role save(Role role) throws RoleAlreadyExistsException {
		if (roleRepository.existsByRole(role.getRole())) {
			throw new RoleAlreadyExistsException();
		}
		roleRepository.save(role);
		return role;
	}

	@Override
	public Role getRoleByName(String role) throws RoleNotFoundException {
		Optional<Role> fetchedRole = roleRepository.findByRole(role);
		fetchedRole.orElseThrow(() -> new RoleNotFoundException());
		return fetchedRole.get();
	}

	@Override
	public List<Role> getAllRoles() throws Exception {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

}

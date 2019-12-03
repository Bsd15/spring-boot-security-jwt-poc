package com.bsd.springbootsecurityjwtpoc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bsd.springbootsecurityjwtpoc.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	boolean existsByRole(String role);
	Optional<Role> findByRole(String role);
}

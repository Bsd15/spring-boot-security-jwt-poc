package com.bsd.springbootsecurityjwtpoc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bsd.springbootsecurityjwtpoc.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUserName(String userName);
}

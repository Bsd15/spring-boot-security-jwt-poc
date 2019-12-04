package com.bsd.springbootsecurityjwtpoc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bsd.springbootsecurityjwtpoc.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query(value = "SELECT u FROM User u JOIN FETCH u.roles where u.userName = :userName")
	Optional<User> findByUserName(@Param("userName") String userName);
//	Optional<User> findByUserName(String userName);
}

package com.bsd.springbootsecurityjwtpoc.seeddata;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bsd.springbootsecurityjwtpoc.domain.Role;
import com.bsd.springbootsecurityjwtpoc.domain.User;
import com.bsd.springbootsecurityjwtpoc.exception.UserAlreadyExistsException;
import com.bsd.springbootsecurityjwtpoc.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class POCSeedDataCommandLineRunner implements CommandLineRunner {
	
	private UserService userService;
	
	@Autowired
	public POCSeedDataCommandLineRunner(UserService userService) {
		this.userService = userService;
	}


	@Override
	public void run(String... args) throws Exception {
//		Create a dummy user object and save it to the user Database.
		Role userRole = new Role();
		userRole.setRole("ROLE_USER");
		Set<Role> roles = new HashSet<Role>();
		roles.add(userRole);
		User dummyUser = new User(1, "test", "test", "first name", "last name", "e@mail.com",roles);
		log.debug(dummyUser.toString());
		try {
			userService.saveUser(dummyUser);
		} catch (UserAlreadyExistsException e) {
			log.error( e.getClass() + " CommandLineRunner Error.");
		}

	}

}

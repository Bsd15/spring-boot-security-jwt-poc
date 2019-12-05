package com.bsd.springbootsecurityjwtpoc.seeddata;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.bsd.springbootsecurityjwtpoc.domain.Role;
import com.bsd.springbootsecurityjwtpoc.domain.User;
import com.bsd.springbootsecurityjwtpoc.exception.UserAlreadyExistsException;
import com.bsd.springbootsecurityjwtpoc.service.RoleService;
import com.bsd.springbootsecurityjwtpoc.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class POCSeedDataCommandLineRunner implements CommandLineRunner {

	private UserService userService;
	private RoleService roleService;

	@Autowired
	public POCSeedDataCommandLineRunner(UserService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}

	@Override
	public void run(String... args) throws Exception {
//		User dummyUser = userService.getUserByUserName("test1");
//		log.debug(dummyUser.toString());
//		Create a dummy user object and save it to the user Database.
//		Role userRole = new Role();
//		userRole.setRole("ROLE_USER");
//		log.debug(userRole.toString());
//		Role adminRole = new Role();
//		adminRole.setRole("ROLE_ADMIN");
//		Set<Role> roles = new HashSet<Role>();
////		roles.add(userRole);
//		roles.add(adminRole);
//		User dummyUser = new User();	
//		dummyUser.setUserName("adminTest");
//		dummyUser.setPassword("test");
//		dummyUser.setEmail("e@mail.com");
//		dummyUser.setFirstName("First Name");
//		dummyUser.setLastName("Last Name");
//		dummyUser.setRoles(roles);
//		dummyUser.setAccountLocked(false);
//		dummyUser.setEnabled(true);
//		dummyUser.setExpired(false);
//		dummyUser.setCredentialsExpired(false);
//		log.debug(dummyUser.toString());
//		try {
//			userService.saveUser(dummyUser);
//		} catch (UserAlreadyExistsException e) {
//			log.error(e.getClass() + " CommandLineRunner Error.");
//		}
//		try {
//			User fetchedDummyUser = userService.getUserByUserName("test");
//			Set<Role> fetchedDummyUserRoles = fetchedDummyUser.getRoles();
//			log.debug(fetchedDummyUserRoles.toString());
//		} catch (UsernameNotFoundException e) {
//			log.error(e.getClass() + " CommandLineRunner Error on line 46 find the user by username");
//		}

	}

}

package com.bsd.springbootsecurityjwtpoc.seeddata;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public POCSeedDataCommandLineRunner(UserService userService, RoleService roleService,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userService = userService;
		this.roleService = roleService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public void run(String... args) throws Exception {
//		Save Admin role to database
		roleService.save(new Role("ROLE_ADMIN"));
//		Save User role to database
		roleService.save(new Role("ROLE_USER"));
//		Fetch admin and user roles from database
		Role adminRole = roleService.getRoleByName("ROLE_ADMIN");
		Role userRole = roleService.getRoleByName("ROLE_USER");
		log.debug("ADMIN ROLE: " + adminRole.toString());
		log.debug("USER ROLE: " + userRole.toString());
//		Generate bcrypt hash for string "test" as it will be used as password
//		for all the users below
		final String password = bCryptPasswordEncoder.encode("test");
//		Create a user with "USER" role.
		User user1 = new User("test", password, "First Name", "Last Name", "e@mail.com", true, false, false, false);
		user1.getRoles().add(userRole);
		log.debug(userRole.toString());
//		Save user1
		userService.saveUser(user1);
//		Create adminUser
		User adminUser1 = new User("adminTest", password, "First Name", "Last Name", "e@mail.com", true, false, false, false, userRole, adminRole);
		log.debug(user1.toString());
//		Save adminUser1
		userService.saveUser(adminUser1);
		log.debug(adminUser1.toString());
		User user2 = new User("test2", password, "First Name", "Last name", "e@mail.com");
		user2.addRole(userRole);
//		Log the user object received after saving user2.
		log.debug(
				userService.saveUser(user2).toString()
				);
//		User with only admin role
		User onlyAdminRoleUser = new User("adminTest2", password, "First Name", "Last Name", "e@mail.com");
		onlyAdminRoleUser.addRole(adminRole);
		userService.saveUser(onlyAdminRoleUser);
	}
}

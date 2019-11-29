package com.bsd.springbootsecurityjwtpoc.service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bsd.springbootsecurityjwtpoc.domain.User;
import com.bsd.springbootsecurityjwtpoc.exception.UserAlreadyExistsException;

public interface UserService {
//	Saves user to database
	public User saveUser(User user) throws UserAlreadyExistsException;
//	Find user by user name
	public User getUserByUserName(String userName) throws UsernameNotFoundException;
//	Find user by ID
	public User getUserById(int id) throws UsernameNotFoundException;
//	Fetch all users
	public List<User> getAllUsers() throws Exception; 
}

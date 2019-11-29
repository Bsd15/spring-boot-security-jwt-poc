package com.bsd.springbootsecurityjwtpoc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bsd.springbootsecurityjwtpoc.domain.User;
import com.bsd.springbootsecurityjwtpoc.exception.UserAlreadyExistsException;
import com.bsd.springbootsecurityjwtpoc.repository.UserRepository;

@Service
public class UserServiceImple implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImple(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User saveUser(User user) throws UserAlreadyExistsException {
//		Check if a user already exists in the database
		if (userRepository.existsById(user.getId())) {
			throw new UserAlreadyExistsException();
		}
		userRepository.save(user);
		return user;
	}

	@Override
	public User getUserByUserName(String userName) throws UsernameNotFoundException {
		Optional<User> userOptional = userRepository.findByUserName(userName);
		userOptional.orElseThrow(() -> new UsernameNotFoundException("No user found with the given username"));
		return userOptional.get();
	}

	@Override
	public User getUserById(int id) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsers() throws Exception {
			return userRepository.findAll();
	}

}

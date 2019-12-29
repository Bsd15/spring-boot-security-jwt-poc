/**
 * 
 */
package com.bsd.springbootsecurityjwtpoc.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import com.bsd.springbootsecurityjwtpoc.domain.Role;
import com.bsd.springbootsecurityjwtpoc.domain.User;
import com.bsd.springbootsecurityjwtpoc.exception.UserAlreadyExistsException;
import com.bsd.springbootsecurityjwtpoc.repository.UserRepository;

/**
 * @author bsd
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {

	@Value("${role.user}")
	private String userRoleString;

	@Value("${role.admin}")
	private String adminRoleString;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userService;

	/**
	 * user - User with all the required data emptyUser - empty user object user2 -
	 * User with only few required fields filled.
	 */
	private User user, emptyUser, user2;
	private Role userRole, adminRole;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		userRole = new Role(userRoleString);
		adminRole = new Role(adminRoleString);
		user = new User("test", "test", "First Name", "Last Name", "e@mail.com");
		user.addRole(userRole);
		emptyUser = new User();
//		user2 = new User();
		System.out.println(userRoleString);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		user = null;
		emptyUser = null;
	}

	/**
	 * Test - UserServiceImpl saveUser()
	 * Test to check if the method saves a user or not.
	 */
	@Test
	void givenUserToSaveShouldReturnUser() {
		when(userRepository.save(user)).thenReturn(user);
		try {
			assertEquals("UserServiceImpl did not return the same user object!", user, userService.saveUser(user));
		} catch (UserAlreadyExistsException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test - UserServiceImpl saveUser()
	 * Test if the method throws UserAlreadyExistsException (if user is already
	 * present)
	 */
	@Test()
	void givenExistingUserToSaveShouldThrowException() {
		when(userRepository.existsById(user.getId())).thenReturn(true);
//		when(userRepository.save(user)).thenReturn(user);
		assertThrows(UserAlreadyExistsException.class, () -> {
			userService.saveUser(user);
		});
	}
	
	/**
	 * Test - UserServiceImpl getUserByUserName()
	 * Test if the method return a user object based on a given string.
	 */
	@Test
	void givenUsernameShouldReturnUser() {
		User expectedUser = new User("test", "test", "First Name", "Last Name", "e@mail.com");
		expectedUser.addRole(userRole);
		Optional<User> userOptional = Optional.of(expectedUser);
		when(userRepository.findByUserName(Mockito.anyString())).thenReturn(userOptional);
		assertEquals("givenUsernameShouldReturnUser(), user object not equal.", user, userService.getUserByUserName("test"));
	}
	
	/**
	 * Test - UserServiceImpl getUserByUserName()
	 * Test if method throws UserNameNotFoundException
	 */
	@Test
	void givenUserNameShouldThrowException() {
		when(userRepository.findByUserName(Mockito.anyString())).thenThrow(UsernameNotFoundException.class);
		assertThrows(UsernameNotFoundException.class, () -> {
			userService.getUserByUserName("test");
		});
	}

}

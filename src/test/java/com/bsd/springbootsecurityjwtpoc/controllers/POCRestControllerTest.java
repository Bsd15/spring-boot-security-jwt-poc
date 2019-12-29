package com.bsd.springbootsecurityjwtpoc.controllers;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bsd.springbootsecurityjwtpoc.repository.UserRepository;
import com.bsd.springbootsecurityjwtpoc.service.UserDetailServiceImpl;
import com.bsd.springbootsecurityjwtpoc.service.UserService;
import com.bsd.springbootsecurityjwtpoc.service.UserServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class POCRestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
//	@MockBean
//	private UserServiceImpl userService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@WithMockUser(value = "test")
	void givenGetRequestShouldReturnOk() {
		try {
			mockMvc
				.perform(get("/api/v1/").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@WithAnonymousUser
	void givenAnonymusUserShouldReturnUnauthorized() {
		try {
			mockMvc
				.perform(get("/api/v1/home").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized())
				.andDo(print());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@WithMockUser("test")
	void givenValidUserShouldReturnOk() {
		try {
			mockMvc
				.perform(get("/api/v1/home").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@WithMockUser(username = "test", roles = {"ADMIN"})
	void givenUserWithAdminRoleOnlyShouldReturnUnauthorized() {
		try {
			mockMvc
				.perform(get("/api/v1/home").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden())
				.andDo(print());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

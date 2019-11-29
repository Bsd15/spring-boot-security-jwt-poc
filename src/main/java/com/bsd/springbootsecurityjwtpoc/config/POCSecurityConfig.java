package com.bsd.springbootsecurityjwtpoc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class POCSecurityConfig extends WebSecurityConfigurerAdapter {
	
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		UserBuilder userBuilder = User.withDefaultPasswordEncoder();
		auth.inMemoryAuthentication()
			.withUser(userBuilder.username("john").password("test").roles("USER"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/home")
				.hasRole("USER")
			.antMatchers("/")
				.permitAll()
			.and()
				.formLogin();
//					.loginPage("/login")
//					.permitAll()
//			.and()
//				.logout()
//					.permitAll();
	}
	
}
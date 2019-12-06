package com.bsd.springbootsecurityjwtpoc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class POCSecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;

	@Autowired
	public POCSecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService);
		auth.authenticationProvider(getAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/home").hasRole("USER").antMatchers("/authenticate").permitAll().and().formLogin();
		http
			.authorizeRequests()
			.antMatchers("/api/v1/", "/api/v1/authenticate")
				.permitAll()
			.antMatchers("/api/v1/**")
				.hasRole("USER")
			.and()
			.csrf()
				.disable();
	}
	
	/**
	 * Creates a bean for BCryptPasswordEncoder
	 * @return BCryptPasswordEncoder
	 */
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * Creates a bean for DaoAuthenticationProvider with UserDetailsService
	 * and PasswordEncoder(BCryptPasswordEncoder) set.
	 * @return DaoAuthenticationProvider
	 */
	@Bean
	public DaoAuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(getPasswordEncoder());
		return authenticationProvider;
	}
	
	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception {
		return super.authenticationManager();
	}

}

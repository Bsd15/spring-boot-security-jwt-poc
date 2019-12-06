package com.bsd.springbootsecurityjwtpoc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bsd.springbootsecurityjwtpoc.dto.AuthenticationRequestDTO;
import com.bsd.springbootsecurityjwtpoc.dto.AuthenticationResponseDTO;
import com.bsd.springbootsecurityjwtpoc.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/")
@Slf4j
public class POCRestController {
	
	private UserDetailsService userDetailsService;
	private AuthenticationManager authenticationManager;
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	public POCRestController(UserDetailsService userDetailsService, AuthenticationManager authenticationManager,
			JwtUtil jwtTokenUtil) {
		this.userDetailsService = userDetailsService;
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
	}

	@RequestMapping("/")
	public ResponseEntity<String> landingPage() {
		return new ResponseEntity<String>("Spring Security POC", HttpStatus.OK);
	}
	
	@GetMapping("home")
	public ResponseEntity<String> home(){
		return ResponseEntity.ok("Home");
	}
	
	@PostMapping("authenticate")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO authenticationRequest) throws BadCredentialsException {
		log.debug(authenticationRequest.toString());
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword()));			
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect username or password");
		}
		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		String jwtToken = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponseDTO(jwtToken));
//		return ResponseEntity.ok(authenticationRequest);
	}
	
}

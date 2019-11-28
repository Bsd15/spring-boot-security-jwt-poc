package com.bsd.springbootsecurityjwtpoc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class POCRestController {
	
	@RequestMapping("/")
	public ResponseEntity<String> landingPage() {
		return new ResponseEntity<String>("Spring Security POC", HttpStatus.OK);
	}
	
	@RequestMapping("/home")
	public ResponseEntity<String> home(){
		return ResponseEntity.ok("Home Page");
	}
}

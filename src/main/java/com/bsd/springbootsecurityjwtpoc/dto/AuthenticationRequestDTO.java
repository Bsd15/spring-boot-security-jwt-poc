package com.bsd.springbootsecurityjwtpoc.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequestDTO implements Serializable{
	private String userName;
	private String password;
}

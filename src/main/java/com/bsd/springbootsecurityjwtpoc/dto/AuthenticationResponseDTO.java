package com.bsd.springbootsecurityjwtpoc.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponseDTO implements Serializable {
	private String jwtToken;
}

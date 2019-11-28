package com.bsd.springbootsecurityjwtpoc.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.constraints.UniqueElements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role {
	@Id
	private int id;
	@UniqueElements
	private String role;
}

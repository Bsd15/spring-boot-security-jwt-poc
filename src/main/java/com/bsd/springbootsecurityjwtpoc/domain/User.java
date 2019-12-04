package com.bsd.springbootsecurityjwtpoc.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//TODO Add Validation to the project
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotBlank
	@Column(unique = true)
	private String userName;

	@NotBlank
	private String password;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@NotBlank
	private String email;

	@NotNull
	private boolean isEnabled;

	@NotNull
	private boolean isAccountLocked;

	@NotNull
	private boolean isExpired;

	@NotNull
	private boolean isCredentialsExpired;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinTable(name = "users_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public User(int id, @NotBlank String userName, @NotBlank String password, @NotBlank String firstName,
			@NotBlank String lastName, @NotBlank String email, @NotBlank boolean isEnabled,
			@NotBlank boolean isAccountLocked, @NotBlank boolean isExpired, @NotBlank boolean isCredentialsExpired,
			Role... roles) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.isEnabled = isEnabled;
		this.isAccountLocked = isAccountLocked;
		this.isExpired = isExpired;
		this.isCredentialsExpired = isCredentialsExpired;
		this.roles = Stream.of(roles).collect(Collectors.toSet());
	}

//	Add role to roles set
	public void addRole(Role role) {
		if (roles == null) {
			roles = new HashSet<Role>();
		}
		roles.add(role);
	}

}

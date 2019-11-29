package com.bsd.springbootsecurityjwtpoc.domain;

import java.util.ArrayList;
import java.util.List;
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
import javax.persistence.UniqueConstraint;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(unique = true)
	private String role;
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinTable(name = "users_role",
	joinColumns = @JoinColumn(name = "role_id"),
	inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> users;
	
	/**
	 * Constructor that takes in N number of users as argument.
	 * @param id
	 * @param role
	 * @param users
	 */
	public Role(int id, @UniqueElements String role, User... users) {
		super();
		this.id = id;
		this.role = role;
		this.users = Stream.of(users).collect(Collectors.toList());
	}
	
	/**
	 * Add a single user to the list of users. Check if the users list is null and 
	 * initializes it if it's null.
	 * @param user User to be added to the users list.
	 */
	public void addUser(User user) {
		if (users == null) {
			users = new ArrayList<User>();
		}
		users.add(user);
	}
}

package com.stacksimplify.restservices.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="user_table")
public class User {
	
	@Id
	@GeneratedValue
	private Long Id;
	
	@NotEmpty(message="User Name is expected to have a value it is Unique Key")
	@Column(name="User_name", length=50, nullable=false,unique=true)
	private String username;
	
	@Size(min=2,max=8,message="First Name should be more than 1 character and less than 8 characters")
	@Column(name="First_name", length=50, nullable=false)
	private String firstname;
	
	@Column(name="Last_name", length=50, nullable=false)
	private String lastname;
	
	@Column(name="Email_address", length=50, nullable=false)
	private String email;
	
	@Column(name="Role", length=50, nullable=false)
	private String role;
	
	@Column(name="Ssn", length=50, nullable=false,unique=true)
	private String ssn;

	//Generate constructors from super class
	//Mandatory - No Args constructor
	public User() {
		//super();
		// TODO Auto-generated constructor stub
	}

	//Generate Getters and Setters
	//Mandatory - Fields
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	//Generate To string
	//Optional - It is used for Bean logging. Troubleshooting
	@Override
	public String toString() {
		return "User [Id=" + Id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}

	//Generate Fields using constructor
	//Optional
	public User(Long id, String username, String firstname, String lastname, String email, String role, String ssn) {
		//super();
		Id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	}
	
	
	
	
	
	
	
}

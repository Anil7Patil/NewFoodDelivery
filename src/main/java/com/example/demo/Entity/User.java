package com.example.demo.Entity;

import jakarta.persistence.Column;

public class User {
	
	private int id;
	@Column(name="Name")
	private String name;
	@Column(name="Email")
	private String email;
	@Column(name="password")
	private String password;
	@olumn(name="Address")
	private String address;
	
	
	

}

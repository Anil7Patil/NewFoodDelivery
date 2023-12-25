package com.example.demo.service;

import java.util.List;

import com.example.demo.Entity.User;

public interface UserService {

	
	List<User>getAllUsers();
	String userRegistration(User user);
	User getUserByEmail(String email);
	void deleteUserByEmail(String email);
	String updateUserByEmail(String email, User updatedUser);
	
}

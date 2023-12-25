package com.example.demo.seiviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserImpl implements UserService{
	
	@Autowired
    private UserRepo userRepository;
	
	 @Override
	    public String userRegistration(User user) {
	        // Assuming User entity has a constructor that takes necessary parameters
	        userRepository.save(user);
	        return "User registered successfully!";
	    }
	 
	 @Override
	    public List<User> getAllUsers() {
	        return userRepository.findAll();
	    }

	 @Override
	    public User getUserByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }
	 
	 @Override
	    @Transactional
	    public void deleteUserByEmail(String email) {
	        userRepository.deleteByEmail(email);
	    }
	 
	 @Override
	    @Transactional
	    public String updateUserByEmail(String email, User updatedUser) {
	        User existingUser = userRepository.findByEmail(email);

	        if (existingUser != null) {
	            // Update user details
	            existingUser.setName(updatedUser.getName());
	            existingUser.setAddress(updatedUser.getAddress());
	            existingUser.setPassword(updatedUser.getPassword());
	            // ... other fields

	            userRepository.save(existingUser);
	            return "User updated successfully!";
	        } else {
	            return "User not found with email: " + email;
	        }
	 }
}

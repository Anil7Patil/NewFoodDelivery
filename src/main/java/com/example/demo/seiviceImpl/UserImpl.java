package com.example.demo.seiviceImpl;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;
import com.example.demo.jwtSecurity.CustomUserDetails;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;

import ch.qos.logback.classic.Logger;
import jakarta.transaction.Transactional;

@Service
public class UserImpl implements UserService,UserDetailsService{
	
	@Autowired
    private UserRepo userRepository;
	
	 @Autowired
	    private PasswordEncoder passwordEncoder;
	 @Override
	    public String userRegistration(User user) {
		 
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		 user.setAddress(user.getAddress());
       //  existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
         user.setMobile(user.getMobile());
   //      existingUser.setAddress(user.getAddress());
         user.setEmail(user.getEmail());
	   
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
	            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
	            existingUser.setMobile(updatedUser.getMobile());
	      //      existingUser.setAddress(user.getAddress());
	            existingUser.setEmail( updatedUser.getEmail());

	            userRepository.save(existingUser);
	            return "User updated successfully!";
	        } else {
	            return "User not found with email: " + email;
	        }
	 }

	 private static final Logger logger = (Logger) LoggerFactory.getLogger(UserImpl.class);

	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

	        logger.debug("Entering in loadUserByUsername Method...");
	        User user = userRepository.findByEmail(email);
	        if(user == null){
	            logger.error("Username not found: " +email);
	            throw new UsernameNotFoundException("could not found user..!!");
	        }
	        logger.info("User Authenticated Successfully..!!!");
	        return new CustomUserDetails(user);
	    }
}

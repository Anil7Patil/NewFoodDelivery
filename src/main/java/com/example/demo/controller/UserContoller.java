package com.example.demo.controller;

import java.util.List;

import javax.naming.AuthenticationException;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.User;
import com.example.demo.dto.AuthRequestDTO;
import com.example.demo.dto.JwtResponseDTO;
import com.example.demo.jwtSecurity.JwtService;
import com.example.demo.repository.UserRepo;
import com.example.demo.seiviceImpl.UserImpl;


@RestController
@RequestMapping("/api/users")
public class UserContoller  {
	
	@Autowired
	private UserImpl userService;
	
	@Autowired
	private UserRepo userRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private  AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> authenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO) throws AuthenticationException {
        authenticateUser(authRequestDTO.getUsername(), authRequestDTO.getPassword());
		String accessToken = jwtService.GenerateToken(authRequestDTO.getEmail());
		JwtResponseDTO jwtResponseDTO = new JwtResponseDTO(accessToken);
		jwtResponseDTO.setAccessToken(accessToken);
		return ResponseEntity.ok(jwtResponseDTO);
    }

    private void authenticateUser(String username, String password) {
        org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        if (!authentication.isAuthenticated()) {
            throw new BadCredentialsException("Authentication failed for user: " + username);
        }
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/ping")
    public String test() {
        try {
            return "Welcome";
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    } 

    @PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String result = userService.userRegistration(user);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/byEmail/")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
    	User user =userRepository.findByEmail(email);
    	//User user = userService.getUserByEmail(email);
        
           return     new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUserByEmail(@RequestParam String email) {
        userService.deleteUserByEmail(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUserByEmail(@RequestParam String email, @RequestBody User updatedUser) {
        String result = userService.updateUserByEmail(email, updatedUser);
        return result != null ?
                new ResponseEntity<>(result, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    

}
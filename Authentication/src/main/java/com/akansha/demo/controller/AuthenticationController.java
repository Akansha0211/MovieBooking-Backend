package com.akansha.demo.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akansha.demo.model.Role;
import com.akansha.demo.model.User;
import com.akansha.demo.repository.RoleRepository;
import com.akansha.demo.repository.UserRepository;
import com.akansha.demo.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletException;


@RestController
@RequestMapping("auth/v1")
@CrossOrigin(origins="*")
public class AuthenticationController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	private Map<String,String> mapObj = new HashMap<String,String>();
	
	
	// REGISTRATION
	@PostMapping("/addUser")
	public ResponseEntity<?> registerUser(@RequestBody User user){
		
//		if(userService.addUser(user) != null) {
//			return new ResponseEntity<User>(user, HttpStatus.CREATED);
//		}
//		return new ResponseEntity<String>("User registration failed", HttpStatus.CONFLICT);
//		
		
		Role role = user.getRole();		
		if(role != null && role.getName().equals("ROLE_CUSTOMER")) {
			if(userService.addUser(user) != null) {
				
				
				return new ResponseEntity<User>(user, HttpStatus.CREATED);
			}
			
			return new ResponseEntity<String>("User registration failed", HttpStatus.CONFLICT);
		}else {
			// role is not "ROLE_CUSTOMER or it is null, deny access
			return new ResponseEntity<String>("Access Denied", HttpStatus.UNAUTHORIZED);
		}
		
		
//		Role role = user.getRole();
//		if(role == null) {
//			return new ResponseEntity<String>("Invalid role", HttpStatus.BAD_REQUEST);
//		}
//		Role savedRole = roleRepo.findById(role.getId()).orElse(null);
//		if(savedRole == null) {
//			return new ResponseEntity<String>("Role not found", HttpStatus.BAD_REQUEST);
//		}
//		
//		user.setRole(savedRole);
//		User savdUser = userService.addUser(user);
//		if(savdUser != null) {
//			return new ResponseEntity<User>(savdUser,HttpStatus.CREATED);
//		}else {
//			return new ResponseEntity<String>("User registration failed", HttpStatus.CONFLICT);
//		}
	}
	
	public String generateToken(String username, String password) throws ServletException {
		
		String jwtToken;
		if(username == null || password == null) {
			throw new ServletException("Credentials cannot be null");
		}
		
		boolean flag = userService.loginUser(username, password);
		System.out.println(flag);
		if(!flag) {
			throw new ServletException("Please enter valid credentials");
		}
		// standard code for generating the token
		else jwtToken = Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+300000))
				.signWith(SignatureAlgorithm.HS256, "secret key").compact();
		
		return jwtToken;
	}
			
	// LOGIN
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user){
		
		try {
			String jwtToken = generateToken(user.getUserName(), user.getPassword());
			mapObj.put("message", "User successfully logged in");
			mapObj.put("Token", jwtToken);
			
			User user1 = userRepo.findByName(user.getUserName());

			int role_id = user1.getRole().getId();

			String role_name = user1.getRole().getName();
			System.out.println(role_name);
//			
			mapObj.put("roleName", role_name); // added this line as we need the roleName while role based in Frontend
		}
		catch(Exception e) {
			mapObj.put("message", "User not logged in");
			mapObj.put("Token", null);
			return new ResponseEntity<>(mapObj, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(mapObj, HttpStatus.OK);
	}
			
	// role based authorization is pending
	
//	private boolean hasRole(Authentication authentication, String roleName) {
//		if(authentication != null) {
//			UserDetails userDetails = (UserDetails)authentication.getPrincipal();
//			return userDetails.getAuthorities()
//					.stream()
//					.map(GrantedAuthority::getAuthority)
//					.anyMatch(role -> role.equals(roleName));
//		}
//		return false;
//	}
}

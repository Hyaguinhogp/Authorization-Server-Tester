package com.test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.entities.User;
import com.test.services.AuthService;
import com.test.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService service;
	
	@Autowired
	private AuthService authService;
	
	@GetMapping
	public ResponseEntity<Page<User>> findAdd(Pageable pageable){
		return ResponseEntity.ok().body(service.findAllPaged(pageable));
	}
	
	@GetMapping(path = "/profile")
	public ResponseEntity<User> findProfile(){
		User user = authService.Authenticated();
		
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping
	public ResponseEntity<User> PostUser(@RequestBody User user){
		user = service.NewUser(user);
		return ResponseEntity.ok().body(user);
	}
}

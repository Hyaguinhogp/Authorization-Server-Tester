package com.test.services;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.entities.User;
import com.test.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Transactional(readOnly = true)
	public User findByName(String name) {
		return repository.findByName(name);
	}
	
	@Transactional(readOnly = true)
	public Page<User> findAllPaged(Pageable pageable){
		Page<User> page = repository.findAll(pageable);
		return page;
	}
	
	@Transactional
	public User NewUser(User user) {
		User userInsert = user;
		userInsert.setPassword(passwordEncoder.encode(user.getPassword()));
		repository.save(userInsert);
		return userInsert;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByName(username);
		
		if(user == null) {
			logger.error("username not found: " + username);
			throw new UsernameNotFoundException("username not found");
		}
		logger.info("username found: " + username);
		return user;
	}
	
}

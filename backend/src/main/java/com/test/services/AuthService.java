package com.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.entities.User;
import com.test.exceptions.ForbiddenException;
import com.test.exceptions.UnauthorizedException;
import com.test.repositories.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	public User Authenticated() {
		
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			return userRepository.findByName(username);
		}
		catch(Exception e) {
			throw new UnauthorizedException("Ivalid user");
		}
	}
	
	public void validateSelfOrAdmin(Long userId) {
		User user = Authenticated();
		if(!user.getId().equals(userId) && !user.hasRole("ROLE_ADMIN")) {
			throw new ForbiddenException("Access denied");
		}
	}
}

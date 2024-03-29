package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	public User findByName(String name);
}

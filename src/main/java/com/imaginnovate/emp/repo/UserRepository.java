package com.imaginnovate.emp.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imaginnovate.emp.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUserNameIgnoreCase(String username);
	Optional<User> findByUserName(String username);
	
}

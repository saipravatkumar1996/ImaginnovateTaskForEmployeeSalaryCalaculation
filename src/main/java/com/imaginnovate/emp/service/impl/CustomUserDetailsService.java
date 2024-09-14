package com.imaginnovate.emp.service.impl;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.imaginnovate.emp.model.User;
import com.imaginnovate.emp.repo.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	private final UserRepository repository;

	public CustomUserDetailsService(UserRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUserNameIgnoreCase(username);
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassWord(),
				new ArrayList<>());
	}

}

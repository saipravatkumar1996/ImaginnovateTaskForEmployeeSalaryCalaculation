package com.imaginnovate.emp.service.impl;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.imaginnovate.emp.bean.AuthRequest;
import com.imaginnovate.emp.bean.AuthResponse;
import com.imaginnovate.emp.exception.UsernameNotFoundException;
import com.imaginnovate.emp.model.User;
import com.imaginnovate.emp.repo.UserRepository;
import com.imaginnovate.emp.service.IUserService;
import com.imaginnovate.emp.util.JwtService;

@Service
public class UserServiceImpl implements IUserService {
	
	private final UserRepository repo;
	
	private BCryptPasswordEncoder passwordEncoder;
	

	private final AuthenticationManager authenticationManager;
	
	private final JwtService service;
	
	public UserServiceImpl(JwtService service,AuthenticationManager authenticationManager,UserRepository repo,BCryptPasswordEncoder passwordEncoder) {
		this.repo=repo;
		this.passwordEncoder=passwordEncoder;
		this.authenticationManager=authenticationManager;
		this.service=service;
	}

	@Override
	public User userSave(User user) {
		user.setPassWord(passwordEncoder.encode(user.getPassWord()));
		return repo.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return repo.findAll();
	}

	@Override
	public User getUser(Integer id) {
		return repo.findById(id).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
	}

	@Override
	public AuthResponse userAuth(AuthRequest request) {
		AuthResponse response=new AuthResponse();
		User user=repo.findByUserName(request.getUserName()).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
		  authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                		user.getUserName(),
	                        request.getPassword()
	                )
	        );
	        String jwtToken = service.generateToken(user.getUserName());
	        jwtToken="Bearer "+jwtToken;
	        
	        response.setStatus("200");
	        response.setToken(jwtToken);
	        response.setMessage("success");
		return response;
	}

	

}

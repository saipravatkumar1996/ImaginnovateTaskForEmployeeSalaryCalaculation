package com.imaginnovate.emp.service;

import java.util.List;

import com.imaginnovate.emp.bean.AuthRequest;
import com.imaginnovate.emp.bean.AuthResponse;
import com.imaginnovate.emp.model.User;

public interface IUserService {
	
	public User userSave(User user);
	
	public List<User> getAllUser();
	
	public User getUser(Integer id);
	
	public AuthResponse userAuth(AuthRequest request);
	

}
